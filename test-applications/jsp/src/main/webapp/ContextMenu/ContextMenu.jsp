<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<f:subview id="contextMenuSubviewID">
	<h:panelGrid columns="2">
		<rich:panel style="width: 130px; height: 50px; background-color: #98FB98;" id="firstPanelId">
			<h:outputText value="panel with contextMenu(DEFAULT)" /> <f:verbatim><br /></f:verbatim>
			<h:outputText value="testing events" />
			<rich:contextMenu id="contextMenuDefaultID" binding="#{contextMenu.htmlContextMenu}" submitMode="ajax" disableDefaultMenu="#{contextMenu.disableDefaultMenu}" style="#{style.style}" styleClass="#{style.styleClass}" disabledItemClass="#{style.disabledItemClass}" disabledItemStyle="#{style.disabledItemStyle}" itemClass="#{style.itemClass}" itemStyle="#{style.itemStyle}" selectItemStyle="#{style.selectItemStyle}" selectItemClass="#{style.selectItemClass}" 
				oncollapse="#{event.oncollapse}" onexpand="#{event.onexpand}" ongroupactivate="#{event.ongroupactivate}" onitemselect="#{event.onitemselect}" onmousemove="#{event.onmousemove}" onmouseout="#{event.onmouseout}" onmouseover="#{event.onmouseover}">
				<rich:menuItem icon="/pics/header.png" value="abc" reRender="cmInfoID" style="#{style.styleA}" styleClass="#{style.styleClassA}">
					<f:param name="cmdParam" value="abc" />
				</rich:menuItem>	
				<rich:menuItem onbeforedomupdate="#{event.onbeforedomupdate}" onclick="#{event.onclick}" oncomplete="#{event.oncomplete}" onmousedown="#{event.onmousedown}" onmousemove="#{event.onmousemove}" onmouseout="#{event.onmouseout}" onmouseover="#{event.onmouseover}" onmouseup="#{event.onmouseup}" onselect="#{event.onselect}"><h:outputText value="event item"/></rich:menuItem>
				<rich:menuItem icon="/pics/header.png" value="JSAPI Hide onmousemove" onmousemove="$('formID:contextMenuSubviewID:contextMenuDefaultID').component.hide()" reRender="cmInfoID">
					<f:param name="cmdParam" value="hide" />
				</rich:menuItem>
				<rich:menuSeparator />
				<rich:menuItem icon="/pics/info.gif" value="a" reRender="cmInfoID" iconClass="#{style.iconClassA}" iconStyle="#{style.iconStyleA}" selectClass="#{style.selectClassA}" styleClass="#{style.styleClassA}" selectStyle="#{style.selectStyleA}" style="#{style.styleA}">
					<f:param name="cmdParam" value="a" />
				</rich:menuItem>
				<rich:menuItem icon="/pics/info.gif" value=" b" reRender="cmInfoID">
					<f:param name="cmdParam" value="b" />
				</rich:menuItem>
				<rich:menuItem icon="/pics/info.gif" value="c" reRender="cmInfoID">
					<f:param name="cmdParam" value="c" />
				</rich:menuItem>
				<rich:menuItem icon="/pics/info.gif" reRender="cmInfoID">
					<h:outputLink value="http://www.jboss.com/"><f:verbatim>Link</f:verbatim></h:outputLink>
				</rich:menuItem>
				<rich:menuGroup value="menuGroup">
					<rich:menuItem icon="/pics/fatal.gif" value="a" reRender="cmInfoID">
						<f:param name="cmdParam" value="a" />
					</rich:menuItem>
					<rich:menuItem icon="/pics/fatal.gif" value="b" reRender="cmInfoID">
						<f:param name="cmdParam" value="b" />
					</rich:menuItem>
					<rich:menuItem icon="/pics/fatal.gif" value="c" reRender="cmInfoID">
						<f:param name="cmdParam" value="c" />
					</rich:menuItem>
					<rich:menuItem icon="/pics/fatal.gif" value="d" reRender="cmInfoID">
						<f:param name="cmdParam" value="d" />
					</rich:menuItem>
				</rich:menuGroup>
			</rich:contextMenu>
		</rich:panel>

		<rich:panel id="cmPanelID" style="width: 130px; height: 50px; background-color: #98FB98;">
			<h:outputText value="panel with contextMenu(attachTo)" />
		</rich:panel>

		<rich:contextMenu attachTo="cmPanelID" id="contextMenuID" attachTiming="onavailable" submitMode="#{contextMenu.submitMode}"
				event="#{contextMenu.event}" disableDefaultMenu="#{contextMenu.disableDefaultMenu}" rendered="#{contextMenu.rendered}"
				hideDelay="#{contextMenu.hideDelay}" showDelay="#{contextMenu.showDelay}" popupWidth="#{contextMenu.popupWidth}">
				<rich:menuItem icon="/pics/header.png" value="abc" reRender="cmInfoID">
					<f:param name="cmdParam" value="abc" />
				</rich:menuItem>
				<rich:menuSeparator />
				<rich:menuItem icon="/pics/info.gif" value="action" reRender="cmInfoID">
					<f:param name="cmdParam" value="action" />
				</rich:menuItem>
				<rich:menuItem icon="/pics/info.gif" value="actionListener" actionListener="#{contextMenu.actionListener}" reRender="cmInfoID">
				</rich:menuItem>
				<rich:menuItem icon="/pics/info.gif" value="ajaxSingle" ajaxSingle="true" reRender="cmInfoID">
					<f:param name="cmdParam" value="ajaxSingle" />
				</rich:menuItem>
				<rich:menuItem icon="/pics/fatal.gif" value="disabled" disabled="true" reRender="cmInfoID">
					<f:param name="cmdParam" value="disabled" />
				</rich:menuItem>
				<rich:menuItem icon="/pics/fatal.gif" value="immediate" immediate="true" action="submit();">
					<f:param name="cmdParam" value="immedeate" />
					<h:inputText value="#{contextMenu.inputText}" />
				</rich:menuItem>
				<rich:menuItem icon="/pics/fatal.gif" value="submitMode: none" submitMode="none" reRender="cmInfoID">
					<f:param name="cmdParam" value="none" />
				</rich:menuItem>
				<rich:menuItem icon="/pics/fatal.gif" value="submitMode: server" submitMode="server" reRender="cmInfoID">
					<f:param name="cmdParam" value="server" />
				</rich:menuItem>
				<rich:menuItem icon="/pics/fatal.gif" value="submitMode: ajax" submitMode="ajax" reRender="cmInfoID">
					<f:param name="cmdParam" value="ajax" />
				</rich:menuItem>
				<rich:menuItem value="select">
					<h:selectOneMenu value="#{contextMenu.selectOneMenu}" onchange="submit();">
						<f:selectItem itemLabel="item1" itemValue="item1" />
						<f:selectItem itemLabel="item2" itemValue="item2" />
					</h:selectOneMenu>
				</rich:menuItem>
				<rich:menuGroup value="menuGroup">
					<rich:menuItem icon="/pics/fatal.gif" value="a" reRender="cmInfoID">
						<f:param name="cmdParam" value="a" />
					</rich:menuItem>
					<rich:menuItem icon="/pics/fatal.gif" value="b" reRender="cmInfoID">
						<f:param name="cmdParam" value="b" />
					</rich:menuItem>
					<rich:menuItem icon="/pics/fatal.gif" value="c" reRender="cmInfoID">
						<f:param name="cmdParam" value="c" />
					</rich:menuItem>
					<rich:menuItem icon="/pics/fatal.gif" value="d" reRender="cmInfoID">
						<f:param name="cmdParam" value="d" />
					</rich:menuItem>
				</rich:menuGroup>
			</rich:contextMenu>
		<a4j:commandLink onclick="$('formID:contextMenuSubviewID:contextMenuDefaultID').component.show(event,'context')" value="JSAPI Show"></a4j:commandLink>
	</h:panelGrid>
	
	<a4j:commandButton value="reRender" reRender="cmInfoID"></a4j:commandButton>
	
	<h:panelGrid id="cmInfoID" columns="2">
		<h:outputText value="Select items: " />
		<h:outputText value="#{contextMenu.info}" style="color: red" />
	</h:panelGrid>
		
	<h:panelGrid id="pgcmTestID" columns="3">
		<h:selectOneMenu value="#{contextMenu.selectOneMenu}">
			<f:selectItem itemLabel="select1" itemValue="select1" />
			<f:selectItem itemLabel="select2" itemValue="select2" />
			<rich:contextMenu attached="#{contextMenu.attached}" submitMode="#{contextMenu.submitMode}" event="#{contextMenu.event}"
				disableDefaultMenu="#{contextMenu.disableDefaultMenu}" rendered="#{contextMenu.rendered}" hideDelay="#{contextMenu.hideDelay}"
				showDelay="#{contextMenu.showDelay}" popupWidth="#{contextMenu.popupWidth}" attachTo="pgcmTestID">
				<rich:menuItem icon="/pics/header.png" value="select" reRender="cmInfoID">
					<f:param name="cmdParam" value="select" />
				</rich:menuItem>
				<rich:menuSeparator />
				<rich:menuItem icon="/pics/info.gif" value="action" action="alert('action work')" reRender="cmInfoID">
					<f:param name="cmdParam" value="action" />
				</rich:menuItem>
				<rich:menuItem icon="/pics/info.gif" value="actionListener" actionListener="#{contextMenu.actionListener}" reRender="cmInfoID">
				</rich:menuItem>
				<rich:menuItem icon="/pics/info.gif" value="ajaxSingle" ajaxSingle="true" reRender="cmInfoID">
					<f:param name="cmdParam" value="ajaxSingle" />
				</rich:menuItem>
				<rich:menuItem icon="/pics/fatal.gif" value="disabled" disabled="true" reRender="cmInfoID">
					<f:param name="cmdParam" value="disabled" />
				</rich:menuItem>
				<rich:menuItem icon="/pics/fatal.gif" value="immediate" immediate="true" action="submit();">
					<f:param name="cmdParam" value="immedeate" />
					<h:inputText value="#{contextMenu.inputText}" />
				</rich:menuItem>
				<rich:menuItem icon="/pics/fatal.gif" value="submitMode: none" submitMode="none" reRender="cmInfoID">
					<f:param name="cmdParam" value="none" />
				</rich:menuItem>
				<rich:menuItem icon="/pics/fatal.gif" value="submitMode: server" submitMode="server" reRender="cmInfoID">
					<f:param name="cmdParam" value="server" />
				</rich:menuItem>
				<rich:menuItem icon="/pics/fatal.gif" value="submitMode: ajax" submitMode="ajax" reRender="cmInfoID">
					<f:param name="cmdParam" value="ajax" />
				</rich:menuItem>
				<rich:menuItem value="select">
					<h:selectOneMenu value="#{contextMenu.selectOneMenu}" onchange="submit();">
						<f:selectItem itemLabel="item1" itemValue="item1" />
						<f:selectItem itemLabel="item2" itemValue="item2" />
					</h:selectOneMenu>
				</rich:menuItem>
			</rich:contextMenu>
		</h:selectOneMenu>

		<rich:tabPanel id="tpcmTestID" switchType="client">
			<rich:tab id="t1cmTestID" label="tab1">
				<rich:contextMenu submitMode="ajax" disableDefaultMenu="#{contextMenu.disableDefaultMenu}">
					<rich:menuItem icon="/pics/header.png" value="tab1" reRender="cmInfoID">
						<f:param name="cmdParam" value="tab1" />
					</rich:menuItem>
					<rich:menuSeparator />
					<rich:menuItem icon="/pics/info.gif" value="a" reRender="cmInfoID">
						<f:param name="cmdParam" value="a" />
					</rich:menuItem>
					<rich:menuItem icon="/pics/info.gif" value=" b" reRender="cmInfoID">
						<f:param name="cmdParam" value="b" />
					</rich:menuItem>
					<rich:menuItem icon="/pics/info.gif" value="c" reRender="cmInfoID">
						<f:param name="cmdParam" value="c" />
					</rich:menuItem>
				</rich:contextMenu>
				<h:outputText value="text1" />
			</rich:tab>
			<rich:tab id="t2cmTestID" label="tab2">
				<rich:contextMenu submitMode="ajax" disableDefaultMenu="#{contextMenu.disableDefaultMenu}" attachTo="t2cmTestID">
					<rich:menuItem icon="/pics/header.png" value="tab2" reRender="cmInfoID">
						<f:param name="cmdParam" value="tab2" />
					</rich:menuItem>
					<rich:menuSeparator />
					<rich:menuItem icon="/pics/info.gif" value="a" reRender="cmInfoID">
						<f:param name="cmdParam" value="a" />
					</rich:menuItem>
					<rich:menuItem icon="/pics/info.gif" value=" b" reRender="cmInfoID">
						<f:param name="cmdParam" value="b" />
					</rich:menuItem>
					<rich:menuItem icon="/pics/info.gif" value="c" reRender="cmInfoID">
						<f:param name="cmdParam" value="c" />
					</rich:menuItem>
				</rich:contextMenu>
				<h:outputText value="text2" />
			</rich:tab>
			<rich:tab id="t3cmTestID" label="tab3">
				<rich:contextMenu submitMode="ajax" disableDefaultMenu="#{contextMenu.disableDefaultMenu}">
					<rich:menuItem icon="/pics/header.png" value="tab3" reRender="cmInfoID">
						<f:param name="cmdParam" value="tab3" />
					</rich:menuItem>
					<rich:menuSeparator />
					<rich:menuItem icon="/pics/info.gif" value="a" reRender="cmInfoID">
						<f:param name="cmdParam" value="a" />
					</rich:menuItem>
					<rich:menuItem icon="/pics/info.gif" value=" b" reRender="cmInfoID">
						<f:param name="cmdParam" value="b" />
					</rich:menuItem>
					<rich:menuItem icon="/pics/info.gif" value="c" reRender="cmInfoID">
						<f:param name="cmdParam" value="c" />
					</rich:menuItem>
				</rich:contextMenu>
				<h:outputText value="text3" />
			</rich:tab>
			<rich:contextMenu attached="#{contextMenu.attached}" submitMode="#{contextMenu.submitMode}" event="#{contextMenu.event}"
				disableDefaultMenu="#{contextMenu.disableDefaultMenu}" rendered="#{contextMenu.rendered}" hideDelay="#{contextMenu.hideDelay}"
				showDelay="#{contextMenu.showDelay}" popupWidth="#{contextMenu.popupWidth}">
				<rich:menuItem icon="/pics/header.png" value="tabPanel" reRender="cmInfoID">
					<f:param name="cmdParam" value="tabPanel" />
				</rich:menuItem>
				<rich:menuSeparator />
				<rich:menuItem icon="/pics/info.gif" value="action" action="alert('action work')" reRender="cmInfoID">
					<f:param name="cmdParam" value="action" />
				</rich:menuItem>
				<rich:menuItem icon="/pics/info.gif" value="actionListener" actionListener="#{contextMenu.actionListener}" reRender="cmInfoID">
				</rich:menuItem>
				<rich:menuItem icon="/pics/info.gif" value="ajaxSingle" ajaxSingle="true" reRender="cmInfoID">
					<f:param name="cmdParam" value="ajaxSingle" />
				</rich:menuItem>
				<rich:menuItem icon="/pics/fatal.gif" value="disabled" disabled="true" reRender="cmInfoID">
					<f:param name="cmdParam" value="disabled" />
				</rich:menuItem>
				<rich:menuItem icon="/pics/fatal.gif" value="immediate" immediate="true" action="submit();">
					<f:param name="cmdParam" value="immedeate" />
					<h:inputText value="#{contextMenu.inputText}" />
				</rich:menuItem>
				<rich:menuItem icon="/pics/fatal.gif" value="submitMode: none" submitMode="none" reRender="cmInfoID">
					<f:param name="cmdParam" value="none" />
				</rich:menuItem>
				<rich:menuItem icon="/pics/fatal.gif" value="submitMode: server" submitMode="server" reRender="cmInfoID">
					<f:param name="cmdParam" value="server" />
				</rich:menuItem>
				<rich:menuItem icon="/pics/fatal.gif" value="submitMode: ajax" submitMode="ajax" reRender="cmInfoID">
					<f:param name="cmdParam" value="ajax" />
				</rich:menuItem>
				<rich:menuItem value="select">
					<h:selectOneMenu value="#{contextMenu.selectOneMenu}" onchange="submit();">
						<f:selectItem itemLabel="item1" itemValue="item1" />
						<f:selectItem itemLabel="item2" itemValue="item2" />
					</h:selectOneMenu>
				</rich:menuItem>
			</rich:contextMenu>
		</rich:tabPanel>
				
		<h:graphicImage id="gicmID" value="/pics/asus.jpg" height="125px" width="150px">
			<rich:contextMenu attached="#{contextMenu.attached}" submitMode="#{contextMenu.submitMode}" event="#{contextMenu.event}"
				disableDefaultMenu="#{contextMenu.disableDefaultMenu}" rendered="#{contextMenu.rendered}" hideDelay="#{contextMenu.hideDelay}"
				showDelay="#{contextMenu.showDelay}" popupWidth="#{contextMenu.popupWidth}" attachTo="gicmID">
				<rich:menuItem icon="/pics/header.png" value="graphicImage" reRender="cmInfoID">
					<f:param name="cmdParam" value="graphicImage" />
				</rich:menuItem>
				<rich:menuSeparator />
				<rich:menuItem icon="/pics/info.gif" value="action" action="alert('action work')" reRender="cmInfoID">
					<f:param name="cmdParam" value="action" />
				</rich:menuItem>
				<rich:menuItem icon="/pics/info.gif" value="actionListener" actionListener="#{contextMenu.actionListener}" reRender="cmInfoID">
				</rich:menuItem>
				<rich:menuItem icon="/pics/info.gif" value="ajaxSingle" ajaxSingle="true" reRender="cmInfoID">
					<f:param name="cmdParam" value="ajaxSingle" />
				</rich:menuItem>
				<rich:menuItem icon="/pics/fatal.gif" value="disabled" disabled="true" reRender="cmInfoID">
					<f:param name="cmdParam" value="disabled" />
				</rich:menuItem>
				<rich:menuItem icon="/pics/fatal.gif" value="immediate" immediate="true" action="submit();">
					<f:param name="cmdParam" value="immedeate" />
					<h:inputText value="#{contextMenu.inputText}" />
				</rich:menuItem>
				<rich:menuItem icon="/pics/fatal.gif" value="submitMode: none" submitMode="none" reRender="cmInfoID">
					<f:param name="cmdParam" value="none" />
				</rich:menuItem>
				<rich:menuItem icon="/pics/fatal.gif" value="submitMode: server" submitMode="server" reRender="cmInfoID">
					<f:param name="cmdParam" value="server" />
				</rich:menuItem>
				<rich:menuItem icon="/pics/fatal.gif" value="submitMode: ajax" submitMode="ajax" reRender="cmInfoID">
					<f:param name="cmdParam" value="ajax" />
				</rich:menuItem>
				<rich:menuItem value="select">
					<h:selectOneMenu value="#{contextMenu.selectOneMenu}" onchange="submit();">
						<f:selectItem itemLabel="item1" itemValue="item1" />
						<f:selectItem itemLabel="item2" itemValue="item2" />
					</h:selectOneMenu>
				</rich:menuItem>
			</rich:contextMenu>
		</h:graphicImage>

		<h:inputText id="itcmTestID" value="inputText">
			<rich:contextMenu attached="#{contextMenu.attached}" submitMode="#{contextMenu.submitMode}" event="#{contextMenu.event}"
				disableDefaultMenu="#{contextMenu.disableDefaultMenu}" rendered="#{contextMenu.rendered}" hideDelay="#{contextMenu.hideDelay}"
				showDelay="#{contextMenu.showDelay}" popupWidth="#{contextMenu.popupWidth}" attachTo="itcmTestID">
				<rich:menuItem icon="/pics/header.png" value="inputText" reRender="cmInfoID">
					<f:param name="cmdParam" value="inputText" />
				</rich:menuItem>
				<rich:menuSeparator />
				<rich:menuItem icon="/pics/info.gif" value="action" action="alert('action work')" reRender="cmInfoID">
					<f:param name="cmdParam" value="action" />
				</rich:menuItem>
				<rich:menuItem icon="/pics/info.gif" value="actionListener" actionListener="#{contextMenu.actionListener}" reRender="cmInfoID">
				</rich:menuItem>
				<rich:menuItem icon="/pics/info.gif" value="ajaxSingle" ajaxSingle="true" reRender="cmInfoID">
					<f:param name="cmdParam" value="ajaxSingle" />
				</rich:menuItem>
				<rich:menuItem icon="/pics/fatal.gif" value="disabled" disabled="true" reRender="cmInfoID">
					<f:param name="cmdParam" value="disabled" />
				</rich:menuItem>
				<rich:menuItem icon="/pics/fatal.gif" value="immediate" immediate="true" action="submit();">
					<f:param name="cmdParam" value="immedeate" />
					<h:inputText value="#{contextMenu.inputText}" />
				</rich:menuItem>
				<rich:menuItem icon="/pics/fatal.gif" value="submitMode: none" submitMode="none" reRender="cmInfoID">
					<f:param name="cmdParam" value="none" />
				</rich:menuItem>
				<rich:menuItem icon="/pics/fatal.gif" value="submitMode: server" submitMode="server" reRender="cmInfoID">
					<f:param name="cmdParam" value="server" />
				</rich:menuItem>
				<rich:menuItem icon="/pics/fatal.gif" value="submitMode: ajax" submitMode="ajax" reRender="cmInfoID">
					<f:param name="cmdParam" value="ajax" />
				</rich:menuItem>
				<rich:menuItem value="select">
					<h:selectOneMenu value="#{contextMenu.selectOneMenu}" onchange="submit();">
						<f:selectItem itemLabel="item1" itemValue="item1" />
						<f:selectItem itemLabel="item2" itemValue="item2" />
					</h:selectOneMenu>
				</rich:menuItem>
			</rich:contextMenu>
		</h:inputText>

		<h:panelGrid id="pgTestID" columns="1" border="5" cellpadding="2" cellspacing="2">
			<h:outputText value="panelGrid" />
			<h:outputText value="" />
			<rich:contextMenu attached="#{contextMenu.attached}" submitMode="#{contextMenu.submitMode}" event="#{contextMenu.event}"
				disableDefaultMenu="#{contextMenu.disableDefaultMenu}" rendered="#{contextMenu.rendered}" hideDelay="#{contextMenu.hideDelay}"
				showDelay="#{contextMenu.showDelay}" popupWidth="#{contextMenu.popupWidth}" attachTo="pgTestID">
				<rich:menuItem icon="/pics/header.png" value="panelGrid" reRender="cmInfoID">
					<f:param name="cmdParam" value="panelGrid" />
				</rich:menuItem>
				<rich:menuSeparator />
				<rich:menuItem icon="/pics/info.gif" value="action" action="alert('action work')" reRender="cmInfoID">
					<f:param name="cmdParam" value="action" />
				</rich:menuItem>
				<rich:menuItem icon="/pics/info.gif" value="actionListener" actionListener="#{contextMenu.actionListener}" reRender="cmInfoID">
				</rich:menuItem>
				<rich:menuItem icon="/pics/info.gif" value="ajaxSingle" ajaxSingle="true" reRender="cmInfoID">
					<f:param name="cmdParam" value="ajaxSingle" />
				</rich:menuItem>
				<rich:menuItem icon="/pics/fatal.gif" value="disabled" disabled="true" reRender="cmInfoID">
					<f:param name="cmdParam" value="disabled" />
				</rich:menuItem>
				<rich:menuItem icon="/pics/fatal.gif" value="immediate" immediate="true" action="submit();">
					<f:param name="cmdParam" value="immedeate" />
					<h:inputText value="#{contextMenu.inputText}" />
				</rich:menuItem>
				<rich:menuItem icon="/pics/fatal.gif" value="submitMode: none" submitMode="none" reRender="cmInfoID">
					<f:param name="cmdParam" value="none" />
				</rich:menuItem>
				<rich:menuItem icon="/pics/fatal.gif" value="submitMode: server" submitMode="server" reRender="cmInfoID">
					<f:param name="cmdParam" value="server" />
				</rich:menuItem>
				<rich:menuItem icon="/pics/fatal.gif" value="submitMode: ajax" submitMode="ajax" reRender="cmInfoID">
					<f:param name="cmdParam" value="ajax" />
				</rich:menuItem>
				<rich:menuItem value="select">
					<h:selectOneMenu value="#{contextMenu.selectOneMenu}" onchange="submit();">
						<f:selectItem itemLabel="item1" itemValue="item1" />
						<f:selectItem itemLabel="item2" itemValue="item2" />
					</h:selectOneMenu>
				</rich:menuItem>
			</rich:contextMenu>
		</h:panelGrid>
	</h:panelGrid>
</f:subview>
