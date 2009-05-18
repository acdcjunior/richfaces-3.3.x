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

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import org.ajax4jsf.Messages;

/**
 * @author shura (latest modification by $Author: alexsmirnov $)
 * @version $Revision: 1.1.2.1 $ $Date: 2007/01/09 18:59:16 $
 *
 */
public class HtmlColor {
	private static Map<String, Color> colorNames;
	
	static {
		// color names.
		colorNames = new HashMap<String, Color>();
	    colorNames.put("black", new Color(0x000000));
		colorNames.put("green", new Color(0x008000));
		colorNames.put("silver", new Color(0xC0C0C0));
		colorNames.put("lime", new Color(0x00FF00));
		colorNames.put("gray", new Color(0x808080));
		colorNames.put("olive", new Color(0x808000));
		colorNames.put("white", new Color(0xFFFFFF));
		colorNames.put("yellow", new Color(0xFFFF00));
		colorNames.put("maroon", new Color(0x800000));
		colorNames.put("navy", new Color(0x000080));
		colorNames.put("red", new Color(0xFF0000));
		colorNames.put("blue", new Color(0x0000FF));
		colorNames.put("purple", new Color(0x800080));
		colorNames.put("teal", new Color(0x008080));
		colorNames.put("fuchsia", new Color(0xFF00FF));
		colorNames.put("aqua", new Color(0x00FFFF));
	}

	/**
	 * Decode HTML-attribute style of color to {@link Color}
	 * @param color - color name or #RRGGBB string
	 * @return - color for this value.
	 */
	public static Color decode(String color){
		if(null == color) {
			throw new IllegalArgumentException(Messages.getMessage(Messages.NULL_COLOR_PARAMETER_ERROR));
		}
		Color c = (Color) colorNames.get(color.trim().toLowerCase());
		if (null == c) {
			try {
				c = Color.decode(color.trim());
			} catch (NumberFormatException e) {
				throw new IllegalArgumentException(Messages.getMessage(Messages.DECODE_COLOR_PARAMETER_ERROR, color, e.getMessage()));
			}
		}
		return c;
	}
	
	public static Integer integerValue(String color){
		return new Integer(decode(color).getRGB());
	}
	
	public static String encodeRGB(Color color){
		if(null == color) {
			throw new IllegalArgumentException(Messages.getMessage(Messages.NULL_COLOR_PARAMETER_ERROR_2));
		}
		return "#" + Integer.toHexString(color.getRGB()).substring(2).toUpperCase();
	}
	
}
