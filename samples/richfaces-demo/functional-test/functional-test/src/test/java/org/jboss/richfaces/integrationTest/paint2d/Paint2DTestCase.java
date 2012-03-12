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

package org.jboss.richfaces.integrationTest.paint2d;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.io.IOException;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.jboss.test.selenium.utils.URLUtils;
import org.jboss.test.selenium.waiting.Condition;
import org.jboss.test.selenium.waiting.Wait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test case that tests Paint2D.
 * 
 * @author <a href="mailto:ppitonak@redhat.com">Pavol Pitonak</a>
 * @version $Revision$
 */
public class Paint2DTestCase extends AbstractSeleniumRichfacesTestCase {

    // messages
    private final String MSG_INITIAL_STATE_INPUT_TEXT = getMsg("INITIAL_STATE_INPUT_TEXT");
    private final String MSG_INITIAL_STATE_INPUT_COLOR = getMsg("INITIAL_STATE_INPUT_COLOR");
    private final int MSG_INITIAL_STATE_SHADOW_SLIDER_HANDLE = Integer
            .parseInt(getMsg("INITIAL_STATE_SHADOW_SLIDER_HANDLE"));
    private final String MSG_INITIAL_STATE_HASH = getMsg("INITIAL_STATE_HASH");

    private final String MSG_CHANGE_TEXT_HASH_1 = getMsg("CHANGE_TEXT_HASH_1");
    private final String MSG_CHANGE_TEXT_HASH_2 = getMsg("CHANGE_TEXT_HASH_2");

    private final String MSG_CHANGE_COLOR_HASH_1 = getMsg("CHANGE_COLOR_HASH_1");
    private final String MSG_CHANGE_COLOR_HASH_2 = getMsg("CHANGE_COLOR_HASH_2");

    private final int MSG_CHANGE_SHADOW_SLIDER_HANDLE_1 = Integer.parseInt(getMsg("CHANGE_SHADOW_SLIDER_HANDLE_1"));
    private final int MSG_CHANGE_SHADOW_SLIDER_HANDLE_2 = Integer.parseInt(getMsg("CHANGE_SHADOW_SLIDER_HANDLE_2"));
    private final int MSG_CHANGE_SHADOW_SLIDER_HANDLE_3 = Integer.parseInt(getMsg("CHANGE_SHADOW_SLIDER_HANDLE_3"));
    private final String MSG_CHANGE_SHADOW_HASH_1 = getMsg("CHANGE_SHADOW_HASH_1");
    private final String MSG_CHANGE_SHADOW_HASH_2 = getMsg("CHANGE_SHADOW_HASH_2");
    private final String MSG_CHANGE_SHADOW_HASH_3 = getMsg("CHANGE_SHADOW_HASH_3");

    private final String MSG_CHANGE_TEXT_AND_COLOR_HASH = getMsg("CHANGE_TEXT_AND_COLOR_HASH");

    private final String MSG_CHANGE_TEXT_AND_SHADOW_HASH = getMsg("CHANGE_TEXT_AND_SHADOW_HASH");
    private final int MSG_CHANGE_TEXT_AND_SHADOW_SLIDER_HANDLE = Integer
            .parseInt(getMsg("CHANGE_TEXT_AND_SHADOW_SLIDER_HANDLE"));

    private final int MSG_CHANGE_COLOR_AND_SHADOW_SLIDER_HANDLE = Integer
            .parseInt(getMsg("CHANGE_COLOR_AND_SHADOW_SLIDER_HANDLE"));
    private final String MSG_CHANGE_COLOR_AND_SHADOW_HASH = getMsg("CHANGE_COLOR_AND_SHADOW_HASH");

    private final int MSG_CHANGE_ALL_SLIDER_HANDLE = Integer.parseInt(getMsg("CHANGE_ALL_SLIDER_HANDLE"));
    private final String MSG_CHANGE_ALL_HASH = getMsg("CHANGE_ALL_HASH");

    // locators
    private final String LOC_EXAMPLE_HEADER = getLoc("EXAMPLE_HEADER");
    private final String LOC_INPUT_TEXT = getLoc("INPUT_TEXT");
    private final String LOC_INPUT_COLOR = getLoc("INPUT_COLOR");
    private final String LOC_BUTTON_APPLY_COLOR = getLoc("BUTTON_APPLY_COLOR");
    private final String LOC_SHADOW_SLIDER = getLoc("SHADOW_SLIDER");
    private final String LOC_SHADOW_SLIDER_HANDLE = getLoc("SHADOW_SLIDER_HANDLE");
    private final String LOC_IMAGE = getLoc("IMAGE");

