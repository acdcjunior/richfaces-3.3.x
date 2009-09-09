package org.jboss.richfaces.integrationTest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jboss.test.selenium.AbstractSeleniumTestCase;
import org.jboss.test.selenium.waiting.Condition;
import org.jboss.test.selenium.waiting.Wait;
import org.jboss.test.selenium.waiting.Wait.Waiting;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestRunner;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.thoughtworks.selenium.DefaultSelenium;

/**
 * 
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>, <a
 *         href="mailto:pjha@redhat.com">Prabhat Jha</a>
 * @version $Revision$
 * 
 */

public class AbstractSeleniumRichfacesTestCase extends AbstractSeleniumTestCase {

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
     * predefined waitings to use in inheritors
     */
    protected Waiting waitModelUpdate = Wait.interval(100).timeout(10000);
    protected Waiting waitGuiInteraction = Wait.interval(100).timeout(500);
    

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
    @BeforeClass
    @Parameters( { "context.root", "context.path", "browser", "selenium.port" })
    public void initializeContext(String contextRoot, String contextPath, String browser, String seleniumPort) {
        this.contextRoot = contextRoot;
        this.contextPath = contextPath;
        selenium = new DefaultSelenium("localhost", Integer.valueOf(seleniumPort), browser, contextRoot);
        selenium.start();
        allowInitialXpath();
    }

    protected void allowInitialXpath() {
        selenium.allowNativeXpath("false");
    }

    /**
     * Finalize context after each class run.
     */
    @AfterClass
    public void finalizeContext() {
        selenium.stop();
        selenium = null;
        contextPath = null;
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
     * Add test listener to get advanced logging and other possibilities.
     * 
     * @param context
     *            will be injected by TestNG
     */
    @BeforeTest
    protected void addTestListeners(ITestContext context) {
        TestRunner runner = (TestRunner) context;
        runner.addTestListener(testListener);
    }

    /**
     * Implementation of listener, which will trigger each test run.
     */
    private ITestListener testListener = new ITestListener() {

        /**
         * Will print to log name of test before each test run.
         */
        public void onTestStart(ITestResult result) {
            String methodName = result.getMethod().toString();
            Matcher matcher = Pattern.compile(".*\\.(.*\\..*)").matcher(methodName);

            if (matcher.lookingAt()) {
                methodName = matcher.group(1);
            }

            String hashes = "##########";
            System.out.println(String.format("%s %s %s", hashes, methodName, hashes));
        }

        public void onFinish(ITestContext context) {
        }

        public void onStart(ITestContext context) {
        }

        public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        }

        public void onTestFailure(ITestResult result) {
        }

        public void onTestSkipped(ITestResult result) {
        }

        public void onTestSuccess(ITestResult result) {
        }
    };

    /**
     * Abstract test for testing source code of examples. Deprecated -- use
     * abstractTestSource(int, String, String) instead of this method.
     * 
     * @param fieldset
     * @param index
     * @param first
     * @param second
     */
    @Deprecated
    protected void abstractTestSource(int fieldset, int index, String first, String second) {
        String xpathPrefix = String.format("//fieldset[%d]/div/div[%d]", fieldset, index);
        String text = null;

        scrollIntoView(xpathPrefix, true);

        text = selenium.getAttribute(xpathPrefix + "/div@style");
        assertTrue(text.contains("display: none;"),
                "Source should not be visible -- it has to contain 'display: none;'.");

        // click on 'View xxx Source'
        waitForElement(xpathPrefix + "/span[2]");
        selenium.click(xpathPrefix + "/span[2]");

        waitForElement(xpathPrefix + "/div/div[2]");

        text = selenium.getAttribute(xpathPrefix + "/div@id");
        assertFalse(text.contains("display: none;"),
                "Source should be visible -- it should not contain 'display: none;'");

        text = selenium.getText(xpathPrefix + "/div/div[2]/div/div/span[1]");
        assertEquals(text, first, "The code should start with '" + first + "'.");

        text = selenium.getText(xpathPrefix + "/div/div[2]/div/div/span[2]");
        assertEquals(text, second);

        // click on 'Hide'
        selenium.click(xpathPrefix + "/span[1]");

        // wait while 'style' attribute changes
        for (int i = 0; i * 100 <= 10000; i++) {
            text = selenium.getAttribute(xpathPrefix + "/div@style");
            if (text.contains("display: none;")) {
                break;
            }
            waitFor(100);
        }

        assertTrue(text.contains("display: none;"), "Source should be hidden.");
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
        final String xpathPrefix = String.format("//fieldset[%d]/div/div/span[contains(text(), '%s')]/..", fieldset,
                linkLabel);
        String text = null;

        scrollIntoView(xpathPrefix, true);

        text = selenium.getAttribute(xpathPrefix + "/div@style");
        assertTrue(text.contains("display: none;"),
                "Source should not be visible -- it has to contain 'display: none;'.");

        // click on 'View xxx Source'
        waitForElement(xpathPrefix + "/span[2]");
        selenium.click(xpathPrefix + "/span[2]");

        waitForElement(xpathPrefix + "/div/div[2]");

        text = selenium.getAttribute(xpathPrefix + "/div@id");
        assertFalse(text.contains("display: none;"),
                "Source should be visible -- it should not contain 'display: none;'");

        text = selenium.getText(xpathPrefix + "/div/div[2]/div/div");

        for (String str : expected) {
            assertTrue(text.contains(str), "The code should contain \"" + str + "\".");
        }

        // click on 'Hide'
        selenium.click(xpathPrefix + "/span[1]");

        // wait while 'style' attribute changes
        Wait.until(new Condition() {
            public boolean isTrue() {
                return selenium.getAttribute(xpathPrefix + "/div@style").contains("display: none;");
            }
        });

        text = selenium.getAttribute(xpathPrefix + "/div@style");
        assertTrue(text.contains("display: none;"), "Source should be hidden.");
    }

