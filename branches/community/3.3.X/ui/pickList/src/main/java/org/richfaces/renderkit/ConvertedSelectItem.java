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
package org.richfaces.renderkit;

import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;

import org.richfaces.component.UIPickList;
import org.richfaces.json.XML;

public class ConvertedSelectItem {
    private SelectItem item;
    
    private Object itemValue;
    
    private String suffix;
    
    private String convertedValue;

    private boolean isSelected = false;
    
    public static ConvertedSelectItem get(FacesContext context, UIPickList pickList, SelectItem item, String suffix) {
        Converter converter = pickList.getConverter();
        Object itemValue = item.getValue();
        
        String convertedItem;
        if (converter == null) {
            convertedItem = itemValue == null ? "" : itemValue.toString();
        } else {
            convertedItem = converter.getAsString(context, pickList, itemValue);
        }
        
        return new ConvertedSelectItem(item, itemValue, convertedItem, suffix);
    }
    
    private ConvertedSelectItem(SelectItem item, Object itemValue, String convertedValue, String suffix) {
        super();
        this.item = item;
        this.itemValue = itemValue;
        this.convertedValue = convertedValue;
        this.suffix = suffix;
    }

    public String getSuffix() {
        return this.suffix;
    }
    
    public SelectItem getItem() {
        return item;
    }

    public Object getValue() {
        return itemValue;
    }

    public String getConvertedValue() {
        return convertedValue;
    }
    
    public void setSelected() {
        this.isSelected = true;
    }
    
    public boolean isSelected() {
        return this.isSelected;
    }
}
