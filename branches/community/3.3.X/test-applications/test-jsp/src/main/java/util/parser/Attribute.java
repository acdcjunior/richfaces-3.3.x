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

/**
 * The Class Attribute represents component's attribute.
 */
public class Attribute {

	/** The name. */
	private String name;

	/** The type. */
	private String type;

	/** The description. */
	private String description;

	/** The status (NOT_TESTED, FAILED, PASSED). */
	private Status status;

	/**
	 * Instantiates a new attribute.
	 */
	public Attribute() {
		this.name = "";
		this.type = "";
		this.description = "";
		this.status = Status.NOT_TESTED;
	}

	/**
	 * Instantiates a new attribute.
	 * 
	 * @param name
	 *            the name
	 */
	public Attribute(String name) {
		this.type = "";
		this.name = name;
		this.description = "";
		this.status = Status.NOT_TESTED;
	}

	/**
	 * Instantiates a new attribute.
	 * 
	 * @param name
	 *            the name
	 * @param type
	 *            the type
	 * @param desc
	 *            the description
	 * @param status
	 *            the status
	 */
	public Attribute(String name, String type, String desc, Status status) {
		this.name = name;
		this.type = type;
		this.description = desc;
		this.status = status;
	}

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the type.
	 * 
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type.
	 * 
	 * @param type
	 *            the new type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Gets the description.
	 * 
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 * 
	 * @param description
	 *            the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the status.
	 * 
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 * 
	 * @param status
	 *            the new status
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[" + "Name: " + name + "\r\n" + "Description: " + description
				+ "\r\n" + "Type: " + type + "\r\n" + "Status: " + status + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		Attribute attr = (Attribute) obj;

		return (attr.getName().equals(this.name));
	}
}