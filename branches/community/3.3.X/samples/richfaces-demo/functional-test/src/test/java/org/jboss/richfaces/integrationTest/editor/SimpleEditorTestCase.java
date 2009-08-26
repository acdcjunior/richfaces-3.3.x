package org.jboss.richfaces.integrationTest.editor;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test case that tests the editor component.
 * <ul>
 * <li><b>TODO</b> implement the test for cleaning up</li>
 * </ul>
 * 
 * @author <a href="mailto:ppitonak@redhat.com">Pavol Pitonak</a>
 * @version $Revision$
 */
public class SimpleEditorTestCase extends AbstractSeleniumRichfacesTestCase {

	// messages
	private final String MSG_COMPONENT_DESCRIPTION = getMess("COMPONENT_DESCRIPTION");
	private final String MSG_STRINGS_SHOULD_BE_BOLD = getMess("STRINGS_SHOULD_BE_BOLD");
	private final String MSG_STRINGS_SHOULD_BE_ITALIC = getMess("STRINGS_SHOULD_BE_ITALIC");
	private final String MSG_STRINGS_SHOULD_BE_UNDERLINED = getMess("STRINGS_SHOULD_BE_UNDERLINED");
	private final String MSG_STRINGS_SHOULD_HAVE_A_LINE_THROUGH = getMess("STRINGS_SHOULD_HAVE_A_LINE_THROUGH");
	private final String MSG_CONTENT_OF_THE_EDITOR = getMess("CONTENT_OF_THE_EDITOR");
	private final String MSG_EDITOR_SHOULD_CONTAIN_OL = getMess("EDITOR_SHOULD_CONTAIN_OL");
	private final String MSG_EDITOR_SHOULD_CONTAIN_UL = getMess("EDITOR_SHOULD_CONTAIN_UL");
	private final String MSG_TWO_LINES_SHOULD_CONTAIN_A_PARAGRAPH = getMess("TWO_LINES_SHOULD_CONTAIN_A_PARAGRAPH");
	private final String MSG_TWO_LINES_IN_THE_EDITOR = getMess("TWO_LINES_IN_THE_EDITOR");
	private final String MSG_LIST_SHOULD_CONTAIN_THREE_ITEMS = getMess("LIST_SHOULD_CONTAIN_THREE_ITEMS");

	// editor
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

		int count = selenium.getXpathCount(LOC_TEXT_AREA_P).intValue();
		assertEquals(count, 2, MSG_TWO_LINES_IN_THE_EDITOR);

		count = selenium.getXpathCount(LOC_TEXT_AREA_P2_B).intValue();
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

		int count = selenium.getXpathCount(LOC_TEXT_AREA_P).intValue();
		assertEquals(count, 2, MSG_TWO_LINES_IN_THE_EDITOR);

		count = selenium.getXpathCount(LOC_TEXT_AREA_P2_I).intValue();
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

		int count = selenium.getXpathCount(LOC_TEXT_AREA_P).intValue();
		assertEquals(count, 2, MSG_TWO_LINES_IN_THE_EDITOR);

		count = selenium.getXpathCount(LOC_TEXT_AREA_P2_U).intValue();
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

		int count = selenium.getXpathCount(LOC_TEXT_AREA_P).intValue();
		assertEquals(count, 2, MSG_TWO_LINES_IN_THE_EDITOR);

		count = selenium.getXpathCount(LOC_TEXT_AREA_P2_STRIKE).intValue();
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

		int count = selenium.getXpathCount(LOC_TEXT_AREA_P).intValue();
		assertEquals(count, 2, MSG_TWO_LINES_SHOULD_CONTAIN_A_PARAGRAPH);

		boolean isPresent = selenium.isElementPresent(LOC_TEXT_AREA_UL);
		assertTrue(isPresent, MSG_EDITOR_SHOULD_CONTAIN_UL);

		count = selenium.getXpathCount(LOC_TEXT_AREA_UL_LI).intValue();
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

		int count = selenium.getXpathCount(LOC_TEXT_AREA_P).intValue();
		assertEquals(count, 2, MSG_TWO_LINES_SHOULD_CONTAIN_A_PARAGRAPH);

		boolean isPresent = selenium.isElementPresent(LOC_TEXT_AREA_OL);
		assertTrue(isPresent, MSG_EDITOR_SHOULD_CONTAIN_OL);

		count = selenium.getXpathCount(LOC_TEXT_AREA_OL_LI).intValue();
		assertEquals(count, 3, MSG_LIST_SHOULD_CONTAIN_THREE_ITEMS);
		
		selenium.selectFrame("relative=top");
	}

	/**
	 * Tests the "View Source". It checks that the source code is not visible,
	 * clicks on the link, and checks the first 2 components of source code,
	 * i.e. that the source code begins with "&lt;ui:composition".
	 */
	@Test
	public void testEditorSource() {
		abstractTestSource(1, 1, "<", "ui:composition");
	}

	/**
	 * Loads the page containing the calendar component.
	 */
	@BeforeMethod
	private void loadPage() {
		super.loadPage("richInputs", 4, MSG_COMPONENT_DESCRIPTION);
		// wait for iframe to load
		waitFor(1000);
		scrollIntoView(LOC_TEXT_AREA, true);
	}
}
