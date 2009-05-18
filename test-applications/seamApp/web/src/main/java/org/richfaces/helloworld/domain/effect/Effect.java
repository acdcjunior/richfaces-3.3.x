/**
 * License Agreement.
 *
 *  JBoss RichFaces - Ajax4jsf Component Library
 *
 * Copyright (C) 2007  Exadel, Inc.
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

package org.richfaces.helloworld.domain.effect;

import org.richfaces.component.html.HtmlEffect;
import org.richfaces.helloworld.domain.util.componentInfo.ComponentInfo;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("effect")
@Scope(ScopeType.SESSION)
public class Effect {
	private double time;
	private String stateName;
	private boolean state;
	private HtmlEffect htmlEffect = null;
	
	public HtmlEffect getHtmlEffect() {
		return htmlEffect;
	}

	public void setHtmlEffect(HtmlEffect htmlEffect) {
		this.htmlEffect = htmlEffect;
	}
	
	public String add(){
		ComponentInfo info = ComponentInfo.getInstance();
		info.addField(htmlEffect);
		return null;
	}

	public Effect() {
		time = 1.4;
		state = true;
		stateName = "buttonID";
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}
}
