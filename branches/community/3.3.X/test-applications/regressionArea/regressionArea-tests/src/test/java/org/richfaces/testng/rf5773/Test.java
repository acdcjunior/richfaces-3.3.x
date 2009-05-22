/**
 * License Agreement.
 *
 * Rich Faces - Natural Ajax for Java Server Faces (JSF)
 *
 * Copyright (C) 2007 Exadel, Inc.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License version 2.1 as published by the Free Software Foundation.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301  USA
 */


package org.richfaces.testng.rf5773;

import junit.framework.Assert;

import org.richfaces.SeleniumTestBase;

/**
 * @author Nick Belaevski
 * @since 3.3.2
 */
public class Test extends SeleniumTestBase {

	private void checkStep(String[] text, int[] counter) {
		for (int i = 0; i < text.length; i++) {
			String s = getTextById("outputRepeat:" + i + ":valuePanel");
			
			Assert.assertEquals(
				String.format("Value check failed for step: [%d]; expected '%s' was '%s'", i, text[i], s),
				text[i], s);
		}

		for (int i = 0; i < counter.length; i++) {
			String s = getTextById("outputRepeat:" + i + ":counterPanel");
			Assert.assertEquals(
				String.format("Counter check failed for step: [%d]; expected '%s' was '%s'", i, counter[i], s),
				String.valueOf(counter[i]), s);
		}
	}
	
	@org.testng.annotations.Test
	public void testExecute() throws Exception {
		renderPage();

		String[] text = {"Item 0", "Item 1", "Item 2"};
		int[] counter = new int[text.length];

		//check initial setup
		checkStep(text, counter);
		AssertTextEquals("messages", "");
		//initial setup ok

		for (int i = 0; i < text.length; i++) {
			text[i] = "Val:" + String.valueOf(i) ;
			counter[i]++;
			type("formsRepeat:" + i + ":form:input", text[i]);
			clickAjaxCommandAndWait("formsRepeat:" + i + ":form:link");
			
			checkStep(text, counter);
			AssertTextEquals("messages", text[i]);
		}
		
	}
	
	
	@Override
	public String getTestUrl() {
		return "pages/rf5773.xhtml";
	}
}
