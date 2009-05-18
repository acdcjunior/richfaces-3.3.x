package custom;

import java.util.ArrayList;

public class CustomList {
	private ArrayList<Custom> customList;
	private int listSize;

	public CustomList() {
		listSize = 12;
		customList = new ArrayList<Custom>();
		
		for(int i = 0; i < listSize; i++) {
			customList.add(new Custom(i));
		}
	}	
	
	public void resizeList(int elements){
		customList.clear();
		for(int i = 0; i < elements; i++){
			customList.add(new Custom(i));
		}
	}
	
	public int getListSize() {
		return listSize;
	}

	public void setListSize(int listSize) {
		this.listSize = listSize;
	}

	public ArrayList<Custom> getCustomList() {
		return customList;
	}

	public void setCustomList(ArrayList<Custom> testList) {
		this.customList = testList;
	}
	
}
