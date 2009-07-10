<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<f:subview id="autoStylesClassesSubviewID">

	<a4j:commandButton value="Get Styles and Classes / Reset"
		reRender="resultStylesClassesPanel"
		action="#{autoStylesClasses.drawStylesClassesGrid}" ajaxSingle="true">
	</a4j:commandButton>

	<a4j:outputPanel id="resultStylesClassesPanel">
		<h:panelGrid id="resultStylesClassesGrid"
			binding="#{autoStylesClasses.resultStylesClassesGrid}" columns="2" />
	</a4j:outputPanel>

	<div class="notes"><b>NOTE: </b>Tests for styles could return
	wrong FAILED result in Firefox and Internet Explorer!</div>

	<%--
	<a4j:commandButton process="resultStylesClassesGrid"
		value="Submit Results"></a4j:commandButton>
--%>
</f:subview>