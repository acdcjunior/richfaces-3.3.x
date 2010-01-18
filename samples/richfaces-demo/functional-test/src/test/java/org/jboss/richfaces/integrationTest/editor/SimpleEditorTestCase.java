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

package org.jboss.richfaces.integrationTest.editor;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test case that tests the editor component.
 * 
 * @author <a href="mailto:ppitonak@redhat.com">Pavol Pitonak</a>
 * @version $Revision$
 */
// TODO implement a test for cleaning up
public class SimpleEditorTestCase extends AbstractSeleniumRichfacesTestCase {

    // messages
    private final String MSG_STRINGS_SHOULD_BE_BOLD = getMsg("STRINGS_SHOULD_BE_BOLD");
    private final String MSG_STRINGS_SHOULD_BE_ITALIC = getMsg("STRINGS_SHOULD_BE_ITALIC");
    private final String MSG_STRINGS_SHOULD_BE_UNDERLINED = getMsg("STRINGS_SHOULD_BE_UNDERLINED");
    private final String MSG_STRINGS_SHOULD_HAVE_A_LINE_THROUGH = getMsg("STRINGS_SHOULD_HAVE_A_LINE_THROUGH");
    private final String MSG_CONTENT_OF_THE_EDITOR = getMsg("CONTENT_OF_THE_EDITOR");
    private final String MSG_EDITOR_SHOULD_CONTAIN_OL = getMsg("EDITOR_SHOULD_CONTAIN_OL");
    private final String MSG_EDITOR_SHOULD_CONTAIN_UL = getMsg("EDITOR_SHOULD_CONTAIN_UL");
    private final String MSG_TWO_LINES_SHOULD_CONTAIN_A_PARAGRAPH = getMsg("TWO_LINES_SHOULD_CONTAIN_A_PARAGRAPH");
    private final String MSG_TWO_LINES_IN_THE_EDITOR = getMsg("TWO_LINES_IN_THE_EDITOR");
    private final String MSG_LIST_SHOULD_CONTAIN_THREE_ITEMS = getMsg("LIST_SHOULD_CONTAIN_THREE_ITEMS");

    // editor
    private final String LOC_EXAMPLE_HEADER = getLoc("EXAMPLE_HEADER");
    private final String LOC_TEXT_AREA = getLoc("TEXT_AREA");
    private final String LOC_IFRAME = getLoc("IFRAME");
    private final String LOC_TEXT_AREA_P = getLoc("TEXT_AREA_P");
    private final String LOC_TEXT_AREA_P2_B = getLoc("TEXT_AREA_P2_B");
    private final String LOC_TEXT_AREA_P2_I = getLoc("TEXT_AREA_P2_I");
    private final String LOC_TEXT_AREA_P2_U = getLoc("TEXT_AREA_P2_U");
    private final String LOC_TEXT_AREA_P2_STRIKE = getLoc("TEXT_AREA_P2_STRIKE");
    private final String LOC_TEXT_AREA_OL = getLoc("TEXT_AREA_OL");
    private final String LOC_TEXT_AREA_OL_LI = getLoc("TEXT_AREA_OL_LI");
    private final String LOC_TEXT_AREA_UL = getLoc("TEXT_AREA_UL");
    private final String LOC_TEXT_AREA_UL_LI = getLoc("TEXT_AREA_UL_LI");

    // buttons in simple mode
    private final String LOC_BOLD_BUTTON = getLoc("BOLD_BUTTON");
    private final String LOC_ITALIC_BUTTON = getLoc("ITALIC_BUTTON");
    private final String LOC_UNDERLINE_BUTTON = getLoc("UNDERLINE_BUTTON");
    private final String LOC_STRIKETHROUGH_BUTTON = getLoc("STRIKETHROUGH_BUTTON");
    private final String LOC_UNDO_BUTTON = getLoc("UNDO_BUTTON");
    private final String LOC_REDO_BUTTON = getLoc("REDO_BUTTON");
    private final String LOC_UNORDERED_LIST_BUTTON = getLoc("UNORDERED_LIST_BUTTON");
    private final String LOC_ORDERED_LIST_BUTTON = getLoc("ORDERED_LIST_BUTTON");

