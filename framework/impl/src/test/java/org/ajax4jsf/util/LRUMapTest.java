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

import java.util.Iterator;
import java.util.Map.Entry;

import junit.framework.TestCase;

/**
 * @author asmirnov
 *
 */
public class LRUMapTest extends TestCase {

	/**
	 * @param name
	 */
	public LRUMapTest(String name) {
		super(name);
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Test method for {@link org.ajax4jsf.util.LRUMap#LRUMap(int)}.
	 */
	public void testLRUMap() {
		LRUMap map = new LRUMap(5);
		for (int i = 0; i < 10; i++) {
			map.put(new Integer(i), "Val"+(new Integer(i)));
		}
		assertEquals(map.size(), 5);
	}

	/**
	 * Test method for {@link org.ajax4jsf.util.LRUMap#removeEldestEntry(java.util.Map.Entry)}.
	 */
	public void testRemoveEldestEntryEntry() {
		
		LRUMap map = new LRUMap(5){
			protected boolean removeEldestEntry(Entry arg0) {
				boolean eldestEntry = super.removeEldestEntry(arg0);
				assertTrue(eldestEntry ^ size()<=5);
				return false;
			}
		};
		for (int i = 0; i < 10; i++) {
			map.put(new Integer(i), "Val"+(new Integer(i)));
		}
	}

	/**
	 * Test method for {@link java.util.HashMap#put(K, V)}.
	 */
	public void testPut() {
		LRUMap map = new LRUMap(5);
		for (int i = 0; i < 10; i++) {
			map.put(new Integer(i), "Val"+(new Integer(i)));
		}
		assertEquals(map.size(), 5);
		Iterator iterator = map.values().iterator();
		for (int i = 5; i < 10; i++) {
			assertTrue(iterator.hasNext());
			assertEquals("Val"+(new Integer(i)), iterator.next());
		}
		assertFalse(iterator.hasNext());
	}

	public void testGetMostRecent() throws Exception {
		LRUMap map = new LRUMap(5);
		for (int i = 0; i < 10; i++) {
			String last = "Val"+(new Integer(i));
			map.put(new Integer(i), last);
			assertSame(last, map.getMostRecent());
		}
		assertEquals(map.size(), 5);
		for (int i = 5; i < 10; i++) {
			Object lastGet = map.get(new Integer(i));
			assertSame(lastGet, map.getMostRecent());
		}
		
	}
}
