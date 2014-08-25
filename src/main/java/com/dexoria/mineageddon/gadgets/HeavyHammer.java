package com.dexoria.mineageddon.gadgets;

import org.bukkit.Material;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import com.dexoria.mineageddon.perks.Rocket;
import com.dexoria.mineageddon.utils.Schedulable;
import com.dexoria.mineageddon.utils.WorldUtils;

public class HeavyHammer extends Gadget {

	public HeavyHammer() {
		super("Heavy Hammer", Material.DIAMOND_AXE);
	}
	
	@Override
	public boolean onPlayerInteractEvent(PlayerInteractEvent event) {
		super.onPlayerInteractEvent(event);
		if(event.getAction() == Action.LEFT_CLICK_BLOCK) {
			WorldUtils.createExplosion(event.getPlayer().getWorld(), event.getPlayer(), event.getClickedBlock().getLocation(), 5, false, true);
			event.getPlayer().damage(10.0, event.getPlayer());
		}
		
		return true;
	}
	
	@Override
	public boolean onEntityDamageByEntityBeingDamager(EntityDamageByEntityEvent event) {
		super.onEntityDamageByEntityBeingDamager(event);
		if(event.getCause() == DamageCause.ENTITY_ATTACK ) {
			if(event.getEntity().isOnGround()){
				Rocket rocketeer = new Rocket(event.getEntity(),new Vector(0,1,0),1);
	    		Schedulable.scheduleSyncRepeatingTask(rocketeer,1);
			} else {
				Vector vec = new Vector(Math.sin(event.getDamager().getLocation().getYaw() + Math.PI),0,Math.cos(event.getDamager().getLocation().getYaw() + Math.PI));
				Rocket rocketeer = new Rocket(event.getEntity(), vec,1);
	    		Schedulable.scheduleSyncRepeatingTask(rocketeer,1);
			}
			
		}
		return true;
		
	}
}