    /**
     * Tests the bold button. It types "aaaa", presses enter followed by the
     * bold button, and types "bbbb ". Then again bold button, "cccc ", bold
     * button and "dddd ". Then it checks that the editor's text area contains
     * the entered text. After that, it checks that there are two lines of text
     * in the editor and that the second line contains two bold strings.
     */
    @Test
    public void testBoldButton() {
        selenium.typeKeys(LOC_TEXT_AREA, "aaaa"); // normal text
        selenium.keyPress(LOC_TEXT_AREA, "13"); // press <Enter>

        selenium.click(LOC_BOLD_BUTTON);
        selenium.typeKeys(LOC_TEXT_AREA, "bbbb "); // bold text
        selenium.click(LOC_BOLD_BUTTON);
        selenium.typeKeys(LOC_TEXT_AREA, "cccc "); // normal text
        selenium.click(LOC_BOLD_BUTTON);
        selenium.typeKeys(LOC_TEXT_AREA, "dddd "); // bold text

        String text = selenium.getText(LOC_TEXT_AREA);
        assertEquals(text, "aaaa\nbbbb cccc dddd", MSG_CONTENT_OF_THE_EDITOR);

        // select the iframe for Selenium to be able to get iframe's content
        selenium.selectFrame(LOC_IFRAME);

        int count = getJQueryCount(LOC_TEXT_AREA_P);
        assertEquals(count, 2, MSG_TWO_LINES_IN_THE_EDITOR);

        count = getJQueryCount(LOC_TEXT_AREA_P2_B);
        assertEquals(count, 2, MSG_STRINGS_SHOULD_BE_BOLD);

        selenium.selectFrame("relative=top");
    }

    /**
     * Tests the italic button. It types "aaaa", presses enter followed by the
     * italic button, and types "bbbb ". Then again italic button, "cccc ",
     * italic button and "dddd ". Then it checks that the editor's text area
     * contains the entered text. After that, it checks that there are two lines
     * of text in the editor and that the second line contains two italic
     * strings.
     */
    @Test
    public void testItalicButton() {
        selenium.typeKeys(LOC_TEXT_AREA, "aaaa"); // normal text
        selenium.keyPress(LOC_TEXT_AREA, "13"); // press <Enter>

        selenium.click(LOC_ITALIC_BUTTON);
        selenium.typeKeys(LOC_TEXT_AREA, "bbbb "); // italic text
        selenium.click(LOC_ITALIC_BUTTON);
        selenium.typeKeys(LOC_TEXT_AREA, "cccc "); // normal text
        selenium.click(LOC_ITALIC_BUTTON);
        selenium.typeKeys(LOC_TEXT_AREA, "dddd "); // italic text

        String text = selenium.getText(LOC_TEXT_AREA);
        assertEquals(text, "aaaa\nbbbb cccc dddd", MSG_CONTENT_OF_THE_EDITOR);

        // select the iframe for Selenium to be able to get iframe's content
        selenium.selectFrame(LOC_IFRAME);

        int count = getJQueryCount(LOC_TEXT_AREA_P);
        assertEquals(count, 2, MSG_TWO_LINES_IN_THE_EDITOR);

        count = getJQueryCount(LOC_TEXT_AREA_P2_I);
        assertEquals(count, 2, MSG_STRINGS_SHOULD_BE_ITALIC);

        selenium.selectFrame("relative=top");
    }

    /**
     * Tests the underline button. It types "aaaa", presses enter followed by
     * the underline button, and types "bbbb ". Then again underline button,
     * "cccc ", underline button and "dddd ". Then it checks that the editor's
     * text area contains the entered text. After that, it checks that there are
     * two lines of text in the editor and that the second line contains two
     * underlined strings.
     */
    @Test
    public void testUnderlineButton() {
        selenium.typeKeys(LOC_TEXT_AREA, "aaaa"); // normal text
        selenium.keyPress(LOC_TEXT_AREA, "13"); // press <Enter>

        selenium.click(LOC_UNDERLINE_BUTTON);
        selenium.typeKeys(LOC_TEXT_AREA, "bbbb "); // underlined text
        selenium.click(LOC_UNDERLINE_BUTTON);
        selenium.typeKeys(LOC_TEXT_AREA, "cccc "); // normal text
        selenium.click(LOC_UNDERLINE_BUTTON);
        selenium.typeKeys(LOC_TEXT_AREA, "dddd "); // underlined text

        String text = selenium.getText(LOC_TEXT_AREA);
        assertEquals(text, "aaaa\nbbbb cccc dddd", MSG_CONTENT_OF_THE_EDITOR);

        // select the iframe for Selenium to be able to get iframe's content
        selenium.selectFrame(LOC_IFRAME);

        int count = getJQueryCount(LOC_TEXT_AREA_P);
        assertEquals(count, 2, MSG_TWO_LINES_IN_THE_EDITOR);

        count = getJQueryCount(LOC_TEXT_AREA_P2_U);
        assertEquals(count, 2, MSG_STRINGS_SHOULD_BE_UNDERLINED);

        selenium.selectFrame("relative=top");
    }

