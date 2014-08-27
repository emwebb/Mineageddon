package com.dexoria.mineageddon;

import java.util.logging.Logger;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import com.dexoria.mineageddon.command.CommandHandler;
import com.dexoria.mineageddon.configuration.Config;
import com.dexoria.mineageddon.gadgets.Gadget;
import com.dexoria.mineageddon.scoresystem.ScoreSystemListener;


public class Mineageddon extends JavaPlugin{
	private static Mineageddon instance;
	
	private ScoreSystemListener ssl;
	private Config config;
	@Override
	public void onEnable() {
		instance = this;
		config = new Config();
		config.onEnable();
		ssl = new ScoreSystemListener();
		getServer().getPluginManager().registerEvents(ssl, this);
		Gadget.onEnable();
		this.getCommand("mineageddon").setExecutor(new CommandHandler());
	}
	
	@Override
	public void onDisable() {
		Gadget.onDisable();
		HandlerList.unregisterAll(ssl);
		config.onDisable();
		config = null;
		instance = null;
	}
	
	public Config getConfigInstannce() {
		return config;
	}
	
	public static Mineageddon getInstance() {
		return instance;
	}
	
	public static Logger getLoggerStaticly() {
		return instance.getLogger();
	}
	
	public static Config getConfigStaticly() {
		return instance.getConfigInstannce();
	}
}
