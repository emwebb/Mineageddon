package com.dexoria.mineageddon.system.statistics;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import com.dexoria.mineageddon.Mineageddon;
import com.dexoria.mineageddon.gadgets.Gadget;

public class StatisticsSystemListener implements Listener {
	@EventHandler(priority = EventPriority.MONITOR)
	public void onEntityDeath(EntityDeathEvent event) {

		if(!Mineageddon.getConfigStaticly().isAllowedWorld(event.getEntity().getWorld().getName()))
			return;

		if (event.getEntity().getKiller() != null) {
			if(event.getEntity().getKiller().getType() == EntityType.PLAYER) {
				if(event.getEntity().getUniqueId() != event.getEntity().getKiller().getUniqueId()) {
					for(ItemStack item : event.getEntity().getKiller().getInventory().getContents()) {
						String vannilaID = item.getType().name() + ":" + item.getDurability();
						if(Gadget.isVanillaItemGadget(vannilaID)) {
							Mineageddon.getStatisticsSystem().increaseKilledWith(Gadget.getGadgetFromVanillaID(vannilaID).getName());
						}
					}
				} else {
					for(ItemStack item : event.getEntity().getKiller().getInventory().getContents()) {
						String vannilaID = item.getType().name() + ":" + item.getDurability();
						if(Gadget.isVanillaItemGadget(vannilaID)) {
							Mineageddon.getStatisticsSystem().increaseSuicide(Gadget.getGadgetFromVanillaID(vannilaID).getName());
						}
					}
				}
				
			}
			Math.sin(.0);
			if(event.getEntity().getType() == EntityType.PLAYER) {
				for(ItemStack item : event.getEntity().getKiller().getInventory().getContents()) {
					String vannilaID = item.getType().name() + ":" + item.getDurability();
					if(Gadget.isVanillaItemGadget(vannilaID)) {
						Mineageddon.getStatisticsSystem().increaseDiedWith(Gadget.getGadgetFromVanillaID(vannilaID).getName());
					}
				}
			}

		} else if(event.getEntity().getType() == EntityType.PLAYER) {
			for(ItemStack item : event.getEntity().getKiller().getInventory().getContents()) {
				String vannilaID = item.getType().name() + ":" + item.getDurability();
				if(Gadget.isVanillaItemGadget(vannilaID)) {
					Mineageddon.getStatisticsSystem().increaseSuicide(Gadget.getGadgetFromVanillaID(vannilaID).getName());
				}
			}
		}
	}
}
