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
    <h:form id="f">
        <a4j:status startText="...start..." stopText="stopped" />
    
        <rich:tree id="tree" value="#{bean.data}" var="item">
            <rich:treeNode id="node" ajaxSubmitSelection="true" ajaxSingle="true"
                nodeSelectListener="#{bean.onSelect}">
                <h:outputText id="item" value="#{item}" />
            </rich:treeNode>
        </rich:tree>
        <h:inputText id="text" value="" required="true" />
    </h:form>
</f:view>
</body>
</html>