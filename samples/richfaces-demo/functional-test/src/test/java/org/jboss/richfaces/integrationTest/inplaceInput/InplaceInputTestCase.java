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

package org.jboss.richfaces.integrationTest.inplaceInput;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.jboss.test.selenium.dom.Event;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test case that tests the inplace input component.
 *
 * @author <a href="mailto:ppitonak@redhat.com">Pavol Pitonak</a>
 * @version $Revision$
 */
// TODO create tests for the fourth example (table)
public class InplaceInputTestCase extends AbstractSeleniumRichfacesTestCase {

	// messages
	private final String MSG_INITIAL_VALUE_NAME = getMsg("INITIAL_VALUE_NAME");
	private final String MSG_INITIAL_VALUE_EMAIL = getMsg("INITIAL_VALUE_EMAIL");
	private final String MSG_NAME_JOHN_SMITH = getMsg("NAME_JOHN_SMITH");
	private final String MSG_EMAIL_JOHN_SMITH = getMsg("EMAIL_JOHN_SMITH");
	private final String MSG_RICH_INPLACE_VIEW = getMsg("RICH_INPLACE_VIEW");
	private final String MSG_RICH_INPLACE_CHANGED = getMsg("RICH_INPLACE_CHANGED");
	private final String MSG_NOT_RICH_INPLACE_CHANGED = getMsg("NOT_RICH_INPLACE_CHANGED");
	private final String MSG_RICH_INPLACE_EDIT = getMsg("RICH_INPLACE_EDIT");
	private final String MSG_NOT_RICH_INPLACE_EDIT = getMsg("NOT_RICH_INPLACE_EDIT");

	// locators
	private final String LOC_FIRST_NAME = getLoc("FIRST_NAME");
	private final String LOC_FIRST_NAME_INPUT = getLoc("FIRST_NAME_INPUT");
	private final String LOC_FIRST_EMAIL = getLoc("FIRST_EMAIL");
	private final String LOC_FIRST_EMAIL_INPUT = getLoc("FIRST_EMAIL_INPUT");
	private final String LOC_SECOND_NAME = getLoc("SECOND_NAME");
	private final String LOC_SECOND_NAME_INPUT = getLoc("SECOND_NAME_INPUT");
	private final String LOC_SECOND_NAME_OK = getLoc("SECOND_NAME_OK");
	private final String LOC_SECOND_NAME_CANCEL = getLoc("SECOND_NAME_CANCEL");
	private final String LOC_SECOND_EMAIL = getLoc("SECOND_EMAIL");
	private final String LOC_SECOND_EMAIL_INPUT = getLoc("SECOND_EMAIL_INPUT");
	private final String LOC_SECOND_EMAIL_OK = getLoc("SECOND_EMAIL_OK");
	private final String LOC_SECOND_EMAIL_CANCEL = getLoc("SECOND_EMAIL_CANCEL");
	private final String LOC_THIRD = getLoc("THIRD");
	private final String LOC_THIRD_INPUT = getLoc("THIRD_INPUT");
	private final String LOC_THIRD_SAVE = getLoc("THIRD_SAVE");
	private final String LOC_THIRD_CANCEL = getLoc("THIRD_CANCEL");

	/**
	 * Tests the input for name in the first example. First it checks the attribute  
	 * "class" of the input. Then it clicks on text, edits the text and fires the
	 * event "blur" so that the text is changed permanently. After that, it 
	 * checks the attribute "class" and verifies that the text was changed.
	 */
	@Test
	public void testFirstName() {
		scrollIntoView(LOC_FIRST_NAME, true);

		assertTrue(belongsClass("rich-inplace-view", LOC_FIRST_NAME), MSG_RICH_INPLACE_VIEW);
		assertFalse(belongsClass("rich-inplace-changed", LOC_FIRST_NAME), MSG_NOT_RICH_INPLACE_CHANGED);
		assertFalse(belongsClass("rich-inplace-edit", LOC_FIRST_NAME), MSG_NOT_RICH_INPLACE_EDIT);
		
		String text = selenium.getText(LOC_FIRST_NAME);
		assertTrue(text.endsWith("click to enter your name"), MSG_INITIAL_VALUE_NAME);

		selenium.click(LOC_FIRST_NAME);

		assertTrue(belongsClass("rich-inplace-edit", LOC_FIRST_NAME), MSG_RICH_INPLACE_EDIT);

		selenium.type(LOC_FIRST_NAME_INPUT, "John Smith");
		selenium.fireEvent(LOC_FIRST_NAME_INPUT, "blur");

		assertTrue(belongsClass("rich-inplace-changed", LOC_FIRST_NAME), MSG_RICH_INPLACE_CHANGED);
		assertFalse(belongsClass("rich-inplace-edit", LOC_FIRST_NAME), MSG_NOT_RICH_INPLACE_EDIT);

		text = selenium.getText(LOC_FIRST_NAME);
		assertTrue(text.endsWith("John Smith"), MSG_NAME_JOHN_SMITH);
	}

