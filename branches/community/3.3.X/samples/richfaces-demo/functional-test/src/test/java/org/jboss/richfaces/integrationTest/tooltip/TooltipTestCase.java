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
package org.jboss.richfaces.integrationTest.tooltip;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.jboss.test.selenium.waiting.*;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public class TooltipTestCase extends AbstractSeleniumRichfacesTestCase {

	private final String LOC_FIELDSET_HEADER_1 = getLoc("FIELDSET_HEADER_1");
	private final String LOC_PANEL_SAMPLE_1 = getLoc("PANEL_SAMPLE_1");
	private final String LOC_PANEL_SAMPLE_2 = getLoc("PANEL_SAMPLE_2");
	private final String LOC_PANEL_SAMPLE_3 = getLoc("PANEL_SAMPLE_3");
	private final String LOC_PANEL_SAMPLE_4 = getLoc("PANEL_SAMPLE_4");
	private final String LOC_SPAN_TOOLTIPS_REQUESTED = getLoc("SPAN_TOOLTIPS_REQUESTED");

	private final String MSG_EVENT_COORDS_AT_PANEL = getMsg("EVENT_COORDS_AT_PANEL");
	private final String MSG_OUTPUT_TOOLTIP_1 = getMsg("OUTPUT_TOOLTIP_1");
	private final String MSG_OUTPUT_TOOLTIP_2 = getMsg("OUTPUT_TOOLTIP_2");
	private final String MSG_OUTPUT_TOOLTIP_3_4_WAITING = getMsg("OUTPUT_TOOLTIP_3_4_WAITING");
	private final String MSG_OUTPUT_TOOLTIP_3_4_TEXT = getMsg("OUTPUT_TOOLTIP_3_4_TEXT");
	private final Pattern MSG_REGEXP_TOOLTIPS_REQUESTED = Pattern.compile(getMsg("REGEXP_TOOLTIPS_REQUESTED"));

	/**
	 * Hover mouse at first panel and so invokes tooltip displaying. Checks that
	 * the tooltip text will display and next hover mouse out of panel to close
	 * tooltip. Do this all above three times.
	 */
	@Test
	public void testDefaultToolTip() {

		for (int i = 0; i < 3; i++) {
			assertFalse(selenium.isTextPresent(MSG_OUTPUT_TOOLTIP_1));

			if (i == 0) /*
						 * satisfies that mouseOverAt will work as expected -
						 * without this mouseOverAt do nothing
						 */
				selenium.mouseMoveAt(LOC_PANEL_SAMPLE_1, MSG_EVENT_COORDS_AT_PANEL);
			mouseOverAt(LOC_PANEL_SAMPLE_1, MSG_EVENT_COORDS_AT_PANEL);

			waitForText(MSG_OUTPUT_TOOLTIP_1);

			selenium.mouseOut(LOC_PANEL_SAMPLE_1);
			waitForTextDisappears(MSG_OUTPUT_TOOLTIP_1);
		}
	}

	/**
	 * Hover mouse at second panel (top right) and so invokes deplayed tooltip
	 * displaying. Checks that expected text will display and then hover mouse
	 * out of panel to close tooltip. Do this all above three times.
	 */
	@Test
	public void testFollowMouseDelayed() {

		for (int i = 0; i < 3; i++) {
			assertFalse(selenium.isTextPresent(MSG_OUTPUT_TOOLTIP_2));

			if (i == 0)
				selenium.mouseMoveAt(LOC_PANEL_SAMPLE_2, MSG_EVENT_COORDS_AT_PANEL);
			mouseOverAt(LOC_PANEL_SAMPLE_2, MSG_EVENT_COORDS_AT_PANEL);

			waitForText(MSG_OUTPUT_TOOLTIP_2);

			selenium.mouseOut(LOC_PANEL_SAMPLE_2);
			waitForTextDisappears(MSG_OUTPUT_TOOLTIP_2);
		}
	}

	/**
	 * Hover mouse at third (bottom left) panel and so invokes server rendered
	 * tooltip diplaying. Checks that expected text will display and then hover
	 * mouse out to close tooltip. Do all above three times and checks that
	 * tooltips requested counter is counting right.
	 */
	@Test
	public void testSeparateServerRequests() {
		Integer tooltipsRequested = null;

		for (int i = 0; i < 3; i++) {
			assertFalse(selenium.isTextPresent(MSG_OUTPUT_TOOLTIP_3_4_WAITING));
			assertFalse(selenium.isTextPresent(MSG_OUTPUT_TOOLTIP_3_4_TEXT));

			if (i == 0)
				selenium.mouseMoveAt(LOC_PANEL_SAMPLE_3, MSG_EVENT_COORDS_AT_PANEL);
			mouseOverAt(LOC_PANEL_SAMPLE_3, MSG_EVENT_COORDS_AT_PANEL);

			tooltipsRequested = waitForTooltipChanges(tooltipsRequested, i == 0);

			selenium.mouseOut(LOC_PANEL_SAMPLE_3);
			waitForTextDisappears(MSG_OUTPUT_TOOLTIP_3_4_TEXT);
		}
	}

	/**
	 * Click at third (bottom left) panel and so invokes server rendered
	 * tooltip diplaying. Checks that expected text will display and then hover
	 * mouse out to close tooltip. Do all above three times and checks that
	 * tooltips requested counter is counting right.
	 */
	@Test
	public void testMouseClickActivation() {
		Integer tooltipsRequested = null;

		for (int i = 0; i < 3; i++) {
			assertFalse(selenium.isTextPresent(MSG_OUTPUT_TOOLTIP_3_4_WAITING));
			assertFalse(selenium.isTextPresent(MSG_OUTPUT_TOOLTIP_3_4_TEXT));

			selenium.clickAt(LOC_PANEL_SAMPLE_4, MSG_EVENT_COORDS_AT_PANEL);

			tooltipsRequested = waitForTooltipChanges(tooltipsRequested, i == 0);

			selenium.mouseOut(LOC_PANEL_SAMPLE_4);
			waitForTextDisappears(MSG_OUTPUT_TOOLTIP_3_4_TEXT);
		}
	}

	private Integer waitForTooltipChanges(Integer tooltipsRequestedOld, boolean firstLoop) {
		Integer tooltipsRequested = null;

		if (firstLoop) {
			waitForText(MSG_OUTPUT_TOOLTIP_3_4_WAITING);
			waitForText(MSG_OUTPUT_TOOLTIP_3_4_TEXT);
			tooltipsRequested = retrieveRequestedTooltips.retrieve();
		} else {
			waitForText(MSG_OUTPUT_TOOLTIP_3_4_TEXT);
			tooltipsRequested = Wait.waitForChangeAndReturn(tooltipsRequestedOld, retrieveRequestedTooltips);

			if (tooltipsRequestedOld != null) {
				assertEquals(tooltipsRequested, Integer.valueOf(tooltipsRequestedOld + 1));
			}
		}

		return tooltipsRequested;
	}

	private Retrieve<Integer> retrieveRequestedTooltips = new Retrieve<Integer>() {
		public Integer retrieve() {
			String text = Wait.interval(20).timeout(2000).waitForChangeAndReturn(null, new Retrieve<String>() {
				public String retrieve() {
					return getTextOrNull(LOC_SPAN_TOOLTIPS_REQUESTED);
				}
			});
			Matcher matcher = MSG_REGEXP_TOOLTIPS_REQUESTED.matcher(text);
			if (!matcher.matches()) {
				fail();
			}
			return Integer.valueOf(matcher.group(1));
		}
	};

	private void waitForTextDisappears(final String text) {
		waitModelUpdate.until(new Condition() {
			public boolean isTrue() {
				return !selenium.isElementPresent(text);
			}
		});
	}

	@SuppressWarnings("unused")
	@BeforeMethod
	private void loadPage() {
		openComponent("ToolTip");
		openTab("Usage");
		scrollIntoView(LOC_FIELDSET_HEADER_1, true);
		selenium.allowNativeXpath("true");
	}
}
