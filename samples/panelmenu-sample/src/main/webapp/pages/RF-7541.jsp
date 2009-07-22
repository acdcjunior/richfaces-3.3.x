<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://labs.jboss.com/jbossrichfaces/ui/panelmenu" prefix="pm"%>

<html>
<body>
<f:view>
    <h:form id="f">
        <pm:panelMenu
            iconCollapsedTopGroup="chevronUp"
            iconExpandedTopGroup="#{facesContext.externalContext.requestContextPath}/images/capitals/alaska.gif"
            iconExpandedGroup="#{facesContext.externalContext.requestContextPath}/images/capitals/alaska.gif"
            iconTopItem="#{facesContext.externalContext.requestContextPath}/images/capitals/arzona.gif"
            iconItem="#{facesContext.externalContext.requestContextPath}/images/capitals/colorado.gif">
             
            <pm:panelMenuItem label="CheckBox">
                <h:selectBooleanCheckbox value="false"></h:selectBooleanCheckbox>
            </pm:panelMenuItem>
            <pm:panelMenuItem>
                <h:outputText value="CheckBox 2"></h:outputText>
                <h:selectBooleanCheckbox value="false"></h:selectBooleanCheckbox>
            </pm:panelMenuItem>
            <pm:panelMenuItem label="Action" />
            <pm:panelMenuGroup 
                label="Group 1(expanded=true)" 
                expanded="true" 
                id="pmg"
                iconExpanded="">
                
                <pm:panelMenuItem label="Item 1" disabled="true" />
                <pm:panelMenuItem label="Item 1 (action)" onmousedown="alert('OnMouseDown');" />
                <pm:panelMenuItem label="Item 2" />
            </pm:panelMenuGroup>
        </pm:panelMenu>
    </h:form>
</f:view>
</body>
</html>