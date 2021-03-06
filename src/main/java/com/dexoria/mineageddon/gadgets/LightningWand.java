package com.dexoria.mineageddon.gadgets;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class LightningWand extends Gadget {

	public LightningWand() {
		super("LIGHTNINGWAND", Material.STICK);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onPlayerInteractEvent(PlayerInteractEvent event) {
		super.onPlayerInteractEvent(event);
		if (event.getAction() == Action.LEFT_CLICK_AIR
				|| event.getAction() == Action.LEFT_CLICK_BLOCK) {
			@SuppressWarnings("deprecation")
			Block lookingAt = event.getPlayer().getTargetBlock(null, 120);
			if (lookingAt != null) {
				Location loc = lookingAt.getLocation();
				LightningStrike ls = loc.getWorld().strikeLightningEffect(loc);
				List<Entity> entities = ls.getNearbyEntities(3, 3, 3);

				for (Entity e : entities) {
					if (e instanceof LivingEntity) {
						((LivingEntity) e).damage(1.3, event.getPlayer());
						e.setFireTicks(80);
					}
				}

			}
			if (event.getPlayer().getFoodLevel() > 0) {
				event.getPlayer().setFoodLevel(
						event.getPlayer().getFoodLevel() - 2);
			} else {
				event.getPlayer().damage(1.5);
			}

		}
	}

	@Override
	public void whilePlayerHoldingGadget(Player player, int periodTime) {
		super.whilePlayerHoldingGadget(player, periodTime);
		player.removePotionEffect(PotionEffectType.SLOW_DIGGING);
		player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING,
				50, 10));
	}

	@Override
	public ItemStack createItem() {
		ItemStack item = new ItemStack(Material.STICK);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GOLD + "Lightning Wand");
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GREEN + "This wand summons lightning!");
		meta.setLore(lore);
		item.setItemMeta(meta);
		item.setAmount(1);
		return item;
	}
}
