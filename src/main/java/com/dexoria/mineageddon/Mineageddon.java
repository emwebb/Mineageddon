package com.dexoria.mineageddon;

import java.sql.SQLException;
import java.util.logging.Logger;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import com.dexoria.mineageddon.command.CommandHandler;
import com.dexoria.mineageddon.configuration.Config;
import com.dexoria.mineageddon.gadgets.Gadget;
import com.dexoria.mineageddon.game.GameManager;
import com.dexoria.mineageddon.mysql.MySQL;
import com.dexoria.mineageddon.scoresystem.ScoreSystem;
import com.dexoria.mineageddon.scoresystem.ScoreSystemListener;
import com.dexoria.mineageddon.shopsystem.ShopSystem;
import com.dexoria.mineageddon.statistics.StatisticsSystem;


public class Mineageddon extends JavaPlugin{
	private static Mineageddon instance;
	
	private ScoreSystemListener ssl;
	private ScoreSystem ss;
	private Config config;
	private MySQL sql;
	private GameManager gm;
	private StatisticsSystem stats;
	private ShopSystem shopSystem;
	@Override
	public void onEnable() {
		instance = this;
		config = new Config();
		config.onEnable();
		gm = new GameManager();
		gm.onEnable();
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
		stats = new StatisticsSystem();
		stats.onEnable();
		shopSystem.onEnable();
	}
	
	@Override
	public void onDisable() {
		stats.onDisable();
		Gadget.onDisable();
		HandlerList.unregisterAll(ssl);

		ss.onDisable();
		try {
			sql.closeConnection();
		} catch (SQLException e) {
			// Can't allow a crash here so no print stack trace.
			
		}
		gm.onDisable();
		config.onDisable();
		shopSystem.onDisable();
		gm = null;
		sql = null;
		ss = null;
		config = null;
		stats = null;
		instance = null;
		shopSystem = null;
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
	
	public static GameManager getGameManager() {
		return instance.gm;
	}
	
	public static StatisticsSystem getStatisticsSystem() {
		return instance.stats;
	}

	public ShopSystem getShopSystem() {
		return shopSystem;
	}

	public void setShopSystem(ShopSystem shopSystem) {
		this.shopSystem = shopSystem;
	}
}
