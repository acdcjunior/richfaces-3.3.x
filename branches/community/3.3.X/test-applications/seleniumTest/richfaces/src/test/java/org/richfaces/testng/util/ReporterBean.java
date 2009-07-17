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

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class ReporterBean {

	public ReporterBean() {

	}

	public void drawReport() {
		RegressionTestReporter rtr = new RegressionTestReporter();
		rtr.generateReport();

		try {
			FileOutputStream fs = new FileOutputStream("RegressionReportByQA.html");
			OutputStreamWriter out = new OutputStreamWriter(fs);
			drawUpperPart(out);
			drawNewFailedMethods(out, rtr);
			drawNewPassedMethods(out, rtr);			
			drawOldFailedMethods(out, rtr);
			drawOldPassedMethods(out, rtr);
			drawDownerPart(out);
			out.write("<h4>Note: stopSeleniumServer, startSeleniumServer, stopSelenium, startSelenium shouldn't be tested(not richfaces methods)</h2>");
			out.close();
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	public void drawUpperPart(OutputStreamWriter out) {
		try {
			out.write("<html>");
			out.write("<head>");
			out.write("<title>");
			out.write("Richfaces regression report");
			out.write("</title>");
			out.write("</head>");
			out.write("<body>");
			out.write("<table border=\"1px\" style=\"border:1px solid black; width:500px; align:center;\"");
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	public void drawDownerPart(OutputStreamWriter out) {
		try {
			out.write("</table>");
			out.write("</body>");
			out.write("</html>");
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	public void drawOldFailedMethods(OutputStreamWriter out,
			RegressionTestReporter rtr) {
		try {
			out
					.write("<tr><td><div style=\"font-weight:bold;font-size:16px;\">Old Failed methods:</div><td></tr>");
			for (SeleniumClass sc : rtr.getOldFailed()) {
				out.write("<tr><td style=\"font-weight:bold;\">Class:" + sc.getName() + "</td></tr>");
				for (String method : sc.getMethods()) {
					out.write("<tr><td style=\"color:grey;\">" + method + "</td></tr>");
				}
			}
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	public void drawNewPassedMethods(OutputStreamWriter out,
			RegressionTestReporter rtr) {
		try {
			out
					.write("<tr><td><div style=\"font-weight:bold;font-size:16px;\">New Passed methods:</div><td></tr>");
			for (SeleniumClass sc : rtr.getNewPassed()) {
				out.write("<tr><td style=\"font-weight:bold;\">Class:" + sc.getName() + "</td></tr>");
				for (String method : sc.getMethods()) {
					out.write("<tr><td style=\"color:green;\">" + method + "</td></tr>");
				}
			}
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	public void drawNewFailedMethods(OutputStreamWriter out,
			RegressionTestReporter rtr) {
		try {
			out
					.write("<tr><td><div style=\"font-weight:bold;font-size:16px;\">New Failed(!) methods:</div><td></tr>");
			for (SeleniumClass sc : rtr.getNewFailed()) {
				out.write("<tr><td style=\"font-weight:bold;\">Class:" + sc.getName() + "</td></tr>");
				for (String method : sc.getMethods()) {
					out.write("<tr><td style=\"color:red;\">" + method + "</td></tr>");
				}
			}
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	public void drawOldPassedMethods(OutputStreamWriter out,
			RegressionTestReporter rtr) {
		try {
			out
					.write("<tr><td><div style=\"font-weight:bold;font-size:16px;\">Old Passed methods:</div><td></tr>");
			for (SeleniumClass sc : rtr.getOldPassed()) {
				out.write("<tr><td style=\"font:bold;\">Class:" + sc.getName() + "</td></tr>");
				for (String method : sc.getMethods()) {
					out.write("<tr><td style=\"color:blue;\">" + method + "</td></tr>");
				}
			}
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	public static void main(String[] args) {
		ReporterBean rb = new ReporterBean();
		rb.drawReport();
	}

}
