<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://labs.jboss.com/jbossrichfaces/ui/suggestionbox"
    prefix="rich"%>
<html>
<head>
<title>Custom Component</title>
</head>
<body>
<f:view>
    <h:form id="f">
        <h:inputText id="text" value="#{customComponent.input}" />
        <rich:suggestionbox for="text" height="100" width="200"
            suggestionAction="#{customComponent.suggestionAction}"
            var="row" fetchValue="#{row}">
            <h:column>
                <h:outputText value="#{row}" />
            </h:column>
        </rich:suggestionbox>
    </h:form>
</f:view>
</body>
</html>