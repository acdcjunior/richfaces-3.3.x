package org.jboss.richfaces.integrationTest.repeat;

import junit.framework.Assert;

import org.jboss.richfaces.integrationTest.AbstractDataIterationTestCase;
import org.jboss.test.selenium.waiting.*;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public class RepeatTestCase extends AbstractDataIterationTestCase {
	/**
	 * Opens specified page
	 */
	public void openPage() {
		selenium.allowNativeXpath("true");
		
		selenium.open(format("{0}/{1}", contextPath, PAGE));

		scrollIntoView(header, true);
	}

	private final String PAGE = "richfaces/repeat.jsf?c=repeat&tab=usage";
	private final String header = getLoc("repeat--header");
	private final String inputsProposedPrice = getLoc("repeat--inputs--proposed-price");
	private final String selectsReason = getLoc("repeat--selects--reason");
	private final String outputsSalesCost = getLoc("repeat--outputs--sales-cost");
	private final String outputsGrossMargin = getLoc("repeat--outputs--gross-margin");
	
	@Test
	public void functionalTest() {
		openPage();
		
		int rows = selenium.getXpathCount(format(inputsProposedPrice, 0)).intValue();
		
		String[] grossMargins = new String[rows];
		// two run through all rows
		for (int instance = 0; instance < rows * 2; instance++) {
			final boolean isFirstIteration = (instance < rows);
			final int row = 1 + (instance % rows);
			final String inputProposedPrice = format(inputsProposedPrice, row);
			final String selectReason = format(selectsReason, row);
			final String outputGrossMargin = format(outputsGrossMargin, row);
			
			int difference = (row%2==0 ? 1 : -1) * (instance%3);
			
			double salesCost = Double.parseDouble(selenium.getText(format(outputsSalesCost, row)));
			double proposedPrice = salesCost + difference;
			
			String grossMarginString = selenium.getText(outputGrossMargin);
			
			if (!isFirstIteration) {
				Assert.assertEquals(grossMarginString, grossMargins[row-1]);
			}
			
			selenium.type(inputProposedPrice, Double.toString(proposedPrice));
			if (isFirstIteration) {
				Wait.waitForChange("", new Retrieve<String>() {
					public String retrieve() {
						return selenium.getValue(selectReason);
					}
				});
			} else {
				waitForTextChanges(outputGrossMargin, grossMarginString);
			}
			
			int options = selenium.getXpathCount(selectReason + "/*[@value]").intValue();
			Assert.assertTrue(options > 0);
			selenium.select(selectReason, format("index={0}", row % options));
			
			grossMarginString = selenium.getText(outputGrossMargin);
			
			double grossMargin = parseGrossMargin(grossMarginString);
			
			if (difference > 0) {
				Assert.assertTrue(grossMargin > 0);
			} else if (difference < 0) {
				Assert.assertTrue(grossMargin < 0);
			} else {
				Assert.assertTrue(grossMargin == 0);
			}
			
			grossMargins[row-1] = grossMarginString;
		}
		
		for (int row = 1; row <= rows; row++) {
			final String outputGrossMargin = format(outputsGrossMargin, row);
			
			String grossMarginString = selenium.getText(outputGrossMargin);
			Assert.assertTrue(grossMarginString.equals(grossMargins[row-1]));
		}
	}
	
	private double parseGrossMargin(String string) {
		string = string.replaceFirst("\\$", "");
		return Double.parseDouble(string);
	}
}
