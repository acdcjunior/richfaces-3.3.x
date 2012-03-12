/*******************************************************************************
 * JBoss, Home of Professional Open Source
 * Copyright 2009, Red Hat, Inc. and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 *******************************************************************************/
package org.jboss.richfaces.integrationTest;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.jboss.test.selenium.AbstractSeleniumTestCase;
import org.jboss.test.selenium.waiting.Condition;
import org.jboss.test.selenium.waiting.Wait;
import org.jboss.test.selenium.waiting.Wait.Waiting;
import org.testng.ITestContext;
import org.testng.SkipException;
import org.testng.TestRunner;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

/**
 * 
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>, <a
 *         href="mailto:pjha@redhat.com">Prabhat Jha</a>
 * @version $Revision$
 * 
 */

public abstract class AbstractSeleniumRichfacesTestCase extends AbstractSeleniumTestCase {
	
    /**
     * context root can be used to obtaining full URL paths, is set to actual
     * tested application's context root
     */
    protected String contextRoot;

    /**
     * ContextPath will be used to retrieve pages from right URL. Don't hesitate
     * to use it in cases of building absolute URLs.
     */
    protected String contextPath;
    
    /**
     * Introduce some maven build properties
     */
    protected String mavenProjectBuildDirectory;	// usually ${project}/target
    protected String mavenResourcesDir;				// usually ${project}/target/test-classes
    protected boolean seleniumDebug;					// if used specified debug mode of selenium testing
    protected String browser;

    /**
     * predefined waitings to use in inheritors
     */
    protected Waiting waitModelUpdate = Wait.interval(100).timeout(30000);
    protected Waiting waitGuiInteraction = Wait.interval(100).timeout(500);

	/**
	 * Test listener used to logging to selenium's server.log via getEval()
	 * method (see {@link SeleniumLoggingTestListener})
	 * 
	 * Don't forget to use SeleniumLoggingTestListener.setSelenium(Selenium) to
	 * initialize selenium-side logging properly
	 */
	private static volatile SeleniumLoggingTestListener loggingTestListener;

    @BeforeSuite
    protected void registerSeleniumInListeners(ITestContext context) {
        loggingTestListener = new SeleniumLoggingTestListener();
        
        TestRunner runner = (TestRunner) context;
        runner.addTestListener(loggingTestListener);
    }

	@BeforeClass
	@Parameters( { "context.root", "context.path", "browser", "selenium.host", "selenium.port", "selenium.debug",
			"selenium.maximize", "maven.resources.dir", "maven.project.build.directory" })
	public void initializeParameters(String contextRoot, String contextPath, String browser, String seleniumDebug,
			String mavenResourcesDir, String mavenProjectBuildDirectory) {
		this.contextRoot = contextRoot;
		this.contextPath = contextPath;
		this.mavenResourcesDir = mavenResourcesDir;
		this.mavenProjectBuildDirectory = mavenProjectBuildDirectory;
		this.seleniumDebug = Boolean.valueOf(seleniumDebug);
		this.browser = browser;
	}

	/**
	 * Initializes context before each class run.
	 * 
	 * Parameters will be obtained from TestNG.
	 * 
	 * @param contextRoot
	 *            server's context root, e.g. http://localhost:8080/
	 * @param contextPath
	 *            context path to application in context of server's root (e.g.
	 *            /myapp)
	 * @param browser
	 *            used browser (e.g. "*firefox", see selenium reference API)
	 * @param seleniumPort
	 *            specifies on which port should selenium server run
	 */
	@BeforeClass(dependsOnMethods = { "initializeParameters", "isTestBrowserEnabled" })
	@Parameters( { "selenium.host", "selenium.port", "selenium.maximize" })
	public void initializeBrowser(String seleniumHost, String seleniumPort, String seleniumMaximize) {
		selenium = RichfacesSelenium.getInstance(seleniumHost, Integer.valueOf(seleniumPort), browser, contextRoot);
		selenium.start();
		allowInitialXpath();
		loadCustomLocationStrategies();
		loggingTestListener.setSelenium(selenium);
		
		if (Boolean.valueOf(seleniumMaximize)) {
			// focus and maximaze tested window
			selenium.windowFocus();
			selenium.windowMaximize();
		}
	}
	
