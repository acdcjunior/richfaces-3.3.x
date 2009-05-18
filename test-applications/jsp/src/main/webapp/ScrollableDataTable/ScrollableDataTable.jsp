bind<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<f:subview id="scrollableDataTableSubviewID">
	<h:panelGrid columns="7" border="1" style="font-size:12px">
		<h:outputText value="#1 "></h:outputText>
		<h:outputText value="#2 Text"></h:outputText>
		<h:outputText value="#3 Link"></h:outputText>
		<h:outputText value="#4 Select"></h:outputText>
		<h:outputText value="#5 Icon"></h:outputText>
		<h:outputText value="#6 outputLink"></h:outputText>
		<h:outputText value="#7 Date"></h:outputText>

		<h:panelGrid columns="1" title="1">
			<h:outputText value="sortBy" />
			<h:outputText value="sortable='true'" />
		</h:panelGrid>
		<h:panelGrid columns="1" title="2">
			<h:outputText value="sortBy" />
			<h:outputText value="sortable='false'" />
		</h:panelGrid>
		<h:panelGrid columns="1" title="3">
			<h:outputText value="sortBy" />
			<h:outputText value="undefined sortable" />
		</h:panelGrid>
		<h:panelGrid columns="1" title="4">
			<h:outputText value="sortBy" />
			<h:outputText value="undefined sortable" />
		</h:panelGrid>
		<h:panelGrid columns="1" title="5">
			<h:outputText value="sortBy" />
			<h:outputText value="undefined sortable" />
		</h:panelGrid>
		<h:panelGrid columns="1" title="6">
			<h:outputText value="undefined sortBy" />
			<h:outputText value="undefined sortable" />
		</h:panelGrid>
		<h:panelGrid columns="1" title="7">
			<h:outputText value="sortBy" />
			<h:outputText value="sortable='true'" />
		</h:panelGrid>
	</h:panelGrid>
	<br />
	<rich:scrollableDataTable
		binding="#{scrollableDT.htmlScrollableDataTable}" id="sdt" var="sdt"
		rowKeyVar="key" onRowDblClick="alert('row: #{key}')"
		rowKeyConverter="#{dataConverter}" value="#{scrollableDT.data}"
		rows="#{scrollableDT.rows}" width="#{scrollableDT.width}"
		height="#{scrollableDT.height}"
		hideWhenScrolling="#{scrollableDT.hideWhenScrolling}"
		reRender="inputID" frozenColCount="#{scrollableDT.frozenColCount}"
		first="#{scrollableDT.first}"
		ignoreDupResponses="#{scrollableDT.ignoreDupResponses}"
		bypassUpdates="#{scrollableDT.bypassUpdates}"
		rendered="#{scrollableDT.rendered}" timeout="#{scrollableDT.timeout}"
		sortMode="#{scrollableDT.sortMode}" eventsQueue="myEventsQueue"
		columnClasses="#{style.columnClasses}"
		footerClass="#{style.footerClass}" headerClass="#{style.headerClass}"
		rowClasses="#{style.rowClasses}" activeClass="#{style.activeClass}"
		styleClass="#{style.styleClass}" style="#{style.style}"
		selectedClass="#{style.selectedClass}"
		onRowMouseDown="#{event.onRowMouseDown}"
		onRowMouseUp="#{event.onRowMouseUp}"
		onselectionchange="#{event.onselectionchange}"
		selection="#{scrollableDT.selection}">
		<f:facet name="header">
			<h:outputText value="facet header"></h:outputText>
		</f:facet>
		<rich:column sortExpression="#{sdt.int0}" sortable="true" id="colID">
			<f:facet name="header">
				<h:outputText value="#" />
			</f:facet>
			<h:outputText value="#{sdt.int0}"></h:outputText>
			<f:facet name="footer">
				<h:outputText value="#" />
			</f:facet>
		</rich:column>

		<rich:column sortExpression="#{sdt.str0}" sortable="false">
			<f:facet name="header">
				<h:outputText value="Text"></h:outputText>
			</f:facet>
			<h:outputText value="#{sdt.str0}"></h:outputText>
			<f:facet name="footer">
				<h:outputText value="Text"></h:outputText>
			</f:facet>
		</rich:column>

		<rich:column sortExpression="#{sdt.str1}">
			<f:facet name="header">
				<h:outputText value="Link"></h:outputText>
			</f:facet>
			<a4j:commandLink value="#{sdt.str1}" reRender="sdt"></a4j:commandLink>
			<f:facet name="footer">
				<h:outputText value="Link"></h:outputText>
			</f:facet>
		</rich:column>

		<rich:column sortExpression="#{sdt.str2}">
			<f:facet name="header">
				<h:outputText value="Select"></h:outputText>
			</f:facet>
			<h:selectOneMenu value="#{sdt.str2}">
				<f:selectItem itemLabel="select0" itemValue="select0" />
				<f:selectItem itemLabel="select1" itemValue="select1" />
				<f:selectItem itemLabel="select2" itemValue="select2" />
				<f:selectItem itemLabel="select3" itemValue="select3" />
				<f:selectItem itemLabel="select4" itemValue="select4" />
			</h:selectOneMenu>
			<f:facet name="footer">
				<h:outputText value="Select"></h:outputText>
			</f:facet>
		</rich:column>

		<rich:column sortExpression="#{sdt.str3}">
			<f:facet name="header">
				<h:outputText value="Icon"></h:outputText>
			</f:facet>
			<h:graphicImage value="#{sdt.str3}"></h:graphicImage>
			<f:facet name="footer">
				<h:outputText value="Icon"></h:outputText>
			</f:facet>
		</rich:column>

		<rich:column>
			<f:facet name="header">
				<h:outputText value="outputLink"></h:outputText>
			</f:facet>
			<h:outputLink value="http://www.jboss.com/">
				<f:verbatim>Link</f:verbatim>
			</h:outputLink>
			<f:facet name="footer">
				<h:outputText value="outputLink"></h:outputText>
			</f:facet>
		</rich:column>

		<rich:column sortExpression="#{sdt.date0}" sortable="true">
			<f:facet name="header">
				<h:outputText value="Date" />
			</f:facet>
			<h:outputText value="#{sdt.date0}"></h:outputText>
			<f:facet name="footer">
				<h:outputText value="Date" />
			</f:facet>
		</rich:column>

		<f:facet name="footer">
			<h:outputText value="facet footer"></h:outputText>
		</f:facet>
	</rich:scrollableDataTable>
	<br />
	<a4j:commandButton value="Show Current Selection" reRender="table"
		action="#{scrollableDT.takeSelection}"
		oncomplete="javascript:Richfaces.showModalPanel('panel');" />

	<rich:modalPanel id="panel" autosized="true" keepVisualState="false">
		<f:facet name="header">
			<h:outputText value="Selected Rows" />
		</f:facet>
		<f:facet name="controls">
			<a4j:commandLink style="cursor: pointer"
				onclick="javascript:Richfaces.hideModalPanel('panel')" value="X" />
		</f:facet>
		<h:panelGroup layout="block" styleClass="scrolls">
			<rich:dataTable value="#{scrollableDT.selectedRows}" var="sel"
				id="table">
				<rich:column sortBy="#{sel.int0}" sortable="true" id="colID">
					<f:facet name="header">
						<h:outputText value="#" />
					</f:facet>
					<h:outputText value="#{sel.int0}"></h:outputText>
				</rich:column>

				<rich:column sortBy="#{sel.str0}" sortable="false">
					<f:facet name="header">
						<h:outputText value="Text"></h:outputText>
					</f:facet>
					<h:outputText value="#{sel.str0}"></h:outputText>
				</rich:column>

				<rich:column sortBy="#{sel.str1}">
					<f:facet name="header">
						<h:outputText value="Link"></h:outputText>
					</f:facet>
					<a4j:commandLink value="#{sel.str1}" reRender="sdt"></a4j:commandLink>
				</rich:column>

				<rich:column sortBy="#{sel.str2}">
					<f:facet name="header">
						<h:outputText value="Select"></h:outputText>
					</f:facet>
					<h:selectOneMenu value="#{sel.str2}">
						<f:selectItem itemLabel="select0" itemValue="select0" />
						<f:selectItem itemLabel="select1" itemValue="select1" />
						<f:selectItem itemLabel="select2" itemValue="select2" />
						<f:selectItem itemLabel="select3" itemValue="select3" />
						<f:selectItem itemLabel="select4" itemValue="select4" />
					</h:selectOneMenu>
				</rich:column>

				<rich:column sortBy="#{sel.str3}">
					<f:facet name="header">
						<h:outputText value="Icon"></h:outputText>
					</f:facet>
					<h:graphicImage value="#{sel.str3}"></h:graphicImage>
				</rich:column>

				<rich:column>
					<f:facet name="header">
						<h:outputText value="outputLink"></h:outputText>
					</f:facet>
					<h:outputLink value="http://www.jboss.com/">
						<f:verbatim>Link</f:verbatim>
					</h:outputLink>
				</rich:column>

				<rich:column sortBy="#{sel.date0}" sortable="true">
					<f:facet name="header">
						<h:outputText value="Date" />
					</f:facet>
					<h:outputText value="#{sel.date0}"></h:outputText>
				</rich:column>
			</rich:dataTable>
		</h:panelGroup>
	</rich:modalPanel>
	<br />
	<h:panelGrid columns="2" border="1">
		<h:panelGroup>
			<h:outputText value="JS API test" style="FONT-WEIGHT: bold;"></h:outputText>
			<br />
			<a4j:commandLink
				onclick="$('formID:scrollableDataTableSubviewID:sdt').component.collapse(colID)"
				value="collapse('colID')"></a4j:commandLink>
			<br />
			<a4j:commandLink
				onclick="$('formID:scrollableDataTableSubviewID:sdt').component.expand(colID)"
				value="expand('colID')"></a4j:commandLink>
		</h:panelGroup>
		<h:panelGroup>
			<f:verbatim>
				<h:outputText value="Component control+JS API"
					style="FONT-WEIGHT: bold;"></h:outputText>
				<br />
				<a href="#" id="collapseID">collapseID</a>
				<br />
				<a href="#" id="expandID">expandID</a>
			</f:verbatim>
			<rich:componentControl attachTo="collapseID" event="onclick"
				for="sdt" operation="collapse">
				<f:param value="colID" name="colID" />
			</rich:componentControl>
			<rich:componentControl attachTo="expandID" event="onclick" for="sdt"
				operation="expand">
				<f:param value="colID" name="colID" />
			</rich:componentControl>
		</h:panelGroup>
	</h:panelGrid>
	<br />
</f:subview>
