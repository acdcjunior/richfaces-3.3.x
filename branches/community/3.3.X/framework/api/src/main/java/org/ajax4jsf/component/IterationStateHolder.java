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
package org.ajax4jsf.component;

import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIData;

/**
 * In the original {@link UIData} component, only state for a {@link EditableValueHolder} component saved for an iteration.
 * In the Richfaces, we also save state for a components implemented this interface.
 * @author asmirnov
 *
 */
public interface IterationStateHolder {
	
	/**
	 * Get component state for a current iteration.
	 * @return request-scope component state. Details are subject for a component implementation 
	 */
	public Object getIterationState();
	
	/**
	 * Set component state for the next iteration. State can be either previously saved iteration state
	 * or <code>null</code> value. In the second case component should reset its state to the initial.
	 * @param state request-scope component state or <code>null</code>. Details are subject for a component 
	 * implementation
	 */
	public void setIterationState( Object state);
	
}
