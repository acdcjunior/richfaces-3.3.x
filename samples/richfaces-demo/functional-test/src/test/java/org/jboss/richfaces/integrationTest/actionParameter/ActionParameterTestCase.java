/**
 * License Agreement.
 *
 *  JBoss RichFaces
 *
 * Copyright (C) 2009  Red Hat, Inc.
 *
 * This code is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License version 2.1 as published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this test suite; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301  USA
 */
package org.jboss.richfaces.integrationTest.actionParameter;

import org.apache.commons.lang.StringUtils;
import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.jboss.test.selenium.waiting.Condition;
import org.jboss.test.selenium.waiting.Wait;
import static org.testng.Assert.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public class ActionParameterTestCase extends AbstractSeleniumRichfacesTestCase {

	private final String LOC_OUTPUT_SELECTED_NAME_PREFORMATTED = getLoc("OUTPUT_SELECTED_NAME_PREFORMATTED");
	private final String LOC_BUTTON_SELECTED_NAME_PREFORMATTED = getLoc("BUTTON_SELECTED_NAME_PREFORMATTED");
	private final String LOC_LABEL_CHANGE_SKIN = getLoc("LABEL_CHANGE_SKIN");
	private final String LOC_ANCHOR_SELECT_SKIN_RELATIVE = getLoc("ANCHOR_SELECT_SKIN_RELATIVE");
	private final String LOC_BUTTON_SCREEN_SIZE = getLoc("BUTTON_SCREEN_SIZE");
	private final String LOC_OUTPUT_SCREEN_WIDTH = formatLoc("OUTPUT_SCREEN_WIDTH", LOC_BUTTON_SCREEN_SIZE);
	private final String LOC_OUTPUT_SCREEN_HEIGHT = formatLoc("OUTPUT_SCREEN_HEIGHT", LOC_BUTTON_SCREEN_SIZE);

	private final String MSG_LABEL_SELECTED_NAME = getMsg("LABEL_SELECTED_NAME");
	private final String MSG_PATTERN_SELECTED_NAME = getMsg("PATTERN_SELECTED_NAME");
	private final String[] MSG_INPUT_GUYS_LIST = StringUtils.split(getMsg("INPUT_GUYS_LIST"), ',');
	private final String[] MSG_RELATION_SKINS_COLORS = StringUtils.split(getMsg("RELATION_SKINS_COLORS"), '#');

	/**
	 * Gets initial selected name (check that it is only a label) and select
	 * each guy by pressing the button and waiting for output changes
	 */
	@Test
	public void testSelectingNames() {
		final String locSelectedName = format(LOC_OUTPUT_SELECTED_NAME_PREFORMATTED, MSG_LABEL_SELECTED_NAME);

		scrollIntoView(locSelectedName, true);

		// get initial selected name (should be only label)
		String selectedName = selenium.getText(locSelectedName);

		assertEquals(selectedName, MSG_LABEL_SELECTED_NAME, format(
				"Initial selected name '{0}' doesn't match expected '{1}'", selectedName, MSG_LABEL_SELECTED_NAME));

		// for each guy press button and wait for output changes
		for (String msgOutputGuy : MSG_INPUT_GUYS_LIST) {
			final String locButton = format(LOC_BUTTON_SELECTED_NAME_PREFORMATTED, msgOutputGuy);
			final String expected = format(MSG_PATTERN_SELECTED_NAME, MSG_LABEL_SELECTED_NAME, msgOutputGuy);

			scrollIntoView(locButton, true);

			selenium.click(locButton);

			Wait.failWith(format("Selected name never changed to '{0}'", expected)).until(new Condition() {
				public boolean isTrue() {
					return expected.equals(selenium.getText(locSelectedName));
				}
			});
		}
	}

	/**
	 * Switching between skins and waiting for actual skin changes (detected by
	 * label's background color changes).
	 */
	@Test
	public void testSelectingSkin() {
		for (String relation : MSG_RELATION_SKINS_COLORS) {
			final String[] msgSkinColor = StringUtils.split(relation, '|');
			final String msgInputSkin = msgSkinColor[0];
			final String msgOutputColor = msgSkinColor[1];
			final String locAnchorSelectSkin = format(LOC_ANCHOR_SELECT_SKIN_RELATIVE, LOC_LABEL_CHANGE_SKIN, msgInputSkin);
			
			scrollIntoView(locAnchorSelectSkin, false);
			
			selenium.click(locAnchorSelectSkin);

			Wait.failWith(format("Color never changed to '{0}'", msgOutputColor)).until(new Condition() {
				public boolean isTrue() {
					String actualColor = getStyle(LOC_LABEL_CHANGE_SKIN, "background-color");

					return msgOutputColor.equals(actualColor);
				}
			});
		}
	}

	/**
	 * Gets a expected width and height of the screen (by javascript evaluation)
	 * and try to evaluate it by click on the button. Wait for output values
	 * matches expected.
	 */
	@Test
	public void testShowScreenSize() {
		final String expectedWidth = selenium.getEval("screen.width");
		final String expectedHeight = selenium.getEval("screen.height");
		
		scrollIntoView(LOC_BUTTON_SCREEN_SIZE, true);

		selenium.click(LOC_BUTTON_SCREEN_SIZE);
		
		Wait.failWith(format("Screen sizes never match expected value {0}x{1}", expectedWidth, expectedHeight)).until(
				new Condition() {
					public boolean isTrue() {
						return expectedWidth.equals(selenium.getText(LOC_OUTPUT_SCREEN_WIDTH))
								&& expectedHeight.equals(selenium.getText(LOC_OUTPUT_SCREEN_HEIGHT));
					}
				});
	}

	/**
	 * Loads a specific component's page
	 */
	@SuppressWarnings("unused")
	@BeforeMethod
	private void loadPage() {
		openComponent("Action Parameter");
	}
}
