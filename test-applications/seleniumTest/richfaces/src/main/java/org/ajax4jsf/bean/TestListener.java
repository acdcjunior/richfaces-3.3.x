/**
 * License Agreement.
 *
 * JBoss RichFaces - Ajax4jsf Component Library
 *
 * Copyright (C) 2007 Exadel, Inc.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License version 2.1 as published by the Free Software Foundation.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA
 */ 
package org.ajax4jsf.bean;

import java.util.HashMap;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.ajax4jsf.context.AjaxContext;
import org.ajax4jsf.context.AjaxContextImpl;
import org.ajax4jsf.event.AjaxEvent;
import org.ajax4jsf.event.AjaxListener;

public class TestListener implements AjaxListener {

	@SuppressWarnings("unchecked")
	public void processAjax(AjaxEvent event) {
		System.out.println("TestListener");
		FacesContext context = FacesContext.getCurrentInstance();
		if (context != null) {
			AjaxContext ajaxContext = AjaxContextImpl.getCurrentInstance(context);
			if (ajaxContext != null) {
				Map<String, Object> data = (Map<String, Object>)ajaxContext.getResponseData();
				if (null == data) {
					data = new HashMap<String, Object>();
					ajaxContext.setResponseData(data);
				}
				data.put("FListener", true);
			}
			
		}
		
	}

}
