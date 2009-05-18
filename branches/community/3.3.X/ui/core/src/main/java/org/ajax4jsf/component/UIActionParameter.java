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

package org.ajax4jsf.component;

import javax.el.ELContext;
import javax.el.MethodExpression;
import javax.el.ValueExpression;
import javax.faces.FacesException;
import javax.faces.component.UIComponentBase;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

import org.ajax4jsf.Messages;

/**
 * @author shura (latest modification by $Author: alexsmirnov $)
 * @version $Revision: 1.1.2.2 $ $Date: 2007/02/01 15:31:55 $
 * 
 */
public class UIActionParameter extends UIParameter implements ActionListener,
		JavaScriptParameter {

	public static final String COMPONENT_TYPE = "org.ajax4jsf.components.UIActionParameter";

	/***************************************************************************
	 * Binding for update on ActionEvent
	 */
	private ValueExpression _assignToBinding = null;

	public void setAssignToBinding(ValueExpression propertyBinding) {
		this._assignToBinding = propertyBinding;
	}

	public ValueExpression getAssignToBinding() {
		return _assignToBinding;
	}

	/** ********************************************************* */

	/***************************************************************************
	 * Action listener to call after binding has been updated
	 */
	private MethodExpression _actionListener = null;
	
	public MethodExpression getActionListener() {
		return _actionListener;
	}

	public void setActionListener(MethodExpression actionListener) {
		this._actionListener = actionListener;
	}

	/** ********************************************************* */
	
	/***************************************************************************
	 * Converter for update value with this parameter
	 */
	private Converter _converter = null;

	public void setConverter(Converter converter) {
		this._converter = converter;
	}

	public Converter getConverter() {
		return _converter;
	}

	/** ********************************************************* */

	/***************************************************************************
	 * Skip quota escaping of parameter value - for substitute JavaScript
	 * exspression on submit
	 */
	private Boolean _noEscape = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ajax4jsf.framework.ajax.JavaScriptParameter#setNoEscape(boolean)
	 */
	public void setNoEscape(boolean noEscape) {
		this._noEscape = Boolean.valueOf(noEscape);
	}

	private static String NO_ESCAPE_ATTR = "noEscape";

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ajax4jsf.framework.ajax.JavaScriptParameter#isNoEscape()
	 */
	public boolean isNoEscape() {
		return isValueOrBinding(_noEscape, NO_ESCAPE_ATTR);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.faces.component.UIParameter#getName()
	 */
	public String getName() {
		String name = super.getName();
		// If name not set - use clientId. be Careful !
		if (null == name) {
			name = getClientId(FacesContext.getCurrentInstance());
		}
		return name;
	}

	public Object getValue() {
		Object value = super.getValue();
		// TODO - perform conversion if converter is present.
		if (null != value) {
			Class<?> type = value.getClass();
			FacesContext context = getFacesContext();
			Converter converter = createConverter(context, type);
			if (null != converter) {
				value = converter.getAsString(context, this, value);

			}

		}
		return value;
	}

	/** ********************************************************* */

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.faces.event.ActionListener#processAction(javax.faces.event.ActionEvent)
	 */
	public void processAction(ActionEvent actionEvent)
			throws AbortProcessingException {
		FacesContext context = getFacesContext();
		ELContext elContext = context.getELContext();
		ValueExpression updateBinding = getAssignToBinding();
		if (updateBinding != null && (!updateBinding.isReadOnly(elContext))) {
			Object requestValue = context.getExternalContext()
					.getRequestParameterMap().get(getName());
			if (requestValue != null && requestValue instanceof String) {
				Class<?> type = updateBinding.getType(elContext);
				Converter converter = createConverter(context, type);
				if (null != converter) {
					requestValue = converter.getAsObject(context, this,
							(String) requestValue);

				}
			}
			if (null != requestValue) {
				updateBinding.setValue(elContext, requestValue);
			}

			MethodExpression listener = getActionListener();
			if (listener != null) {
				listener.invoke(elContext, new Object[] {actionEvent});
			}
		}
	}

	/**
	 * @param context
	 * @param type
	 * @return
	 * @throws FacesException
	 */
	private Converter createConverter(FacesContext context, Class<?> type)
			throws FacesException {
		Converter converter = getConverter();
		if (converter == null && type != null && !type.equals(String.class)
				&& !type.equals(Object.class)) {
			try {
				converter = context.getApplication().createConverter(type);
			} catch (Exception e) {
				throw new FacesException(Messages.getMessage(
						Messages.NO_CONVERTER_REGISTERED, type.getName()), e);
			}
		}
		return converter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.faces.component.UIComponentBase#restoreState(javax.faces.context.FacesContext)
	 */
	public void restoreState(FacesContext context, Object state) {
		Object values[] = (Object[]) state;
		super.restoreState(context, values[0]);
		// restore fields values
		_assignToBinding = (ValueExpression) UIComponentBase.restoreAttachedState(
				context, values[1]);
		_noEscape = (Boolean) values[2];
		_converter = (Converter) UIComponentBase.restoreAttachedState(context,
				values[3]);
		_actionListener = (MethodExpression) UIComponentBase.restoreAttachedState(context, 
				values[4]);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.faces.component.UIComponentBase#saveState(javax.faces.context.FacesContext)
	 */
	public Object saveState(FacesContext context) {
		Object values[] = new Object[5];
		values[0] = super.saveState(context);
		// save fields values
		values[1] = UIComponentBase
				.saveAttachedState(context, _assignToBinding);
		values[2] = _noEscape;
		values[3] = UIComponentBase.saveAttachedState(context, _converter);
		values[4] = UIComponentBase.saveAttachedState(context, _actionListener);
		return ((Object) (values));
	}

	/**
	 * @param field -
	 *            value of field to get.
	 * @param name -
	 *            name of field, to get from ValueBinding
	 * @return boolean value, based on field or valuebinding.
	 */
	private boolean isValueOrBinding(Boolean field, String name) {
		if (null != field) {
			return field.booleanValue();
		}
		ValueExpression vb = getValueExpression(name);
		if (null != vb) {
			FacesContext context = getFacesContext();
			ELContext elContext = context.getELContext();
			return ((Boolean) vb.getValue(elContext)).booleanValue();
		} else {
			return false;
		}
	}

}
