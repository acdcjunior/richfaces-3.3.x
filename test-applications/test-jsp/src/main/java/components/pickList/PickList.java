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

package components.pickList;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import components.ImmediateComponentBean;
import components.RequiredComponentBean;

/**
 * The PickList bean.
 */
public class PickList implements ImmediateComponentBean, RequiredComponentBean {

	/** The value. */
	private List<String> value;

	/** The select items. */
	private ArrayList<SelectItem> selectItems;

	/** The immediate. */
	private boolean immediate = false;

	/** The required. */
	private boolean required = false;

	/**
	 * Instantiates a new pick list.
	 */
	public PickList() {
		if (selectItems == null) {
			selectItems = new ArrayList<SelectItem>();
			for (int i = 0; i < 10; i++) {
				selectItems.add(new SelectItem("Car-" + i, "lcar-" + i));
			}
		}
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	public List<String> getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 * 
	 * @param value
	 *            the new value
	 */
	public void setValue(List<String> value) {
		this.value = value;
	}

	/**
	 * Gets the select items.
	 * 
	 * @return the select items
	 */
	public ArrayList<SelectItem> getSelectItems() {
		return selectItems;
	}

	/**
	 * Sets the select items.
	 * 
	 * @param selectItems
	 *            the new select items
	 */
	public void setSelectItems(ArrayList<SelectItem> selectItems) {
		this.selectItems = selectItems;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see components.ImmediateComponentBean#setImmediate(boolean)
	 */
	public void setImmediate(boolean immediate) {
		this.immediate = immediate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see components.ImmediateComponentBean#isImmediate()
	 */
	public boolean isImmediate() {
		return immediate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see components.RequiredComponentBean#isRequired()
	 */
	public boolean isRequired() {
		return required;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see components.RequiredComponentBean#setRequired(boolean)
	 */
	public void setRequired(boolean required) {
		this.required = required;
	}
}
