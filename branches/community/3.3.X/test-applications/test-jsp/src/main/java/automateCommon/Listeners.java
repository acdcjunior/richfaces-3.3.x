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

package automateCommon;

import javax.faces.event.PhaseId;
import javax.faces.event.ValueChangeEvent;

import util.parser.Attribute;
import util.parser.Status;
import util.phaseTracker.PhaseTracker;

/**
 * The Listeners bean provides methods for "valueChangeListener" attribute
 * testing.
 */
public class Listeners {

	/**
	 * The statusValueChangeListener indicates whether valueChangeListener is
	 * called or not.
	 */
	private boolean statusValueChangeListener = false;

	/**
	 * The phaseValueChangeListener keeps the JSF phase on which
	 * valueChangeListener is triggered.
	 */
	private PhaseId phaseValueChangeListener = PhaseId.ANY_PHASE;

	/**
	 * Resets test variables.
	 */
	public void reset() {
		statusValueChangeListener = false;
		phaseValueChangeListener = PhaseId.ANY_PHASE;
	}

	/**
	 * Value change listener.
	 * 
	 * @param e
	 *            the parameter of the value change listener
	 */
	public void valueChangeListener(ValueChangeEvent e) {
		System.out.println("++>> valueChangeListener was called");
		statusValueChangeListener = true;
		phaseValueChangeListener = PhaseTracker.currentPhase;
	}

	/**
	 * Value change listener check.
	 * 
	 * @param attr
	 *            the tested attribute
	 * @param immediate
	 *            the immediate attribute value of the current component
	 */
	public void valueChangeListenerCheck(Attribute attr, boolean immediate) {
		// validator should be called...
		if (statusValueChangeListener) {
			// ... on the 3th phase if component is not immediate...
			if ((phaseValueChangeListener.equals(PhaseId.PROCESS_VALIDATIONS))
					&& (!immediate)
					// ... or on the 2th phase if component is immediate
					|| (phaseValueChangeListener
							.equals(PhaseId.APPLY_REQUEST_VALUES))
					&& (immediate)) {
				attr.setStatus(Status.PASSED);
			} else {
				attr.setStatus(Status.FAILED);
			}
		} else {
			attr.setStatus(Status.FAILED);
		}
	}
}
