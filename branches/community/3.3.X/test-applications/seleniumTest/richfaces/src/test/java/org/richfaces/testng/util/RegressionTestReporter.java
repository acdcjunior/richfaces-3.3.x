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

import java.util.ArrayList;

public class RegressionTestReporter {
	private ArrayList<SeleniumClass> oldFailed;
	private ArrayList<SeleniumClass> newPassed;
	private ArrayList<SeleniumClass> newFailed;
	private ArrayList<SeleniumClass> oldPassed;
	
	public RegressionTestReporter() {
		this.oldFailed = new ArrayList<SeleniumClass>();
		this.newPassed = new ArrayList<SeleniumClass>();
		this.newFailed = new ArrayList<SeleniumClass>();
		this.oldPassed = new ArrayList<SeleniumClass>();	
	}
	
	public void generateReport(){		
		XMLParser.parse();		
		fillOldFailedMethods();
		fillNewPassedMethods();
		fillNewFailedMethods();
		fillOldPassedMethods();
	}	
	
	private void fillOldFailedMethods(){
		ArrayList<String> previousFailedMethods = new ArrayList<String>();
		ArrayList<String> actualFailedMethods = new ArrayList<String>();
		for(SeleniumClass sc1:XMLParser.testngResultsClassList){
			for(SeleniumClass sc2:XMLParser.testngFailedClassList){
				if(sc1.equals(sc2)){
					previousFailedMethods.addAll(sc1.getMethods());
					actualFailedMethods.addAll(sc2.getMethods());
					previousFailedMethods.retainAll(actualFailedMethods);
					if(previousFailedMethods.size() > 0){
						SeleniumClass sc = new SeleniumClass(sc1.getName());
						sc.getMethods().addAll(previousFailedMethods);
						oldFailed.add(sc);
					}				
				}
			}	
			previousFailedMethods.clear();
			actualFailedMethods.clear();
		}		
	}	
	private void fillNewPassedMethods(){
		ArrayList<String> previousFailedMethods = new ArrayList<String>();
		ArrayList<String> actualPassedMethods = new ArrayList<String>();
		for(SeleniumClass sc1:XMLParser.testngResultsClassList){
			for(SeleniumClass sc2:XMLParser.passedClassList){
				if(sc1.equals(sc2)){
					previousFailedMethods.addAll(sc1.getMethods());
					actualPassedMethods.addAll(sc2.getMethods());
					previousFailedMethods.retainAll(actualPassedMethods);
					if(previousFailedMethods.size() > 0){
						SeleniumClass sc = new SeleniumClass(sc1.getName());
						sc.getMethods().addAll(previousFailedMethods);
						newPassed.add(sc);
					}				
				}
			}	
			previousFailedMethods.clear();
			actualPassedMethods.clear();
		}			
	}
	private void fillNewFailedMethods(){
		ArrayList<String> actualFailedMethods = new ArrayList<String>();
		ArrayList<String> previousFailedMethods = new ArrayList<String>();
		for(SeleniumClass sc1:XMLParser.testngFailedClassList){
			for(SeleniumClass sc2:XMLParser.testngResultsClassList){
				if(sc1.equals(sc2)){
					actualFailedMethods.addAll(sc1.getMethods());
					previousFailedMethods.addAll(sc2.getMethods());
					actualFailedMethods.removeAll(previousFailedMethods);
					if(actualFailedMethods.size() > 0){
						SeleniumClass sc = new SeleniumClass(sc1.getName());
						sc.getMethods().addAll(actualFailedMethods);
						newFailed.add(sc);
					}				
				}
			}
			actualFailedMethods.clear();
			previousFailedMethods.clear();
		}		
	}
	private void fillOldPassedMethods(){
		ArrayList<String> actualPassedMethods = new ArrayList<String>();
		ArrayList<String> previousFailedMethods = new ArrayList<String>();
		for(SeleniumClass sc1:XMLParser.passedClassList){
			for(SeleniumClass sc2:XMLParser.testngResultsClassList){
				if(sc1.equals(sc2)){
					actualPassedMethods.addAll(sc1.getMethods());
					previousFailedMethods.addAll(sc2.getMethods());
					actualPassedMethods.removeAll(previousFailedMethods);
					if(actualPassedMethods.size() > 0){
						SeleniumClass sc = new SeleniumClass(sc1.getName());
						sc.getMethods().addAll(actualPassedMethods);
						oldPassed.add(sc);
					}				
				}
			}	
			actualPassedMethods.clear();
			previousFailedMethods.clear();
		}		
	}

	public ArrayList<SeleniumClass> getOldFailed() {
		return oldFailed;
	}

	public void setOldFailed(ArrayList<SeleniumClass> oldFailed) {
		this.oldFailed = oldFailed;
	}

	public ArrayList<SeleniumClass> getNewPassed() {
		return newPassed;
	}

	public void setNewPassed(ArrayList<SeleniumClass> newPassed) {
		this.newPassed = newPassed;
	}

	public ArrayList<SeleniumClass> getNewFailed() {
		return newFailed;
	}

	public void setNewFailed(ArrayList<SeleniumClass> newFailed) {
		this.newFailed = newFailed;
	}

	public ArrayList<SeleniumClass> getOldPassed() {
		return oldPassed;
	}

	public void setOldPassed(ArrayList<SeleniumClass> oldPassed) {
		this.oldPassed = oldPassed;
	}	
	
}
