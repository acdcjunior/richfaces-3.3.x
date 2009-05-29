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

package org.richfaces.regressionarea.issues.rf7273;

import javax.faces.component.UIComponent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.richfaces.event.NodeExpandedEvent;

/**
 * This test case for: <a href="https://jira.jboss.org/jira/browse/RF-7273">RF-7273 - 
 * Tree: expanded state is not correct inside NodeExpandedEvent listener</a>
 * 
 * @author Nick Belaevski
 * @since 3.3.1
 */
@Name("rf7273")
@Scope(ScopeType.SESSION)
public class Bean {

	private DefaultMutableTreeNode node;

	private String stateString = "";
	
	public Bean() {
		node = new DefaultMutableTreeNode("root");
		node.add(new DefaultMutableTreeNode("child"));
	}
	
	public void processExpand(NodeExpandedEvent event) {
		UIComponent component = event.getComponent();
		Boolean expanded = (Boolean) component.getAttributes().get("expanded");
		stateString += expanded ? " expanded" : " collapsed";
	}
	
	public TreeNode getNode() {
		return node;
	}
	
	public String getStateString() {
		return stateString;
	}
}