    /**
     * <p>
     * <b>Deprecated.</b> <i>This method is replaced by method
     * {@link #openComponent(String)} and {{@link #openTab(String)}</i>
     * </p>
     * 
     * Loads the page defined in contextPath. There is a 5-second timeout.
     * 
     * @param group
     *            ID of div element that represents a group of menu items in
     *            main menu, e.g. ajaxSupport,richInputs, richValidators, etc.
     * @param index
     *            which menu item from the group should be clicked, numbered
     *            from 1
     * @param text
     *            wait while the specified text appears on the page; it does not
     *            wait if the text is null
     */
    // TODO: remove this @deprecated method
    @Deprecated
    protected void loadPage(String group, int index, String text) {
        loadPage(group, index, 1, text);
    }

    /**
     * <p>
     * <b>Deprecated.</b> <i>This method is replaced by method
     * {@link #openComponent(String)} and {{@link #openTab(String)}</i>
     * </p>
     * 
     * Loads the page defined in contextPath. There is a 5-second timeout.
     * 
     * @param group
     *            ID of div element that represents a group of menu items in
     *            main menu, e.g. ajaxSupport,richInputs, richValidators, etc.
     * @param index
     *            which menu item from the group should be clicked, numbered
     *            from 1
     * @param tab
     *            the number of the tab
     * @param text
     *            wait while the specified text appears on the page; it does not
     *            wait if the text is null
     */
    // TODO: remove this @deprecated method
    @Deprecated
    protected void loadPage(String group, int index, int tab, String text) {

        selenium.open(contextPath);
        selenium.waitForPageToLoad("5000");

        String menuItem = String.format("//div[@id='%s']/div[3]/table/tbody/tr/td/table/tbody/tr[%d]/td[2]/a", group,
                index);

        // click the group
        selenium.click(group);
        // click the menu item
        selenium.click(menuItem);

        waitForElement("//table[@class='content_container']/tbody/tr/td[2]/table/tbody/tr[1]/td/form/table/tbody/tr/td["
                + (tab * 2) + "]/table");
        selenium
                .click("//table[@class='content_container']/tbody/tr/td[2]/table/tbody/tr[1]/td/form/table/tbody/tr/td["
                        + (tab * 2) + "]/table");

        if (text != null) {
            waitForText(text);
        }
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

        final String LOC_MENU_ITEM = format(
                "//table[@class='left_menu']//td[contains(@class, 'text')]//a/span[normalize-space(text())='{0}']",
                componentName);

        // TODO needs to open clean page, see {@link
        // https://jira.jboss.org/jira/browse/RF-7640}
        selenium.getEval("selenium.doDeleteAllVisibleCookies()");

        // open context path of application
        selenium.open(contextPath);

        // wait for new page is opened
        selenium.waitForPageToLoad("5000");

        // if component's page is already opened, skin selecting from menu
        if (isComponentPageActive(componentName)) {
            return;
        }

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
        final String LOC_OUTPUT_COMPONENT_NAME = "//body/table[@class='left_menu']//*[contains(@class,'panel_documents')]//strong";
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
                "//form[contains(@id, ':_form')]//td[contains(@class,'rich-tab-header') and text()='{0}']", tabTitle);

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
