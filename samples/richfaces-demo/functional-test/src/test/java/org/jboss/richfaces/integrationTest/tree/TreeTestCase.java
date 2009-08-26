/**
 * License Agreement.
 *
 *  JBoss RichFaces
 *
 * Copyright (C) 2009  Red Hat, Inc.
 *
 * This code is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License version 2.1 as published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this code; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301  USA
 */

package org.jboss.richfaces.integrationTest.tree;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test the first tab of tree component page.
 * 
 * @author <a href="mailto:ppitonak@redhat.com">Pavol Pitonak</a>
 * @version $Revision$
 */
public class TreeTestCase extends AbstractSeleniumRichfacesTestCase {

    private final String LOC_HEADER_PREFORMATTED = getLoc("HEADER_PREFORMATTED");
    private final String LOC_NODE_1_LINK_PREFORMATTED = getLoc("NODE_1_LINK_PREFORMATTED");
    private final String LOC_NODE_1_1_LABEL_PREFORMATTED = getLoc("NODE_1_1_LABEL_PREFORMATTED");
    private final String LOC_NODE_1_1_IMAGE_FIRST_PREFORMATTED = getLoc("NODE_1_1_IMAGE_FIRST_PREFORMATTED");
    private final String LOC_NODE_1_1_IMAGE_SECOND_PREFORMATTED = getLoc("NODE_1_1_IMAGE_SECOND_PREFORMATTED");
    private final String LOC_NODE_1_1_LINK_PREFORMATTED = getLoc("NODE_1_1_LINK_PREFORMATTED");
    private final String LOC_NODE_1_1_4_LABEL_PREFORMATTED = getLoc("NODE_1_1_4_LABEL_PREFORMATTED");
    private final String LOC_NODE_1_1_N_LABEL_PREFORMATTED = getLoc("NODE_1_1_N_LABEL_PREFORMATTED");
    private final String LOC_NODE_N_PREFORMATTED = getLoc("NODE_N_PREFORMATTED");
    private final String LOC_CHILDREN_1_1_PREFORMATTED = getLoc("CHILDREN_1_1_PREFORMATTED");
    private final String LOC_CHILDREN_1_PREFORMATTED = getLoc("CHILDREN_1_PREFORMATTED");

    private final String[] MSG_NODE_1_1_N_LABEL = new String[] { getMsg("NODE_1_1_1_LABEL"),
            getMsg("NODE_1_1_2_LABEL"), getMsg("NODE_1_1_3_LABEL"), getMsg("NODE_1_1_4_LABEL"),
            getMsg("NODE_1_1_5_LABEL"), getMsg("NODE_1_1_6_LABEL"), getMsg("NODE_1_1_7_LABEL"),
            getMsg("NODE_1_1_8_LABEL"), getMsg("NODE_1_1_9_LABEL"), getMsg("NODE_1_1_10_LABEL"),
            getMsg("NODE_1_1_11_LABEL"), };
    private final String MSG_NODE_1_1_LABEL = getMsg("NODE_1_1_LABEL");
    private final int MSG_CHILDREN_COUNT_1_1 = Integer.parseInt(getMsg("CHILDREN_COUNT_1_1"));
    private final int MSG_CHILDREN_COUNT_TOP = Integer.parseInt(getMsg("CHILDREN_COUNT_TOP"));

    /**
     * Tests the first example.
     * 
     * @see org.jboss.richfaces.integrationTest.tree.TreeTestCase#abstractTestTree
     *      abstractTestTree
     */
    @Test
    public void testTreeAjax() {
        abstractTestTree(1);
    }

    /**
     * Tests the second example.
     * 
     * @see org.jboss.richfaces.integrationTest.tree.TreeTestCase#abstractTestTree
     *      abstractTestTree
     */
    @Test
    public void testTreeClient() {
        abstractTestTree(2);
    }

    /**
     * Tests the third example.
     * 
     * @see org.jboss.richfaces.integrationTest.tree.TreeTestCase#abstractTestTree
     *      abstractTestTree
     */
    @Test
    public void testTreeServer() {
        abstractTestTree(3);
    }

    /**
     * Tests the "View Source" in the first example -- Ajax switch type. It
     * checks that the source code is not visible, clicks on the link, and
     * checks 8 lines of source code.
     */
    @Test
    public void testAjaxExampleSource() {
        String[] strings = new String[] {
                "<ui:composition xmlns=\"http://www.w3.org/1999/xhtml\"",
                "<h:form>",
                "<rich:tree style=\"width:300px\" value=\"#{library.data}\" var=\"item\" nodeFace=\"#{item.type}\">",
                "<rich:treeNode type=\"artist\" iconLeaf=\"/images/tree/singer.gif\" icon=\"/images/tree/singer.gif\">",
                "<h:outputText value=\"#{item.name}\" />", "</rich:treeNode>",
                "<rich:treeNode type=\"album\" iconLeaf=\"/images/tree/disc.gif\" icon=\"/images/tree/disc.gif\">",
                "<rich:treeNode type=\"song\" iconLeaf=\"/images/tree/song.gif\" icon=\"/images/tree/song.gif\">", };

        abstractTestSource(1, "View Source", strings);
    }

