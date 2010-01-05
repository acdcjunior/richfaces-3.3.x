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
package org.richfaces.component;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.el.ELContext;
import javax.el.ValueExpression;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.component.EditableValueHolder;
import javax.faces.component.NamingContainer;
import javax.faces.component.UIComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.component.UIInput;
import javax.faces.component.UIMessage;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.FacesEvent;
import javax.faces.event.PhaseId;
import javax.faces.validator.Validator;

import org.ajax4jsf.component.AjaxComponent;
import org.ajax4jsf.component.AjaxContainer;
import org.ajax4jsf.component.AjaxSupport;
import org.ajax4jsf.component.EventValueExpression;
import org.ajax4jsf.context.AjaxContext;
import org.ajax4jsf.el.ELContextWrapper;
import org.ajax4jsf.event.AjaxEvent;
import org.ajax4jsf.event.AjaxListener;
import org.ajax4jsf.renderkit.AjaxContainerRenderer;
import org.ajax4jsf.renderkit.AjaxRendererUtils;
import org.ajax4jsf.util.CapturingELResolver;
import org.richfaces.event.ValidationEvent;
import org.richfaces.validator.HibernateValidator;
import org.richfaces.validator.FacesBeanValidator;
import org.richfaces.validator.GraphValidator;


/**
 * JSF component class
 * 
 */
public abstract class UIGraphValidator extends UIComponentBase {

	public static final String COMPONENT_TYPE = "org.richfaces.GraphValidator";

	public static final String COMPONENT_FAMILY = "org.richfaces.GraphValidator";

	public static final String STATE_ATTRIBUTE_PREFIX = COMPONENT_TYPE+NamingContainer.SEPARATOR_CHAR;
	
	
	

	/**
	 * Get object for validation
	 * 
	 * @return
	 */
	public abstract Object getValue();

	/**
	 * Set object for validation
	 * 
	 * @param newvalue
	 */
	public abstract void setValue(Object newvalue);
	
	/**
	 * Get object for validation
	 * 
	 * @return
	 */
	public abstract String getSummary();

	/**
	 * Set object for validation
	 * 
	 * @param newvalue
	 */
	public abstract void setSummary(String newvalue);

	/**
	 * Get set of profiles for validation
	 * 
	 * @return
	 */
	public abstract Object getProfiles();

	/**
	 * Set set of profiles for validation
	 * 
	 * @param newvalue
	 */
	public abstract void setProfiles(Object newvalue);
	
	/**
	 * Get graph validator Id.
	 * @return
	 */
	public abstract String getType();

	/**
	 * Set graph validator Id.
	 * @param newvalue
	 */
	public abstract void setType(String newvalue);


	@Override
	public void processDecodes(FacesContext context) {
		GraphValidatorState validatorState = null;
		// Detect value EL-expression.
		ValueExpression valueExpression = getValueExpression("value");
		if (null != valueExpression) {

			
			Object value = getValue();
			if (null !=value && value instanceof Cloneable) {
				try {
				ELContext initialELContext = context.getELContext();

				CapturingELResolver capturingELResolver = new CapturingELResolver(initialELContext.getELResolver());
				Class<?> type = valueExpression.getType(new ELContextWrapper(initialELContext, capturingELResolver));
				if(null != type) {
					validatorState = new GraphValidatorState();
					Method method = getCloneMethod(value.getClass());
					if(!Modifier.isPublic(method.getModifiers())){
						// Method Object#clone() is protected by default. Make it public
						// unless developer did it.
						method.setAccessible(true);
					}
					validatorState.cloned = method.invoke(value);
					validatorState.base = capturingELResolver.getBase();
					validatorState.property = capturingELResolver.getProperty();
					validatorState.active = true;
					context.getExternalContext().getRequestMap().put(getStateId(context), validatorState);
				}
				} catch (NoSuchMethodException e) {
					// do nothing, that is really not possible.
				} catch (InvocationTargetException e) {
					throw new FacesException(e);
				} catch (IllegalArgumentException e) {
					// do nothing, that is really not possible.
				} catch (IllegalAccessException e) {
					throw new FacesException(e);
				}
			}
		}
		super.processDecodes(context);
		if(null != validatorState){
			validatorState.active = false;
		}
	}

	private Method getCloneMethod(Class<?> clazz) throws NoSuchMethodException {
		try {
			return clazz.getDeclaredMethod("clone");
		} catch( NoSuchMethodException e){
			if(null != clazz.getSuperclass()){
				return getCloneMethod(clazz.getSuperclass());
			} else {
				throw e;
			}
		}
	}

