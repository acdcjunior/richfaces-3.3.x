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

package components.comboBox;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import components.ImmediateComponentBean;
import components.RequiredComponentBean;

import util.data.Car;
import util.data.CarProvider;

/**
 * The ComboBox bean.
 */
public class ComboBox implements ImmediateComponentBean, RequiredComponentBean {

	/** The items. */
	private List<SelectItem> items;

	/** The value. */
	private String value;

	/** The immediate. */
	private boolean immediate = false;

	/** The required. */
	private boolean required = false;

	/**
	 * Instantiates a new combo box.
	 */
	public ComboBox() {
		CarProvider cp = new CarProvider();
		List<Car> listCars = cp.getAllCars();
		items = new ArrayList<SelectItem>();
		for (Car car : listCars) {
			items.add(new SelectItem(car.toString()));
		}
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 * 
	 * @param value
	 *            the new value
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * Gets the items.
	 * 
	 * @return the items
	 */
	public List<SelectItem> getItems() {
		return items;
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
