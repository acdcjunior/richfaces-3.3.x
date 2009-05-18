/**
 * 
 */
package org.richfaces.component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.faces.FacesException;
import javax.faces.component.UIComponent;

/**
 * @author asmirnov
 * 
 */
public class LayoutStructure implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5700775901583881224L;

	private UILayoutPanel top = null;
	private UILayoutPanel bottom = null;
	private UILayoutPanel left = null;
	private UILayoutPanel right = null;
	private UILayoutPanel center = null;
	private int deep = 0;
	private List<Column> columns;

	public LayoutStructure(UILayout layout) {
		sortPanels(layout);
	}

	public LayoutStructure(UILayoutPanel panel){
		UIComponent parent = panel.getParent();
		if (parent instanceof UILayout) {
			UILayout layout = (UILayout) parent;
			sortPanels(layout);
		} else {
			throw new FacesException("Layout panel should be children of UILayout pomponent");
		}
	}

	/**
	 * @param layout
	 * @throws FacesException
	 */
	public void sortPanels(UILayout layout) throws FacesException {
		this.deep = layoutDeep(layout);
		for (UIComponent child : layout.getChildren()) {
			if (child instanceof UILayoutPanel && child.isRendered()) {
				UILayoutPanel layoutPanel = (UILayoutPanel) child;
				LayoutPosition position = layoutPanel.getPosition();
				if (LayoutPosition.top.equals(position)) {
					if (null != top) {
						throw new FacesException(
								"Duplicate layoutPanel's with same position"
										+ position);
					}
					top = layoutPanel;
				} else if (LayoutPosition.bottom.equals(position)) {
					if (null != bottom) {
						throw new FacesException(
								"Duplicate layoutPanel's with same position"
										+ position);
					}
					bottom = layoutPanel;
				} else if (LayoutPosition.left.equals(position)) {
					if (null != left) {
						throw new FacesException(
								"Duplicate layoutPanel's with same position"
										+ position);
					}
					left = layoutPanel;
				} else if (LayoutPosition.right.equals(position)) {
					if (null != right) {
						throw new FacesException(
								"Duplicate layoutPanel's with same position"
										+ position);
					}
					right = layoutPanel;
				} else if (null == position
						|| LayoutPosition.center.equals(position)) {
					if (null != center) {
						throw new FacesException(
								"Duplicate layoutPanel's with same position"
										+ position);
					}
					center = layoutPanel;
				} else {

				}
			}

		}
		// Reorganise central row.
		if(null == left && null != center){
			left = center;
			center = null;
		}
		if(null == left && null != right){
			left = right;
			right = null;
		}
		if(null == center && null != right){
			center = right;
			right = null;
		}
		this.columns = new ArrayList<Column>(3);
		if(null != left){
			columns.add(new Column(left));
		}
		if(null != center){
			columns.add(new Column(center));
		}
		if(null != right){
			columns.add(new Column(right));
		}
	}

	/**
	 * @throws NumberFormatException
	 */
	public void calculateWidth() throws NumberFormatException {
		// calculate widths.
		if(getColumns()>0){
			float totalPercent = 0.0f;
			int numOfPercentColumns = 0;
			float totalPart = 0.0f;
			int numOfPartColumns = 0;
			// Collect width information.
			for (Column column : columns) {
				String width = column.panel.getWidth();
				if(null == width || 0 ==width.length()){
					column.partWidth = 1.0f;
					totalPart += column.partWidth;
					numOfPartColumns++;
				} else if(width.startsWith("*")){
					column.partWidth = Float.parseFloat(width.substring(1));
					totalPart += column.partWidth;
					numOfPartColumns++;
				} else if(width.endsWith("%")) {
					column.percentWidth = Float.parseFloat(width.substring(0,width.length()-1));
					totalPercent += column.percentWidth;
					numOfPercentColumns++;
				} 
			}
			float partToPercent=0.0f;
			float percentCoefficient = 1.0f;
			if(numOfPartColumns>0 && numOfPercentColumns >0){
				float rest = 100.00f - totalPercent;
				partToPercent = rest/totalPart;
			} else if(numOfPartColumns>0){
				partToPercent = 100.00f/totalPart;
			} else if(numOfPercentColumns == columns.size()){
				percentCoefficient = 100.00f/totalPercent;
			}
			// TODO - calculate precisious coefficient.
			percentCoefficient*=0.98f;
			// Recalculate width.
			for (Column column : columns) {
				if(column.partWidth>0.0f){
					column.percentWidth = column.partWidth*partToPercent;
				}
				column.percentWidth = column.percentWidth*percentCoefficient;
			}
		}
	}

	/**
	 * @return the top
	 */
	public UILayoutPanel getTop() {
		return top;
	}

	/**
	 * @return the bottom
	 */
	public UILayoutPanel getBottom() {
		return bottom;
	}

	/**
	 * @return the left
	 */
	public UILayoutPanel getLeft() {
		return left;
	}

	/**
	 * @return the right
	 */
	public UILayoutPanel getRight() {
		return right;
	}

	/**
	 * @return the center
	 */
	public UILayoutPanel getCenter() {
		return center;
	}

	/**
	 * @return the columns
	 */
	public int getColumns() {
		return columns.size();
	}
	
	/**
	 */
	public String getWidth(UILayoutPanel panel){
		return getWidth(panel,1.0f);
	}

	/**
	 * @param panel
	 * @param coeeficient
	 * @return
	 */
	public String getWidth(UILayoutPanel panel, float coeeficient){
		if(this.top == panel || this.bottom == panel){
			return panel.getWidth();
		} else {
			for (Column column : columns) {
				if(column.panel == panel){
					if(column.percentWidth>0){
						return String.format((Locale)null, "%2.2f%%", column.percentWidth*coeeficient);
					} else {
						return panel.getWidth();
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * Calculate deep of layout components.
	 * @param component
	 * @return
	 */
	protected int layoutDeep(UIComponent component) {
		int deep = 0;
		if(null != component){
			deep = layoutDeep(component.getParent());
			if(component instanceof UILayout){
				deep++;
			}
		}
		return deep;
	}

	
	static class Column {
		private final UILayoutPanel panel;
		private float percentWidth = 0.0f;
		private float partWidth = 0.0f;
		
		public Column(UILayoutPanel panel) {
			this.panel = panel;
		}
		
		public String getWidth(){
			if(percentWidth>0.0f){
				return String.format((Locale)null, "%2.2f%%", percentWidth);
			} else {
				return panel.getWidth();
			}
		}

		/**
		 * @return the percentWidth
		 */
		public float getPercentWidth() {
			return percentWidth;
		}
	}


	/**
	 * @return the deep
	 */
	public int getDeep() {
		return deep;
	}
}
