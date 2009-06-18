<!doctype html public "-//w3c//dtd html 4.0 transitional//en">

<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://labs.jboss.com/jbossrichfaces/ui/tabPanel" prefix="tabs"%>

<html>
<head>
<title></title>
<style type="text/css">
</style>
</head>
<body bgcolor="white">
<f:view>
    <h:form id="formID">
		<a4j:keepAlive beanName="personBean" />

		<a4j:form>
			<tabs:tabPanel switchType="ajax" selectedTab="#{personBean.selectedTab}">
				<tabs:tab id="tab1" label="firstname">
					<a4j:region>
						<h:inputText value="#{personBean.firstName}" />
					</a4j:region>
				</tabs:tab>
				<tabs:tab id="tab2" label="lastname">
					<a4j:region>
						<h:inputText value="#{personBean.secondName}" />
					</a4j:region>
				</tabs:tab>
			</tabs:tabPanel>
		</a4j:form>	
        <br />
        <a4j:status startText="Work" stopText="Stop"></a4j:status>
    </h:form>
</f:view>
</body>
</html>
