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
import static org.testng.Assert.fail;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.jboss.test.selenium.waiting.Condition;
import org.jboss.test.selenium.waiting.Wait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test case that tests the advanced version of the editor.
 * 
 * @author <a href="mailto:ppitonak@redhat.com">Pavol Pitonak</a>
 * @version $Revision$
 */
public class AdvancedEditorTestCase extends AbstractSeleniumRichfacesTestCase {

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
    private final String MSG_PARAGRAPH_ALIGNED_TO_THE_SIDE_S = getMsg("PARAGRAPH_ALIGNED_TO_THE_SIDE_S");
    private final String MSG_PARAGRAPH_JUSTIFIED = getMsg("PARAGRAPH_JUSTIFIED");
    private final String MSG_PARAGRAPH_SHOULD_HAVE_NO_STYLE = getMsg("PARAGRAPH_SHOULD_HAVE_NO_STYLE");
    private final String MSG_PARAGRAPHS_CLASS_ATTRIBUTE = getMsg("PARAGRAPHS_CLASS_ATTRIBUTE");
    private final String MSG_TAG_S_SHOULD_BE_IN_EDITOR = getMsg("TAG_S_SHOULD_BE_IN_EDITOR");
    private final String MSG_TAG_S_SHOULD_NOT_BE_IN_EDITOR = getMsg("TAG_S_SHOULD_NOT_BE_IN_EDITOR");
    private final String MSG_CONTENT_OF_TAG_S = getMsg("CONTENT_OF_TAG_S");
    private final String MSG_PARAGRAPH_INDENTED_BY_N_PX = getMsg("PARAGRAPH_INDENTED_BY_N_PX");
    private final String MSG_NUMBER_OF_RULERS = getMsg("NUMBER_OF_RULERS");
    private final String MSG_TEXT_IN_SUBSCRIPT = getMsg("TEXT_IN_SUBSCRIPT");
    private final String MSG_TEXT_IN_SUPERSCRIPT = getMsg("TEXT_IN_SUPERSCRIPT");
    private final String MSG_LABEL_OF_LINK = getMsg("LABEL_OF_LINK");
    private final String MSG_LINK_ATTRIBUTE_S = getMsg("LINK_ATTRIBUTE_S");
    private final String MSG_CLASS_ATTRIBUTE = getMsg("CLASS_ATTRIBUTE");
    private final String MSG_IMG_ATTRIBUTE_S = getMsg("IMG_ATTRIBUTE_S");

    private final String LOC_ADVANCED_BUTTON = getLoc("ADVANCED_BUTTON");
    private final String LOC_EXAMPLE_HEADER = getLoc("EXAMPLE_HEADER");

    // editor
    private final String LOC_TEXT_AREA = getLoc("TEXT_AREA");
    private final String LOC_IFRAME = getLoc("IFRAME");
    private final String LOC_ADV_DIALOG_IFRAME = getLoc("ADV_DIALOG_IFRAME");

    private final String LOC_TEXT_AREA_P = getLoc("TEXT_AREA_P");
    private final String LOC_TEXT_AREA_P_A = getLoc("TEXT_AREA_P_A");
    private final String LOC_TEXT_AREA_P_IMG = getLoc("TEXT_AREA_P_IMG");
    private final String LOC_TEXT_AREA_P_SPAN = getLoc("TEXT_AREA_P_SPAN");
    private final String LOC_TEXT_AREA_P_HR = getLoc("TEXT_AREA_P_HR");
    private final String LOC_TEXT_AREA_P_SUB = getLoc("TEXT_AREA_P_SUB");
    private final String LOC_TEXT_AREA_P_SUP = getLoc("TEXT_AREA_P_SUP");
    private final String LOC_TEXT_AREA_P2 = getLoc("TEXT_AREA_P2");
    private final String LOC_TEXT_AREA_P2_B = getLoc("TEXT_AREA_P2_B");
    private final String LOC_TEXT_AREA_P2_I = getLoc("TEXT_AREA_P2_I");
    private final String LOC_TEXT_AREA_P2_U = getLoc("TEXT_AREA_P2_U");
    private final String LOC_TEXT_AREA_P2_STRIKE = getLoc("TEXT_AREA_P2_STRIKE");
    private final String LOC_TEXT_AREA_OL = getLoc("TEXT_AREA_OL");
    private final String LOC_TEXT_AREA_OL_LI = getLoc("TEXT_AREA_OL_LI");
    private final String LOC_TEXT_AREA_UL = getLoc("TEXT_AREA_UL");
    private final String LOC_TEXT_AREA_UL_LI = getLoc("TEXT_AREA_UL_LI");
    private final String LOC_TEXT_AREA_ADDRESS = getLoc("TEXT_AREA_ADDRESS");
    private final String LOC_TEXT_AREA_PRE = getLoc("TEXT_AREA_PRE");
    private final String LOC_TEXT_AREA_HN = getLoc("TEXT_AREA_HN");

