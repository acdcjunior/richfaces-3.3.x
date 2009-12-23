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

/*
 * ColumnsHandler.java		Date created: 07.12.2007
 * Last modified by: $Author$
 * $Revision$	$Date$
 */

package org.richfaces.taglib;

import com.sun.facelets.FaceletContext;
import com.sun.facelets.tag.MetaRule;
import com.sun.facelets.tag.MetaRuleset;
import com.sun.facelets.tag.MetaTagHandler;
import com.sun.facelets.tag.Metadata;
import com.sun.facelets.tag.MetadataTarget;
import com.sun.facelets.tag.TagAttribute;
import com.sun.facelets.tag.jsf.ComponentConfig;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.richfaces.el.ELBuilder;

import javax.el.ELException;
import javax.el.ValueExpression;
import javax.el.VariableMapper;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.Iterator;

/**
 * TODO Class description goes here.
 * 
 * @author "Andrey Markavtsov"
 * 
 */
public class ColumnsHandler extends MetaTagHandler {

	private static final Log log = LogFactory.getLog(ColumnsHandler.class);
	
	private com.sun.facelets.tag.jsf.ComponentHandler handler;

	static final String ITERATION_INDEX_VARIABLE = "__richfaces_iteration_index_variable";
	
	private static final String F_GENERATION_SERIES_ATTRIBUTE = "org.richfaces.F_COLUMNS_GENERATION_SERIES";

	/** value attribute */
	private TagAttribute value;

    /** index attribute */
	private TagAttribute index;

	private ThreadLocal<ColumnsHandlerIterationContext> iterationContextLocal = new ThreadLocal<ColumnsHandlerIterationContext>();

	private ColumnsHandlerIterationContext getIterationContext() {
		return iterationContextLocal.get();
	}

	/**
	 * TODO Description goes here.
	 * 
	 * @param config
	 */
	public ColumnsHandler(final ComponentConfig config) {
		super(config);
		
		final ComponentConfig columnConfig;
		
		TagAttribute idAttribute = config.getTag().getAttributes().get("id");
		if (idAttribute != null && idAttribute.isLiteral()) {
			columnConfig = new ColumnHandlerComponentConfig(config);
		} else {
			columnConfig = config;
		}
		
		handler = new ColumnTagHandler(columnConfig) {

			@Override
			protected MetaRuleset createMetaRuleset(Class type) {
				MetaRuleset ruleset = super.createMetaRuleset(type);
				ruleset.addRule(new MetaRule() {

					@Override
					public Metadata applyRule(final String name,
							final TagAttribute attribute, MetadataTarget meta) {
						if (ColumnsAttributes.FILTER_ATTRIBUTES.indexOf(name) != -1 ||
							ColumnsAttributes.SORT_ATTRIBUTES.indexOf(name) != -1) {

							return new Metadata() {

								@Override
								public void applyMetadata(FaceletContext ctx, Object instance) {
									if (!attribute.isLiteral()) {
										String expr = attribute.getValue();
										ColumnsHandlerIterationContext itContext = iterationContextLocal.get();

										ValueExpression ve = ELBuilder.createValueExpression(expr, Object.class, ctx.getExpressionFactory(), ctx,
												itContext.getItemId(), itContext.getIndexId(),
												itContext.getVarReplacement(index), itContext.getIndexReplacement());
										((UIComponent)instance).setValueExpression(name, ve);
									}else {
										((UIComponent)instance).getAttributes().put(name, attribute.getValue());
									}
								}

							};
						}
						return null;
					}

				});
				return ruleset;
			}

			@Override
			protected void applyNextHandler(FaceletContext ctx, UIComponent c)
			throws IOException, FacesException, ELException {
				c.getAttributes().put(F_GENERATION_SERIES_ATTRIBUTE, RequestUniqueIdGenerator.generateId(ctx.getFacesContext()));
				super.applyNextHandler(ctx, c);
			}
		};
	}

