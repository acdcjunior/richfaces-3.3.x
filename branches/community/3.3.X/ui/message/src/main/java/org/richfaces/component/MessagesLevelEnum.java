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

/**
 * @author Andrey Markhel
 * This class is enumeration of all possible <code>FacesMessages.SEVERITY</code> levels of errors
 *
 */
public enum MessagesLevelEnum {
	PASSED, INFO, WARN, FATAL, ERROR;
	
	/**
	 * This method return all levels of errors  to be rendered(all with severity higher then specified min level and also min level)
	 * @param level - string representation of minimum level of error should be rendered
	 * @return all levels of errors to be rendered(all with severity higher then specified min level and also min level)
	 */
	public static MessagesLevelEnum[] getHigherLevels(String level) {
		MessagesLevelEnum [] all = values();
		for (int i = 0; i < all.length; i++) {
			if (all[i].name().equals(level.toUpperCase().trim())) {
				MessagesLevelEnum [] levels = new MessagesLevelEnum [all.length - i];
				System.arraycopy(all, i, levels, 0, all.length - i);
				return levels;
			}
		}
		return values();
	}
}
