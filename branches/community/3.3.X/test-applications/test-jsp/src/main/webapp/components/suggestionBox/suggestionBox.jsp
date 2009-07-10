<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<f:subview id="suggestionBoxSubviewID">
	<div>This element adds "on-keypress" suggestions capabilites to
	any input text component (like &lt;h:inputText&gt;). It creates a
	pop-up window for a input field component pointed to by the "for"
	attribute. For an ordinary request, render a hidden HTML &lt;div&gt;
	element and the necessary JavaScript code. When input to the target
	field exceeds the "minChars" property (default is 1), perform an AJAX
	request with the value current entered. If the AJAX request is detected
	and the request parameters map contains the client ID of this
	component, call a method defined by suggestionAction, and use it to
	return a value as data for render table, same as &lt;h:dataTable&gt;.
	In this case, the component then works the same as an ordinary
	dataTable. The rendered table is inserted in the pop-up area and is
	used for suggestion prompts for input element. If a user clicks on such
	a table row, its text (or value defined by fetchValue) is inserted as
	the value of the input field.</div>

	<h:inputText id="sbText" value="#{suggestionBox.value}" />
	<rich:suggestionbox id="suggestionBoxID" height="300" width="200"
		for="sbText" suggestionAction="#{suggestionBox.suggestionAction}"
		var="row" fetchValue="#{row.make}" usingSuggestObjects="true"
		onbeforedomupdate="markEventAsWorkable('onbeforedomupdate');"
		oncomplete="markEventAsWorkable('oncomplete');"
		onobjectchange="markEventAsWorkable('onobjectchange');"
		onselect="markEventAsWorkable('onselect');"
		onsubmit="markEventAsWorkable('onsubmit');"
		entryClass="entryClass-entryClass" popupClass="popupClass-popupClass"
		popupStyle="popupStyle:popupStyle"
		rowClasses="rowClasses-rowClasses1, rowClasses-rowClasses2"
		selectedClass="selectedClass-selectedClass"
		selectValueClass="selectValueClass-selectValueClass"
		style="style:style" styleClass="styleClass-styleClass">
		<rich:column>
			<h:outputText value="#{row.make}" />
		</rich:column>
		<rich:column>
			<h:outputText value="#{row.model}" />
		</rich:column>
		<rich:column>
			<h:outputText value="#{row.price}" />
		</rich:column>
	</rich:suggestionbox>
	<br />
</f:subview>