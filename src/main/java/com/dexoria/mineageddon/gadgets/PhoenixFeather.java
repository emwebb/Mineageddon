package com.dexoria.mineageddon.gadgets;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PhoenixFeather extends Gadget {

	public PhoenixFeather() {
		super("PHOENIXFEATHER", Material.FEATHER);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onEntityDamageByEntityBeingDamager(EntityDamageByEntityEvent event) {
		if(event.getCause() == DamageCause.ENTITY_ATTACK) {
			event.getEntity().setFireTicks(100);
		}
	}
	
	@Override
	public void whilePlayerHoldingGadget(Player player, int periodTime) {
		super.whilePlayerHoldingGadget(player, periodTime);
		player.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
		player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 50, 10));
	}
	
	@Override
	public ItemStack createItem() {
		ItemStack item = new ItemStack(Material.FEATHER);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.RED + "Phoenix Feather");
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GREEN + "Hit entity to set on fire.");
		lore.add(ChatColor.GREEN + "Hold for fire resistance.");
		meta.setLore(lore);
		item.setItemMeta(meta);
		item.setAmount(1);
		return item;
	}

}
