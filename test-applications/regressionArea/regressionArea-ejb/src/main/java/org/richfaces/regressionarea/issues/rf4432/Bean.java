package org.richfaces.regressionarea.issues.rf4432;

import java.util.ArrayList;
import java.util.Collection;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.richfaces.model.selection.SimpleSelection;

@Name("rf4432")
@Scope(ScopeType.SESSION)
public class Bean {
	private Collection<Item> items;
	private SimpleSelection selectedItemTableRow;
	private int currentView = 1;

	public Bean() {
		items = new ArrayList<Item>();
		for (int i = 0; i < 10; i++) {
			items.add(new Item("name " + i, "description " + i));
		}
	}

	public Collection<Item> getItems() {
		return items;
	}

	public void setItems(Collection<Item> items) {
		this.items = items;
	}

	public SimpleSelection getSelectedItemTableRow() {
		return selectedItemTableRow;
	}

	public void setSelectedItemTableRow(SimpleSelection selecteditemTableRow) {
		this.selectedItemTableRow = selecteditemTableRow;
	}

	public boolean getDrawView1() {
		return currentView == 1;
	}

	public boolean getDrawView2() {
		return currentView == 2;
	}

	public String goView1() {
		currentView = 1;
		return null;
	}

	public String goView2() {
		currentView = 2;
		return null;
	}
}
