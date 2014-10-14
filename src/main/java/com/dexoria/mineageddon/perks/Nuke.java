package com.dexoria.mineageddon.perks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import com.dexoria.mineageddon.Mineageddon;
import com.dexoria.mineageddon.utils.Schedulable;

public class Nuke extends Schedulable {

	private int ticks, duration;

	public Nuke(int duration) {
		this.duration = duration;
	}

	@Override
	protected void tick() {
		if (ticks++ > duration) {
			stop();
			return;
		}
		
		for(Player player : Bukkit.getOnlinePlayers()) {
			if (!Mineageddon.getWorldSystem().isMgWorld(player.getWorld().getName()))
				return;
			player.getWorld().createExplosion(player.getLocation(), 5);
		}

	}

	
}
