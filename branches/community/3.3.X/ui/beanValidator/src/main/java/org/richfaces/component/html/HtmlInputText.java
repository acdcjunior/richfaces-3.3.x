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
package org.richfaces.component.html;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.richfaces.component.UIRichInput;
import org.richfaces.validator.FacesBeanValidator;
import org.richfaces.validator.NullValueValidator;

/**
 * @author asmirnov
 * 
 */
public class HtmlInputText extends javax.faces.component.html.HtmlInputText {

	@Override
	protected void validateValue(FacesContext context, Object newValue) {
		// If our value is valid, enforce the required property if present
		if (isValid() && isRequired() && UIRichInput.isEmpty(newValue)) {
			super.validateValue(context, newValue);
		}
		UIRichInput.validateInput(context, this, newValue);

	}

}
