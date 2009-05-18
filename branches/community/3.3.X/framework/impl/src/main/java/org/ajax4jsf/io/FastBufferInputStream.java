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
import java.io.InputStream;

/**
 * Class for reading from a byte array chain.
 * 
 * @author glory
 *
 */
public class FastBufferInputStream extends InputStream {
	/**
	 * The first link in the chain of char arrays.
	 */
	ByteBuffer firstLink;
	
	/**
	 * Currently read link.
	 */
	ByteBuffer current;
	
	/**
	 * Position of next byte in current link.
	 */
	int index;
	
	int mark;
	
	/**
	 * Creates instance of FastBufferInputStream.
	 * @param stream
	 */	
	public FastBufferInputStream(FastBufferOutputStream stream) {
		this.firstLink = stream.getFirstBuffer();
		current = stream.getFirstBuffer();
		index = 0;
		mark = 0;
	}
	
	/**
	 * @see java.io.InputStream.read()
	 */
	public int read() throws IOException {
		if(current == null) return -1;
		if(current.getUsedSize() <= index) {
			current = current.getNext();
			if(current == null) return -1;
			index = 0;
		}
		byte c = current.getByteAt(index);
		index++;
		return c;
	}

	/**
	 * @see java.io.InputStream.read(byte b[])
	 */
	public int read(byte b[]) throws IOException {
		if (b == null) {
			throw new NullPointerException();
		}
		if(current == null) return -1;
		int off = 0;
		int len = b.length;
		int actuallyRead = 0;
		while(current != null && len > 0) {
			int av = current.getUsedSize() - index;
			if(av <= 0) {
				current = current.getNext();
				index = 0;
				continue;
			}
			if(av > len) av = len;
			System.arraycopy(current.getBytes(), index, b, off, av);
			index += av;
			off += av;
			actuallyRead += av;
			len -= av;
		}
		return actuallyRead == 0 ? -1 : actuallyRead;
	}

	/**
	 * @see java.io.InputStream.read(byte b[], int off, int len)
	 */
	public int read(byte[] b, int off, int len) throws IOException {
		if (b == null) {
			throw new NullPointerException();
		} else if ((off < 0) || (off > b.length) || (len < 0) ||
				((off + len) > b.length) || ((off + len) < 0)) {
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
			System.arraycopy(current.getBytes(), index, b, off, av);
			index += av;
			off += av;
			actuallyRead += av;
			len -= av;
		}
		return actuallyRead == 0 ? -1 : actuallyRead;
	}

	/**
	 * @see java.io.InputStream.available()
	 */
	public int available() throws IOException {
		if(current == null) return 0;
		ByteBuffer b = current;
		int result = -index;
		while(b != null) {
			result += b.getUsedSize();
			b = b.getNext();
		}
		return result;
	}

	/**
	 * @see java.io.InputStream.mark()
	 */
	public void mark(int readAheadLimit) {
		if(current == null) {
			mark = 0;
		} else {
			mark = index;
			ByteBuffer b = current.getPrevious();
			if(b != null) mark += b.getTotalSize();
		}
	}
	
	/**
	 * @see java.io.InputStream.reset()
	 */
	public void reset() {
		if(current == null) current = firstLink;
		int m = 0;
		while(current != null && current.getUsedSize() + m <= mark) {
			m += current.getUsedSize();
			current = current.getNext();
		}
		if(current != null) {
			index = mark - m;
		}
	}

	/**
	 * @see java.io.InputStream.skip()
	 */
	public long skip(long n) throws IOException {
		long skipped = 0;
		while(n > 0) {
			if(current == null) return 0;
			if(current.getUsedSize() - index <= n) {
				index += n;
				skipped += n;
				break;
			} else {
				int dn = current.getUsedSize() - index;
				skipped += dn;
				n -= dn;
				current = current.getNext();
				index = 0;
			}
		}
		return skipped;
	}

}
