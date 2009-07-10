<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<f:subview id="dropDownMenuSubviewID">
	<div>The &lt;rich:dropDownMenu&gt; component is used for creating
	multilevel drop-down menus.</div>
	<br />
	<rich:dropDownMenu id="dropDownMenuID"
		value="Click HERE to call the menu" submitMode="ajax"
		oncollapse="markEventAsWorkable('oncollapse');"
		onexpand="markEventAsWorkable('onexpand');"
		ongroupactivate="markEventAsWorkable('ongroupactivate');"
		onitemselect="markEventAsWorkable('onitemselect');"
		onmousemove="markEventAsWorkable('onmousemove');"
		onmouseout="markEventAsWorkable('onmouseout');"
		onmouseover="markEventAsWorkable('onmouseover');" style="style:style"
		styleClass="styleClass-styleClass"
		disabledItemClass="disabledItemClass-disabledItemClass"
		disabledItemStyle="disabledItemStyle:disabledItemStyle"
		disabledLabelClass="disabledLabelClass-disabledLabelClass"
		itemClass="itemClass-itemClass" itemStyle="itemStyle:itemStyle"
		labelClass="labelClass-labelClass"
		selectedLabelClass="selectedLabelClass-selectedLabelClass"
		selectItemClass="selectItemClass-selectItemClass"
		selectItemStyle="selectItemStyle:selectItemStyle">
		<rich:menuItem value="it-1"></rich:menuItem>
		<rich:menuItem disabled="true" value="it-2"></rich:menuItem>
		<rich:menuGroup value="gr-1">
			<rich:menuItem value="it-1.1"></rich:menuItem>
			<rich:menuItem value="it-1.2"></rich:menuItem>
		</rich:menuGroup>
		<rich:menuGroup disabled="true" value="gr-2">
			<rich:menuItem value="it-2.1"></rich:menuItem>
			<rich:menuItem value="it-2.2"></rich:menuItem>
		</rich:menuGroup>
	</rich:dropDownMenu>
	<rich:dropDownMenu id="dropDownMenuDisabledID" value="Disabled menu"
		disabled="true"
		disabledItemClass="disabledItemClass-disabledItemClass"
		disabledItemStyle="disabledItemStyle:disabledItemStyle"
		disabledLabelClass="disabledLabelClass-disabledLabelClass">
		<rich:menuItem value="it-1"></rich:menuItem>
		<rich:menuItem value="it-2"></rich:menuItem>
		<rich:menuGroup value="gr-1">
			<rich:menuItem value="it-1.1"></rich:menuItem>
			<rich:menuItem value="it-1.2"></rich:menuItem>
		</rich:menuGroup>
	</rich:dropDownMenu>
	<br />
</f:subview>