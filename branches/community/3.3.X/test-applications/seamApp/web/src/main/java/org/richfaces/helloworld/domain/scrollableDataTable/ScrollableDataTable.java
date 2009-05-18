package org.richfaces.helloworld.domain.scrollableDataTable;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import org.ajax4jsf.model.DataComponentState;
import org.richfaces.helloworld.domain.util.componentInfo.ComponentInfo;
import org.richfaces.helloworld.domain.util.data.Data;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.richfaces.component.html.HtmlScrollableDataTable;
import org.richfaces.model.selection.Selection;

/**
 * @author AYanul
 *
 */
@Name("scrollableDT")
@Scope(ScopeType.SESSION)
public class ScrollableDataTable 
{
	private ArrayList<Data> data;
	private String width;
	private int rows;
	private String key;
	private boolean hideWhenScrolling;
	private int dataLength;
	private int frozenColCount;
	private String sortMode;
	private int first;
	private boolean rendered;
	private int timeout;
	private String height;
	private boolean limitToList;
	private DataComponentState componentState;
	private boolean bypassUpdates;
	private boolean ignoreDupResponses;
	private String eventsQueue;
	private String activeRowKey;
	private Selection selection;
	private boolean ajaxSingle;
	private GregorianCalendar date;
	private HtmlScrollableDataTable htmlScrollableDataTable = null;
	
	public void addHtmlScrollableDataTable(){
		ComponentInfo info = ComponentInfo.getInstance();
		info.addField(htmlScrollableDataTable);
	}
	
	public HtmlScrollableDataTable getHtmlScrollableDataTable() {
		return htmlScrollableDataTable;
	}

	public void setHtmlScrollableDataTable(
			HtmlScrollableDataTable htmlScrollableDataTable) {
		this.htmlScrollableDataTable = htmlScrollableDataTable;
	}

	public GregorianCalendar getDate() {
		return date;
	}

	public void setDate(GregorianCalendar date) {
		this.date = date;
	}

	public ScrollableDataTable() {
		sortMode = "single";
		width = "400px";
		rows = 30;
		hideWhenScrolling = false;
		dataLength = 40;
		data = new ArrayList<Data>();
		first = 0;
		rendered = true;
		timeout = 0;
		height = "400px";
		limitToList = false;
		bypassUpdates = false;
		ignoreDupResponses = false;
		ajaxSingle = false;
		date = new GregorianCalendar();
		addNewItem();
	}
	
	public Selection getSelection() {
		return selection;
	}

	public void setSelection(Selection selection) {
		this.selection = selection;
	}

	public int getFrozenColCount() {
		return frozenColCount;
	}

	public void setFrozenColCount(int frozenColCount) {
		this.frozenColCount = frozenColCount;
	}

	public int getFirst() {
		return first;
	}

	public void setFirst(int first) {
		this.first = first;
	}

	public boolean isRendered() {
		return rendered;
	}

	public void setRendered(boolean rendered) {
		this.rendered = rendered;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public boolean isLimitToList() {
		return limitToList;
	}

	public void setLimitToList(boolean limitToList) {
		this.limitToList = limitToList;
	}

	public DataComponentState getComponentState() {
		return componentState;
	}

	public void setComponentState(DataComponentState componentState) {
		this.componentState = componentState;
	}

	public boolean isBypassUpdates() {
		return bypassUpdates;
	}

	public void setBypassUpdates(boolean bypassUpdates) {
		this.bypassUpdates = bypassUpdates;
	}

	public boolean isIgnoreDupResponses() {
		return ignoreDupResponses;
	}

	public void setIgnoreDupResponses(boolean ignoreDupResponses) {
		this.ignoreDupResponses = ignoreDupResponses;
	}

	public String getEventsQueue() {
		return eventsQueue;
	}

	public void setEventsQueue(String eventsQueue) {
		this.eventsQueue = eventsQueue;
	}

	public String getActiveRowKey() {
		return activeRowKey;
	}

	public void setActiveRowKey(String activeRowKey) {
		this.activeRowKey = activeRowKey;
	}

	public String getKey() {
		return key;
	}

	public boolean isHideWhenScrolling() {
		return hideWhenScrolling;
	}

	public void setHideWhenScrolling(boolean hideWhenScrolling) {
		this.hideWhenScrolling = hideWhenScrolling;
	}
	
	public void addNewItem() {		
		if(dataLength < 0) dataLength = 0;
		if(data.size() > dataLength)
			for(int i = data.size() - 1; i >= dataLength; i--)
				data.remove(i);
		else
			for(int i = data.size(); i < dataLength; i++){
				date.set(2008, 5, 14, 3, i); 			
				data.add(new Data(i, "Text " + i, "Link " + i, "select" +(i % 5), Data.statusIcon[i % 5], date.getTime()));
			}
	}
	
	public ArrayList<Data> getData() {
		return data;
	}

	public void setData(ArrayList<Data> data) {
		this.data = data;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public int getDataLength() {
		return dataLength;
	}

	public void setDataLength(int dataLength) {
		this.dataLength = dataLength;
	}

	public boolean isAjaxSingle() {
		return ajaxSingle;
	}

	public void setAjaxSingle(boolean ajaxSingle) {
		this.ajaxSingle = ajaxSingle;
	}

	public String getSortMode() {
		return sortMode;
	}

	public void setSortMode(String sortMode) {
		this.sortMode = sortMode;
	}
}