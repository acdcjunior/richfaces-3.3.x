<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<f:subview id="autoEventHandlersSubviewID">

	<a4j:commandButton value="Get Event Handlers / Reset"
		reRender="resultEventHandlersPanel"
		action="#{autoEventHandlers.drawEventHandlersGrid}" ajaxSingle="true">
	</a4j:commandButton>

	<a4j:outputPanel id="resultEventHandlersPanel">
		<h:panelGrid id="resultEventHandlersGrid"
			binding="#{autoEventHandlers.resultEventHandlersGrid}" columns="2" />
	</a4j:outputPanel>

	<%--
	<a4j:commandButton process="resultEventHandlersGrid"
		value="Submit Results"></a4j:commandButton>
--%>
</f:subview>