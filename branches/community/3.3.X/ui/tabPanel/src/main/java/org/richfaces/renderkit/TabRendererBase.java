/**
 * License Agreement.
 *
 *  JBoss RichFaces - Ajax4jsf Component Library
 *
 * Copyright (C) 2007  Exadel, Inc.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License version 2.1 as published by the Free Software Foundation.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301  USA
 */

package org.richfaces.renderkit;

import org.ajax4jsf.context.AjaxContext;
import org.ajax4jsf.renderkit.AjaxRendererUtils;
import org.ajax4jsf.renderkit.RendererBase;
import org.richfaces.component.TabEncoder;
import org.richfaces.component.UISwitchablePanel;
import org.richfaces.component.UITab;
import org.richfaces.component.UITabPanel;

import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.Map;


/**
 * @author Nick Belaevski - nbelaevski@exadel.com
 *         created 12.01.2007
 */
public class TabRendererBase extends RendererBase implements TabEncoder {
    private RendererBase tabHeaderRenderer;

    private synchronized RendererBase getHeaderRenderer() {
    	if (tabHeaderRenderer == null) {
            Package pkg = this.getClass().getPackage();
            try {
                tabHeaderRenderer = (RendererBase) Class.forName(pkg.getName() + ".TabHeaderRenderer").newInstance();
            } catch (InstantiationException e) {
                throw new FacesException(e);
            } catch (IllegalAccessException e) {
                throw new FacesException(e);
            } catch (ClassNotFoundException e) {
                throw new FacesException(e);
            }
    	}

    	return tabHeaderRenderer;
    }

    @Override
    protected void doDecode(FacesContext context, UIComponent component) {
        super.doDecode(context, component);

        UITab tab = (UITab) component;
        UITabPanel panel = tab.getPane();

        Map<String, String> requestParameterMap = context.getExternalContext()
                .getRequestParameterMap();

        if (AjaxRendererUtils.isAjaxRequest(context)
                && tab.getSwitchTypeOrDefault().equals(UISwitchablePanel.AJAX_METHOD)
                && requestParameterMap.get(tab.getClientId(context)) != null) {

            // add toggle panel itself to rendered list of components
            AjaxRendererUtils.addRegionByName(context, panel, panel.getId());
            AjaxRendererUtils.addRegionsFromComponent(tab, context);

            AjaxContext ajaxContext = AjaxContext.getCurrentInstance(context);
            Set<String> toProcess = ajaxContext.getAjaxAreasToProcess();
            if (toProcess == null) {
                toProcess = new HashSet<String>(1);
                ajaxContext.setAjaxAreasToProcess(toProcess);
            }
            toProcess.add(panel.getClientId(context));

            ajaxContext.addAreasToProcessFromComponent(context, tab);
        }
    }

    public TabRendererBase() {
        super();
    }

    /**
     * Encode this tab header in Panel switch pane.
     *
     * @param context
     * @param tab
     * @param active
     * @throws IOException
     */
    public void encodeTab(FacesContext context, UITab tab, boolean active) throws IOException {
        getHeaderRenderer().encodeBegin(context, tab);
        getHeaderRenderer().encodeEnd(context, tab);
    }

    public String getTabDisplay(FacesContext context, UITab tab) {
        if (!tab.isActive()) {
            return "display: none;";
        }

        return "";
    }

    protected boolean shouldRenderTab(UITab tab) {
        String method = tab.getSwitchTypeOrDefault();
        return (tab.isActive() || !tab.isDisabled() && UISwitchablePanel.CLIENT_METHOD.equals(method));
    }

    public void encodeBegin(FacesContext context, UIComponent component)
            throws IOException {

        UITab tab = (UITab) component;
        if (shouldRenderTab(tab)) {
            super.encodeBegin(context, component);
        }
    }

    public void encodeEnd(FacesContext context, UIComponent component)
            throws IOException {

        UITab tab = (UITab) component;
        if (shouldRenderTab(tab)) {
            super.encodeEnd(context, component);
        }
    }

    public void encodeChildren(FacesContext context, UIComponent component) throws IOException {

        UITab tab = (UITab) component;
        if (shouldRenderTab(tab)) {
            if ((tab.getChildren() != null) && (tab.getChildren().size() > 0)) {
                renderChildren(context, component);
            } else {
                ResponseWriter out = context.getResponseWriter();
                out.write("&#160;");
            }
        }
    }

    protected Class getComponentClass() {
        return UITab.class;
    }

    public boolean getRendersChildren() {
        return true;
    }
}
