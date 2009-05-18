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

package org.ajax4jsf.builder.generator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.ajax4jsf.builder.config.BuilderConfig;
import org.ajax4jsf.builder.config.ComponentBean;
import org.ajax4jsf.builder.config.PropertyBean;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;


/**
 * Class implement functionality for generate component java files
 * inner element of {@link org.ajax4jsf.builder.ant.JSFGeneratorTask}
 * use in ant build.xml :
 * &lt;jsfgenerator ... &gt;
 *     ....
 *     &lt;components ... /&gt;
 *     ....
 * &lt;/jsfgenerator&gt;
 * attributes:
 * {@see com.exadel.vcp.builder.ant.InnerGenerator}
 * @author asmirnov@exadel.com (latest modification by $Author: ishabalov $)
 * @version $Revision: 1.1.2.3 $ $Date: 2007/02/20 20:58:00 $
 *
 */
public class ComponentGenerator extends InnerGenerator {

	private static final String COMPONENT_FILE_TEMPLATE = "component.vm";
	/**
	 * @param task
	 */
	public ComponentGenerator(JSFGeneratorConfiguration task, Logger log) {
		super(task,log);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.exadel.vcp.builder.ant.InnerGenerator#createFiles(com.exadel.vcp.builder.config.BuilderConfig)
	 */
	public void createFiles(BuilderConfig config) throws GeneratorException {
		VelocityContext context = new VelocityContext();
		Template template = getTemplate();
		context.put("generator",this);
		// Put common properties
		for (Iterator iter = config.getComponents().iterator(); iter.hasNext();) {
			ComponentBean component = (ComponentBean) iter.next();
			info("Create class file "+component.getClassname());
			if (component.isGenerate()) {
				context.put("component", component);
				context.put("renderer", component.getRenderer());
				context.put("package", component.getPackageName());
				Set<String> importClasses = new HashSet<String>();
				for (Iterator it = component.getProperties().iterator(); it
						.hasNext();) {
					PropertyBean property = (PropertyBean) it.next();
					// For non-primitive types, add import declaration.
					if (!property.isSimpleType() && !property.isExist()) {
						importClasses.add(property.getClassname());
					}
				}
				importClasses.add(component.getSuperclass());
				context.put("imports", importClasses);
				String resultPath = component.getClassname().replace('.', '/')
						+ ".java";
				File javaFile = new File(getDestDir(), resultPath);
				File javaDir = javaFile.getParentFile();
				if (!javaDir.exists()) {
					javaDir.mkdirs();
				}
				try {
					if (javaFile.exists()) {
						javaFile.delete();
					}
					Writer out = new BufferedWriter(new FileWriter(javaFile));
					template.merge(context, out);
					out.flush();
					out.close();
				} catch (Exception e) {
					throw new GeneratorException(
							"Error create new Component Java file ", e);
				}
			}
		}
		
	}

	/* (non-Javadoc)
	 * @see com.exadel.vcp.builder.ant.InnerGenerator#getDefaultTemplateName()
	 */
	protected String getDefaultTemplateName() {
		// TODO Auto-generated method stub
		return COMPONENT_FILE_TEMPLATE;
	}

}
