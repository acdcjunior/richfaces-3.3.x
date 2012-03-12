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
package org.jboss.richfaces.integrationTest.extendedDataTable;

import static org.testng.Assert.*;

import java.util.HashMap;
import java.util.Vector;

import org.jboss.test.selenium.actions.Drag;
import org.jboss.test.selenium.dom.Event;
import org.jboss.test.selenium.utils.array.ArrayTransform;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public class DraggingTestCase extends AbstractExtendedDataTableTestCase {

	private final String LOC_DIV_DROP_ZONE_STATE = format(getLoc("DIV_DROP_ZONE_LEFT_RELATIVE"), LOC_TH_STATE);
	private final String LOC_DIV_DROP_ZONE_CAPITAL = format(getLoc("DIV_DROP_ZONE_RIGHT_RELATIVE"), LOC_TH_CAPITAL);
	private final String LOC_IMAGE_SRC_FLAG_PREFORMATTED = getLoc("IMAGE_SRC_FLAG_PREFORMATTED");
	private final String[] LOC_TH_DRAGGING_TESTS = new String[] { LOC_TH_STATE, LOC_TH_CAPITAL, LOC_TH_FLAG,
			LOC_TH_TIME_ZONE };

	private final String MSG_INPUT_LETTER_FILTER = getMsg("INPUT_LETTER_FILTER");

	/**
	 * Apply filtering and sorting to two different columns and next drag two
	 * columns. Checks that association of all table data didn't change and that
	 * columns are in right order
	 */
	@Test
	public void testDragging() {
		assertEquals(getColumnIndex(LOC_TH_FLAG), 1);
		assertEquals(getColumnIndex(LOC_TH_STATE), 2);
		assertEquals(getColumnIndex(LOC_TH_CAPITAL), 3);
		assertEquals(getColumnIndex(LOC_TH_TIME_ZONE), 4);

		// set filter
		String inputFilterState = getInputFilterState();
		selenium.type(inputFilterState, MSG_INPUT_LETTER_FILTER);
		selenium.fireEvent(inputFilterState, Event.KEYUP);
		waitForSplash();

		int associationHash = getAssociationMap().hashCode();
		
		// sort by capital
		selenium.click(LOC_TH_CAPITAL);
		waitForSplash();
		
		assertEquals(associationHash, getAssociationMap().hashCode());
		
		// change column order - drag timezone column to state
		new Drag(selenium, LOC_SPAN_TIME_ZONE, LOC_DIV_DROP_ZONE_STATE).drop();
		waitForSplash();
		
		assertEquals(associationHash, getAssociationMap().hashCode());
		assertEquals(getColumnIndex(LOC_TH_FLAG), 1);
		assertEquals(getColumnIndex(LOC_TH_TIME_ZONE), 2);
		assertEquals(getColumnIndex(LOC_TH_STATE), 3);
		assertEquals(getColumnIndex(LOC_TH_CAPITAL), 4);
		
		// change column order - drag state column to capital
		new Drag(selenium, LOC_SPAN_STATE, LOC_DIV_DROP_ZONE_CAPITAL).drop();
		waitForSplash();

		assertEquals(associationHash, getAssociationMap().hashCode());
		assertEquals(getColumnIndex(LOC_TH_FLAG), 1);
		assertEquals(getColumnIndex(LOC_TH_TIME_ZONE), 2);
		assertEquals(getColumnIndex(LOC_TH_CAPITAL), 3);
		assertEquals(getColumnIndex(LOC_TH_STATE), 4);
	}

	private String getInputFilterState() {
		return preformatFilterInput(LOC_TH_STATE);
	}

	private HashMap<String, Vector<String>> getAssociationMap() {
		HashMap<String, Vector<String>> association = new HashMap<String, Vector<String>>();

		String[] columnsPreformatted = new ArrayTransform<String, String>(String.class) {
			public String transformation(String columnHeaderDragging) {
				return preformatColumn(columnHeaderDragging);
			}
		}.transform(LOC_TH_DRAGGING_TESTS);

		int rows = getJQueryCount(format(columnsPreformatted[0], 0));
		
		for (int row = 1; row <= rows; row++) {
		    String key = selenium.getText(format(columnsPreformatted[0], row));
			Vector<String> values = new Vector<String>(columnsPreformatted.length - 1);
			for (int column = 1; column < columnsPreformatted.length; column++) {
				String columnsContentDiv = columnsPreformatted[column];
				String value = getImgSrcOrText(format(columnsContentDiv, row));
				values.add(column - 1, value);
			}
			association.put(key, values);
		}

		return association;
	}

	private String getImgSrcOrText(String locator) {
		String imgSrc = format(LOC_IMAGE_SRC_FLAG_PREFORMATTED, locator);
		
		if (selenium.isElementPresent(imgSrc)) {
			return selenium.getAttribute(imgSrc);
		} else {
			return selenium.getText(locator);
		}
	}
}
