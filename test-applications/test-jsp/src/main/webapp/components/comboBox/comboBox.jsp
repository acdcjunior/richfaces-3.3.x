<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<f:subview id="comboBoxSubviewID">
	<div>The &lt;rich:comboBox&gt; is a component, that provides
	editable combo box element on a page.</div>
	<br />
	<rich:comboBox id="comboBoxID" value="#{comboBox.value}"
		validator="#{validation.validate}"
		validatorMessage="#{validation.validatorMessage}"
		valueChangeListener="#{listeners.valueChangeListener}"
		converter="#{convertion.convert}"
		converterMessage="#{convertion.converterMessage}"
		onblur="markEventAsWorkable('onblur');"
		onchange="markEventAsWorkable('onchange');"
		onclick="markEventAsWorkable('onclick');"
		ondblclick="markEventAsWorkable('ondblclick');"
		onfocus="markEventAsWorkable('onfocus');"
		onkeydown="markEventAsWorkable('onkeydown');"
		onkeypress="markEventAsWorkable('onkeypress');"
		onkeyup="markEventAsWorkable('onkeyup');"
		onlistcall="markEventAsWorkable('onlistcall');"
		onlistclose="markEventAsWorkable('onlistclose');"
		onmousedown="markEventAsWorkable('onmousedown');"
		onmousemove="markEventAsWorkable('onmousemove');"
		onmouseout="markEventAsWorkable('onmouseout');"
		onmouseover="markEventAsWorkable('onmouseover');"
		onmouseup="markEventAsWorkable('onmouseup');"
		onselect="markEventAsWorkable('onselect');" style="style:style"
		styleClass="styleClass-styleClass"
		buttonClass="buttonClass-buttonClass"
		buttonDisabledClass="buttonDisabledClass-buttonDisabledClass"
		buttonDisabledStyle="buttonDisabledStyle:buttonDisabledStyle"
		buttonInactiveClass="buttonInactiveClass-buttonInactiveClass"
		buttonInactiveStyle="buttonInactiveStyle:buttonInactiveStyle"
		buttonStyle="buttonStyle:buttonStyle"
		inputClass="inputClass-inputClass"
		inputDisabledClass="inputDisabledClass-inputDisabledClass"
		inputDisabledStyle="inputDisabledStyle:inputDisabledStyle"
		inputInactiveClass="inputInactiveClass-inputInactiveClass"
		inputInactiveStyle="inputInactiveStyle:inputInactiveStyle"
		inputStyle="inputStyle:inputStyle" itemClass="itemClass-itemClass"
		itemSelectedClass="itemSelectedClass-itemSelectedClass"
		listClass="listClass-listClass" listStyle="listStyle:listStyle"
		required="#{comboBox.required}"
		requiredMessage="#{validation.requiredMessage}"
		immediate="#{comboBox.immediate}">
		<f:selectItem itemValue="validator" />
		<f:selectItem itemValue="converter" />
		<f:selectItems value="#{comboBox.items}" />
	</rich:comboBox>
	<br />
	<h:panelGrid columns="2">
		<rich:spacer width="200"></rich:spacer>
		<h:panelGrid columns="2">
			<h:commandLink
				onclick="document.getElementById('formID:comboBoxSubviewID:comboBoxID').component.showList(); return false;"
				value="showList" />
			<h:commandLink
				onclick="document.getElementById('formID:comboBoxSubviewID:comboBoxID').component.hideList(); return false;"
				value="hideList" />
			<h:commandLink
				onclick="document.getElementById('formID:comboBoxSubviewID:comboBoxID').component.enable(); return false;"
				value="enable" />
			<h:commandLink
				onclick="document.getElementById('formID:comboBoxSubviewID:comboBoxID').component.disable(); return false;"
				value="disable" />
		</h:panelGrid>
	</h:panelGrid>
	<br />
</f:subview>