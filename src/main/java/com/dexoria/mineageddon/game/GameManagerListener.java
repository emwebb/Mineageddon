package com.dexoria.mineageddon.game;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

import com.dexoria.mineageddon.Mineageddon;

public class GameManagerListener implements Listener {

	@EventHandler
	public void onEntityDeath(EntityDeathEvent event) {
		if(!Mineageddon.getConfigStaticly().isAllowedWorld(event.getEntity().getWorld().getName()))
			return;
		
		event.getDrops().clear();
		if(event.getEntityType() == EntityType.PLAYER) {
			
			Potion potion = new Potion(PotionType.INSTANT_HEAL,1);
			event.getDrops().add(potion.toItemStack(1));
			event.getDrops().add(new ItemStack(Material.COOKIE,3));
			
			Mineageddon.getGameSystem().addPlayerToRespawnList((Player)event.getEntity());
		}
	}
	
	@EventHandler
	public void onBlockDrop(PlayerDropItemEvent event) {
		if(!Mineageddon.getConfigStaticly().isAllowedWorld(event.getPlayer().getWorld().getName()))
			return;
		
		event.setCancelled(true);
	
	}
	
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		
		if(Mineageddon.getGameSystem().playerOnRespawnList(event.getPlayer())) {
			Mineageddon.getGameSystem().removePlayerFromRespawnList(event.getPlayer());
			Mineageddon.getGameSystem().setupPlayer(event.getPlayer());
		}
	
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void dmg(final EntityDamageEvent event) {
		if(!Mineageddon.getConfigStaticly().isAllowedWorld(event.getEntity().getWorld().getName()))
			return;
		
		Entity e = event.getEntity();
		if(e instanceof Player) {
			final Player player = (Player)e;
			
			Bukkit.getScheduler().scheduleSyncDelayedTask(Mineageddon.getInstance(), new Runnable(){

				@Override
				public void run() {
					player.getInventory().getBoots().setDurability((short) 0);
					player.getInventory().getLeggings().setDurability((short) 0);
					player.getInventory().getChestplate().setDurability((short) 0);
					
				}}, 1);
		}
		
		
	}
}
