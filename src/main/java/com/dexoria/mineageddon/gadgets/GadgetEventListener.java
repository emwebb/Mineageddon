package com.dexoria.mineageddon.gadgets;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class GadgetEventListener implements Listener{
	@EventHandler
	public boolean playerInteract(PlayerInteractEvent event) {
		if(event.hasItem()) {
			String vinnilaID = event.getItem().getType().name() + ":" + event.getItem().getDurability();
			if(Gadget.isVanillaItemGadget(vinnilaID)) {
				return Gadget.getGadgetFromVanillaID(vinnilaID).onPlayerInteractEvent(event);
			}
		}
		return true;
	}
}
