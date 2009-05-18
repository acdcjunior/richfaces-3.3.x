/**
 * 
 */
package org.richfaces.helloworld.domain.validator;

import org.hibernate.validator.Length;
import org.hibernate.validator.Max;
import org.hibernate.validator.Min;
import org.hibernate.validator.NotEmpty;
import org.hibernate.validator.NotNull;

/**
 * @author asmirnov
 *
 */
public class MinMaxBean implements Validable {
	
	private String text;
	
	@Min(2)
	@Max(10)
	private int intValue;

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the intValue
	 */
	public int getIntValue() {
		return intValue;
	}

	/**
	 * @param intValue the intValue to set
	 */
	public void setIntValue(int intValue) {
		this.intValue = intValue;
	}

	public String getTextDescription() {
		return "Text Value, no restrictions";
	}

	public String getIntDescription() {
		// TODO Auto-generated method stub
		return "Integer Value, valid values from 2 to 10";
	}

}
