/**
 * License Agreement.
 *
 *  JBoss RichFaces - Ajax4jsf Component Library
 *
 * Copyright (C) 2007  Exadel, Inc.
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

package org.richfaces.component.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import org.ajax4jsf.renderkit.RendererUtils;
import org.ajax4jsf.util.HtmlDimensions;

/**
 * @author Nick Belaevski - nbelaevski@exadel.com created 09.02.2007
 * 
 */
public class HtmlUtil {
	
    private static final String ORG_RICHFACES = "org.richfaces.";

    private static final String ORG_AJAX4JSF = "org.ajax4jsf.";
    
    public static String qualifySize(String sizeDeclaration) {
		String trimmedValue = sizeDeclaration.trim();
		if (trimmedValue.length() != 0) {
			char lastChar = trimmedValue.charAt(trimmedValue.length() - 1);
			if (Character.isDigit(lastChar)) {
				return sizeDeclaration + "px";
			}
		}

		return sizeDeclaration;
	}

	public static String addToSize(String declaration, String delta) {
		Double doubleDelta = HtmlDimensions.decode(delta);
		Double decoded = HtmlDimensions.decode(declaration);

		return HtmlDimensions.formatPx(new Double(decoded.doubleValue()
				+ doubleDelta.doubleValue()));
	}

	public static final Pattern idSelectorPattern = Pattern
			.compile("#((?:-[A-Za-z_-]|[A-Za-z_]|\\\\[^A-F0-9U]|\\\\[A-F0-9]{1,6}\\s?|\\\\U[0-9A-F]{2}(?:A[1-9A-F]|[B-F][0-9A-F]))(?:\\\\[A-F0-9]{1,6}\\s?|[A-Za-z0-9_-]|\\\\:)*)");

	public static String expandIdSelector(String selector,
			UIComponent component, FacesContext context) {
		Matcher matcher = idSelectorPattern.matcher(selector);
		StringBuffer sb = new StringBuffer();

		while (matcher.find()) {
			// make new id selector here using matcher.group(1)
			String unescaped = matcher.group(1).replaceAll("\\\\:", ":");
			UIComponent target = RendererUtils.getInstance().findComponentFor(context, component, unescaped);

			if (target != null) {
				matcher.appendReplacement(sb, "#"
						+ target.getClientId(context).replaceAll(":",
								"\\\\\\\\:"));
			}
		}
		matcher.appendTail(sb);
		return sb.toString();
	}
	
	public static String idsToIdSelector(String ids) {
		StringBuffer buffer = new StringBuffer();
		if (ids != null) {
			String[] idString = ids.split("\\s*,\\s*");
			
			for(int i = 0; i < idString.length; i++) {
				if (i > 0) {
					buffer.append(",");
				}
				idString[i] = idString[i].replaceAll(":", "\\\\:");
				buffer
					.append("#")
					.append(idString[i]);
			}
		}
		return buffer.toString();
	}
	
	public static boolean shouldWriteId(UIComponent component) {
	    String rendererType = component.getRendererType();

	    String id = component.getId();
	    if (id != null && !id.startsWith(UIViewRoot.UNIQUE_ID_PREFIX)) {
		
		return true;
	    }

	    if (rendererType != null && 
		    (rendererType.startsWith(ORG_AJAX4JSF) || rendererType.startsWith(ORG_RICHFACES))) {
		
		return true;
	    }
	    
	    return false;
	}
	
	private static boolean isEmpty(String s) {
		return s == null || s.length() == 0;
	}

	public static String concatClasses(String... classes) {
		StringBuilder result = new StringBuilder();
		
		for (String className : classes) {
			if (!isEmpty(className)) {
				if (result.length() != 0) {
					result.append(' ');
				}
				
				result.append(className.trim());
			}
		}
		
		return result.toString();
	}

	public static String concatStyles(String... styles) {
		StringBuilder result = new StringBuilder();

		for (String style : styles) {
			if (!isEmpty(style)) {
				if (result.length() != 0) {
					result.append(';');
				}
				
				result.append(style.trim());
			}
		}
		
		return result.toString();
	}
}
