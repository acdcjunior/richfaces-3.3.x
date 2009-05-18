<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<f:subview id="tTreeStraightforwardSubviewID">
	<style type="text/css">
.LeftTreePane {
	
}

.RightTreePane {
	
}

.TreeContainer {
	overflow: auto;
	height: 400px;
	border: 3px inset gray;
}
</style>

	<a4j:outputPanel id="tTreeRNAPanelID">
		<h:panelGrid columns="1"
			rendered="#{pVisability.tTreeStraightforwardSubviewID}">
			<h:outputText value="Tree with recursiveTreeNodesAdaptor"
				style="color: red" />
			<rich:tree>
				<rich:treeNodesAdaptor nodes="#{tTreeRNA.treeRNAroots}" var="root">
					<rich:treeNode>
						<h:outputText value="#{root.name}" />
					</rich:treeNode>
					<rich:recursiveTreeNodesAdaptor var="dir" roots="#{root.dirs}"
						nodes="#{dir.dirs}" recursionOrder="#{tTreeRNA.recursionOrder}">
						<rich:treeNode>
							<h:outputText value="Node 1 - #{dir.name}" />
						</rich:treeNode>
						<rich:treeNodesAdaptor id="adapt1" nodes="#{dir.packages}"
							var="package1">
							<rich:treeNode>
								<h:outputText value="Adaptor 1 - #{package1.name}" />
							</rich:treeNode>
						</rich:treeNodesAdaptor>
						<rich:treeNodesAdaptor id="adapt2" nodes="#{dir.packages}"
							var="package2">
							<rich:treeNode>
								<h:outputText value="Adaptor 2 - #{package2.name}" />
							</rich:treeNode>
						</rich:treeNodesAdaptor>
						<rich:treeNodesAdaptor id="adapt3" nodes="#{dir.packages}"
							var="package3">
							<rich:treeNode>
								<h:outputText value="Adaptor 3 - #{package3.name}" />
							</rich:treeNode>
						</rich:treeNodesAdaptor>
					</rich:recursiveTreeNodesAdaptor>
				</rich:treeNodesAdaptor>
			</rich:tree>
			<rich:separator></rich:separator>
			<h:panelGrid columns="2">
				<h:outputText value="recursionOrder: " />
				<h:selectOneRadio value="#{tTreeRNA.recursionOrder}"
					onchange="submit();">
					<f:selectItem itemLabel="first" itemValue="first" />
					<f:selectItem itemLabel="last" itemValue="last" />
					<f:selectItem itemLabel="Adaptor 2" itemValue="adapt2" />
				</h:selectOneRadio>
			</h:panelGrid>
			<rich:separator height="10" />
		</h:panelGrid>
	</a4j:outputPanel>

	<a4j:outputPanel id="tTreeDNDPanelID">
		<h:panelGrid columns="1"
			rendered="#{pVisability.tTreeDefaultSubviewID}">
			<h:outputText value="Tree with Drag and Drop functionality"
				style="color: red" />

			<rich:dragIndicator id="treeIndicator">
				<f:facet name="single">
					<f:verbatim>{marker} {nodeParam}({treeParam})</f:verbatim>
				</f:facet>
			</rich:dragIndicator>

			<h:panelGrid columns="2" columnClasses="LeftTreePane,RightTreePane">

				<h:panelGroup id="leftContainer" layout="block"
					styleClass="TreeContainer">
					<h:outputText escape="false"
						value="Selected Node: <b>#{tTreeDND.leftSelectedNodeTitle}</b>"
						id="selectedNodeL" />

					<rich:tree id="leftTree" style="width:300px"
						nodeSelectListener="#{tTreeDND.processLSelection}"
						reRender="selectedNodeL" ajaxSubmitSelection="true"
						switchType="client" value="#{tTreeDND.treeNodeLeft}"
						changeExpandListener="#{tTreeDND.onExpand}"
						binding="#{tTreeDND.leftTree}"
						onselected="window.status='selectedNode: '+event.selectedNode;"
						onexpand="window.status='expandedNode: '+event.expandedNode"
						oncollapse="window.status='collapsedNode: '+event.collapsedNode"
						dropListener="#{tTreeDND.onDrop}"
						dragListener="#{tTreeDND.onDrag}" dragIndicator="treeIndicator"
						acceptedTypes="treeNodeR" dragType="treeNodeL" rowKeyVar="key"
						var="item" showConnectingLines="false">

						<rich:dndParam name="treeParam" value="leftTree" />
					</rich:tree>

				</h:panelGroup>

				<h:panelGroup id="rightContainer" layout="block"
					styleClass="TreeContainer">
					<h:outputText escape="false"
						value="Selected Node: <b>#{tTreeDND.rightSelectedNodeTitle}</b>"
						id="selectedNodeR" />

					<rich:tree id="rightTree" style="width:300px"
						nodeSelectListener="#{tTreeDND.processRSelection}"
						reRender="selectedNodeR" ajaxSubmitSelection="true"
						switchType="client" value="#{tTreeDND.treeNodeRight}"
						changeExpandListener="#{tTreeDND.onExpand}"
						binding="#{tTreeDND.rightTree}"
						onselected="window.status='selectedNode: '+event.selectedNode;"
						onexpand="window.status='expandedNode: '+event.expandedNode"
						oncollapse="window.status='collapsedNode: '+event.collapsedNode"
						rowKeyVar="key" dropListener="#{tTreeDND.onDrop}"
						dragListener="#{tTreeDND.onDrag}" dragIndicator="treeIndicator"
						acceptedTypes="treeNodeL" dragType="treeNodeR" var="item">

						<rich:dndParam name="treeParam" value="rightTree" />
					</rich:tree>
				</h:panelGroup>
			</h:panelGrid>
			<rich:separator height="10" />
		</h:panelGrid>
	</a4j:outputPanel>
</f:subview>