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

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.ajax4jsf.templatecompiler.builder.CompilationContext;
import org.ajax4jsf.templatecompiler.builder.CompilationException;
import org.ajax4jsf.templatecompiler.el.ELParser;
import org.ajax4jsf.templatecompiler.elements.A4JRendererElementsFactory;
import org.ajax4jsf.templatecompiler.elements.NameValuePair;
import org.ajax4jsf.templatecompiler.elements.TemplateElementBase;
import org.apache.velocity.VelocityContext;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 * Processing c:scriptOption
 * @author Nick Belaevski
 * @since 3.3.2
 */
public class ScriptOptionTemplateElement extends TemplateElementBase {

	private static final String TEMPLATE = A4JRendererElementsFactory.TEMPLATES_TEMPLATECOMPILER_PATH+"/scriptOption.vm";

	private List<NameValuePair> values = new ArrayList<NameValuePair>();
	
	private String defaultValue;
	
	private String variables = null;
	private String attributes = null;

	private String name = null;
	private String value = null;

	private String wrapperName;
	
	private static boolean isEmpty(String s) {
		return s == null || s.length() == 0;
	}

	private static String getWrapperName(String wrapper) {
		String result = "DEFAULT";
		
		if (!isEmpty(wrapper)) {
			result = wrapper.replaceAll("(\\p{Upper})", "_$1").toUpperCase(Locale.US);
		}

		return result;
	}
	
	private void initializeSettings(Node element) {
		NamedNodeMap nnm = element.getAttributes();
		
		Node variablesNode = nnm.getNamedItem("variables");
		if (variablesNode != null) {
			variables = variablesNode.getNodeValue();
		}
		
		Node attributesNode = nnm.getNamedItem("attributes");
		if (attributesNode != null) {
			attributes = attributesNode.getNodeValue();
		}
		
		Node nameNode = nnm.getNamedItem("name");
		if (nameNode != null) {
			name = nameNode.getNodeValue();
		}
		
		Node valueNode = nnm.getNamedItem("value");
		if (valueNode != null) {
			value = valueNode.getNodeValue();
		}
		
		Node defaultValueNode = nnm.getNamedItem("defaultValue");
		if (defaultValueNode != null) {
			defaultValue = defaultValueNode.getNodeValue();
		}
		
		Node wrapperNode = nnm.getNamedItem("wrapper");
		String wrapperNodeValue = null;
		if (wrapperNode != null) {
			wrapperNodeValue = wrapperNode.getNodeValue();
		}
		
		wrapperName = getWrapperName(wrapperNodeValue);
	}
	
	private void checkSettings() {
		if (!isEmpty(attributes) || !isEmpty(variables)) {
			if (!isEmpty(name) || !isEmpty(value)) {
				throw new RuntimeException("c:scriptOption tag misconfiguration: use either 'attributes'/'variables' or 'name' and 'value'!");
			}
		} else {
			if (isEmpty(name) || isEmpty(value)) {
				throw new RuntimeException("c:scriptOption tag misconfiguration: 'name' and 'value' should be both set!");
			}
		}
	}
	
	private void addELValues(String namesList, String elFormat, CompilationContext componentBean) {
		if (!isEmpty(namesList)) {
			String[] attributesSet = namesList.split(",");
			for (String attribute : attributesSet) {
				String trimmedAttribute = attribute.trim();
				if (trimmedAttribute.length() != 0) {
					String elExpression = String.format(elFormat, trimmedAttribute);
					values.add(new NameValuePair(trimmedAttribute, 
						ELParser.compileEL(elExpression, componentBean)));
				}
			}
		}
	}
	
	public ScriptOptionTemplateElement(Node element,
			CompilationContext componentBean) {
		super(element, componentBean);
		
		componentBean.addToImport("org.ajax4jsf.renderkit.RendererUtils.ScriptHashVariableWrapper");
		
		initializeSettings(element);
		checkSettings();
		
		addELValues(this.attributes, "#{component.attributes['%s']}", componentBean);
		addELValues(this.variables, "#{%s}", componentBean);
		
		if (!isEmpty(name) && !isEmpty(value)) {
			values.add(new NameValuePair(name.trim(), ELParser.compileEL(value.trim(), componentBean)));
		}
	}

	protected String getTemplateName() {
		return TEMPLATE;
	}

	public String getBeginElement() throws CompilationException {
		VelocityContext context = new VelocityContext();
		
		context.put("mapName", ScriptObjectTemplateElement.getVariableName());
		context.put("valuesList", values);
		
		if (!isEmpty(defaultValue)) {
			context.put("defaultValue", defaultValue);
		} else {
			context.put("defaultValue", null);
		}

		context.put("wrapperName", wrapperName);
		
		return this.getComponentBean().processTemplate(getTemplateName(), context);
	}

	public String getEndElement() throws CompilationException {
		return null;
	}
	
}
