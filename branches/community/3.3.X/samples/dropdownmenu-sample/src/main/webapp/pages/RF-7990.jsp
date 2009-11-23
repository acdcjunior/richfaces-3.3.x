<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://labs.jboss.com/jbossrichfaces/ui/menu-components" prefix="mc" %>
<%@ taglib uri="http://labs.jboss.com/jbossrichfaces/ui/toolBar" prefix="tb" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>RF-7990 Test Page </title>
</head>
<body>
<f:view>
    <h:form id="f">
        <tb:toolBar id="t">                 
            <mc:menuItem
                    id="item"
                    submitMode="server"
                    action="internalIndex"
                    style="color: red;"
                    styleClass="first">
                <h:graphicImage value="/images/home.png" styleClass="menuIcon"/>
                <h:panelGroup styleClass="menuLabel">
                    <h:outputText value="Hello"/>                     
                </h:panelGroup>
            </mc:menuItem>
        </tb:toolBar>
    </h:form>
</f:view>
</body>
</html>