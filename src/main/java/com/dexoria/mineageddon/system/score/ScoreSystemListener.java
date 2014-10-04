package com.dexoria.mineageddon.system.score;

import java.util.logging.Level;

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

public class ScoreSystemListener implements Listener {

	@EventHandler(priority = EventPriority.MONITOR)
	public void entityDamagerByEntity(EntityDamageByEntityEvent event) {
		if (Debug.ON) {
			Mineageddon.getLoggerStaticly().log(
					Level.INFO,
					"EntityDamageByEntityEvent - Damager: "
							+ event.getDamager().getUniqueId() + " Type: "
							+ event.getCause().name());
		}
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onEntityDeath(EntityDeathEvent event) {

		if (!Mineageddon.getConfigStaticly().isAllowedWorld(
				event.getEntity().getWorld().getName()))
			return;

		if (event.getEntity().getKiller() != null) {
			if (event.getEntity().getKiller().getType() == EntityType.PLAYER) {
				Player killer = event.getEntity().getKiller();
				String killedName;
				if (event.getEntity().getType() == EntityType.PLAYER) {
					Player killed = ((Player) event.getEntity());
					killedName = killed.getName();
					if (killer.getUniqueId() == killed.getUniqueId()) {
						killedByNothing(killed);
					} else {
						killedByPlayer(killer, killed);
					}
				} else {
					killedName = event.getEntity().getType().name();
				}
				Mineageddon.getLoggerStaticly().log(Level.INFO,
						killer.getName() + " killed " + killedName);

			}

		} else if (event.getEntity().getType() == EntityType.PLAYER) {
			killedByNothing((Player) event.getEntity());
		}
	}

	public void killedByNothing(Player player) {
		Mineageddon.getScoreSystem()
				.removePercentageOfPointsAndTransferToPlayersOnTheServer(
						player.getUniqueId().toString(), 0.01f);

	}

	public void killedByPlayer(Player killer, Player killed) {
		Mineageddon.getScoreSystem().transferScore(
				killer.getUniqueId().toString(),
				killed.getUniqueId().toString(), 0.01F);

	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerLogin(PlayerLoginEvent event) {

		Mineageddon.getScoreSystem().addPlayerIfMissing(
				event.getPlayer().getUniqueId().toString());

	}
}
