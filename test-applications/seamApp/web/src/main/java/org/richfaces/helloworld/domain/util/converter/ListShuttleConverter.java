package org.richfaces.helloworld.domain.util.converter;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.richfaces.helloworld.domain.util.data.Data;

public class ListShuttleConverter implements Converter {

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		String [] str = arg2.split(":");
		return new Data(Integer.parseInt(str[0]), str[1], str[2], str[3],str[4]);
	}
//list.add(new Data(i, "Button " + i, "Link " + i, "select" +(i % 5), statusIcon[i % 5]));
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		Data data = (Data)arg2;
		return data.getInt0() + ":" + data.getStr0()  + ":" + data.getStr1() + ":" +  data.getStr2() + ":" + data.getStr3();
	}

}
//	public Object getAsObject(FacesContext context, UIComponent component,
//			String value) {
//		int index = value.indexOf(':');
//		return new OptionItem(value.substring(0, index), Integer.valueOf(value.substring(index + 1)));
//	}
//	public String getAsString(FacesContext context, UIComponent component,
//			Object value) {
//		OptionItem optionItem = (OptionItem) value;
//		return optionItem.getName() + ":" + optionItem.getPrice();
//	}
