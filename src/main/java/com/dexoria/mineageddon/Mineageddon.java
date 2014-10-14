package com.dexoria.mineageddon;

import java.sql.SQLException;
import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import com.dexoria.mineageddon.command.CommandHandler;
import com.dexoria.mineageddon.configuration.Config;
import com.dexoria.mineageddon.gadgets.Gadget;
import com.dexoria.mineageddon.mysql.MySQL;
import com.dexoria.mineageddon.system.display.DisplaySystem;
import com.dexoria.mineageddon.system.game.GameSystem;
import com.dexoria.mineageddon.system.score.ScoreSystem;
import com.dexoria.mineageddon.system.shop.ShopSystem;
import com.dexoria.mineageddon.system.statistics.StatisticsSystem;
import com.dexoria.mineageddon.system.world.WorldSystem;

/**
 * @author nxsupert
 * 
 */
public class Mineageddon extends JavaPlugin {

	/**
	 * The static instance of {@link com.dexoria.mineageddon.Mineageddon} .
	 */
	private static Mineageddon instance;

	/**
	 * The instance of the configuration.
	 */
	private Config config;

	/**
	 * The instance of the MySQL connection and handler.
	 */
	private MySQL sql;

	/**
	 * The instance of the Score System.
	 */
	private ScoreSystem scoreSystem;

	/**
	 * The instance of the Game System.
	 */
	private GameSystem gameSystem;

	/**
	 * The instance of the Statistics System.
	 */
	private StatisticsSystem statisticsSystem;

	/**
	 * The instance of the Shop System.
	 */
	private ShopSystem shopSystem;
	/**
	 * The instance of the Display System.
	 */
	private DisplaySystem displaySystem;
	
	/**
	 * The instance of the World System
	 */
	private WorldSystem worldSystem;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bukkit.plugin.java.JavaPlugin#onEnable()
	 */
	@Override
	public void onEnable() {
		instance = this;

		config = new Config();
		config.onEnable();

		sql = new MySQL(this, config.getDBHostName(), config.getDBPort(),
				config.getDBDatabase(), config.getDBUsername(),
				config.getDBPassword());
		
		worldSystem = new WorldSystem();
		gameSystem = new GameSystem();
		statisticsSystem = new StatisticsSystem();
		scoreSystem = new ScoreSystem();
		shopSystem = new ShopSystem();
		displaySystem = new DisplaySystem();
		
		worldSystem.onEnable();
		Gadget.onEnable();
		gameSystem.onEnable();
		statisticsSystem.onEnable();
		scoreSystem.onEnable();
		shopSystem.onEnable();
		displaySystem.onEnable();

		this.getCommand("mineageddon").setExecutor(new CommandHandler());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bukkit.plugin.java.JavaPlugin#onDisable()
	 */
	@Override
	public void onDisable() {
		statisticsSystem.onDisable();
		Gadget.onDisable();

		scoreSystem.onDisable();
		try {
			sql.closeConnection();
		} catch (SQLException e) {
			// Can't allow a crash here so no print stack trace.

		}
		displaySystem.onDisable();
		
		gameSystem.onDisable();
		config.onDisable();
		shopSystem.onDisable();
		worldSystem.onDisable();
		gameSystem = null;
		sql = null;
		scoreSystem = null;
		config = null;
		statisticsSystem = null;
		shopSystem = null;
		displaySystem = null;
		worldSystem = null;
		instance = null;
	}

	/**
	 * @return the instance of
	 *         {@link com.dexoria.mineageddon.Mineageddon#config} .
	 */
	public Config getConfigInstannce() {
		return config;
	}

	/**
	 * @return the instance of
	 *         {@link com.dexoria.mineageddon.Mineageddon#instance} .
	 */
	public static Mineageddon getInstance() {
		return instance;
	}

	/**
	 * @return Mineageddon's instance of
	 *         {@link org.bukkit.plugin.java.JavaPlugin#getLogger} .
	 */
	public static Logger getLoggerStaticly() {
		return instance.getLogger();
	}

	/**
	 * @return the instance of
	 *         {@link com.dexoria.mineageddon.Mineageddon#config} .
	 */
	public static Config getConfigStaticly() {
		return instance.getConfigInstannce();
	}

	/**
	 * @return the instance of
	 *         {@link com.dexoria.mineageddon.Mineageddon#scoreSystem} .
	 */
	public static ScoreSystem getScoreSystem() {
		return instance.scoreSystem;
	}

	/**
	 * @return the instance of {@link com.dexoria.mineageddon.Mineageddon#sql} .
	 */
	public static MySQL getMySQL() {
		return instance.sql;
	}

	/**
	 * @return the instance of
	 *         {@link com.dexoria.mineageddon.Mineageddon#gameSystem} .
	 */
	public static GameSystem getGameSystem() {
		return instance.gameSystem;
	}

	/**
	 * @return the instance of
	 *         {@link com.dexoria.mineageddon.Mineageddon#statisticsSystem} .
	 */
	public static StatisticsSystem getStatisticsSystem() {
		return instance.statisticsSystem;
	}

	/**
	 * @return the instance of
	 *         {@link com.dexoria.mineageddon.Mineageddon#shopSystem} .
	 */
	public static ShopSystem getShopSystem() {
		return instance.shopSystem;
	}

	public static DisplaySystem getDisplaySystem() {
		return instance.displaySystem;
	}

	public static WorldSystem getWorldSystem() {
		return instance.worldSystem;
	}



}
