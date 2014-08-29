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

public class OmniSword extends Gadget {

	public OmniSword() {
		super("OMNISWORD", Material.WOOD_SWORD);
	}
	
	@Override
	public void whilePlayerHoldingGadget(Player player, int periodTime) {
		super.whilePlayerHoldingGadget(player, periodTime);
		player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 15, 2),true);
		player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 15, 2),true);
		
	}

	@Override
	public ItemStack createItem() {
		ItemStack item = new ItemStack(Material.WOOD_SWORD);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.DARK_RED + "Omni Sword");
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GREEN + "A normal sword that makes you take");
		lore.add(ChatColor.GREEN + "less damage and makes you jump high!");
		meta.setLore(lore);
		item.setItemMeta(meta);
		item.setAmount(1);
		
		return item;
	}

}
