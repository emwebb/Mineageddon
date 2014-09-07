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

public class DemonicWardStone extends Gadget {

	public DemonicWardStone() {
		super("DEMONICWARDSTONE", Material.GOLD_INGOT);
		// TODO Auto-generated constructor stub
	}
	

	
	@Override
	public void whilePlayerHoldingGadget(Player player, int periodTime) {
		super.whilePlayerHoldingGadget(player, periodTime);
		player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 20, 90),true);
		player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20, 3),true);
		player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20, 1),true);
		player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20, 4),true);
		player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 20, 3),true);
		player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 20, 3),true);
	}
	
	@Override
	public ItemStack createItem() {
		ItemStack item = new ItemStack(Material.GOLD_INGOT);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.RED + "Demonic Ward Stone");
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GREEN + "When holding this stone you turn into a");
		lore.add(ChatColor.GREEN + "demonic creature.");
		meta.setLore(lore);
		item.setItemMeta(meta);
		item.setAmount(1);
		return item;
	}

}
