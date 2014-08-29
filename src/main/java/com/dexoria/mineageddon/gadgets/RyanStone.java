package com.dexoria.mineageddon.gadgets;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class RyanStone extends Gadget {

	public RyanStone() {
		super("RYANSTONE", Material.EMERALD);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onEntityDamageByEntityBeingDamager(EntityDamageByEntityEvent event) {
		super.onEntityDamageByEntityBeingDamager(event);
		if(event.getCause() == DamageCause.ENTITY_ATTACK) {
			if(event.getEntity() instanceof LivingEntity) {
				((LivingEntity)event.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.SLOW,60,20));
			}
			
		}
	}
	
	@Override
	public ItemStack createItem() {
		ItemStack item = new ItemStack(Material.EMERALD);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.DARK_PURPLE + "Ryan Stone");
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GREEN + "Hit an player with this stone to");
		lore.add(ChatColor.GREEN + "hard code its position! (Make the");
		lore.add(ChatColor.GREEN + "player unable to move for 3 seconds)");
		meta.setLore(lore);
		item.setItemMeta(meta);
		item.setAmount(1);
		return item;
	}
}
