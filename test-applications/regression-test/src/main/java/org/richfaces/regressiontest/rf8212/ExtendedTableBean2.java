/**
 * 
 */
package org.richfaces.regressiontest.rf8212;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.richfaces.model.DataProvider;
import org.richfaces.model.ExtendedTableDataModel;
import org.richfaces.model.selection.Selection;
import org.richfaces.model.selection.SimpleSelection;

/**
 * @author Ilya Shaikovsky, Pavol Pitonak
 *
 */
public class ExtendedTableBean2 {
	private String sortMode="single";
	private String selectionMode="none";
	private Object tableState;
	private Selection selection = new SimpleSelection();
	private List<Capital> capitals = new ArrayList<Capital>();
	private ExtendedTableDataModel<Capital> dataModel;
	private List<Capital> selectedCapitals = new ArrayList<Capital>();
	
	public String getSortMode() {
		return sortMode;
	}

	public void setSortMode(String sortMode) {
		this.sortMode = sortMode;
	}

	public String getSelectionMode() {
		return selectionMode;
	}

	public void setSelectionMode(String selectionMode) {
		this.selectionMode = selectionMode;
	}

	public ExtendedTableBean2() {
	    
	    for (char charr = 'a'; charr <= 'z'; charr++) {
	        Capital c = new Capital();
	        c.setName("name " + charr + charr + charr);
	        c.setState("state " + charr + charr + charr);
	        capitals.add(c);
	    }
	    
	    
	    
	}
	
	public void takeSelection(){
		selectedCapitals.clear();
		Iterator<Object> iterator = getSelection().getKeys();
		while (iterator.hasNext()) {
			Object key = iterator.next();
			selectedCapitals.add(getCapitalsDataModel().getObjectByKey(key));
		}
	}
	
	public ExtendedTableDataModel<Capital> getCapitalsDataModel() {
		if (dataModel == null) {
			dataModel = new ExtendedTableDataModel<Capital>(new DataProvider<Capital>(){

				private static final long serialVersionUID = 5054087821033164847L;

				public Capital getItemByKey(Object key) {
					for(Capital c : capitals){
						if (key.equals(getKey(c))){
							return c;
						}
					}
					return null;
				}

				public List<Capital> getItemsByRange(int firstRow, int endRow) {
					return capitals.subList(firstRow, endRow);
				}

				public Object getKey(Capital item) {
					return item.getName();
				}

				public int getRowCount() {
					return capitals.size();
				}
				
			});
			
		}
		return dataModel;
	}

	public void setCapitals(List<Capital> capitals) {
		this.capitals = capitals;
	}

	public Object getTableState() {
		return tableState;
	}

	public void setTableState(Object tableState) {
		this.tableState = tableState;
	}

	public Selection getSelection() {
		return selection;
	}

	public void setSelection(Selection selection) {
		this.selection = selection;
	}

	public List<Capital> getSelectedCapitals() {
		return selectedCapitals;
	}

	public void setSelectedCapitals(List<Capital> selectedCapitals) {
		this.selectedCapitals = selectedCapitals;
	}

}
