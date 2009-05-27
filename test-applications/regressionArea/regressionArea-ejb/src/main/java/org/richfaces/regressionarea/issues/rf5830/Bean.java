package org.richfaces.regressionarea.issues.rf5830;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("rf5830")
@Scope(ScopeType.SESSION)
public class Bean {

	public Bean(){
		
	}
	public void addMessage(){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("message"));	
	}
}
