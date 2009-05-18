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

package org.ajax4jsf.javascript;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author shura (latest modification by $Author: alexsmirnov $)
 * @version $Revision: 1.1.2.2 $ $Date: 2007/01/24 13:22:31 $
 *
 */
public class JSFunctionDefinition extends ScriptStringBase implements ScriptString
{


    
    private List<Object> parameters = new ArrayList<Object>();
    
    private StringBuffer body = new StringBuffer();
    
    private String name ;

    /**
     * Construct {@link JSFunctionDefinition} with arbitrary list of params
     * @param params
     */
	public JSFunctionDefinition(Object ... params) {
		parameters.addAll(Arrays.asList(params));
	}
	
	public void addParameter(Object param) {
		parameters.add(param);
	}
	
    public JSFunctionDefinition addToBody(Object body)
    {
        this.body.append(body);
        return this;
    }
    /* (non-Javadoc)
     * @see org.ajax4jsf.components.renderkit.scriptutils.ScriptString#appendScript(java.lang.StringBuffer)
     */
    public void appendScript(StringBuffer functionString)
    {
    	functionString.append("function");
    	if(null != name){
    		functionString.append(" ").append(name);
    	}
    	functionString.append("(");
        boolean first = true;
        for (Iterator<Object> param = parameters.iterator(); param.hasNext();)
        {
            Object element =  param.next();
            if(!first){
                functionString.append(',');
            }
            functionString.append(element.toString());
            first = false;
        }
        functionString.append("){").append(body).append("}");
    }

	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
