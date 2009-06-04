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

package org.ajax4jsf.templatecompiler.elements.vcp;

import org.ajax4jsf.templatecompiler.builder.CompilationContext;
import org.ajax4jsf.templatecompiler.builder.CompilationException;
import org.ajax4jsf.templatecompiler.el.ELParser;
import org.ajax4jsf.templatecompiler.elements.TemplateElementBase;
import org.w3c.dom.Node;

/**
 * @author Nick Belaevski
 * @since 3.3.2
 */
public class FWriteAsScript extends TemplateElementBase {

	private String value;
	
	public FWriteAsScript(Node element, CompilationContext componentBean) {
		super(element, componentBean);

		Node node = element.getAttributes().getNamedItem("value");
		if (node == null) {
			throw new RuntimeException("'value' attribute is required for f:writeAsScript tag!");
		}
		
		value = ELParser.compileEL(node.getNodeValue(), componentBean);
	}

	public String getBeginElement() throws CompilationException {
		return String.format("org.ajax4jsf.javascript.ScriptUtils.writeToStream(writer, %s);\n", value);
	}

	public String getEndElement() throws CompilationException {
		return null;
	}
}
