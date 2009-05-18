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

package org.ajax4jsf.gwt.client;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Gwt event exceptions wrapper for Rpc calls
 * 
 * @author shura
 */
public class GwtFacesException extends Throwable implements IsSerializable {

	private String _message;

	/**
	 * 
	 */
	public GwtFacesException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public GwtFacesException(String message) {
		super();
		_message = message;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return Returns the message.
	 */
	public String getMessage() {
		return _message;
	}

	/**
	 * @param message
	 *            The message to set.
	 */
	public void setMessage(String message) {
		_message = message;
	}

}
