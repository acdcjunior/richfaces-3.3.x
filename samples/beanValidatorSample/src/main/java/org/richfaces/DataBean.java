/**
 * 
 */
package org.richfaces;

import java.util.ArrayList;
import java.util.List;

import org.ajax4jsf.event.AjaxEvent;
import org.hibernate.validator.Max;
import org.hibernate.validator.Valid;

/**
 * @author asmirnov
 *
 */
public class DataBean  {
	
	private final List<Validable> beans;
	
	/**
	 * @return the beans
	 */
	@Valid
	public List<Validable> getBeans() {
		return beans;
	}

	public DataBean() {
		beans = new ArrayList<Validable>(6);
		beans.add(new NotNullBean());
		beans.add(new NotEmptyBean());
		beans.add(new LengthBean());
		beans.add(new MinBean());
		beans.add(new MaxBean());
		beans.add(new MinMaxBean());
	}

	@Max(value=20,message="Total value should be less then 20")
	public int getTotal(){
		int total = 0;
		for (Validable bean : beans) {
			total += bean.getIntValue();
		}
		return total;
	}

	public void ajaxListener(AjaxEvent ajaxEvent) {
		System.out.println("DataBean.ajaxListener()");
	}

}
