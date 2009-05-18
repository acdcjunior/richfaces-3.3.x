<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<f:subview id="Paint2DStraightforwardSubviewID">
		<rich:simpleTogglePanel switchType="client" opened="true" label="paint2D straightforward">
			<h:panelGrid columns="3">
				<h:outputText value="Test1" />
				<a4j:commandButton action="#{paint2D.bTest1}" value="run" reRender="paint2dID,paint2DPropertyID"></a4j:commandButton>
				<h:outputText value="#{msg.t1Paint2D}"/>

				<h:outputText value="Test2" />
				<a4j:commandButton action="#{paint2D.bTest2}" value="run" reRender="paint2dID,paint2DPropertyID"></a4j:commandButton>
				<h:outputText value="#{msg.t2Paint2D}"/>

				<h:outputText value="Test3" />
				<a4j:commandButton action="#{paint2D.bTest3}" value="run" reRender="paint2dID,paint2DPropertyID"></a4j:commandButton>
				<h:outputText value="#{msg.t3Paint2D}"/>

				<h:outputText value="Test4" />
				<a4j:commandButton action="#{paint2D.bTest4}" value="run" reRender="paint2dID,paint2DPropertyID"></a4j:commandButton>
				<h:outputText value="#{msg.t4Paint2D}"/>

				<h:outputText value="Test5" />
				<a4j:commandButton action="#{paint2D.bTest5}" value="run" reRender="paint2dID,paint2DPropertyID"></a4j:commandButton>
				<h:outputText value="#{msg.t5Paint2D}"/>
			</h:panelGrid>
		</rich:simpleTogglePanel>
</f:subview>