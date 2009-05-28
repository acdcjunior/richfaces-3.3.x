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
package org.richfaces.component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.faces.component.UIComponent;
/**
 * @author Andrey Markhel
 * This utility class used in determination levels of errors should be rendered.
 *
 */
public class RichMessageLevelHelper {

	private RichMessageLevelHelper(){
		
	}
	
	public static List<String> getSeverenities(UIComponent msg){
		UIRichMessage richMessage = null;
		UIRichMessages richMessages = null;
		String level = null;
		String minLevel = null;
		if(msg instanceof UIRichMessages){
			richMessages = (UIRichMessages)msg;
			level = richMessages.getLevel();
			minLevel = richMessages.getMinLevel();
		}else if(msg instanceof UIRichMessage){
			richMessage = (UIRichMessage)msg;
			level = richMessage.getLevel();
			minLevel = richMessage.getMinLevel();
		}
		if(level != null && minLevel != null){
			System.err.println("ERROR: To properly working of component you must specify only one attribbute(minLevel or level)");
		}
		if(null == level && null == minLevel){
			level = "ALL";
		}
		List<String> severenities;
		if (null != level) {
			String[] levels = level.split(",");
			severenities = new ArrayList<String>(levels.length);
			for (int i = 0; i < levels.length; i++) {
				String levelName = levels[i].toUpperCase().trim();
				severenities.add(levelName);
			}
		} else if(null != minLevel){
			String min = minLevel;
			MessagesLevelEnum[] levels = MessagesLevelEnum.getHigherLevels(min.toUpperCase().trim());
			severenities = new ArrayList<String>(levels.length);
			for (int i = 0; i < levels.length; i++) {
				String levelName = levels[i].name().trim();
				severenities.add(levelName);
			}
		}
		else {
			severenities = Collections.emptyList();
		}
		return severenities;
	}
}
