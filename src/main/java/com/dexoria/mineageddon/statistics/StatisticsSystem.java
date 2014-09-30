package com.dexoria.mineageddon.statistics;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;

import com.dexoria.mineageddon.Mineageddon;
import com.dexoria.mineageddon.core.SubSystem;
import com.dexoria.mineageddon.gadgets.Gadget;

public class StatisticsSystem implements SubSystem{
	
	private StatisticsSystemListener ssl;
	
	public void onEnable() {
		for(String gadgetName : Gadget.getAllGadget().keySet()) {
			addGadgetIfMissing(gadgetName);
		}
		ssl = new StatisticsSystemListener();
		Bukkit.getPluginManager().registerEvents(ssl, Mineageddon.getInstance());
		
	}

	public void increaseDiedWith(final String gadgetName) {
		Bukkit.getScheduler().runTaskAsynchronously(Mineageddon.getInstance(), new Runnable(){
			@Override
			public void run() {
				try {
					Mineageddon.getMySQL().updateSQL("UPDATE mgStatistics SET diedWith = diedWith + 1 WHERE Gadget = '" + gadgetName + "';");
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});


	}
	public void increaseKilledWith(final String gadgetName) {
		Bukkit.getScheduler().runTaskAsynchronously(Mineageddon.getInstance(), new Runnable(){
			@Override
			public void run() {
				try {
					Mineageddon.getMySQL().updateSQL("UPDATE mgStatistics SET killedWith = killedWith + 1 WHERE Gadget = '" + gadgetName + "';");
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});


	}
	
	public void increaseSuicide(final String gadgetName) {
		Bukkit.getScheduler().runTaskAsynchronously(Mineageddon.getInstance(), new Runnable(){
			@Override
			public void run() {
				try {
					Mineageddon.getMySQL().updateSQL("UPDATE mgStatistics SET suicide = suicide + 1 WHERE Gadget = '" + gadgetName + "';");
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});


	}
	
	public boolean gadgetExists(String gadget) {
		try {
			ResultSet res = Mineageddon.getMySQL().querySQL("SELECT * FROM `mcph196778`.`mgStatistics` WHERE Gadget = '" + gadget + "';");

			if(!res.next()) {
				return false;
			} else {
				return true;
			}

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	public void addGadget(String gadget){
		try {

			Mineageddon.getMySQL().updateSQL("INSERT INTO `mcph196778`.`mgStatistics` (`ID`, `Gadget`, `diedWith`, `killedWith`, 'suicide') VALUES (NULL, '" + gadget + "', '0', '0', '0');");
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addGadgetIfMissing(final String gadget) {
		Bukkit.getScheduler().runTaskAsynchronously(Mineageddon.getInstance(), new Runnable(){
			@Override
			public void run() {
				if(!gadgetExists(gadget)) {
					addGadget(gadget);
				}
			}
		});
	}

	public void onDisable() {
		HandlerList.unregisterAll(ssl);
		
	}
}
