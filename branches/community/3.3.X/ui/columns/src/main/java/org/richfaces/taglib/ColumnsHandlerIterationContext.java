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

package org.richfaces.taglib;

import org.richfaces.iterator.ForEachIterator;
import org.richfaces.iterator.SimpleForEachIterator;
import org.richfaces.el.ELBuilder;
import com.sun.facelets.tag.TagAttribute;
import com.sun.facelets.FaceletContext;

import javax.servlet.jsp.JspTagException;
import javax.el.ValueExpression;
import javax.el.ELException;
import java.util.List;
import java.util.Collection;
import java.util.Iterator;
import java.util.Enumeration;
import java.util.Map;

/**
 * @author akolonitsky
 * @since Dec 21, 2009
 */
class ColumnsHandlerIterationContext {
    /**
     * Iterator for columns's tag value attribute
     */
    private ForEachIterator items; // our 'digested' items

    /**
     * Var attr - defines page variable for current item
     */
    private String indexId;

    /**
     * Integer value begin attr
     */
    private Integer begin;

    /**
     * Integer value end attr
     */
    private Integer end;

    /**
     * Integer value of end attr.
     */
    private Integer columnsCount;

    /**
     * String value of var attr
     */
    private String itemId = null;

    /**
     * Current column counter
     */
    private Integer index = 0;

    /**
     * Expression for var item
     */
    private IteratedExpression iteratedExpression;

    private String valueExpr;

    public String getVarReplacement(TagAttribute index) {
        if (valueExpr == null) {
            return String.valueOf(index);
        }

        if (items != null && items.getVarReplacement() != null) {
            return items.getVarReplacement();
        }
        
        return valueExpr + "[" + this.index + "]";
    }

    public String getIndexReplacement() {
        return String.valueOf(index);
    }

    /**
	 * Return true if we didn't complete column's count
	 *
	 * @return hasNext
	 */
	boolean hasNext() {
		try {
            return (end == 0 || index < end) && items.hasNext();
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * Inits first iteration item
	 */
	void correctFirst() {
		if (items != null) {
			if (begin > 0 && (index < begin)) {
				while (index < begin && hasNext()) {
					next();
				}
				if (!hasNext()) {
					index = 0;
				}
			}
		}
	}

	/**
	 * Iterate to next column
	 *
	 * @param ctx
     * @return
	 */
	Object next() {
		try {
			Object o = items.next();
			index++;
			return o;
		} catch (Exception e) {
			return null;
		}
	}

    void setColumnsCount(FaceletContext ctx, TagAttribute columns) {
        if (columns != null) {
            try {
                this.columnsCount = Integer.parseInt((String) columns.getObject(ctx));
                if (this.columnsCount < 0) {
                    this.columnsCount = 0; // If end is negative set up zero
                }
            } catch (Exception e) {
                this.columnsCount = 0;
            }
        } else {
            this.columnsCount = 0;
        }
    }


    void setBegin(FaceletContext ctx, TagAttribute begin) {
        if (begin != null) {
            try {
                Object o = begin.getObject(ctx);
                if (o instanceof Number) {
                    this.begin = ((Number)o).intValue();
                }else if (o instanceof String) {
                    this.begin = Integer.parseInt((String) o);
                }
                this.begin--;
                if (this.begin < 0) {
                    this.begin = 0; // If end is negative set up zero
                }
            } catch (Exception e) {
                this.begin = 0;
            }
        } else {
            this.begin = 0;
        }
    }


    void setEnd(FaceletContext ctx, TagAttribute end) {
        if (end != null) {
            try {
                Object o = end.getObject(ctx);
                if (o instanceof Number) {
                    this.end = ((Number)o).intValue();
                }else if ( o instanceof String) {
                    this.end = Integer.parseInt((String) o);
                }
                if (this.end < 0) {
                    this.end = 0; // If end is negative set up zero
                }
            } catch (Exception e) {
                this.end = 0;
            }
        } else {
            this.end = 0;
        }
    }

    void setIndex(FaceletContext ctx, TagAttribute index) {
        if (index != null) {
            try {
                indexId = (String) index.getObject(ctx);
            } catch (ClassCastException e) {
                indexId = null;
            }
        }
    }

    void setVar(FaceletContext ctx, TagAttribute var) {
        if (var != null) {
            try {
                itemId = (String) var.getObject(ctx);
            } catch (ClassCastException e) {
                itemId = null;
            }
        }
    }

    void setValue(FaceletContext ctx, TagAttribute value) throws JspTagException {
        // produce the right sort of ForEachIterator
        if (value != null) {
            valueExpr = ELBuilder.getVarReplacement(value.getValue());

            // If this is a deferred expression, make a note and get the 'items' instance.
            // extract an iterator over the 'items' we've got
            items = SimpleForEachIterator.supportedTypeForEachIterator(value.getObject(ctx));
        } else {
            // no 'items', so use 'begin' and 'end'
            items = SimpleForEachIterator.beginEndForEachIterator(columnsCount - 1);
        }
    }

    /**
     * Return expression for page variables
     *
     * @param ctx
     * @param expr
     * @return
     */
    ValueExpression getVarExpression(FaceletContext ctx, ValueExpression expr) {
        Object o = expr.getValue(ctx.getFacesContext().getELContext());
        int k = index;
        if (o.getClass().isArray() || o instanceof List) {
            return new IndexedValueExpression(expr, k);
        }

        if (o instanceof Collection || o instanceof Iterator
            || o instanceof Enumeration || o instanceof Map
            || o instanceof String) {

            if (iteratedExpression == null) {
                iteratedExpression = new IteratedExpression(expr, ",");
            }
            return new IteratedValueExpression(iteratedExpression, k);
        }

        throw new ELException("FOREACH_BAD_ITEMS");
    }

    /**
     * Release iteration variables
     */
    void release() {
        items = null;
        index = 0;
    }

    public String getIndexId() {
        return indexId;
    }

    public String getItemId() {
        return itemId;
    }

    public Integer getBegin() {
        return begin;
    }

    public Integer getIndex() {
        return index;
    }
}
