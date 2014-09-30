package com.dexoria.mineageddon.utils;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;

public class WorldUtils {
	public static void createExplosion(World world,Entity entity, Location location, float power, boolean doFire, boolean doBlockDamage) {
		((org.bukkit.craftbukkit.v1_7_R3.CraftWorld)world).getHandle().createExplosion(((org.bukkit.craftbukkit.v1_7_R3.entity.CraftEntity) entity).getHandle(), location.getX(),location.getY(),location.getZ(),power,doFire,doBlockDamage);
	}
	
}
