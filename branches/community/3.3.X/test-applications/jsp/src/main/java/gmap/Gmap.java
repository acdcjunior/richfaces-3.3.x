package gmap;

import org.richfaces.component.html.HtmlGmap;

import util.componentInfo.ComponentInfo;

/**
 * @author user
 *
 */
public class Gmap {

	private String continuousZoom;
	private String doubleClickZoom;
	private String dragging;
	private String enableInfoWindow;
	private String lat;
	private String lng;
	private String mapType;
	private String showGLargeMapControl;
	private String showGMapTypeControl;
	private String showGScaleControl;
	private String warningMessage;
	private String zoom;
	private String locale;
	private boolean rendered;
	private HtmlGmap htmlGmap = null;
	
	public void addHtmlGmap(){
		ComponentInfo info = ComponentInfo.getInstance();
		info.addField(htmlGmap);
	}

	public HtmlGmap getHtmlGmap() {
		return htmlGmap;
	}

	public void setHtmlGmap(HtmlGmap htmlGmap) {
		this.htmlGmap = htmlGmap;
	}

	public Gmap() {
		locale = "en";
		zoom = "17";
		mapType ="G_NORMAL_MAP";
		lng = "44.44";
		lat = "37.37";
		warningMessage = "warning!!!";
		showGLargeMapControl = "true";
		showGMapTypeControl = "true";
		showGScaleControl = "true";
		enableInfoWindow = "true";
		rendered = true;  
		continuousZoom = "false";
		doubleClickZoom = "false";
		dragging = "false";
	}

	public String act() {
		zoom = "17";
		System.out.println("zoom=" + zoom);
		return null;
	}

	public String getContinuousZoom() {
		return continuousZoom;
	}

	public void setContinuousZoom(String continuousZoom) {
		this.continuousZoom = continuousZoom;
	}

	public String getDoubleClickZoom() {
		return doubleClickZoom;
	}

	public void setDoubleClickZoom(String doubleClickZoom) {
		this.doubleClickZoom = doubleClickZoom;
	}

	public String getDragging() {
		return dragging;
	}

	public void setDragging(String dragging) {
		this.dragging = dragging;
	}

	public String getEnableInfoWindow() {
		return enableInfoWindow;
	}

	public void setEnableInfoWindow(String enableInfoWindow) {
		this.enableInfoWindow = enableInfoWindow;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getMapType() {
		return mapType;
	}

	public void setMapType(String mapType) {
		this.mapType = mapType;
	}

	public String getShowGLargeMapControl() {
		return showGLargeMapControl;
	}

	public void setShowGLargeMapControl(String showGLargeMapControl) {
		this.showGLargeMapControl = showGLargeMapControl;
	}

	public String getShowGMapTypeControl() {
		return showGMapTypeControl;
	}

	public void setShowGMapTypeControl(String showGMapTypeControl) {
		this.showGMapTypeControl = showGMapTypeControl;
	}

	public String getShowGScaleControl() {
		return showGScaleControl;
	}

	public void setShowGScaleControl(String showGScaleControl) {
		this.showGScaleControl = showGScaleControl;
	}

	public String getWarningMessage() {
		return warningMessage;
	}

	public void setWarningMessage(String warningMessage) {
		this.warningMessage = warningMessage;
	}

	public String getZoom() {
		return zoom;
	}

	public void setZoom(String zoom) {
		this.zoom = zoom;
	}

	public boolean isRendered() {
		return rendered;
	}

	public void setRendered(boolean rendered) {
		this.rendered = rendered;
	}

	/**
	 * @return the locale
	 */
	public String getLocale() {
		return locale;
	}

	/**
	 * @param locale the locale to set
	 */
	public void setLocale(String locale) {
		this.locale = locale;
	}
}
