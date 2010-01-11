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
package org.richfaces.validator;

import java.beans.FeatureDescriptor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;

import javax.el.ELResolver;
import javax.faces.FacesException;
import javax.faces.context.FacesContext;

import org.hibernate.validator.ClassValidator;
import org.hibernate.validator.InvalidValue;
import org.hibernate.validator.MessageInterpolator;
import org.hibernate.validator.Validator;

/**
 * Perform validation by Hibernate Validator annotations
 * 
 * @author asmirnov
 * 
 */
public class HibernateValidator extends ObjectValidator {

	static final String DEFAULT_VALIDATOR_MESSAGES = "org.hibernate.validator.resources.DefaultValidatorMessages";
	static final String VALIDATOR_MESSAGES = "ValidatorMessages";

	private Map<ValidatorKey, ClassValidator<? extends Object>> classValidators = new ConcurrentHashMap<ValidatorKey, ClassValidator<? extends Object>>();

	HibernateValidator() {
		super();
		// This is a "singleton"-like class. Only factory methods allowed.
		// Enforce class to load
		ClassValidator.class.getName();
	}

	HibernateValidator(ObjectValidator parent){
		super(parent);
		// This is a "singleton"-like class. Only factory methods allowed.
		// Enforce class to load
		ClassValidator.class.getName();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Collection<String> validateGraph(FacesContext context, Object value,
			Set<String> profiles) {
		if (null == context) {
			throw new FacesException(INPUT_PARAMETERS_IS_NOT_CORRECT);
		}
		Collection<String> validationMessages = null;
		if (null != value) {
			ClassValidator<Object> validator = (ClassValidator<Object>) getValidator(
					context, value.getClass());
			if (validator.hasValidationRules()) {
				InvalidValue[] invalidValues = validator
						.getInvalidValues(value);
				if (null != invalidValues && invalidValues.length > 0) {
					validationMessages = new ArrayList<String>(invalidValues.length);
					for (int i = 0; i < invalidValues.length; i++) {
						InvalidValue invalidValue = invalidValues[i];
						validationMessages.add(invalidValue.getMessage());
					}
				}
			}
			if(null != parent){
				Collection<String> parentMessages = parent.validateGraph(context, value, profiles);
				if(null != validationMessages){
					if (null != parentMessages) {
						validationMessages.addAll(parentMessages);
					}
				} else {
					validationMessages = parentMessages;
				}
			}
		}
		return validationMessages;
	}

	/**
	 * Validate bean property in the base class aganist new value.
	 * @param beanClass
	 * @param property
	 * @param value
	 * 
	 * @return
	 */
	protected InvalidValue[] validateClass(FacesContext facesContext,
			Class<? extends Object> beanClass, String property, Object value) {
		ClassValidator<? extends Object> classValidator = 
		    getValidator(facesContext, beanClass);
		
		InvalidValue[] invalidValues = classValidator
				.getPotentialInvalidValues(property, value);
		return invalidValues;
	}

	/**
	 * Get ( or create ) {@link ClassValidator} for a given bean class.
	 * @param beanClass
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected ClassValidator<? extends Object> getValidator(
			FacesContext facesContext, Class<? extends Object> beanClass) {
		// TODO - localization support.
		ValidatorKey key = new ValidatorKey(beanClass, calculateLocale(facesContext));
		ClassValidator result = classValidators.get(key);
		if (null == result) {
			result = createValidator(facesContext, beanClass);
			classValidators.put(key, result);
		}
		return result;
	}

	/*
	 * Method for create new instance of ClassValidator, if same not in cache.
	 * 
	 * @param beanClass - Class to validate @param locale - user Locale, used
	 * during validation process @return ClassValidator instance
	 */
	@SuppressWarnings("unchecked")
	protected ClassValidator<? extends Object> createValidator(
			FacesContext facesContext, Class<? extends Object> beanClass) {
		ResourceBundle bundle = createHibernateMessages(facesContext);
		return bundle == null ? new ClassValidator(beanClass)
				: new ClassValidator(beanClass, bundle);
	}

	/**
	 * @param facesContext
	 * @return
	 */
	protected ResourceBundle createHibernateMessages(FacesContext facesContext) {
		ResourceBundle bundle = getResourceBundle(facesContext, VALIDATOR_MESSAGES);
		ResourceBundle defaultMessagesBundle = getResourceBundle(facesContext, DEFAULT_VALIDATOR_MESSAGES);
		if(null != bundle && defaultMessagesBundle != null){
			bundle = new ResourceBundleChain(bundle, defaultMessagesBundle);
		} else if(null != defaultMessagesBundle){
			bundle = defaultMessagesBundle;
		}
		return bundle;
	}

	@Override
	protected Collection<String> validate(FacesContext facesContext, Object base, String property,
			Object value, Set<String> profiles) {
				InvalidValue[] invalidValues = validateBean(facesContext, base, property,
						value);
				if (null == invalidValues) {
					return null;
				} else {
					Collection<String> result = new ArrayList<String>(invalidValues.length);
					for (int i = 0; i < invalidValues.length; i++) {
						InvalidValue invalidValue = invalidValues[i];
						result.add(invalidValue.getMessage());
					}
					return result;
				}
			}

	/**
	 * Validate bean property of the base object aganist new value
	 * @param base
	 * @param property
	 * @param value
	 * 
	 * @return
	 */
	protected InvalidValue[] validateBean(FacesContext facesContext, Object base, String property,
			Object value) {
		Class<? extends Object> beanClass = base.getClass();
		
		InvalidValue[] invalidValues = validateClass(facesContext, beanClass, property, value);
		return invalidValues;
	}

	private static class JsfMessageInterpolator implements MessageInterpolator {

		private Locale locale;
		private MessageInterpolator delegate;

		public JsfMessageInterpolator(Locale locale,
				MessageInterpolator delegate) {
			this.locale = locale;
			this.delegate = delegate;
		}


		public String interpolate(String message, Validator validator,
				MessageInterpolator defaultInterpolator) {
			return delegate.interpolate(message, validator, defaultInterpolator);
		}

	}
	
	static class ResourceBundleChain extends ResourceBundle {
		
		private final ResourceBundle delegate;
		
		public ResourceBundleChain(ResourceBundle delegate, ResourceBundle parent) {
			this.delegate = delegate;
			setParent(parent);
		}

		@Override
		public Enumeration<String> getKeys() {
			// TODO Auto-generated method stub
			return null!=delegate?delegate.getKeys():Collections.<String>enumeration(Collections.<String>emptyList());
		}

		@Override
		protected Object handleGetObject(String key) {
			try {
				return null != delegate ? delegate.getObject(key) : null;

			} catch (MissingResourceException e) {
				return null;
			}
		}

		@Override
		public Locale getLocale() {
			return null!=delegate?delegate.getLocale():null;
		}
	}

}
