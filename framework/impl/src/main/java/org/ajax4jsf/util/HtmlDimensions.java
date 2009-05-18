/**
 * License Agreement.
 *
 * Rich Faces - Natural Ajax for Java Server Faces (JSF)
 *
 * Copyright (C) 2007 Exadel, Inc.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License version 2.1 as published by the Free Software Foundation.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301  USA
 */

package org.ajax4jsf.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.regex.Pattern;

import org.ajax4jsf.Messages;

public class HtmlDimensions {
	
	private static final Pattern PATTERN_NUMERIC = Pattern.compile("^[+-]?\\d+(\\.\\d+)?$");
	private static final Pattern PATTERN_PX = Pattern.compile("^[+-]?\\d+(\\.\\d+)?px$");
	private static final Pattern PATTERN_PCT = Pattern.compile("^[+-]?\\d+(\\.\\d+)?%$");
	
	private static final NumberFormat numericFormat = new DecimalFormat();
	private static final DecimalFormat pxFormat = new DecimalFormat();
	private static final NumberFormat pctFormat = NumberFormat.getPercentInstance();
	
	static{
		pxFormat.setPositiveSuffix("px");
		pxFormat.setNegativeSuffix("px");
	}
	public static Double decode(String size){
		// TODO - handle px,ex,pt enc suffixes.
		double d = 0;
		try {
			if(size != null){
				if(PATTERN_NUMERIC.matcher(size).matches()){
					synchronized(numericFormat){
						d = numericFormat.parse(size).doubleValue();
					}
				} else if(PATTERN_PX.matcher(size).matches()){
					synchronized (pxFormat) {
						d = pxFormat.parse(size).doubleValue();
					}
				} else if(PATTERN_PCT.matcher(size).matches()){
					synchronized (pctFormat) {
						d = pctFormat.parse(size).doubleValue();
					}
				}
			}
		} catch (ParseException e) {
			throw new IllegalArgumentException(Messages.getMessage(Messages.DECODE_PARAMETER_ERROR, new Object[]{"size", size, e.getMessage()}));
		}
		return new Double(d);
	}
	
	public static String formatPx(Double value){
		return (value.intValue() + "px");
	}
	public static String formatPct(Double value){
		String v = "";
		synchronized (pctFormat) {
			v = pctFormat.format(value.doubleValue());
		}
		return v;
	}
}
