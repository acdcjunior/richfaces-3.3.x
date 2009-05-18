/**
 * License Agreement.
 *
 * Rich Faces - Natural Ajax for Java Server Faces (JSF)
 *
 * Copyright (C) 2007 Exadel, Inc.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License version 2.1 as published by the Free Software Foundation.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301  USA
 */

package org.ajax4jsf.model;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;

/**
 * @author shura
 *
 */
public class SequenceDataModel extends ExtendedDataModel {
	
	private DataModel wrappedModel;

	/**
	 * @param wrapped
	 */
	public SequenceDataModel(DataModel wrapped) {
		super();
		this.wrappedModel = wrapped;
	}

	/* (non-Javadoc)
	 * @see org.ajax4jsf.ajax.repeat.ExtendedDataModel#dataIterator(org.ajax4jsf.ajax.repeat.Range)
	 */
/*	public Iterator dataIterator(Range range) {
		final SequenceRange seqRange = (SequenceRange) range;
		int rows = seqRange.getRows();
		int rowCount = wrapped.getRowCount();
		final int firstRow = seqRange.getFirstRow();
		if(rows > 0){
			rows += firstRow;
			if(rowCount >=0){
				rows = Math.min(rows, rowCount);
			}
		} else if(rowCount >=0 ){
			rows = rowCount;
		} 
		final int maxRow = rows;
		return new Iterator(){
			
			int rowIndex = firstRow;

			public boolean hasNext() {
				// TODO Auto-generated method stub
				return maxRow < 0 || rowIndex < maxRow;
			}

			public Object next() {
				// TODO Auto-generated method stub
				return new Integer(rowIndex++);
			}

			public void remove() {
				throw new IllegalStateException();				
			}
			
		};
	}
*/	
	public void walk(FacesContext context, DataVisitor visitor, Range range, Object argument) throws IOException {
		final SequenceRange seqRange = (SequenceRange) range;
		int rows = seqRange.getRows();
		int rowCount = wrappedModel.getRowCount();
		int currentRow = seqRange.getFirstRow();
		if(rows > 0){
			rows += currentRow;
			if(rowCount >=0){
				rows = Math.min(rows, rowCount);
			}
		} else if(rowCount >=0 ){
			rows = rowCount;
		} else {
			rows = -1;
		}
		while (rows < 0 || currentRow < rows) {
			wrappedModel.setRowIndex(currentRow);
			if(wrappedModel.isRowAvailable()){
				visitor.process(context, new Integer(currentRow), argument);
			} else {
				break;
			}
			currentRow++;
		}
		
	}

	/* (non-Javadoc)
	 * @see org.ajax4jsf.ajax.repeat.ExtendedDataModel#getRowKey()
	 */
	public Object getRowKey() {
		int index = wrappedModel.getRowIndex();
		if(index<0){
			return null;
		}
		return new Integer(index);
	}


	/* (non-Javadoc)
	 * @see org.ajax4jsf.ajax.repeat.ExtendedDataModel#setRowKey(java.lang.Object)
	 */
	public void setRowKey(Object key) {
		if(null == key){
			wrappedModel.setRowIndex(-1);
		} else {
			Integer index = (Integer) key;
			wrappedModel.setRowIndex(index.intValue());
		}
	}

	/* (non-Javadoc)
	 * @see javax.faces.model.DataModel#getRowCount()
	 */
	public int getRowCount() {
		// TODO Auto-generated method stub
		return wrappedModel.getRowCount();
	}

	/* (non-Javadoc)
	 * @see javax.faces.model.DataModel#getRowData()
	 */
	public Object getRowData() {
		// TODO Auto-generated method stub
		return wrappedModel.getRowData();
	}

	/* (non-Javadoc)
	 * @see javax.faces.model.DataModel#getRowIndex()
	 */
	public int getRowIndex() {
		// TODO Auto-generated method stub
		return wrappedModel.getRowIndex();
	}

	/* (non-Javadoc)
	 * @see javax.faces.model.DataModel#getWrappedData()
	 */
	public Object getWrappedData() {
		// TODO Auto-generated method stub
		return wrappedModel.getWrappedData();
	}

	/* (non-Javadoc)
	 * @see javax.faces.model.DataModel#isRowAvailable()
	 */
	public boolean isRowAvailable() {
		// TODO Auto-generated method stub
		return wrappedModel.isRowAvailable();
	}

	/* (non-Javadoc)
	 * @see javax.faces.model.DataModel#setRowIndex(int)
	 */
	public void setRowIndex(int rowIndex) {
		wrappedModel.setRowIndex(rowIndex);
	}

	/* (non-Javadoc)
	 * @see javax.faces.model.DataModel#setWrappedData(java.lang.Object)
	 */
	public void setWrappedData(Object data) {
		wrappedModel.setWrappedData(data);
	}

	/**
	 * @return the wrappedModel
	 */
	protected DataModel getWrappedModel() {
		return this.wrappedModel;
	}

	/**
	 * @param wrappedModel the wrappedModel to set
	 */
	protected void setWrappedModel(DataModel wrappedModel) {
		this.wrappedModel = wrappedModel;
	}

}
