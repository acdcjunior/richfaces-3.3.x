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

package org.jboss.richfaces.integrationTest.stateManager;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotSame;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.jboss.test.selenium.waiting.Condition;
import org.jboss.test.selenium.waiting.Wait;
import org.testng.SkipException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * A test case for State Manager API. It verifies https://jira.jboss.org/jira/browse/RF-8204. To run this test, demo has
 * to run on top of JSF 2 and server-side state serialization has to be switched on. If the test suite is launched with
 * JSF 1.2, all tests in this class will be skipped.
 * 
 * @author <a href="mailto:ppitonak@redhat.com">Pavol Pitonak</a>
 * @version $Revision$
 */
public class StateManagerTestCase extends AbstractSeleniumRichfacesTestCase {

    private final String LOC_HIDDEN_INPUT_PREFORMATTED = getLoc("HIDDEN_INPUT_PREFORMATTED");
    private final String LOC_LINK = getLoc("LINK");

    /**
     * Tests behavior of the application after Ajax request. It clicks on the link in the form on the page. There are
     * three forms on the page, each of them has to contain the same value which cannot change after Ajax request.
     */
    @Test(dependsOnMethods = { "testJsfVersion" })
    public void testAjaxRequest() {
        String[] oldValues = new String[3];
        getValuesAndVerify(oldValues);

        selenium.click(LOC_LINK);
        Wait.failWith("The form did not change").until(new Condition() {
            public boolean isTrue() {
                return selenium.getText(LOC_LINK).equals("(To login)");
            }
        });

        String[] newValues = new String[3];
        getValuesAndVerify(newValues);

        assertEquals(oldValues[0], newValues[0],
            "The value attribute in all forms should not change after an Ajax request.");
    }

    /**
     * Tests behavior of the application after non-Ajax request. It reloads the page which will cause a non-Ajax
     * request. There are three forms on the page, each of them has to contain the same value which has to change after
     * non-Ajax request.
     */
    @Test(dependsOnMethods = { "testJsfVersion" })
    public void testNonAjaxRequest() {
        String[] oldValues = new String[3];
        getValuesAndVerify(oldValues);

        selenium.refresh();
        waitFor(4000);

        String[] newValues = new String[3];
        getValuesAndVerify(newValues);

        assertNotSame(oldValues[0], newValues[0],
            "The value attribute in all forms should change after a non-Ajax request.");
    }

    /**
     * Gets the "value" attribute in three form on the page and verifies that all are the same.
     * 
     * @param values
     */
    private void getValuesAndVerify(String[] values) {
        for (int i = 0; i < 3; i++) {
            values[i] = selenium.getAttribute(format(LOC_HIDDEN_INPUT_PREFORMATTED, i));
        }

        assertEquals(values[0], values[1],
            "The attribute value in the first form should be the same as one in the second form.");
        assertEquals(values[1], values[2],
            "The attribute value in the second form should be the same as one in the third form.");
    }

    /**
     * Tests that the test suite is running with JSF2. If test suite is launched with JSF 1.x, all tests in this class
     * are skipped.
     * 
     * @param jsfMajorVersion
     *            major version of JSF, 1 or 2
     */
    @Test
    @Parameters( { "jsf.majorVersion" })
    public void testJsfVersion(String jsfMajorVersion) {
        if (!"2".equals(jsfMajorVersion)) {
            throw new SkipException("Test should be launched only if running on top of JSF 2.");
        }
    }

    /**
     * Loads the needed page.
     */
    protected void loadPage() {
        openComponent("State Manager API");
    }
}
