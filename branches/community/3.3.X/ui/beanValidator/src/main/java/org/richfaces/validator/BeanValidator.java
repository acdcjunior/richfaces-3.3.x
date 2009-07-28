/**
 * 
 */
package org.richfaces.validator;

import java.util.Locale;
import java.util.Set;

import javax.faces.FacesException;
import javax.faces.context.FacesContext;
import javax.validation.ConstraintViolation;
import javax.validation.MessageInterpolator;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.validation.ValidatorContext;
import javax.validation.ValidatorFactory;
import javax.validation.groups.Default;

/**
 * @author asmirnov
 * 
 */
public class BeanValidator extends ObjectValidator {

	private static final Class[] DEFAULT_PROFILE = new Class[] {};
	private final ValidatorFactory validatorFactory;

	BeanValidator() {
		// Enforce class to load
		ValidatorFactory.class.getName();
		// Check Factory, to avoid instantiation errors
		// https://jira.jboss.org/jira/browse/RF-7226
		validatorFactory = Validation
					.buildDefaultValidatorFactory();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.richfaces.validator.ObjectValidator#validate(java.lang.Object,
	 * java.lang.String, java.lang.Object, java.util.Locale)
	 */
	@Override
	protected String[] validate(FacesContext facesContext, Object base, String property,
			Object value, Set<String> profiles) {
		Class beanType = base.getClass();
		Set<ConstraintViolation<Object>> constrains = getValidator(facesContext)
				.validateValue(beanType, property, value, getGroups(profiles));
		return extractMessages(constrains);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.richfaces.validator.ObjectValidator#validateGraph(javax.faces.context
	 * .FacesContext, java.lang.Object, java.util.Set)
	 */
	@Override
	public String[] validateGraph(FacesContext context, Object value,
			Set<String> profiles) {
		Class<?>[] groups = getGroups(profiles);
		Set<ConstraintViolation<Object>> violations = getValidator(
				context).validate(value, groups);
		String[] messages = extractMessages(violations);
		return messages;
	}

	private Class<?>[] getGroups(Set<String> profiles) {
		Class<?> groups[] = null;
		if (null != profiles) {
			groups = new Class<?>[profiles.size()];
			int i = 0;
			for (String group : profiles) {
				try {
					groups[i] = Class.forName(group, false, Thread
							.currentThread().getContextClassLoader());
				} catch (ClassNotFoundException e) {
					try {
						groups[i] = Class.forName(group);
					} catch (ClassNotFoundException e1) {
						throw new FacesException(
								"Bean validation group not found " + group, e1);
					}
				}
				i++;
			}

		} else {
			groups = DEFAULT_PROFILE;
		}
		return groups;
	}

	private String[] extractMessages(Set<ConstraintViolation<Object>> violations) {
		String[] messages = null;
		if (null != violations && violations.size() > 0) {
			messages = new String[violations.size()];
			int i = 0;
			for (ConstraintViolation<? extends Object> constraintViolation : violations) {
				messages[i++] = constraintViolation.getMessage();
			}

		}
		return messages;
	}

	protected Validator getValidator(FacesContext facesContext) {
		ValidatorContext validatorContext = validatorFactory.usingContext();
		MessageInterpolator jsfMessageInterpolator = new JsfMessageInterpolator(
				calculateLocale(facesContext), validatorFactory.getMessageInterpolator());
		validatorContext.messageInterpolator(jsfMessageInterpolator);
		Validator beanValidator = validatorContext.getValidator();
		return beanValidator;
	}

	private static class JsfMessageInterpolator implements MessageInterpolator {

		private Locale locale;
		private MessageInterpolator delegate;

		public JsfMessageInterpolator(Locale locale,
				MessageInterpolator delegate) {
			this.locale = locale;
			this.delegate = delegate;
		}

		public String interpolate(String messageTemplate, Context context) {

			if (null != locale) {
				return delegate.interpolate(messageTemplate, context,
						this.locale);
			} else {
				return delegate.interpolate(messageTemplate, context);
			}
		}

		public String interpolate(String messageTemplate, Context context,
				Locale locale) {
			if (null != locale) {
				return delegate.interpolate(messageTemplate, context,
						this.locale);
			} else {
				return delegate.interpolate(messageTemplate, context, locale);
			}
		}

	}

}
