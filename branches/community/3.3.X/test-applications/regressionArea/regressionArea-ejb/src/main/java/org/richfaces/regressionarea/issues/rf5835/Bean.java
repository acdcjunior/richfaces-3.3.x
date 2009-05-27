package org.richfaces.regressionarea.issues.rf5835;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.richfaces.model.TreeNode;
import org.richfaces.model.TreeNodeImpl;

@Name("treeBean")
@Scope(ScopeType.SESSION)
public class Bean {	
	private TreeNode data;
	

	public TreeNode getData() {
		return data;
	}


	public void setData(TreeNode data) {
		this.data = data;
	}


	public Bean() {
		TreeNodeImpl<String> n = new TreeNodeImpl<String>();
		n.setData("rnode");
		int i = 0;
		for (i = 0; i < 10; i++) {
			TreeNodeImpl<String> t = new TreeNodeImpl<String>();
			t.setData("node-" + i);
			n.addChild(Integer.valueOf(i), t);				
		}
		data = new TreeNodeImpl<String>();
		data.setData("data");
		data.addChild(Integer.valueOf(i), n);

		}
		


}
