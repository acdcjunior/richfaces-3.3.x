/**
 * 
 */
package org.richfaces.component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import org.richfaces.validator.ValidableBean;

/**
 * @author asmirnov
 *
 */
public class CloneableBean implements Cloneable {
	
	private ValidableBean testBean;
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((testBean == null) ? 0 : testBean.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof CloneableBean))
			return false;
		CloneableBean other = (CloneableBean) obj;
		if (testBean == null) {
			if (other.testBean != null)
				return false;
		} else if (!testBean.equals(other.testBean))
			return false;
		return true;
	}

	public CloneableBean() {
		this.testBean = new ValidableBean();
	}

	/**
	 * @return the testBean
	 */
	public ValidableBean getTestBean() {
		return testBean;
	}

	/**
	 * @param testBean the testBean to set
	 */
	public void setTestBean(ValidableBean testBean) {
		this.testBean = testBean;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		CloneableBean clone = (CloneableBean) super.clone();
		clone.testBean = new ValidableBean();
		clone.testBean.setFoo(testBean.getFoo());
		clone.testBean.setArray(testBean.getArray().clone());
		clone.testBean.setIntegerProperty(testBean.getIntegerProperty());
		clone.testBean.setList(new ArrayList<String>(testBean.getList()));
		clone.testBean.setMap(new HashMap<String, String>(testBean.getMap()));
		return clone;
	}
}