    // simple editor buttons
    private final String LOC_BOLD_BUTTON = getLoc("BOLD_BUTTON");
    private final String LOC_ITALIC_BUTTON = getLoc("ITALIC_BUTTON");
    private final String LOC_UNDERLINE_BUTTON = getLoc("UNDERLINE_BUTTON");
    private final String LOC_STRIKETHROUGH_BUTTON = getLoc("STRIKETHROUGH_BUTTON");
    private final String LOC_UNDO_BUTTON = getLoc("UNDO_BUTTON");
    private final String LOC_REDO_BUTTON = getLoc("REDO_BUTTON");

    // advanced editor buttons
    private final String LOC_ADV_UNORDERED_LIST_BUTTON = getLoc("ADV_UNORDERED_LIST_BUTTON");
    private final String LOC_ADV_ORDERED_LIST_BUTTON = getLoc("ADV_ORDERED_LIST_BUTTON");
    private final String LOC_ADV_ALIGN_LEFT_BUTTON = getLoc("ADV_ALIGN_LEFT_BUTTON");
    private final String LOC_ADV_ALIGN_RIGHT_BUTTON = getLoc("ADV_ALIGN_RIGHT_BUTTON");
    private final String LOC_ADV_ALIGN_CENTER_BUTTON = getLoc("ADV_ALIGN_CENTER_BUTTON");
    private final String LOC_ADV_ALIGN_FULL_BUTTON = getLoc("ADV_ALIGN_FULL_BUTTON");
    private final String LOC_ADV_STYLE_SELECT_BUTTON = getLoc("ADV_STYLE_SELECT_BUTTON");
    private final String LOC_ADV_FORMAT_SELECT_BUTTON = getLoc("ADV_FORMAT_SELECT_BUTTON");
    private final String LOC_ADV_STYLE_N = getLoc("ADV_STYLE_N");
    private final String LOC_ADV_FORMAT_N = getLoc("ADV_FORMAT_N");
    private final String LOC_ADV_INDENT_BUTTON = getLoc("ADV_INDENT_BUTTON");
    private final String LOC_ADV_OUTDENT_BUTTON = getLoc("ADV_OUTDENT_BUTTON");
    private final String LOC_ADV_HORIZONTAL_RULER_BUTTON = getLoc("ADV_HORIZONTAL_RULER_BUTTON");
    private final String LOC_ADV_REMOVE_FORMATTING_BUTTON = getLoc("ADV_REMOVE_FORMATTING_BUTTON");
    private final String LOC_ADV_SUBSCRIPT_BUTTON = getLoc("ADV_SUBSCRIPT_BUTTON");
    private final String LOC_ADV_SUPERSCRIPT_BUTTON = getLoc("ADV_SUPERSCRIPT_BUTTON");
    private final String LOC_ADV_LINK_DLG_URL = getLoc("ADV_LINK_DLG_URL");
    private final String LOC_ADV_LINK_DLG_TARGET_N = getLoc("ADV_LINK_DLG_TARGET_N");
    private final String LOC_ADV_LINK_DLG_TITLE = getLoc("ADV_LINK_DLG_TITLE");
    private final String LOC_ADV_LINK_DLG_CLASS_N = getLoc("ADV_LINK_DLG_CLASS_N");
    private final String LOC_ADV_LINK_DLG_UPDATE = getLoc("ADV_LINK_DLG_UPDATE");
    private final String LOC_ADV_LINK_BUTTON = getLoc("ADV_LINK_BUTTON");
    private final String LOC_ADV_UNLINK_BUTTON = getLoc("ADV_UNLINK_BUTTON");
    private final String LOC_ADV_TOGGLE_INVISIBLE_BUTTON = getLoc("ADV_TOGGLE_INVISIBLE_BUTTON");
    private final String LOC_ADV_ANCHOR_BUTTON = getLoc("ADV_ANCHOR_BUTTON");
    private final String LOC_ADV_ANCHOR_DLG_NAME = getLoc("ADV_ANCHOR_DLG_NAME");
    private final String LOC_ADV_ANCHOR_DLG_INSERT_BUTTON = getLoc("ADV_ANCHOR_DLG_INSERT_BUTTON");
    private final String LOC_ADV_IMAGE_BUTTON = getLoc("ADV_IMAGE_BUTTON");
    private final String LOC_ADV_IMAGE_DLG_URL = getLoc("ADV_IMAGE_DLG_URL");
    private final String LOC_ADV_IMAGE_DLG_DESCRIPTION = getLoc("ADV_IMAGE_DLG_DESCRIPTION");
    private final String LOC_ADV_IMAGE_DLG_ALIGNMENT = getLoc("ADV_IMAGE_DLG_ALIGNMENT");
    private final String LOC_ADV_IMAGE_DLG_WIDTH = getLoc("ADV_IMAGE_DLG_WIDTH");
    private final String LOC_ADV_IMAGE_DLG_HEIGHT = getLoc("ADV_IMAGE_DLG_HEIGHT");
    private final String LOC_ADV_IMAGE_DLG_BORDER = getLoc("ADV_IMAGE_DLG_BORDER");
    private final String LOC_ADV_IMAGE_DLG_VSPACE = getLoc("ADV_IMAGE_DLG_VSPACE");
    private final String LOC_ADV_IMAGE_DLG_HSPACE = getLoc("ADV_IMAGE_DLG_HSPACE");
    private final String LOC_ADV_IMAGE_DLG_INSERT_BUTTON = getLoc("ADV_IMAGE_DLG_INSERT_BUTTON");
//    private final String LOC_ADV_CUSTOM_CHAR_BUTTON = getLoc("ADV_CUSTOM_CHAR_BUTTON");
//    private final String LOC_ADV_CUSTOM_CHAR_DLG_M_N = getLoc("ADV_CUSTOM_CHAR_DLG_M_N");

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

