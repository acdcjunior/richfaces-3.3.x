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
public class Album implements TreeNode {
	
	private static final long serialVersionUID = 6514596192023597908L;
	private long id;
	private Map songs = new LinkedHashMap();
	private String title;
	private Integer year;
	private Artist artist;

	public Album(long id) {
		this.id = id;
	}
	
	public void addSong(Song song) {
		addChild(Long.toString(song.getId()), song);
		song.setParent(this);
	}
	public void addChild(Object identifier, TreeNode child) {
		songs.put(identifier, child);
	}

	public TreeNode getChild(Object id) {
		return (TreeNode) songs.get(id);
	}

	public Iterator getChildren() {
		return songs.entrySet().iterator();
	}

	public Object getData() {
		return this;
	}

	public TreeNode getParent() {
		return artist;
	}

	public boolean isLeaf() {
		return songs.isEmpty();
	}

	public void removeChild(Object id) {
		songs.remove(id);
	}

	public void setData(Object data) {
	}

	public void setParent(TreeNode parent) {
		this.artist = (Artist) parent;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public long getId() {
		return id;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}
	
	public String getType() {
		return "album";
	}

}