	/**
	 * Tests the input for email in the first example. First it checks the attribute  
	 * "class" of the input. Then it clicks on text, edits the text and fires the
	 * event "blur" so that the text is changed permanently. After that, it 
	 * checks the attribute "class" and verifies that the text was changed.
	 */
	@Test
	public void testFirstEmail() {
		scrollIntoView(LOC_FIRST_EMAIL, true);

		assertTrue(belongsClass("rich-inplace-view", LOC_FIRST_EMAIL), MSG_RICH_INPLACE_VIEW);
		assertFalse(belongsClass("rich-inplace-changed", LOC_FIRST_EMAIL), MSG_NOT_RICH_INPLACE_CHANGED);
		assertFalse(belongsClass("rich-inplace-edit", LOC_FIRST_EMAIL), MSG_NOT_RICH_INPLACE_EDIT);

		String text = selenium.getText(LOC_FIRST_EMAIL);
		assertTrue(text.endsWith("click to enter your email"), MSG_INITIAL_VALUE_EMAIL);

		selenium.click(LOC_FIRST_EMAIL);

		assertTrue(belongsClass("rich-inplace-edit", LOC_FIRST_EMAIL), MSG_RICH_INPLACE_EDIT);

		selenium.type(LOC_FIRST_EMAIL_INPUT, "john@smith.name");
		selenium.fireEvent(LOC_FIRST_EMAIL_INPUT, "blur");

		assertTrue(belongsClass("rich-inplace-changed", LOC_FIRST_EMAIL), MSG_RICH_INPLACE_CHANGED);
		assertFalse(belongsClass("rich-inplace-edit", LOC_FIRST_EMAIL), MSG_NOT_RICH_INPLACE_EDIT);

		text = selenium.getText(LOC_FIRST_EMAIL);
		assertTrue(text.endsWith("john@smith.name"), MSG_EMAIL_JOHN_SMITH);
	}

	/**
	 * Tests the input for name in the second example. First it checks the attribute  
	 * "class" of the input. Then it clicks on text, edits the text and 
	 * clicks on the button so that the text is changed permanently. After that, it 
	 * checks the attribute "class" and verifies that the text was changed.
	 */
	@Test
	public void testSecondName() {
		scrollIntoView(LOC_SECOND_NAME, true);

		assertTrue(belongsClass("rich-inplace-view", LOC_SECOND_NAME), MSG_RICH_INPLACE_VIEW);
		assertFalse(belongsClass("rich-inplace-changed", LOC_SECOND_NAME), MSG_NOT_RICH_INPLACE_CHANGED);
		assertFalse(belongsClass("rich-inplace-edit", LOC_SECOND_NAME), MSG_NOT_RICH_INPLACE_EDIT);

		String text = selenium.getText(LOC_SECOND_NAME);
		assertTrue(text.endsWith("click to enter your name"), MSG_INITIAL_VALUE_NAME);

		selenium.click(LOC_SECOND_NAME);

		assertTrue(belongsClass("rich-inplace-edit", LOC_SECOND_NAME), MSG_RICH_INPLACE_EDIT);

		selenium.type(LOC_SECOND_NAME_INPUT, "John Smith");
		// TODO check that the button is visible
		selenium.fireEvent(LOC_SECOND_NAME_OK, Event.MOUSEDOWN);

		assertTrue(belongsClass("rich-inplace-changed", LOC_SECOND_NAME), MSG_RICH_INPLACE_CHANGED);
		assertFalse(belongsClass("rich-inplace-edit", LOC_SECOND_NAME), MSG_NOT_RICH_INPLACE_EDIT);

		text = selenium.getText(LOC_SECOND_NAME);
		assertTrue(text.endsWith("John Smith"), MSG_NAME_JOHN_SMITH);
	}

	/**
	 * Tests the input for email in the second example. First it checks the attribute  
	 * "class" of the input. Then it clicks on text, edits the text and 
	 * clicks on the button so that the text is changed permanently. After that, it 
	 * checks the attribute "class" and verifies that the text was changed.
	 */
	@Test
	public void testSecondEmail() {
		scrollIntoView(LOC_SECOND_EMAIL, true);

		assertTrue(belongsClass("rich-inplace-view", LOC_SECOND_EMAIL), MSG_RICH_INPLACE_VIEW);
		assertFalse(belongsClass("rich-inplace-changed", LOC_SECOND_EMAIL), MSG_NOT_RICH_INPLACE_CHANGED);
		assertFalse(belongsClass("rich-inplace-edit", LOC_SECOND_EMAIL), MSG_NOT_RICH_INPLACE_EDIT);

		String text = selenium.getText(LOC_SECOND_EMAIL);
		assertTrue(text.endsWith("click to enter your email"), MSG_INITIAL_VALUE_EMAIL);

		selenium.click(LOC_SECOND_EMAIL);

		assertTrue(belongsClass("rich-inplace-edit", LOC_SECOND_EMAIL), MSG_RICH_INPLACE_EDIT);

		selenium.type(LOC_SECOND_EMAIL_INPUT, "john@smith.name");
		// TODO check that the button is visible
		selenium.fireEvent(LOC_SECOND_EMAIL_OK, Event.MOUSEDOWN);
		
		assertTrue(belongsClass("rich-inplace-changed", LOC_SECOND_EMAIL), MSG_RICH_INPLACE_CHANGED);
		assertFalse(belongsClass("rich-inplace-edit", LOC_SECOND_EMAIL), MSG_NOT_RICH_INPLACE_EDIT);

		text = selenium.getText(LOC_SECOND_EMAIL);
		assertTrue(text.endsWith("john@smith.name"), MSG_EMAIL_JOHN_SMITH);
	}

