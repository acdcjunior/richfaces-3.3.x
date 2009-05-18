<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<f:subview id="listShuttlePropertySubviewID">
	<h:commandButton value="add test"
		action="#{listShuttle.addHtmlListShuttle}"></h:commandButton>

	<br />
	<br />
	<h:outputText value="JavaScript API" style="font-weight: bold;" />
	<h:panelGrid columns="4" border="1">
		<h:panelGroup>
			<a4j:commandLink
				onclick="$('formID:listShuttleSubviewID:listShuttleID').component.enable()"
				value="enable"></a4j:commandLink>
			<br />
			<a4j:commandLink
				onclick="$('formID:listShuttleSubviewID:listShuttleID').component.disable()"
				value="disable"></a4j:commandLink>
			<br />
			<a4j:commandLink
				onclick="alert($('formID:listShuttleSubviewID:listShuttleID').component.isEnabled())"
				value="isEnabled"></a4j:commandLink>
		</h:panelGroup>
		<h:panelGroup>
			<a4j:commandLink
				onclick="$('formID:listShuttleSubviewID:listShuttleID').component.up()"
				value="up"></a4j:commandLink>
			<br />
			<a4j:commandLink
				onclick="$('formID:listShuttleSubviewID:listShuttleID').component.down()"
				value="down"></a4j:commandLink>
			<br />
			<a4j:commandLink
				onclick="$('formID:listShuttleSubviewID:listShuttleID').component.top()"
				value="top"></a4j:commandLink>
			<br />
			<a4j:commandLink
				onclick="$('formID:listShuttleSubviewID:listShuttleID').component.bottom()"
				value="bottom"></a4j:commandLink>
		</h:panelGroup>
		<h:panelGroup>
			<a4j:commandLink
				onclick="$('formID:listShuttleSubviewID:listShuttleID').component.copy()"
				value="copy"></a4j:commandLink>
			<br />
			<a4j:commandLink
				onclick="$('formID:listShuttleSubviewID:listShuttleID').component.remove()"
				value="remove"></a4j:commandLink>
			<br />
			<a4j:commandLink
				onclick="$('formID:listShuttleSubviewID:listShuttleID').component.copyAll()"
				value="copyAll"></a4j:commandLink>
			<br />
			<a4j:commandLink
				onclick="$('formID:listShuttleSubviewID:listShuttleID').component.removeAll()"
				value="removeAll"></a4j:commandLink>
		</h:panelGroup>
		<h:panelGroup>
			<a4j:commandLink
				onclick="alert($('formID:listShuttleSubviewID:listShuttleID').component.targetList.getSelection())"
				value="targetList.getSelection"></a4j:commandLink>
			<br />
			<a4j:commandLink
				onclick="alert($('formID:listShuttleSubviewID:listShuttleID').component.sourceList.getSelection())"
				value="sourceList.getSelection"></a4j:commandLink>
			<br />
			<a4j:commandLink
				onclick="alert($('formID:listShuttleSubviewID:listShuttleID').component.targetList.getItems())"
				value="targetList.getItems"></a4j:commandLink>
			<br />
			<a4j:commandLink
				onclick="alert($('formID:listShuttleSubviewID:listShuttleID').component.sourceList.getItems())"
				value="sourceList.getItems"></a4j:commandLink>
		</h:panelGroup>
	</h:panelGrid>

	<h:panelGrid columns="2">
		<h:column></h:column>
		<h:panelGroup>
			<a4j:commandButton value="reRender" reRender="listShuttleID"></a4j:commandButton>
			<a4j:commandButton immediate="true" reRender="listShuttleID"
				value="immediate submit(); (a4j)"></a4j:commandButton>
			<h:commandButton value="submit();" />
			<h:commandButton immediate="true" value="immediate submit();" />
		</h:panelGroup>

		<h:outputText value="Enter quantity of lines" />
		<h:panelGroup>
			<h:inputText value="#{listShuttle.lenght}" />
			<a4j:commandButton action="#{listShuttle.addNewItem}"
				reRender="listShuttleID" value="ok" />
		</h:panelGroup>

		<h:outputText value="showButtonLabels" />
		<h:selectBooleanCheckbox value="#{listShuttle.showButtonLabels}"
			onchange="submit();" />

		<h:outputText value="orderControlsVisible" />
		<h:selectBooleanCheckbox value="#{listShuttle.orderControlsVisible}"
			onchange="submit();" />

		<h:outputText value="moveControlsVisible" />
		<h:selectBooleanCheckbox value="#{listShuttle.moveControlsVisible}"
			onchange="submit();" />

		<h:outputText value="fastOrderControlsVisible" />
		<h:selectBooleanCheckbox
			value="#{listShuttle.fastOrderControlsVisible}" onchange="submit();" />

		<h:outputText value="fastMoveControlsVisible" />
		<h:selectBooleanCheckbox
			value="#{listShuttle.fastMoveControlsVisible}" onchange="submit();" />

		<h:outputText value="switchByClick" />
		<h:selectBooleanCheckbox value="#{listShuttle.switchByClick}"
			onchange="submit();" />
			
		<h:outputText value="switchByDbClick" />
		<h:selectBooleanCheckbox value="#{listShuttle.switchByDblClick}"
			onchange="submit();" />					
			
		<h:outputText value="sourceRequired" />
		<h:selectBooleanCheckbox value="#{listShuttle.sourceRequired}"
			onchange="submit();" />
			
		<h:outputText value="targetRequired" />
		<h:selectBooleanCheckbox value="#{listShuttle.targetRequired}"
			onchange="submit();" />

		<h:outputText value="sourceCaptionLabel" />
		<h:inputText value="#{listShuttle.sourceCaptionLabel}"
			onchange="submit()" />

		<h:outputText value="targetCaptionLabel" />
		<h:inputText value="#{listShuttle.targetCaptionLabel}"
			onchange="submit()" />

		<h:outputText value="sourceListWidth" />
		<h:inputText value="#{listShuttle.sourceListWidth}"
			onchange="submit()" />

		<h:outputText value="targetListWidth" />
		<h:inputText value="#{listShuttle.targetListWidth}"
			onchange="submit()" />

		<h:outputText value="listsHeight" />
		<h:inputText value="#{listShuttle.listsHeight}" onchange="submit()" />

		<h:outputText value="copyControlLabel" />
		<h:inputText value="#{listShuttle.copyControlLabel}"
			onchange="submit()" />

		<h:outputText value="removeControlLabel" />
		<h:inputText value="#{listShuttle.removeControlLabel}"
			onchange="submit()" />

		<h:outputText value="copyAllControlLabel" />
		<h:inputText value="#{listShuttle.copyAllControlLabel}"
			onchange="submit()" />

		<h:outputText value="removeAllControlLabel" />
		<h:inputText value="#{listShuttle.removeAllControlLabel}"
			onchange="submit()" />

		<h:outputText value="bottomControlLabel" />
		<h:inputText value="#{listShuttle.bottomControlLabel}"
			onchange="submit()" />

		<h:outputText value="downControlLabel" />
		<h:inputText value="#{listShuttle.downControlLabel}"
			onchange="submit()" />

		<h:outputText value="topControlLabel" />
		<h:inputText value="#{listShuttle.topControlLabel}"
			onchange="submit()" />

		<h:outputText value="upControlLabel" />
		<h:inputText value="#{listShuttle.upControlLabel}" onchange="submit()" />
		
		<h:outputText value="controlsVerticalAlign" />
		<h:panelGroup>
			<h:selectOneRadio value="#{listShuttle.controlsVerticalAlign}" onchange="submit()">
				<f:selectItem itemValue="top"/>
				<f:selectItem itemValue="middle"/>
				<f:selectItem itemValue="bottom"/>
			</h:selectOneRadio>
		</h:panelGroup>
		
		<h:outputText value="controlsType" />
		<h:panelGroup>
	       <h:selectOneRadio value="#{listShuttle.controlsType}" onchange="submit()">
		        <f:selectItem itemValue="none" itemLabel="none"/>
		        <f:selectItem itemValue="button" itemLabel="button"/>
		   </h:selectOneRadio>
		</h:panelGroup>
	</h:panelGrid>
	

	<br />
	<br />
	<%-- 
	<div style="FONT-WEIGHT: bold;">rich:findComponent</div>
	<h:panelGrid columns="2">
		<rich:column>
			<a4j:commandLink value="getActiveItem" reRender="findID"></a4j:commandLink>
		</rich:column>
		<rich:column id="findID">
			<h:outputText
				value="#{rich:findComponent('listShuttleID').activeItem}" />
		</rich:column>
	</h:panelGrid>
	--%>
</f:subview>