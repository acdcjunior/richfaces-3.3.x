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


package org.ajax4jsf.builder.generator;

public final class ResourcesConfigGeneratorBeanEntry {
	
	public ResourcesConfigGeneratorBeanEntry(String name, String path, boolean pathResource,
			ResourceType type, boolean derived) {
		super();
		this.name = name;
		this.path = path;
		this.pathResource = pathResource;
		this.type = type;
		this.derived = derived;
	}

	private String name;
	
	private String path;
	
	private boolean pathResource;
	
	private ResourceType type;
	
	private boolean derived;

	public boolean isPathResource() {
		return pathResource;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPath() {
		return path;
	}
	
	public ResourceType getType() {
		return type;
	}
	
	public boolean isDerived() {
		return derived;
	}
}