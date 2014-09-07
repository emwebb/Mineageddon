package com.dexoria.mineageddon.game;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitScheduler;

import com.dexoria.mineageddon.Mineageddon;
import com.dexoria.mineageddon.gadgets.Gadget;
import com.dexoria.mineageddon.gadgets.GadgetScheduler.Gadget10TickSchedule;

public class BorderManager {
	private int scheduleID;
	public void onEnable() {
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
		scheduleID = scheduler.scheduleSyncRepeatingTask(Mineageddon.getInstance(), new BorderWarningTick(), 0, 10);
	}

	public void onDisbale() {
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
		scheduler.cancelTask(scheduleID);
	}

	public static final double WARNING_DISTANCE_SQUARED = (51*51)+(51*51);
	public static final double DIE_DISTANCE_SQUARED = (55*55)+(55*55);
	public class BorderWarningTick implements Runnable {

		public void run() {
			for(Player player: Bukkit.getOnlinePlayers()) {
				if(Mineageddon.getConfigStaticly().isAllowedWorld(player.getWorld().getName())) {

					double playerX = player.getLocation().getX();
					double playerZ = player.getLocation().getZ();
					double squaredDistanceFromCenter = (playerX * playerX) + (playerZ * playerZ);
					if(squaredDistanceFromCenter > WARNING_DISTANCE_SQUARED) {
						player.sendMessage(ChatColor.RED + "YOU ARE OUTSIDE OF THE WORLD BORDER. PLEASE HEAD BACK TOWARDS THE CENTER OR YOU WILL BE EXTERMINATED!");
					}
					if(squaredDistanceFromCenter > DIE_DISTANCE_SQUARED) {
						player.setHealth(0.0);
					}

				}
			}

		}

	}
}