	/**
	 * Uses selenium.addLocationStrategy to implement own strategies to locate
	 * items in the tested page
	 */
	private void loadCustomLocationStrategies() {
		// jQuery location strategy
		try {
			String jqueryLocationStrategy = IOUtils.toString(new FileReader(
					"src/test/resources/selenium-location-strategies/jquery-strategy.js"));
			selenium.addLocationStrategy("jquery", jqueryLocationStrategy);
		} catch (IOException ex) {
			throw new IllegalStateException(ex);
		}
	}

	/**
	 * Setup initial type of XPath to non-native version.
	 * 
	 * Use to return XPath settings to initial type.
	 */
    protected void allowInitialXpath() {
        selenium.allowNativeXpath("false");
    }

	/**
	 * Finalize context after each class run.
	 */
	@AfterClass
	public void finalizeBrowser() {
		loggingTestListener.setSelenium(null);
		selenium.close();
		selenium.stop();
		selenium = null;
	}

	@Parameters( { "internet-explorer-enabled", "firefox-enabled" })
	@BeforeClass(dependsOnMethods="initializeParameters")
	public void isTestBrowserEnabled(@Optional("true") String internetExplorerEnabled, @Optional("true") String firefoxEnabled) {
		boolean isTestBrowserEnabled = false;

		if (Boolean.valueOf(internetExplorerEnabled) && browserIsInternetExplorer()) {
			isTestBrowserEnabled = true;
		}

		if (Boolean.valueOf(firefoxEnabled) && browserIsFirefox()) {
			isTestBrowserEnabled = true;
		}

		if (!isTestBrowserEnabled) {
			throw new SkipException("The test isn't enabled for current browser");
		}
	}

	@BeforeMethod(alwaysRun = true)
	public void callLoadPage() {
		loadPage();
	}
	
	protected abstract void loadPage();

    private final String[] INTERNET_EXPLORER_PREFIXES = new String[] { "*iexplore", "*piiexplore", "*iehta" };
    private final String[] FIREFOX_PREFIXES = new String[] { "*firefox", "*pifirefox", "*chrome" };
    
    public boolean browserIsInternetExplorer() {
		return containsBrowserOneOfPrefixes(browser, INTERNET_EXPLORER_PREFIXES);
	}
    
    public boolean browserIsFirefox() {
		return containsBrowserOneOfPrefixes(browser, FIREFOX_PREFIXES);
	}
    
    private boolean containsBrowserOneOfPrefixes(String browser, String[] prefixes) {
    	for (String prefix : prefixes) {
			if (StringUtils.defaultString(browser).startsWith(prefix)) {
				return true;
			}
		}
		return false;
    }

    /**
     * Default implementation of obtaining properties for each class.
     * 
     * @see AbstractSeleniumTestCase (method getLoc(String,String))
     */
    @Override
    protected Properties getLocatorsProperties() {
        return getNamedPropertiesForClass(this.getClass(), "locators");
    }
    
    /**
     * Default implementation of obtaining properties for each class.
     * 
     * @see AbstractSeleniumTestCase (method getMess(String,String))
     */
    @Override
    protected Properties getMessagesProperties() {
        return getNamedPropertiesForClass(this.getClass(), "messages");
    }
	
    /**
     * An abstract implementation of test for testing source code of examples.
     * 
     * @param fieldset
     *            the number of the example
     * @param linkLabel
     *            the label of link, e.g. "View Source"
     * @param expected
     *            an array of strings that should be in the snippet
     */
    protected void abstractTestSource(int fieldset, String linkLabel, String[] expected) {
        final String prefix = format("jquery=fieldset:eq({0}) > div > div:has(span:textEndsWith({1}))", fieldset-1, linkLabel);
        
        scrollIntoView(prefix, true);
        
        assertFalse(isDisplayed(prefix + " > div"), "Source should not be visible -- it has to contain 'display: none;'.");
        
        // click on 'View xxx Source'
        waitForElement(prefix + " > span:eq(1)");
        selenium.click(prefix + " > span:eq(1)");

        waitForElement(prefix + " div[class*=viewsourcebody]");

        assertTrue(isDisplayed(prefix + " > div"), "Source should be visible -- it should not contain 'display: none;'.");
        
        String source = selenium.getText(prefix + " div.viewsourcediv");
        for (String str : expected) {
            assertTrue(source.contains(str), "The code should contain \"" + str + "\".");
        }

        // click on 'Hide'
        selenium.click(prefix + " > span:eq(0)");

        // wait while 'style' attribute changes
        Wait.until(new Condition() {
            public boolean isTrue() {
                return !isDisplayed(prefix + " > div");
            }
        });

        assertFalse(isDisplayed(prefix + " > div"), "Source should be hidden.");
    }

