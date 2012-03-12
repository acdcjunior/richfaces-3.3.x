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
package org.jboss.richfaces.integrationTest.outputPanel;

import static org.testng.Assert.*;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.jboss.test.selenium.dom.Event;
import org.jboss.test.selenium.waiting.Condition;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public class OutputPanelTestCase extends AbstractSeleniumRichfacesTestCase {

    private String LOC_FIELDSET_HEADER = getLoc("FIELDSET_HEADER");
    private String LOC_INPUT_WRONG = getLoc("INPUT_WRONG");
    private String LOC_INPUT_CORRECT = getLoc("INPUT_CORRECT");
    private String LOC_OUTPUT_MESSAGE = getLoc("OUTPUT_MESSAGE");
    private String LOC_OUTPUT_ERROR = getLoc("OUTPUT_ERROR");

    private String MSG_INPUT_CORRECT = getMsg("INPUT_CORRECT");
    private String MSG_INPUT_WRONG = getMsg("INPUT_WRONG");
    
     /**
     * Add correct text into wrong input, checks that no text and no error
     * message appear.
     */
     @Test
     public void testCorrectTextIntoWrongInput() {
     enterTextAndCheckOutputAndErrorMessage(LOC_INPUT_WRONG,
     MSG_INPUT_CORRECT, false, false);
     }
    
     /**
     * Enter wrong text into wrong input, checks that no text and no error
     * message appear.
     */
     @Test
     public void testWrongTextIntoWrongInput() {
     enterTextAndCheckOutputAndErrorMessage(LOC_INPUT_WRONG, MSG_INPUT_WRONG,
     false, false);
     }
    
     /**
     * Enter correct text into correct input, checks that text but no error
     * message appear.
     */
     @Test
     public void testCorrectTextIntoSuccessInput() {
     enterTextAndCheckOutputAndErrorMessage(LOC_INPUT_CORRECT,
     MSG_INPUT_CORRECT, true, false);
     }

    /**
     * Enter wrong text into correct input, checks that no text but only error
     * message appear.
     */
    @Test
    public void testWrongTextIntoSuccessInput() {
        enterTextAndCheckOutputAndErrorMessage(LOC_INPUT_CORRECT, MSG_INPUT_WRONG, false, true);
    }

    private void enterTextAndCheckOutputAndErrorMessage(String locInput, final String msgText,
            final boolean textShouldAppear, final boolean errorMessageShouldAppear) {
        selenium.type(locInput, msgText);
        selenium.fireEvent(locInput, Event.KEYUP);

        waitModelUpdate.dontFail().until(new Condition() {
            public boolean isTrue() {
                if (errorMessageShouldAppear || textShouldAppear) {
                    return getJQueryCount(format(LOC_OUTPUT_MESSAGE, msgText)) > 0
                            || getJQueryCount(LOC_OUTPUT_ERROR) > 0;
                }
                return false;
            }
        });

        assertEquals(getJQueryCount(format(LOC_OUTPUT_MESSAGE, msgText)), textShouldAppear ? 1 : 0,
                textShouldAppear ? "Text output should appear" : "No text output should appear");
        assertEquals(getJQueryCount(LOC_OUTPUT_ERROR), errorMessageShouldAppear ? 1 : 0,
                errorMessageShouldAppear ? "Error message should appear" : "No error message should appear");
    }

    protected void loadPage() {
        openComponent("Output Panel");

        scrollIntoView(LOC_FIELDSET_HEADER, true);
    }
}
