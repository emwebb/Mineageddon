package com.dexoria.mineageddon.system.game;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import com.dexoria.mineageddon.Mineageddon;
import com.dexoria.mineageddon.system.ISubSystem;

public class BorderManager {
	private int warningScheduleID;
	private int dislayScheduleID;
	public void onEnable() {
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
		warningScheduleID = scheduler.scheduleSyncRepeatingTask(
				Mineageddon.getInstance(), new BorderWarningTick(), 0, 10);
	}

	public void onDisable() {
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
		scheduler.cancelTask(warningScheduleID);
	}

	public static final double WARNING_DISTANCE_SQUARED = (51 * 51) + (51 * 51);
	public static final double DIE_DISTANCE_SQUARED = (55 * 55) + (55 * 55);

	public class BorderWarningTick implements Runnable {

		public void run() {
			for (Player player : Bukkit.getOnlinePlayers()) {
				if (Mineageddon.getWorldSystem().isMgWorld(player.getWorld().getName())) {

					double playerX = player.getLocation().getX();
					double playerZ = player.getLocation().getZ();
					double squaredDistanceFromCenter = (playerX * playerX)
							+ (playerZ * playerZ);
					if (squaredDistanceFromCenter > WARNING_DISTANCE_SQUARED) {
						player.sendMessage(ChatColor.RED
								+ "YOU ARE OUTSIDE OF THE WORLD BORDER. PLEASE HEAD BACK TOWARDS THE CENTER OR YOU WILL BE EXTERMINATED!");
					}
					if (squaredDistanceFromCenter > DIE_DISTANCE_SQUARED) {
						player.damage(10.0);
					}

				}
			}

		}

	}
	
	public class BorderDisplayTick implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			
		}
		
	}

}
