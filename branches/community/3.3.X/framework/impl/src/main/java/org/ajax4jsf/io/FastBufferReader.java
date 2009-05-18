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

package org.ajax4jsf.io;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

/**
 * Class for reading from a char array chain.
 * 
 * @author glory
 *
 */
public class FastBufferReader extends Reader {
	/**
	 * The first link in the chain of char arrays.
	 */
	CharBuffer firstLink;

	/**
	 * Currently read link.
	 */
	CharBuffer current;

	/**
	 * Position of next char in current link.
	 */
	int index;
	
	/**
	 * Creates instance for given writer.
	 * @param writer
	 */	
	public FastBufferReader(FastBufferWriter writer) {
		firstLink = writer.getFirstBuffer();
		current = firstLink;
		index = 0;
	}

	public void close() throws IOException {
	}
	
	/**
	 * @see java.io.Reader.read()
	 */
	public int read() throws IOException {
		if(current == null) return -1;
		if(current.getUsedSize() <= index) {
			current = current.getNext();
			if(current == null) return -1;
			index = 0;
		}
		char c = current.getCharAt(index);
		index++;
		return c;
	}

	/**
	 * @see java.io.Reader.read(char[] cbuf, int off, int len)
	 */
	public int read(char[] cbuf, int off, int len) throws IOException {
        if ((off < 0) || (off > cbuf.length) || (len < 0) ||
                ((off + len) > cbuf.length) || ((off + len) < 0)) {
                throw new IndexOutOfBoundsException();
            } else if (len == 0) {
                return 0;
            }
        if(current == null) return -1;
		int actuallyRead = 0;
		while(current != null && len > 0) {
			int av = current.getUsedSize() - index;
			if(av <= 0) {
				current = current.getNext();
				index = 0;
				continue;
			}
			if(av > len) av = len;
			System.arraycopy(current.getChars(), index, cbuf, off, av);
			index += av;
			off += av;
			actuallyRead += av;
			len -= av;
		}
		return actuallyRead == 0 ? -1 : actuallyRead;
	}
	
	/**
	 * Returns the number of chars that may be read from this storage.
	 * @return
	 * @throws IOException
	 */
	public int available() throws IOException {
		if(current == null) return 0;
		CharBuffer b = current;
		int result = -index;
		while(b != null) {
			result += b.getUsedSize();
			b = b.getNext();
		}
		return result;
	}
	
	/** Writes rest of data written up to the moment to out.
	 * @param out
	 * @throws IOException
	 */
	public void writeTo(Writer writer) throws IOException {
		if(current == null) return ;
		if(current.getUsedSize() > index) {
			writer.write(current.getChars(), index, current.getUsedSize()-index);
			current = current.getNext();
		}
		while(current != null) {
			writer.write(current.getChars(), 0, current.getUsedSize());
			current = current.getNext();
		}
		index = 0;
	}

	
}
