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

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.faces.validator.ValidatorException;

import util.parser.Attribute;
import util.parser.Status;
import util.phaseTracker.PhaseTracker;

/**
 * The Validation bean provides methods for "validator", "validatorMessage",
 * "required", "requiredMessage" and "immediate" attributes testing.
 */
public class Validation {

	/** The validator message which is defined in "validatorMessage" attribute. */
	private String validatorMessage;

	/** The validator message test which is actual displayed on the page. */
	private String validatorMessageTest = "";

	/** The required message which is defined in "requiredMessage" attribute. */
	private String requiredMessage;

	/** The required message test which is actual displayed on the page. */
	private String requiredMessageTest = "";

	/** /** The status validator indicates whether validator is called or not. */
	private boolean statusValidator = false;

	/** The phase validator keeps the JSF phase on which validator is triggered. */
	private PhaseId phaseValidator = PhaseId.ANY_PHASE;

	/**
	 * Instantiates a new validation.
	 */
	public Validation() {
		// messages should not be changed in order that test execution is
		// correct
		this.validatorMessage = "validator test message!";
		this.requiredMessage = "required test message!";
	}

	/**
	 * Resets test variables.
	 */
	public void reset() {
		validatorMessageTest = "";
		requiredMessageTest = "";
		statusValidator = false;
		phaseValidator = PhaseId.ANY_PHASE;
	}

	/**
	 * Validates input value.
	 * 
	 * @param context
	 *            the context
	 * @param component
	 *            the component
	 * @param value
	 *            the value
	 * 
	 * @throws ValidatorException
	 *             the validator exception
	 */
	@SuppressWarnings("unchecked")
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		// validator is called
		statusValidator = true;
		// keep phase
		phaseValidator = PhaseTracker.currentPhase;
		if (value != null) {
			if (value instanceof List) {
				List<Object> arr_value = (List<Object>) value;
				for (Object val : arr_value) {
					// should not be changed in order that test execution is
					// correct
					if (val.toString().equals("validator")) {
						throw new ValidatorException(new FacesMessage(
								FacesMessage.SEVERITY_ERROR,
								"Validation error", "Incorrect input"));
					}
				}
			} else {
				String st = value.toString();
				// should not be changed in order that test execution
				// is correct
				if (st.equals("validator")) {
					throw new ValidatorException(new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Validation error",
							"Incorrect input"));
				}
			}
		}
	}

	/**
	 * Tests validator attribute.
	 * 
	 * @param attr
	 *            the tested attribute
	 * @param immediate
	 *            the immediate attribute value of the current component
	 */
	public void validatorCheck(Attribute attr, boolean immediate) {
		// validator should be called...
		if (statusValidator) {
			// ... on the 3th phase if component is not immediate...
			if ((phaseValidator.equals(PhaseId.PROCESS_VALIDATIONS))
					&& (!immediate)
					// ... or on the 2th phase if component is immediate
					|| (phaseValidator.equals(PhaseId.APPLY_REQUEST_VALUES))
					&& (immediate)) {
				attr.setStatus(Status.PASSED);
			} else {
				attr.setStatus(Status.FAILED);
			}
		} else {
			attr.setStatus(Status.FAILED);
		}
	}

	/**
	 * Tests validatorMessage attribute.
	 * 
	 * @param attr
	 *            the tested attribute
	 */
	public void validatorMessageCheck(Attribute attr) {
		// actual message should be given from page
		if (validatorMessageTest.equals("")) {
			attr.setStatus(Status.NOT_TESTED);
		} else {
			if (validatorMessageTest.indexOf(validatorMessage) != -1) {
				attr.setStatus(Status.PASSED);
			} else {
				attr.setStatus(Status.FAILED);
			}
		}
	}

	/**
	 * Tests immediate attribute.
	 * 
	 * @param attr
	 *            the tested attribute
	 * @param immediate
	 *            the immediate attribute value of the current component
	 */
	public void immediateCheck(Attribute attr, boolean immediate) {
		// component should be immediate...
		if ((statusValidator) && (immediate)) {
			// ...and validator should be called on the 2th phase
			if (phaseValidator.equals(PhaseId.APPLY_REQUEST_VALUES)) {
				attr.setStatus(Status.PASSED);
			} else {
				attr.setStatus(Status.FAILED);
			}
		} else
			attr.setStatus(Status.NOT_TESTED);
	}

	/**
	 * Tests required attribute.
	 * 
	 * @param attr
	 *            the tested attribute
	 */
	public void requiredCheck(Attribute attr) {
		// actual message should be given from page
		if (requiredMessageTest.equals("")) {
			attr.setStatus(Status.NOT_TESTED);
		} else {
			attr.setStatus(Status.PASSED);
		}
	}

	/**
	 * Tests requiredMessage attribute.
	 * 
	 * @param attr
	 *            the tested attribute
	 */
	public void requiredMessageCheck(Attribute attr) {
		// actual message should be given from page
		if (requiredMessageTest.equals("")) {
			attr.setStatus(Status.NOT_TESTED);
		} else {
			if (requiredMessageTest.indexOf(requiredMessage) != -1) {
				attr.setStatus(Status.PASSED);
			} else {
				attr.setStatus(Status.FAILED);
			}
		}
	}

	/**
	 * Gets the validator message.
	 * 
	 * @return the validator message
	 */
	public String getValidatorMessage() {
		return validatorMessage;
	}

	/**
	 * Gets the required message.
	 * 
	 * @return the required message
	 */
	public String getRequiredMessage() {
		return requiredMessage;
	}

	/**
	 * Gets the validator message test.
	 * 
	 * @return the validator message test
	 */
	public String getValidatorMessageTest() {
		return validatorMessageTest;
	}

	/**
	 * Sets the validator message test.
	 * 
	 * @param validatorMessageTest
	 *            the new validator message test
	 */
	public void setValidatorMessageTest(String validatorMessageTest) {
		this.validatorMessageTest = validatorMessageTest;
	}

	/**
	 * Gets the required message test.
	 * 
	 * @return the required message test
	 */
	public String getRequiredMessageTest() {
		return requiredMessageTest;
	}

	/**
	 * Sets the required message test.
	 * 
	 * @param requiredMessageTest
	 *            the new required message test
	 */
	public void setRequiredMessageTest(String requiredMessageTest) {
		this.requiredMessageTest = requiredMessageTest;
	}
}