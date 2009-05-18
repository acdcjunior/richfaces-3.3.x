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
package org.richfaces.model.impl.expressive;

import javax.el.ELContext;
import javax.el.ELException;
import javax.el.ELResolver;

/**
 * Expression evaluated by applying application
 * property resolver to the base object
 * @author Maksim Kaszynski
 *
 */
final class SimplePropertyExpression extends Expression {
	/**
	 * 
	 */
	private final ELResolver resolver;
	private final ELContext context;
	
	/**
	 * @param n
	 * @param resolver
	 */
	SimplePropertyExpression(String n, ELContext context, ELResolver resolver) {
		super(n);
		this.resolver = resolver;
		this.context = context;
	}

	public Object evaluate(Object base) {
		Object o = null;
		try {
			return resolver.getValue(context, base, getExpressionString());
		} catch (ELException e) {
			
		} 
		
		return o;
	}
}