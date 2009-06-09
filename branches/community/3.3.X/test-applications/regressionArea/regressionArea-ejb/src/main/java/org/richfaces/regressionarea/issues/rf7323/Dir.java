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
package org.richfaces.regressionarea.issues.rf7323;

import java.util.ArrayList;

/**
 * @author Mikhail Vitenkov
 * @since 3.3.2
 */
public class Dir {
	private String name;
	private ArrayList<Package> packages;
	private ArrayList<Dir> dirs;
	
	public Dir(String name, ArrayList<Package> packages) {
		this.name = name;
		this.packages = packages;
	}
	
	public Dir(String name, ArrayList<Package> packages, ArrayList<Dir> dirs) {
		this.name = name;
		this.packages = packages;
		this.dirs = dirs;
	}
	
	public ArrayList<Dir> getDirs() {
		return dirs;
	}

	public void setDirs(ArrayList<Dir> dirs) {
		this.dirs = dirs;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Package> getPackages() {
		return packages;
	}

	public void setPackages(ArrayList<Package> packages) {
		this.packages = packages;
	}
}