	protected String getStateId(FacesContext context) {
		String stateId = STATE_ATTRIBUTE_PREFIX+getClientId(context);
		return stateId;
	}
	
	protected GraphValidatorState getValidatorState(FacesContext context){
		return (GraphValidatorState) context.getExternalContext().getRequestMap().get(getStateId(context));
	}

	@Override
	public void processValidators(FacesContext context) {
		GraphValidatorState validatorState = getValidatorState(context);
		if(null != validatorState){
			validatorState.active = true;
		}
		super.processValidators(context);
		if(null != validatorState){
			validatorState.active = false;
			validateObject(context, validatorState.cloned);
			context.getExternalContext().getRequestMap().remove(getStateId(context));
		}
	}
	
	@Override
	public void processUpdates(FacesContext context) {
		super.processUpdates(context);
		Object value = getValue();
		validateObject(context, value);
	}

	private void validateObject(FacesContext context, Object value) {
		if (null != value) {
			Validator validator = context.getApplication().createValidator(getType());
			if (validator instanceof GraphValidator) {
				GraphValidator graphValidator = (GraphValidator) validator;
				Collection<String> messages = graphValidator.validateGraph(context,this, value,getProfiles());
				if (null != messages) {
					context.renderResponse();
					// send all validation messages.
					String clientId = getClientId(context);
					for (String msg : messages) {
						// TODO - create Summary message ?
						String summary = null != getSummary() ? getSummary() + msg : msg;
						context.addMessage(clientId, new FacesMessage(
								FacesMessage.SEVERITY_ERROR, summary, msg));
					}
				}
				
			} else {
				throw new FacesException("Validator "+FacesBeanValidator.BEAN_VALIDATOR_TYPE+" does not implement GraphValidator");
			}

		}
	}
	
	@Override
	public void encodeBegin(FacesContext context) throws IOException {
		super.encodeBegin(context);
		FacesBeanValidator validator = (FacesBeanValidator)context.getApplication().createValidator(getType());
		validator.setSummary(getSummary());
		ValueExpression expression = getValueExpression("profiles");
		if(null != expression){
			validator.setProfiles(expression);
		} else {
			validator.setProfiles(getProfiles());
		}
		setupValidators(this,validator);
	}
	
	@Override
	public void encodeChildren(FacesContext context) throws IOException {
		if(isRendered()){
			for (UIComponent child : getChildren()) {
				if(child.isRendered()){
					child.encodeAll(context);
				}
			}
		}
	}
	
	private void setupValidators(UIComponent component,
			Validator validator) {
		Iterator<UIComponent> facetsAndChildren = component.getFacetsAndChildren();
		while (facetsAndChildren.hasNext()) {
			UIComponent child = facetsAndChildren.next();
			if (child instanceof EditableValueHolder) {
				EditableValueHolder input = (EditableValueHolder) child;
				setupValidator(input,validator);
			}
			setupValidators(child, validator);
		}
	}

	/**
	 * @param input
	 */
	private void setupValidator(EditableValueHolder input,Validator validator) {
		Validator[] validators = input.getValidators();
		for (int i = 0; i < validators.length; i++) {
			if(validators[i] instanceof FacesBeanValidator){
				return;
			}
		}
		input.addValidator(validator);
	}

	@Override
	public boolean getRendersChildren() {
		return true;
	}
	
	public static final class GraphValidatorState {
		private boolean active = false;
		private Object cloned;
		private Object base;
		private Object property;
		/**
		 * @return the active
		 */
		public boolean isActive() {
			return active;
		}
		/**
		 * @param active the active to set
		 */
		public void setActive(boolean active) {
			this.active = active;
		}
		/**
		 * @return the cloned
		 */
		public Object getCloned() {
			return cloned;
		}
		/**
		 * @param cloned the cloned to set
		 */
		public void setCloned(Object cloned) {
			this.cloned = cloned;
		}
		/**
		 * @return the base
		 */
		public Object getBase() {
			return base;
		}
		/**
		 * @param base the base to set
		 */
		public void setBase(Object base) {
			this.base = base;
		}
		/**
		 * @return the property
		 */
		public Object getProperty() {
			return property;
		}
		/**
		 * @param property the property to set
		 */
		public void setProperty(Object property) {
			this.property = property;
		}
		
		public boolean isSameBase(Object base){
			return (null == base && null == this.base)||(base == this.base);
		}
		
		public boolean isSameProperty(Object property){
			if(null == this.property){
				return null == property;
			} else {
				return this.property.equals(property);
			}
		}
		
		public boolean isSame(Object base, Object property){
			return isSameBase(base)&& isSameProperty(property)&& active;
		}
	}

}
