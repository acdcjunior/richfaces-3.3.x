<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j" %>
<%@ taglib uri="http://labs.jboss.com/jbossrichfaces/ui/dataTable" prefix="data" %>
<html>
<head>
    <title></title>
    <style>
        .dataTable .rich-sort-icon {
            display: none;
        }
    </style>
</head>
<body>
<f:view>
    <a4j:form>
        <data:dataTable value="#{data.mounths}" var="entry">
            <data:column selfSorted="false" sortBy="#{entry.mounth}" sortOrder="DESCENDING">
                <h:outputText value="#{entry.mounth}"/>
            </data:column>
            <data:column>
                <h:outputText value="#{entry.total}"/>
            </data:column>
        </data:dataTable>
    </a4j:form>
</f:view>
</body>
</html>
