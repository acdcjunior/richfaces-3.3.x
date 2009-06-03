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

package org.ajax4jsf.templatecompiler.elements.std;

import java.util.HashMap;

import org.ajax4jsf.templatecompiler.builder.CompilationContext;
import org.ajax4jsf.templatecompiler.builder.CompilationException;
import org.ajax4jsf.templatecompiler.elements.A4JRendererElementsFactory;
import org.ajax4jsf.templatecompiler.elements.TemplateElement;
import org.ajax4jsf.templatecompiler.elements.TemplateElementBase;
import org.apache.velocity.VelocityContext;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 * Processing c:scriptObject
 * 
 * @author Nick Belaevski
 * @since 3.3.2
 * 
 */
public class ScriptObjectTemplateElement extends TemplateElementBase {

	private static final String TEMPLATE = A4JRendererElementsFactory.TEMPLATES_TEMPLATECOMPILER_PATH+"/scriptObject.vm";

	private String variableName;

	private static final Class<?> VARIABLE_TYPE = HashMap.class;
	
	public ScriptObjectTemplateElement(final Node element,
			final CompilationContext componentBean) {
		super(element, componentBean);

		NamedNodeMap nnm = element.getAttributes();
		variableName = nnm.getNamedItem("var").getNodeValue();
		if (variableName == null || variableName.length() == 0) {
			throw new RuntimeException("'var' attribute required for c:scriptObject tag!");
		}
		
		this.getComponentBean().addVariable(variableName, VARIABLE_TYPE);
	}

	@Override
	public void addSubElement(TemplateElement e) {
		super.addSubElement(e);
		
		if (e instanceof ScriptOptionTemplateElement) {
			((ScriptOptionTemplateElement) e).setMapName(variableName);
		}
	}
	
	public String getBeginElement() throws CompilationException {
		VelocityContext context = new VelocityContext();
		context.put("variable", this.variableName);
		context.put("type", VARIABLE_TYPE.getName().replace('$', '.'));
		
		return this.getComponentBean().processTemplate(getTemplateName(), context);
	}
	
	/**
	 * @return
	 */
	protected String getTemplateName() {
		return TEMPLATE;
	}

	public String getEndElement() {
		return null;
	}
}