    /**
     * Method prepares all we need for starting of tag rendering
     *
     * @param ctx
     */
    private void prepare(FaceletContext ctx) {
        ColumnsHandlerIterationContext itContext = getIterationContext();

        try {
            this.index = getAttribute("index");
            itContext.setIndex(ctx, this.index);

            itContext.setColumnsCount(ctx, getAttribute("columns"));
            itContext.setVar(ctx, getAttribute("var"));
            itContext.setBegin(ctx, getAttribute("begin"));
            itContext.setEnd(ctx, getAttribute("end"));

            this.value = getAttribute("value");
            itContext.setValue(ctx, this.value);

        } catch (Exception e) {
            log.error(e.getLocalizedMessage(), e);
            // TODO: handle exception
        }

        itContext.correctFirst();
    }

    /*
      * (non-Javadoc)
      *
      * @see org.richfaces.taglib.ComponentHandler#apply(com.sun.facelets.FaceletContext,
      *      javax.faces.component.UIComponent)
      */
	public void apply(FaceletContext ctx, UIComponent parent)
	    throws IOException, FacesException, ELException {

        clearOldColumns(ctx.getFacesContext(), parent);

        ColumnsHandlerIterationContext iterationContext = new ColumnsHandlerIterationContext();
        iterationContextLocal.set(iterationContext);
        prepare(ctx);

		try {
			while (iterationContext.hasNext()) {
				exposeVariables(ctx);
				handler.apply(ctx, parent);
				iterationContext.next();
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
			// TODO: handle exception
		} finally {
			iterationContext.release();
			unExposeVariables(ctx);

            iterationContextLocal.remove();
		}
	}


	private static void clearOldColumns(FacesContext context, UIComponent parent) {
		if (parent.getChildCount() > 0) {
			Integer generatedId = RequestUniqueIdGenerator.generateId(context);
			
			Iterator<UIComponent> childrenIt = parent.getChildren().iterator();
			while (childrenIt.hasNext()) {
				UIComponent c = childrenIt.next();

				Object generationSeries = c.getAttributes().get(F_GENERATION_SERIES_ATTRIBUTE);
				if (generationSeries != null && !generationSeries.equals(generatedId)) {
					childrenIt.remove();
				}
			}
		}
	}

    /**
     * Sets page request variables
     *
     * @param ctx
     */
    private void exposeVariables(FaceletContext ctx) {
        VariableMapper vm = ctx.getVariableMapper();
        if (vm == null) {
            return;
        }

        ColumnsHandlerIterationContext itContext = getIterationContext();
        if (itContext.getItemId() != null) {
            if (value != null) {
                ValueExpression srcVE = value.getValueExpression(ctx, Object.class);
                ValueExpression ve = itContext.getVarExpression(ctx, srcVE);
                vm.setVariable(itContext.getItemId(), ve);
            }
        }

        // Set up index variable
        if (itContext.getIndexId() != null) {
            ValueExpression ve = new IteratedIndexExpression(itContext.getIndex());
            vm.setVariable(itContext.getIndexId(), ve);
        }

        int componentsCount = itContext.getIndex() - itContext.getBegin();
        if (componentsCount != 0) {
            ValueExpression ve = ctx.getExpressionFactory().createValueExpression(
                UIViewRoot.UNIQUE_ID_PREFIX + componentsCount, String.class);
            
            vm.setVariable(ITERATION_INDEX_VARIABLE, ve);
        }
    }

    /**
     * Removes page attributes that we have exposed and, if applicable, restores
     * them to their prior values (and scopes).
     *
     * @param ctx
     */
    private void unExposeVariables(FaceletContext ctx) {
        VariableMapper vm = ctx.getVariableMapper();
        if (vm == null) {
            return;
        }

        // "nested" variables are now simply removed
        ColumnsHandlerIterationContext itContext = getIterationContext();
        if (itContext.getItemId() != null) {
            vm.setVariable(itContext.getItemId(), null);
        }
        if (itContext.getIndexId() != null) {
            vm.setVariable(itContext.getIndexId(), null);
        }

        vm.setVariable(ITERATION_INDEX_VARIABLE, null);
    }
}
