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

package util.parser;

import java.util.ArrayList;

/**
 * The Class AttributesList is used for keeping all attributes of some component
 * and providing access to events' handlers, styles, classes and common
 * attributes.
 */
public class AttributesList extends ArrayList<Attribute> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3245089852351607636L;

	/**
	 * Instantiates a new attributes list.
	 */
	public AttributesList() {
		super();
	}

	/**
	 * Gets the names array.
	 * 
	 * @return the names array
	 */
	public ArrayList<String> getNamesArray() {
		ArrayList<String> result = new ArrayList<String>();
		for (Attribute attr : this) {
			result.add(attr.getName());
		}
		return result;
	}

	/**
	 * Gets the descriptions array.
	 * 
	 * @return the descriptions array
	 */
	public ArrayList<String> getDescriptionArray() {
		ArrayList<String> result = new ArrayList<String>();
		for (Attribute attr : this) {
			result.add(attr.getDescription());
		}
		return result;
	}

	/**
	 * Gets the types array.
	 * 
	 * @return the types array
	 */
	public ArrayList<String> getTypeArray() {
		ArrayList<String> result = new ArrayList<String>();
		for (Attribute attr : this) {
			result.add(attr.getType());
		}
		return result;
	}

	/**
	 * Gets the statuses array.
	 * 
	 * @return the statuses array
	 */
	public ArrayList<Status> getStatusArray() {
		ArrayList<Status> result = new ArrayList<Status>();
		for (Attribute attr : this) {
			result.add(attr.getStatus());
		}
		return result;
	}

	/**
	 * Gets the handlers.
	 * 
	 * @return the handlers
	 */
	public ArrayList<Attribute> getHandlers() {
		ArrayList<Attribute> result = new ArrayList<Attribute>();
		for (Attribute attr : this) {
			if (attr.getName().startsWith("on")) {
				result.add(attr);
			}
		}
		return result;
	}

	/**
	 * Gets the styles.
	 * 
	 * @return the styles
	 */
	public ArrayList<Attribute> getStyles() {
		ArrayList<Attribute> result = new ArrayList<Attribute>();
		for (Attribute attr : this) {
			if (attr.getName().indexOf("tyle") != -1
					|| attr.getName().indexOf("lass") != -1) {
				result.add(attr);
			}
		}
		return result;
	}

	/**
	 * Gets the common attributes.
	 * 
	 * @return the common attributes
	 */
	public ArrayList<Attribute> getCommonAttributes() {
		ArrayList<Attribute> result = new ArrayList<Attribute>();
		for (Attribute attr : this) {
			if (!(attr.getName().startsWith("on")
					|| (attr.getName().indexOf("tyle") != -1) || (attr
					.getName().indexOf("lass") != -1))) {
				result.add(attr);
			}
		}
		return result;
	}
}
