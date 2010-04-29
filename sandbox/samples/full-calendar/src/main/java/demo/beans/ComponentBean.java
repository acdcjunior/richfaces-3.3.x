package demo.beans;

import javax.faces.component.UIComponent;

public class ComponentBean {

	private UIComponent component = new UIFullCalendar();
	
	public UIComponent getComponent() {
		return component;
	}
	
	public void setComponent(UIComponent component) {
		this.component = component;
	}

}
