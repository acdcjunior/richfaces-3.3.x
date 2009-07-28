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

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.component.UIComponent;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.event.PhaseId;

import util.data.Car;
import util.parser.Attribute;
import util.parser.Status;
import util.phaseTracker.PhaseTracker;

/**
 * The Convertion bean provides methods for "converter" and "converterMessage"
 * attributes testing.
 */
public class Convertion {

	/**
	 * The status converter get as object indicates whether getAsObject is
	 * called or not.
	 */
	private boolean statusConverterGetAsObject = false;

	/**
	 * The status converter get as string indicates whether getAsString is
	 * called or not.
	 */
	private boolean statusConverterGetAsString = false;

	/**
	 * The phase converter get as object keeps the JSF phase on which
	 * getAsObject is triggered.
	 */
	private PhaseId phaseConverterGetAsObject = PhaseId.ANY_PHASE;

	/**
	 * The phase converter get as string keeps the JSF phase on which
	 * getAsString is triggered.
	 */
	private PhaseId phaseConverterGetAsString = PhaseId.ANY_PHASE;

	/** The converter message test which is actual displayed on the page. */
	private String converterMessageTest = "";

	/** The converter message which is defined in "converterMessage" attribute. */
	private String converterMessage;

	/**
	 * Instantiates a new convertion.
	 */
	public Convertion() {
		// message should not be changed in order that test execution is
		// correct
		this.converterMessage = "test converter error!";
	}

	/**
	 * Resets test variables.
	 */
	public void reset() {
		converterMessageTest = "";
		statusConverterGetAsObject = false;
		statusConverterGetAsString = false;
		phaseConverterGetAsObject = PhaseId.ANY_PHASE;
		phaseConverterGetAsString = PhaseId.ANY_PHASE;
	}

	/**
	 * Gets the converter.
	 * 
	 * @return the converter
	 */
	public Converter getConvert() {
		return new Converter() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext,
			 *      javax.faces.component.UIComponent, java.lang.String)
			 */
			public Object getAsObject(FacesContext context,
					UIComponent component, String newValue)
					throws ConverterException {
				// getAsObject is called
				statusConverterGetAsObject = true;
				// keep phase
				phaseConverterGetAsObject = PhaseTracker.currentPhase;
				if (newValue.equals("converter"))
					throw new ConverterException(new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Converter error",
							"converter to Object error"));

				return newValue;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext,
			 *      javax.faces.component.UIComponent, java.lang.Object)
			 */
			public String getAsString(FacesContext context,
					UIComponent component, Object value)
					throws ConverterException {
				// getAsString is called
				statusConverterGetAsString = true;
				// keep phase
				phaseConverterGetAsString = PhaseTracker.currentPhase;

				return (null == value) ? null : value.toString();
			}
		};
	}

	/**
	 * Gets the car converter.
	 * 
	 * @return the car converter
	 */
	public Converter getCarConvert() {
		return new Converter() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext,
			 *      javax.faces.component.UIComponent, java.lang.String)
			 */
			public Object getAsObject(FacesContext context,
					UIComponent component, String newValue)
					throws ConverterException {
				// getAsObject is called
				statusConverterGetAsObject = true;
				// keep phase
				phaseConverterGetAsObject = PhaseTracker.currentPhase;
				if (newValue.indexOf("converter") > -1)
					throw new ConverterException(new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Converter error",
							"converter to Object error"));

				String[] str = newValue.split(":");
				return new Car(str[0], str[1], Integer.parseInt(str[2]));
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext,
			 *      javax.faces.component.UIComponent, java.lang.Object)
			 */
			public String getAsString(FacesContext context,
					UIComponent component, Object value)
					throws ConverterException {
				// getAsString is called
				statusConverterGetAsString = true;
				// keep phase
				phaseConverterGetAsString = PhaseTracker.currentPhase;
				if (value instanceof Car) {
					Car car = (Car) value;
					return (null == car) ? null : car.toString();
				} else
					throw new ConverterException("is not a class util.data.Car");
			}
		};
	}

	/**
	 * Tests converter attribute.
	 * 
	 * @param attr
	 *            the tested attribute
	 * @param immediate
	 *            the immediate attribute value of the current component
	 */
	public void converterCheck(Attribute attr, boolean immediate) {
		// converter should be called...
		if ((statusConverterGetAsObject) && (statusConverterGetAsString)) {
			// ... getAsObject on the 3th phase if component is not immediate...
			if (((phaseConverterGetAsObject.equals(PhaseId.PROCESS_VALIDATIONS))
					&& (!immediate)
			// ... getAsObject or on the 2th phase if component is immediate...
			|| (phaseConverterGetAsObject.equals(PhaseId.APPLY_REQUEST_VALUES))
					&& (immediate))
					// ... and getAsString on the 6th phase
					&& (phaseConverterGetAsString
							.equals(PhaseId.RENDER_RESPONSE))) {
				attr.setStatus(Status.PASSED);
			} else {
				attr.setStatus(Status.FAILED);
			}
		} else {
			attr.setStatus(Status.FAILED);
		}
	}

	/**
	 * Tests converterMessage attribute.
	 * 
	 * @param attr
	 *            the tested attribute
	 */
	public void converterMessageCheck(Attribute attr) {
		// actual message should be given from page
		if (converterMessageTest.equals("")) {
			attr.setStatus(Status.NOT_TESTED);
		} else {
			if (converterMessageTest.indexOf(converterMessage) != -1) {
				attr.setStatus(Status.PASSED);
			} else {
				attr.setStatus(Status.FAILED);
			}
		}
	}

	/**
	 * Gets the converter message test.
	 * 
	 * @return the converter message test
	 */
	public String getConverterMessageTest() {
		return converterMessageTest;
	}

	/**
	 * Sets the converter message test.
	 * 
	 * @param converterMessageTest
	 *            the new converter message test
	 */
	public void setConverterMessageTest(String converterMessageTest) {
		this.converterMessageTest = converterMessageTest;
	}

	/**
	 * Gets the converter message.
	 * 
	 * @return the converter message
	 */
	public String getConverterMessage() {
		return converterMessage;
	}
}
