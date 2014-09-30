package com.dexoria.mineageddon.scoresystem;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.dexoria.mineageddon.Mineageddon;
import com.dexoria.mineageddon.core.SubSystem;

public class ScoreSystem implements SubSystem{
	public void onEnable() {
		
	
		
	}
	
	public void onDisable() {
		
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
	
	public void removePercentageOfScore(final String playerUID, final float decimalPercentage) {
		Bukkit.getScheduler().runTaskAsynchronously(Mineageddon.getInstance(), new Runnable(){
			@Override
			public void run() {
				int loosingPlayerScore = getPlayerScore(playerUID);
				int loss = (int) (loosingPlayerScore * decimalPercentage);
				setPlayerScore(playerUID, loosingPlayerScore - loss);
			}
		});
	}
	
	public void removePercentageOfPointsAndTransferToPlayersOnTheServer(final String playerUID, final float decimalPercentage) {
		Bukkit.getScheduler().runTaskAsynchronously(Mineageddon.getInstance(), new Runnable(){
			@Override
			public void run() {
				int loosingPlayerScore = getPlayerScore(playerUID);
				int loss = (int) (loosingPlayerScore * decimalPercentage);
				setPlayerScore(playerUID, loosingPlayerScore - loss);
				int numOfPlayers = 0;
				for(Player player : Bukkit.getOnlinePlayers()) {
					if(Mineageddon.getConfigStaticly().isAllowedWorld(player.getWorld().getName()) && player.getUniqueId().toString() != playerUID){
						numOfPlayers ++;
					}
				}
				
				int gain = loss / numOfPlayers;
				for(Player player : Bukkit.getOnlinePlayers()) {
					if(Mineageddon.getConfigStaticly().isAllowedWorld(player.getWorld().getName()) && player.getUniqueId().toString() != playerUID){
						int playerSocre = getPlayerScore(player.getUniqueId().toString());
						int newScore =  playerSocre + gain;
						setPlayerScore(player.getUniqueId().toString(),newScore);
					}
				}
				
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
