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

package org.richfaces.json;

import java.util.Collection;
import java.util.Map;


class JSONAccessor {
	static boolean putValue(JSONObject object, String key, Object value) {
		try {
			if (value instanceof Boolean) {
				object.put(key, ((Boolean) value).booleanValue());
			} else if (value instanceof Double) {
				object.put(key, ((Double) value).doubleValue());
			} else if (value instanceof Integer) {
				object.put(key, ((Integer) value).intValue());
			} else if (value instanceof Long) {
				object.put(key, ((Long) value).longValue());
			} else if (value instanceof Collection) {
				object.put(key, (Collection) value);
			} else if (value instanceof Map) {
				object.put(key, (Map) value);
			} else {
				object.put(key, value);
			}
		} catch (JSONException e) {
			e.printStackTrace();

			return false;
		}
		
		//no exception - suppose it's added
		return true;
	}

	static Object unwrapValue(Object value) throws JSONException {
		if (value instanceof JSONObject) {
			return new JSONMap((JSONObject) value);
		} else if (value instanceof JSONArray) {
			return new JSONCollection((JSONArray) value);
		}

		return value;
	}
	
	static Object getValue(JSONObject object, String key) throws JSONException {
		return unwrapValue(object.get(key));
	}
}