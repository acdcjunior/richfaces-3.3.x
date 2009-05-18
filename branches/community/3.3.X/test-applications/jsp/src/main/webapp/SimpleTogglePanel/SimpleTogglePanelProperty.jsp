<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<f:subview id="stpPropertySubviewID">
	<h:commandButton value="add test"
		action="#{simpleTogglePanel.addHtmlSimpleTogglePanel}"></h:commandButton>
	<h:panelGrid columns="2" border="1">
		<h:outputText value="Width:">
		</h:outputText>
		<h:inputText value="#{simpleTogglePanel.width}">
			<a4j:support event="onchange" reRender="sTP,sTP1"></a4j:support>
		</h:inputText>

		<h:outputText value="Height:">
		</h:outputText>
		<h:inputText value="#{simpleTogglePanel.height}">
			<a4j:support event="onchange" reRender="sTP,sTP1"></a4j:support>
		</h:inputText>

		<h:outputText value="Switch Type:"></h:outputText>
		<h:selectOneRadio value="#{simpleTogglePanel.switchType}">
			<f:selectItem itemLabel="client" itemValue="client" />
			<f:selectItem itemLabel="server" itemValue="server" />
			<f:selectItem itemLabel="ajax" itemValue="ajax" />
			<a4j:support event="onclick" reRender="sTP,sTP1,sTP2"></a4j:support>
		</h:selectOneRadio>

		<h:outputText value="Rendered:"></h:outputText>
		<h:selectBooleanCheckbox value="#{simpleTogglePanel.rendered}"
			onclick="submit()">
		</h:selectBooleanCheckbox>
	</h:panelGrid>
	<br />
	<br />
	<%--
	<div style="FONT-WEIGHT: bold;">rich:findComponent</div>
	<h:panelGrid columns="2">
		<rich:column>
			<a4j:commandLink value="getValue(for 2nd stPanel)" reRender="findID"></a4j:commandLink>
		</rich:column>
		<rich:column id="findID">
			<h:outputText value="#{rich:findComponent('sTP').value}" />
		</rich:column>
	</h:panelGrid>
	--%>
</f:subview>