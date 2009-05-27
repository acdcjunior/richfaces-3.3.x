package org.richfaces.regressionarea.issues.rf5772;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.international.LocaleSelector;

@Name("rf5772")
@Scope(ScopeType.SESSION)
public class Bean {

	private Map<String, List<String>> myMap;

	public Bean() {

		List<String> locals = new ArrayList<String>();
		for (SelectItem i : LocaleSelector.instance().getSupportedLocales()) {
			locals.add((String) i.getValue());
		}

		myMap = new HashMap<String, List<String>>();
		myMap.put("all", locals);

	}

	public Map<String, List<String>> getMyMap() {
		return myMap;
	}

	public void setMyMap(Map<String, List<String>> myMap) {
		this.myMap = myMap;
	}
}