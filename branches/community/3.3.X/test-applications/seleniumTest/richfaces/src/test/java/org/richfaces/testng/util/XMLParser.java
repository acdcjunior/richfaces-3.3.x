/**
 * License Agreement.
 *
 * JBoss RichFaces - Ajax4jsf Component Library
 *
 * Copyright (C) 2007 Exadel, Inc.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License version 2.1 as published by the Free Software Foundation.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA
 */ 
package org.richfaces.testng.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLParser {
	private static String TESTNG_FAILED_PATH;
	private static String TESTNG_RESULTS_PATH;		
	protected static ArrayList<SeleniumClass> testngFailedClassList;
	protected static ArrayList<SeleniumClass> testngResultsClassList;
	protected static ArrayList<SeleniumClass> passedClassList;

	public static ArrayList<SeleniumClass> getPassedClassList() {
		return passedClassList;
	}

	public static void setPassedClassList(
			ArrayList<SeleniumClass> passedClassList) {
		XMLParser.passedClassList = passedClassList;
	}

	public ArrayList<SeleniumClass> getTestngFailedClassList() {
		return testngFailedClassList;
	}

	public void setTestngFailedClassList(
			ArrayList<SeleniumClass> testngFailedClassList) {
		XMLParser.testngFailedClassList = testngFailedClassList;
	}

	public ArrayList<SeleniumClass> getTestngResultsClassList() {
		return testngResultsClassList;
	}

	public void setTestngResultsClassList(
			ArrayList<SeleniumClass> testngResultsClassList) {
		XMLParser.testngResultsClassList = testngResultsClassList;
	}

	public static void fillTestngFailedClassList() {

		Document doc = buildDocument(TESTNG_FAILED_PATH);		
		NodeList classLst = doc.getElementsByTagName("class");

		ArrayList<String> tempMethods = new ArrayList<String>();
		for (int i = 0; i < classLst.getLength(); i++) {
			Node classNode = classLst.item(i);// enter to the <class>
			SeleniumClass seleniumClass = new SeleniumClass(classNode
					.getAttributes().item(0).getNodeValue());

			if (classNode.getNodeType() == Node.ELEMENT_NODE) {
				Element classEl = (Element) classNode;

				NodeList includeList = classEl.getElementsByTagName("include");

				tempMethods.clear();
				for (int j = 0; j < includeList.getLength(); j++) {
					Node failedMethodName = includeList.item(j).getAttributes()
							.item(0);
					tempMethods.add(failedMethodName.getNodeValue());
				}
				seleniumClass.getMethods().addAll(tempMethods);
			}
			testngFailedClassList.add(seleniumClass);
		}
	}

	public static void fillTestngResultsClassList() {

		Document doc = buildDocument(TESTNG_RESULTS_PATH);		
		NodeList methodLst = doc.getElementsByTagName("group");

		ArrayList<String> tempMethods = new ArrayList<String>();

		Node groupNode = methodLst.item(0);// enter to the <group>

		if (groupNode.getNodeType() == Node.ELEMENT_NODE) {
			Element groupEl = (Element) groupNode;

			NodeList methodList = groupEl.getElementsByTagName("method");

			tempMethods.clear();
			for (int i = 0; i < methodList.getLength(); i++) {
				Node failuresMethodName = methodList.item(i).getAttributes()
						.getNamedItem("name");
				Node failuresClassName = methodList.item(i).getAttributes()
						.getNamedItem("class");
				String s = failuresClassName.getNodeValue();
				if (!XMLParser.getStringArrayRepresentation(
						testngResultsClassList).contains(s)) {
					SeleniumClass seleniumClass = new SeleniumClass(s);
					seleniumClass.getMethods().add(
							failuresMethodName.getNodeValue());
					testngResultsClassList.add(seleniumClass);
				} else {
					testngResultsClassList.get(
							testngResultsClassList
									.indexOf(new SeleniumClass(s)))
							.getMethods()
							.add(failuresMethodName.getNodeValue());
				}
			}
		}
	}

	public static void fillPassedClassList() {

		Document doc = buildDocument(TESTNG_RESULTS_PATH);
		NodeList classLst = doc.getElementsByTagName("class");

		ArrayList<String> passedMethods = new ArrayList<String>();
		ArrayList<String> failedMethods = new ArrayList<String>();
		for (int i = 0; i < classLst.getLength(); i++) {
			Node classNode = classLst.item(i);// enter to the <class>
			SeleniumClass seleniumClass = new SeleniumClass(classNode
					.getAttributes().item(0).getNodeValue());

			if (classNode.getNodeType() == Node.ELEMENT_NODE) {
				Element classEl = (Element) classNode;

				NodeList testMethodList = classEl
						.getElementsByTagName("test-method");

				passedMethods.clear();
				failedMethods.clear();
				for (int j = 0; j < testMethodList.getLength(); j++) {
					Node methodName = testMethodList.item(j).getAttributes()
							.getNamedItem("name");
					Node methodStatus = testMethodList.item(j).getAttributes()
							.getNamedItem("status");
					String name = methodName.getNodeValue();
					String status = methodStatus.getNodeValue();
					if (status.equals("PASS")
							&& !(passedMethods.contains(name))) {
						passedMethods.add(name);
					}
					if (status.equals("FAIL")
							&& !(failedMethods.contains(name))) {
						failedMethods.add(name);
					}
				}
				passedMethods.removeAll(failedMethods);
				seleniumClass.getMethods().addAll(passedMethods);
			}
			passedClassList.add(seleniumClass);
		}
	}
	
	public static void parse() {
		try {
			init();
		} catch (IOException e) {
			e.printStackTrace();
		}
		fillTestngFailedClassList();
		fillTestngResultsClassList();
		fillPassedClassList();
	}

	private static void init() throws IOException {		
		String currentPath = (new File("")).getAbsolutePath();
		TESTNG_FAILED_PATH = currentPath + "/test-output/testng-failed.xml";
		TESTNG_RESULTS_PATH = currentPath + "/test-output/testng-results.xml";

		testngFailedClassList = new ArrayList<SeleniumClass>();
		testngResultsClassList = new ArrayList<SeleniumClass>();
		passedClassList = new ArrayList<SeleniumClass>();
	}

	public static Document buildDocument(String PATH) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		Document doc = null;
		try {
			db = dbf.newDocumentBuilder();
			doc = db.parse(PATH);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		doc.getDocumentElement().normalize();
		return doc;
	}

	private static ArrayList<String> getStringArrayRepresentation(
			ArrayList<SeleniumClass> classes) {
		ArrayList<String> strs = new ArrayList<String>();
		for (SeleniumClass sc : classes) {
			strs.add(sc.getName());
		}
		return strs;
	}

}
