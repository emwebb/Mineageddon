package com.dexoria.mineageddon.configuration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.dexoria.mineageddon.Mineageddon;
import com.dexoria.mineageddon.system.ISubSystem;
import com.dexoria.mineageddon.utils.FileUtils;

public class Config {


	private static final String DATABASE_HOST = "database.hostname";
	private static final String DATABASE_PORT = "database.port";
	private static final String DATABASE_DB = "database.db";
	private static final String DATABASE_USERNAME = "database.username";
	private static final String DATABASE_PASSWORD = "database.password";
	private static final String USE_BAR = "usebar";
	

	private File configFile;
	private FileConfiguration config;

	public void onEnable() {
		configFile = new File(Mineageddon.getInstance().getDataFolder(),
				"mainConfig.yml");
		if (!configFile.exists()) {
			configFile.getParentFile().mkdirs();
			FileUtils.copy(
					Mineageddon.getInstance().getResource("mainConfig.yml"),
					configFile);
		}

		config = new YamlConfiguration();

		try {
			config.load(configFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onDisable() {
		try {
			config.save(configFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}



	public String getDBHostName() {
		return config.getString(DATABASE_HOST);
	}

	public String getDBDatabase() {
		return config.getString(DATABASE_DB);
	}

	public String getDBPort() {
		return config.getString(DATABASE_PORT);
	}

	public String getDBUsername() {
		return config.getString(DATABASE_USERNAME);
	}

	public String getDBPassword() {
		return config.getString(DATABASE_PASSWORD);
	}
	
	
	public boolean canUseBar() {
		return config.getBoolean(USE_BAR);
	}

	public void setUseBar(boolean useBar) {
		config.set(USE_BAR, useBar);
	}

}
