package org.richfaces.regressionarea.issues.rf7140;

import java.util.Random;
import java.util.UUID;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("rf7140")
@Scope(ScopeType.SESSION)
public class Bean {

	public static final class Item {
		private String[] data;

		private Object sortOrder;
		
		public Item(String[] data) {
			super();
			this.data = data;
		}
		
		public String[] getData() {
			return data;
		}
		
		public Object getSortOrder() {
			return sortOrder;
		}
		
		public void setSortOrder(Object sortOrder) {
			this.sortOrder = sortOrder;
		}
	}

	private Item[] items;
	
	{
		items = new Item[4];
		for (int i = 0; i < items.length; i++) {
			String[] s = new String[4];
			for (int j = 0; j < s.length; j++) {
				s[j] = UUID.randomUUID().toString();
			}
			items[i] = new Item(s);
		}
	}
	
	public Item[] getItems() {
		return items;
	}
}