    /**
     * Tests all component on the page as they appear after the page is loaded.
     * It checks the value in the text input, color, position of the slider, and
     * hash code of the displayed image.
     */
    @Test
    public void testInitialState() {
        String text = selenium.getValue(LOC_INPUT_TEXT);
        assertEquals(text, MSG_INITIAL_STATE_INPUT_TEXT, "Text in the image.");

        text = selenium.getValue(LOC_INPUT_COLOR);
        assertEquals(text, MSG_INITIAL_STATE_INPUT_COLOR, "Color of the image.");

        int position = Integer.parseInt(getStyle(LOC_SHADOW_SLIDER_HANDLE, "left").replace("px", ""));
        assertEquals(position, MSG_INITIAL_STATE_SHADOW_SLIDER_HANDLE, "Size of the shadow.");

        assertEquals(getImageHash(), MSG_INITIAL_STATE_HASH, "Hash of the image.");
    }

    /**
     * Tests changing text that should be in the image.
     */
    @Test
    public void testChangeText() {
        selenium.type(LOC_INPUT_TEXT, "XoXoXoXoX");
        selenium.typeKeys(LOC_INPUT_TEXT, " "); // why is this necessary?

        Wait.failWith("Hash of the image.").until(new Condition() {
            public boolean isTrue() {
                return getImageHash().equals(MSG_CHANGE_TEXT_HASH_1);
            }
        });

        selenium.type(LOC_INPUT_TEXT, "Red Hat");
        selenium.typeKeys(LOC_INPUT_TEXT, " "); // why is this necessary?
        
        Wait.failWith("Hash of the image.").until(new Condition() {
            public boolean isTrue() {
                return getImageHash().equals(MSG_CHANGE_TEXT_HASH_2);
            }
        });

    }

    /**
     * Tests changing color of the image.
     */
    @Test
    public void testChangeColor() {
        selenium.type(LOC_INPUT_COLOR, "fbff00");
        selenium.click(LOC_BUTTON_APPLY_COLOR);
        
        Wait.failWith("Hash of the image.").until(new Condition() {
            public boolean isTrue() {
                return getImageHash().equals(MSG_CHANGE_COLOR_HASH_1);
            }
        });

        selenium.type(LOC_INPUT_COLOR, "00ffbb");
        selenium.click(LOC_BUTTON_APPLY_COLOR);
        
        Wait.failWith("Hash of the image.").until(new Condition() {
            public boolean isTrue() {
                return getImageHash().equals(MSG_CHANGE_COLOR_HASH_2);
            }
        });
    }

    /**
     * Tests 3 positions of the slider. Verifies that the image changed.
     */
    @Test
    public void testChangeShadow() {
        selenium.mouseDownAt(LOC_SHADOW_SLIDER, "0,0");
        selenium.mouseUp(LOC_SHADOW_SLIDER);
        int position = Integer.parseInt(getStyle(LOC_SHADOW_SLIDER_HANDLE, "left").replace("px", ""));
        assertEquals(position, MSG_CHANGE_SHADOW_SLIDER_HANDLE_1, "Position of the slider's handle.");

        Wait.failWith("Hash of the image.").until(new Condition() {
            public boolean isTrue() {
                return getImageHash().equals(MSG_CHANGE_SHADOW_HASH_1);
            }
        });

        selenium.mouseDownAt(LOC_SHADOW_SLIDER, "96,0");
        selenium.mouseUp(LOC_SHADOW_SLIDER);
        position = Integer.parseInt(getStyle(LOC_SHADOW_SLIDER_HANDLE, "left").replace("px", ""));
        assertEquals(position, MSG_CHANGE_SHADOW_SLIDER_HANDLE_2, "Position of the slider's handle.");

        Wait.failWith("Hash of the image.").until(new Condition() {
            public boolean isTrue() {
                return getImageHash().equals(MSG_CHANGE_SHADOW_HASH_2);
            }
        });
        
        selenium.mouseDownAt(LOC_SHADOW_SLIDER, "191,0");
        selenium.mouseUp(LOC_SHADOW_SLIDER);
        position = Integer.parseInt(getStyle(LOC_SHADOW_SLIDER_HANDLE, "left").replace("px", ""));
        assertEquals(position, MSG_CHANGE_SHADOW_SLIDER_HANDLE_3, "Position of the slider's handle.");

        Wait.failWith("Hash of the image.").until(new Condition() {
            public boolean isTrue() {
                return getImageHash().equals(MSG_CHANGE_SHADOW_HASH_3);
            }
        });
    }

    /**
     * Tests changing text and color at the same time.
     */
    @Test
    public void testChangeTextAndColor() {
        selenium.type(LOC_INPUT_TEXT, "RichFaces");

        selenium.type(LOC_INPUT_COLOR, "894bd6");
        selenium.click(LOC_BUTTON_APPLY_COLOR);

        Wait.failWith("Hash of the image.").until(new Condition() {
            public boolean isTrue() {
                return getImageHash().equals(MSG_CHANGE_TEXT_AND_COLOR_HASH);
            }
        });
    }

