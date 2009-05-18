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

import java.io.UnsupportedEncodingException;

/**
 * A single link in chain of byte arrays. 
 * @author glory
 */
public class ByteBuffer {
	private ByteBuffer prev;
	private ByteBuffer next;

	/**
	 * Stored bytes
	 */
	private byte[] bytes;

	/**
	 * Length of byte array.
	 */
	private int cacheSize;

	/**
	 * Number of bytes stored in the array.
	 */
	private int usedSize;

	/**
	 * Creates instance of ByteBuffer with byte array of required length.
	 * @param cacheSize length of byte array
	 */	
	public ByteBuffer(int cacheSize) {
		bytes = new byte[cacheSize];
		this.cacheSize = cacheSize;
		usedSize = 0;
	}
	
	/**
	 * Creates instance of ByteBuffer already filled by bytes.
	 * @param bytes
	 */
	public ByteBuffer(byte[] bytes) {
		this.bytes = bytes;
		usedSize = bytes.length;
		cacheSize = usedSize;
	}

	/**
	 * Appends byte to array if there are unfilled positions in it.
	 * Otherwize creates next link in the chain, and appends the byte to it.
	 * @param c
	 * @return instance of ByteBuffer to which byte was appended.
	 */	
	public ByteBuffer append(byte c) {
		if(next != null) {
			return next.append(c);
		}
		if(usedSize < cacheSize) {
			bytes[usedSize] = c;
			usedSize++;
			return this;
		} else {
			next = new ByteBuffer(cacheSize * 2);
			next.prev = this;
			return next.append(c);
		}
	}

	/**
	 * Appends segment of a byte array to array if there are unfilled positions in it.
	 * Otherwize creates next link in the chain, and appends data to it.
	 * @param c
	 * @return instance of ByteBuffer to which byte array was appended.
	 */	
	public ByteBuffer append(byte[] bs, int off, int len) {
		if(next != null) {
			return append(bs, off, len);
		}
		if(len + usedSize <= cacheSize) {
			System.arraycopy(bs, off, bytes, usedSize, len);
			usedSize += len;
			return this;
		}
		int av = cacheSize - usedSize;
		if(av > 0) {
			System.arraycopy(bs, off, bytes, usedSize, av);
			usedSize += av;
			off += av;
			len -= av;
		}
		next = new ByteBuffer(cacheSize * 2);
		next.prev = this;
		return next.append(bs, off, len);
	}

	/**
	 * Returns stored byte array.
	 * @return stored byte array
	 */	
	public byte[] getBytes() {
		return bytes;
	}
	
	/**
	 * Returns byte at index. No check is fulfilled to provide high speed.
	 * @param index
	 * @return
	 */
	public byte getByteAt(int index) {
		return bytes[index];
	}
	
	/**
	 * Returns actual number of byte stored in this link.
	 * @return
	 */	
	public int getUsedSize() {
		return usedSize;
	}
	
	/**
	 * Returns capacity of this link.
	 * @return
	 */	
	public int getCacheSize() {
		return cacheSize;
	}
	
	/**
	 * Returns total number of bytes stored in this link and all its predecessors. 
	 * @return
	 */	
	public int getTotalSize() {
		return (prev == null) ? usedSize : prev.getTotalSize() + usedSize;
	}
	
	/**
	 * Returns the previous link in the chain.
	 * @return
	 */	
	public ByteBuffer getPrevious() {
		return prev;
	}
	
	/**
	 * Returns the next link in the chain.
	 * @return
	 */	
	public ByteBuffer getNext() {
		return next;
	}
	
	/**
	 * Sets the next link in the chain.
	 * @param b
	 */	
	public void setNext(ByteBuffer b) {
		next = b;
		if(b != null) b.prev = this;
	}
	
	/**
	 * Transforms this instance to instance of CharBuffer (a link of chain of char arrays).
	 * @param encoding
	 * @return link of chain of char arrays
	 * @throws UnsupportedEncodingException
	 */
	public CharBuffer toCharBuffer(String encoding) throws UnsupportedEncodingException {
		String s;
		if (null != encoding) {
			s = new String(bytes, 0, usedSize, encoding);
		} else {
			s = new String(bytes, 0, usedSize);			
		}
		return new CharBuffer(s.toCharArray());
	}

	/**
	 * Transforms this instance to instance of CharBuffer (a link of chain of char arrays).
	 * @return link of chain of char arrays
	 */
	public CharBuffer toCharBuffer() {
		String s = new String(bytes, 0, usedSize);
		return new CharBuffer(s.toCharArray());
	}

	/**
	 * Resets this byte buffer to empty state
	 * @since 3.3.0
	 */
	public void reset() {
		usedSize = 0;
		next = null;
		prev = null;
	}
}
