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
package org.richfaces;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

public class rf7472 {
    
    private List<String> value;
    
    public Converter getConvert() {
        return new Converter() {
            public Object getAsObject(FacesContext context,
                    UIComponent component, String newValue)
                    throws ConverterException {

                System.out.println("***> toObject ** " + newValue);
//                new Exception().printStackTrace();

                return newValue;
            }

            public String getAsString(FacesContext context,
                    UIComponent component, Object value)
                    throws ConverterException {

                System.out.println("***> toString ** " + value.toString());
//                new Exception().printStackTrace();
                
                return value.toString();
            }
        };
    }

    public List<String> getValue() {
        return value;
    }

    public void setValue(List<String> value) {
        this.value = value;
    }
}