    /**
     * Tests the strikethrough button. It types "aaaa", presses enter followed
     * by the strikethrough button, and types "bbbb ". Then again strikethrough
     * button, "cccc ", strikethrough button and "dddd ". Then it checks that
     * the editor's text area contains the entered text. After that, it checks
     * that there are two lines of text in the editor and that the second line
     * contains two strings having a line through.
     */
    @Test
    public void testStrikethroughButton() {
        selenium.typeKeys(LOC_TEXT_AREA, "aaaa"); // normal text
        selenium.keyPress(LOC_TEXT_AREA, "13"); // press <Enter>

        selenium.click(LOC_STRIKETHROUGH_BUTTON);
        selenium.typeKeys(LOC_TEXT_AREA, "bbbb "); // text with line through
        selenium.click(LOC_STRIKETHROUGH_BUTTON);
        selenium.typeKeys(LOC_TEXT_AREA, "cccc "); // normal text
        selenium.click(LOC_STRIKETHROUGH_BUTTON);
        selenium.typeKeys(LOC_TEXT_AREA, "dddd "); // text with line through

        String text = selenium.getText(LOC_TEXT_AREA);
        assertEquals(text, "aaaa\nbbbb cccc dddd", MSG_CONTENT_OF_THE_EDITOR);

        // select the iframe for Selenium to be able to get iframe's content
        selenium.selectFrame(LOC_IFRAME);

        int count = getJQueryCount(LOC_TEXT_AREA_P);
        assertEquals(count, 2, MSG_TWO_LINES_IN_THE_EDITOR);

        count = getJQueryCount(LOC_TEXT_AREA_P2_STRIKE);
        assertEquals(count, 2, MSG_STRINGS_SHOULD_HAVE_A_LINE_THROUGH);

        selenium.selectFrame("relative=top");
    }

    /**
     * Tests both the undo and redo buttons. It types into text area, checks the
     * content of the editor, reverts changes by clicking on the undo button,
     * and checks the content of the editor again. Then, it clicks on the redo
     * button and checks the text area.
     */
    @Test
    public void testUndoRedo() {
        selenium.typeKeys(LOC_TEXT_AREA, "aaaabbbb");

        String text = selenium.getText(LOC_TEXT_AREA);
        assertEquals(text, "aaaabbbb", MSG_CONTENT_OF_THE_EDITOR);

        selenium.click(LOC_UNDO_BUTTON);
        text = selenium.getText(LOC_TEXT_AREA);
        assertEquals(text, "", MSG_CONTENT_OF_THE_EDITOR);

        selenium.click(LOC_REDO_BUTTON);
        text = selenium.getText(LOC_TEXT_AREA);
        assertEquals(text, "aaaabbbb", MSG_CONTENT_OF_THE_EDITOR);
    }

    /**
     * Tests the unordered list button. It types a normal string, then clicks on
     * the unordered list button, types three strings, clicks on the unordered
     * list button, and types one more string. Then it checks that the text area
     * contains all strings, then checks that there are two paragraphs, presence
     * of unordered list, and the number of list's items.
     */
    @Test
    public void testUnorderedList() {
        selenium.typeKeys(LOC_TEXT_AREA, "aaaa");
        selenium.keyPress(LOC_TEXT_AREA, "13"); // press <Enter>

        selenium.click(LOC_UNORDERED_LIST_BUTTON);
        selenium.typeKeys(LOC_TEXT_AREA, "bbbb");
        selenium.keyPress(LOC_TEXT_AREA, "13"); // press <Enter>
        selenium.typeKeys(LOC_TEXT_AREA, "cccc");
        selenium.keyPress(LOC_TEXT_AREA, "13"); // press <Enter>
        selenium.typeKeys(LOC_TEXT_AREA, "dddd");
        selenium.keyPress(LOC_TEXT_AREA, "13"); // press <Enter>
        selenium.click(LOC_UNORDERED_LIST_BUTTON);

        selenium.typeKeys(LOC_TEXT_AREA, "eeee");

        // select the iframe for Selenium to be able to get iframe's content
        selenium.selectFrame(LOC_IFRAME);

        String text = selenium.getText(LOC_TEXT_AREA);
        assertEquals(text, "aaaa\nbbbbccccddddeeee", MSG_CONTENT_OF_THE_EDITOR);

        int count = getJQueryCount(LOC_TEXT_AREA_P);
        assertEquals(count, 2, MSG_TWO_LINES_SHOULD_CONTAIN_A_PARAGRAPH);

        boolean isPresent = selenium.isElementPresent(LOC_TEXT_AREA_UL);
        assertTrue(isPresent, MSG_EDITOR_SHOULD_CONTAIN_UL);

        count = getJQueryCount(LOC_TEXT_AREA_UL_LI);
        assertEquals(count, 3, MSG_LIST_SHOULD_CONTAIN_THREE_ITEMS);

        selenium.selectFrame("relative=top");
    }

