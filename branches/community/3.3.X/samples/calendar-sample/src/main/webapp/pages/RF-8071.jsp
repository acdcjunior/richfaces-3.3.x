<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://labs.jboss.com/jbossrichfaces/ui/calendar" prefix="rich" %>
<html>
<head>
    <title>RF-8071 Calendar: "NaN" is displayed for selected date</title>
</head>
<body>
<f:view>
    <h:form id="f">
        <rich:calendar
                id="inputValidFrom"
                datePattern="dd-MMM-yyyy"
                showFooter="false"
                required="true"
                requiredMessage="msg.error_validFromNotEmpty" 
                enableManualInput="true"
                buttonIcon=""/>
    </h:form>
</f:view>
</body>
</html>