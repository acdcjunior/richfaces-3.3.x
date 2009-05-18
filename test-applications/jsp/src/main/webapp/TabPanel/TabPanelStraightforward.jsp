<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<f:subview id="tabPanelPropertySubviewID">
			<h:panelGrid columns="3">
				<h:outputText value="Test1" />
				<a4j:commandButton action="#{tabPanel.bTest1}" value="run" reRender="tpPropertyID,tabPanelId"></a4j:commandButton>
				<h:outputText value="#{msg.t1TabPanel}"/>

				<h:outputText value="Test2" />
				<a4j:commandButton action="#{tabPanel.bTest2}" value="run" reRender="tpPropertyID,tabPanelId"></a4j:commandButton>
				<h:outputText value="#{msg.t2TabPanel}"/>

				<h:outputText value="Test3" />
				<a4j:commandButton action="#{tabPanel.bTest3}" value="run" reRender="tpPropertyID,tabPanelId"></a4j:commandButton>
				<h:outputText value="#{msg.t3TabPanel}"/>

				<h:outputText value="Test4" />
				<a4j:commandButton action="#{tabPanel.bTest4}" value="run" reRender="tpPropertyID,tabPanelId"></a4j:commandButton>
				<h:outputText value="#{msg.t4TabPanel}"/>

				<h:outputText value="Test5" />
				<a4j:commandButton action="#{tabPanel.bTest5}" value="run" reRender="tpPropertyID,tabPanelId"></a4j:commandButton>
				<h:outputText value="#{msg.t5TabPanel}"/>
			</h:panelGrid>
</f:subview>