/*
 * JBoss, Home of Professional Open Source
 * Copyright ${year}, Red Hat, Inc. and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */ 
package org.richfaces.context;

import javax.faces.context.FacesContext;
import javax.faces.context.PartialViewContext;
import javax.faces.context.PartialViewContextFactory;

import org.ajax4jsf.context.AjaxContext;

/**
 * @author Nick Belaevski
 *
 */
public class PartialViewContextFactoryImpl extends PartialViewContextFactory {

	private PartialViewContextFactory defaultFactory;
	
	public PartialViewContextFactoryImpl(PartialViewContextFactory defaultFactory) {
		this.defaultFactory = defaultFactory;
	}

	@Override
	public PartialViewContext getPartialViewContext(FacesContext facesContext) {
		PartialViewContext partialViewContext = this.defaultFactory.getPartialViewContext(facesContext);
		
		boolean isAjaxRequest = AjaxContext.getCurrentInstance(facesContext).isAjaxRequest(facesContext);
		if (isAjaxRequest) {
			partialViewContext = new PartialViewContextImpl(partialViewContext, facesContext);
		}

		return partialViewContext;
	}
	
	
}
