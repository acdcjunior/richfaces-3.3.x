package org.richfaces.regressionarea.issues.rf6487;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("rf6487")
@Scope(ScopeType.SESSION)
public class Bean {

	private String val;

	public Bean() {
		val = "";
	}

	public void action() {
		val += "-add";
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

}
