package org.richfaces.validator;

import java.beans.FeatureDescriptor;
import java.util.Collection;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.Stack;

import javax.el.ELContext;
import javax.el.ELException;
import javax.el.ELResolver;
import javax.el.ValueExpression;
import javax.faces.FacesException;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.ajax4jsf.el.ELContextWrapper;

public abstract class ObjectValidator {

	private static final String RESOURCE_BUNDLE_IS_NOT_REGISTERED_FOR_CURRENT_LOCALE = "Resource bundle is not registered for current locale";

	private static final String FACES_CONTEXT_IS_NULL = "Faces context is null";
	protected static final String INPUT_PARAMETERS_IS_NOT_CORRECT = "Input parameters is not correct.";
	private static final String LOCALE_IS_NOT_SET = "Locale is not set";
	private static final String VIEW_ROOT_IS_NOT_INITIALIZED = "ViewRoot is not initialized";
	public static final String VALIDATOR_PARAM = HibernateValidator.class
			.getName();

	private static final Object MUTEX = new Object();

	/**
	 * Create BeanValidator instance. For a Junit tests only.
	 * 
	 * @return
	 */
	protected static ObjectValidator createInstance() {
		// TODO - get instance class name from a "META-INF/service"
		// If the Seam framework is active, org.jboss.seam.core.Validators
		// component should be used.
		ObjectValidator validator;
		try {
			validator = new BeanValidator();

		} catch (NoClassDefFoundError e) {
			try {
				validator = new HibernateValidator();

			} catch (NoClassDefFoundError e2) {
				validator = new NullValidator();
			}
		}
		return validator;
	}

	/**
	 * Return BeanValidator object from a ServletContext attribute. Create new
	 * instance if none is defined.
	 * 
	 * @param context
	 * @return
	 */
	public static ObjectValidator getInstance(FacesContext context) {
		ExternalContext externalContext = context.getExternalContext();
		externalContext.getContext();
		ObjectValidator instance;
		// TODO - use properly synchronization mutex ?
		synchronized (MUTEX) {
			Map<String, Object> applicationMap = externalContext
					.getApplicationMap();
			instance = (ObjectValidator) applicationMap.get(VALIDATOR_PARAM);
			if (null == instance) {
				// Vaildator not initialized - create and store new instance.
				instance = createInstance();
				applicationMap.put(VALIDATOR_PARAM, instance);
			}
		}
		return instance;
	}

	public abstract String[] validateGraph(FacesContext context, Object value,
			Set<String> profiles);

	/**
	 * Perform Validation for a new value.
	 * 
	 * @param context
	 *            current faces context.
	 * @param target
	 *            {@link ValueExpression} for a value assignment.
	 * @param value
	 *            new value for validation
	 * @param profiles TODO
	 * @return null if no validation errors. Array of the validation messages
	 *         otherwise.
	 * @throws FacesException
	 *             if locale or context not properly initialized
	 */
	public String[] validate(FacesContext context, ValueExpression target,
			Object value, Set<String> profiles) {
		if (null == context) {
			throw new FacesException(INPUT_PARAMETERS_IS_NOT_CORRECT);
		}
		String[] validationMessages = null;
		if (null != target) {
			ELContext elContext = context.getELContext();
			ValidationResolver validationResolver = createValidationResolver(
					elContext.getELResolver(), calculateLocale(context),profiles);
			ELContextWrapper wrappedElContext = new ELContextWrapper(elContext,
					validationResolver);
			// TODO - handle ELExceptions ?
			try {
				target.setValue(wrappedElContext, value);
			} catch (ELException e) {
				throw new FacesException(e);
			}
			if (!validationResolver.isValid()) {
				validationMessages = validationResolver.getValidationMessages();
			}

		}
		return validationMessages;
	}

	protected Locale calculateLocale(FacesContext context) {
		if (null == context.getViewRoot()) {
			throw new FacesException(VIEW_ROOT_IS_NOT_INITIALIZED);
		} else if (null == context.getViewRoot().getLocale()) {
			throw new FacesException(LOCALE_IS_NOT_SET);
		}
		Locale locale = context.getViewRoot().getLocale();
		return locale;
	}

	/**
	 * Validate bean property for a new value.
	 * 
	 * @param base
	 *            - bean
	 * @param property
	 *            - bean property name.
	 * @param value
	 *            new value.
	 * @param profiles TODO
	 * @return null for a valid value, array of the validation messages
	 *         othervise.
	 */
	protected abstract String[] validate(Object base, String property,
			Object value, Locale locale, Set<String> profiles);

	protected ResourceBundle getCurrentResourceBundle(Locale locale) {
		if (null == FacesContext.getCurrentInstance()
				|| null == FacesContext.getCurrentInstance().getApplication()) {
			throw new FacesException(FACES_CONTEXT_IS_NULL);
		}
		String appBundle = FacesContext.getCurrentInstance().getApplication()
				.getMessageBundle();
		if (null == appBundle || null == locale) {
			return null;
		}

		ResourceBundle bundle;

		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		if (classLoader != null) {
			bundle = ResourceBundle.getBundle(appBundle, locale, classLoader);
		} else {
			bundle = ResourceBundle.getBundle(appBundle, locale);
		}

		return bundle;
	}

	protected ValidationResolver createValidationResolver(ELResolver parent,
			Locale locale, Set<String> profiles) {
		return new ValidationResolver(parent, locale, profiles);
	}

	/**
	 * @author asmirnov
	 * 
	 */
	protected static class BasePropertyPair {
		private final Object base;
		private final Object property;

