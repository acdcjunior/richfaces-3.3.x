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

package org.jboss.richfaces.integrationTest.tree;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.jboss.test.selenium.waiting.Condition;
import org.jboss.test.selenium.waiting.Wait;
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
    private final String LOC_CHRIS_REA_LINK_PREFORMATTED = getLoc("CHRIS_REA_LINK_PREFORMATTED");
    private final String LOC_CHRIS_REA_NODE_1_LABEL_PREFORMATTED = getLoc("CHRIS_REA_NODE_1_LABEL_PREFORMATTED");
    private final String LOC_CHRIS_REA_NODE_1_IMAGE_FIRST_PREFORMATTED = getLoc("CHRIS_REA_NODE_1_IMAGE_FIRST_PREFORMATTED");
    private final String LOC_CHRIS_REA_NODE_1_IMAGE_SECOND_PREFORMATTED = getLoc("CHRIS_REA_NODE_1_IMAGE_SECOND_PREFORMATTED");
    private final String LOC_CHRIS_REA_NODE_1_LINK_PREFORMATTED = getLoc("CHRIS_REA_NODE_1_LINK_PREFORMATTED");
    private final String LOC_CHRIS_REA_NODE_1_4_LABEL_PREFORMATTED = getLoc("CHRIS_REA_NODE_1_4_LABEL_PREFORMATTED");
    private final String LOC_CHRIS_REA_NODE_1_N_LABEL_PREFORMATTED = getLoc("CHRIS_REA_NODE_1_N_LABEL_PREFORMATTED");
    private final String LOC_NODE_N_PREFORMATTED = getLoc("NODE_N_PREFORMATTED");
    private final String LOC_CHRIS_REA_NODE_1_CHILDREN_PREFORMATTED = getLoc("CHRIS_REA_NODE_1_CHILDREN_PREFORMATTED");
    private final String LOC_CHILDREN_PREFORMATTED = getLoc("CHILDREN_PREFORMATTED");

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
        abstractTestTree(0);
    }

    /**
     * Tests the second example.
     * 
     * @see org.jboss.richfaces.integrationTest.tree.TreeTestCase#abstractTestTree
     *      abstractTestTree
     */
    @Test
    public void testTreeClient() {
        abstractTestTree(1);
    }

    /**
     * Tests the third example.
     * 
     * @see org.jboss.richfaces.integrationTest.tree.TreeTestCase#abstractTestTree
     *      abstractTestTree
     */
    @Test
    public void testTreeServer() {
        abstractTestTree(2);
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
    private void abstractTestTree(final int index) {
        scrollIntoView(format(LOC_HEADER_PREFORMATTED, index), true);

        // click 'Chris Rea'
        waitForElement(format(LOC_CHRIS_REA_LINK_PREFORMATTED, index));
        selenium.click(format(LOC_CHRIS_REA_LINK_PREFORMATTED, index));

        // check Rea's child node
        Wait.interval(2000).failWith("Name of the first child of first node.").until(new Condition() {
            public boolean isTrue() {
                return selenium.getText(format(LOC_CHRIS_REA_NODE_1_LABEL_PREFORMATTED, index)).equals(MSG_NODE_1_1_LABEL);
            }
        });
        
        // check the icon of node
        assertTrue(isDisplayed(format(LOC_CHRIS_REA_NODE_1_IMAGE_FIRST_PREFORMATTED, index)),
                "Node 1.1 should be collapsed -- wrong image.");
        assertFalse(isDisplayed(format(LOC_CHRIS_REA_NODE_1_IMAGE_SECOND_PREFORMATTED, index)),
                "Node 1.1 should be collapsed -- wrong image.");

        // click 'The Road to Hell'
        selenium.click(format(LOC_CHRIS_REA_NODE_1_LINK_PREFORMATTED, index));
        
        // check the number of nodes
        Wait.interval(2000).failWith("Number of children of node 1.1.").until(new Condition() {
            public boolean isTrue() {
                return getJQueryCount(format(LOC_CHRIS_REA_NODE_1_CHILDREN_PREFORMATTED, index)) == MSG_CHILDREN_COUNT_1_1;
            }
        });
        
        // get all nodes
        String label = null;
        for (int i = 0; i < 11; i++) {
            label = selenium.getText(format(LOC_CHRIS_REA_NODE_1_N_LABEL_PREFORMATTED, index, i));
            assertEquals(label, MSG_NODE_1_1_N_LABEL[i], format("Node 1.1.{0} should have name {1}.", i + 1, MSG_NODE_1_1_N_LABEL[0]));
        }

        // check the number of expanded nodes on first level
        int numberOfNodes = getJQueryCount(format(LOC_CHILDREN_PREFORMATTED, index));
        assertEquals(numberOfNodes, MSG_CHILDREN_COUNT_TOP, format("There should be {0} top nodes.", MSG_CHILDREN_COUNT_TOP));
    }

    /**
     * Loads the needed page.
     */
    protected void loadPage() {
        openComponent("Tree");
    }
}
