package com.dexoria.mineageddon.system.world;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.dexoria.mineageddon.Mineageddon;
import com.dexoria.mineageddon.gadgets.Gadget;
import com.dexoria.mineageddon.utils.FileUtils;

public class WorldConfig {
	
	private static final String C_RADIUS = "radius";
	private static final String C_PROBABILITY = "probability";
	private static final String CONNECTOR = ".";
	
	private File configFile;
	private FileConfiguration config;
	
	public WorldConfig(String configURL) {
		configFile = new File(configURL);
		
		if (!configFile.exists()) {
			configFile.getParentFile().mkdirs();
			FileUtils.copy(
					Mineageddon.getInstance().getResource("empty.yml"),
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
		
		addMissingData();
		try {
			config.save(configFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void addMissingData() {
		
		if(!config.contains(C_RADIUS)) {
			config.set(C_RADIUS, 51);
		}
		for(String gadget : Gadget.getAllGadget().keySet()){
			if(!config.contains(C_PROBABILITY + CONNECTOR + gadget)) {
				config.set(C_PROBABILITY + CONNECTOR + gadget, 100);
			}
		}
	}
	
	public void delete() {
		configFile.delete();
	}
	
	public double getGadgetProbability(Gadget gadget) {
		return config.getDouble(C_PROBABILITY + CONNECTOR + gadget.getName());
		
	}
	
	public int getRadius() {
		return config.getInt(C_RADIUS);
	}
}