		/**
		 * @param base
		 * @param property
		 */
		public BasePropertyPair(Object base, Object property) {
			this.base = base;
			this.property = property;
		}

		/**
		 * @return the base
		 */
		public Object getBase() {
			return base;
		}

		/**
		 * @return the property
		 */
		public Object getProperty() {
			return property;
		}

	}

	/**
	 * Class for identify validator instance by locale
	 * 
	 * @author amarkhel
	 * 
	 */
	protected static class ValidatorKey {
		private final Class<? extends Object> validatableClass;
		private final Locale locale;

		/**
		 * Constructor for ValidatorKey object
		 * 
		 * @param validatableClass
		 *            - class to validate
		 * @param locale
		 *            - User locale to determine Resource bundle, used during
		 *            validation process
		 */
		public ValidatorKey(Class<? extends Object> validatableClass,
				Locale locale) {
			this.validatableClass = validatableClass;
			this.locale = locale;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((locale == null) ? 0 : locale.hashCode());
			result = prime
					* result
					+ ((validatableClass == null) ? 0 : validatableClass
							.hashCode());
			return result;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (!(obj instanceof ValidatorKey))
				return false;
			ValidatorKey other = (ValidatorKey) obj;
			if (locale == null) {
				if (other.locale != null)
					return false;
			} else if (!locale.equals(other.locale))
				return false;
			if (validatableClass == null) {
				if (other.validatableClass != null)
					return false;
			} else if (!validatableClass.equals(other.validatableClass))
				return false;
			return true;
		}

	}

	/**
	 * Wrapper class for a {@link ELResolver}. For a setValue method, perform
	 * validation instead of real assignment.
	 * 
	 * @author asmirnov
	 * 
	 */
	final class ValidationResolver extends ELResolver {

		/**
		 * Original resolver.
		 */
		private final ELResolver parent;

		private boolean valid = true;

		private String[] validationMessages = null;

		private Locale locale = null;

		private Stack<BasePropertyPair> valuesStack;

		private Set<String> profiles;

		/**
		 * @param parent
		 */
		public ValidationResolver(ELResolver parent, Locale locale,Set<String> profiles) {
			this.parent = parent;
			this.locale = locale;
			this.valuesStack = new Stack<BasePropertyPair>();
			this.profiles = profiles;
		}

		public boolean isValid() {
			return valid;
		}

		/**
		 * @param context
		 * @param base
		 * @return
		 * @see javax.el.ELResolver#getCommonPropertyType(javax.el.ELContext,
		 *      java.lang.Object)
		 */
		public Class<?> getCommonPropertyType(ELContext context, Object base) {
			return parent.getCommonPropertyType(context, base);
		}

		/**
		 * @param context
		 * @param base
		 * @return
		 * @see javax.el.ELResolver#getFeatureDescriptors(javax.el.ELContext,
		 *      java.lang.Object)
		 */
		public Iterator<FeatureDescriptor> getFeatureDescriptors(
				ELContext context, Object base) {
			return parent.getFeatureDescriptors(context, base);
		}

		/**
		 * @param context
		 * @param base
		 * @param property
		 * @return
		 * @see javax.el.ELResolver#getType(javax.el.ELContext,
		 *      java.lang.Object, java.lang.Object)
		 */
		public Class<?> getType(ELContext context, Object base, Object property) {
			return parent.getType(context, base, property);
		}

		/**
		 * @param context
		 * @param base
		 * @param property
		 * @return
		 * @see javax.el.ELResolver#getValue(javax.el.ELContext,
		 *      java.lang.Object, java.lang.Object)
		 */
		public Object getValue(ELContext context, Object base, Object property) {
			Object value = parent.getValue(context, base, property);
			valuesStack.push(new BasePropertyPair(base, property));
			return value;
		}

		/**
		 * @param context
		 * @param base
		 * @param property
		 * @return
		 * @see javax.el.ELResolver#isReadOnly(javax.el.ELContext,
		 *      java.lang.Object, java.lang.Object)
		 */
		public boolean isReadOnly(ELContext context, Object base,
				Object property) {
			return parent.isReadOnly(context, base, property);
		}

		/**
		 * @param context
		 * @param base
		 * @param property
		 * @param value
		 * @see javax.el.ELResolver#setValue(javax.el.ELContext,
		 *      java.lang.Object, java.lang.Object, java.lang.Object)
		 */
		public void setValue(ELContext context, Object base, Object property,
				Object value) {
			if (null != base && null != property) {
				context.setPropertyResolved(true);
				// For Arrays, Collection or Map use parent base and property.
				BasePropertyPair basePropertyPair = lookupBeanProperty(new BasePropertyPair(
						base, property));
				base = basePropertyPair.getBase();
				property = basePropertyPair.getProperty();
				if (null != base && null != property) {
					// https://jira.jboss.org/jira/browse/RF-4034
					// apache el looses locale information during value
					// resolution,
					// so we use our own
					validationMessages = validate(base, property.toString(),
							value, locale, profiles);
					valid = null == validationMessages
							|| 0 == validationMessages.length;

				}
			}
		}

		private BasePropertyPair lookupBeanProperty(BasePropertyPair pair) {
			Object base = pair.getBase();
			if (null != base
					&& (base instanceof Collection || base instanceof Map || base
							.getClass().isArray())) {
				try {
					pair = lookupBeanProperty(valuesStack.pop());
				} catch (EmptyStackException e) {
					// Do nothing, this is a first item.
				}
			}
			return pair;
		}

		/**
		 * @return the validationMessages
		 */
		public String[] getValidationMessages() {
			return validationMessages;
		}

	}

}