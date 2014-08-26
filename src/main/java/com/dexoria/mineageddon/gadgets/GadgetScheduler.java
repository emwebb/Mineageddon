package com.dexoria.mineageddon.gadgets;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitScheduler;

import com.dexoria.mineageddon.Mineageddon;

public class GadgetScheduler {
	
	private int scheduleID;
	public void onEnable() {
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
		scheduleID = scheduler.scheduleSyncRepeatingTask(Mineageddon.getInstance(), new Gadget10TickSchedule(), 0, 10);
	}
	
	public void onDisbale() {
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
		scheduler.cancelTask(scheduleID);
	}
	
	
	public class Gadget10TickSchedule implements Runnable {

		public void run() {
			for(Player player: Bukkit.getOnlinePlayers()) {
				ItemStack itemInHand = player.getItemInHand();
				String vanillaID = itemInHand.getType().name() + ":" + itemInHand.getDurability();
				if(Gadget.isVanillaItemGadget(vanillaID)){
					Gadget.getGadgetFromVanillaID(vanillaID).whilePlayerHoldingGadget(player, 10);
				}
				
			}
			
		}
		
	}
}
