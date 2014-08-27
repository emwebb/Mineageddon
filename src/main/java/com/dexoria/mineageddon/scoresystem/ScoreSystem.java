package com.dexoria.mineageddon.scoresystem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.Bukkit;

import com.dexoria.mineageddon.Mineageddon;
import com.dexoria.mineageddon.mysql.MySQL;

public class ScoreSystem {
	private MySQL scoreSQL;
	private Connection connection;
	public void onEnable() {
		scoreSQL = new MySQL(Mineageddon.getInstance(),
				Mineageddon.getConfigStaticly().getDBHostName(),
				Mineageddon.getConfigStaticly().getDBPort(),
				Mineageddon.getConfigStaticly().getDBDatabase(),
				Mineageddon.getConfigStaticly().getDBUsername(),
				Mineageddon.getConfigStaticly().getDBPassword());

		try {
			connection = scoreSQL.openConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void onDisable() {
		try {
			connection.close();

			scoreSQL.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean playerExists(String playerUUID) {
		Statement statement = null;
		try {
			ResultSet res;
			statement = connection.createStatement();
			res = statement.executeQuery("SELECT * FROM playerScore WHERE playerUUID = '" + playerUUID + "';");
			
			if(!res.next()) {
				return false;
			} else {
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	public void addPlayer(String playerUUID){
		try {

			Statement statement = connection.createStatement();
			statement.execute("INSERT INTO playerScore (`ID`, `playerUUID`, `score`) VALUES (NULL, '" + playerUUID + "', '2000');");
		} catch (SQLException e) {
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
			ResultSet res;
			Statement statement = connection.createStatement();
			res = statement.executeQuery("SELECT * FROM playerScore WHERE playerUUID = '" + playerUUID + "';");
			res.next();
			return res.getInt("score");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public void setPlayerScore(String playerUUID, int score) {

		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("UPDATE playerScore SET score='" + score + "' WHERE playerUUID=" + playerUUID + ";");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
