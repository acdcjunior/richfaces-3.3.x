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
package org.richfaces.component;

import javax.faces.component.UIInput;

/**
 * UI implementation of InplaceInput component
 * @author Vladimir Molotkov
 * @since 3.2.0
 */


public abstract class UIInplaceInput extends UIInput {
	
	/**
     * <p>The standard component type for this component.</p>
     */
    public static final String COMPONENT_TYPE = "org.richfaces.InplaceInput";
    final static public  String COMPONENT_FAMILY = "org.richfaces.InplaceInput";
    
//    public static final String VALUE_SUFFIX = "value";
    public abstract String getDefaultLabel();
    public abstract void setDefaultLabel(String label);
}