        // small hack to enable the undo button
        selenium.click(LOC_UNDO_BUTTON);
        selenium.click(LOC_TEXT_AREA);

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

        selenium.click(LOC_ADV_UNORDERED_LIST_BUTTON);
        selenium.typeKeys(LOC_TEXT_AREA, "bbbb");
        selenium.keyPress(LOC_TEXT_AREA, "13"); // press <Enter>
        selenium.typeKeys(LOC_TEXT_AREA, "cccc");
        selenium.keyPress(LOC_TEXT_AREA, "13"); // press <Enter>
        selenium.typeKeys(LOC_TEXT_AREA, "dddd");
        selenium.keyPress(LOC_TEXT_AREA, "13"); // press <Enter>
        selenium.click(LOC_ADV_UNORDERED_LIST_BUTTON);

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

        selenium.click(LOC_ADV_ORDERED_LIST_BUTTON);
        selenium.typeKeys(LOC_TEXT_AREA, "bbbb");
        selenium.keyPress(LOC_TEXT_AREA, "13"); // press <Enter>
        selenium.typeKeys(LOC_TEXT_AREA, "cccc");
        selenium.keyPress(LOC_TEXT_AREA, "13"); // press <Enter>
        selenium.typeKeys(LOC_TEXT_AREA, "dddd");
        selenium.keyPress(LOC_TEXT_AREA, "13"); // press <Enter>
        selenium.click(LOC_ADV_ORDERED_LIST_BUTTON);

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
     * Tests aligning paragraphs. First it types long text into editor, then it
     * checks the style attribute of the paragraph. Then it tries to align the
     * paragraph to the left, right, center and justify it.
     */
    @Test
    public void testAlignParagraph() {
        for (int i = 0; i < 5; i++) {
            selenium.typeKeys(LOC_TEXT_AREA, "aaa bbb ccc ddd eee fff ggg ");
        }

        String attr = null;
        try {
            attr = selenium.getAttribute(LOC_TEXT_AREA_P + "@style");
            fail(MSG_PARAGRAPH_SHOULD_HAVE_NO_STYLE);
        } catch (Exception e) {
            // OK - there is no attribute
        }

        selenium.click(LOC_ADV_ALIGN_LEFT_BUTTON);
        attr = getStyle(LOC_TEXT_AREA_P, "text-align");
        assertEquals(attr, "left", format(MSG_PARAGRAPH_ALIGNED_TO_THE_SIDE_S, "left"));

        selenium.click(LOC_ADV_ALIGN_RIGHT_BUTTON);
        attr = getStyle(LOC_TEXT_AREA_P, "text-align");
        assertEquals(attr, "right", format(MSG_PARAGRAPH_ALIGNED_TO_THE_SIDE_S, "right"));

        selenium.click(LOC_ADV_ALIGN_CENTER_BUTTON);
        attr = getStyle(LOC_TEXT_AREA_P, "text-align");
        assertEquals(attr, "center", format(MSG_PARAGRAPH_ALIGNED_TO_THE_SIDE_S, "center"));

        selenium.click(LOC_ADV_ALIGN_FULL_BUTTON);
        attr = getStyle(LOC_TEXT_AREA_P, "text-align");
        assertEquals(attr, "justify", MSG_PARAGRAPH_JUSTIFIED);
    }

