package org.jboss.richfaces.integrationTest.script;

import junit.framework.Assert;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.jboss.test.selenium.waiting.Condition;
import org.jboss.test.selenium.waiting.Wait;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public class ScriptTestCase extends AbstractSeleniumRichfacesTestCase {
	/**
	 * Opens specified page
	 */
	private void openPage() {
		selenium.open(contextPath
				+ "/richfaces/script.jsf?c=loadScript&tab=usage");
		scrollIntoView(header, true);
	}

	private String header = getLoc("script--header");
	private String buttonHide = getLoc("script--button--hide");
	private String buttonShow = getLoc("script--button--show");
	private String inputName = getLoc("script--input--name");
	private String inputJob = getLoc("script--input--job");
	private String buttonSubmit = getLoc("script--button--submit");
	private String outputName = getLoc("script--output--name");
	private String outputJob = getLoc("script--output--job");
	private String panelMyPanel = getLoc("script--panel--mypanel");
	private String messName = getMess("script--input--name");
	private String messJob = getMess("script--input--job");
	private String prefixName = getMess("script--regexp--name-prefix");
	private String prefixJob = getMess("script--regexp--job-prefix");

	@Test
	public void testHide() {
		openPage();

		hide();
	}

	@Test
	public void testHideAndShow() {
		openPage();

		hide();
		show();
	}

	@Test
	public void testFillIn() {
		openPage();

		fillIn();
		submit();
		validateOutput();
	}

	@Test
	public void testFillInHideAndShow() {
		testFillIn();
		hide();
		show();
		validateOutput();
		submit();
		validateOutput();
	}

	private void fillIn() {
		selenium.type(inputName, messName);
		selenium.type(inputJob, messJob);
	}

	private void submit() {
		// remember output values before submit
		final String name = getName();
		final String job = getJob();

		// click on Submit
		selenium.click(buttonSubmit);

		// wait until output changes or 5 sec
		Wait.dontFail().timeout(5000).until(new Condition() {
			public boolean isTrue() {
				// name or job changes
				return !name.equals(getName()) || !job.equals(getJob());
			}
		});
	}

	private void validateOutput() {
		Assert.assertEquals(messName, getName());
		Assert.assertEquals(messJob, getJob());
	}

	private String getName() {
		return selenium.getText(outputName).replaceFirst(prefixName, "");
	}

	private String getJob() {
		return selenium.getText(outputJob).replaceFirst(prefixJob, "");
	}

	private void hide() {
		selenium.click(buttonHide);

		Wait.until(new Condition() {
			public boolean isTrue() {
				return "none".equals(getStyle(panelMyPanel, "display"));
			}
		});
	}

	private void show() {
		selenium.click(buttonShow);

		Wait.until(new Condition() {
			public boolean isTrue() {
				return "block".equals(getStyle(panelMyPanel, "display"));
			}
		});
	}
}
