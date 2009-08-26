package org.jboss.richfaces.integrationTest.ajaxForm;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.Wait;

/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public class AjaxFormTestCase extends AbstractSeleniumRichfacesTestCase {
	/**
	 * Opens specified page
	 */
	private void openPage() {
		selenium.open(contextPath + "/richfaces/form.jsf?c=form&tab=usage");

		scrollIntoView(header, true);
	}

	private String header = "//legend[text()='AjaxForm Demo']";
	private String ajaxButton = getLoc("ajax-form--ajax-button");
	private String nonAjaxButton = getLoc("ajax-form--non-ajax-button");
	private String textRelative = "ajax-form--text-relative";
	private String ajaxText = formatLoc(textRelative, ajaxButton);
	private String nonAjaxText = formatLoc(textRelative, nonAjaxButton);

	@Test
	public void testNonAjaxSubmit() {
		openPage();
		nonAjaxSubmit();
	}

	@Test
	public void testAjaxSubmit() {
		openPage();
		ajaxSubmit();
	}

	@Test
	public void testInterleaving() {
		openPage();
		ajaxSubmit();
		nonAjaxSubmit();
		ajaxSubmit();
		nonAjaxSubmit();
	}

	public void nonAjaxSubmit() {
		String expected = getMess("ajax-form--non-ajax-result");

		selenium.click(nonAjaxButton);
		selenium.waitForPageToLoad(String.valueOf(Wait.DEFAULT_TIMEOUT));
		scrollIntoView(header, true);

		String actual = selenium.getText(ajaxText);
		Assert.assertEquals(actual, expected);

		actual = selenium.getText(nonAjaxText);
		Assert.assertEquals(actual, expected);
	}

	public void ajaxSubmit() {
		String expected = getMess("ajax-form--ajax-result");

		String startingNonAjaxText = selenium.getText(nonAjaxText);

		selenium.click(ajaxButton);
		waitForTextEquals(ajaxText, expected);

		String actual = selenium.getText(nonAjaxText);
		Assert.assertEquals(actual, startingNonAjaxText);
	}
}
