/**
 * 
 */
package com.dexoria.mineageddon.system;

import nxsupert.utils.IEndable;

/**
 * @author nxsupert
 *
 */
public class SubSystem implements ISubSystem, IEndable {

	private boolean safeToNullify = true;
	/* (non-Javadoc)
	 * @see com.dexoria.mineageddon.system.ISubSystem#reload()
	 */
	@Override
	public void reload() {
		onDisable();
		onEnable();

	}

	@Override
	public boolean end() {
		try {
			onDisable();
		} catch (Exception e) {
			return false;
		}
			return true;
		
	}

	@Override
	public boolean isSafeToNullify() {
		return safeToNullify;
	}

	@Override
	public void onEnable() {
		safeToNullify = false;
		
	}

	@Override
	public void onDisable() {
		safeToNullify = true;
		
	}

	@Override
	public void kill() {
		onDisable();
		
	}

}
