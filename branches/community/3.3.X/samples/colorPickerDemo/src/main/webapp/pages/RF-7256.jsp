<%@ page contentType="application/xhtml+xml; charset=ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://labs.jboss.com/jbossrichfaces/ui/tabPanel" prefix="rich" %>
<%@ taglib uri="http://labs.jboss.com/jbossrichfaces/ui/colorPicker" prefix="colorPicker"%>

<html>
<body>
<f:view>
    <h:form id="f">
        <rich:tabPanel id="p" switchType="server">
            <rich:tab id="first" label="first"></rich:tab>
            <rich:tab id="second" label="second"></rich:tab>
        </rich:tabPanel>
        <colorPicker:colorPicker id="color" inputSize="3" />
    </h:form>
</f:view>
</body>
</html>