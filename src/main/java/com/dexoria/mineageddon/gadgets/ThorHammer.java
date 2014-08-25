package com.dexoria.mineageddon.gadgets;

import org.bukkit.Material;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class ThorHammer extends Gadget {

	public ThorHammer() {
		super("Thor's Hammer", Material.DIAMOND_AXE);
	}
	
	@Override
	public boolean onPlayerInteractEvent(PlayerInteractEvent event) {
		if(event.getAction() == Action.LEFT_CLICK_BLOCK) {
			event.getPlayer().getWorld().createExplosion(event.getClickedBlock().getLocation(), 2);
		}
		
		return true;
	}
}
