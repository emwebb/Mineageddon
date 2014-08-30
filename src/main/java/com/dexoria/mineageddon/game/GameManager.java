package com.dexoria.mineageddon.game;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import com.dexoria.mineageddon.Mineageddon;
import com.dexoria.mineageddon.gadgets.Gadget;

public class GameManager {
	
	public static final int WORLD_SIZE = 100;
	
	public static final int ITEMS_PER_PALYER = 3;
	
	private Random rand;
	
	private GameManagerListener gml;
	
	public GameManager() {
		rand = new Random();
	}
	
	public void onEnable() {
		gml = new GameManagerListener();
		Bukkit.getPluginManager().registerEvents(gml, Mineageddon.getInstance());
	}
	
	public void onDisable() {
		HandlerList.unregisterAll(gml);
		gml = null;
	}
	
	public void setupPlayer(Player player) {

		
		spawnPlayer(player);
		
		player.getInventory().clear();
		
		equipPlayer(player);
		
		player.setGameMode(GameMode.SURVIVAL);
		player.setHealth(20.0);
		player.setFoodLevel(20);
		player.setSaturation(20);
		for (PotionEffect effect : player.getActivePotionEffects())
			player.removePotionEffect(effect.getType());
		player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100,50),true);
		player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100,50),true);
		
	}
	
	public void equipPlayer(Player player) {
		
		for(int n = 0; n < ITEMS_PER_PALYER; n++) {
			addRandomGadgetToPlayer(player);
		}
		
		
	}
	
	public void addRandomGadgetToPlayer(Player player) {
		Object[] gadgetArray =  Gadget.getAllGadget().values().toArray();
		boolean success = false;
		ItemStack gadgetItem = null;
		while(!success) {
			gadgetItem = ((Gadget)gadgetArray[rand.nextInt(gadgetArray.length)]).createItem();
			success = !player.getInventory().contains(gadgetItem);
			
		}
		player.getInventory().addItem(gadgetItem);
		
	}
	
	public void spawnPlayer(Player player) {

		Location playerSpawn = generateSpawnLocation();
		
		player.teleport(playerSpawn);
	}
	
	public Location generateSpawnLocation() {
	
		boolean success = false;
		Location loc = null;
		while(!success) {
			String worldName =	Mineageddon.getConfigStaticly().getAllowedWorlds().get(rand.nextInt(Mineageddon.getConfigStaticly().getAllowedWorlds().size()));
			Block spawnBlock = Bukkit.getWorld(worldName).getHighestBlockAt(rand.nextInt(WORLD_SIZE) - (WORLD_SIZE / 2), rand.nextInt(WORLD_SIZE) - (WORLD_SIZE / 2));
			
			loc = spawnBlock.getLocation().add(new Vector(0,1,0));
			success = true;
		}
		
		
		return loc;
		
	}
}
