<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<script type="text/javascript">
function multiply(height,width){
	var tbody = document.getElementById('tbodyID');
	var y = parseInt(height);
	var x = parseInt(width);	
	tbody.innerHTML = 'modalPanel\'s size: ' + x + 'x' +y + ' px*px';
	return false;	
}
</script>
<style>
.dr-mpnl-shadow {
	top: 5px;
	left: 5px;
	opacity: 0.9;
	filter: alpha(opacity =     200);
}
</style>

<f:subview id="modalPanelSubviewID">

	<rich:modalPanel id="modalPanelID"
		binding="#{modalPanel.htmlModalPanel}"
		visualOptions="#{modalPanel.visualOptions}" left="#{modalPanel.left}"
		top="#{modalPanel.top}" height="#{modalPanel.height}"
		width="#{modalPanel.width}" moveable="#{modalPanel.moveable}"
		resizeable="#{modalPanel.resizeable}"
		keepVisualState="#{modalPanel.keepVisualState}"
		rendered="#{modalPanel.rendered}" zindex="#{modalPanel.zindex}"
		autosized="#{modalPanel.autosized}"
		shadowDepth="#{modalPanel.shadowDepth}"
		shadowOpacity="#{modalPanel.shadowOpacity}"
		showWhenRendered="#{modalPanel.showWhenRendered}"
		trimOverlayedElements="#{modalPanel.trimOverlayedElements}"
		controlsClass="#{style.controlsClass}"
		headerClass="#{style.headerClass}" styleClass="#{style.styleClass}"
		onhide="#{event.onhide}" onmaskclick="#{event.onmaskclick}"
		onmaskcontextmenu="#{event.onmaskcontextmenu}"
		onmaskdblclick="#{event.onmaskdblclick}"
		onmaskmousedown="#{event.onmaskmousedown}"
		onmaskmousemove="#{event.onmaskmousemove}"
		onmaskmouseout="#{event.onmaskmouseout}"
		onmaskmouseover="#{event.onmaskmouseover}"
		onmaskmouseup="#{event.onmaskmouseup}" onmove="#{event.onmove}"
		onresize="#{event.onresize}" onshow="#{event.onshow}"
		onbeforehide="#{event.onbeforehide}"
		onbeforeshow="#{event.onbeforeshow}"
		tridentIVEngineSelectBehavior="hide"
		domElementAttachment="#{modalPanel.domElementAttachment}"
		label="panel1">
		<f:facet name="header">
			<h:outputText value="HEADER for PANEL1" />
		</f:facet>
		<f:facet name="controls">
			<h:graphicImage value="/pics/error.gif"
				onclick="Richfaces.hideModalPanel('modalPanelID'); return false;" />
		</f:facet>
		<h:outputText value="selectOneMenu inside the MODALPanel: " />
		<h:selectOneMenu value="#{richBean.srcContainer}">
			<f:selectItems value="#{richBean.listContainer}" />
		</h:selectOneMenu>
		<rich:separator></rich:separator>
		<h:inputText value="#{modalPanel.inputTextTest}" />
		<br />
		<h:selectOneListbox value="#{modalPanel.selectOneListboxTest}">
			<f:selectItem itemLabel="1" itemValue="1" />
			<f:selectItem itemLabel="2" itemValue="2" />
			<f:selectItem itemLabel="3" itemValue="3" />
		</h:selectOneListbox>
		<rich:separator></rich:separator>
		<rich:spacer height="20"></rich:spacer>
		<h:outputLink value="http://localhost:8081/jsp/">
			<f:verbatim>http://localhost:8081/jsp/</f:verbatim>
		</h:outputLink>
		<rich:separator></rich:separator>
		<br />
		<br />
		<a4j:commandLink
			onclick="Richfaces.showModalPanel('modalPanel2ID');return false;"
			value="show ModalPanel 2"></a4j:commandLink>
		<br />
		<br />
		<h:outputLink
			onclick="Richfaces.hideModalPanel('modalPanelID');return false;">
			<f:verbatim>Close</f:verbatim>
		</h:outputLink>
		<br />
		<h:outputLink onclick="Richfaces.hideTopModalPanel();return false;">
			<f:verbatim>Close Top Panel</f:verbatim>
		</h:outputLink>
	</rich:modalPanel>

	<rich:modalPanel id="modalPanel2ID" autosized="true" resizeable="false">
		<f:facet name="header">
			<h:outputText value="HEADER for PANEL2" />
		</f:facet>
		<f:facet name="controls">
			<h:graphicImage value="/pics/error.gif"
				onclick="Richfaces.hideModalPanel('modalPanel2ID'); return false;" />
		</f:facet>
		<h:inputText value="input"></h:inputText>
		<br />
		<h:outputLink
			onclick="Richfaces.hideModalPanel('modalPanel2ID');return false;">
			<f:verbatim>Close</f:verbatim>
		</h:outputLink>
		<br />
		<h:outputLink onclick="Richfaces.hideTopModalPanel();return false;">
			<f:verbatim>Close Top Panel</f:verbatim>
		</h:outputLink>
		<rich:pickList id="pickListID">
			<f:selectItem itemValue="selectItem 0" itemLabel="selectItem 0" />
			<f:selectItem itemValue="selectItem 1" itemLabel="selectItem 1" />
			<f:selectItem itemValue="selectItem 2" itemLabel="selectItem 2" />
		</rich:pickList>
		<br />
		<a4j:commandButton reRender="pickListID" value="refresh target"></a4j:commandButton>
	</rich:modalPanel>

	<a onclick="Richfaces.showModalPanel('modalPanelID');" href="#">show
	ModalPanel 1</a>
	<br />
	<a4j:commandLink
		onclick="Richfaces.showModalPanel('modalPanel2ID');return false;"
		value="show ModalPanel 2"></a4j:commandLink>
	<br />
	<rich:spacer height="20"></rich:spacer>
	<br />
	<div id="idDiv1" align="center"
		style="position: relative; font-size: 50px; z-index: 2; color: navy">z-index1</div>
	<div id="idDiv2" align="center"
		style="position: relative; font-size: 50px; z-index: 2; color: navy">z-index2</div>

	<%-- <div style="FONT-WEIGHT: bold;">rich:findComponent</div>
		<h:panelGrid columns="2" id="findComponentID">
			<a4j:commandLink value="getSize"
				onclick="multiply(#{rich:findComponent('modalPanelID').height},#{rich:findComponent('modalPanelID').width});" />
			<div id="tbodyID"></div>
		</h:panelGrid>
		<rich:separator></rich:separator> --%>
</f:subview>
