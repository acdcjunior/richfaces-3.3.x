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
package org.ajax4jsf.util;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

/**
 * Last Recent Used Map cache. See {@link LinkedHashMap} for details.
 * @author asmirnov
 *
 */
public class LRUMap<K,V> extends LinkedHashMap<K,V> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7232885382582796665L;
	private int capacity;
	
	
	/**
	 * Default capacity constructor
	 */
	public LRUMap() {
		this(100);
	}
	
	/**
	 * @param capacity - maximal cache capacity.
	 */
	public LRUMap(int capacity) {
		super(capacity, 1.0f,true);
		this.capacity = capacity;
	}

	
	protected boolean removeEldestEntry(Entry<K,V> entry) {
		// Remove last entry if size exceeded.
		return size()>capacity;
	}

	/**
	 * Get most recent used element 
	 * @return the most Recent value
	 */
	public V getMostRecent() {
		Iterator<V> iterator = values().iterator();
		V mostRecent=null;
		while (iterator.hasNext()) {
			 mostRecent = iterator.next();
			
		}
		return mostRecent;
	}
}
