package com.dexoria.mineageddon.system.game;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import com.dexoria.mineageddon.Mineageddon;
import com.dexoria.mineageddon.utils.particles.ParticleEffect;

public class BorderManager {
	private int warningScheduleID;
	public void onEnable() {
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
		warningScheduleID = scheduler.scheduleSyncRepeatingTask(
				Mineageddon.getInstance(), new BorderWarningTick(), 0, 10);
	}

	public void onDisable() {
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
		scheduler.cancelTask(warningScheduleID);
	}

	public class BorderWarningTick implements Runnable {

		public void run() {
			for (Player player : Bukkit.getOnlinePlayers()) {
				if (Mineageddon.getWorldSystem().isMgWorld(player.getWorld().getName())) {

					double playerX = player.getLocation().getX();
					double playerZ = player.getLocation().getZ();
					double squaredDistanceFromCenter = (playerX * playerX)
							+ (playerZ * playerZ);
					int deathDistance = Mineageddon.getWorldSystem().getWorldConfig(player.getWorld().getName()).getRadius();
					int deathDistanceSquared = (deathDistance*deathDistance)*2;
					int waringDistanceSquared = ((deathDistance-5)*(deathDistance-5))*2;

					if (squaredDistanceFromCenter > waringDistanceSquared) {
						player.sendMessage(ChatColor.RED
								+ "YOU ARE OUTSIDE OF THE WORLD BORDER. PLEASE HEAD BACK TOWARDS THE CENTER OR YOU WILL BE EXTERMINATED!");
					}
					if (squaredDistanceFromCenter > deathDistanceSquared) {
						player.damage(10.0);
					}

				}
			}

		}

	}

}



