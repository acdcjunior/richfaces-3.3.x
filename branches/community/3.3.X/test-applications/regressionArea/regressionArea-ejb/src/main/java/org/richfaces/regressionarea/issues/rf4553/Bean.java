package org.richfaces.regressionarea.issues.rf4553;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("rf4553")
@Scope(ScopeType.SESSION)
public class Bean {
	private String firstName;
	private String lastName;
	private String company;
	private String notes;
	private int counter;
	private boolean ajaxRendered;
	private boolean useAjaxRendered;

	public boolean isAjaxRendered() {
		return ajaxRendered;
	}

	public void setAjaxRendered(boolean ajaxRendered) {
		this.ajaxRendered = ajaxRendered;
	}

	public int getCounter() {
		return counter++;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public Bean() {
		firstName = "";
		lastName = "";
		company = "";
		notes = "";
		ajaxRendered = false;
		useAjaxRendered = false;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		System.out.println("setFirstName(String firstName)");
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		System.out.println("setLastName(String lastName)");
		this.lastName = lastName;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		System.out.println("setCompany(String company)");
		this.company = company;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		System.out.println("setNotes(String notes)");
		this.notes = notes;
	}

	public boolean isUseAjaxRendered() {
		return useAjaxRendered;
	}

	public void setUseAjaxRendered(boolean useAjaxRendered) {
		this.useAjaxRendered = useAjaxRendered;
	}
	
	public void reset(){
		counter = 0;
	}

}
