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

class LinkTag extends HtmlTag {
    
    private static final long serialVersionUID = -6944275891941825069L;
    private Boolean haveHtml = false;
    
    public LinkTag() {
        super(A);
    }
    
    @Override
    public void appendBody(HtmlTag tag) {
        super.appendBody(tag);
        
        haveHtml = true;
    }
    
    @Override
    public String print() {
        if (haveHtml) {
            return printPlain();
        }
        
        return super.print();
    }
    
    @Override
    public String printStart() {
        return "[";
    }

    @Override
    public String printEnd() {
        String s = getAttribute("href");
        if (s == null) {
            s = "/";
        }
        return "=>"+ s + ']';
    }

    @Override
    public String printStartSuffix() {
        return "";
    }
}
