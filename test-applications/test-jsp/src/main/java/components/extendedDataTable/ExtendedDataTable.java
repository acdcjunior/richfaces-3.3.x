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

package components.extendedDataTable;

import java.util.ArrayList;

import util.data.Car;
import util.data.CarProvider;

/**
 * The ExtendedDataTable bean.
 */
public class ExtendedDataTable {

	/** The value. */
	private ArrayList<Car> value = null;

	/**
	 * Instantiates a new extended data table.
	 */
	public ExtendedDataTable() {
		CarProvider cpr = new CarProvider();
		value = cpr.getAllCars();
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	public ArrayList<Car> getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 * 
	 * @param value
	 *            the new value
	 */
	public void setValue(ArrayList<Car> value) {
		this.value = value;
	}
}
