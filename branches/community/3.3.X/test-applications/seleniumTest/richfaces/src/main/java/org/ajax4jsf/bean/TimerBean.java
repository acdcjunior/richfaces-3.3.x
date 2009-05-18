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

import java.util.Date;

/**
 * @author asmirnov
 *
 */
public class TimerBean {

	private boolean _enabled;

	/**
	 * @return the enabled
	 */
	public boolean isEnabled() {
		return _enabled;
	}

	/**
	 * @param enabled the enabled to set
	 */
	public void setEnabled(boolean enabled) {
		_enabled = enabled;
	}
	
	public Date getTimer(){
		return new Date(System.currentTimeMillis());
	}
	
	public String go() {
		setEnabled(true);
		return null;
	}

	public String stop() {
		setEnabled(false);
		return null;
	}
}
