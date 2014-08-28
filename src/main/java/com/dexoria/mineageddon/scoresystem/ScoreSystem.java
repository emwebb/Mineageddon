package com.dexoria.mineageddon.scoresystem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.Bukkit;

import com.dexoria.mineageddon.Mineageddon;
import com.dexoria.mineageddon.mysql.MySQL;

public class ScoreSystem {
	public void onEnable() {
		
	
		
	}
	
	public void onDisable() {
		try {
			Mineageddon.getMySQL().closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean playerExists(String playerUUID) {
		try {
			ResultSet res = Mineageddon.getMySQL().querySQL("SELECT * FROM playerScore WHERE playerUUID = '" + playerUUID + "';");
			
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
	
	public void addPlayer(String playerUUID){
		try {

			Mineageddon.getMySQL().updateSQL("INSERT INTO playerScore (`ID`, `playerUUID`, `score`) VALUES (NULL, '" + playerUUID + "', '2000');");
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addPlayerIfMissing(final String playerUUID) {
		Bukkit.getScheduler().runTaskAsynchronously(Mineageddon.getInstance(), new Runnable(){
			@Override
			public void run() {
				if(!Mineageddon.getScoreSystem().playerExists(playerUUID)) {
					Mineageddon.getScoreSystem().addPlayer(playerUUID);
				}
			}
		});
	}
	
	public void transferScore(final String playerUUIDGet, final String playerUUIDLose, final float decimalPercentage) {
		Bukkit.getScheduler().runTaskAsynchronously(Mineageddon.getInstance(), new Runnable(){
			@Override
			public void run() {
				int loosingPlayerScore = getPlayerScore(playerUUIDLose);
				int loss = (int) (loosingPlayerScore * decimalPercentage);
				setPlayerScore(playerUUIDLose, loosingPlayerScore - loss);
				int winningPlayerScore = getPlayerScore(playerUUIDGet);
				setPlayerScore(playerUUIDGet, winningPlayerScore + loss);
			}
		});
	}
	
	public int getPlayerScore(String playerUUID) {
		try {
			ResultSet res = Mineageddon.getMySQL().querySQL("SELECT * FROM playerScore WHERE playerUUID = '" + playerUUID + "';");
			res.next();
			return res.getInt("score");
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public void setPlayerScore(String playerUUID, int score) {

		try {
			Mineageddon.getMySQL().updateSQL("UPDATE playerScore SET score='" + score + "' WHERE playerUUID='" + playerUUID + "';");
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
