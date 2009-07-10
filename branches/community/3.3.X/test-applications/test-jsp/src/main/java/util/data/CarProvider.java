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

package util.data;

import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * The Class CarProvider parses cars.xml resource file to provide list of Car
 * objects which is used for test purposes.
 */
public class CarProvider extends DefaultHandler {

	/** The all cars. */
	private ArrayList<Car> allCars = null;

	/**
	 * Instantiates a new car provider.
	 */
	public CarProvider() {
		if (allCars == null) {
			allCars = new ArrayList<Car>();
			try {
				SAXParserFactory spf = SAXParserFactory.newInstance();
				SAXParser sp = spf.newSAXParser();
				sp.parse(new InputSource(getClass().getResourceAsStream(
						"cars.xml")), this);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Gets the all cars.
	 * 
	 * @return the all cars
	 */
	public ArrayList<Car> getAllCars() {
		return allCars;
	}

	/** The element. */
	private String element = "";

	/** The make. */
	private String mak = "";

	/** The model. */
	private String mod = "";

	/** The price. */
	private Integer pr = 0;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#startDocument()
	 */
	public void startDocument() {
		System.out.println("*** > Parser starts its work");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String,
	 *      java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	public void startElement(String namespaceURI, String localName,
			String rawName, Attributes attrs) {
		element = rawName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
	 */
	public void characters(char ch[], int start, int length) {
		String text = new String(ch, start, length);

		if (element.equals("make")) {
			element = "";
			mak = text;
		} else if (element.equals("model")) {
			element = "";
			mod = text;
		} else if (element.equals("price")) {
			try {
				element = "";
				Integer p = Integer.parseInt(text);
				pr = p;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	public void endElement(String namespaceURI, String localName, String rawName) {

		if (rawName.equals("car")) {
			allCars.add(new Car(mak, mod, pr));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#endDocument()
	 */
	public void endDocument() {
		System.out.println("*** > Parser ends its work");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#warning(org.xml.sax.SAXParseException)
	 */
	public void warning(SAXParseException ex) {
		System.err.println("[Warning] : " + ex.getMessage());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#error(org.xml.sax.SAXParseException)
	 */
	public void error(SAXParseException ex) {
		System.err.println("[Error] : " + ex.getMessage());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#fatalError(org.xml.sax.SAXParseException)
	 */
	public void fatalError(SAXParseException ex) throws SAXException {
		System.err.println("[Fatal Error] : " + ex.getMessage());
		throw ex;
	}
}
