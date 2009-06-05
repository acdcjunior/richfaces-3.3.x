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


/**
 * @author Alex.Kolonitsky
 * @since 3.3.2.CR1
 * */
public class ParagraphTag extends LineTag {

    private static final long serialVersionUID = 1720000557944774249L;

    protected ParagraphTag() {
        super(P);
    }
    
    private boolean isFirstChars = true;
    
    @Override
    public void appendBody(String str) {
        if (isFirstChars) {
            String substr = cutLeadBreaklines(str);  
            if (substr != null) {
                body.add(substr);
                isFirstChars = false;
            } else {
                return;
            }
        } else {
            body.add(str);
        }
    }
    
    private String cutLeadBreaklines(String str) {
        char text[] = str.toCharArray();
        int firstNotNewLineChar = 0;
        while (firstNotNewLineChar < text.length && (text[firstNotNewLineChar] == '\r' || text[firstNotNewLineChar] == '\n')) {
            firstNotNewLineChar++;
        }
        
        return firstNotNewLineChar < text.length ? str.substring(firstNotNewLineChar) : null;
    }
    
    @Override
    public String printStart() {
        return "";
    }

    @Override
    public String printEnd() {
        return "\n\n"; 
    }

    @Override
    public String printStartSuffix() {
        return "";
    }
}
