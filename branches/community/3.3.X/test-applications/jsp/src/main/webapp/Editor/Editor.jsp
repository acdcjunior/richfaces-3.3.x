<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<f:subview id="editorSubviewID">
	<h:panelGrid id="EditorPanelID" columns="2" border="1">

		<rich:editor id="editorID" binding="#{editor.htmlEditor}"
			value="#{editor.value}" width="#{editor.width}"
			height="#{editor.height}" theme="#{editor.theme}"
			onchange="#{event.onchange}" oninit="#{event.oninit}"
			onsetup="#{event.onsetup}" 
			autoResize="#{editor.autoResize}" converter="#{editor.convert}"
			converterMessage="converterMessage" immediate="#{editor.immediate}"
			rendered="#{editor.rendered}" required="#{editor.required}"
			useSeamText="#{editor.useSeamText}"
			validator="#{editor.validate}" validatorMessage="validatorMessage"
			valueChangeListener="#{editor.valueChangeListener}"
			viewMode="#{editor.viewMode}" readonly="#{editor.readonly}"
			tabindex="#{editor.tabindex}" dialogType="#{editor.dialogType}"
			language="#{editor.language}" styleClass="EditorStyleClass"
			plugins="media" label="test_editor">

			<f:param name="theme_advanced_resizing" value="true" />
			<f:param name="theme_advanced_statusbar_location" value="top" />
		</rich:editor>

		<h:panelGrid columns="1">
			<f:facet name="header">
				<h:outputText value="Results" />
			</f:facet>
			<h:outputText value="#{editor.value}" escape="false" />
			<div style="color: red; font-style: italic">HTML code:</div>
			<h:outputText value="#{editor.value}"
				style="font-style: italic; color: gray" />
		</h:panelGrid>
	</h:panelGrid>
	<a4j:commandButton value="Show Result" reRender="EditorPanelID" />
	<a4j:commandButton value="reRender editor" reRender="editorID" />
</f:subview>