	/**
	 * Tests the input in the third example. First it checks the attribute  
	 * "class" of the input. Then it clicks on text, edits the text and 
	 * clicks on the "Save" button so that the text is changed permanently. 
	 * After that, it checks the attribute "class" and verifies that the text was changed.
	 */
	@Test
	public void testThird() {
		scrollIntoView(LOC_THIRD, true);

		assertTrue(belongsClass("rich-inplace-view", LOC_THIRD), MSG_RICH_INPLACE_VIEW);
		assertFalse(belongsClass("rich-inplace-changed", LOC_THIRD), MSG_NOT_RICH_INPLACE_CHANGED);
		assertFalse(belongsClass("rich-inplace-edit", LOC_THIRD), MSG_NOT_RICH_INPLACE_EDIT);

		String text = selenium.getText(LOC_THIRD);
		assertTrue(text.endsWith("Click here to edit"), MSG_INITIAL_VALUE_EMAIL);

		selenium.click(LOC_THIRD);

		assertTrue(belongsClass("rich-inplace-edit", LOC_THIRD), MSG_RICH_INPLACE_EDIT);

		selenium.type(LOC_THIRD_INPUT, "John Smith");
		// TODO check that the button is visible
		selenium.fireEvent(LOC_THIRD_SAVE, Event.MOUSEDOWN);

		assertTrue(belongsClass("rich-inplace-changed", LOC_THIRD), MSG_RICH_INPLACE_CHANGED);
		assertFalse(belongsClass("rich-inplace-edit", LOC_THIRD), MSG_NOT_RICH_INPLACE_EDIT);

		text = selenium.getText(LOC_THIRD);
		assertTrue(text.endsWith("John Smith"), MSG_NAME_JOHN_SMITH);
	}

	/**
     * Tests the "View Source" in the first example. It checks that the source code is not visible,
     * clicks on the link, and checks 9 lines of source code.
     */
    @Test
    public void testFirstSource() {
        String[] strings = new String[] { "<ui:composition xmlns=\"http://www.w3.org/1999/xhtml\"",
                "<rich:panel style=\"width:220px;\">",
                "<f:facet name=\"header\">",
                "<h:outputText value=\"Person Info\"></h:outputText>",
                "<h:panelGrid columns=\"2\">",
                "<h:outputText value=\"Name: \"/>",
                "<rich:inplaceInput defaultLabel=\"click to enter your name\"/>",
                "<h:outputText value=\"Email:\"/>",
                "<rich:inplaceInput defaultLabel=\"click to enter your email\"/>",
        };

        abstractTestSource(1, "View Source", strings);
    }

    /**
     * Tests the "View Source" in the second example. It checks that the source code is not visible,
     * clicks on the link, and checks 9 lines of source code.
     */
    @Test
    public void testSecondSource() {
        String[] strings = new String[] { "<ui:composition xmlns=\"http://www.w3.org/1999/xhtml\"",
                "<rich:panel style=\"width:220px;\">",
                "<f:facet name=\"header\">",
                "<h:outputText value=\"Person Info\"/>",
                "<h:panelGrid columns=\"2\">",
                "<h:outputText value=\"Name: \"/>",
                "<rich:inplaceInput defaultLabel=\"click to enter your name\" ",
                "showControls=\"true\"/>",
                "</h:panelGrid>",
        };

        abstractTestSource(2, "View Source", strings);
    }
    
    /**
     * Tests the "View Source" in the third example. It checks that the source code is not visible,
     * clicks on the link, and checks 9 lines of source code.
     */
    @Test
    public void testThirdSource() {
        String[] strings = new String[] { "<ui:composition xmlns=\"http://www.w3.org/1999/xhtml\"",
                "<rich:inplaceInput defaultLabel=\"Click here to edit\" showControls=\"true\"",
                "controlsHorizontalPosition=\"left\" controlsVerticalPosition=\"bottom\"",
                "id=\"inplaceInput\">",
                " <f:facet name=\"controls\">",
                "<button onmousedown=\"#{rich:component('inplaceInput')}.save();\"",
                " type=\"button\">Save</button>",
                "<button onmousedown=\"#{rich:component('inplaceInput')}.cancel();\"",
                "<rich:spacer height=\"25px\" width=\"100%\"/>",
        };

        abstractTestSource(3, "View Source", strings);
    }
    
	/**
     * Loads the page containing needed component.
     */
    protected void loadPage() {
        openComponent("Inplace Input");
    }
}
