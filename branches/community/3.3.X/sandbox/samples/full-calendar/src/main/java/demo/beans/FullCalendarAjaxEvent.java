package demo.beans;


import java.util.Date;

import javax.faces.component.UIComponent;

import org.ajax4jsf.event.AjaxEvent;

public class FullCalendarAjaxEvent extends AjaxEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3588719931853540807L;

	private Date startDate;
	
	private Date endDate;
	
	public FullCalendarAjaxEvent(UIComponent component, Date startDate, Date endDate) {
		super(component);
	}

	public Date getStartDate() {
		return startDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}
}
