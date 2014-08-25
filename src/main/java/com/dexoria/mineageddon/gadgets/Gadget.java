package com.dexoria.mineageddon.gadgets;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerInteractEvent;

import com.dexoria.mineageddon.Mineageddon;
import com.dexoria.mineageddon.references.Debug;

public class Gadget {
	// Static stuff. 
	private static Map<String,String> itemToGadgetIdList;
	private static Map<String,Gadget> gadgets;
	private static GadgetEventListener listener;
	public static void onEnable() {
		listener = new GadgetEventListener();
		Bukkit.getServer().getPluginManager().registerEvents(listener, Mineageddon.getInstance());
		
		itemToGadgetIdList = new HashMap<String,String>();
		gadgets = new HashMap<String,Gadget>();
		
		Gadget gadget = new Gadget("Test Gadget", Material.APPLE, 0);
	}
	
	public static void onDisable() {
		
		itemToGadgetIdList.clear();
		gadgets.clear();
		
		HandlerList.unregisterAll(listener);
		listener = null;
	}
	

	public static boolean isVanillaItemGadget(String vanillaID) {
		return itemToGadgetIdList.containsKey(vanillaID);
	}
	
	public static Gadget getGadgetFromVanillaID(String vanillaID) {
		return gadgets.get(itemToGadgetIdList.get(vanillaID));
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
	
	public boolean onPlayerInteractEvent(PlayerInteractEvent event) {
		if(Debug.ON) {
			Mineageddon.getLoggerStaticly().log(Level.INFO,"onPlayerInteractEvent invoked for '" + name + "' by '" + event.getPlayer().getName() + "'.");
		}
		
		return true;
		
	}
	
}
