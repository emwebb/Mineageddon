package com.dexoria.mineageddon;

import java.sql.SQLException;
import java.util.logging.Logger;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import com.dexoria.mineageddon.command.CommandHandler;
import com.dexoria.mineageddon.configuration.Config;
import com.dexoria.mineageddon.gadgets.Gadget;
import com.dexoria.mineageddon.mysql.MySQL;
import com.dexoria.mineageddon.scoresystem.ScoreSystem;
import com.dexoria.mineageddon.scoresystem.ScoreSystemListener;


public class Mineageddon extends JavaPlugin{
	private static Mineageddon instance;
	
	private ScoreSystemListener ssl;
	private ScoreSystem ss;
	private Config config;
	private MySQL sql;
	@Override
	public void onEnable() {
		instance = this;
		config = new Config();
		config.onEnable();
		sql = new MySQL(Mineageddon.getInstance(),
				Mineageddon.getConfigStaticly().getDBHostName(),
				Mineageddon.getConfigStaticly().getDBPort(),
				Mineageddon.getConfigStaticly().getDBDatabase(),
				Mineageddon.getConfigStaticly().getDBUsername(),
				Mineageddon.getConfigStaticly().getDBPassword());
		ss = new ScoreSystem();
		ss.onEnable();
		ssl = new ScoreSystemListener();
		getServer().getPluginManager().registerEvents(ssl, this);
		Gadget.onEnable();
		this.getCommand("mineageddon").setExecutor(new CommandHandler());
	}
	
	@Override
	public void onDisable() {
		Gadget.onDisable();
		HandlerList.unregisterAll(ssl);

		ss.onDisable();
		try {
			sql.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		config.onDisable();
		sql = null;
		ss = null;
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
	
	public static ScoreSystem getScoreSystem() {
		return instance.ss;
	}
	
	public static MySQL getMySQL() {
		return instance.sql;
	}
}
