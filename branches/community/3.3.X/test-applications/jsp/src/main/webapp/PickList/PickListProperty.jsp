<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<f:subview id="pickListPropertySubviewID">
	<h:commandButton value="add test" action="#{pickList.addHtmlPickList}"></h:commandButton>
	<h:panelGrid columns="2">
		<a4j:commandButton reRender="pickListTargenID" value="refresh target"></a4j:commandButton>
		<h:dataTable id="pickListTargenID" value="#{pickList.value}"
			var="list" border="1">
			<h:column>
				<h:outputText value="#{list}"></h:outputText>
			</h:column>
		</h:dataTable>

		<h:outputText value="title:"></h:outputText>
		<h:inputText value="#{pickList.title}" onchange="submit();"></h:inputText>

		<h:outputText value="moveControlsVerticalAlign:"></h:outputText>
		<h:inputText value="#{pickList.moveControlsVerticalAlign}"
			onchange="submit();"></h:inputText>

		<h:outputText value="showButtonLabels:"></h:outputText>
		<h:selectBooleanCheckbox value="#{pickList.showButtonLabels}"
			onchange="submit();"></h:selectBooleanCheckbox>

		<h:outputText value="copyAllControlLabel:"></h:outputText>
		<h:inputText value="#{pickList.copyAllControlLabel}"
			onchange="submit();"></h:inputText>

		<h:outputText value="copyControlLabel:"></h:outputText>
		<h:inputText value="#{pickList.copyControlLabel}" onchange="submit();"></h:inputText>

		<h:outputText value="removeControlLabel:"></h:outputText>
		<h:inputText value="#{pickList.removeControlLabel}"
			onchange="submit();"></h:inputText>

		<h:outputText value="removeAllControlLabel:"></h:outputText>
		<h:inputText value="#{pickList.removeAllControlLabel}"
			onchange="submit();"></h:inputText>

		<h:outputText value="switchByClick:"></h:outputText>
		<h:selectBooleanCheckbox value="#{pickList.switchByClick}"
			onchange="submit();"></h:selectBooleanCheckbox>

		<h:outputText value="listsHeight:"></h:outputText>
		<h:inputText value="#{pickList.listsHeight}" onchange="submit();"></h:inputText>

		<h:outputText value="sourceListWidth:"></h:outputText>
		<h:inputText value="#{pickList.sourceListWidth}" onchange="submit();"></h:inputText>

		<h:outputText value="targetListWidth:"></h:outputText>
		<h:inputText value="#{pickList.targetListWidth}" onchange="submit();"></h:inputText>

		<h:outputText value="moveControlsVisible:"></h:outputText>
		<h:selectBooleanCheckbox value="#{pickList.moveControlsVisible}"
			onchange="submit();"></h:selectBooleanCheckbox>

		<h:outputText value="fastMoveControlsVisible:"></h:outputText>
		<h:selectBooleanCheckbox value="#{pickList.fastMoveControlsVisible}"
			onchange="submit();"></h:selectBooleanCheckbox>
			
		<h:outputText value="rendered:"></h:outputText>
		<h:selectBooleanCheckbox value="#{pickList.rendered}"
			onchange="submit();"></h:selectBooleanCheckbox>

		<h:outputText value="immediate:"></h:outputText>
		<h:selectBooleanCheckbox value="#{pickList.immediate}"
			onchange="submit();"></h:selectBooleanCheckbox>

		<h:outputText value="disabled:"></h:outputText>
		<h:selectBooleanCheckbox value="#{pickList.disabled}"
			onchange="submit();"></h:selectBooleanCheckbox>

		<h:outputText value="required:"></h:outputText>
		<h:selectBooleanCheckbox value="#{pickList.required}"
			onchange="submit();"></h:selectBooleanCheckbox>

		<h:outputText value="requiredMessage:"></h:outputText>
		<h:inputText value="#{pickList.requiredMessage}" onchange="submit();"></h:inputText>

		<h:commandButton actionListener="#{pickList.checkBinding}"
			value="Binding"></h:commandButton>
		<h:outputText value="#{pickList.bindLabel}"></h:outputText>

	</h:panelGrid>
	<br />
	<br />
	<%--
	<div style="FONT-WEIGHT: bold;">rich:findComponent</div>
	<h:panelGrid columns="2">
		<rich:column>
			<a4j:commandLink value="getSelectedValues" reRender="findID"></a4j:commandLink>
		</rich:column>
		<rich:column id="findID">
			<h:outputText value="#{rich:findComponent('pickListID').selectedValues}" />
		</rich:column>
	</h:panelGrid>
	--%>
</f:subview>