    /**
     * <p>
     * Opens new page on contextPath's location and then selects in menu the
     * item specified by componentName and waits for Component's page is opened.
     * </p>
     * 
     * <p>
     * If componen's page is already opened, skip phase of menu selection.
     * </p>
     * 
     * <p>
     * For opening specified tab of Component's page use
     * {@link #openTab(String)}.
     * </p>
     * 
     * @param componentName
     *            name of component given from components' menu on the left of
     *            RF Live Demo application
     */
	protected void openComponent(final String componentName) {

		final String LOC_MENU_ITEM = format("jquery=table.left_menu td.text a > span:textEquals('{0}')", componentName);

		// TODO needs to open clean page, see {@link
		// https://jira.jboss.org/jira/browse/RF-7640}
		selenium.getEval("selenium.doDeleteAllVisibleCookies()");

		// open context path of application
        selenium.open(contextPath);

		// wait for new page is opened
		selenium.waitForPageToLoad("5000");

		Wait.until(new Condition() {
			public boolean isTrue() {
				return selenium.isElementPresent(LOC_MENU_ITEM);
			}
		});

		// click the menu item
		selenium.click(LOC_MENU_ITEM);

		// wait for component's page opened
		waitModelUpdate.until(new Condition() {
			public boolean isTrue() {
				return isComponentPageActive(componentName);
			}
		});
	}

    private boolean isComponentPageActive(String componentName) {
        final String LOC_OUTPUT_COMPONENT_NAME = "jquery=body table.left_menu *.panel_documents strong";
        return componentName.equals(getTextOrNull(LOC_OUTPUT_COMPONENT_NAME));
    }

    /**
     * <p>
     * Opens specified tab on the Component's page.
     * </p>
     * 
     * <p>
     * If tab is already opened, skip phase of tab selection.
     * </p>
     * 
     * <p>
     * Use this method after opening page by {@link #openComponent(String)}.
     * </p>
     * 
     * @param tabTitle
     *            title on the tab header, which should be opened.
     */
    protected void openTab(String tabTitle) {

        final String LOC_TAB = format(
                "jquery=form[id$='_form'] td.rich-tab-header:contains('{0}')", tabTitle);

        if (belongsClass("rich-tab-active", LOC_TAB)) {
            return;
        }

        waitForElement(LOC_TAB);
        selenium.click(LOC_TAB);

        waitModelUpdate.until(new Condition() {
            public boolean isTrue() {
                return belongsClass("rich-tab-active", LOC_TAB);
            }
        });
    }

    /**
     * Controls that for each method should be started new browser session.
     * 
     * It means that after each method except last is browser session stopped
     * and before each method except first is started clean browser session.
     */
    protected void setCleanSessionForEachMethod(boolean cleanSessionForEachMethod) {
        this.cleanSessionForEachMethod = cleanSessionForEachMethod;
    }

    private boolean cleanSessionForEachMethod = false;

    @BeforeMethod
    public void startBrowserIfNotFirstTestMethod(ITestContext context) {
        if (cleanSessionForEachMethod) {
            if (0 < getRunnedTestCount(context)) {
                selenium.start();
            }
        }
    }

    @AfterMethod
    public void stopBrowserIfNotLastTestMethod(ITestContext context) {
        if (cleanSessionForEachMethod) {
            if (getRunnedTestCount(context) + 1 < context.getAllTestMethods().length) {
                selenium.stop();
            }
        }
    }

    private int getRunnedTestCount(ITestContext context) {
        return context.getPassedTests().size() + context.getSkippedTests().size() + context.getFailedTests().size();
    }
}
