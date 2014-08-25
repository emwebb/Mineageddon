package com.dexoria.mineageddon.gadgets;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class GadgetEventListener implements Listener{
	@EventHandler
	public void playerInteract(PlayerInteractEvent event) {
		if(event.hasItem()) {
			String vinnilaID = event.getItem().getType().name() + ":" + event.getItem().getDurability();
			if(Gadget.isVanillaItemGadget(vinnilaID)) {
				Gadget.getGadgetFromVanillaID(vinnilaID).onPlayerInteractEvent(event);
			}
		}
	}
	
	@EventHandler
	public void entityDamagerByEntity(EntityDamageByEntityEvent event) {
		if(event.getDamager().getType() == EntityType.PLAYER) {
			String vanillaID = ((Player)event.getDamager()).getItemInHand().getType().name() + ":" + ((Player)event.getDamager()).getItemInHand().getDurability();
			if(Gadget.isVanillaItemGadget(vanillaID)) {
				Gadget.getGadgetFromVanillaID(vanillaID).onEntityDamageByEntityBeingDamager(event);
			}
				
			
		}
	}
}
