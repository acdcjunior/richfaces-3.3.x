<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://labs.jboss.com/jbossrichfaces/ui/calendar" prefix="rich" %>
<html>
<head>
    <title></title>
</head>
<body dir="rtl">
<f:view>
    <h:form id="f">
        <rich:calendar value="#{calendarBean.selectedDate}"
                       locale="#{calendarBean.locale}"
                       popup="#{calendarBean.popup}"
                       datePattern="#{calendarBean.pattern}"
                       showApplyButton="#{calendarBean.showApply}">
            
            <f:facet name="weekDay">
                <h:outputText value="{weekDayLabelShort}"/>
            </f:facet>
            <f:facet name="weekNumber">
                <h:outputText value="1"/>
            </f:facet>
        </rich:calendar>
    </h:form>
</f:view>
</body>
</html>