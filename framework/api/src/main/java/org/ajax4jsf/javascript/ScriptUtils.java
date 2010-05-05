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

import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.lang.reflect.Array;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.MissingResourceException;

import javax.faces.FacesException;
import javax.faces.context.ResponseWriter;

import org.ajax4jsf.Messages;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author shura (latest modification by $Author: alexsmirnov $)
 * @version $Revision: 1.1.2.3 $ $Date: 2007/01/24 13:22:31 $
 *
 */
public class ScriptUtils {

    private static final Log LOG = LogFactory.getLog(ScriptUtils.class);

    /**
	 * This is utility class, don't instantiate.
	 */
	private ScriptUtils() {

	}

	private static void writeScriptToStream(Writer writer, Object obj, Map<Object, Boolean> cycleBusterMap) throws IOException {
        Boolean cycleBusterValue = cycleBusterMap.put(obj, Boolean.TRUE);

        if (cycleBusterValue != null) {
        	if (LOG.isDebugEnabled()) {
        		String formattedMessage;
        		try {
        			formattedMessage = Messages.getMessage(Messages.JAVASCRIPT_CIRCULAR_REFERENCE, obj);
        		} catch (MissingResourceException e) {
        			//ignore exception: workaround for unit tests
        			formattedMessage = MessageFormat.format("Circular reference serializing object to JS: {0}", obj);
        		}

        		LOG.debug(formattedMessage);
        	}
			writer.write("null");
        } else if (null == obj) {
			writer.write("null");
		} else if (obj instanceof ScriptString) {
			writer.write(((ScriptString) obj).toScript());
		} else if (obj.getClass().isArray()) {
			writer.write("[");
			boolean first = true;
			for (int i = 0; i < Array.getLength(obj); i++) {
				Object element = Array.get(obj, i);
				if (!first) {
					writer.write(',');
				}
				writeScriptToStream(writer, element, cycleBusterMap);
				first = false;
			}

			writer.write("] ");
		} else if (obj instanceof Collection<?>) {
			// Collections put as JavaScript array.

			@SuppressWarnings("unchecked")
			Collection<Object> collection = (Collection<Object>) obj;

			writer.write("[");
			boolean first = true;
			for (Iterator<Object> iter = collection.iterator(); iter.hasNext();) {
				Object element = iter.next();
				if (!first) {
					writer.write(',');
				}
				writeScriptToStream(writer, element, cycleBusterMap);
				first = false;
			}
			writer.write("] ");
		} else if (obj instanceof Map<?, ?>) {

			// Maps put as JavaScript hash.
			@SuppressWarnings("unchecked")
			Map<Object, Object> map = (Map<Object, Object>) obj;

			writer.write("{");
			boolean first = true;
			for (Map.Entry<Object, Object> entry : map.entrySet()) {
				if (!first) {
					writer.write(',');
				}

				writeEncodedString(writer, entry.getKey());
				writer.write(":");
				writeScriptToStream(writer, entry.getValue(), cycleBusterMap);
				first = false;
			}
			writer.write("} ");
		} else if (obj instanceof Number || obj instanceof Boolean) {
			// numbers and boolean put as-is, without conversion
			writer.write(obj.toString());
		} else if (obj instanceof String) {
			// all other put as encoded strings.
			writeEncodedString(writer, obj);
        } else if (obj instanceof Enum<?>) {
            // all other put as encoded strings.
            writeEncodedString(writer, obj);
        } else if (obj.getClass().getName().startsWith("java.sql.")) {
            writer.write("{");
            boolean first = true;
            for (PropertyDescriptor propertyDescriptor :
                                PropertyUtils.getPropertyDescriptors(obj)) {
                String key = propertyDescriptor.getName();
                if ("class".equals(key)) {
                    continue;
                }
                Object value = null;
                try {
                    value = PropertyUtils.getProperty(obj, key);
                } catch (Exception e) {
                    continue;
                }

                if (!first) {
                    writer.write(',');
                }

                writeEncodedString(writer, key);
                writer.write(":");
                writeScriptToStream(writer, value, cycleBusterMap);

                first = false;
            }
            writer.write("} ");
        } else {
    		// All other objects threaded as Java Beans.
            writer.write("{");

            PropertyDescriptor[] propertyDescriptors;
            try {
                propertyDescriptors = PropertyUtils.getPropertyDescriptors(obj);
            } catch (Exception e) {
                throw new FacesException(
                        "Error in conversion Java Object to JavaScript", e);
            }

            boolean first = true;
            for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                String key = propertyDescriptor.getName();
                if ("class".equals(key)) {
                    continue;
                }
                if (!first) {
                    writer.write(',');
                }
                writeEncodedString(writer, key);
                writer.write(":");

                Object propertyValue;
                try{
                	propertyValue = PropertyUtils.getProperty(obj, key);
                } catch (Exception e) {
                	throw new FacesException(
                        "Error in conversion Java Object to JavaScript", e);
                }

				writeScriptToStream(writer, propertyValue, cycleBusterMap);
                first = false;
            }

            writer.write("} ");
        }

