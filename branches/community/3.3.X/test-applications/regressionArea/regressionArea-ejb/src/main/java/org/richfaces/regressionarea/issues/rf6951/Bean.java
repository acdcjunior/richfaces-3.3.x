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
package org.richfaces.regressionarea.issues.rf6951;

import java.util.Arrays;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

/**
 * This test case for: <a href="https://jira.jboss.org/jira/browse/RF-6267">RF-6951 - 
 *  List shuttle causes "invalid value expression" error.</a>
 * 
 * @author Mikhail Vitenkov
 * @since 3.3.2
 */
@Name("rf6951")
@Scope(ScopeType.SESSION)
public class Bean {

	private String[] availableLine = { "Line 1", "Line 2", "Line 3" };
	private String[] selectedLine = {};
	private String available;
	private String selected;
	
	public Bean(){
		
	}

	public String[] getAvailableLine() {
		return availableLine;
	}

	public void setAvailableLine(String[] availableLine) {
		this.availableLine = availableLine;
		available = Arrays.toString(availableLine);
	}

	public String[] getSelectedLine() {
		return selectedLine;
	}

	public void setSelectedLine(String[] selectedLine) {
		this.selectedLine = selectedLine;
		selected = Arrays.toString(selectedLine);
	}

	public String getAvailable() {
		return available;
	}

	public void setAvailable(String available) {
		this.available = available;
	}

	public String getSelected() {
		return selected;
	}

	public void setSelected(String selected) {
		this.selected = selected;
	}
}
