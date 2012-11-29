/************************************************************************************
 * Copyright (c) 2012 Brandon Breuil. Contributions by Peter Feiler.                                               *
 *                                                                                  *
 * All rights reserved. This program and the accompanying materials are made        *
 * available under the terms of the Eclipse Public License v1.0 which accompanies   *
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html *
 *                                                                                  *
 * Use is subject to the terms of Eclipse Public License v1.0.                      *
 *                                                                                  *
 ************************************************************************************/

package org.osate.imv.osate.filters;

import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.osate.aadl2.Mode;
import org.osate.aadl2.Subcomponent;
import org.osate.aadl2.instance.InstanceObject;
import org.osate.aadl2.instance.ModeInstance;
import org.osate.aadl2.instance.SystemOperationMode;
import org.osate.imv.model.ModeManager;



public class ModeFilter extends ViewerFilter {

	/**
	 * The current mode used for filtering components.
	 */
	private Mode currentMode;

	public ModeFilter() {
		this.currentMode = ModeManager.ALL_MODES;
	}

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		boolean existsInMode = false; // Indicates if the element exists in the current mode.

		// All elements will be visible if no mode is set or mode is set to ALL_MODES.
		if(currentMode == null || currentMode.equals(ModeManager.ALL_MODES))
			return true;

		if(element instanceof Subcomponent){
			// Get the mode that the subcomponent exists in.
			List<Mode> opModeList = ((Subcomponent)element).getInModes();
			if(opModeList == null){
				// null indicates that the element exists in all modes.
				existsInMode = true;
			}else if(opModeList.contains(currentMode)){
				existsInMode = true;
			}
		}

		if(element instanceof InstanceObject){
			// Get the mode that the instance object exists in.
			List<SystemOperationMode> opModeList = ((InstanceObject)element).getExistsInModes();
			if(opModeList == null){
				// null indicates that the element exists in all modes.
				existsInMode = true;
			}else{
				// For each SOM, get the mode list and check if the current mode is present.
				for(Iterator<SystemOperationMode> it = opModeList.iterator(); it.hasNext(); ){
					// Current modes list will never be null.
					if(it.next().getCurrentModes().contains(currentMode)){
						existsInMode = true;
						break;
					}
				}
			}
		}

		return existsInMode;
	}

	public Mode getCurrentMode() {
		return currentMode;
	}

	public void setCurrentMode(Mode currentMode) {
		this.currentMode = currentMode;
	}

}