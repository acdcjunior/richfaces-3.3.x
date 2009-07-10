/**
 * License Agreement.
 *
 * Ajax4jsf 1.1 - Natural Ajax for Java Server Faces (JSF)
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

package util;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.context.FacesContext;

/**
 * The Class BeanManager provides jsf managed bean from http session.
 */
public class BeanManager {

	/**
	 * Retrieves jsf managed bean from http session.
	 * 
	 * @param managedBeanName
	 *            name of bean.
	 * 
	 * @return managed bean if founded.
	 */
	public static Object getManagedBeanFromSession(String managedBeanName) {
		ValueExpression preDestroyVe = getExpressionFactory()
				.createValueExpression(getElContext(),
						"#{" + managedBeanName + "}", Object.class);
		return preDestroyVe.getValue(getElContext());
	}

	/**
	 * Returns ElContext object.
	 * 
	 * @return el context.
	 */
	protected static ELContext getElContext() {
		return FacesContext.getCurrentInstance().getELContext();
	}

	/**
	 * Returns ExpressionFactory object.
	 * 
	 * @return ExpressionFactory.
	 */
	protected static ExpressionFactory getExpressionFactory() {
		return FacesContext.getCurrentInstance().getApplication()
				.getExpressionFactory();
	}
}
