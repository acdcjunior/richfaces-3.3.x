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

class FormattingTag extends HtmlTag {
    private static final long serialVersionUID = 2688496380368279023L;

    private String seamTag;
    private Boolean formating;
    
    public FormattingTag(String tagName, String seamTag) {
        super(tagName);
        this.seamTag = seamTag;
    }

    @Override
    public void appendBody(HtmlTag tag) {
        super.appendBody(tag);
        
        if (!(tag instanceof FormattingTag)) {
            formating = false;
        }
    }

    @Override
    public String print() {
        if (isFormating()) {
            return super.print();
        }
        
        return printPlain();
    }
    
    @Override
    public String printEnd() {
        return seamTag;
    }

    @Override
    public String printStart() {
        return seamTag;
    }

    public boolean isFormating() {
        if (formating != null) {
            return formating;
        }

        for (Object child : body) {
            if (child instanceof FormattingTag) {
                FormattingTag formattingChild = (FormattingTag) child;
                if (!formattingChild.isFormating()) {
                    formating = false;
                    return false;
                }
            } else if (child instanceof HtmlTag) {
                throw new IllegalStateException(
                        "It is imposible, in this case we must have formating = false");
            }
        }
        
        formating = true;
        return true;
    }

    public String getSeamTag() {
        return seamTag;
    }

    public void setSeamTag(String seamTag) {
        this.seamTag = seamTag;
    }
}
