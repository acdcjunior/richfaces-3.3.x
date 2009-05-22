/**
 * 
 */
package org.richfaces.taglib;

import java.util.Set;

import javax.faces.component.UIComponent;

import org.ajax4jsf.renderkit.AjaxRendererUtils;
import org.richfaces.component.UIGraphValidator;

import com.sun.facelets.FaceletContext;
import com.sun.facelets.tag.MetaRuleset;
import com.sun.facelets.tag.TagAttribute;
import com.sun.facelets.tag.jsf.ComponentConfig;
import com.sun.facelets.tag.jsf.ComponentHandler;

/**
 * @author asmirnov
 *
 */
public class GraphValidatorHandler extends ComponentHandler {

	private TagAttribute _profiles;

	/**
	 * @param config
	 */
	public GraphValidatorHandler(ComponentConfig config) {
		super(config);
		_profiles = getAttribute("profiles");
	}

	@Override
	protected MetaRuleset createMetaRuleset(Class type) {
		return super.createMetaRuleset(type).ignore("profiles");
	}
	
	
	@Override
	protected void onComponentCreated(FaceletContext ctx, UIComponent c,
			UIComponent parent) {
		super.onComponentCreated(ctx, c, parent);
		if (c instanceof UIGraphValidator) {
			UIGraphValidator graphValidator = (UIGraphValidator) c;
			if(null != _profiles){
				if (_profiles.isLiteral()) {
					graphValidator.setProfiles(AjaxRendererUtils.asSet(_profiles.getValue()));
				} else {
					graphValidator.setValueExpression("profiles", _profiles.getValueExpression(ctx, Set.class));
				}
			}
			
		}
	}
}
