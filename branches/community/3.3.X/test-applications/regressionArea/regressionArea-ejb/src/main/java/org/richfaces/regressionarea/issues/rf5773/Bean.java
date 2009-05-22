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


package org.richfaces.regressionarea.issues.rf5773;

import java.util.ArrayList;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

/**
 * This test case for: <a href="https://jira.jboss.org/jira/browse/RF-5773">RF-5773 - 
 * Forms not working within DataTable</a>
 * 
 * @author Nick Belaevski
 * @since 3.3.2
 */

@Name("rf5773")
@Scope(ScopeType.SESSION)
public class Bean {

	private List<Item> items;

	public Bean() {
		items = new ArrayList<Item>();
		for (int i = 0; i < 3; i++) {
			items.add(new Item("Item " + i));
		}
	}
	
	public List<Item> getItems() {
		return items;
	}

}
