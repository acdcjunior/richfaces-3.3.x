<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<f:subview id="SeparatorPropertySubviewID">
<h:commandButton value="add test" action="#{separator.addHtmlSeparator}"></h:commandButton>
	<h:panelGrid columns="2">
		<h:outputText value="Width (px or %): "></h:outputText>
		<h:inputText value="#{separator.width}">
			<a4j:support event="onchange" reRender="separatorId"></a4j:support>
		</h:inputText>

		<h:outputText value="Height (px or %):"></h:outputText>
		<h:inputText value="#{separator.height}">
			<a4j:support event="onchange" reRender="separatorId"></a4j:support>
		</h:inputText>

		<h:outputText value="LineType:"></h:outputText>
		<h:selectOneRadio value="#{separator.lineType}" layout="pageDirection">
			<f:selectItem itemLabel="beveled" itemValue="beveled" />
			<f:selectItem itemLabel="dotted" itemValue="dotted" />
			<f:selectItem itemLabel="dashed" itemValue="dashed" />
			<f:selectItem itemLabel="double" itemValue="double" />
			<f:selectItem itemLabel="solid" itemValue="solid" />
			<a4j:support event="onclick" reRender="separatorId"></a4j:support>
		</h:selectOneRadio>

		<h:outputText value="Align:"></h:outputText>
		<h:selectOneRadio value="#{separator.align}">
			<f:selectItem itemLabel="left" itemValue="left" />
			<f:selectItem itemLabel="center" itemValue="center" />
			<f:selectItem itemLabel="right" itemValue="right" />
			<a4j:support event="onclick" reRender="separatorId"></a4j:support>
		</h:selectOneRadio>

		<h:outputText value="Title:"></h:outputText>
		<h:inputText value="#{separator.title}">
			<a4j:support event="onchange" reRender="separatorId"></a4j:support>
		</h:inputText>

		<h:outputText value="Rendered:"></h:outputText>
		<h:selectBooleanCheckbox value="#{separator.rendered}"
			onclick="submit()"></h:selectBooleanCheckbox>

		<h:outputText value="Switch Styles:" />
		<h:commandButton action="#{separator.doStyles}"
			value="#{separator.btnLabel}" />
	</h:panelGrid>
	<br />
	<br />
	<div style="FONT-WEIGHT: bold;">rich:findComponent</div>
	<h:panelGrid columns="2">
		<rich:column>
			<a4j:commandLink value="getHeight" reRender="findID"></a4j:commandLink>
		</rich:column>
		<rich:column>
			<h:outputText id="findID" value="#{rich:findComponent('separatorId').height}" />
		</rich:column>
	</h:panelGrid>
</f:subview>