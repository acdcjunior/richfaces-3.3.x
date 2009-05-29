package org.richfaces.regressionarea.issues.rf6896;

import java.util.ArrayList;

import javax.faces.model.SelectItem;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("rf6896")
@Scope(ScopeType.SESSION)
public class Bean {
	
	private ArrayList<SelectItem> data;
	
	public ArrayList<SelectItem> getData() {
		return data;
	}

	public void setData(ArrayList<SelectItem> data) {
		this.data = data;
	}

	public Bean(){
		data = new ArrayList<SelectItem>();
		for(int i=0;i<10;i++){
			data.add(new SelectItem("item"+i,"item"+i));
		}		
	}

}