    /**
     * Tests all styles of the text. It goes through all options from the Styles
     * select.
     */
    @Test
    public void testStyles() {
        selenium.typeKeys(LOC_TEXT_AREA, "aaa bbb ccc ddd eee fff ggg");

        try {
            selenium.getAttribute(LOC_TEXT_AREA_P + "@class");
            fail(MSG_PARAGRAPH_SHOULD_HAVE_NO_STYLE);
        } catch (Exception e) {
            // OK - there is no attribute
        }

        selenium.click(LOC_ADV_STYLE_SELECT_BUTTON);
        selenium.click(format(LOC_ADV_STYLE_N, 1));
        assertTrue(belongsClass("bold-larger", LOC_TEXT_AREA_P), MSG_PARAGRAPHS_CLASS_ATTRIBUTE);

        selenium.click(LOC_ADV_STYLE_SELECT_BUTTON);
        selenium.click(format(LOC_ADV_STYLE_N, 2));
        assertTrue(belongsClass("bold-smaller", LOC_TEXT_AREA_P), MSG_PARAGRAPHS_CLASS_ATTRIBUTE);

        selenium.click(LOC_ADV_STYLE_SELECT_BUTTON);
        selenium.click(format(LOC_ADV_STYLE_N, 3));
        assertTrue(belongsClass("red-bold", LOC_TEXT_AREA_P), MSG_PARAGRAPHS_CLASS_ATTRIBUTE);

        selenium.click(LOC_ADV_STYLE_SELECT_BUTTON);
        selenium.click(format(LOC_ADV_STYLE_N, 4));
        assertTrue(belongsClass("red-bold-larger", LOC_TEXT_AREA_P), MSG_PARAGRAPHS_CLASS_ATTRIBUTE);

        selenium.click(LOC_ADV_STYLE_SELECT_BUTTON);
        selenium.click(format(LOC_ADV_STYLE_N, 5));
        assertTrue(belongsClass("red-bold-smaller", LOC_TEXT_AREA_P), MSG_PARAGRAPHS_CLASS_ATTRIBUTE);

        selenium.click(LOC_ADV_STYLE_SELECT_BUTTON);
        selenium.click(format(LOC_ADV_STYLE_N, 0));
        try {
            selenium.getAttribute(LOC_TEXT_AREA_P + "@class");
            fail(MSG_PARAGRAPH_SHOULD_HAVE_NO_STYLE);
        } catch (Exception e) {
            // OK - there is no attribute
        }
    }

