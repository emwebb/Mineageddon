package com.dexoria.mineageddon.game;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

import com.dexoria.mineageddon.Mineageddon;

public class GameManagerListener implements Listener {

	@EventHandler()
	public void onEntityDeath(EntityDeathEvent event) {
		if(!Mineageddon.getConfigStaticly().isAllowedWorld(event.getEntity().getWorld().getName()))
			return;
		
		event.getDrops().clear();
		if(event.getEntityType() == EntityType.PLAYER) {
			
			Potion potion = new Potion(PotionType.INSTANT_HEAL,1);
			event.getDrops().add(potion.toItemStack(1));
		}
	}
	
	@EventHandler
	public void onBlockDrop(PlayerDropItemEvent event) {
		if(!Mineageddon.getConfigStaticly().isAllowedWorld(event.getPlayer().getWorld().getName()))
			return;
		
		event.setCancelled(true);
	
	}
}
