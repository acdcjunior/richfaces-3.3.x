/**
 * License Agreement.
 *
 * Ajax4jsf 1.1 - Natural Ajax for Java Server Faces (JSF)
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

package util.phaseTracker;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

/**
 * The Class PhaseTracker prints out the phases application passes.
 */
public class PhaseTracker implements PhaseListener {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6533052212003582848L;

	/** The Current JSF phase of the application. */
	public static PhaseId currentPhase = PhaseId.ANY_PHASE;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.faces.event.PhaseListener#afterPhase(javax.faces.event.PhaseEvent)
	 */
	public void afterPhase(PhaseEvent arg0) {
		System.out.println("PhaseTracker.afterPhase()" + arg0.getPhaseId());
		if (arg0.getPhaseId().toString().equals("RENDER_RESPONSE(6)"))
			System.out.println("********** end lifecycle *************");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.faces.event.PhaseListener#beforePhase(javax.faces.event.PhaseEvent)
	 */
	public void beforePhase(PhaseEvent arg0) {
		if (arg0.getPhaseId().toString().equals("RESTORE_VIEW(1)"))
			System.out.println("********** begin lifecycle *************");
		currentPhase = arg0.getPhaseId();
		System.out.println("PhaseTracker.beforePhase()" + arg0.getPhaseId());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.faces.event.PhaseListener#getPhaseId()
	 */
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}
}
