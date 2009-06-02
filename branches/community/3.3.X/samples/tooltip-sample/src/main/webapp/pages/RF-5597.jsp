<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://labs.jboss.com/jbossrichfaces/ui/tooltip" prefix="rich" %>

<html>
    <head>
        <script type="text/javascript">/* <![CDATA[ */
            function showPopup( e, popupElemId ) {
                popup = document.getElementById( popupElemId );
                popup.component.show(e);
                return false;
            }

            function hidePopup( e, popupElemId ) {
                popup = document.getElementById( popupElemId );
                popup.component.hide(e);
                return false;
            }
        /* ]]> */</script>
    </head>

    <body>
    <f:view>

        <h:outputLink id="link" value="#" onclick="return showPopup(event, 'popup' );">
            Click to Open
        </h:outputLink>
        <rich:toolTip id="popup" for="link" showEvent="none" hideEvent="none">
            <h:outputLink value="#" onclick="return hidePopup( event, 'popup' );">
                Click to Close
            </h:outputLink>
        </rich:toolTip>

    </f:view>
    </body>
</html> 