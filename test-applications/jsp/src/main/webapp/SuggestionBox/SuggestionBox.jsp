<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<f:subview id="suggestionBoxSubviewID">
	<h:messages showDetail="true" />
	<f:verbatim>Suggestion Box will suggest you Town's names if it's started with the "a" or "A" letter
		<br />
	</f:verbatim>
	<h:inputText value="#{sb.property}" id="text">
		<f:validateLength minimum="0" maximum="30"/>
	</h:inputText>
	<rich:suggestionbox id="suggestionBoxId" 
		ajaxSingle="#{sb.ajaxSingle}"
		bgcolor="#{sb.bgColor}"
		binding="#{sb.mySuggestionBox}"
		bypassUpdates="#{sb.bypassUpdates}"
		cellpadding="#{sb.cellpadding}"
		cellspacing="#{sb.cellspacing}" 
		dir="#{sb.dir}"
		entryClass="#{style.entryClass}" 
		eventsQueue="myEventsQueue" 
		fetchValue="#{result.text}" 
		first="#{sb.first}" 
		for="text"
		frame="#{sb.frame}" 
		frequency="#{sb.frequency}" 
		height="#{sb.height}"
		ignoreDupResponses="#{sb.ignoreDupResponses}"
		immediate="#{sb.immediate}"
		limitToList="false"
		minChars="#{sb.minchars}" 
		nothingLabel="#{sb.nothingLabel}"
 		onbeforedomupdate="#{event.onbeforedomupdate}" 
 		oncomplete="#{event.oncomplete}" 
 		onobjectchange="#{event.onobjectchange}"
 		onselect="#{event.onselect}" 
 		onsubmit="#{event.onsubmit}"
 		popupStyle="background-color: black;"		
		usingSuggestObjects="#{sb.usingSuggestObjects}"
		reRender="label"
		rendered="#{sb.rendered}" 
		requestDelay="#{sb.requestDelay}"
		selfRendered="#{sb.selfRendered}"		 
		var="result" 
		suggestionAction="#{sb.autocomplete}" 
		width="#{sb.width}" 
		border="#{sb.border}" 
		zindex="#{sb.zindex}" 
		rules="#{sb.rules}" 
		tokens="#{sb.tokens}" 
		title="#{result.text}" 
		summary="summary" 
		shadowOpacity="#{sb.shadowOpacity}"
		shadowDepth="#{sb.shadowDepth}" 
		selectValueClass="mousemove" 
		style="#{style.style}" 
		styleClass="#{style.styleClass}" 
		selectedClass="#{style.selectedClass}" 
		rowClasses="#{style.rowClasses}" >
		
		<h:column>		
			<h:outputText value="#{result.city}" />
		</h:column>
		<h:column>
			<h:outputText value="#{result.contry}" />
		</h:column>
		<h:column>
			<h:outputText value="#{result.flag}" />
		</h:column>
		<h:column>
			<a4j:htmlCommandLink  actionListener="#{sb.selectValue}" value="Click me!"/>
		</h:column>
	</rich:suggestionbox>
	<div id="label" style="position: relative; font-size: 50px; z-index: 2; color: navy">z-index</div>
</f:subview>