    /**
     * Tests the ordered list button. It types a normal string, then clicks on
     * the ordered list button, types three strings, clicks on the ordered list
     * button, and types one more string. Then it checks that the text area
     * contains all strings, then checks that there are two paragraphs, presence
     * of ordered list, and the number of list's items.
     */
    @Test
    public void testOrderedList() {
        selenium.typeKeys(LOC_TEXT_AREA, "aaaa");
        selenium.keyPress(LOC_TEXT_AREA, "13"); // press <Enter>

        selenium.click(LOC_ORDERED_LIST_BUTTON);
        selenium.typeKeys(LOC_TEXT_AREA, "bbbb");
        selenium.keyPress(LOC_TEXT_AREA, "13"); // press <Enter>
        selenium.typeKeys(LOC_TEXT_AREA, "cccc");
        selenium.keyPress(LOC_TEXT_AREA, "13"); // press <Enter>
        selenium.typeKeys(LOC_TEXT_AREA, "dddd");
        selenium.keyPress(LOC_TEXT_AREA, "13"); // press <Enter>
        selenium.click(LOC_ORDERED_LIST_BUTTON);

        selenium.typeKeys(LOC_TEXT_AREA, "eeee");

        // select the iframe for Selenium to be able to get iframe's content
        selenium.selectFrame(LOC_IFRAME);

        String text = selenium.getText(LOC_TEXT_AREA);
        assertEquals(text, "aaaa\nbbbbccccddddeeee", MSG_CONTENT_OF_THE_EDITOR);

        int count = getJQueryCount(LOC_TEXT_AREA_P);
        assertEquals(count, 2, MSG_TWO_LINES_SHOULD_CONTAIN_A_PARAGRAPH);

        boolean isPresent = selenium.isElementPresent(LOC_TEXT_AREA_OL);
        assertTrue(isPresent, MSG_EDITOR_SHOULD_CONTAIN_OL);

        count = getJQueryCount(LOC_TEXT_AREA_OL_LI);
        assertEquals(count, 3, MSG_LIST_SHOULD_CONTAIN_THREE_ITEMS);

        selenium.selectFrame("relative=top");
    }

    /**
     * Tests the "View Source". It checks that the source code is not visible,
     * clicks on the link, and checks 17 lines of source code.
     */
    @Test
    public void testEditorSource() {
        String[] strings = new String[] {
                "<ui:composition xmlns=\"http://www.w3.org/1999/xhtml\"",
                "<h:form id=\"form\">",
                "<h:panelGrid columns=\"2\" width=\"100%\" columnClasses=\"column,column\">",
                "<h:panelGroup style=\"height:320px;width:400px;\" layout=\"block\">",
                "<rich:editor configuration=\"#{editorBean.currentConfiguration}\"",
                "id=\"editor\" width=\"400\" height=\"300\" validator=\"#{editorBean.validate}\"",
                "viewMode=\"#{editorBean.viewMode}\" value=\"#{editorBean.value}\" useSeamText=\"#{editorBean.useSeamText}\">",
                "<a4j:support event=\"onchange\" reRender=\"result\" ajaxSingle=\"true\"",
                "requestDelay=\"1000\" ",
                "onsubmit=\"if (!#{rich:element('form:editorvalue')} &amp;&amp; !#{editorBean.useSeamText}) return false;\" />",
                "<rich:panel id=\"controls\">",
                "<f:facet name=\"header\">",
                "<h:panelGrid columns=\"2\">",
                "<h:selectOneRadio value=\"#{editorBean.currentConfiguration}\"",
                " <h:selectBooleanCheckbox value=\"#{editorBean.liveUpdatesEnabled}\"",
                " valueChangeListener=\"#{inputResetBean.processValueChange}\">",
                "<a4j:support event=\"onclick\" reRender=\"editor,result,controls\" requestDelay=\"500\" ajaxSingle=\"true\"", };

        abstractTestSource(1, "View Source", strings);
    }

    /**
     * Loads the page containing needed component.
     */
    protected void loadPage() {
        openComponent("Editor");
        // wait for iframe to load
        waitFor(1000);
        scrollIntoView(LOC_EXAMPLE_HEADER, true);
    }
}
