package com.dexoria.mineageddon.utils;

import net.minecraft.server.v1_7_R1.MobEffect;
import net.minecraft.server.v1_7_R1.MobEffectList;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;

public class WorldUtils {
	public static void createExplosion(World world,Entity entity, Location location, float power, boolean doFire, boolean doBlockDamage) {
		((org.bukkit.craftbukkit.v1_7_R1.CraftWorld)world).getHandle().createExplosion(((org.bukkit.craftbukkit.v1_7_R1.entity.CraftEntity) entity).getHandle(), location.getX(),location.getY(),location.getZ(),power,doFire,doBlockDamage);
	}
	
}
