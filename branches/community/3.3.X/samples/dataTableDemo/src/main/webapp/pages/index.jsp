<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://labs.jboss.com/jbossrichfaces/ui/dataTable" prefix="data" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j" %>
<html>
	<head>
		<title></title>
		<style>
			.buttonStyle {
				color:#050;
   				font-weight:bold;
			}
		</style>
    </head>
	<body>
		<f:view >
		<h:form id="skinForm" >
			<h:selectOneRadio binding="#{skinBean.component}" />
			<h:commandLink action="#{skinBean.change}" value="set skin" />
			<h:outputText value=" Current skin: #{skinBean.skin}" /><br />
		</h:form>
		<h:form id="form" >
		<h:panelGrid columns="3" border="1">
			<data:dataTable id="master" var="master" value="#{data.mounths}" 
			styleClass="table" captionClass="caption" rowClasses="rowa,rowb,rowc rowcc"
			headerClass="header" footerClass="footer">
			    <f:facet name="caption"><h:outputText value="caption" /></f:facet>
			    <f:facet name="header">
			    <data:columnGroup columnClasses="cola, colb ,rowc rowcc">
			    <data:column rowspan="2">
			    <h:outputText value="2-row head" />
			    </data:column>
			    <h:column >
			    <h:outputText value="head in UIColumn" />
			    </h:column>
			    <data:column breakBefore="true">
			    <h:outputText value="2-d row head" />
			    </data:column>
			    </data:columnGroup>
			    </f:facet>
			    <f:facet name="footer"><h:outputText value="table foot" /></f:facet>
			    <data:column id="mounth" styleClass="column" colspan="2" 
			    headerClass="cheader" footerClass="cfooter" >
			    <f:facet name="header"><h:outputText value="mounth" /></f:facet>
			    <f:facet name="footer"><h:outputText value="-//-" /></f:facet>
			      <h:outputText value="#{master.mounth}" />
			    </data:column>
			<data:subTable id="detail" var="detail" value="#{master.detail}">
			    <data:column id="name">
			      <h:outputText value="#{detail.name}" />
			    </data:column>
			    <data:column id="qty">
			      <h:outputText value="#{detail.qty}" />
			    </data:column>
			</data:subTable>
			    <data:column id="total" styleClass="total" colspan="2">
			      <h:outputText value="#{master.total}" />
			    </data:column>
			</data:dataTable>
			
			<data:dataTable id="master1" var="master" value="#{data.mounths}" >
			    <data:column id="mounth" >
			      <h:outputText value="#{master.mounth}" />
			    </data:column>
			    <data:column id="total">
			      <h:outputText value="#{master.total}" />
			    </data:column>
			<data:subTable id="detail" var="detail" value="#{master.detail}">
			    <data:column id="name" colspan="2">
			      <h:outputText value="#{detail.name}" />
			    </data:column>
			    <data:column id="qty" breakBefore="true" colspan="2">
			      <h:outputText value="#{detail.qty}" />
			    </data:column>
			</data:subTable>
			
			</data:dataTable>
			<data:dataTable id="master2" var="master" value="#{data.mounths}" 
			    rowClasses="rowa,rowb,rowc rowcc" 
			    columnClasses="cola, colb ,rowc rowcc">
			    <h:column id="mounth" >
			      <h:outputText value="#{master.mounth}" />
			    </h:column>
			    <h:column id="total">
			      <h:outputText value="#{master.total}" />
			    </h:column>
			<data:subTable id="detail" var="detail" value="#{master.detail}">
			    <data:column id="name">
			      <h:outputText value="#{detail.name}" />
			    </data:column>
			    <data:column id="qty">
			      <h:outputText value="#{detail.qty}" />
			    </data:column>
			</data:subTable>
			</data:dataTable>
			<a4j:commandButton value="Re-render table" reRender="master" styleClass="buttonStyle"></a4j:commandButton>
			<a4j:commandButton value="Re-render table" reRender="master1"></a4j:commandButton>
			<a4j:commandButton value="Re-render table" reRender="master2"></a4j:commandButton>
      </h:panelGrid>
      </h:form>
      <a4j:log hotkey="D"/>
	</f:view>
	</body>	
</html>  
