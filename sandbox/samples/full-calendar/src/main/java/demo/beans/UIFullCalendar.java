package demo.beans;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.FacesEvent;

import org.ajax4jsf.context.AjaxContext;
import org.ajax4jsf.javascript.JSFunction;
import org.ajax4jsf.javascript.JSFunctionDefinition;
import org.ajax4jsf.javascript.JSObject;
import org.ajax4jsf.javascript.JSReference;
import org.ajax4jsf.renderkit.AjaxRendererUtils;
import org.ajax4jsf.renderkit.RendererUtils.HTML;

public class UIFullCalendar extends UIComponentBase {

	private static final String CALLBACK = "callback";
	private static final String END_DATE = "endDate";
	private static final String START_DATE = "startDate";

	@Override
	public String getFamily() {
		return null;
	}

	private Object createAjaxFunction(FacesContext context) {
		JSFunction ajaxFunction = AjaxRendererUtils.buildAjaxFunction(this,
				context);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put(START_DATE, new JSReference(START_DATE));
		params.put(END_DATE, new JSReference(END_DATE));

		Map<String, Object> eventOptions = AjaxRendererUtils.buildEventOptions(
				context, this, params, true);
		eventOptions.put("oncomplete", new JSReference(CALLBACK));

		ajaxFunction.addParameter(eventOptions);

		return new JSFunctionDefinition("event", START_DATE, END_DATE, CALLBACK)
				.addToBody(ajaxFunction);
	}

	@Override
	public void decode(FacesContext context) {
		if (!this.isRendered()) {
			return;
		}

		Map<String, String> requestParameterMap = context.getExternalContext()
				.getRequestParameterMap();
		if (requestParameterMap.get(this.getClientId(context)) != null) {
			String startDateParam = requestParameterMap.get(START_DATE);
			String endDateParam = requestParameterMap.get(END_DATE);

			try {
				Date startDate = new Date(Long.parseLong(startDateParam));
				Date endDate = new Date(Long.parseLong(endDateParam));

				new FullCalendarAjaxEvent(this, startDate, endDate).queue();
			} catch (NumberFormatException e) {
				// TODO: handle exception
			}
		}
	}

	@Override
	public void broadcast(FacesEvent event) throws AbortProcessingException {
		super.broadcast(event);

		if (event instanceof FullCalendarAjaxEvent) {
			FullCalendarAjaxEvent calendarAjaxEvent = (FullCalendarAjaxEvent) event;

			FacesContext facesContext = getFacesContext();
			AjaxContext ajaxContext = AjaxContext
					.getCurrentInstance(facesContext);

			ajaxContext.setResponseData(getCalendarData(calendarAjaxEvent
					.getStartDate(), calendarAjaxEvent.getEndDate()));
		}
	}

	private Object getCalendarData(Date startDate, Date endDate) {

//		$year = date('Y');
//		$month = date('m');
//
//		echo json_encode(array(
//		
//			array(
//				'id' => 111,
//				'title' => "Event1",
//				'start' => "$year-$month-10",
//				'url' => "http://yahoo.com/"
//			),
//			
//			array(
//				'id' => 222,
//				'title' => "Event2",
//				'start' => "$year-$month-20",
//				'end' => "$year-$month-22",
//				'url' => "http://yahoo.com/"
//			)
//		
//		));		

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
		format.setLenient(false);
		
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		
		Map<String, Object> firstDataElement = new HashMap<String, Object>();
		
		firstDataElement.put("id", 111);
		firstDataElement.put("title", "Event 1");
		Calendar calendar = Calendar.getInstance();

		calendar.set(Calendar.DATE, 10);
		
		firstDataElement.put("start", format.format(calendar.getTimeInMillis()));
		firstDataElement.put("url", "http://www.yahoo.com");
		
		data.add(firstDataElement);
		
		Map<String, Object> secondDataElement = new HashMap<String, Object>();
		
		secondDataElement.put("id", 222);
		secondDataElement.put("title", "Event 2");
		calendar.set(Calendar.DATE, 20);
		secondDataElement.put("start", format.format(calendar.getTimeInMillis()));
		calendar.set(Calendar.DATE, 22);
		secondDataElement.put("end", format.format(calendar.getTimeInMillis()));
		secondDataElement.put("url", "http://www.yahoo.com");
		
		data.add(secondDataElement);

		return data;
	}

	@Override
	public void encodeEnd(FacesContext context) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		String clientId = this.getClientId(context);

		writer.startElement(HTML.DIV_ELEM, this);
		writer.writeAttribute(HTML.id_ATTRIBUTE, clientId, HTML.id_ATTRIBUTE);
		writer.writeAttribute(HTML.class_ATTRIBUTE, "rich-fullc",
				HTML.class_ATTRIBUTE);

		UIComponent loadingFacet = getFacet("loading");
		if (loadingFacet != null && loadingFacet.isRendered()) {
			writer.startElement(HTML.DIV_ELEM, this);
			writer.writeAttribute(HTML.id_ATTRIBUTE, clientId + ":loading",
					null);
			writer.writeAttribute(HTML.class_ATTRIBUTE, "rich-fullc-loading",
					null);

			loadingFacet.encodeAll(context);

			writer.endElement(HTML.DIV_ELEM);
		}

		writer.startElement(HTML.SCRIPT_ELEM, this);
		writer
				.writeAttribute(HTML.TYPE_ATTR, "text/javascript",
						HTML.TYPE_ATTR);
		writer.writeText(new JSObject("RichFaces.FullCalendar", clientId,
				createAjaxFunction(context)).toScript(), null);
		writer.endElement(HTML.SCRIPT_ELEM);

		writer.endElement(HTML.DIV_ELEM);
	}

}
