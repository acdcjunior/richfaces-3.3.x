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

package org.richfaces.event;

import javax.faces.component.UIComponent;
import javax.faces.event.ActionEvent;
import javax.faces.event.FacesListener;

/**
 * @author Wesley Hales
 */
public class DataScrollerEvent extends ActionEvent {

    /**
     * 
     */
    private static final long serialVersionUID = 2657353903701932561L;
    private String  oldScrolVal;
    private String  newScrolVal;
    private int page;

    /**
    * Creates a new ScrollerEvent.
    *
    * @param component         the source of the event
    * @param thisOldScrolVal  the previously showing item identifier
    * @param thisNewScrolVal  the currently showing item identifier
    */
    public DataScrollerEvent(UIComponent component, String thisOldScrolVal, String thisNewScrolVal, int page) {
        super(component);
        oldScrolVal = thisOldScrolVal;
        newScrolVal = thisNewScrolVal;
        this.page = page;
    }

    public String getOldScrolVal() {
        return oldScrolVal;
    }

    public String getNewScrolVal() {
        return newScrolVal;
    }

    /**
     * @since 3.2
     * @return new page or <code>-1</code> if not applicable
     */
    public int getPage() {
	return page;
    }
    
    public boolean isAppropriateListener(FacesListener listener){
        return super.isAppropriateListener(listener) || (listener instanceof DataScrollerListener);
    }

    /**
    * Delivers this event to the SliderListener.
    *
    * @param listener  the slider listener
    */
    public void processListener(FacesListener listener){
        if (listener instanceof DataScrollerListener) {
			DataScrollerListener dataScrollerListener = (DataScrollerListener) listener;
			dataScrollerListener.processScroller(this);
		}
        
        if (super.isAppropriateListener(listener)) {
        	super.processListener(listener);
        }
    }
}
