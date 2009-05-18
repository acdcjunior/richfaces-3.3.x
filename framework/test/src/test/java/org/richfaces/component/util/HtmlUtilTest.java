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

package org.richfaces.component.util;

import javax.faces.component.UIComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.component.UIInput;
import javax.faces.component.html.HtmlForm;
import javax.faces.context.FacesContext;

import org.ajax4jsf.tests.AbstractAjax4JsfTestCase;

import junit.framework.TestCase;

/**
 * @author Nick Belaevski - nbelaevski@exadel.com
 * created 11.04.2007
 * 
 */
public class HtmlUtilTest extends TestCase {
	
	public HtmlUtilTest(String name) {
		super(name);
	}
	
	public void setUp() throws Exception {
		super.setUp();
	}
	
	public void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testQualifySize() throws Exception {
		assertEquals("", HtmlUtil.qualifySize(""));

		assertEquals("100px", HtmlUtil.qualifySize("100px"));
		assertEquals("100px", HtmlUtil.qualifySize("100"));
		assertEquals("100pt", HtmlUtil.qualifySize("100pt"));

		assertEquals("100in", HtmlUtil.qualifySize("100in"));
		assertEquals("100cm", HtmlUtil.qualifySize("100cm"));
		assertEquals("100em", HtmlUtil.qualifySize("100em"));
		assertEquals("100%", HtmlUtil.qualifySize("100%"));
	}
	
	public void testAddToSize() throws Exception {
		assertEquals("120px", HtmlUtil.addToSize("100", "20px"));
		assertEquals("120px", HtmlUtil.addToSize("100px", "20"));
		assertEquals("120px", HtmlUtil.addToSize("100", "20"));
		assertEquals("120px", HtmlUtil.addToSize("100px", "20px"));
	}
	
	public void testExpandIdSelector() {
		String selector = ".class_form+#-Test .class2 #_aaaa";
		
		UIComponent component = new UIComponentBase() {
			public String getFamily() {
				// TODO Auto-generated method stub
				return null;
			}
			public UIComponent findComponent(String expr) {
				if ("-Test".equals(expr)) {
					return new UIComponentBase() {
						public String getClientId(FacesContext context) {
							return "component1";
						}
						public String getFamily() {
							// TODO Auto-generated method stub
							return null;
						}
					};
				} else if ("_aaaa".equals(expr)) {
					return new UIComponentBase() {
						public String getClientId(FacesContext context) {
							return "component2";
						}
						public String getFamily() {
							// TODO Auto-generated method stub
							return null;
						}
					};
				}
				
				return null;
			}
		};
		
		String string = 
			HtmlUtil.expandIdSelector(selector, component, null);

		assertEquals(".class_form+#component1 .class2 #component2", string);
		
		String s = ".class_form+.component1 .class2 #1component2";
		assertEquals(s, HtmlUtil.expandIdSelector(s, component, null));
	}
}
