package com.dexoria.mineageddon.gadgets;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
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

public class PandorasBox extends Gadget {

	public PandorasBox() {
		super("PANDORASBOX", Material.COAL);
	}
	
	@Override
	public void onPlayerInteractEvent(PlayerInteractEvent event) {
		super.onPlayerInteractEvent(event);
		if(event.getAction() != Action.PHYSICAL) {
			pandora(event.getPlayer());
		}
	}
	
	private void pandora(Player user) {
		for(Entity others : user.getNearbyEntities(2, 2, 2)) {
			if(others instanceof LivingEntity) {
				((LivingEntity) others).damage(12.0, user);
			}
		}
		
		user.damage((double)Integer.MAX_VALUE); //INFINITE DAMAGE!
			
	}
	
	
	
	@Override
	public ItemStack createItem() {
		ItemStack item = new ItemStack(Material.COAL);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "PANDORA'S BOX");
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GREEN + "Deals 6 hearts of damage to");
		lore.add(ChatColor.GREEN + "everyone near you and kills you.");
		meta.setLore(lore);
		item.setItemMeta(meta);
		item.setAmount(1);
		return item;
	}
	
}
