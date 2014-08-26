package com.dexoria.mineageddon.configuration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.dexoria.mineageddon.Mineageddon;
import com.dexoria.mineageddon.utils.FileUtils;

public class Config {
	private static final String ALLOWED_WORLDS_ID = "allowedWorlds";
	
	private File configFile;
	private FileConfiguration config;
	public void onEnable() {
		configFile = new File(Mineageddon.getInstance().getDataFolder(), "mainConfig.yml");
		if(!configFile.exists()) {
			configFile.getParentFile().mkdirs();
			FileUtils.copy(Mineageddon.getInstance().getResource("mainConfig.yml"), configFile);
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
	
	public List<String> getAllowedWorlds() {
		return config.getStringList(ALLOWED_WORLDS_ID);
	}
	
	public void setAllowedWorlds(List<String> worlds) {
		config.set(ALLOWED_WORLDS_ID, worlds);
	}
	
	public void addAllowedWorld(String world){
		List<String> allowedWorlds = getAllowedWorlds();
		if(allowedWorlds.contains(world)) {
			throw new IllegalArgumentException("World '" + world + "' is already in allowedWorlds");
		} else {
			allowedWorlds.add(world);
			setAllowedWorlds(allowedWorlds);
		}	
	}
	
	public void removeAllowedWorld(String world) {
		List<String> allowedWorlds = getAllowedWorlds();
		if(!allowedWorlds.contains(world)) {
			throw new IllegalArgumentException("World '" + world + "' is not in allowedWorlds");
		} else {
			allowedWorlds.remove(world);
			setAllowedWorlds(allowedWorlds);
		}	
	}
}