    /**
     * Tests various formats of the paragraph. It goes through all options from
     * Format select.
     */
    @Test
    public void testFormat() {
        selenium.typeKeys(LOC_TEXT_AREA, "aaa bbb ccc ddd eee fff ggg");

        // initial state
        selenium.selectFrame(LOC_IFRAME);
        int count = getJQueryCount(LOC_TEXT_AREA_P);
        assertEquals(count, 1, format(MSG_TAG_S_SHOULD_BE_IN_EDITOR, "p"));
        String text = selenium.getText(LOC_TEXT_AREA_P);
        assertEquals(text, "aaa bbb ccc ddd eee fff ggg", format(MSG_CONTENT_OF_TAG_S, "p"));
        selenium.selectFrame("relative=top");

        // address
        selenium.click(LOC_ADV_FORMAT_SELECT_BUTTON);
        selenium.click(format(LOC_ADV_FORMAT_N, 2));
        selenium.selectFrame(LOC_IFRAME);
        count = getJQueryCount(LOC_TEXT_AREA_ADDRESS);
        assertEquals(count, 1, format(MSG_TAG_S_SHOULD_BE_IN_EDITOR, "address"));
        text = selenium.getText(LOC_TEXT_AREA_ADDRESS);
        assertEquals(text, "aaa bbb ccc ddd eee fff ggg", format(MSG_CONTENT_OF_TAG_S, "address"));
        selenium.selectFrame("relative=top");

        // paragraph
        selenium.click(LOC_ADV_FORMAT_SELECT_BUTTON);
        selenium.click(format(LOC_ADV_FORMAT_N, 1));
        selenium.selectFrame(LOC_IFRAME);
        count = getJQueryCount(LOC_TEXT_AREA_P);
        assertEquals(count, 1, format(MSG_TAG_S_SHOULD_BE_IN_EDITOR, "p"));
        text = selenium.getText(LOC_TEXT_AREA_P);
        assertEquals(text, "aaa bbb ccc ddd eee fff ggg", format(MSG_CONTENT_OF_TAG_S, "p"));
        selenium.selectFrame("relative=top");

        // preformatted
        selenium.click(LOC_ADV_FORMAT_SELECT_BUTTON);
        selenium.click(format(LOC_ADV_FORMAT_N, 3));
        selenium.selectFrame(LOC_IFRAME);
        count = getJQueryCount(LOC_TEXT_AREA_PRE);
        assertEquals(count, 1, format(MSG_TAG_S_SHOULD_BE_IN_EDITOR, "pre"));
        text = selenium.getText(LOC_TEXT_AREA_PRE);
        assertEquals(text, "aaa bbb ccc ddd eee fff ggg", format(MSG_CONTENT_OF_TAG_S, "pre"));
        selenium.selectFrame("relative=top");

        // change back to the initial state by clicking on "Format"
        selenium.click(LOC_ADV_FORMAT_SELECT_BUTTON);
        selenium.click(format(LOC_ADV_FORMAT_N, 0));
        selenium.selectFrame(LOC_IFRAME);
        count = getJQueryCount(LOC_TEXT_AREA_P);
        assertEquals(count, 1, format(MSG_TAG_S_SHOULD_BE_IN_EDITOR, "p"));
        text = selenium.getText(LOC_TEXT_AREA_P);
        assertEquals(text, "aaa bbb ccc ddd eee fff ggg", format(MSG_CONTENT_OF_TAG_S, "p"));
        selenium.selectFrame("relative=top");

        // heading 1-6
        for (int i = 1; i < 7; i++) {
            selenium.click(LOC_ADV_FORMAT_SELECT_BUTTON);
            selenium.click(format(LOC_ADV_FORMAT_N, 3 + i));
            selenium.selectFrame(LOC_IFRAME);
            count = getJQueryCount(format(LOC_TEXT_AREA_HN, i));
            assertEquals(count, 1, format(MSG_TAG_S_SHOULD_BE_IN_EDITOR, "h" + i));
            text = selenium.getText(format(LOC_TEXT_AREA_HN, i));
            assertEquals(text, "aaa bbb ccc ddd eee fff ggg", format(MSG_CONTENT_OF_TAG_S, "h" + i));
            selenium.selectFrame("relative=top");
        }
    }

    /**
     * Tests indenting and outdenting. It click the indent button and checks
     * indenting. Then it clicks it second time and again checks the style
     * attribute. Then it outdents the paragraph twice and checks the padding
     * after each click.
     */
    @Test
    public void testIndentOutdent() {
        selenium.typeKeys(LOC_TEXT_AREA, "aaa bbb ccc ddd eee fff ggg");

        selenium.click(LOC_ADV_INDENT_BUTTON);
        assertEquals(getStyle(LOC_TEXT_AREA_P, "padding-left"), "30px", format(MSG_PARAGRAPH_INDENTED_BY_N_PX, 30));

        selenium.click(LOC_ADV_INDENT_BUTTON);
        assertEquals(getStyle(LOC_TEXT_AREA_P, "padding-left"), "60px", format(MSG_PARAGRAPH_INDENTED_BY_N_PX, 60));

        selenium.click(LOC_ADV_OUTDENT_BUTTON);
        assertEquals(getStyle(LOC_TEXT_AREA_P, "padding-left"), "30px", format(MSG_PARAGRAPH_INDENTED_BY_N_PX, 30));

        selenium.click(LOC_ADV_OUTDENT_BUTTON);
        try {
            selenium.getAttribute(LOC_TEXT_AREA_P + "@style");
            fail(format(MSG_PARAGRAPH_INDENTED_BY_N_PX, 0));
        } catch (Exception e) {
            // OK - no attribute
        }
    }

    /**
     * Tests inserting horizontal lines. First it clicks on ruler button three
     * times. Then it checks that there are 3 rulers in the editor.
     */
    @Test
    public void testInsertHorizontalRuler() {
        selenium.click(LOC_ADV_HORIZONTAL_RULER_BUTTON);
        selenium.click(LOC_ADV_HORIZONTAL_RULER_BUTTON);
        selenium.click(LOC_ADV_HORIZONTAL_RULER_BUTTON);

        selenium.selectFrame(LOC_IFRAME);

        Wait.failWith(MSG_NUMBER_OF_RULERS).interval(3000).until(new Condition() {
            public boolean isTrue() {
                return getJQueryCount(LOC_TEXT_AREA_P_HR) == 3;
            }
        });

        selenium.selectFrame("relative=top");
    }

