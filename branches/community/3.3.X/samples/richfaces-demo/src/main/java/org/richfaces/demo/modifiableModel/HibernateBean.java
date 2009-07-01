/**
 * 
 */
package org.richfaces.demo.modifiableModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.faces.FacesException;

import org.hibernate.Session;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Startup;
import org.jboss.seam.log.Log;

/**
 * @author Nick Belaevski
 * @since 3.3.1
 */
@Scope(ScopeType.APPLICATION)
@Startup
@Name("hibernateBean")
public class HibernateBean {

	@Logger
	private Log log;
	
	@In
	private Session hibernateSession;
	
	private static final String[] CSV_FIELDS = {
		"key", "summary", "assignee", "fixVersion", "reporter", "priority", "status",
		"resolution", "created", "updated"
	};
	
	private static final Method[] CSV_FIELDS_SETTERS;
	
	public String[] getCsvFields() {
		return CSV_FIELDS;
	}
	
	static {
		CSV_FIELDS_SETTERS = new Method[CSV_FIELDS.length];

		for (int i = 0; i < CSV_FIELDS.length; i++) {
			char[] cs = CSV_FIELDS[i].toCharArray();
			cs[0] = Character.toUpperCase(cs[0]);
			
			try {
				CSV_FIELDS_SETTERS[i] = DataItem.class.getMethod("set" + new String(cs), String.class);
			} catch (SecurityException e) {
				throw new FacesException(e.getMessage(), e);
			} catch (NoSuchMethodException e) {
				throw new FacesException(e.getMessage(), e);
			}
		}
	}
	
	@Create
	public void fillDatabase() {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(
				new InputStreamReader(getClass().getResourceAsStream("/JIRA.csv")));

			String line;
			
			while ((line = reader.readLine()) != null) {
				String[] split = line.split(";");

				DataItem dataItem = new DataItem();
				for (int i = 0; i < split.length && i < CSV_FIELDS_SETTERS.length; i++) {
					try {
						CSV_FIELDS_SETTERS[i].invoke(dataItem, split[i]);
					} catch (IllegalArgumentException e) {
						log.error(e.getMessage(), e);
					} catch (IllegalAccessException e) {
						log.error(e.getMessage(), e);
					} catch (InvocationTargetException e) {
						log.error(e.getMessage(), e);
					}
				}
				
				hibernateSession.persist(dataItem);
			}
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				log.error(e.getMessage(), e);
			}
		}
		
		hibernateSession.flush();
	}

}
