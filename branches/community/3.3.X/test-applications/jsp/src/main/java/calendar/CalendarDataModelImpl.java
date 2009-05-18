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

package calendar;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import javax.faces.application.FacesMessage;
import javax.faces.validator.ValidatorException;

import org.richfaces.model.CalendarDataModel;
import org.richfaces.model.CalendarDataModelItem;

/**
 * @author Nick Belaevski - mailto:nbelaevski@exadel.com
 * created 30.06.2007
 *
 */
public class CalendarDataModelImpl implements CalendarDataModel {

	/* (non-Javadoc)
	 * @see org.richfaces.component.CalendarDataModel#getData(java.util.Date[])
	 */
	public CalendarDataModelItem[] getData(Date[] dateArray) {
		if (dateArray == null) {
			return null;
		}
		
		CalendarDataModelItem[] items = new CalendarDataModelItem[dateArray.length];
		for (int i = 0; i < dateArray.length; i++) {
			items[i] = createDataModelItem(dateArray[i]);
		}

		return items;
	}

	protected CalendarDataModelItem createDataModelItem(Date date) {
		CalendarDataModelItemImpl item = new CalendarDataModelItemImpl();
		Map data = new HashMap();
		DateFormat enFormatter = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.ENGLISH);
		DateFormat frFormatter = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.FRENCH);
		DateFormat deFormatter = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.GERMAN);
		data.put("enLabel", enFormatter.format(date));
		data.put("frLabel", frFormatter.format(date));
		data.put("deLabel", deFormatter.format(date));
		/*Calendar c = Calendar.getInstance();
		c.setTime(date);
		item.setDay(c.get(Calendar.DAY_OF_MONTH));*/
		
//		if (new Random().nextInt(10) > 5) {
//			item.setEnabled(true);
//		} else {
//			item.setEnabled(false);
//		}
		
		item.setData(data);
		if (date != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			int d = calendar.get(Calendar.DATE);
			if (d == 7 || d == 15) {
				item.setEnabled(false);
			} else item.setEnabled(true);
		}
		System.out.println(item.getData() + " " + item.isEnabled());
		
		return item;
	}

	/* (non-Javadoc)
	 * @see org.richfaces.component.CalendarDataModel#getToolTip(java.util.Date)
	 */
	public Object getToolTip(Date date) {
	
		return null;
	}

}
