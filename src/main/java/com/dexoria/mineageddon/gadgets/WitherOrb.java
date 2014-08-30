package com.dexoria.mineageddon.gadgets;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class WitherOrb extends Gadget{

	public WitherOrb() {
		super("WITHERORB", Material.NETHER_STAR);
	}
	
	@Override
	public void whilePlayerHoldingGadget(Player player, int periodTime) {
		super.whilePlayerHoldingGadget(player, periodTime);
		for(Entity entity : player.getNearbyEntities(2,2,2)) {
			if(entity instanceof LivingEntity ) {
				((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 9, 1));
				
				((LivingEntity) entity).damage(1.0, player);
			}
		}
		player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 20, 9),true);
/*		if(player.getHealth() > 1.0) {

			player.damage(1.0);
		}*/
			
	}
	
	@Override
	public ItemStack createItem() {
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.BLACK + "Wither Orb");
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GREEN + "While holding this all players");
		lore.add(ChatColor.GREEN + "with in 2 blocks of you will wither");
		lore.add(ChatColor.GREEN + "but also gives you poison!");
		meta.setLore(lore);
		item.setItemMeta(meta);
		item.setAmount(1);
		return item;
	}
}
