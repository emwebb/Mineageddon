package com.dexoria.mineageddon.gadgets;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class IronPotion extends Gadget{

	public IronPotion() {
		super("IRONPOTION", Material.IRON_INGOT);
	}
	
	@Override
	public void onPlayerInteractEvent(PlayerInteractEvent event) {
		super.onPlayerInteractEvent(event);
		
		event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 600, 2),true);
		event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 600, 2),true);
			
		event.getItem().setAmount(event.getItem().getAmount() - 1);
	}
	
	@Override
	public ItemStack createItem() {
		ItemStack item = new ItemStack(Material.IRON_INGOT);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GRAY + "Iron Potion");
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GREEN + "Drinking this will make you as tough");
		lore.add(ChatColor.GREEN + "as iron for a short while. Unfortunately");
		lore.add(ChatColor.GREEN + "it will also make you as heavy as iron.");
		meta.setLore(lore);
		item.setItemMeta(meta);
		item.setAmount(5);
		return item;
	}
}
