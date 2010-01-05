package org.richfaces.validator;

import java.util.Collection;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

import javax.faces.context.FacesContext;

import org.ajax4jsf.tests.AbstractAjax4JsfTestCase;

public class ObjectValidatorTest extends AbstractAjax4JsfTestCase {

	public ObjectValidatorTest(String name) {
		super(name);
	}

	@Override
	public void setUp() throws Exception {
		super.setUp();
	}
	
	@Override
	public void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testCreateInstance() {
		ObjectValidator objectValidator = ObjectValidator.createInstance();
		assertEquals(HibernateValidator.class, objectValidator.getClass());
	}

	public void testGetInstance() {
		ObjectValidator objectValidator = ObjectValidator.getInstance(facesContext);
		assertEquals(HibernateValidator.class, objectValidator.getClass());
		ObjectValidator objectValidator2 = ObjectValidator.getInstance(facesContext);
		assertSame(objectValidator, objectValidator2);
	}

	public void testCalculateLocale() {
		ObjectValidatorStub validator = new ObjectValidatorStub();
		facesContext.getViewRoot().setLocale(Locale.FRANCE);
		assertEquals(Locale.FRANCE, validator.calculateLocale(facesContext));
		facesContext.getViewRoot().setLocale(Locale.US);
		assertEquals(Locale.US, validator.calculateLocale(facesContext));
	}

	public void testGetResourceBundle() {
		ObjectValidatorStub validator = new ObjectValidatorStub();
		facesContext.getViewRoot().setLocale(Locale.US);
		ResourceBundle bundle = validator.getResourceBundle(facesContext, HibernateValidator.DEFAULT_VALIDATOR_MESSAGES);
		assertNotNull(bundle);
		assertEquals(Locale.US.getLanguage(), bundle.getLocale().getLanguage());
	}

	public void testGetResourceBundleNonExists() {
		ObjectValidatorStub validator = new ObjectValidatorStub();
		facesContext.getViewRoot().setLocale(Locale.US);
		ResourceBundle bundle = validator.getResourceBundle(facesContext, "fooBar");
		assertNull(bundle);
	}

	private static class ObjectValidatorStub extends ObjectValidator {

		@Override
		protected Collection<String> validate(FacesContext facesContext, Object base, String property,
				Object value, Set<String> profiles) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Collection<String> validateGraph(FacesContext context, Object value,
				Set<String> profiles) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
}