    /**
     * Tests removing formatting from selected text. First it types two lines of
     * text, from which the second is in bold. Then it presses Ctrl+A to select
     * the content of the editor and clicks the "Remove formatting" button. Then
     * it checks that formatting was removed.
     */
    @Test
    public void testRemoveFormatting() {
        selenium.typeKeys(LOC_TEXT_AREA, "aaa");
        selenium.keyPress(LOC_TEXT_AREA, "13"); // press <Enter>
        selenium.click(LOC_BOLD_BUTTON);
        selenium.typeKeys(LOC_TEXT_AREA, "bbb");

        selenium.selectFrame(LOC_IFRAME);
        int count = getJQueryCount(LOC_TEXT_AREA_P2_B);
        assertEquals(count, 1, format(MSG_TAG_S_SHOULD_BE_IN_EDITOR, "b"));
        String text = selenium.getText(LOC_TEXT_AREA_P2_B);
        assertEquals(text, "bbb", format(MSG_CONTENT_OF_TAG_S, "b"));

        // press Ctrl+A to select content of the editor
        selenium.controlKeyDown();
        selenium.keyPress("//body", "a");
        selenium.controlKeyUp();

        selenium.selectFrame("relative=top");
        selenium.click(LOC_ADV_REMOVE_FORMATTING_BUTTON);

        count = getJQueryCount(LOC_TEXT_AREA_P2_B);
        assertEquals(count, 0, format(MSG_TAG_S_SHOULD_NOT_BE_IN_EDITOR, "b"));
        text = selenium.getText(LOC_TEXT_AREA_P2);
        assertEquals(text, "bbb", format(MSG_CONTENT_OF_TAG_S, "b"));
    }

    /**
     * Test subscript and superscript. First it types a string containg normal
     * font, subscript, and superscript. Then it verifies that the editor
     * contains whole string and checks the subscript and superscript part.
     */
    @Test
    public void testSubscriptSuperscript() {
        selenium.typeKeys(LOC_TEXT_AREA, "aaa"); // normal
        selenium.click(LOC_ADV_SUBSCRIPT_BUTTON);
        selenium.typeKeys(LOC_TEXT_AREA, "bbb"); // subscript
        selenium.click(LOC_ADV_SUBSCRIPT_BUTTON);
        selenium.typeKeys(LOC_TEXT_AREA, "ccc"); // normal
        selenium.click(LOC_ADV_SUPERSCRIPT_BUTTON);
        selenium.typeKeys(LOC_TEXT_AREA, "ddd"); // superscript
        selenium.click(LOC_ADV_SUPERSCRIPT_BUTTON);
        selenium.typeKeys(LOC_TEXT_AREA, "eee"); // normal

        selenium.selectFrame(LOC_IFRAME);

        String text = selenium.getText(LOC_TEXT_AREA_P);
        assertEquals(text, "aaabbbcccdddeee", MSG_CONTENT_OF_THE_EDITOR);

        text = selenium.getText(LOC_TEXT_AREA_P_SUB);
        assertEquals(text, "bbb", MSG_TEXT_IN_SUBSCRIPT);

        text = selenium.getText(LOC_TEXT_AREA_P_SUP);
        assertEquals(text, "ddd", MSG_TEXT_IN_SUPERSCRIPT);

        selenium.selectFrame("relative=top");
    }

