<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<f:subview id="pickListSubviewID">
	<div>The &lt;rich:pickList&gt; component is used for moving
	selected item(s) from one list into another.</div>
	<rich:pickList id="pickListID" value="#{pickList.value}"
		onblur="markEventAsWorkable('onblur');"
		onclick="markEventAsWorkable('onclick');"
		ondblclick="markEventAsWorkable('ondblclick');"
		onfocus="markEventAsWorkable('onfocus');"
		onkeydown="markEventAsWorkable('onkeydown');"
		onkeypress="markEventAsWorkable('onkeypress');"
		onkeyup="markEventAsWorkable('onkeyup');"
		onlistchange="markEventAsWorkable('onlistchange');"
		onlistchanged="markEventAsWorkable('onlistchanged');"
		onmousedown="markEventAsWorkable('onmousedown');"
		onmousemove="markEventAsWorkable('onmousemove');"
		onmouseout="markEventAsWorkable('onmouseout');"
		onmouseover="markEventAsWorkable('onmouseover');"
		onmouseup="markEventAsWorkable('onmouseup');"
		controlClass="controlClass-controlClass"
		disabledStyle="disabledStyle:disabledStyle"
		disabledStyleClass="disabledStyleClass-disabledStyleClass"
		enabledStyle="enabledStyle:enabledStyle"
		enabledStyleClass="enabledStyleClass-enabledStyleClass"
		listClass="listClass-listClass" style="style:style"
		styleClass="styleClass-styleClass" validator="#{validation.validate}"
		validatorMessage="#{validation.validatorMessage}"
		converter="#{convertion.convert}"
		converterMessage="#{convertion.converterMessage}"
		required="#{pickList.required}"
		requiredMessage="#{validation.requiredMessage}"
		valueChangeListener="#{listeners.valueChangeListener}"
		immediate="#{pickList.immediate}">
		<f:selectItems value="#{pickList.selectItems}" />
		<f:selectItem itemLabel="validator" itemValue="validator" />
		<f:selectItem itemLabel="converter" itemValue="converter" />
	</rich:pickList>
	<%-- converter select item should be the last in the list 
	because of https://jira.jboss.org/jira/browse/RF-7472 --%>
	
	<rich:pickList id="pickListDisabledID" disabled="true"
		value="#{pickList.value}" disabledStyle="disabledStyle:disabledStyle"
		disabledStyleClass="disabledStyleClass-disabledStyleClass">
		<f:selectItems value="#{pickList.selectItems}" />
	</rich:pickList>
	<br />
</f:subview>