        if (cycleBusterValue == null) {
        	cycleBusterMap.remove(obj);
        }
	}

	/**
	 * Convert any Java Object to JavaScript representation ( as possible ) and write it to
	 * writer immediately
	 *
	 * @param responseWriter
	 * @param obj
	 * @throws IOException
	 */
	public static void writeToStream(final ResponseWriter responseWriter, Object obj) throws IOException {
		Map<Object, Boolean> cycleBusterMap = new IdentityHashMap<Object, Boolean>();
		writeScriptToStream(new ResponseWriterWrapper(responseWriter), obj, cycleBusterMap);
	}

	/**
	 * Convert any Java Object to JavaScript representation ( as possible ).
	 * @param obj
	 * @return
	 */
	public static String toScript(Object obj) {
		StringBuilder sb = new StringBuilder();
		try {
			Map<Object, Boolean> cycleBusterMap = new IdentityHashMap<Object, Boolean>();
			writeScriptToStream(new StringBuilderWriter(sb), obj, cycleBusterMap);
		} catch (IOException e) {
			//ignore
		}
		return sb.toString();
	}

	public static void writeEncodedString(Writer w, Object obj) throws IOException {
		w.write("'");
		writeEncoded(w, obj);
		w.write("'");
	}

	public static void addEncodedString(StringBuilder buff, Object obj) {
		try {
			writeEncodedString(new StringBuilderWriter(buff), obj);
		} catch (IOException e) {
			//ignore
		}
	}

	public static void writeEncoded(Writer w, Object obj) throws IOException {
		JSEncoder encoder = new JSEncoder();
		char chars[] = obj.toString().toCharArray();
		for (int i = 0; i < chars.length; i++) {
			char c = chars[i];
			if (!encoder.compile(c)) {
				w.write(encoder.encode(c));
			} else {
				w.write(c);
			}
		}
	}

	public static void addEncoded(StringBuilder buff, Object obj) {
		try {
			writeEncoded(new StringBuilderWriter(buff), obj);
		} catch (IOException e) {
			//ignore
		}
	}

	public static String getValidJavascriptName(String s) {

		StringBuffer buf = null;
		for (int i = 0, len = s.length(); i < len; i++) {
			char c = s.charAt(i);

			if (Character.isLetterOrDigit(c)||c=='_' ) {
				// allowed char
				if (buf != null)
					buf.append(c);
			} else {
				if (buf == null) {
					buf = new StringBuffer(s.length() + 10);
					buf.append(s.substring(0, i));
				}

				buf.append('_');
				if (c < 16) {
					// pad single hex digit values with '0' on the left
					buf.append('0');
				}

				if (c < 128) {
					// first 128 chars match their byte representation in UTF-8
					buf.append(Integer.toHexString(c).toUpperCase());
				} else {
					byte[] bytes;
					try {
						bytes = Character.toString(c).getBytes("UTF-8");
					} catch (UnsupportedEncodingException e) {
						throw new RuntimeException(e);
					}

					for (int j = 0; j < bytes.length; j++) {
						int intVal = bytes[j];
						if (intVal < 0) {
							// intVal will be >= 128
							intVal = 256 + intVal;
						} else if (intVal < 16) {
							// pad single hex digit values with '0' on the left
							buf.append('0');
						}
						buf.append(Integer.toHexString(intVal).toUpperCase());
					}
				}
			}

		}

		return buf == null ? s : buf.toString();
	}
}