    /**
     * Tests inserting link and unlinking. First it types "aaa" into editor,
     * presses Ctrl+A to select the text and makes a link from this string. Then
     * it checks that proper tag with all settings was generated.
     */
    @Test
    public void testInsertLinkUnlink() {
        selenium.typeKeys(LOC_TEXT_AREA, "aaa");

        // press Ctrl+A to select content of the editor
        // XXX why is it necessary to do it twice - a bug in Selenium?
        selenium.click(LOC_TEXT_AREA_P);
        selenium.controlKeyDown();
        selenium.keyPress("//body", "a");
        selenium.controlKeyUp();
        selenium.click(LOC_TEXT_AREA_P);
        selenium.controlKeyDown();
        selenium.keyPress("//body", "a");
        selenium.controlKeyUp();

        waitForElement(LOC_ADV_LINK_BUTTON);
        selenium.click(LOC_ADV_LINK_BUTTON);

        waitForElement(LOC_ADV_DIALOG_IFRAME);
        selenium.selectFrame(LOC_ADV_DIALOG_IFRAME);

        waitForElement(LOC_ADV_LINK_DLG_URL);
        selenium.type(LOC_ADV_LINK_DLG_URL, "http://www.redhat.com");
        selenium.select(LOC_ADV_LINK_DLG_TARGET_N, "Open link in a new window");
        selenium.type(LOC_ADV_LINK_DLG_TITLE, "title title");
        selenium.select(LOC_ADV_LINK_DLG_CLASS_N, "red-bold");
        selenium.click(LOC_ADV_LINK_DLG_UPDATE);

        selenium.selectFrame(LOC_IFRAME);

        String text = selenium.getText(LOC_TEXT_AREA_P_A);
        assertEquals(text, "aaa", MSG_LABEL_OF_LINK);
        text = selenium.getAttribute(LOC_TEXT_AREA_P_A + "@class");
        assertEquals(text, "red-bold", format(MSG_LINK_ATTRIBUTE_S, "class"));
        text = selenium.getAttribute(LOC_TEXT_AREA_P_A + "@href");
        assertEquals(text, "http://www.redhat.com", format(MSG_LINK_ATTRIBUTE_S, "href"));
        text = selenium.getAttribute(LOC_TEXT_AREA_P_A + "@title");
        assertEquals(text, "title title", format(MSG_LINK_ATTRIBUTE_S, "title"));
        text = selenium.getAttribute(LOC_TEXT_AREA_P_A + "@target");
        assertEquals(text, "_blank", format(MSG_LINK_ATTRIBUTE_S, "target"));

        selenium.selectFrame("relative=top");

        // press Ctrl+A to select content of the editor
        selenium.click(LOC_TEXT_AREA_P);
        selenium.controlKeyDown();
        selenium.keyPress("//body", "a");
        selenium.controlKeyUp();
        selenium.click(LOC_TEXT_AREA_P);
        selenium.controlKeyDown();
        selenium.keyPress("//body", "a");
        selenium.controlKeyUp();

        selenium.click(LOC_ADV_UNLINK_BUTTON);

        selenium.selectFrame(LOC_IFRAME);

        text = selenium.getText(LOC_TEXT_AREA_P_SPAN);
        assertEquals(text, "aaa", format(MSG_CONTENT_OF_TAG_S, "span"));
        text = selenium.getAttribute(LOC_TEXT_AREA_P_SPAN + "@class");
        assertEquals(text, "red-bold", MSG_CLASS_ATTRIBUTE);

        selenium.selectFrame("relative=top");
    }

    /**
     * Tests inserting an anchor and hiding it in the editor.
     */
    @Test
    public void testToggleInvisibleElementsAndAnchor() {
        selenium.click(LOC_ADV_ANCHOR_BUTTON);

        waitForElement(LOC_ADV_DIALOG_IFRAME);
        selenium.selectFrame(LOC_ADV_DIALOG_IFRAME);

        selenium.type(LOC_ADV_ANCHOR_DLG_NAME, "aaa");
        selenium.click(LOC_ADV_ANCHOR_DLG_INSERT_BUTTON);

        String attr = selenium.getAttribute(LOC_TEXT_AREA_P_A + "@name");
        assertEquals(attr, "aaa", format(MSG_LINK_ATTRIBUTE_S, "name"));

        attr = selenium.getAttribute(LOC_TEXT_AREA_P_A + "@class");
        assertEquals(attr, "mceItemAnchor", format(MSG_LINK_ATTRIBUTE_S, "class"));

        selenium.click(LOC_ADV_TOGGLE_INVISIBLE_BUTTON);

        attr = selenium.getAttribute(LOC_TEXT_AREA_P_A + "@name");
        assertEquals(attr, "aaa", format(MSG_LINK_ATTRIBUTE_S, "name"));

        try {
            attr = selenium.getAttribute(LOC_TEXT_AREA_P_A + "@class");
            fail(format(MSG_LINK_ATTRIBUTE_S, "class"));
        } catch (Exception e) {
            // OK -- there is no class attribute
        }
        
        selenium.selectFrame("relative=top");
    }

