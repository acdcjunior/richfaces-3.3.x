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
package org.richfaces.validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.validator.Length;
import org.hibernate.validator.Max;
import org.hibernate.validator.Min;
import org.hibernate.validator.NotEmpty;
import org.hibernate.validator.NotNull;

/**
 * @author asmirnov
 *
 */
public class ValidableBean implements Cloneable {
	
	@Min(2)
	@Max(5)
	private int integerProperty;
	
	private String text;
	
	private Object foo;
	
	@NotEmpty
	private List<String> list = new ArrayList<String>();
	
	private String[] array = new String[1];
	
	private Map<String, String> map = new HashMap<String, String>();

	/**
	 * @return the list
	 */
	public List<String> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<String> list) {
		this.list = list;
	}

	/**
	 * @return the array
	 */
	@NotEmpty
	@Length(min=2,max=5)
	public String[] getArray() {
		return array;
	}

	/**
	 * @param array the array to set
	 */
	public void setArray(String[] array) {
		this.array = array;
	}

	/**
	 * @return the map
	 */
	@Length(min=2,max=5)
	public Map<String, String> getMap() {
		return map;
	}

	/**
	 * @param map the map to set
	 */
	public void setMap(Map<String, String> map) {
		this.map = map;
	}

	/**
	 * @return the integerProperty
	 */
	public int getIntegerProperty() {
		return integerProperty;
	}

	/**
	 * @param integerProperty the integerProperty to set
	 */
	@Min(2)
	@Max(5)
	public void setIntegerProperty(int integerProperty) {
		this.integerProperty = integerProperty;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	@Length(max=10,min=1,message="text size")
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the foo
	 */
	public Object getFoo() {
		return foo;
	}

	/**
	 * @param foo the foo to set
	 */
	@NotNull
	public void setFoo(Object foo) {
		this.foo = foo;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(array);
		result = prime * result + ((foo == null) ? 0 : foo.hashCode());
		result = prime * result + integerProperty;
		result = prime * result + ((list == null) ? 0 : list.hashCode());
		result = prime * result + ((map == null) ? 0 : map.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ValidableBean))
			return false;
		ValidableBean other = (ValidableBean) obj;
		if (!Arrays.equals(array, other.array))
			return false;
		if (foo == null) {
			if (other.foo != null)
				return false;
		} else if (!foo.equals(other.foo))
			return false;
		if (integerProperty != other.integerProperty)
			return false;
		if (list == null) {
			if (other.list != null)
				return false;
		} else if (!list.equals(other.list))
			return false;
		if (map == null) {
			if (other.map != null)
				return false;
		} else if (!map.equals(other.map))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}

}
