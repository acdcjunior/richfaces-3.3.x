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
package org.ajax4jsf.javascript;

import static org.easymock.EasyMock.aryEq;
import static org.easymock.EasyMock.eq;
import static org.easymock.EasyMock.isNull;
import static org.easymock.EasyMock.reportMatcher;
import static org.easymock.classextension.EasyMock.createStrictMock;
import static org.easymock.classextension.EasyMock.replay;
import static org.easymock.classextension.EasyMock.verify;

import java.io.Writer;
import java.lang.reflect.Array;
import java.util.Random;

import javax.faces.context.ResponseWriter;

import junit.framework.TestCase;

import org.easymock.IArgumentMatcher;

/**
 * @author Nick Belaevski
 * @since 3.3.2
 */
public class ResponseWriterWrapperTest extends TestCase {

	private ResponseWriter mockWriter;
	
	private Writer writer;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
	
		mockWriter = createStrictMock(ResponseWriter.class);
		writer = new ResponseWriterWrapper(mockWriter);
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();

		this.writer = null;
		this.mockWriter = null;
	}
	
	public void testWrite1() throws Exception {
		char[] cs = new char[] {'a', 'b'};
		mockWriter.writeText(cs, 0, 2);
		replay(mockWriter);

		writer.write(cs);
		verify(mockWriter);
	}
	
	private static char[] expectSingleChar(final char c) {
		reportMatcher(new IArgumentMatcher() {

			private String failureMessage;
			
			public void appendTo(StringBuffer sb) {
				sb.append(failureMessage);
			}

			public boolean matches(Object o) {
				if (!(o instanceof char[])) {
					failureMessage = "Array of chars expected as argument";
				} else {
					if (Array.getLength(o) == 0) {
						failureMessage = "Array should be of non-zero length";
					} else {
						if (Array.getChar(o, 0) != c) {
							failureMessage = "["+c+"] expected as [0] char";
						}
					}
				}
				
				return failureMessage == null;
			}
			
		});
		
		return null;
	}
	
	private static char[] expectFirstChars(final char[] cs) {
		return expectFirstChars(cs, cs.length);
	}

	private static char[] expectFirstChars(final char[] cs, final int length) {
		reportMatcher(new IArgumentMatcher() {

			private String failureMessage;
			
			public void appendTo(StringBuffer sb) {
				sb.append(failureMessage);
			}

			public boolean matches(Object o) {
				if (!(o instanceof char[])) {
					failureMessage = "Array of chars expected as argument";
				} else {
					char[] argChars = (char[]) o;
					if (argChars.length < length) {
						failureMessage = "Array should have minimum " + length + " length, but has only: " + argChars.length;
					} else {
						for (int i = 0; i < length; i++) {
							if (argChars[i] != cs[i]) {
								failureMessage = "Char at offset [" + i + "] mismath: expected " + cs[i] + " but was " + argChars[i];
								break;
							}
						}
					}
				}
				
				return failureMessage == null;
			}
			
		});
		
		return null;
	}
	
	public void testWrite2() throws Exception {
		mockWriter.writeText(expectSingleChar((char) 0x5678), eq(0), eq(1));
		mockWriter.writeText(expectSingleChar((char) 0xBA98), eq(0), eq(1));
		replay(mockWriter);
		writer.write(0x12345678);
		writer.write(0xFECDBA98);
		verify(mockWriter);
	}
	
	public void testWrite3() throws Exception {
		mockWriter.writeText(eq("test"), (String) isNull());
		replay(mockWriter);
		writer.write("test");
		verify(mockWriter);
	}
	
	public void testWrite4() throws Exception {
		mockWriter.writeText(aryEq("abcd".toCharArray()), eq(1), eq(2));
		mockWriter.writeText(aryEq("efgh".toCharArray()), eq(0), eq(3));
		mockWriter.writeText(aryEq("ijklm".toCharArray()), eq(2), eq(3));

		replay(mockWriter);
		writer.write("abcd".toCharArray(), 1, 2);
		writer.write("efgh".toCharArray(), 0, 3);
		writer.write("ijklm".toCharArray(), 2, 3);
		verify(mockWriter);
	}
	
	public void testWrite5() throws Exception {
		mockWriter.writeText(expectFirstChars("string to".toCharArray()), eq(0), eq(9));
		mockWriter.writeText(expectFirstChars("one".toCharArray()), eq(0), eq(3));
		
		replay(mockWriter);
		writer.write("string to test", 0, 9);
		writer.write("short one", 6, 3);
		verify(mockWriter);
	}
	
	public void testWrite6() throws Exception {
		char[] cs = new char[4098];
		int length = cs.length - 2;

		for (int i = 0; i < cs.length; i++) {
			cs[i] = (char) new Random().nextInt(Character.MAX_VALUE + 1);
		}
		
		mockWriter.writeText(expectFirstChars(cs, length), eq(0), eq(length)); 
		replay(mockWriter);
		writer.write(String.valueOf(cs), 0, length);
		verify(mockWriter);
	}
	
	public void testFlush() throws Exception {
		mockWriter.flush();
		replay(mockWriter);
		writer.flush();
		verify(mockWriter);
	}

	public void testClose() throws Exception {
		mockWriter.close();
		replay(mockWriter);
		writer.close();
		verify(mockWriter);
	}
}
