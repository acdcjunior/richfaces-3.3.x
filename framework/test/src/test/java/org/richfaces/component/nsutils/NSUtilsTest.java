/**
 * License Agreement.
 *
 *  JBoss RichFaces - Ajax4jsf Component Library
 *
 * Copyright (C) 2007  Exadel, Inc.
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

package org.richfaces.component.nsutils;

import java.io.StringWriter;

import javax.faces.component.UIInput;
import javax.faces.context.ResponseWriter;

import org.ajax4jsf.tests.AbstractAjax4JsfTestCase;
import org.apache.shale.test.mock.MockResponseWriter;

public class NSUtilsTest extends AbstractAjax4JsfTestCase {

	public NSUtilsTest(String name) {
		super(name);
	}

	public void setUp() throws Exception {
		super.setUp();
	}

	public void tearDown() throws Exception {
		super.tearDown();
	}

	public void testWriteNameSpace() throws Exception {
		StringWriter stringWriter = new StringWriter();
		facesContext.setResponseWriter(new MockResponseWriter(stringWriter, "text/html", "UTF8"));
		ResponseWriter writer = facesContext.getResponseWriter();
		
		UIInput input = new UIInput();
		
		writer.startDocument();
		writer.startElement("span", input);
		
		NSUtils.writeNameSpace(facesContext, input);
		
		writer.endElement("span");
		writer.endDocument();

		String result = stringWriter.getBuffer().toString();
		assertTrue(result.contains("span xmlns:rich=\"http://richfaces.ajax4jsf.org/rich\""));
	}
}
