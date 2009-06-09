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
package org.richfaces.regressionarea.issues.rf7323;

import java.util.ArrayList;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.richfaces.event.NodeSelectedEvent;

/**
 * @author Mikhail Vitenkov
 * @since 3.3.2
 */
@Name("rf7323")
@Scope(ScopeType.SESSION)
public class TTreeRNA {
	private ArrayList<Dir> treeRNAroots;	
	private String recursionOrder;
	private String selectedNode;

	public String getSelectedNode() {
		return selectedNode;
	}

	public void setSelectedNode(String selectedNode) {
		this.selectedNode = selectedNode;
	}

	public TTreeRNA() {
		recursionOrder = "first";
		selectedNode = "";
		
		treeRNAroots = new ArrayList<Dir>();
		ArrayList<Dir> dirsArr = new ArrayList<Dir>();
		ArrayList<Dir> subDirsArr = new ArrayList<Dir>();
		ArrayList<Package> packArr = new ArrayList<Package>();
		ArrayList<Package> subPackArr = new ArrayList<Package>();

		treeRNAroots.clear();
		dirsArr.clear();
		for (int j = 0; j < 2; j++) {
			packArr.clear();
			subDirsArr.clear();
			for (int k = 0; k < 3; k++) {
				packArr.add(new Package("package #" + j + " " + k));
			}
			for (int f = 0; f < 2; f++) {
				subPackArr.clear();
				for (int l = 0; l < 3; l++) {
					subPackArr.add(new Package("subPackage #" + j + " " + f
							+ " " + l));
				}
				subDirsArr.add(new Dir("subDir #" + j + " " + f,
						new ArrayList<Package>(subPackArr)));
			}
			dirsArr.add(new Dir("dir #" + j, new ArrayList<Package>(packArr),
					new ArrayList<Dir>(subDirsArr)));
		}
		treeRNAroots.add(new Dir("*** root ***", null, dirsArr));
	}
	
	public void nodeSelectListener(NodeSelectedEvent e) {
		
		selectedNode = e.getSource().toString();
	}

	public String getRecursionOrder() {
		return recursionOrder;
	}

	public void setRecursionOrder(String recursionOrder) {
		this.recursionOrder = recursionOrder;
	}

	public ArrayList<Dir> getTreeRNAroots() {
		return treeRNAroots;
	}

	public void setTreeRNAroots(ArrayList<Dir> treeRNAroots) {
		this.treeRNAroots = treeRNAroots;
	}	
}
