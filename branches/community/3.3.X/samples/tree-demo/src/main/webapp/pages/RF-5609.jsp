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
    <h:form id="f1">
        <a4j:status startText="...start..." stopText="stopped" />

        <rich:tree id="tree1" value="#{bean.data}" var="item"
            ajaxSubmitSelection="true" >
            <rich:treeNode id="node"  
                nodeSelectListener="#{bean.onSelect}"
                oncomplete="alert('oncomplete');" >
                <h:outputText id="item" value="#{item}" />
            </rich:treeNode>
        </rich:tree>
        <h:inputText id="text" value="123" required="true" />
    </h:form>

    <p>_____________________________________</p>
    <h:form id="f2">
        <rich:tree id="tree2" value="#{bean.data}" var="item"
            ajaxSubmitSelection="true" >
            <rich:treeNode id="node"  
                nodeSelectListener="#{bean.onSelect}"
                >
                <h:outputText id="item" value="#{item}" />
            </rich:treeNode>
        </rich:tree>
        <h:inputText id="text" value="123" required="true" />
    </h:form>
    
    <p>_____________________________________</p>
    <h:form id="f3">
        <rich:tree id="tree3" value="#{bean.data}" var="item"
            ajaxSubmitSelection="false" >
            <rich:treeNode id="node"  
                nodeSelectListener="#{bean.onSelect}"
                oncomplete="alert('oncomplete');" >
                <h:outputText id="item" value="#{item}" />
            </rich:treeNode>
        </rich:tree>
        <h:inputText id="text" value="123" required="true" />
    </h:form>

    <p>_____________________________________</p>
    <h:form id="f4">
        <rich:tree id="tree4" value="#{bean.data}" var="item"
            ajaxSubmitSelection="false" >
            <rich:treeNode id="node"  
                nodeSelectListener="#{bean.onSelect}"
                 >
                <h:outputText id="item" value="#{item}" />
            </rich:treeNode>
        </rich:tree>
        <h:inputText id="text" value="123" required="true" />
    </h:form>
</f:view>


</body>
</html>

