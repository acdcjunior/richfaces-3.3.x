<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<f:subview id="dataFilterSliderSubviewID">

	<rich:dataFilterSlider sliderListener="#{dfs.doSlide}" action="#{dfs.act}" actionListener="#{dfs.actListener}"
		rendered="#{dfs.rendered}" binding="#{dfs.htmlDataFilterSlider}"
		for="carList" forValRef="inventoryList.carInventory"
		filterBy="getMileage" 
		styleClass="#{style.styleClass}" rangeStyleClass="#{style.rangeStyleClass}" trailerStyleClass="#{style.trailerStyleClass}" style="#{style.style}" fieldStyleClass="#{style.fieldStyleClass}" trackStyleClass="#{style.trackStyleClass}" handleStyleClass="#{style.handleStyleClass}"  
		storeResults="true"
		startRange="10000" endRange="60000" increment="10000"
		manualInput="true" width="400px" 
		trailer="true" handleValue="10000" id="dfsID"
		onbeforedomupdate="#{event.onbeforedomupdate}"
		onclick="#{event.onclick}"
		oncomplete="#{event.oncomplete}" ondblclick="#{event.ondblclick}"
		onerror="#{event.onerror}" onkeydown="#{event.onkeydown}"
		onkeypress="#{event.onkeypress}" onkeyup="#{event.onkeyup}"
		onmousedown="#{event.onmousedown}" onmousemove="alert('onmousemove');"
		onmouseout="#{event.onmouseout}" onmouseover="#{event.onmouseover}"
		onmouseup="#{event.onmouseup}" 
		onchange="#{event.onchange}" onslide="#{event.onslide}" submitOnSlide="true"
		oninputkeydown="#{event.oninputkeydown}"
		oninputkeypress="#{event.oninputkeypress}"
		oninputkeyup="#{event.oninputkeyup}"
		onSlideSubmit="#{event.onSlideSubmit}">
	</rich:dataFilterSlider>

	<h:panelGrid id="list-body">
		<h:dataTable id="carIndex" rows="10" 
			binding="#{inventoryList.carMakeIndexUIData}"
			value="#{inventoryList.carMakeIndex}" var="category"
			styleClass="list-table1" columnClasses="column-index"
			rowClasses="list-row3">

			<h:column>
				<a4j:commandLink actionListener="#{inventoryList.filterCarList}"
					reRender="carList">
					<h:outputText value="#{category}" />
					<f:attribute name="filterRule" value="showTable" />

				</a4j:commandLink>
			</h:column>
		</h:dataTable>

		<h:dataTable id="carList" rows="10" rendered="#{dfs.rendered}"
			value="#{inventoryList.carInventory}" var="category"
			rowClasses="list-row1, list-row2" columnClasses="column"
			headerClass="list-header" styleClass="list-table2">
			<h:column>
				<f:facet name="header">
					<h:outputText styleClass="headerText" value="Make" />
				</f:facet>
				<h:outputText value="#{category.make}" />
			</h:column>

			<h:column>
				<f:facet name="header">
					<h:outputText styleClass="headerText" value="Model" />
				</f:facet>
				<h:outputText value="#{category.model}" />
			</h:column>

			<h:column>
				<f:facet name="header">
					<h:outputText styleClass="headerText"
						value="#{inventoryList.priceColumnName}" />
				</f:facet>
				<h:outputText value="#{category.price}" />
			</h:column>

			<h:column>
				<f:facet name="header">
					<h:outputText styleClass="headerText"
						value="#{inventoryList.mileageColumnName}" />
				</f:facet>
				<h:outputText value="#{category.mileage}" />
			</h:column>
		</h:dataTable>
	</h:panelGrid>
	<h:outputText value="click reRender for update page(RF-1365)" />
	<h:commandButton value="reRender" />

	<h:panelGrid columns="2">
		<a4j:commandButton value="action" rendered="actionDFSID" style=" width : 95px;"></a4j:commandButton>
		<h:outputText id="actionDFSID" value="#{dfs.action}" />
		
		<a4j:commandButton value="actionListener" rendered="actionListenerDFSID" style=" width : 95px;"></a4j:commandButton>
		<h:outputText id="actionListenerDFSID" value="#{dfs.actionListener}" />
	</h:panelGrid>
</f:subview>
