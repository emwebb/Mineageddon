package com.dexoria.mineageddon.gadgets;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import org.bukkit.Material;

import com.dexoria.mineageddon.Mineageddon;
import com.dexoria.mineageddon.references.Debug;

public class Gadget {
	// Static stuff. 
	private static Map<String,String> itemToGadgetIdList;
	private static Map<String,Gadget> gadgets;
	
	public static void onEnable() {
		itemToGadgetIdList = new HashMap<String,String>();
		gadgets = new HashMap<String,Gadget>();
		
		Gadget gadget = new Gadget("Test Gadget", Material.APPLE, 0);
	}
	
	public static void onDisable() {
		itemToGadgetIdList.clear();
		gadgets.clear();
	}
	
	private final String name;
	private final String vanillaID;
	public Gadget(String name, Material vanillaBoundItemMatrial, int vanillaBoundItemMeta) {
		String vanillaID = vanillaBoundItemMatrial.name() + ":" + vanillaBoundItemMeta;
		if(Debug.ON) {
			Mineageddon.getLoggerStaticly().log(Level.INFO,"Added gadget named '" + name + "' and bound it to '" + vanillaID + "'.");
		}
		
		this.name = name;
		this.vanillaID = vanillaID;
		itemToGadgetIdList.put(vanillaID, name);
		gadgets.put(name, this);
	}
	
	
}
