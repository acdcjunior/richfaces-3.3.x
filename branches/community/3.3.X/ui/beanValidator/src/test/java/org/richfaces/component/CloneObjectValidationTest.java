/**
 * 
 */
package org.richfaces.component;

import javax.el.ValueExpression;

import org.ajax4jsf.tests.AbstractAjax4JsfTestCase;
import org.richfaces.component.UIGraphValidator.GraphValidatorState;
import org.richfaces.component.html.HtmlGraphValidator;

/**
 * @author asmirnov
 *
 */
public class CloneObjectValidationTest extends AbstractAjax4JsfTestCase {

	UIGraphValidator validator;
	/**
	 * @param name
	 */
	public CloneObjectValidationTest(String name) {
		super(name);
	}

	/* (non-Javadoc)
	 * @see org.ajax4jsf.tests.AbstractAjax4JsfTestCase#setUp()
	 */
	public void setUp() throws Exception {
		super.setUp();
		validator = new HtmlGraphValidator();
		validator.setId("validator");
		facesContext.getViewRoot().getChildren().add(validator);
	}

	/* (non-Javadoc)
	 * @see org.ajax4jsf.tests.AbstractAjax4JsfTestCase#tearDown()
	 */
	public void tearDown() throws Exception {
		validator = null;
		super.tearDown();
	}

	/**
	 * Test method for {@link org.richfaces.component.UIGraphValidator#processDecodes(javax.faces.context.FacesContext)}.
	 */
	public void testProcessDecodesFacesContext() {
		ValueExpression valueExpression = application.getExpressionFactory().createValueExpression(facesContext.getELContext(),"#{cloneableBean}", CloneableBean.class);
		validator.setValueExpression("value", valueExpression);
		CloneableBean bean = new CloneableBean();
		facesContext.getExternalContext().getSessionMap().put("cloneableBean", bean);
		validator.processDecodes(facesContext);
		GraphValidatorState validatorState = validator.getValidatorState(facesContext);
		assertNotNull(validatorState);
		assertNotSame(bean, validatorState.getCloned());
		assertEquals(bean, validatorState.getCloned());
	}

	/**
	 * Test method for {@link org.richfaces.component.UIGraphValidator#processValidators(javax.faces.context.FacesContext)}.
	 */
	public void testProcessValidatorsFacesContext() {
		ValueExpression valueExpression = application.getExpressionFactory().createValueExpression(facesContext.getELContext(),"#{cloneableBean}", CloneableBean.class);
		validator.setValueExpression("value", valueExpression);
		CloneableBean bean = new CloneableBean();
		facesContext.getExternalContext().getSessionMap().put("cloneableBean", bean);
		validator.processDecodes(facesContext);
		GraphValidatorState validatorState = validator.getValidatorState(facesContext);
		assertNotNull(validatorState);
		assertNotSame(bean, validatorState.getCloned());
		validator.processValidators(facesContext);
		assertTrue(facesContext.getRenderResponse());
		assertTrue(facesContext.getMessages().hasNext());
	}

}
