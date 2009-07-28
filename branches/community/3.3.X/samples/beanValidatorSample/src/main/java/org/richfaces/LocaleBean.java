/**
 * 
 */
package org.richfaces;

import java.util.Locale;

/**
 * @author asmirnov
 *
 */
public class LocaleBean {
	
	private Locale current = Locale.ENGLISH;
	
	private String language = "en";

	/**
	 * @return the current
	 */
	public Locale getCurrent() {
		return current;
	}

	/**
	 * @param current the current to set
	 */
	public void setCurrent(Locale current) {
		this.current = current;
	}

	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * @param language the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}
	
	
	public String select(){
		setCurrent(new Locale(getLanguage()));
		return null;
	}
	

}
