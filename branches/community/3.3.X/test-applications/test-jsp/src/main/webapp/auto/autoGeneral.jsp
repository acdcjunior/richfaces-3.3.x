<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<f:subview id="autoGeneralSubviewID">

	<a4j:commandButton value="Get Automated General Attrs / Reset"
		reRender="resultAutoGeneralPanel"
		action="#{autoGeneral.drawAutoGeneralGrid}" ajaxSingle="true">
	</a4j:commandButton>

	<a4j:outputPanel id="resultAutoGeneralPanel">
		<h:panelGrid id="resultAutoGeneralGrid"
			binding="#{autoGeneral.resultAutoGeneralGrid}" columns="2" />
	</a4j:outputPanel>

	<a4j:commandButton value="TEST" oncomplete="checkMessage()"
		reRender="componentID, resultAutoGeneralPanel"
		action="#{autoGeneral.testAutoGeneral}"></a4j:commandButton>

	<div class="notes"><b>NOTE: </b> For testing validator and
	validatorMessage perform following steps please:
	<ul>
		<li>select "validator" value</li>
		<li>click TEST button</li>
		<li>select correct value</li>
		<li>click TEST button again</li>
	</ul>
	The same for converter and converterMessage, excepting: "converter"
	value should be selected.</div>

	<h:inputHidden id="hiddenValidatorInput"
		value="#{validation.validatorMessageTest}" />
	<h:inputHidden id="hiddenConverterInput"
		value="#{convertion.converterMessageTest}" />
	<h:inputHidden id="hiddenRequiredInput"
		value="#{validation.requiredMessageTest}" />

</f:subview>