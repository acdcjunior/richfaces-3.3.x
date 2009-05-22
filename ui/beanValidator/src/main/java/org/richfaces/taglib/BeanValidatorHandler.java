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
package org.richfaces.taglib;

import java.util.Set;

import javax.faces.validator.Validator;

import org.ajax4jsf.renderkit.AjaxRendererUtils;
import org.richfaces.validator.FacesBeanValidator;

import com.sun.facelets.FaceletContext;
import com.sun.facelets.tag.MetaRuleset;
import com.sun.facelets.tag.TagAttribute;
import com.sun.facelets.tag.TagConfig;
import com.sun.facelets.tag.jsf.ValidateHandler;
import com.sun.facelets.tag.jsf.ValidatorConfig;

/**
 * @author asmirnov
 * 
 */
public class BeanValidatorHandler extends ValidateHandler {


	private TagAttribute _profiles;

	

	/**
	 * @param config
	 */
	public BeanValidatorHandler(ValidatorConfig config) {
		super(config);
		_profiles = getAttribute("profiles");
	}

	@Override
	protected Validator createValidator(FaceletContext ctx) {
		FacesBeanValidator validator = (FacesBeanValidator) ctx.getFacesContext()
				.getApplication().createValidator(
						FacesBeanValidator.BEAN_VALIDATOR_TYPE);
		if(null != _profiles){
			if(_profiles.isLiteral()){
				validator.setProfiles(AjaxRendererUtils.asSet(_profiles.getValue()));
			} else {
				validator.setProfiles(_profiles.getValueExpression(ctx, Set.class));
			}
		}
		return validator;
	}

}
