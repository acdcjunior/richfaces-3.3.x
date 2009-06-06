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
package org.richfaces.regressionarea.issues.rf7181;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.richfaces.model.TreeNode;

/**
 * @author Mikhail Vitenkov
 * @since 3.3.2
 */
public class Artist implements TreeNode {
	private long id;
	private Map albums = new LinkedHashMap();
	private String name;
	private Library library;
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 6831863694596474846L;

	public Artist(long id) {
		this.id = id;
	}

	public void addAlbum(Album album) {
		addChild(Long.toString(album.getId()), album);
		album.setParent(this);
	}
	
	public void addChild(Object identifier, TreeNode child) {
		albums.put(identifier, child);
	}

	public TreeNode getChild(Object id) {
		return (TreeNode) albums.get(id);
	}

	public Iterator getChildren() {
		return albums.entrySet().iterator();
	}

	public Object getData() {
		return this;
	}

	public TreeNode getParent() {
		return library;
	}

	public boolean isLeaf() {
		return albums.isEmpty();
	}

	public void removeChild(Object id) {
		albums.remove(id);
	}

	public void setData(Object data) {
	}

	public void setParent(TreeNode parent) {
		library = (Library) parent;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Library getLibrary() {
		return library;
	}

	public void setLibrary(Library library) {
		this.library = library;
	}
	public String getType() {
		return "artist";
	}

}
