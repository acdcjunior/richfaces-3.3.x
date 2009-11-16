package org.richfaces.validator;

import java.util.Locale;

import javax.faces.component.UIInput;
import javax.faces.component.html.HtmlInputText;

import org.ajax4jsf.tests.AbstractAjax4JsfTestCase;

public class FacesValidatorTest extends AbstractAjax4JsfTestCase {

	public FacesValidatorTest(String name) {
		super(name);
	}

	public void setUp() throws Exception {
		super.setUp();
	}

	public void tearDown() throws Exception {
		super.tearDown();
	}

	public void testValidate() {

	}

	public void testFormatMessage() {
		String message = FacesBeanValidator.formatMessage("foo {0}", Locale.ENGLISH, "bar");
		assertEquals("foo bar", message);
	}

	public void testGetLabel() {
		UIInput	 input = new UIInput();
		input.setId("foo");
		facesContext.getViewRoot().getChildren().add(input);
		assertEquals("foo", FacesBeanValidator.getLabel(facesContext, input));
	}
	
	public void testGetLabel1() {
		HtmlInputText input = new HtmlInputText();
		input.setId("foo");
		input.setLabel("bar");
		facesContext.getViewRoot().getChildren().add(input);
		assertEquals("bar", FacesBeanValidator.getLabel(facesContext, input));
	}

}
