<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://labs.jboss.com/jbossrichfaces/ui/pickList" prefix="rich"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<f:view>
    <html>
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title><h:outputText value="PickList" /></title>
    </head>
    <body>
    <h:form>
        <rich:pickList value="#{rf7472.value}" converter="#{rf7472.convert}">
            <f:selectItem itemLabel="item-1" itemValue="item-1" />
            <f:selectItem itemLabel="item-2" itemValue="item-2" />
        </rich:pickList>
        <h:commandButton value="submit();" />
    </h:form>
    </body>
    </html>
</f:view>