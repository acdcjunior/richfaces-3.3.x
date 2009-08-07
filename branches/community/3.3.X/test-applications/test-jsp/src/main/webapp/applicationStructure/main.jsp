<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@page contentType="text/html; charset=UTF-8"%>
<f:view>
	<html>
	<head>
	<title>RichFaces JSP+MyFaces Project</title>
	<link href="/test-jsp/styles/layout.css" rel="stylesheet"
		type="text/css" />
	<script type="text/javascript" src="/test-jsp/javascript/jsTools.js"></script>
	</head>
	<body>
	<h:form id="formID">
		<a4j:outputPanel id="headerID" layout="block"><jsp:include
				page="header.jsp" />
		</a4j:outputPanel>
		<hr />
		<rich:messages id="messagesID" showDetail="true" styleClass="error" />

		<a4j:outputPanel id="componentID" layout="block">
			<jsp:include page="${pageContent.currentComponent}" /></a4j:outputPanel>

		<h:panelGrid columns="4" border="1">
			<a4j:commandLink value="ManualGeneralAttribute" reRender="testPageID"
				limitToList="true" immediate="true">
				<a4j:actionparam name="testType" value="MANUAL_GENERAL_ATTRIBUTES"
					assignTo="#{pageContent.testType}"></a4j:actionparam>
			</a4j:commandLink>
			<a4j:commandLink value="AutoGeneralAttribute" reRender="testPageID"
				limitToList="true" immediate="true">
				<a4j:actionparam name="testType" value="AUTO_GENERAL_ATTRIBUTES"
					assignTo="#{pageContent.testType}"></a4j:actionparam>
			</a4j:commandLink>
			<a4j:commandLink value="AutoEventHandlers" reRender="testPageID"
				limitToList="true" immediate="true">
				<a4j:actionparam name="testType" value="AUTO_EVENTHANDLERS"
					assignTo="#{pageContent.testType}"></a4j:actionparam>
			</a4j:commandLink>
			<a4j:commandLink value="AutoStylesClasses" reRender="testPageID"
				limitToList="true" immediate="true">
				<a4j:actionparam name="testType" value="AUTO_STYLES_CLASSES"
					assignTo="#{pageContent.testType}"></a4j:actionparam>
			</a4j:commandLink>
		</h:panelGrid>

		<a4j:outputPanel id="testPageID" layout="block"><jsp:include
				page="${pageContent.currentTestPage}" /></a4j:outputPanel>
	</h:form>
	</body>
	</html>
</f:view>