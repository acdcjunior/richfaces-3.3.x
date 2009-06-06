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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.richfaces.model.TreeNode;

/**
 * @author Mikhail Vitenkov
 * @since 3.3.2
 */
@Name("rf7181")
@Scope(ScopeType.SESSION)
public class Library implements TreeNode {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3530085227471752526L;
	private Map artists = null;
	private Object state1;
	private Object state2;
	private List<Song> songsList;
	private Set<Song> selectedSongsSet = new HashSet<Song>();
	private List<Song> selectedSongsList = new ArrayList<Song>();
	private String input1;
	private String input2;
	
	public Library() {
		songsList = new ArrayList<Song>();
		songsList.addAll(getLibraryAsList());
		input1 = "input1";
		input2 = "input2";
	}	
	private Map getArtists() {
		if (this.artists==null) {
			initData();
		}
		return this.artists;
	}
	public void addArtist(Artist artist) {
		addChild(Long.toString(artist.getId()), artist);
	}
	
	public void addChild(Object identifier, TreeNode child) {
		getArtists().put(identifier, child);
		child.setParent(this);
	}

	public TreeNode getChild(Object id) {
		return (TreeNode) getArtists().get(id);
	}

	public Iterator getChildren() {
		return getArtists().entrySet().iterator();
	}

	public Object getData() {
		return this;
	}

	public TreeNode getParent() {
		return null;
	}

	public boolean isLeaf() {
		return getArtists().isEmpty();
	}

	public void removeChild(Object id) {
		getArtists().remove(id);
	}

	public void setData(Object data) {
	}

	public void setParent(TreeNode parent) {
	}

	public String getType() {
		return "library";
	}
	
	
	private long nextId = 0;
	private long getNextId() {
		return nextId++;
	}
	private Map albumCache = new HashMap();
	private Map artistCache = new HashMap();
	private Artist getArtistByName(String name, Library library) {
		Artist artist = (Artist)artistCache.get(name);
		if (artist==null) {
			artist = new Artist(getNextId());
			artist.setName(name);
			artistCache.put(name, artist);
			library.addArtist(artist);
		}
		return artist;
	}
	private Album getAlbumByTitle(String title, Artist artist) {
		Album album = (Album)albumCache.get(title);
		if (album==null) {
			album = new Album(getNextId());
			album.setTitle(title);
			albumCache.put(title, album);
			artist.addAlbum(album);
		}
		return album;
	}
	
	private void initData() {
		artists = new HashMap();
		InputStream is = this.getClass().getClassLoader().getResourceAsStream("data.txt");
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		byte[] rb = new byte[1024];
		int read;
		try {
			do {
				read = is.read(rb);
				if (read>0) {
					os.write(rb, 0, read);
				}
			} while (read>0);
			String buf = os.toString();
			StringTokenizer toc1 = new StringTokenizer(buf,"\n");
			while (toc1.hasMoreTokens()) {
				String str = toc1.nextToken();
				StringTokenizer toc2 = new StringTokenizer(str, "\t");
				String songTitle = toc2.nextToken();
				String artistName = toc2.nextToken();
				String albumTitle = toc2.nextToken();
				toc2.nextToken();
				toc2.nextToken();
				String albumYear = toc2.nextToken();
				Artist artist = getArtistByName(artistName,this);
				Album album = getAlbumByTitle(albumTitle, artist);
				album.setYear(new Integer(albumYear));
				Song song = new Song(getNextId());
				song.setTitle(songTitle);
				album.addSong(song);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	public Object getState1() {
		return state1;
	}
	public void setState1(Object state1) {
		this.state1 = state1;
	}
	public Object getState2() {
		return state2;
	}
	public void setState2(Object state2) {
		this.state2 = state2;
	}
	
	public void walk(TreeNode node, List<TreeNode> appendTo, Class<? extends TreeNode> type) {
		if (type.isInstance(node)){
			appendTo.add(node);
		}
		Iterator<Map.Entry<Object, TreeNode>> iterator = node.getChildren();
		while(iterator.hasNext()) {
			walk(iterator.next().getValue(), appendTo, type);
		}
		
	}
	
	public ArrayList getLibraryAsList(){
		ArrayList appendTo = new ArrayList();
		walk(this, appendTo, Song.class);
		return appendTo;
	}
	public List<Song> getSongsList() {
		return songsList;
	}
	public void setSongsList(List<Song> songsList) {
		this.songsList = songsList;
	}
	public void takeSelection() {
		selectedSongsList.clear();
		selectedSongsList.addAll(selectedSongsSet);
	}
	public Set<Song> getSelectedSongsSet() {
		return selectedSongsSet;
	}
	public void setSelectedSongsSet(Set<Song> selectedSongsSet) {
		this.selectedSongsSet = selectedSongsSet;
	}
	public List<Song> getSelectedSongsList() {
		return selectedSongsList;
	}
	public void setSelectedSongsList(List<Song> selectedSongsList) {
		this.selectedSongsList = selectedSongsList;
	}
	public String getInput1() {
		return input1;
	}
	public void setInput1(String input1) {
		this.input1 = input1;
	}
	public String getInput2() {
		return input2;
	}
	public void setInput2(String input2) {
		this.input2 = input2;
	}

}
