package com.dexoria.mineageddon.gadgets;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SpeedSword extends Gadget {

	public SpeedSword() {
		super("SPEEDSWORD", Material.STONE_SWORD);
	}
	
	@Override
	public void whilePlayerHoldingGadget(Player player, int periodTime) {
		super.whilePlayerHoldingGadget(player, periodTime);
		player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 15, 2),true);
		player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 15, 2),true);
		player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 15, 2),true);
		
	}

	@Override
	public ItemStack createItem() {
		ItemStack item = new ItemStack(Material.STONE_SWORD);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.BLACK + "Speed Sword");
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GREEN + "A normal sword that makes you move");
		lore.add(ChatColor.GREEN + "fast!");
		meta.setLore(lore);
		item.setItemMeta(meta);
		item.setAmount(1);
		
		return item;
	}

}
