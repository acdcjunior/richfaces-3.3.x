<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<f:subview id="layoutComponentsPropertySubviewID">
	<h:commandButton value="regroup" action="#{layout.validate}"></h:commandButton>

	<h:panelGrid columns="2" border="1" style="float:left;">
		<h:outputText value="Component:" />
		<h:outputText value="rich:page" style="font-weight:bold;" />

		<h:outputText value="contentType" />
		<h:selectOneRadio value="#{page.contentType}">
			<f:selectItem itemLabel="text/html" itemValue="text/html" />
			<f:selectItem itemLabel="image/png" itemValue="image/png" />
			<f:selectItem itemLabel="image/gif" itemValue="image/gif" />
			<f:selectItem itemLabel="video/mpeg" itemValue="video/mpeg" />
			<f:selectItem itemLabel="text/css" itemValue="text/css" />
			<f:selectItem itemLabel="audio/basic" itemValue="audio/basic" />
			<a4j:support event="onchange" reRender="pageID"></a4j:support>
		</h:selectOneRadio>

		<h:outputText value="dir" />
		<h:selectOneRadio value="#{page.dir}">
			<f:selectItem itemLabel="rtl" itemValue="rtl" />
			<f:selectItem itemLabel="ltr" itemValue="ltr" />
			<a4j:support event="onchange" reRender="pageID"></a4j:support>
		</h:selectOneRadio>

		<h:outputText value="lang" />
		<h:selectOneRadio value="#{page.lang}">
			<f:selectItem itemLabel="en" itemValue="en" />
			<f:selectItem itemLabel="ru" itemValue="ru" />
			<f:selectItem itemLabel="fr" itemValue="fr" />
			<a4j:support event="onchange" reRender="pageID"></a4j:support>
		</h:selectOneRadio>

		<h:outputText value="markupType" />
		<h:panelGroup>
			<h:inputText value="#{page.markupType}">
				<a4j:support event="onblur" reRender="pageID"></a4j:support>
			</h:inputText>
			<h:outputText value="jsp,xhtml and so on" />
		</h:panelGroup>

		<h:outputText value="namespace" />
		<h:inputText value="#{page.namespace}" onchange="submit">
			<a4j:support event="onblur" reRender="pageID"></a4j:support>
		</h:inputText>

		<h:outputText value="pageTitle" />
		<h:inputText value="#{page.pageTitle}" onchange="submit">
			<a4j:support event="onblur" reRender="pageID"></a4j:support>
		</h:inputText>

		<h:outputText value="rendered" />
		<h:selectBooleanCheckbox value="#{page.rendered}">
			<a4j:support event="onclick" reRender="pageID"></a4j:support>
		</h:selectBooleanCheckbox>

		<h:outputText value="sidebarPosition" />
		<h:selectOneMenu value="#{page.sidebarPosition}">
			<f:selectItem itemValue="left" itemLabel="left"/>
			<f:selectItem itemValue="right" itemLabel="right"/>
			<a4j:support event="onchange" reRender="pageID"></a4j:support>
		</h:selectOneMenu>

		<h:outputText value="sidebarWidth" />
		<h:inputText value="#{page.sidebarWidth}" onchange="submit">
			<a4j:support event="onblur" reRender="pageID"></a4j:support>
		</h:inputText>

		<h:outputText value="theme" />
		<h:inputText value="#{page.theme}" onchange="submit">
			<a4j:support event="onblur" reRender="pageID"></a4j:support>
		</h:inputText>

		<h:outputText value="title" />
		<h:inputText value="#{page.title}" onchange="submit">
			<a4j:support event="onblur" reRender="pageID"></a4j:support>
		</h:inputText>

		<h:outputText value="width" />
		<h:inputText value="#{page.width}" onchange="submit">
			<a4j:support event="onblur" reRender="pageID"></a4j:support>
		</h:inputText>

		<h:outputText value="rich:page test" />
		<h:commandButton value="add test" action="#{page.addPage}"></h:commandButton>

		<h:commandButton actionListener="#{page.checkPage}"
			value="Check Page Id" />
		<h:outputText value="#{page.pageLabel}" />
	</h:panelGrid>
	<h:panelGrid columns="2" border="1">
		<h:outputText value="Component:" />
		<h:outputText value="rich:layout" style="font-weight:bold;" />

		<h:outputText value="rendered" />
		<h:selectBooleanCheckbox value="#{layout.rendered}">
			<a4j:support event="onclick" reRender="pageID"></a4j:support>
		</h:selectBooleanCheckbox>

		<h:outputText value="rich:layout test" />
		<h:commandButton value="add test" action="#{layout.addLayout}"></h:commandButton>

		<h:commandButton actionListener="#{layout.checkLayout}"
			value="Check Layout Id" />
		<h:outputText value="#{layout.layoutLabel}" />
	</h:panelGrid>
	<h:panelGrid columns="2" border="1">
		<h:outputText value="Component:" />
		<h:outputText value="rich:layoutPanel" style="font-weight:bold;" />

		<h:outputText value="rendered" />
		<h:selectBooleanCheckbox value="#{layoutPanel.rendered}">
			<a4j:support event="onclick" reRender="pageID"></a4j:support>
		</h:selectBooleanCheckbox>

		<h:outputText value="rich:layout test" />
		<h:commandButton value="add test" action="#{layoutPanel.addLayoutPanel}"></h:commandButton>

		<h:commandButton actionListener="#{layoutPanel.checkLayoutPanel}"
			value="Check Layout Id" />
		<h:outputText value="#{layoutPanel.layoutPanelLabel}" />
	</h:panelGrid>
</f:subview>