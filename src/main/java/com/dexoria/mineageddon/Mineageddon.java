package com.dexoria.mineageddon;

import java.util.logging.Logger;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import com.dexoria.mineageddon.gadgets.Gadget;
import com.dexoria.mineageddon.scoresystem.ScoreSystemListener;


public class Mineageddon extends JavaPlugin{
	private static Mineageddon instance;
	private ScoreSystemListener ssl;
	@Override
	public void onEnable() {
		instance = this;
		ssl = new ScoreSystemListener();
		 getServer().getPluginManager().registerEvents(ssl, this);
		Gadget.onEnable();
	}
	
	@Override
	public void onDisable() {
		Gadget.onDisable();
		HandlerList.unregisterAll(ssl);
		instance = null;
	}
	
	public static Mineageddon getInstance() {
		return instance;
	}
	
	public static Logger getLoggerStaticly() {
		return instance.getLogger();
	}
}
