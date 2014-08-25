package com.dexoria.mineageddon.utils;

import org.bukkit.Bukkit;

import com.dexoria.mineageddon.Mineageddon;

public abstract class Schedulable implements Runnable{

	private int id = -1;
	private boolean finished = false;
	
	public static void scheduleSyncRepeatingTask(Schedulable task, int period) throws IllegalStateException
	{
		
			task.setId(Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Mineageddon.getInstance(), task, 0, period));
		
	}
	
	private void setId(int id)
	{
		this.id = id;
	}
	
	private void cancel()
	{
		if(id != -1)
		{
			Bukkit.getServer().getScheduler().cancelTask(id);
		}
	}
	
	public void stop()
	{
		finished = true;
	}
	
	public  void run()
	{
		if(!finished)
			tick();
		if(finished)
			cancel();
	}
	protected abstract void tick();
}
