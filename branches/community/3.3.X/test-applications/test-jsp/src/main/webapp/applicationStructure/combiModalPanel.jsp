<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@page contentType="text/html; charset=UTF-8"%>
<f:view>
	<html>
	<head>
	<title>Current component inside MODAL PANEL</title>
	<link href="/test-jsp/styles/layout.css" rel="stylesheet"
		type="text/css" />
	<script type="text/javascript" src="/test-jsp/javascript/jsTools.js"></script>
	</head>
	<body>
	<rich:modalPanel id="combiModalPanelID" showWhenRendered="true"
		autosized="true">
		<f:facet name="controls">
			<f:verbatim>
				<a href="#"
					onclick="Richfaces.hideModalPanel('combiModalPanelID'); return false;">hide</a>
			</f:verbatim>
		</f:facet>
		<f:facet name="header">
			<h:outputText value="Combi Modal Panel" />
		</f:facet>
		<h:form>
			<a4j:status startText="working" stopText="-------"
				startStyle="color: red;"></a4j:status>
			<a4j:outputPanel id="currentComponentInsideModalPanelID"
				layout="block">
				<jsp:include page="${pageContent.currentComponent}" />
			</a4j:outputPanel>
			<a4j:commandButton reRender="currentComponentInsideModalPanelID"
				value="reRender"></a4j:commandButton>
		</h:form>
	</rich:modalPanel>

	<a onclick="Richfaces.showModalPanel('combiModalPanelID');" href="#">show
	ModalPanel with current component</a>
	</body>
	</html>
</f:view>