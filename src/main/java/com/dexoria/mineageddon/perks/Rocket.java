package com.dexoria.mineageddon.perks;

import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

import com.dexoria.mineageddon.utils.Schedulable;


public class Rocket extends Schedulable{

	
	private Entity entity;
	private Vector vec;
	private int ticks, duration;
	
	public Rocket(Entity entity, Vector vec, int duration)
	{
		this.duration = duration;
		this.vec = vec;
		this.entity = entity;
	}
	

	@Override
	protected void tick()
	{
		if(ticks++ > duration)
		{
			stop();
			return;
		}
		
		entity.setVelocity(vec);
		
	}

	public Entity getEntity()
	{
		return entity;
	}
	
	public Vector getVector()
	{
		return vec.clone();
	}
	
	public void setVector(Vector vector)
	{
		vec = vector;
	}
}
