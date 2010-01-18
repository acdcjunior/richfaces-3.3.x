/*******************************************************************************
 * JBoss, Home of Professional Open Source
 * Copyright 2009, Red Hat, Inc. and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 *******************************************************************************/

package org.jboss.richfaces.integrationTest.contextMenu;

import static org.testng.Assert.assertTrue;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Tests context menu in the first example.
 * 
 * @author <a href="mailto:ppitonak@redhat.com">Pavol Pitonak</a>
 * @version $Revision$
 */
public class ContextMenuImageTestCase extends AbstractSeleniumRichfacesTestCase {

    private final String LOC_FIRST_HEADER = getLoc("FIRST_HEADER");
    private final String LOC_FIRST_IMAGE = getLoc("FIRST_IMAGE");
    private final String LOC_FIRST_CONTEXT_MENU = getLoc("FIRST_CONTEXT_MENU");
    private final String LOC_FIRST_ZOOM_IN = getLoc("FIRST_ZOOM_IN");
    private final String LOC_FIRST_ZOOM_OUT = getLoc("FIRST_ZOOM_OUT");

    /**
     * Clicks into image and verifies that the context menu is displayed.
     */
    @Test
    public void testImageContextMenu() {
        // open context menu
        selenium.fireEvent(LOC_FIRST_IMAGE, "contextmenu");

        // check that context menu is visible
        waitForElement(LOC_FIRST_CONTEXT_MENU);
        assertTrue(isDisplayed(LOC_FIRST_CONTEXT_MENU), "Context menu should be visible.");
    }

    /**
     * Tests zooming in. It gets width and height of the image, then opens the
     * context menu and zooms in four times. Then it gets image's width and
     * height again and checks that the image got bigger.
     */
    @Test
    public void testZoomIn() {
        // get the size of image at the beginning
        int originalWidth = selenium.getElementWidth(LOC_FIRST_IMAGE).intValue();
        int originalHeight = selenium.getElementHeight(LOC_FIRST_IMAGE).intValue();

        // open context menu
        selenium.fireEvent(LOC_FIRST_IMAGE, "contextmenu");
        waitForElement(LOC_FIRST_ZOOM_IN);

        // zoom in
        selenium.click(LOC_FIRST_ZOOM_IN);
        selenium.click(LOC_FIRST_ZOOM_IN);
        selenium.click(LOC_FIRST_ZOOM_IN);
        selenium.click(LOC_FIRST_ZOOM_IN);

        // get the size of image after zooming in
        int width = selenium.getElementWidth(LOC_FIRST_IMAGE).intValue();
        assertTrue(width > originalWidth, format(
                "After zooming in, the image should be bigger (width {0}px -> {1}px).", originalWidth, width));

        int height = selenium.getElementHeight(LOC_FIRST_IMAGE).intValue();
        assertTrue(height > originalHeight, format(
                "After zooming in, the image should be bigger (height {0}px -> {1}px).", originalHeight, height));
    }

    /**
     * Tests zooming out. It gets width and height of the image, then opens the
     * context menu and zooms out four times. Then it gets image's width and
     * height again and checks that the image got smaller.
     */
    @Test
    void testZoomOut() {
        // get the size of image at the beginning
        int originalWidth = selenium.getElementWidth(LOC_FIRST_IMAGE).intValue();
        int originalHeight = selenium.getElementHeight(LOC_FIRST_IMAGE).intValue();

        // open context menu
        selenium.fireEvent(LOC_FIRST_IMAGE, "contextmenu");
        waitForElement(LOC_FIRST_ZOOM_OUT);

        // zoom out
        selenium.click(LOC_FIRST_ZOOM_OUT);
        selenium.click(LOC_FIRST_ZOOM_OUT);
        selenium.click(LOC_FIRST_ZOOM_OUT);
        selenium.click(LOC_FIRST_ZOOM_OUT);

        // get the size of image after zooming out
        int width = selenium.getElementWidth(LOC_FIRST_IMAGE).intValue();
        assertTrue(originalWidth > width, format(
                "After zooming out, the image should be smaller (width {0}px -> {1}px).", originalWidth, width));

        int height = selenium.getElementHeight(LOC_FIRST_IMAGE).intValue();
        assertTrue(originalHeight > height, format(
                "After zooming out, the image should be smaller (height {0}px -> {1}px).", originalHeight, height));
    }

    /**
     * Tests zooming in and out. It gets width and height of the image, then
     * opens the context menu and zooms in and out. Then it gets image's width
     * and height again and checks that the image has the same proportions as at
     * start.
     */
    @Test
    void testZoomInZoomOut() {
        // get the size of image at the beginning
        int originalWidth = selenium.getElementWidth(LOC_FIRST_IMAGE).intValue();
        int originalHeight = selenium.getElementHeight(LOC_FIRST_IMAGE).intValue();

        // open context menu
        selenium.fireEvent(LOC_FIRST_IMAGE, "contextmenu");
        waitForElement(LOC_FIRST_ZOOM_IN);

        selenium.click(LOC_FIRST_ZOOM_IN);
        selenium.click(LOC_FIRST_ZOOM_OUT);

        // get the size of image after zooming in and out
        // it does not zoom accurately so there has to be some tolerance
        int width = selenium.getElementWidth(LOC_FIRST_IMAGE).intValue();
        assertTrue(Math.abs(width - originalWidth) < 3, format(
                "After zooming in and out, the image should have the same size (width {0}px -> {1}px).", originalWidth,
                width));

        int height = selenium.getElementHeight(LOC_FIRST_IMAGE).intValue();
        assertTrue(Math.abs(height - originalHeight) < 3, format(
                "After zooming in and out, the image should have the same size (height {0}px -> {1}px).",
                originalHeight, height));
    }

    /**
     * Tests the "View Source" link. It checks that the source code is not
     * visible, clicks on the link, and checks 8 lines of source code.
     */
    @Test
    public void testPageSource() {
        String[] strings = new String[] {
                "<ui:composition xmlns=\"http://www.w3.org/1999/xhtml\"",
                "<a4j:loadScript src=\"/scripts/picturesUtils.js\"/>",
                "<h:panelGrid columns=\"1\" columnClasses=\"cent\">",
                "<h:panelGroup id=\"picture\">",
                "<h:graphicImage value=\"/richfaces/jQuery/images/pic1.jpg\" id=\"pic\" style=\"border : 5px solid #E4EAEF\"/>",
                "<rich:contextMenu event=\"oncontextmenu\" attachTo=\"pic\" submitMode=\"none\">",
                "<rich:menuItem value=\"Zoom In\" onclick=\"enlarge('pic');\" id=\"zin\"></rich:menuItem>",
                "<rich:menuItem value=\"Zoom Out\" onclick=\"decrease('pic');\" id=\"zout\"></rich:menuItem>", };

        abstractTestSource(1, "View Source", strings);
    }

    /**
     * Loads the needed page.
     */
    protected void loadPage() {
        openComponent("Context Menu");

        // XXX: context menu opens in top left corner so it isn't visible if it
        // scrolls down
        // scrollIntoView(LOC_FIRST_HEADER, true);
    }
}
