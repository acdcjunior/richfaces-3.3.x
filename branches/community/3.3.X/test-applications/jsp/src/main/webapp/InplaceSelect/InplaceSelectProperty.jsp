<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<f:subview id="inplaceSelectPropertySubviewID">
<h:commandButton value="add test" action="#{inplaceSelect.addHtmlInplaceSelect}"></h:commandButton>
	<h:panelGrid columns="2">
		<h:outputText value="value"></h:outputText>
		<h:outputText value="#{inplaceSelect.value}"></h:outputText>

		<h:outputText value="defaultLabel"></h:outputText>
		<h:inputText value="#{inplaceSelect.defaultLabel}"
			onchange="submit();"></h:inputText>

		<h:outputText value="editEvent"></h:outputText>
		<h:inputText value="#{inplaceSelect.editEvent}" onchange="submit();"></h:inputText>

		<h:outputText value="selectWidth"></h:outputText>
		<h:inputText value="#{inplaceSelect.selectWidth}" onchange="submit();"></h:inputText>

		<h:outputText value="maxSelectWidth"></h:outputText>
		<h:inputText value="#{inplaceSelect.maxSelectWidth}"
			onchange="submit();"></h:inputText>

		<h:outputText value="minSelectWidth"></h:outputText>
		<h:inputText value="#{inplaceSelect.minSelectWidth}"
			onchange="submit();"></h:inputText>

		<h:outputText value="listWidth"></h:outputText>
		<h:inputText value="#{inplaceSelect.listWidth}" onchange="submit();"></h:inputText>

		<h:outputText value="listHeight"></h:outputText>
		<h:inputText value="#{inplaceSelect.listHeight}" onchange="submit();"></h:inputText>

		<h:outputText value="controlsVerticalPosition"></h:outputText>
		<h:selectOneRadio value="#{inplaceSelect.controlsVerticalPosition}"
			onchange="submit();">
			<f:selectItem itemLabel="top" itemValue="top" />
			<f:selectItem itemLabel="center" itemValue="center" />
			<f:selectItem itemLabel="bottom" itemValue="bottom" />

		</h:selectOneRadio>

		<h:outputText value="controlsHorizontalPosition"></h:outputText>
		<h:selectOneRadio value="#{inplaceSelect.controlsHorizontalPosition}"
			onchange="submit();">
			<f:selectItem itemLabel="right" itemValue="right" />
			<f:selectItem itemLabel="center" itemValue="center" />
			<f:selectItem itemLabel="left" itemValue="left" />
		</h:selectOneRadio>
		<h:outputText value="tabindex"></h:outputText>
		<h:inputText value="#{inplaceSelect.tabindex}" onchange="submit();"></h:inputText>

		<h:outputText value="showControls"></h:outputText>
		<h:selectBooleanCheckbox value="#{inplaceSelect.showControls}"
			onchange="submit();"></h:selectBooleanCheckbox>

		<h:outputText value="applyFromControlsOnly"></h:outputText>
		<h:selectBooleanCheckbox
			value="#{inplaceSelect.applyFromControlsOnly}" onchange="submit();"></h:selectBooleanCheckbox>

		<h:outputText value="openOnEdit"></h:outputText>
		<h:selectBooleanCheckbox value="#{inplaceSelect.openOnEdit}"
			onchange="submit();"></h:selectBooleanCheckbox>

		<h:outputText value="rendered"></h:outputText>
		<h:selectBooleanCheckbox value="#{inplaceSelect.rendered}"
			onchange="submit();"></h:selectBooleanCheckbox>

		<h:commandButton actionListener="#{inplaceSelect.checkBinding}"
			value="Binding"></h:commandButton>
		<h:outputText value="#{inplaceSelect.bindLabel}" />

		<h:outputText value="immediate"></h:outputText>
		<h:selectBooleanCheckbox value="#{inplaceSelect.immediate}"
			onchange="submit();"></h:selectBooleanCheckbox>

		<h:outputText value="required"></h:outputText>
		<h:selectBooleanCheckbox value="#{inplaceSelect.required}"
			onchange="submit();"></h:selectBooleanCheckbox>

		<h:outputText value="requiredMessage"></h:outputText>
		<h:inputText value="#{inplaceSelect.requiredMessage}"
			onchange="submit();"></h:inputText>

		<h:outputText value="layout"></h:outputText>
		<h:inputText value="#{inplaceSelect.layout}" onchange="submit();"></h:inputText>

	</h:panelGrid>

	<a4j:commandLink
		onclick="$('formID:inplaceSelectSubviewID:inplaceSelectID').component.edit()"
		value="edit"></a4j:commandLink>
	<br />
	<a4j:commandLink
		onclick="$('formID:inplaceSelectSubviewID:inplaceSelectID').component.save()"
		value="save"></a4j:commandLink>
	<br />
	<a4j:commandLink
		onclick="$('formID:inplaceSelectSubviewID:inplaceSelectID').component.cancel()"
		value="cancel"></a4j:commandLink>
	<br />
	<a4j:commandLink
		onclick="alert ($('formID:inplaceSelectSubviewID:inplaceSelectID').component.getValue())"
		value="getValue"></a4j:commandLink>
	<br />
	<a4j:commandLink
		onclick="$('formID:inplaceSelectSubviewID:inplaceSelectID').component.setValue(event,{value:'passeds'})"
		value="setValue(e,{params})"></a4j:commandLink>
	<br />
	
	<a4j:commandLink
		onclick="$('formID:inplaceSelectSubviewID:inplaceSelectID').component.setValue('1234')"
		value="setValue('infos')"></a4j:commandLink>

	<br />
	<f:verbatim>
		<h:outputText value="Component Control test"
			style="FONT-WEIGHT: bold;"></h:outputText>
		<br />
		<a href="#" id="editID">edit()</a>
		<br />
		<a href="#" id="saveID">save()</a>
		<br />
		<a href="#" id="cancelID">cancel()</a>
		<br />
		<a href="#" id="getValueID">getValue()</a>
		<br />
		<a href="#" id="setValueID">setValue('warns')</a>
	</f:verbatim>

	<rich:componentControl attachTo="editID" event="onclick"
		for="inplaceSelectID" operation="edit"></rich:componentControl>
	<rich:componentControl attachTo="saveID" event="onclick"
		for="inplaceSelectID" operation="save"></rich:componentControl>
	<rich:componentControl attachTo="cancelID" event="onclick"
		for="inplaceSelectID" operation="cancel"></rich:componentControl>
	<rich:componentControl attachTo="getValueID" event="onclick"
		for="inplaceSelectID" operation="getValue" name="alert">	
		</rich:componentControl>
	<rich:componentControl attachTo="setValueID" event="onclick"
		for="inplaceSelectID" operation="setValue">
		<f:param name="value" value="warns"/>
		</rich:componentControl>
	<br />
	<br />
	<div style="FONT-WEIGHT: bold;">rich:findComponent</div>
	<h:panelGrid columns="2">
		<rich:column>
			<a4j:commandLink value="getValue" reRender="findID"></a4j:commandLink>
		</rich:column>
		<rich:column id="findID">
			<h:outputText value="#{rich:findComponent('inplaceSelectID').value}" />
		</rich:column>
	</h:panelGrid>
</f:subview>