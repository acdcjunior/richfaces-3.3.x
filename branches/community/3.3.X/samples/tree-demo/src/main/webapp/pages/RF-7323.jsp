<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://labs.jboss.com/jbossrichfaces/ui/tree"
    prefix="rich"%>
<html>
<head>
<title></title>
</head>
<body>
<f:view>
    <a4j:status startText="...start..." stopText="stopped" />

    <h:form id="id" >
        <h:inputText id="input" value="#{rf4351.value}" />
        <a4j:commandLink value="process" process="input" ajaxSingle="true" /><br /><br />
        
        <h:outputText id="output" value="#{rf4351.date}" />
        <a4j:commandLink value="reRender" reRender="output" ajaxSingle="true" /><br /><br />
        
        <rich:tree id="tree" value="#{rf4351.data}" var="node" switchType="ajax"
            rowKeyConverter="org.richfaces.TreeRowKeyConverter">
            <rich:treeNode ajaxSingle="true" process="input" >
                <h:outputText value="#{node}" />
                <a4j:commandLink ajaxSingle="true" value="link" process="input" reRender="output" action="#{rf4351.dateAction}"/>
            </rich:treeNode>
        </rich:tree>
    </h:form>
</f:view>
</body>
</html>