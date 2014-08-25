package com.dexoria.mineageddon;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import com.dexoria.mineageddon.gadgets.Gadget;


public class Mineageddon extends JavaPlugin{
	private static Mineageddon instance;
	@Override
	public void onEnable() {
		instance = this;
		Gadget.onEnable();
	}
	
	@Override
	public void onDisable() {
		Gadget.onDisable();
		instance = null;
	}
	
	public static Mineageddon getInstance() {
		return instance;
	}
	
	public static Logger getLoggerStaticly() {
		return instance.getLogger();
	}
}
