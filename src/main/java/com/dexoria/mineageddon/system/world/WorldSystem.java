package com.dexoria.mineageddon.system.world;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.World;

import com.dexoria.mineageddon.system.SubSystem;

public class WorldSystem extends SubSystem {
	private static final String CONFIG_FILE_NAME = "mgWorldConfig.yml";
	private Map<String,WorldConfig> worldConfigurations;
	
	@Override
	public void onEnable() {
		worldConfigurations = new HashMap<String,WorldConfig>();
		for(World world : Bukkit.getWorlds()) {
			File configFile = new File(world.getWorldFolder().getPath() + File.pathSeparator + CONFIG_FILE_NAME);
			if(configFile.exists()) {
				worldConfigurations.put(world.getName(), new WorldConfig(configFile.getPath()));
			}
				
			
		}
	}
	
	@Override
	public void onDisable() {
		worldConfigurations.clear();
		worldConfigurations = null;
	}
	
	public boolean isMgWorld(String worldName) {
		return worldConfigurations.containsKey(worldName);
	}
	
	public WorldConfig getWorldConfig(String worldName) {
		return worldConfigurations.get(worldName);
	}

	public String[] getMgWorlds() {
		return (String[]) worldConfigurations.keySet().toArray();
	}

	public void removeMgWorld(String world) {
		if(worldConfigurations.containsKey(world)) {
			worldConfigurations.get(world).delete();
			worldConfigurations.remove(world);
		}
		
	}

	public void addMgWorld(String worldName) {
		if(!worldConfigurations.containsKey(worldName)) {
			World world = Bukkit.getWorld(worldName);
			File configFile = new File(world.getWorldFolder().getPath() + File.pathSeparator + CONFIG_FILE_NAME);
			worldConfigurations.put(worldName, new WorldConfig(configFile.getPath()));
			
		}
		
	}
}
