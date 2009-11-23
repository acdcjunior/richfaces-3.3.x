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

/*
 * JspTagTest.java		Date created: 14.12.2007
 * Last modified by: $Author$
 * $Revision$	$Date$
 */

package org.richfaces.jsp.tag;

import java.util.ArrayList;
import java.util.List;

import javax.el.ELContext;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.webapp.UIComponentClassicTagBase;
import javax.servlet.jsp.JspException;

import org.ajax4jsf.tests.AbstractJspTestCase;
import org.ajax4jsf.tests.MockPageContext;
import org.ajax4jsf.tests.MockValueExpression;
import org.richfaces.component.UIColumn;
import org.richfaces.component.UIDataTable;
import org.richfaces.component.html.HtmlDataTable;
import org.richfaces.taglib.ColumnsTag;
import org.richfaces.taglib.DataTableTag;

/**
 * Test class for JSP columns tag
 * 
 * @author Andrey Markavtsov
 * @author Nick Belaevski
 */
public class ColumnsJspTagTest extends AbstractJspTestCase {

	/** Jsp tag to be tested */
	private ColumnsTag tag;

	private DataTableTag parentTag;

	private UIDataTable dataTable;

	/** Columns count to be created */
	private int columnsCount;

	/**
	 * TODO Description goes here.
	 * 
	 * @param name
	 */
	public ColumnsJspTagTest(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Creates value expression
	 * 
	 * @param o
	 *            - object to be returned by this expression
	 * @param el
	 *            - ELContext
	 * @return - created value expression
	 */
	private ValueExpression getValueExpression(Object o, ELContext el) {
		ValueExpression exp = new MockValueExpression(o);
		return exp;
	}

	/**
	 * Inits parent tag contained Data Table component
	 * 
	 * @throws JspException
	 */
	private void initParentTag() throws JspException {

		dataTable = new HtmlDataTable();
		parentTag = new DataTableTag();
		parentTag.setBinding(application.getExpressionFactory()
				.createValueExpression(dataTable, UIComponent.class));
		parentTag.setPageContext(new MockPageContext());
		List<UIComponentClassicTagBase> list = new ArrayList<UIComponentClassicTagBase>();
		list.add(parentTag);
	}

	private void initTag() {
		ELContext el = facesContext.getELContext();
		ValueExpression expr = null;

		// begin
		expr = getValueExpression(0, el);
		tag.setBegin(expr);

		// value
		List<String> list = new ArrayList<String>();
		columnsCount = (int) Math.random() * 10;
		if (columnsCount == 0)
			columnsCount = 1;
		for (int i = 0; i < columnsCount; i++) {
			list.add(Integer.toString(i));
		}
		expr = getValueExpression(list, el);
		tag.setValue(expr);

		// style
		expr = getValueExpression("color: Blue;", el);
		tag.setStyle(expr);

		// var
		expr = getValueExpression("var", el);
		tag.setVar(expr);

		// width
		expr = getValueExpression("100px;", el);
		tag.setWidth(expr);

		// index
		expr = getValueExpression("counter", el);
		tag.setIndex(expr);

		MockPageContext pageContext = new MockPageContext();
		tag.setPageContext(pageContext);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	public void setUp() throws Exception {
		super.setUp();

		facesContext.getApplication().addComponent("org.richfaces.Column",
				"org.richfaces.component.html.HtmlColumn");

		tag = new ColumnsTag();
		tag.setPageContext(pageContext);
		initTag();
		initParentTag();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 */
	@Override
	public void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
		this.tag = null;
		this.parentTag = null;
		this.columnsCount = 0;
		this.dataTable = null;
	}

	/**
	 * Tests JSP columns tag
	 * 
	 * @throws Exception
	 */
	public void testJspTag() throws Exception {
		parentTag.doStartTag();
		tag.doStartTag();

		int body = tag.doAfterBody();
		while (body == tag.EVAL_BODY_AGAIN) {
			body = tag.doAfterBody();
		}
		tag.doEndTag();
		parentTag.doEndTag();

		assertNotNull(dataTable);
		assertTrue(dataTable.getChildCount() == columnsCount);

		UIColumn column = (UIColumn) dataTable.getChildren().get(0);
		assertNotNull(column);
		assertTrue(column.getParent().equals(dataTable));
	}

}
