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
package org.jboss.richfaces.integrationTest.dragSupport;

import java.util.regex.Pattern;

import static org.testng.Assert.*;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.jboss.test.selenium.actions.Drag;
import org.jboss.test.selenium.waiting.Condition;
import org.jboss.test.selenium.waiting.Wait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public class DragSupportTestCase extends AbstractSeleniumRichfacesTestCase {

	private final String LOC_FIELDSET_HEADER = getLoc("FIELDSET_HEADER");
	private final String LOC_DIV_DRAG_INDICATOR = getLoc("DIV_DRAG_INDICATOR");
	private final String LOC_CLASS_DRAG_INDICATOR = getLoc("CLASS_DRAG_INDICATOR");
	private final String LOC_IMGSRC_DRAG_INDICATOR = getLoc("IMGSRC_DRAG_INDICATOR");
	private final String LOC_CHOICES_OF_DIV_TARGETS = getLoc("CHOICES_OF_DIV_TARGETS");
	private final String LOC_TBODY_TARGET_ITEMS_AREA_RELATIVE = getLoc("TBODY_TARGET_ITEMS_AREA_RELATIVE");
	private final String LOC_DIV_DRAGGED_ITEM_PREFORMATTED = getLoc("DIV_DRAGGED_ITEM_PREFORMATTED");

	private final String MSG_CHOICES_FRAMEWORKS = getMsg("CHOICES_FRAMEWORKS");
	private final String MSG_REGEXP_CLASS_OF_MOVING = getMsg("REGEXP_CLASS_OF_MOVING");
	private final String MSG_REGEXP_IMGSRC_OF_MOVING = getMsg("REGEXP_IMGSRC_OF_MOVING");
	private final String MSG_REGEXP_CLASS_OF_ACCEPTING = getMsg("REGEXP_CLASS_OF_ACCEPTING");
	private final String MSG_REGEXP_IMGSRC_OF_ACCEPTING = getMsg("REGEXP_IMGSRC_OF_ACCEPTING");
	private final String MSG_REGEXP_CLASS_OF_REJECTING = getMsg("REGEXP_CLASS_OF_REJECTING");
	private final String MSG_REGEXP_IMGSRC_OF_REJECTING = getMsg("REGEXP_IMGSRC_OF_REJECTING");

	/**
	 * Drag item to accepting target and checks that drag indicator is present
	 * before start dragging and has 'display: none'. Then start drag and checks
	 * that indicator goes displayed and checks that image source of dragged
	 * item and its class is as expecting.
	 */
	@Test
	public void testMoving() {
		accepting(0);
	}

	/**
	 * Drag item to accepting target. Checks that when enter target item the
	 * indicator changed class and image's src to accepting ones (as
	 * predefined).
	 */
	@Test
	public void testAcceptingEnter() {
		accepting(1);
	}

	/**
	 * Drag item to accepting target. Checks that when item entered target, the
	 * first item is still in framework list, and when dropped, wait for drag
	 * indicator disappear, then checks that dragged item already disappeared
	 * from framework list and also appeared in drag target (box).
	 */
	@Test
	public void testAcceptingDrop() {
		accepting(2);
	}

	/**
	 * Drad item to rejecting target. Checks that when item entered rejecting
	 * target, the drag indicator (check its img-src and class) changes to
	 * rejecting one.
	 */
	@Test
	public void testRejectingEnter() {
		rejecting(1);
	}

	/**
	 * Drag item to rejecting target. Checks that when item dropped on rejecting
	 * target, no item will appear in target and no item disappear from
	 * framework list.
	 */
	@Test
	public void testRejectingDrop() {
		rejecting(2);
	}

	private void accepting(int phase) {
		String itemText = format(MSG_CHOICES_FRAMEWORKS, 1);
		String item = format(LOC_DIV_DRAGGED_ITEM_PREFORMATTED, 1);
		String target = format(LOC_CHOICES_OF_DIV_TARGETS, 0);

		if (phase == 0) {
			assertTrue(selenium.isElementPresent(LOC_DIV_DRAG_INDICATOR),
					"Drag indicator isn't present (in DOM) at initial state");
			assertEquals(getStyle(LOC_DIV_DRAG_INDICATOR, "display"), "none",
					"Drag indicator is displayed as not expected");
		}

		Drag drag = new Drag(selenium, item, target);
		
		drag.move();

		if (phase == 0) {
			Wait.failWith("Drag indicator never get displayed").until(new Condition() {
				public boolean isTrue() {
					return "block".equals(getStyle(LOC_DIV_DRAG_INDICATOR, "display"));
				}
			});

			String actual = selenium.getAttribute(LOC_CLASS_DRAG_INDICATOR);
			assertTrue(Pattern.matches(MSG_REGEXP_CLASS_OF_MOVING, actual), format(
					"The class of indicator '{0}' doesn't match '{1}'", actual, MSG_REGEXP_CLASS_OF_MOVING));

			actual = selenium.getAttribute(LOC_IMGSRC_DRAG_INDICATOR);
			assertTrue(Pattern.matches(MSG_REGEXP_IMGSRC_OF_MOVING, actual), format(
					"The image source of indicator '{0}' doesn't match '{1}", actual, MSG_REGEXP_IMGSRC_OF_MOVING));
		}
		
		drag.enter();

		if (phase == 1) {
			Wait.failWith("Drag indicator wasn't displayed").until(new Condition() {
				public boolean isTrue() {
					return "block".equals(getStyle(LOC_DIV_DRAG_INDICATOR, "display"));
				}
			});

			String actual = selenium.getAttribute(LOC_CLASS_DRAG_INDICATOR);
			assertTrue(Pattern.matches(MSG_REGEXP_CLASS_OF_ACCEPTING, actual), format(
					"The class of indicator '{0}' doesn't match '{1}'", actual, MSG_REGEXP_CLASS_OF_ACCEPTING));

			actual = selenium.getAttribute(LOC_IMGSRC_DRAG_INDICATOR);
			assertTrue(Pattern.matches(MSG_REGEXP_IMGSRC_OF_ACCEPTING, actual), format(
					"The image source of indicator '{0}' doesn't match '{1}", actual, MSG_REGEXP_IMGSRC_OF_ACCEPTING));
		}

		final String locTargetItemsArea = format(LOC_TBODY_TARGET_ITEMS_AREA_RELATIVE, target);
		
		if (phase == 2) {
			assertEquals(selenium.getText(locTargetItemsArea), "",
					"There was some text in target area which was expected to be empty");
		}

		drag.drop();
		
		if (phase == 2) {
			Wait.failWith("Drag indicator never disappeared when item dropped").until(new Condition() {
				public boolean isTrue() {
					return "none".equals(getStyle(LOC_DIV_DRAG_INDICATOR, "display"));
				}
			});

			assertEquals(selenium.getText(locTargetItemsArea), itemText,
					"There is no item in target or the first and only inserted item hasn't same text as dragged item");
			assertFalse(selenium.getText(item).contains(itemText),
					"The dragged item was probably still in framework list after drop to accepting target");
		}
	}

	private void rejecting(int phase) {
		String itemText = format(MSG_CHOICES_FRAMEWORKS, 1);
		String item = format(LOC_DIV_DRAGGED_ITEM_PREFORMATTED, 1);
		String target = format(LOC_CHOICES_OF_DIV_TARGETS, 1);

		Drag drag = new Drag(selenium, item, target);
		drag.move();
		drag.enter();

		if (phase == 1) {
			Wait.until(new Condition() {
				public boolean isTrue() {
					return "block".equals(getStyle(LOC_DIV_DRAG_INDICATOR, "display"));
				}
			});
			
			String actual = selenium.getAttribute(LOC_CLASS_DRAG_INDICATOR);
			assertTrue(Pattern.matches(MSG_REGEXP_CLASS_OF_REJECTING, actual), format(
					"The class of indicator '{0}' doesn't match '{1}'", actual, MSG_REGEXP_CLASS_OF_REJECTING));

			actual = selenium.getAttribute(LOC_IMGSRC_DRAG_INDICATOR);
			assertTrue(Pattern.matches(MSG_REGEXP_IMGSRC_OF_REJECTING, actual), format(
					"The image source of indicator '{0}' doesn't match '{1}", actual, MSG_REGEXP_IMGSRC_OF_REJECTING));
		}

		final String locTargetItemsArea = format(LOC_TBODY_TARGET_ITEMS_AREA_RELATIVE, target);
		
		if (phase == 2) {
			assertEquals(selenium.getText(locTargetItemsArea), "",
					"There was some text in target area which was expected to be empty");
		}

		drag.drop();

		if (phase == 2) {
			Wait.failWith("Drag indicator never disappeared when item dropped").until(new Condition() {
				public boolean isTrue() {
					return "none".equals(getStyle(LOC_DIV_DRAG_INDICATOR, "display"));
				}
			});
			
			assertFalse(selenium.getText(locTargetItemsArea).contains(itemText),
					"There was item inserted in target but no item expected when dropped to rejecting target");
			assertTrue(selenium.getText(item).contains(itemText),
					"The dragged item disappeared as not expected when dropped to rejecting target");
		}
	}

	protected void loadPage() {
		openComponent("Drag Support");
		scrollIntoView(LOC_FIELDSET_HEADER, true);
		selenium.allowNativeXpath("true");
	}
}