    /**
     * Tests changing text and shadow at the same time.
     */
    @Test
    public void testChangeTextAndShadow() {
        selenium.type(LOC_INPUT_TEXT, "RichFaces");

        selenium.mouseDownAt(LOC_SHADOW_SLIDER, "159,0");
        selenium.mouseUp(LOC_SHADOW_SLIDER);
        int position = Integer.parseInt(getStyle(LOC_SHADOW_SLIDER_HANDLE, "left").replace("px", ""));
        assertEquals(position, MSG_CHANGE_TEXT_AND_SHADOW_SLIDER_HANDLE, "Position of the slider's handle.");

        Wait.failWith("Hash of the image.").until(new Condition() {
            public boolean isTrue() {
                return getImageHash().equals(MSG_CHANGE_TEXT_AND_SHADOW_HASH);
            }
        });
    }

    /**
     * Tests changing color and shadow at the same time.
     */
    @Test
    public void testChangeColorAndShadow() {
        selenium.type(LOC_INPUT_COLOR, "6bab57");
        selenium.click(LOC_BUTTON_APPLY_COLOR);

        selenium.mouseDownAt(LOC_SHADOW_SLIDER, "96,0");
        selenium.mouseUp(LOC_SHADOW_SLIDER);
        int position = Integer.parseInt(getStyle(LOC_SHADOW_SLIDER_HANDLE, "left").replace("px", ""));
        assertEquals(position, MSG_CHANGE_COLOR_AND_SHADOW_SLIDER_HANDLE, "Position of the slider's handle.");

        Wait.failWith("Hash of the image.").until(new Condition() {
            public boolean isTrue() {
                return getImageHash().equals(MSG_CHANGE_COLOR_AND_SHADOW_HASH);
            }
        });
    }

    /**
     * Tests changing text, color and size of shadow.
     */
    @Test
    public void testChangeAll() {
        selenium.type(LOC_INPUT_TEXT, "RichFaces");

        selenium.type(LOC_INPUT_COLOR, "e8a54c");
        selenium.click(LOC_BUTTON_APPLY_COLOR);

        selenium.mouseDownAt(LOC_SHADOW_SLIDER, "127,0");
        selenium.mouseUp(LOC_SHADOW_SLIDER);
        int position = Integer.parseInt(getStyle(LOC_SHADOW_SLIDER_HANDLE, "left").replace("px", ""));
        assertEquals(position, MSG_CHANGE_ALL_SLIDER_HANDLE, "Position of the slider's handle.");

        Wait.failWith("Hash of the image.").until(new Condition() {
            public boolean isTrue() {
                return getImageHash().equals(MSG_CHANGE_ALL_HASH);
            }
        });
    }

    /**
     * Tests the "View Source". It checks that the source code is not visible,
     * clicks on the link, and checks 5 lines of source code.
     */
    @Test
    public void testPageSource() {
        String[] strings = new String[] { "<ui:composition xmlns=\"http://www.w3.org/1999/xhtml\"",
                "<h:panelGrid columns=\"3\" width=\"100%\">", "<h:inputText value=\"#{paintData.text}\">",
                "<a4j:support event=\"onchange\" reRender=\":painter\" />",
                "<rich:inputNumberSlider showInput=\"false\"", };

        abstractTestSource(1, "View Source", strings);
    }

    /**
     * Tests the "View PaintBean.java Source". It checks that the source code is
     * not visible, clicks on the link, and checks 5 lines of source code.
     */
    @Test
    public void testPaintBeanSource() {
        String[] strings = new String[] { "package org.richfaces.demo.paint2d;", "import java.awt.Graphics2D;",
                "public void paint(Graphics2D g2d, Object obj) {", "int testLenght = data.text.length();",
                "g2d.setPaint(new Color(color.getRed(),color.getGreen(), color.getBlue(), 30));", };

        abstractTestSource(1, "View PaintBean.java Source", strings);
    }

    /**
     * Tests the "View PaintData.java Source". It checks that the source code is
     * not visible, clicks on the link, and checks 5 lines of source code.
     */
    @Test
    public void testPaintDataSource() {
        String[] strings = new String[] { "package org.richfaces.demo.paint2d;",
                "public class PaintData implements Serializable{", "public int getColor() {", "return color;",
                "public void setText(String text) {", };

        abstractTestSource(1, "View PaintData.java Source", strings);
    }

   /**
    * Returns hash code of the image.
    */
   private String getImageHash() {
       // create URL of the image
       int index = selenium.getLocation().indexOf('/', 7);
       String tmp = selenium.getLocation().substring(0, index);
       tmp += selenium.getAttribute(LOC_IMAGE + "@src");

       try {
           return URLUtils.resourceMd5Digest(tmp);
       } catch (IOException e) {
           e.printStackTrace();
           fail(e.getMessage());
       }
       
       return null;
   }
    
    /**
     * Loads the page containing the component.
     */
    protected void loadPage() {
        openComponent("Paint2D");
        scrollIntoView(LOC_EXAMPLE_HEADER, true);
    }
}
