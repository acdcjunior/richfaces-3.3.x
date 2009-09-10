<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>

<%@ taglib uri="http://labs.jboss.com/jbossrichfaces/ui/tree" prefix="tree"%>
<%@ taglib uri="http://labs.jboss.com/jbossrichfaces/ui/drag-drop" prefix="dnd"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>

<html>
<head>
<title></title>
</head>
<body>
<f:view>
    <h:form id="f" >
        <a4j:outputPanel id="op" ajaxRendered="true">
            <h:messages />
        </a4j:outputPanel>

        <!-- ajaxSingle="true" -->

        <tree:tree 
            id="tree" 
            switchType="client" 
            ajaxSingle="true"
            
            value="#{treeDndBean.treeNodeLeft}"
            rowKeyVar="key"         
            var="item"
            
            binding="#{treeDndBean.leftTree}"
            dropListener="#{treeDndBean.onDrop}"
            dragListener="#{treeDndBean.onDrag}"
            
            dragIndicator="treeIndicator"
            acceptedTypes="treeNode"
            dragType="treeNode" >
        </tree:tree>
    </h:form>
    
    <h:form id="f2">
        <a4j:status startText="...start..." stopText="stopped" />
    
        <tree:tree id="tree" value="#{bean.data}" var="item">
            <tree:treeNode 
                id="node" 
                ajaxSubmitSelection="true" 
                ajaxSingle="false"
                nodeSelectListener="#{bean.onSelect}"
                
                process="text"
                reRender="text2"
                >
                <h:outputText id="item" value="#{item}" />
            </tree:treeNode>
        </tree:tree>
        
        <h:inputText id="text" value="#{bean.value}" />
        <h:inputText id="text2" value="#{bean.value}"/>
        <a4j:region selfRendered="true">
            <h:inputText id="text3" value="#{bean.value}"/>
        </a4j:region>
    </h:form>
    
</f:view>
</body>
</html>
