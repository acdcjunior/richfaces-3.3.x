<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<f:subview id="componentControlSubviewID">
<rich:componentControl attachTo="testCID" attachTiming="onavailable" for="cc" event="onclick" operation="doExpand" params="show:'componentControl work(doExpand)'"/>
<rich:calendar popup="true" id="cc"/>
<f:verbatim>
	<a href="#" id="testCID">Click text(attachTiming)</a>
	<br />
</f:verbatim> 
	<h:inputText value="test">
		<rich:componentControl name="controlMe" event="onclick" attachTiming="onload" for="ccToltipID" operation="show" />
	</h:inputText>

	<rich:panel>
		<rich:toolTip id="ccToltipID" followMouse="false" direction="top-right" value="Help me, help!">
		</rich:toolTip>
	</rich:panel>

	<rich:modalPanel id="ccModalPanelID" onshow="alert(event.parameters.show)" onhide="alert(event.parameters.hide)">
		<h:outputLink id="hideButton1ID" onclick="return false;" value="#">
			<f:verbatim>Close 1</f:verbatim>
		</h:outputLink>
		<f:verbatim>
			<br />
		</f:verbatim>
		<h:outputLink id="hideButton2ID" onclick="return false;" value="#">
			<f:verbatim>Close 2</f:verbatim>
		</h:outputLink>
	</rich:modalPanel>

	<h:commandButton id="showButtonID" value="show ModalPanel">
		<rich:componentControl for="ccModalPanelID" event="onclick" disableDefault="true" operation="show"
			params="show:'componentControl work(show)'" />
	</h:commandButton>

	<rich:componentControl attachTiming="onload" attachTo="hideButton1ID, hideButton2ID" event="onclick" for="ccModalPanelID"
		operation="hide" params="hide:'componentControl work(hide)'" />

	<f:verbatim>
		<br />
		<br />
	</f:verbatim>

	<rich:calendar popup="#{componentControl.calendarPopup}" id="ccCalendarID" />
	<h:panelGrid columns="2">
		<h:outputText value="popup calendar" />
		<h:selectBooleanCheckbox id="calendarSelectID" value="#{componentControl.calendarPopup}" onchange="submit();" />
	</h:panelGrid>

	<f:verbatim>
		<br />
		<a href="#" id="doExpandCalendarID">Calendar (doExpand)</a>
		<br />
		<a href="#" id="doNextYearCalendarID">Calendar (nextYear)</a>
		<br />
		<a href="#" id="doPrevYearCalendarID">Calendar (prevYear)</a>
		<br />
		<a href="#" id="doNextMonthCalendarID">Calendar (nextMonth)</a>
		<br />
		<a href="#" id="doPrevMonthCalendarID">Calendar (prevMonth)</a>
		<br />
		<a href="#" id="doTodayCalendarID">Calendar (today)</a>
		<br />
		<a href="#" id="doTodayCalendarRenderedID">Calendar (today) rendered="false"</a>
	</f:verbatim>

	<rich:componentControl attachTo="doExpandCalendarID" for="ccCalendarID" event="onclick" operation="doExpand" />
	<rich:componentControl attachTo="doNextYearCalendarID" for="ccCalendarID" event="onclick" operation="nextYear" />
	<rich:componentControl attachTo="doPrevYearCalendarID" for="ccCalendarID" event="onclick" operation="prevYear" />
	<rich:componentControl attachTo="doNextMonthCalendarID" for="ccCalendarID" event="onclick" operation="nextMonth" />
	<rich:componentControl attachTo="doPrevMonthCalendarID" for="ccCalendarID" event="onclick" operation="prevMonth" />
	<rich:componentControl attachTo="doTodayCalendarID" for="ccCalendarID" event="onclick" operation="today" />
	<rich:componentControl attachTo="doTodayCalendarRenderedID" for="ccCalendarID" rendered="false" event="onclick" operation="today" />

	<f:verbatim>
		<br />
		<br />
	</f:verbatim>

	<rich:panelMenu id="ccContextMenuPanelMenuID">
		<h:outputText value="click text" />
	</rich:panelMenu>

	<rich:contextMenu id="ccContextMenuID" submitMode="ajax" onexpand="alert(event.parameters.expand)">
		<rich:menuItem icon="/pics/header.png" value="tab1">
		</rich:menuItem>
		<rich:menuSeparator />
		<rich:menuItem icon="/pics/info.gif" value="a">
		</rich:menuItem>
		<rich:menuItem icon="/pics/info.gif" value="b">
		</rich:menuItem>
		<rich:menuItem icon="/pics/info.gif" value="c">
		</rich:menuItem>
	</rich:contextMenu>
	<rich:componentControl id="componentID" event="oncontextmenu" attachTo="ccContextMenuPanelMenuID" for="ccContextMenuID" operation="show"
		params="expand:'show work'" />
	<br/>
	<br/>
	<div style="FONT-WEIGHT: bold;">rich:findComponent</div>
	<h:panelGrid columns="2">
	<rich:column>
	<a4j:commandLink value="getOperation" reRender="findID"></a4j:commandLink>
	</rich:column>
	<rich:column id="findID">
	<h:outputText value="#{rich:findComponent('componentID').operation}" />	
	</rich:column>
	</h:panelGrid>		
</f:subview>