    /**
     * Tests inserting an image into editor. It tries to insert the "Editor"
     * image that is on the page. It sets the URL, alternative text, alignment,
     * width, height, border, vertical space, and horizontal space.
     */
    @Test
    public void testInsertImage() {
        selenium.click(LOC_ADV_IMAGE_BUTTON);
        waitForElement(LOC_ADV_DIALOG_IFRAME);
        selenium.selectFrame(LOC_ADV_DIALOG_IFRAME);

        int index = selenium.getLocation().indexOf('/', 7);
        String url = selenium.getLocation().substring(0, index);
        url += "/richfaces-demo/images/cn_Editor.gif";

        selenium.type(LOC_ADV_IMAGE_DLG_URL, url);
        selenium.type(LOC_ADV_IMAGE_DLG_DESCRIPTION, "Editor");
        selenium.select(LOC_ADV_IMAGE_DLG_ALIGNMENT, "Right");
        selenium.type(LOC_ADV_IMAGE_DLG_WIDTH, "192");
        selenium.type(LOC_ADV_IMAGE_DLG_HEIGHT, "62");
        selenium.type(LOC_ADV_IMAGE_DLG_BORDER, "3");
        selenium.type(LOC_ADV_IMAGE_DLG_VSPACE, "10");
        selenium.type(LOC_ADV_IMAGE_DLG_HSPACE, "8");

        selenium.click(LOC_ADV_IMAGE_DLG_INSERT_BUTTON);

        waitForElement(LOC_TEXT_AREA_P_IMG);
        String attr = selenium.getAttribute(LOC_TEXT_AREA_P_IMG + "@src");
        assertEquals(attr, "../images/cn_Editor.gif", format(MSG_IMG_ATTRIBUTE_S, "src"));

        attr = selenium.getAttribute(LOC_TEXT_AREA_P_IMG + "@alt");
        assertEquals(attr, "Editor", format(MSG_IMG_ATTRIBUTE_S, "alt"));

        attr = selenium.getAttribute(LOC_TEXT_AREA_P_IMG + "@style");
        assertTrue(attr.contains("float: right;"), format(MSG_IMG_ATTRIBUTE_S, "style"));
        assertTrue(attr.contains("border: 3px solid black;"), format(MSG_IMG_ATTRIBUTE_S, "style"));
        assertTrue(attr.contains("margin: 10px 8px"), format(MSG_IMG_ATTRIBUTE_S, "style"));

        attr = selenium.getAttribute(LOC_TEXT_AREA_P_IMG + "@width");
        assertEquals(attr, "192", format(MSG_IMG_ATTRIBUTE_S, "width"));

        attr = selenium.getAttribute(LOC_TEXT_AREA_P_IMG + "@height");
        assertEquals(attr, "62", format(MSG_IMG_ATTRIBUTE_S, "height"));

        selenium.selectFrame("relative=top");
    }

//    /**
//     * Tests inserting custom character. It tries to insert euro sign, upper
//     * case omega, and a symbol for heart suite.
//     */
//    @Test
//    public void testInsertCustomCharacter() {
//        selenium.click(LOC_ADV_CUSTOM_CHAR_BUTTON);
//        waitForElement(LOC_ADV_DIALOG_IFRAME);
//        selenium.selectFrame(LOC_ADV_DIALOG_IFRAME);
//        waitForElement(format(LOC_ADV_CUSTOM_CHAR_DLG_M_N, 0, 4));
//        // euro sign
//        selenium.click(format(LOC_ADV_CUSTOM_CHAR_DLG_M_N, 0, 4));
//
//        selenium.click(LOC_ADV_CUSTOM_CHAR_BUTTON);
//        waitForElement(LOC_ADV_DIALOG_IFRAME);
//        selenium.selectFrame(LOC_ADV_DIALOG_IFRAME);
//        waitForElement(format(LOC_ADV_CUSTOM_CHAR_DLG_M_N, 8, 4));
//        // Omega
//        selenium.click(format(LOC_ADV_CUSTOM_CHAR_DLG_M_N, 8, 4));
//
//        selenium.click(LOC_ADV_CUSTOM_CHAR_BUTTON);
//        waitForElement(LOC_ADV_DIALOG_IFRAME);
//        selenium.selectFrame(LOC_ADV_DIALOG_IFRAME);
//        waitForElement(format(LOC_ADV_CUSTOM_CHAR_DLG_M_N, 9, 17));
//        // heart suite
//        selenium.click(format(LOC_ADV_CUSTOM_CHAR_DLG_M_N, 9, 17));
//
//        String text = selenium.getText(LOC_TEXT_AREA_P);
//        assertEquals(text, "€Ω♥", MSG_CONTENT_OF_THE_EDITOR);
//    }

    /**
     * Loads the page containing the calendar component.
     */
    protected void loadPage() {
        openComponent("Editor");

        // wait for iframe to load
        selenium.click(LOC_ADVANCED_BUTTON);
        Wait.until(new Condition() {
            public boolean isTrue() {
                return selenium.isElementPresent(LOC_ADV_ANCHOR_BUTTON);
            }
        });

        scrollIntoView(LOC_EXAMPLE_HEADER, true);
    }
}
