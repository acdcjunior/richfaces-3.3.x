<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<f:subview id="headerSubviewID">
	<h:panelGrid columns="1">
		<h:outputText value="RichFaces version: #{pageContent.version}" />
		<hr />
		<h:panelGrid columns="8">
			<h:panelGrid columns="2">
				<h:commandButton value="Submit" />
				<a4j:commandButton reRender="componentID" value="A4j Submit" />
				<h:commandButton immediate="true" value="Immediate Submit" />
				<a4j:commandButton reRender="componentID" immediate="true"
					value="Immediate A4j Submit" />
			</h:panelGrid>
			<a4j:status startText="working" stopText="-------"
				startStyleClass="error"></a4j:status>
			<h:panelGrid columns="2">
				<h:panelGrid columns="2">
					<h:outputText value="Component: " />
					<h:selectOneMenu value="#{pageContent.component}">
						<f:selectItems value="#{pageContent.allComponents}" />
					</h:selectOneMenu>

					<h:outputText value="Skin: " />
					<h:selectOneMenu value="#{pageContent.skin}">
						<f:selectItems value="#{pageContent.allSkins}" />
					</h:selectOneMenu>
				</h:panelGrid>
				<h:panelGroup>
					<h:commandButton value="Apply"/>
				</h:panelGroup>
			</h:panelGrid>
			<h:panelGroup></h:panelGroup>
			<h:panelGroup></h:panelGroup>
			<h:panelGroup></h:panelGroup>
			<h:panelGroup></h:panelGroup>
			<h:panelGroup>
				<dl>
					<li><a href="combiModalPanel.jsf" target="newTab">current
					component inside MODAL PANEL</a></li>
					<li><a href="#" target="newTab">current component inside
					DATA TABLE</a></li>
				</dl>
			</h:panelGroup>
		</h:panelGrid>
	</h:panelGrid>
</f:subview>