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

package components.suggestionBox;

import java.util.ArrayList;
import java.util.Iterator;

import util.data.Car;
import util.data.CarProvider;
import components.ImmediateComponentBean;

/**
 * The SuggestionBox bean.
 */
public class SuggestionBox implements ImmediateComponentBean {

	/** The data. */
	private ArrayList<Car> data = null;

	/** The value. */
	private String value = "";

	/** The immediate. */
	private boolean immediate = false;

	/**
	 * Instantiates a new suggestion box.
	 */
	public SuggestionBox() {
		CarProvider cPr = new CarProvider();
		data = cPr.getAllCars();
		data.add(new Car("validator", "", 0));
	}

	/**
	 * Suggestion action.
	 * 
	 * @param obj
	 *            the text which is typed by user
	 * 
	 * @return the array list< car>
	 */
	public ArrayList<Car> suggestionAction(Object obj) {
		String str = (String) obj;
		ArrayList<Car> result;
		result = new ArrayList<Car>();
		Car car;
		for (Iterator<Car> itCar = this.data.iterator(); itCar.hasNext();) {
			car = itCar.next();
			if (null != car) {
				// if car name or car model contains typed text then add car to
				// the result list
				if (car.getMake().toUpperCase().contains(str.toUpperCase())
						|| car.getModel().toUpperCase().contains(
								str.toUpperCase())) {
					result.add(car);
				}
			}
		}
		return result;
	}

	/**
	 * Gets the data.
	 * 
	 * @return the data
	 */
	public ArrayList<Car> getData() {
		return data;
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
}
