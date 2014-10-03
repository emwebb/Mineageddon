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

public class DemonSythe extends Gadget {

	public DemonSythe() {
		super("DEMONSYTHE", Material.DIAMOND_HOE);
	}

	@Override
	public void onEntityDamageByEntityBeingDamager(
			EntityDamageByEntityEvent event) {
		super.onEntityDamageByEntityBeingDamager(event);

		if (event.getCause() == DamageCause.ENTITY_ATTACK) {
			event.setDamage(0.0);
			if (event.getEntity() instanceof LivingEntity) {
				((LivingEntity) event.getEntity())
						.addPotionEffect(new PotionEffect(
								PotionEffectType.POISON, 100, 1));

			}
		}

	}

	@Override
	public ItemStack createItem() {
		ItemStack item = new ItemStack(Material.DIAMOND_HOE);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.RED + "Demon Sythe");
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GREEN + "Does no damage to players");
		lore.add(ChatColor.GREEN + "but does poison them!");
		meta.setLore(lore);
		item.setItemMeta(meta);
		item.setAmount(1);
		return item;
	}

}
