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

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Map;

/**
 * @user: akolonitsky
 * Date: Mar 24, 2009
 * 
 * TODO Rename to HtmlTag
 */
public class HtmlTag implements Cloneable, Serializable{

    private static final long serialVersionUID = -372761460000118889L;

    public static final String HR = "hr";
    public static final String BR = "br";
    public static final String IMG = "img";
    public static final String AREA = "area";
    public static final String COL = "col";

    public static final String A = "a";
    public static final String H1 = "h1";
    public static final String H2 = "h2";
    public static final String H3 = "h3";
    public static final String H4 = "h4";
    public static final String P = "p";
    public static final String UL = "ul";
    public static final String OL = "ol";
    public static final String LI = "li";
    public static final String PRE = "pre";
    public static final String TT = "tt";
    public static final String DEL = "del";
    public static final String SUP = "sup";
    public static final String Q = "q";
    public static final String I = "i";
    public static final String U = "u";
    public static final String BLOCKQOUTE = "blockqoute";
    
    private String name;
    private Map<String, String> attributes;
    
    protected LinkedList<Object> body;

    private boolean isEmpty = true;

    HtmlTag() {
        cleanBody();
    }

    HtmlTag(String name) {
        this();
        
        setName(name);
    }

    HtmlTag(String name, Map<String, String> attributes) {
        this();
        
        setName(name);
        setAttributes(attributes);
    }

    protected boolean isBreakLineChild(Object child) {
        if (child == null || !(child instanceof String)) {
            return false;
        }
        
        final String str = (String) child;
        return str.endsWith("\r") || str.endsWith("\n");
    }
    
    protected String printPlainStart(){
        final StringBuilder builder = new StringBuilder();
        builder.append('<').append(getName());

        if (!attributes.isEmpty()) {
            builder.append(' ').append(printAttributes());
        }
        builder.append('>');

        return builder.toString();
    }

    protected String printStartSuffix(){
        return ">";
    }

    protected String printStart(){
        return printPlainStart();
    }

    protected String printAttributes() {
        if (attributes == null) {
            return "";
        }
        
        final StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, String> pair : attributes.entrySet()) {
            builder.append(pair.getKey())
                .append("=\"").append(prepareValue(pair.getValue())).append("\" ");
        }
        return builder.substring(0, builder.length() - 1);
    }

    protected String prepareValue(String value) {
        return value.replace('"', '^');
    }

    protected void appendChildTag(StringBuilder res, HtmlTag child) {
        res.append(child);
    }
    
    protected String printBody() {
        final StringBuilder res = new StringBuilder();
        for (Object child : body) {
            if (child instanceof HtmlTag) {
                appendChildTag(res, (HtmlTag)child);    
            } else {
                res.append(child);
            }
        }
        return res.toString();
    }
    
    public void appendBody(HtmlTag tag) {
        body.add(tag);
    }
    
    public void appendBody(String str) {
        body.add(str);
    }
    
    public void cleanBody() {
        body = new LinkedList<Object>();
    } 
    
    public boolean isBodyEmpty() {
        for (Object child : body) {
            if (child != null && !"".equals(child.toString())) {
                return false;
            }
        }
        
        return true;
    }
    
    protected String printPlainEnd(){
        if (isEmpty) {
//            return "/>";
        }
        
        final StringBuilder builder = new StringBuilder(getName().length() + 3);
        builder.append("</").append(getName()).append('>');

        return builder.toString();
    }

    protected String printEnd(){
        return printPlainEnd();
    }
    
    public boolean isLineTag() {
        return this instanceof LineTag;
    }

    public boolean isQuote() {
        return Q.equals(name);
    }
    
    public boolean isLink() {
        return A.equals(name);
    }

    public boolean isHeader() {
        return H1.equals(name) || H2.equals(name) || H3.equals(name) || H4.equals(name);
    }

    public boolean isParagraph() {
        return P.equals(name);
    }

    public boolean isList() {
        return UL.equals(name) || OL.equals(name);
    }

    public boolean isListItem() {
        return LI.equals(name);
    }

    public boolean isPreFormattedElement() {
        return TT.equals(name);
    }

    public void setEmpty() {
        isEmpty = true;
    }
    
    public void setNotEmpty() {
        isEmpty = false;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.toLowerCase();
    }

    public String getAttribute(String attr) {
        return attributes.get(attr);
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return print();
    }
    
    public String print() {
        return printStart() + printBody() + printEnd();
    }

    public String printPlain() {
        return printPlainStart() + printBody() + printPlainEnd();
    }

    protected HtmlTag cloneTag() {
        HtmlTag clone;
        try {
            clone = (HtmlTag) this.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
        
        clone.name = this.name;
        clone.cleanBody();
        
        return clone;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
}
