<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<f:subview id="extendedDataTableSubviewID">
	<div>The &lt;rich:extendedDataTable&gt; component is used for
	tables extending standard component &lt;rich:dataTable&gt;.</div>
	<br />
	<rich:extendedDataTable id="extendedDataTableID"
		value="#{extendedDataTable.value}" var="car"
		onclick="markEventAsWorkable('onclick')"
		ondblclick="markEventAsWorkable('ondblclick')"
		onkeydown="markEventAsWorkable('onkeydown')"
		onkeypress="markEventAsWorkable('onkeypress')"
		onkeyup="markEventAsWorkable('onkeyup')"
		onmousedown="markEventAsWorkable('onmousedown')"
		onmousemove="markEventAsWorkable('onmousemove')"
		onmouseout="markEventAsWorkable('onmouseout')"
		onmouseover="markEventAsWorkable('onmouseover')"
		onmouseup="markEventAsWorkable('onmouseup')"
		onRowClick="markEventAsWorkable('onRowClick')"
		onRowDblClick="markEventAsWorkable('onRowDblClick')"
		onRowMouseDown="markEventAsWorkable('onRowMouseDown')"
		onRowMouseMove="markEventAsWorkable('onRowMouseMove')"
		onRowMouseOut="markEventAsWorkable('onRowMouseOut')"
		onRowMouseOver="markEventAsWorkable('onRowMouseOver')"
		onRowMouseUp="markEventAsWorkable('onRowMouseUp')"
		onselectionchange="markEventAsWorkable('onselectionchange')"
		activeClass="activeClass-activeClass"
		captionClass="captionClass-captionClass"
		captionStyle="captionStyle:captionStyle"
		columnClasses="columnClasses-columnClasses"
		footerClass="footerClass-footerClass"
		headerClass="headerClass-headerClass"
		rowClasses="rowClasses-rowClasses"
		selectedClass="selectedClass-selectedClass"
		styleClass="styleClass-styleClass" style="style:style">
		<f:facet name="caption">
			<h:outputText value="CAPTION" />
		</f:facet>
		<f:facet name="header">
			<h:outputText value="HEADER" />
		</f:facet>
		<rich:column id="makeID" label="Make" sortBy="#{car.make}"
			filterBy="#{car.make}">
			<f:facet name="header">
				<h:outputText value="Make" />
			</f:facet>
			<h:outputText value="#{car.make}" />
		</rich:column>
		<rich:column id="modelID" label="Model" sortBy="#{car.model}"
			filterBy="#{car.model}">
			<f:facet name="header">
				<h:outputText value="Model" />
			</f:facet>
			<h:outputText value="#{car.model}" />
		</rich:column>
		<rich:column id="priceID" label="Price" sortBy="#{car.price}">
			<f:facet name="header">
				<h:outputText value="Price" />
			</f:facet>
			<h:outputText value="#{car.price}" />
		</rich:column>
		<f:facet name="footer">
			<h:outputText value="Created by ADubovsky@" />
		</f:facet>
	</rich:extendedDataTable>
	<br />
</f:subview>