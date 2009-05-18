<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<f:subview id="togglePanelSubviewID">

	<rich:togglePanel id="panel1" binding="#{togglePanel.htmlTogglePanel}"
		switchType="#{togglePanel.switchType}" initialState="asus"
		stateOrder="asus,blank" styleClass="#{style.styleClass}"
		style="width:300px!important;#{style.style};"
		onclick="#{event.onclick}" ondblclick="#{event.ondblclick}"
		onkeydown="#{event.onkeydown}" onkeypress="#{event.onkeypress}"
		onkeyup="#{event.onkeyup}" onmousedown="#{event.onmousedown}"
		onmousemove="#{event.onmousemove}" onmouseout="#{event.onmouseout}"
		onmouseover="#{event.onmouseover}" onmouseup="#{event.onmouseup}"
		immediate="#{togglePanel.immediate}"
		valueChangeListener="#{togglePanel.valueChangeListener}">
		<f:facet name="blank">
			<rich:panel>
				<f:facet name="header">
					<h:panelGroup>
						<rich:toggleControl id="toggleControl_blank"
							for="togglePanelSubviewID:panel1">
							<h:outputText value="Expand" style="font-weight: bold;" />
							<h:graphicImage url="/pics/collapse.gif"
								style="border-width: 0px;" />
						</rich:toggleControl>
					</h:panelGroup>
				</f:facet>
			</rich:panel>
		</f:facet>

		<f:facet name="asus">
			<rich:panel style="overflow: auto">
				<f:facet name="header">
					<h:panelGroup>
						<rich:toggleControl id="toggleControl_panel1"
							for="togglePanelSubviewID:panel1">
							<h:outputText value="Collapse" style="font-weight: bold;" />
							<h:graphicImage url="/pics/expand.gif" style="border-width: 0px;" />
						</rich:toggleControl>
					</h:panelGroup>
				</f:facet>
				<h:panelGrid columns="2" border="0"
					style="width: 100%;background-color: white;">
					<h:graphicImage url="/pics/asus.jpg" height="300" width="300"
						alt="asus.jpg" />
					<h:inputText value="input" required="true" />
					<h:panelGroup>
						<h:outputText style="font: 18px;font-weight: bold;"
							value="Asus F 3 Tc" />
						<f:verbatim>
								Processor:  AMD Turion 64 X 2 - 1600 Mhz<br />
								RAM: 1024 Mb<br />
								HDD: 100 Gb<br />
								Screen: 15.4 WXGA<br />
								Video: NVIDIA GeForce Go 7300<br />
								Drive: DVD- RW DL<br />
						</f:verbatim>
						<h:outputLink value="http://www.jboss.com/">
							<f:verbatim>Link</f:verbatim>
						</h:outputLink>
					</h:panelGroup>
				</h:panelGrid>
			</rich:panel>
		</f:facet>
	</rich:togglePanel>

	<f:verbatim>
		<br />
		<br />
	</f:verbatim>

	<rich:togglePanel id="panel2" switchType="#{togglePanel.switchType}"
		initialState="#{togglePanel.initialState}"
		stateOrder="#{togglePanel.stateOrder}" onclick="#{event.onclick}"
		ondblclick="#{event.ondblclick}" onkeydown="#{event.onkeydown}"
		onkeypress="#{event.onkeypress}" onkeyup="#{event.onkeyup}"
		onmousedown="#{event.onmousedown}" onmousemove="#{event.onmousemove}"
		onmouseout="#{event.onmouseout}" onmouseover="#{event.onmouseover}"
		onmouseup="#{event.onmouseup}" immediate="#{togglePanel.immediate}">
		<f:facet name="asus">
			<rich:panel>
				<f:facet name="header">
					<h:panelGroup>
						<h:outputText value="Customizable toggle panel"
							style="font-weight: bold;" />
						<rich:toggleControl id="toggleControl_panel_1"
							for="togglePanelSubviewID:panel2">
							<h:outputText value="Next"></h:outputText>
							<h:graphicImage url="/pics/expand.gif" style="border-width: 0px;" />
						</rich:toggleControl>
					</h:panelGroup>
				</f:facet>
				<h:panelGrid columns="2" border="0"
					style="width: 100%;background-color: white;">
					<h:graphicImage url="/pics/asus.jpg" height="300" width="300"
						alt="asus.jpg" />
					<h:inputText value="input" required="true" />
					<h:panelGroup>
						<h:outputText style="font: 18px;font-weight: bold;"
							value="Asus F 3 Tc" />
						<f:verbatim>
								Processor:  AMD Turion 64 X 2 - 1600 Mhz<br />
								RAM: 1024 Mb<br />
								HDD: 100 Gb<br />
								Screen: 15.4 WXGA<br />
								Video: NVIDIA GeForce Go 7300<br />
								Drive: DVD- RW DL<br />
						</f:verbatim>
					</h:panelGroup>
				</h:panelGrid>
			</rich:panel>
		</f:facet>

		<f:facet name="benq">
			<rich:panel>
				<f:facet name="header">
					<h:panelGroup>
						<h:outputText value="Customizable toggle panel"
							style="font-weight: bold;" />
						<rich:toggleControl id="toggleControl_panel_2"
							for="togglePanelSubviewID:panel2">
							<h:outputText value="Next"></h:outputText>
							<h:graphicImage url="/pics/expand.gif" style="border-width: 0px;" />
						</rich:toggleControl>
					</h:panelGroup>
				</f:facet>
				<h:panelGrid columns="2" border="0"
					style="width: 100%;background-color: yellow;">
					<h:graphicImage url="/pics/benq.jpg" height="300" width="300"
						alt="benq.jpg" />
					<h:panelGroup>
						<h:outputText style="font: 18px;font-weight: bold;"
							value="BenQ A 52" />
						<f:verbatim>
								Processor:  Core Duo T2250 (1.73GHz)<br />
								RAM: 1024 Mb<br />
								HDD: 100 Gb<br />
								Screen: 15.4 WXGA<br />
								Video: ATI Mobility Radeon X 200<br />
								Drive: DVD- RW D<br />
						</f:verbatim>
					</h:panelGroup>
				</h:panelGrid>
			</rich:panel>
		</f:facet>

		<f:facet name="toshiba">
			<rich:panel>
				<f:facet name="header">
					<h:panelGroup>
						<h:outputText value="Customizable toggle panel"
							style="font-weight: bold;" />
						<rich:toggleControl id="toggleControl_panel_3"
							for="togglePanelSubviewID:panel2">
							<h:outputText value="Next"></h:outputText>
							<h:graphicImage url="/pics/expand.gif" style="border-width: 0px;" />
						</rich:toggleControl>
					</h:panelGroup>
				</f:facet>
				<h:panelGrid columns="2" border="0"
					style="width: 100%;background-color: orange;">
					<h:graphicImage url="/pics/toshiba.jpg" height="300" width="300"
						alt="toshiba.jpg" />
					<h:panelGroup>
						<h:outputText style="font: 18px;font-weight: bold;"
							value="Toshiba Satellite A 100-784" />
						<f:verbatim>
								Processor:  Intel Core Duo T2250 - 1.73GHz<br />
								RAM: 1024 Mb<br />
								HDD: 100 Gb<br />
								Screen: 15.4 WXGA<br />
								Video: Intel Graphics Media 950<br />
								Drive: DVD- RW DL<br />
						</f:verbatim>
					</h:panelGroup>
				</h:panelGrid>
			</rich:panel>
		</f:facet>
	</rich:togglePanel>

	<ui:debug hotkey="L"></ui:debug>
</f:subview>
