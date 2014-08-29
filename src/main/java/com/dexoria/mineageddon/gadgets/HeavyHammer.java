package com.dexoria.mineageddon.gadgets;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import com.dexoria.mineageddon.perks.Rocket;
import com.dexoria.mineageddon.utils.Schedulable;
import com.dexoria.mineageddon.utils.WorldUtils;

public class HeavyHammer extends Gadget {

	public HeavyHammer() {
		super("HEAVYHAMMER", Material.DIAMOND_AXE);
	}
	
	@Override
	public void onPlayerInteractEvent(PlayerInteractEvent event) {
		super.onPlayerInteractEvent(event);
		if(event.getAction() == Action.LEFT_CLICK_BLOCK) {
			WorldUtils.createExplosion(event.getPlayer().getWorld(), event.getPlayer(), event.getClickedBlock().getLocation(), 5, false, true);
			event.getPlayer().damage(10.0, event.getPlayer());
		}
	}
	
	@Override
	public void onEntityDamageByEntityBeingDamager(EntityDamageByEntityEvent event) {
		super.onEntityDamageByEntityBeingDamager(event);
		if(event.getCause() == DamageCause.ENTITY_ATTACK ) {
			if(event.getEntity().isOnGround()){
				Rocket rocketeer = new Rocket(event.getEntity(),new Vector(0,1,0),1);
	    		Schedulable.scheduleSyncRepeatingTask(rocketeer,1);
			} else {
				Vector vec = new Vector(Math.cos(Math.toRadians(event.getDamager().getLocation().getYaw())+(Math.PI/2)),0,Math.sin(Math.toRadians(event.getDamager().getLocation().getYaw())+(Math.PI/2)));
				Rocket rocketeer = new Rocket(event.getEntity(), vec,1);
	    		Schedulable.scheduleSyncRepeatingTask(rocketeer,1);
			}
			
		}
		
	}
	
	@Override
	public void whilePlayerHoldingGadget(Player player, int periodTime) {
		super.whilePlayerHoldingGadget(player, periodTime);
		player.removePotionEffect(PotionEffectType.SLOW_DIGGING);
		player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 50, 10));
	}
	
	@Override
	public ItemStack createItem() {
		ItemStack item = new ItemStack(Material.DIAMOND_AXE);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.AQUA + "Heavy Hammer");
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GREEN + "Hit player to propel player.");
		lore.add(ChatColor.GREEN + "Hit block to create explosion.");
		meta.setLore(lore);
		item.setItemMeta(meta);
		item.setAmount(1);
		return item;
	}
	
}
