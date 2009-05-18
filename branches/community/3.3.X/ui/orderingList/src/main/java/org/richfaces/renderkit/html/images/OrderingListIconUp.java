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

package org.richfaces.renderkit.html.images;

import static org.richfaces.renderkit.html.images.OrderingListIconConstants.SELECT_LIST_BORDER_COLOR;
import static org.richfaces.renderkit.html.images.OrderingListIconConstants.SELECT_LIST_ICON_COLOR;

import javax.faces.context.FacesContext;

/**
 * @author Siarhej Chalipau
 *
 */
public class OrderingListIconUp extends TriangleIconUp {

	protected Object getDataToStore(FacesContext context, Object data) {
		return super.getDataToStore(context, SELECT_LIST_ICON_COLOR, ICON_COLOR, 
				SELECT_LIST_BORDER_COLOR, BORDER_COLOR);
	}
}
