package com.dexoria.mineageddon.gadgets;

import net.minecraft.server.v1_7_R4.Entity;
import net.minecraft.server.v1_7_R4.Explosion;
import net.minecraft.server.v1_7_R4.World;

import org.bukkit.Material;
import org.bukkit.entity.Explosive;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.dexoria.mineageddon.utils.WorldUtils;

public class ThorHammer extends Gadget {

	public ThorHammer() {
		super("Thor's Hammer", Material.DIAMOND_AXE);
	}
	
	@Override
	public boolean onPlayerInteractEvent(PlayerInteractEvent event) {
		if(event.getAction() == Action.LEFT_CLICK_BLOCK) {
			WorldUtils.createExplosion(event.getPlayer().getWorld(), event.getPlayer(), event.getClickedBlock().getLocation(), 5, false, true);
		}
		
		return true;
	}
}
