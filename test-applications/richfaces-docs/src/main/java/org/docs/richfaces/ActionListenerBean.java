package org.docs.richfaces;

import org.ajax4jsf.event.AjaxEvent;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;

public class ActionListenerBean implements org.ajax4jsf.event.AjaxListener {
    public void processAjax(AjaxEvent event) {
        FacesContext.getCurrentInstance().addMessage("form", new FacesMessage("Ajax request is sent"));
    }
}
