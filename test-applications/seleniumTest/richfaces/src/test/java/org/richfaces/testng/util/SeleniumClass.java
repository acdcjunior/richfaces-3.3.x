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
package org.richfaces.testng.util;

import java.util.ArrayList;

public class SeleniumClass {

	private String name;
	private ArrayList<String> methods;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<String> getMethods() {
		return methods;
	}

	public void setMethods(ArrayList<String> methods) {
		this.methods = methods;
	}

	public SeleniumClass() {
		this.methods = new ArrayList<String>();
	}

	public SeleniumClass(String name) {
		this.name = name;
		this.methods = new ArrayList<String>();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof SeleniumClass))
			return false;
		return ((SeleniumClass) obj).getName().equals(this.name);
	}
}
