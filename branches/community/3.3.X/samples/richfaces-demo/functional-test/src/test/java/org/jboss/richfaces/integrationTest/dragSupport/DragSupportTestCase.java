package org.jboss.richfaces.integrationTest.dragSupport;

import java.util.regex.Pattern;

import junit.framework.Assert;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.jboss.test.selenium.actions.Drag;
import org.jboss.test.selenium.waiting.Condition;
import org.jboss.test.selenium.waiting.Wait;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public class DragSupportTestCase extends AbstractSeleniumRichfacesTestCase {
	{
		// each test will be ran in separated browser session
		setCleanSessionForEachMethod(true);
	}
	
	/**
	 * Opens specified page
	 */
	private void openPage() {
		selenium.open(contextPath
				+ "/richfaces/dragSupport.jsf?c=dragSupport&tab=usage");
		scrollIntoView(header, true);
	}

	private String header = getLoc("drag-support--header");
	private String indicator = getLoc("drag-support--div--indicator");
	private String classIndicator = getLoc("drag-support--class--indicator");
	private String imageSourceIndicator = getLoc("drag-support--image-source--indicator");
	private String itemTexts = getMess("drag-support--text--items");
	private String itemPattern = getLoc("drag-support--div--item");
	private String targets = getLoc("drag-support--div--targets");
	private String relativeFirstInsertedItem = getLoc("drag-support--relative--first-inserted-item");
	private String regexpMovingClass = getMess("drag-support--regexp--moving--class");
	private String regexpMovingImageSource = getMess("drag-support--regexp--moving--image-source");
	private String regexpAcceptingClass = getMess("drag-support--regexp--accepting--class");
	private String regexpAcceptingImageSource = getMess("drag-support--regexp--accepting--image-source");
	private String regexpRejectingClass = getMess("drag-support--regexp--rejecting--class");
	private String regexpRejectingImageSource = getMess("drag-support--regexp--rejecting--image-source");

	@Test
	public void testMoving() {
		accepting(0);
	}

	@Test
	public void testAcceptingEnter() {
		accepting(1);
	}

	@Test
	public void testAcceptingDrop() {
		accepting(2);
	}

	@Test
	public void testRejectingEnter() {
		rejecting(1);
	}

	@Test
	public void testRejectingDrop() {
		rejecting(2);
	}

	private void accepting(int phase) {
		openPage();

		String itemText = format(itemTexts, 1);
		String item = format(itemPattern, itemText);
		String target = format(targets, 1);

		if (phase == 0) {
			Assert.assertTrue(selenium.isElementPresent(indicator));
			Assert.assertEquals("none", getStyle(indicator, "display"));
		}

		Drag drag = new Drag(selenium, item, target);

		drag.move();

		if (phase == 0) {
			Wait.until(new Condition() {
				public boolean isTrue() {
					return "block".equals(getStyle(indicator, "display"));
				}
			});

			Assert.assertTrue(Pattern.matches(regexpMovingClass, selenium
					.getAttribute(classIndicator)));
			Assert.assertTrue(Pattern.matches(regexpMovingImageSource, selenium
					.getAttribute(imageSourceIndicator)));
		}

		drag.enter();

		if (phase == 1) {
			Wait.until(new Condition() {
				public boolean isTrue() {
					return "block".equals(getStyle(indicator, "display"));
				}
			});

			Assert.assertEquals("block", getStyle(indicator, "display"));
			Assert.assertTrue(Pattern.matches(regexpAcceptingClass, selenium
					.getAttribute(classIndicator)));
			Assert.assertTrue(Pattern.matches(regexpAcceptingImageSource,
					selenium.getAttribute(imageSourceIndicator)));
		}

		String firstInsertedItem = format(relativeFirstInsertedItem, target);

		if (phase == 2) {
			Assert.assertFalse(selenium.isElementPresent(firstInsertedItem));
		}

		drag.drop();

		if (phase == 2) {
			Wait.until(new Condition() {
				public boolean isTrue() {
					return "none".equals(getStyle(indicator, "display"));
				}
			});

			Assert.assertTrue(selenium.isElementPresent(firstInsertedItem));
			Assert.assertFalse(selenium.isElementPresent(item));

			String actual = selenium.getText(firstInsertedItem);
			Assert.assertEquals(itemText, actual);
		}
	}

	private void rejecting(int phase) {
		openPage();

		String itemText = format(itemTexts, 1);
		String item = format(itemPattern, itemText);
		String target = format(targets, 2);

		Drag drag = new Drag(selenium, item, target);
		drag.move();
		drag.enter();

		if (phase == 1) {
			Wait.until(new Condition() {
				public boolean isTrue() {
					return "block".equals(getStyle(indicator, "display"));
				}
			});

			Assert.assertTrue(Pattern.matches(regexpRejectingClass, selenium
					.getAttribute(classIndicator)));
			Assert.assertTrue(Pattern.matches(regexpRejectingImageSource,
					selenium.getAttribute(imageSourceIndicator)));
		}

		String firstInsertedItem = format(relativeFirstInsertedItem, target);

		if (phase == 2) {
			Assert.assertFalse(selenium.isElementPresent(firstInsertedItem));
		}

		drag.drop();

		if (phase == 2) {
			Wait.until(new Condition() {
				public boolean isTrue() {
					return "none".equals(getStyle(indicator, "display"));
				}
			});

			Assert.assertFalse(selenium.isElementPresent(firstInsertedItem));
			Assert.assertTrue(selenium.isElementPresent(item));
		}
	}
}
