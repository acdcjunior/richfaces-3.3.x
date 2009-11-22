<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://labs.jboss.com/jbossrichfaces/ui/panelbar" prefix="rich" %>
<html>

<head>
</head>

<body>
<f:view>
    <h:form>
        <h:messages style="color:red"/>

        <rich:panelBar id="panelBarId" height="400" width="500" selectedPanel="#{bean.value}" >
            <a4j:support event="onitemchange"/>
            
            <rich:panelBarItem id="item_01" label="#{bean.label}" >
                <h:outputText value="-1-"/>
            </rich:panelBarItem>

            <rich:panelBarItem id="item_02" label="#{bean.label}">
                <h:outputText value="-2-"/>
            </rich:panelBarItem>
        </rich:panelBar>

        <h:panelGrid columns="3">
            <h:outputText value="Label:"/>
            <h:inputText value="#{bean.label}"/>
            <h:commandButton value="Change!"/>
        </h:panelGrid>
    </h:form>
</f:view>
</body>
</html>
