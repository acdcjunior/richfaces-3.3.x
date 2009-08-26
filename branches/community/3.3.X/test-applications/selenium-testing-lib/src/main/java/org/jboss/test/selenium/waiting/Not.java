/*
 * JBoss, Home of Professional Open Source
 * Copyright 2009, Red Hat Middleware LLC, and others contributors as indicated
 * by the @authors tag. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 * This copyrighted material is made available to anyone wishing to use,
 * modify, copy, or redistribute it subject to the terms and conditions
 * of the GNU Lesser General Public License, v. 2.1.
 * This program is distributed in the hope that it will be useful, but WITHOUT A
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public License,
 * v.2.1 along with this distribution; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 */

package org.jboss.test.selenium.waiting;

/**
 * Opposite to Condition - you specify when desired conditions is not satisfied.
 * 
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public abstract class Not implements Condition {

	/**
	 * Implementation of condition, which will not be satisfied.
	 * 
	 * @return true if desired condition is <b>not</b> satisfied
	 */
	public final boolean isTrue() {
		return !not();
	}

	/**
	 * This method has to be overridden to return condition which shouldn't be
	 * satisfied.
	 * 
	 * @return true if desired condition is <b>not</b> satisfied, false if it is
	 * 
	 */
	public abstract boolean not();
}