    /**
     * Tests the "View Source" in the second example -- client switch type. It
     * checks that the source code is not visible, clicks on the link, and
     * checks 8 lines of source code.
     */
    @Test
    public void testClientExampleSource() {
        String[] strings = new String[] {
                "<ui:composition xmlns=\"http://www.w3.org/1999/xhtml\"",
                "<h:form>",
                "<rich:tree switchType=\"client\" style=\"width:300px\" value=\"#{library.data}\" var=\"item\" nodeFace=\"#{item.type}\">",
                "<rich:treeNode type=\"artist\" iconLeaf=\"/images/tree/singer.gif\" icon=\"/images/tree/singer.gif\">",
                "<h:outputText value=\"#{item.name}\" />", "</rich:treeNode>",
                "<rich:treeNode type=\"album\" iconLeaf=\"/images/tree/disc.gif\" icon=\"/images/tree/disc.gif\">",
                "<rich:treeNode type=\"song\" iconLeaf=\"/images/tree/song.gif\" icon=\"/images/tree/song.gif\">", };

        abstractTestSource(2, "View Source", strings);
    }

    /**
     * Tests the "View Source" in the third example -- server switch type. It
     * checks that the source code is not visible, clicks on the link, and
     * checks 8 lines of source code.
     */
    @Test
    public void testServerExampleSource() {
        String[] strings = new String[] {
                "<ui:composition xmlns=\"http://www.w3.org/1999/xhtml\"",
                "<h:form>",
                "<rich:tree switchType=\"server\" style=\"width:300px\" value=\"#{library.data}\" var=\"item\" nodeFace=\"#{item.type}\">",
                "<rich:treeNode type=\"artist\" iconLeaf=\"/images/tree/singer.gif\" icon=\"/images/tree/singer.gif\">",
                "<h:outputText value=\"#{item.name}\" />", "</rich:treeNode>",
                "<rich:treeNode type=\"album\" iconLeaf=\"/images/tree/disc.gif\" icon=\"/images/tree/disc.gif\">",
                "<rich:treeNode type=\"song\" iconLeaf=\"/images/tree/song.gif\" icon=\"/images/tree/song.gif\">", };

        abstractTestSource(3, "View Source", strings);
    }

    /**
     * Tests selected tree. First it tries to expand "Chris Rea" node, checks
     * its only child node, verifies that child's icon is displayed and expands
     * it. Then it checks the number of nodes in "Chris Rea/The Road To Hell"
     * node and verifies these nodes. In the end it checks that there are 4
     * top-level nodes and that only the first is expanded.
     * 
     * @param index
     *            which tree is being tested
     */
    private void abstractTestTree(int index) {
        scrollIntoView(format(LOC_HEADER_PREFORMATTED, index), true);

        // click 'Chris Rea'
        waitForElement(format(LOC_NODE_1_LINK_PREFORMATTED, index));
        selenium.click(format(LOC_NODE_1_LINK_PREFORMATTED, index));

        // check Rea's child node
        waitForElement(format(LOC_NODE_1_1_LABEL_PREFORMATTED, index));
        String text = selenium.getText(format(LOC_NODE_1_1_LABEL_PREFORMATTED, index));
        assertEquals(text, MSG_NODE_1_1_LABEL, "Name of the first child of first node.");

        // check the icon of node
        assertTrue(isDisplayed(format(LOC_NODE_1_1_IMAGE_FIRST_PREFORMATTED, index)),
                "Node 1.1 should be collapsed -- wrong image.");
        assertFalse(isDisplayed(format(LOC_NODE_1_1_IMAGE_SECOND_PREFORMATTED, index)),
                "Node 1.1 should be collapsed -- wrong image.");

        // click 'The Road to Hell'
        selenium.click(format(LOC_NODE_1_1_LINK_PREFORMATTED, index));

        // check the number of nodes
        waitForElement(format(LOC_NODE_1_1_4_LABEL_PREFORMATTED, index));
        int numberOfNodes = selenium.getXpathCount(format(LOC_CHILDREN_1_1_PREFORMATTED, index)).intValue();
        assertEquals(numberOfNodes, MSG_CHILDREN_COUNT_1_1, "Number of children of node 1.1.");

        // get all nodes
        String label = null;
        for (int i = 0; i < numberOfNodes; i++) {
            label = selenium.getText(format(LOC_NODE_1_1_N_LABEL_PREFORMATTED, index, i + 1));
            assertEquals(label, MSG_NODE_1_1_N_LABEL[i], format("Node 1.1.{0} should have name {1}.", i + 1,
                    MSG_NODE_1_1_N_LABEL[0]));
        }

        // check the number of expanded nodes on first level
        numberOfNodes = selenium.getXpathCount(format(LOC_CHILDREN_1_PREFORMATTED, index)).intValue();
        assertEquals(numberOfNodes, MSG_CHILDREN_COUNT_TOP, format("There should be {0} top nodes.",
                MSG_CHILDREN_COUNT_TOP));

        // check that only the first node is expanded
        for (int i = 2; i < 5; i++) {
            assertFalse(isDisplayed(format(LOC_NODE_N_PREFORMATTED, index, i)), format(
                    "Node nr. {0} should be collapsed.", i));
        }
    }

    /**
     * Loads the needed page.
     */
    @BeforeMethod
    private void loadPage() {
        openComponent("Tree");
    }
}
