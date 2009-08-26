package org.jboss.richfaces.integrationTest.ajaxRegion;

import junit.framework.Assert;

import org.apache.commons.lang.StringUtils;
import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.jboss.test.selenium.waiting.Condition;
import org.jboss.test.selenium.waiting.Not;
import org.jboss.test.selenium.waiting.Wait;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public class AjaxRegionTestCase extends AbstractSeleniumRichfacesTestCase {
	/**
	 * Opens specified page
	 */
	private void openPage() {
		selenium.open(contextPath + "/richfaces/region.jsf?c=region&tab=usage");
	}
	
	private String input = getMess("ajax-region--input");
	private String firedEvent = getMess("ajax-region--fired-event");
	private String name1 = formatLoc("ajax-region--input", 1, 1, 1);
	private String job1 = formatLoc("ajax-region--input", 1, 1, 2);
	private String name2 = formatLoc("ajax-region--input", 1, 2, 1);
	private String name3 = formatLoc("ajax-region--input", 2, 1, 1);
	private String name4 = formatLoc("ajax-region--input", 2, 2, 1);
	private String messages = getLoc("ajax-region--messages");
	private String outname = formatLoc("ajax-region--typed-name", "");
	private String outname2 = formatLoc("ajax-region--typed-name", "2");
	private String textDisappears = formatLoc("ajax-region--influenced-text", 3);
	private String textNotDisappears = formatLoc("ajax-region--influenced-text", 4);
	
	@Test
	public void testName1DontPassValidation() {
		openPage();
		name1DontPassValidation();
	}
	
	@Test
	public void testJob1ChangeDontInfluenceName1ValidationError() {
		testName1DontPassValidation();
		job1ChangeInfluenceName1Validation();
	}
	
	@Test
	public void testName1CanBeChangedAnywayIfJob1IsNotBlank() {
		testJob1ChangeDontInfluenceName1ValidationError();
		name1CanBeChangedAnywayIfJob1IsNotBlank();
	}
	
	@Test
	public void test2CanBeChangedAnyway() {
		openPage();
		trySomeInputs(name2);
	}
	
	@Test
	public void textWillDisappear() {
		openPage();
		
		Assert.assertTrue(StringUtils.isNotBlank(selenium.getText(textDisappears)));
		
		selenium.type(name3, input);
		selenium.fireEvent(name3, "keyup");
		
		Wait.until(new Condition() {
			public boolean isTrue() {
				return formatMess("ajax-region--typed-name", input(input)).equals(selenium.getText(outname2));
			}
		});
		
		Assert.assertTrue(StringUtils.isBlank(selenium.getText(textDisappears)));
	}
	
	@Test
	public void textWillNotDisappear() {
		openPage();
		
		Assert.assertTrue(StringUtils.isNotBlank(selenium.getText(textNotDisappears)));
		
		selenium.type(name4, input);
		selenium.fireEvent(name4, "keyup");
		
		Wait.until(new Condition() {
			public boolean isTrue() {
				return formatMess("ajax-region--typed-name", input(input)).equals(selenium.getText(outname2));
			}
		});
		
		Assert.assertTrue(StringUtils.isNotBlank(selenium.getText(textNotDisappears)));
	}
	
	public void name1DontPassValidation() {
		selenium.type(name1, input);
		selenium.fireEvent(name1, firedEvent);
		
		Wait.until(new Condition() {
			public boolean isTrue() {
				if (!selenium.isElementPresent(messages)) return false;
				return selenium.getText(messages).contains(getMess("ajax-region--value-is-required"));
			}
		});
		
		Assert.assertEquals(selenium.getText(outname), formatMess("ajax-region--typed-name", ""));
	}
	
	public void job1ChangeInfluenceName1Validation() {
		Assert.assertTrue(selenium.getText(messages).contains(getMess("ajax-region--value-is-required")));
		
		selenium.type(job1, input);
		selenium.fireEvent(name1, "keyup");
		
		Wait.until(new Not() {
			public boolean not() {
				return selenium.isElementPresent(messages);
			}
		});
		
		Assert.assertEquals(selenium.getText(outname), formatMess("ajax-region--typed-name", input(input)));
	}
	
	public void name1CanBeChangedAnywayIfJob1IsNotBlank() {
		Assert.assertFalse(selenium.isElementPresent(messages));
		Assert.assertTrue(StringUtils.isNotBlank(selenium.getValue(job1)));
		
		trySomeInputs(name1);
	}
	
	public void trySomeInputs(String locator) {
		for (final String enter : new String[]{"", input}) {
			selenium.type(locator, enter);
			selenium.fireEvent(locator, "keyup");
			
			Wait.until(new Condition() {
				public boolean isTrue() {
					return formatMess("ajax-region--typed-name", input(enter)).equals(selenium.getText(outname));
				}
			});
			
			Assert.assertFalse(selenium.isElementPresent(messages));
		}
	}
	
	public String input(String input) {
		if (StringUtils.isBlank(input)) {
			return "";
		} else {
			return " " + input;
		}
	}
}
