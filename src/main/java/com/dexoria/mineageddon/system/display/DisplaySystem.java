package com.dexoria.mineageddon.system.display;

import java.util.concurrent.Callable;

import me.confuser.barapi.BarAPI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

import com.dexoria.mineageddon.Mineageddon;
import com.dexoria.mineageddon.system.SubSystem;

public class DisplaySystem extends SubSystem {

	private BukkitTask task;

	public void onEnable() {
		super.onEnable();
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
		task = scheduler.runTaskTimerAsynchronously(Mineageddon.getInstance(),
				new Display20TickSchedule(), 0, 20);
	}

	public void onDisable() {
		task.cancel();
		task = null;
		super.onDisable();
	}	

	public class Display20TickSchedule implements Runnable {

		public void run() {
			if (Mineageddon.getConfigStaticly().canUseBar()) {
				for (final Player player : Bukkit.getOnlinePlayers()) {
					if (Mineageddon.getConfigStaticly().isAllowedWorld(
							player.getWorld().getName())) {
						final int score = Mineageddon
								.getScoreSystem()
								.getPlayerScore(player.getUniqueId().toString());
						Bukkit.getScheduler().callSyncMethod(
								Mineageddon.getInstance(),
								new Callable<Object>() {

									@Override
									public Object call() throws Exception {
										BarAPI.setMessage(player,
												ChatColor.BLUE + ""
														+ ChatColor.BOLD
														+ "Score: " + score);
										return null;
									}
								});
					}
				}
			}

		}
	}

	

}
