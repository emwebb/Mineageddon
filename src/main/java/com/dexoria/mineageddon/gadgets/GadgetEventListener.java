package com.dexoria.mineageddon.gadgets;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import com.dexoria.mineageddon.Mineageddon;

public class GadgetEventListener implements Listener{
	@EventHandler
	public void playerInteract(PlayerInteractEvent event) {
		if(Mineageddon.getConfigStaticly().isAllowedWorld(event.getPlayer().getWorld().getName())) {
			if(event.hasItem()) {
				String vinnilaID = event.getItem().getType().name() + ":" + event.getItem().getDurability();
				if(Gadget.isVanillaItemGadget(vinnilaID)) {
					Gadget.getGadgetFromVanillaID(vinnilaID).onPlayerInteractEvent(event);
				}
			}
		}

	}

	@EventHandler
	public void entityDamagerByEntity(EntityDamageByEntityEvent event) {
		if(Mineageddon.getConfigStaticly().isAllowedWorld(event.getEntity().getWorld().getName())) {

			if(event.getDamager().getType() == EntityType.PLAYER) {
				String vanillaID = ((Player)event.getDamager()).getItemInHand().getType().name() + ":" + ((Player)event.getDamager()).getItemInHand().getDurability();
				if(Gadget.isVanillaItemGadget(vanillaID)) {
					Gadget.getGadgetFromVanillaID(vanillaID).onEntityDamageByEntityBeingDamager(event);
				}


			}
		}
	}
}
