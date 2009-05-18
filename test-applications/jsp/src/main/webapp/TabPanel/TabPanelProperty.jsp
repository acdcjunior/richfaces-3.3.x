<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<f:subview id="tabPanelStraightforwardSubviewID">
<h:commandButton value="add test" action="#{tabPanel.addHtmlTabPanel}"></h:commandButton>
	<h:panelGrid columns="2" cellspacing="10px">
		<h:outputText value="Width (px or %):"></h:outputText>
		<h:inputText value="#{tabPanel.width}" onchange="submit();" />

		<h:outputText value="Height (px or %):"></h:outputText>
		<h:inputText value="#{tabPanel.height}" onchange="submit();" />

		<h:outputText value="Title:"></h:outputText>
		<h:inputText value="#{tabPanel.title}">
			<a4j:support event="onchange" reRender="tabPanelId"></a4j:support>
		</h:inputText>

		<h:outputText value="LabelWidth:"></h:outputText>
		<h:inputText value="#{tabPanel.labelWidth}" onchange="submit();" />

		<h:outputText value="SwitchType:"></h:outputText>
		<h:selectOneRadio value="#{tabPanel.switchType}">
			<f:selectItem itemLabel="client" itemValue="client" />
			<f:selectItem itemLabel="server" itemValue="server" />
			<f:selectItem itemLabel="ajax" itemValue="ajax" />
			<a4j:support event="onchange" reRender="tabPanelId"></a4j:support>
		</h:selectOneRadio>

		<h:outputText value="Header Alignment:"></h:outputText>
		<h:selectOneRadio value="#{tabPanel.headerAlignment}">
			<f:selectItem itemLabel="left" itemValue="left" />
			<f:selectItem itemLabel="center" itemValue="center" />
			<f:selectItem itemLabel="right" itemValue="right" />
			<a4j:support event="onchange" reRender="tabPanelId"></a4j:support>
		</h:selectOneRadio>

		<h:outputText value="Header Spacing:"></h:outputText>
		<h:inputText value="#{tabPanel.headerSpacing}">
			<a4j:support event="onchange" reRender="tabPanelId"></a4j:support>
		</h:inputText>

		<h:outputText value="Selected Tab:"></h:outputText>
		<h:selectOneRadio value="#{tabPanel.selectedTab}" onchange="submit();">
			<f:selectItem itemLabel="1" itemValue="tabOne" />
			<f:selectItem itemLabel="2" itemValue="tabTwo" />
			<f:selectItem itemLabel="3" itemValue="tabThree" />
		</h:selectOneRadio>

		<h:outputText value="immediate" />
		<h:selectBooleanCheckbox value="#{tabPanel.immediate}">
			<a4j:support event="onchange" reRender="tabPanelId"></a4j:support>
		</h:selectBooleanCheckbox>

		<h:outputText value="Rendered"></h:outputText>
		<h:selectBooleanCheckbox value="#{tabPanel.rendered}"
			onclick="submit()">
		</h:selectBooleanCheckbox>

		<h:outputText value="Disable Tab"></h:outputText>
		<h:selectBooleanCheckbox value="#{tabPanel.disabledTab}"
			onclick="submit()">
		</h:selectBooleanCheckbox>
		<h:outputText value="Switch Styles:" />
		<h:commandButton action="#{tabPanel.doStyles}"
			value="#{tabPanel.btnLabel}" />
	</h:panelGrid>
	<br />
	<br />
	<div style="FONT-WEIGHT: bold;">rich:findComponent</div>
	<h:panelGrid columns="2">
		<rich:column>
			<a4j:commandLink value="getSelectedTab" reRender="findID"></a4j:commandLink>
		</rich:column>
		<rich:column id="findID">
			<h:outputText value="#{rich:findComponent('tabPanelId').selectedTab}" />
		</rich:column>
	</h:panelGrid>
</f:subview>