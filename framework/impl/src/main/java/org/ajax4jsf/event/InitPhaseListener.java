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

package org.ajax4jsf.event;

import java.util.Iterator;

import javax.faces.FactoryFinder;
import javax.faces.application.Application;
import javax.faces.application.StateManager;
import javax.faces.application.ViewHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.faces.lifecycle.Lifecycle;
import javax.faces.lifecycle.LifecycleFactory;

import org.ajax4jsf.application.AjaxStateManager;
import org.ajax4jsf.application.AjaxViewHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * One time called listener, for initialize framework at first request.
 * @author shura
 *
 */
public class InitPhaseListener implements PhaseListener {
	
	private volatile boolean removed= false;
	private volatile boolean initialized = false;
	
	private static final Log log = LogFactory.getLog(InitPhaseListener.class);

	/* (non-Javadoc)
	 * @see javax.faces.event.PhaseListener#afterPhase(javax.faces.event.PhaseEvent)
	 */
	public synchronized void afterPhase(PhaseEvent event) {
        if (!removed) {
        	if(log.isDebugEnabled()){
        		log.debug("Remove init phase listener from factories");
        	}
            LifecycleFactory factory = (LifecycleFactory)
                  FactoryFinder.getFactory(FactoryFinder.LIFECYCLE_FACTORY);
            for(Iterator<String> iter = factory.getLifecycleIds(); iter.hasNext(); ) {
                Lifecycle lifecycle = factory.getLifecycle((String) iter.next());
                lifecycle.removePhaseListener(this);
            }
            removed = true;
        }
	}

	/* (non-Javadoc)
	 * @see javax.faces.event.PhaseListener#beforePhase(javax.faces.event.PhaseEvent)
	 */
	public synchronized void beforePhase(PhaseEvent event) {
		if(!initialized){
			if(log.isDebugEnabled()){
				log.debug("Perform additional framework initialization on first request");
			}
			FacesContext facesContext = event.getFacesContext();
			Application application = facesContext.getApplication();
			ViewHandler viewHandler = application.getViewHandler();
			
			if (!(viewHandler instanceof AjaxViewHandler)) {
				if(log.isDebugEnabled()){
					log.debug("Set AjaxViewHandler on top of chain");
				}

				AjaxViewHandler ajaxViewHandler = new AjaxViewHandler(viewHandler);
				ajaxViewHandler.fillChain(facesContext);
				
				application.setViewHandler(ajaxViewHandler);
			} else {
				((AjaxViewHandler) viewHandler).fillChain(facesContext);
			}
			initialized = true;
		}
	}

	/* (non-Javadoc)
	 * @see javax.faces.event.PhaseListener#getPhaseId()
	 */
	public PhaseId getPhaseId() {
		// TODO Auto-generated method stub
		return PhaseId.ANY_PHASE;
	}

}
