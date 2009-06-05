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
package org.richfaces.regressionarea.issues.rf3341;

import java.util.ArrayList;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

/**
 * This test case for: <a href="https://jira.jboss.org/jira/browse/RF-3341">RF-3341 - 
 * rich:dataTable encodes its childs if this isn't nesessary</a>
 * 
 * @author Mikhail Vitenkov
 * @since 3.3.2
 */

@Name("rf3341")
@Scope(ScopeType.SESSION)
public class Bean {
	private String name;
	private String job;
	private ArrayList<Capital> capitals;
	private boolean rendered;
	private int counter;
	
	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public boolean isRendered() {
		return rendered;
	}

	public void setRendered(boolean rendered) {
		this.rendered = rendered;
	}

	public String getName() {
		System.out.println("getName()");
		return name;
	}

	public void setName(String name) {
		System.out.println("setName(String name)");
		this.name = name;
	}

	public String getJob() {
		System.out.println("getJob()");
		return job;
	}

	public void setJob(String job) {
		System.out.println("setJob(String job)");
		this.job = job;
	}

	public ArrayList<Capital> getCapitals() {
		System.out.println("getCapitals()");
		counter++;
		return capitals;
	}

	public void setCapitals(ArrayList<Capital> capitals) {
		System.out.println("setCapitals(ArrayList<Capital> capitals)");
		this.capitals = capitals;
	}

	public Bean(){
		this.name = "name";
		this.job = "job";
		
		capitals = new ArrayList<Capital>();
		String[] caps = {"Washington","Abu Dhabi","Antananarivo","Minsk"};
		for(String s:caps){
			capitals.add(new Capital(s));
		}
	}
}
