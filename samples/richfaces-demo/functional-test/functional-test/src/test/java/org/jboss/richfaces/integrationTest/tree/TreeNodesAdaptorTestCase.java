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

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.jboss.test.selenium.waiting.Condition;
import org.jboss.test.selenium.waiting.Wait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test for tree nodes adaptors.
 * 
 * @author <a href="mailto:ppitonak@redhat.com">Pavol Pitonak</a>
 * @version $Revision$
 */
public class TreeNodesAdaptorTestCase extends AbstractSeleniumRichfacesTestCase {

    private final String LOC_EXAMPLE_HEADER = getLoc("EXAMPLE_HEADER");
    private final String LOC_TABLE_SUBNODES = getLoc("TABLE_SUBNODES");
    private final String LOC_DEMO_NODE = getLoc("DEMO_NODE");
    private final String LOC_DIV_SUBNODES = getLoc("DIV_SUBNODES");
    private final String LOC_DIV_SUBNODES_PREFORMATTED = getLoc("DIV_SUBNODES_PREFORMATTED");

    private final int MSG_CHILDREN_COUNT = Integer.parseInt(getMsg("CHILDREN_COUNT"));

    /**
     * Tests tree on the page. First it clicks on "org/richfaces/demo" to expand
     * it. Then it verifies that the node was expanded and that its siblings are
     * collapsed. After that it clicks on "org/richfaces/demo" again and checks
     * that tha node and all its siblings are collapsed.
     */
    @Test
    public void testTreeNodesAdaptor() {
        scrollIntoView(LOC_EXAMPLE_HEADER, true);
        
        int count = getJQueryCount(LOC_TABLE_SUBNODES);
        assertEquals(count, MSG_CHILDREN_COUNT, "Nodes in org/richfaces.");

        // click "demo"
        selenium.click(LOC_DEMO_NODE);

        // wait until the node is expanded
        Wait.failWith("The node org/richfaces/demo should be expanded.").until(new Condition() {
            public boolean isTrue() {
                return isDisplayed(format(LOC_DIV_SUBNODES_PREFORMATTED, 0));
            }
        });

        // check that all siblings of node "demo" are collapsed
        for (int i = 1; i < 4; i++) {
            assertFalse(isDisplayed(format(LOC_DIV_SUBNODES_PREFORMATTED, i)), format("Node nr. {0} in /org/richfaces should be collapsed.", i+1));
        }

        waitFor(6000);
        // click "demo" (to collapse node)
        selenium.click(LOC_DEMO_NODE);
        waitFor(6000);
        
        // wait until the node is collapsed
        Wait.failWith("The node org/richfaces/demo should be collapsed.").until(new Condition() {
            public boolean isTrue() {
                return !isDisplayed(format(LOC_DIV_SUBNODES_PREFORMATTED, 0));
            }
        });

        // check that all siblings of node "demo" are collapsed
        for (int i = 1; i < 4; i++) {
            assertFalse(isDisplayed(format(LOC_DIV_SUBNODES_PREFORMATTED, i)), format(
                    "Node nr. {0} in /org/richfaces should be collapsed.", i+1));
        }
    }

//    /**
//     * Tests the "View Source". It checks that the source code is not visible,
//     * clicks on the link, and checks 7 lines of source code.
//     */
//    @Test
//    public void testPageSource() {
//        String[] strings = new String[] {
//                "<ui:composition xmlns=\"http://www.w3.org/1999/xhtml\"",
//                "<h:form> ",
//                "<rich:tree style=\"width:300px\" switchType=\"ajax\" stateAdvisor=\"#{treeDemoStateAdvisor}\">",
//                "<rich:recursiveTreeNodesAdaptor roots=\"#{fileSystemBean.sourceRoots}\" var=\"item\" nodes=\"#{item.nodes}\" />",
//                "</rich:tree>", "</h:form>", "</ui:composition>", };
//
//        abstractTestSource(1, "View Source", strings);
//    }
//
//    /**
//     * Tests the "View FileSystemBean.java Source". It checks that the source
//     * code is not visible, clicks on the link, and checks 8 lines of source
//     * code.
//     */
//    @Test
//    public void testFileSystemBeanSource() {
//        String[] strings = new String[] { "package org.richfaces.treemodeladaptor;", "public class FileSystemBean {",
//                "private static String SRC_PATH = \"/WEB-INF/src\";", "private FileSystemNode[] srcRoots;",
//                "public synchronized FileSystemNode[] getSourceRoots() {", "if (srcRoots == null) {",
//                "srcRoots = new FileSystemNode(SRC_PATH).getNodes();", "return srcRoots;", };
//
//        abstractTestSource(1, "View FileSystemBean.java Source", strings);
//    }
//
//    /**
//     * Tests the "View FileSystemNode.java Source". It checks that the source
//     * code is not visible, clicks on the link, and checks 8 lines of source
//     * code.
//     */
//    @Test
//    public void testFileSystemNodeSource() {
//        String[] strings = new String[] { "package org.richfaces.treemodeladaptor;", "public class FileSystemNode {",
//                "public FileSystemNode(String path) {", "public synchronized FileSystemNode[] getNodes() {",
//                "FacesContext facesContext = FacesContext.getCurrentInstance();",
//                "ExternalContext externalContext = facesContext.getExternalContext();",
//                "Set resourcePaths = externalContext.getResourcePaths(this.path);",
//                "Object[] nodes = (Object[]) resourcePaths.toArray();", };
//
//        abstractTestSource(1, "View FileSystemNode.java Source", strings);
//    }
//
//    /**
//     * Tests the "View PostbackPhaseListener.java Source". It checks that the
//     * source code is not visible, clicks on the link, and checks 8 lines of
//     * source code.
//     */
//    @Test
//    public void testPostbackPhaseListenerSource() {
//        String[] strings = new String[] { "package org.richfaces.treemodeladaptor;",
//                "public class PostbackPhaseListener implements PhaseListener {",
//                "public static final String POSTBACK_ATTRIBUTE_NAME = PostbackPhaseListener.class.getName();",
//                "public void afterPhase(PhaseEvent event) {", "public void beforePhase(PhaseEvent event) {",
//                "FacesContext facesContext = event.getFacesContext();", "public PhaseId getPhaseId() {",
//                "public static boolean isPostback() {", };
//
//        abstractTestSource(1, "View PostbackPhaseListener.java Source", strings);
//    }
//
//    /**
//     * Tests the "View TreeDemoStateAdvisor.java Source". It checks that the
//     * source code is not visible, clicks on the link, and checks 8 lines of
//     * source code.
//     */
//    @Test
//    public void testTreeDemoStateAdvisorSource() {
//        String[] strings = new String[] { "package org.richfaces.treemodeladaptor;",
//                "public class TreeDemoStateAdvisor implements TreeStateAdvisor {",
//                "public Boolean adviseNodeOpened(UITree tree) {", "if (!PostbackPhaseListener.isPostback()) {",
//                "Object key = tree.getRowKey();", "TreeRowKey treeRowKey = (TreeRowKey) key;",
//                "if (treeRowKey == null || treeRowKey.depth() <= 2) {", "return Boolean.TRUE;", };
//
//        abstractTestSource(1, "View TreeDemoStateAdvisor.java Source", strings);
//    }

    /**
     * Loads the needed page.
     */
    protected void loadPage() {
        openComponent("Tree Adaptor");
    }
}
