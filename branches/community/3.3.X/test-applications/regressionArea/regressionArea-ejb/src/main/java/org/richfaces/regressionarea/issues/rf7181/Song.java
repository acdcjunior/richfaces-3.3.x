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

import java.util.ArrayList;
import java.util.Iterator;

import org.richfaces.model.TreeNode;

/**
 * @author Mikhail Vitenkov
 * @since 3.3.2
 */
public class Song implements TreeNode {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7155620465939481885L;
	private long id;
	private String title;
	private String genre;
	private int trackNumber;
	private Album album;

	public Song(long id,String title,int num) {
		this.id=id;
		this.title=title;
		this.genre=genre;
		this.trackNumber=num;
	}	
	
	public Song(long id) {
		this.id = id;
	}
	
	public void addChild(Object identifier, TreeNode child) {
		throw new UnsupportedOperationException("Songs do not have children");
	}

	public TreeNode getChild(Object id) {
		throw new UnsupportedOperationException("Songs do not have children");
	}

	public Iterator getChildren() {
		// TODO: Fix me!
		return new ArrayList().iterator(); // work around limitation for TreeNode
	}

	public Object getData() {
		return this;
	}

	public TreeNode getParent() {
		return album;
	}

	public boolean isLeaf() {
		return true;
	}

	public void removeChild(Object id) {
		throw new UnsupportedOperationException("Songs do not have children");
	}

	public void setData(Object data) {
	}

	public void setParent(TreeNode parent) {
		this.album = (Album) parent;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getTrackNumber() {
		return trackNumber;
	}

	public void setTrackNumber(int trackNumber) {
		this.trackNumber = trackNumber;
	}

	public long getId() {
		return id;
	}
	public String getType() {
		return "song";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + trackNumber;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Song other = (Song) obj;
		if (id != other.id)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (trackNumber != other.trackNumber)
			return false;
		return true;
	}

}
