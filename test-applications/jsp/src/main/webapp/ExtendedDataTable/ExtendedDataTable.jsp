<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<f:subview id="extendedDataTableSubviewID">
	<style type="text/css">
.leftColumn {
	width: 50%;
	height: 100%;
}

.rightColumn {
	width: 50%;
	height: 100%;
}

table {
	cell-padding: 10;
	cell-spacing: 10;
}

#mainPanel {
	width: 100%;
	height: 100%;
}

#leftPanel {
	width: 100%;
	height: 100%;
}

#rightPanel {
	width: 100%;
	height: 100%;
}
.dataTableHeader{
	align: center;
}
</style>
	<rich:extendedDataTable id="demoTable"
		value="#{extendedDataTableBean.dataModel}" var="patient"
		style="margin: 0 auto;" 
		rows="#{extendedDataTableControlBean.rowsNumber}"
		width="#{extendedDataTableControlBean.width}"
		height="#{extendedDataTableControlBean.height}"
		selectedClass="dataTableSelectedRow" footerClass="demo-footer"
		sortMode="#{extendedDataTableControlBean.sortMode}"
		selectionMode="#{extendedDataTableControlBean.selectionMode}"
		selection="#{extendedDataTableBean.selection}" rowKeyVar="rkvar"
		tableState="#{extendedDataTableBean.tableState}"
		binding="#{extendedDataTableControlBean.extDTable}"
		onclick="#{event.onclick}" ondblclick="#{event.ondblclick}"
		onkeydown="#{event.onkeydown}" onkeypress="#{event.onkeypress}"
		onkeyup="#{event.onkeyup}" onmousedown="#{event.onmousedown}"
		onmousemove="#{event.onmousemove}" onmouseout="#{event.onmouseout}"
		onmouseover="#{event.onmouseover}" onmouseup="#{event.onmouseup}"
		onRowClick="#{event.onRowClick}"
		onRowDblClick="#{event.onRowDblClick}"
		onRowMouseDown="#{event.onRowMouseDown}"
		onRowMouseMove="#{event.onRowMouseMove}"
		onRowMouseOut="#{event.onRowMouseOut}"
		onRowMouseOver="#{event.onRowMouseOver}"
		onRowMouseUp="#{event.onRowMouseUp}"
		onselectionchange="#{event.onselectionchange}"
		border="#{extendedDataTableControlBean.border}"
		cellpadding="#{extendedDataTableControlBean.cellpadding}"
		cellspacing="#{extendedDataTableControlBean.cellspacing}"
		dir="#{extendedDataTableControlBean.dir}"
		first="#{extendedDataTableControlBean.first}"
		frame="#{extendedDataTableControlBean.frame}"
		groupingColumn="#{extendedDataTableControlBean.groupingColumn}"
		rendered="#{extendedDataTableControlBean.rendered}">
		
		<rich:column id="id" headerClass="dataTableHeader" width="25%"
			label="id" sortable="true" sortBy="#{patient.id}"
			filterBy="#{patient.id}" filterEvent="onkeyup">
			<f:facet name="header">
				<h:outputText value="id" />
			</f:facet>
			<h:outputText value="#{patient.id}" />
		</rich:column>
		<rich:column id="firstName" headerClass="dataTableHeader" width="25%"
			label="FirstName" sortable="true" sortBy="#{patient.firstName}"
			filterBy="#{patient.firstName}" filterEvent="onkeyup">
			<f:facet name="header">
				<h:outputText value="First name" />
			</f:facet>
			<h:outputText id="text" value="#{patient.firstName}">
				<rich:toolTip value="#{patient.firstName}" />
			</h:outputText>
		</rich:column>
		<rich:column id="lastName" headerClass="dataTableHeader" width="25%"
			label="Last name" sortable="true" sortBy="#{patient.lastName}"
			filterBy="#{patient.lastName}" filterEvent="onkeyup">
			<f:facet name="header">
				<h:outputText value="Last name" />
			</f:facet>
			<h:outputText value="#{patient.lastName}" />
		</rich:column>
		<rich:column id="admissionDate" headerClass="dataTableHeader"
			width="25%" label="Admission date" sortable="true"
			sortBy="#{patient.admissionDate}">
			<f:facet name="header">
				<h:outputText value="Admission date" />
			</f:facet>
			<h:outputText value="#{patient.admissionDate}" />
		</rich:column>
		<a4j:support event="onselectionchange"
			rendered="#{extendedDataTableBean.a4jRendered}"
			action="#{extendedDataTableBean.takeSelection}"
			reRender="selectedPatients">
		</a4j:support>
	</rich:extendedDataTable>
	<rich:datascroller style="width: #{extendedDataTableControlBean.width}"
		rendered="#{extendedDataTableControlBean.paginated}" for="demoTable">
	</rich:datascroller>
</f:subview>