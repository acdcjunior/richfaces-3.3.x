package org.richfaces.regressionarea.issues.rf4432;

public class Item {
	private String name;
	private String description;
	
	public Item(String name, String desc){
		this.name = name;
		this.description = desc;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
