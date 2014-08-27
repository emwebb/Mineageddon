package com.dexoria.mineageddon.scoresystem;

import java.util.logging.Level;

import net.minecraft.server.v1_7_R1.EntityPlayer;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerLoginEvent;

import com.dexoria.mineageddon.Mineageddon;
import com.dexoria.mineageddon.references.Debug;

public class ScoreSystemListener implements Listener{

	@EventHandler(priority = EventPriority.MONITOR)
	public void entityDamagerByEntity(EntityDamageByEntityEvent event) {
		if(Debug.ON){
			Mineageddon.getLoggerStaticly().log(Level.INFO,"EntityDamageByEntityEvent - Damager: " + event.getDamager().getUniqueId() + " Type: " + event.getCause().name());
		}
	}
	@EventHandler(priority = EventPriority.MONITOR)
	public void onEntityDeath(EntityDeathEvent event) {
		
		if(!Mineageddon.getConfigStaticly().isAllowedWorld(event.getEntity().getWorld().getName()))
			return;
		
		if (event.getEntity().getKiller() != null) {
			if(event.getEntity().getKiller().getType() == EntityType.PLAYER) {
				Player player = event.getEntity().getKiller();
				String killedName;
				if(event.getEntity().getType() == EntityType.PLAYER) {
					killedName = ((EntityPlayer) event.getEntity()).getName();
					Mineageddon.getScoreSystem().transferScore(player.getUniqueId().toString(), ((EntityPlayer) event.getEntity()).getUniqueID().toString(), 0.1F);
				} else {
					killedName = event.getEntity().getType().name();
				}
				Mineageddon.getLoggerStaticly().log(Level.INFO,player.getName() + " killed " + killedName);

			}
			
		}
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerLogin(PlayerLoginEvent event) {
			
		Mineageddon.getScoreSystem().addPlayerIfMissing(event.getPlayer().getUniqueId().toString());
		
		


	}
}
