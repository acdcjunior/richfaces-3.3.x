<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<f:subview id="ModalPanelPropertySubviewID">
	<rich:modalPanel id="modalPanelID" minHeight="100" minWidth="200"
		moveable="true" autosized="true" resizeable="false">
		<f:facet name="header">
			<h:outputText value="Events..." />
		</f:facet>
		<f:facet name="controls">
			<h:commandLink value="Close (Does not work)"
				onclick="Richfaces.hideModalPanel('modalPanelID'); return false;" />
		</f:facet>

		<h:outputText value="Events..." />
		<rich:calendar popup="true" />
		<rich:separator></rich:separator>
		<h:outputLink
			onclick="Richfaces.hideModalPanel('modalPanelID');return false;">
			<f:verbatim>Close (Does not work)</f:verbatim>
		</h:outputLink>
		<br />
		<h:outputLink onclick="Richfaces.hideTopModalPanel();return false;">
			<f:verbatim>Close Top Panel</f:verbatim>
		</h:outputLink>
	</rich:modalPanel>
	<f:verbatim>
		<fieldset><legend>Rich.hideTopModalPanel() TEST</legend> <h:outputText
			value="click on image for open Modal Panel 3" /> <h:graphicImage
			value="/pics/info.gif">
			<rich:componentControl event="onclick" for="modalPanelID"
				operation="show"></rich:componentControl>
		</h:graphicImage></fieldset>
	</f:verbatim>
	<br />
	<h:commandButton value="add test"
		action="#{modalPanel.addHtmlModalPanel}"></h:commandButton>
	<h:panelGrid columns="2">
		<h:outputText value="domElementAttachment"></h:outputText>
		<h:selectOneRadio value="#{modalPanel.domElementAttachment}"
			onchange="submit();">
			<f:selectItem itemValue="body" itemLabel="body" />
			<f:selectItem itemValue="parent" itemLabel="parent" />
			<f:selectItem itemValue="form" itemLabel="form" />
		</h:selectOneRadio>

		<h:outputText value="visualOptions"></h:outputText>
		<h:inputText value="#{modalPanel.visualOptions}" onchange="submit();"></h:inputText>

		<h:outputText value="shadowDepth" />
		<h:inputText value="#{modalPanel.shadowDepth}" onchange="submit();">
		</h:inputText>

		<h:outputText value="shadowOpacity" />
		<h:inputText value="#{modalPanel.shadowOpacity}" onchange="submit();">
		</h:inputText>

		<h:outputText value="keepVisualState" />
		<h:selectBooleanCheckbox value="#{modalPanel.keepVisualState}"
			onchange="submit();">
		</h:selectBooleanCheckbox>

		<h:outputText value="left" />
		<h:inputText value="#{modalPanel.left}" onchange="submit();">
		</h:inputText>

		<h:outputText value="top" />
		<h:inputText value="#{modalPanel.top}" onchange="submit();">
		</h:inputText>

		<h:outputText value="rendered" />
		<h:selectBooleanCheckbox value="#{modalPanel.rendered}"
			onchange="submit();">

		</h:selectBooleanCheckbox>

		<h:outputText value="showWhenRendered" />
		<h:selectBooleanCheckbox value="#{modalPanel.showWhenRendered}"
			onchange="submit();">
		</h:selectBooleanCheckbox>

		<h:outputText value="zindex" />
		<h:selectOneRadio value="#{modalPanel.zindex}" onchange="submit();">
			<f:selectItem itemLabel="1" itemValue="1" />
			<f:selectItem itemLabel="3" itemValue="3" />
		</h:selectOneRadio>

		<h:outputText value="Width:" />
		<h:inputText value="#{modalPanel.width}" onchange="submit();">
		</h:inputText>

		<h:outputText value="Height:" />
		<h:inputText value="#{modalPanel.height}" onchange="submit();">
		</h:inputText>

		<h:outputText value="minWidth:" />
		<h:inputText value="#{modalPanel.minWidth}" onchange="submit();">
		</h:inputText>

		<h:outputText value="minHeight:" />
		<h:inputText value="#{modalPanel.minHeight}" onchange="submit();">
		</h:inputText>

		<h:outputText value="autosized:" />
		<h:selectBooleanCheckbox value="#{modalPanel.autosized}"
			onchange="submit();"></h:selectBooleanCheckbox>

		<h:outputText value="resizeable:" />
		<h:selectBooleanCheckbox value="#{modalPanel.resizeable}"
			onchange="submit();"></h:selectBooleanCheckbox>

		<h:outputText value="Moveable: " />
		<h:selectBooleanCheckbox value="#{modalPanel.moveable}"
			onchange="submit();"></h:selectBooleanCheckbox>
	</h:panelGrid>
</f:subview>