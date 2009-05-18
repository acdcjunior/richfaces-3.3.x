<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<f:subview id="dropDownMenuStraightforwardSubviewID">
			<h:panelGrid columns="3">
				<h:outputText value="Test1" />
				<a4j:commandButton action="#{dDMenu.bTest1}" value="run" reRender="ddmId,ddMenuPropertyID"></a4j:commandButton>
				<h:outputText value="#{msg.t1DDMenu}"/>

				<h:outputText value="Test2" />
				<a4j:commandButton action="#{dDMenu.bTest2}" value="run" reRender="ddmId,ddMenuPropertyID"></a4j:commandButton>
				<h:outputText value="#{msg.t2DDMenu}"/>

				<h:outputText value="Test3" />
				<a4j:commandButton action="#{dDMenu.bTest3}" value="run" reRender="ddmId,ddMenuPropertyID"></a4j:commandButton>
				<h:outputText value="#{msg.t3DDMenu}"/>

				<h:outputText value="Test4" />
				<a4j:commandButton action="#{dDMenu.bTest4}" value="run" reRender="ddmId,ddMenuPropertyID"></a4j:commandButton>
				<h:outputText value="#{msg.t4DDMenu}"/>

				<h:outputText value="Test5" />
				<a4j:commandButton action="#{dDMenu.bTest5}" value="run" reRender="ddmId,ddMenuPropertyID"></a4j:commandButton>
				<h:outputText value="#{msg.t5DDMenu}"/>
			</h:panelGrid>
</f:subview>