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

package org.ajax4jsf.builder.mojo;

/**
 * @author shura
 * 
 */
public class Library {

	public static final String JSF10 = "1.0";

	public static final String JSF11 = "1.1";

	public static final String JSF12 = "1.2";

	private String _prefix;

	private String _description;

	private String _jsfVersion;

	private Taglib _taglib;

	private Renderkit[] _renderkits;

	/**
	 * @parameter
	 */
	private Taglib[] taglibs;

	/**
	 * @return the prefix
	 */
	public String getPrefix() {
		return this._prefix;
	}

	/**
	 * @param prefix
	 *            the prefix to set
	 */
	public void setPrefix(String prefix) {
		this._prefix = prefix;
	}

	/**
	 * @return the renderkits
	 */
	public Renderkit[] getRenderkits() {
		return this._renderkits;
	}

	/**
	 * @param renderkits
	 *            the renderkits to set
	 */
	public void setRenderkits(Renderkit[] renderkits) {
		this._renderkits = renderkits;
	}

	/**
	 * @return the taglib
	 */
	public Taglib getTaglib() {
		return this._taglib;
	}

	/**
	 * @param taglib
	 *            the taglib to set
	 */
	public void setTaglib(Taglib taglib) {
		this._taglib = taglib;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return this._description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this._description = description;
	}

	/**
	 * @return the jsfVersion
	 */
	public String getJsfVersion() {
		return this._jsfVersion;
	}

	/**
	 * @param jsfVersion
	 *            the jsfVersion to set
	 */
	public void setJsfVersion(String jsfVersion) {
		if (JSF10.equals(jsfVersion) || JSF11.equals(jsfVersion)
				|| JSF12.equals(jsfVersion)) {
			this._jsfVersion = jsfVersion;

		} else {
			throw new IllegalArgumentException(
					"Supported JSF versions is  1.0,1.1 and 1.2 only. ");
		}
	}

	/**
	 * @return the taglibs
	 */
	public Taglib[] getTaglibs() {
		return taglibs;
	}

	/**
	 * @param taglibs the taglibs to set
	 */
	public void setTaglibs(Taglib[] taglibs) {
		this.taglibs = taglibs;
	}

	@Override
	public String toString() {		
		return "JSF library "+getPrefix()+", desc: "+getDescription()+(null!=getTaglibs()?", libs: "+getTaglibs():"");
	}
}
