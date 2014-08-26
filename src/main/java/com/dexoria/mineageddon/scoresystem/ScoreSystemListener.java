package com.dexoria.mineageddon.scoresystem;

import java.util.logging.Level;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;

import com.dexoria.mineageddon.Mineageddon;
import com.dexoria.mineageddon.references.Debug;

public class ScoreSystemListener implements Listener{

	@EventHandler(priority = EventPriority.MONITOR)
	public void entityDamagerByEntity(EntityDamageByEntityEvent event) {
		if(Debug.ON){
			Mineageddon.getLoggerStaticly().log(Level.INFO,"EntityDamageByEntityEvent - Damager: " + event.getDamager().getUniqueId() + " Type: " + event.getCause().name());
		}
	}
	@EventHandler
	public void onEntityDeath(EntityDeathEvent event) {
		if (event.getEntity().getKiller() != null) {
			Player player = event.getEntity().getKiller();
			Mineageddon.getLoggerStaticly().log(Level.INFO,player.getName() + " killed " + event.getEventName());
			
		}
	}
}
