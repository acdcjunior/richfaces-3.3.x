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

package org.richfaces.convert.seamtext.tags;

import java.util.LinkedList;

public class RootTag extends HtmlTag{

    private static final long serialVersionUID = -5798300257186134777L;

    RootTag () {
        super("root");
    }
    
    @Override
    protected String printEnd() {
        return "";
    }

    @Override
    protected String printStart() {
        return "";
    }

    @Override
    protected String printBody() {
        body = prepareBody();
        return super.printBody();
    }
    
    /*
     * from seam-text.g
     * 
     * startRule: (newline)* ( (heading (newline)* )? text (heading (newline)* text)* )?
     *          ;
     * 
     * text     : ( (paragraph|preformatted|blockquote|list|html) (newline)* )+
     *          ;
     *     
     * After header alwas must be text.  
     * 
     * */
    private LinkedList<Object> prepareBody() {
        LinkedList<Object> res = new LinkedList<Object>();
        StringBuilder builder = new StringBuilder("<span>");
        
        for (int i = 0; i < body.size(); i++) {
            Object child = body.get(i);
            Object nextChild = i+1 < body.size() ? body.get(i+1) : null;
            
            if (isChildString(child)) {
                builder.append(child);
                if (nextChild == null || !isChildString(nextChild)) {
                    builder.append("</span>");
                    
                    res.add(builder.toString());
                    
                    builder = new StringBuilder("<span>");                    
                } 
            } else if (isChildHeader(child) && (isChildHeader(nextChild) || nextChild == null)) {
                res.add(child);
                res.add(" \n\n"); // empty paragraph
            } else if (child instanceof HtmlTag && ((HtmlTag)child).isQuote()) {
                res.add(child);
                res.add("\n\n"); // empty paragraph
            } else if (child instanceof HtmlTag) {
                res.add(child);
            } else {
                throw new IllegalStateException("Unknow child element '" + child + "'");
            }
        }
        
        return res;
    }
    
    private boolean isChildHeader(Object child) {
        return child instanceof LineTag && ((LineTag)child).isHeader();
    }

    private boolean isChildString(Object child) {
        return child instanceof String;
    }
}
