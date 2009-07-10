/**
 * License Agreement.
 *
 * Ajax4jsf 1.1 - Natural Ajax for Java Server Faces (JSF)
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

package util.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * The Class TLDParser parses richfaces.tld file to provide AttributeList object
 * for selected component.
 */
public class TLDParser {

	/** The component's name. */
	protected String component;

	/** The tld object represents a richfaces.tld file. */
	protected JarEntry tld;

	/** The richfacesUI object represents a richfaces-ui.jar file. */
	protected JarFile richfacesUI;

	/** The all attributes. */
	protected AttributesList allAttributes;

	/**
	 * Instantiates a new tLD parser.
	 * 
	 * @param str
	 *            the component's name
	 */
	public TLDParser(String str) {
		this.component = str;
		allAttributes = new AttributesList();
	}

	/**
	 * Gets the all attributes from richfaces.tld file for selected component.
	 * 
	 * @return the all attributes
	 */
	public AttributesList getAllAttributes() {
		allAttributes.clear();
		tld = getRichfacesUI().getJarEntry("META-INF/richfaces.tld");
		InputStream input = null;
		try {
			input = richfacesUI.getInputStream(tld);

			InputStreamReader isr = new InputStreamReader(input);
			BufferedReader reader = new BufferedReader(isr);
			String line, attr;
			Attribute attribute = new Attribute();
			int position, end;
			boolean insideTag = true;
			StringBuilder sb = new StringBuilder("");
			while (((line = reader.readLine()) != null) && insideTag) {
				if ((position = line.indexOf("<name>")) != -1) {
					end = line.indexOf("</name>");
					attr = line.substring(position + 6, end).trim();
					if (attr.equalsIgnoreCase(component)) {
						while (!(line = reader.readLine()).contains("</tag>")) {
							if (line.contains("<attribute>")) {
								do {
									// find attribute name
									if ((position = line.indexOf("<name>")) != -1) {
										end = line.indexOf("</name>");
										attribute.setName(line.substring(
												position + 6, end).trim());
									}
									// find attribute description
									if ((position = line
											.indexOf("<description>")) != -1) {

										if ((end = line
												.indexOf("</description>")) != -1) {
											attribute
													.setDescription(line
															.substring(
																	position + 13,
																	line
																			.length() - 14));
										} else {
											sb.append(line.substring(
													position + 13,
													line.length()).trim()
													.replaceAll("\t", ""));
											line = reader.readLine();
											while ((end = line
													.indexOf("</description>")) == -1) {
												sb.append(line.substring(0,
														line.length())
														.replaceAll("\t", ""));
												line = reader.readLine();
											}
											sb.append(line.substring(0,
													line.length() - 14).trim()
													.replaceAll("\t", ""));

											attribute.setDescription(sb
													.toString());
											sb.delete(0, sb.length());
										}
									}
									// find attribute type
									if ((position = line.indexOf("<type>")) != -1) {
										end = line.indexOf("</type>");
										attribute.setType(line.substring(
												position + 6, end).trim());
									}
									// find attribute method-signature
									if ((position = line
											.indexOf("<method-signature>")) != -1) {
										end = line
												.indexOf("</method-signature>");
										attribute.setType(line.substring(
												position + 18, end).trim());
									}
								} while (!((line = reader.readLine())
										.contains("</attribute>")));
								// define initial attribute status
								attribute.setStatus(Status.NOT_TESTED);
								allAttributes.add(attribute);
								attribute = new Attribute();
							}
						}
						insideTag = false;
					}
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return allAttributes;
	}

	/**
	 * Gets the richfaces.tld resource.
	 * 
	 * @return the richfaces.tld resource
	 */
	public String getExtPath() {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		String resource = "META-INF/richfaces.tld";
		return loader.getResource(resource).toString();
	}

	/**
	 * Gets the component's name.
	 * 
	 * @return the component's name
	 */
	public String getComponent() {
		return component;
	}

	/**
	 * Sets the component's name.
	 * 
	 * @param component
	 *            the new component's name
	 */
	public void setComponent(String component) {
		this.component = component;
	}

	/**
	 * Gets the richfacesUI object representing a richfaces-ui.jar file.
	 * 
	 * @return the richfaces ui
	 */
	public JarFile getRichfacesUI() {
		String temp = null;
		int position;
		try {
			if ((position = getExtPath().indexOf('!')) != -1) {
				temp = getExtPath().substring("jar:file:\\".length(), position);
			}
			richfacesUI = new JarFile(temp);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return richfacesUI;
	}

	/**
	 * Gets the all possible handlers from richfaces.tld file.
	 * 
	 * @return the all possible handlers
	 */
	public ArrayList<String> getAllHandlers() {
		tld = getRichfacesUI().getJarEntry("META-INF/richfaces.tld");
		InputStream input = null;
		ArrayList<String> handlers = new ArrayList<String>();

		try {
			input = richfacesUI.getInputStream(tld);
			InputStreamReader isr = new InputStreamReader(input);
			BufferedReader reader = new BufferedReader(isr);
			String line, temp;
			int start, end;
			while ((line = reader.readLine()) != null) {
				if ((start = line.indexOf("<name>")) != -1) {
					end = line.indexOf("</name>");
					temp = line.substring(start + 6, end).trim();
					if (temp.startsWith("on") && !handlers.contains(temp)) {
						handlers.add(temp);
					}
				}
			}
			return handlers;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
}
