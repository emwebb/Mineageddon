package com.dexoria.mineageddon.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.dexoria.mineageddon.Mineageddon;
import com.dexoria.mineageddon.gadgets.Gadget;

public class CommandHandler implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if(cmd.getName().equalsIgnoreCase("mineageddon") || cmd.getName().equalsIgnoreCase("mg")){
			if(args.length == 0) {
				help(sender, cmd, label, args);
				return true;
			}
			switch(args[0].toLowerCase()) {
			case "world" :
				world(sender, cmd, label, args);
				return true;
			case "give" :
				give(sender, cmd, label, args);
				return true;
			case "score" :
				score(sender, cmd, label, args);
				return true;
			case "list" :
				list(sender, cmd, label, args);
				return true;
			case "join" :
				join(sender, cmd, label, args);
				return true;
				
			}
			return true;
		}
		return false;
	}
	
	private void join(CommandSender sender, Command cmd, String label,
			String[] args) {
		if(sender instanceof Player) {
			Mineageddon.getGameSystem().setupPlayer((Player) sender);
		} else {
			sender.sendMessage("This can only be exacuted by players!");
		}
		
	}

	private void list(CommandSender sender, Command cmd, String label,
			String[] args) {
		sender.sendMessage("Gadget List:");
		
			for(String s : Gadget.getAllGadget().keySet()) {
			sender.sendMessage(s);
		}
	}
	
	
	

	private void score(CommandSender sender, Command cmd, String label,
			String[] args) {
		if(sender instanceof Player) {
			sender.sendMessage("Your score is: " + Mineageddon.getScoreSystem().getPlayerScore(((Player) sender).getUniqueId().toString()));
		}
		
	}

	private void give(CommandSender sender, Command cmd, String label,
			String[] args) {
		if(!sender.hasPermission("mineageddon.admin")) {
			sender.sendMessage("Insufficient permission");
			return;
		}
		
		if(args.length != 3) {
			sender.sendMessage("Usage: /mineageddon give [player] [gadget name]");
			
			return;
		}
		@SuppressWarnings("deprecation")
		Player player = Bukkit.getPlayer(args[1]);
		if(!player.isOnline()) {
			sender.sendMessage("Player " + args[1] + " is not online");
			
			return;
		}
		
		if(!Gadget.isValidGadgetName(args[2])) {
			sender.sendMessage(args[2] + " is not a valid gadget name");
			return;
		}
		
		player.getInventory().addItem(Gadget.getGadget(args[2]).createItem());
		
	}

	private void world(CommandSender sender, Command cmd, String label,
			String[] args) {
		if(args.length < 2) {
			sender.sendMessage("Usage: /mineageddon world [add/remove/list] <world name>");
			return;
		}
		switch(args[1].toLowerCase()) {
			case "add":
				if(!sender.hasPermission("mineageddon.admin")) {
					sender.sendMessage("Insufficient permission");
					return;
				}
				if(args.length < 3){
					sender.sendMessage("Usage: /mineageddon world add <world name>");
					return;
				}
				try {
				Mineageddon.getConfigStaticly().addAllowedWorld(args[2]);
				} catch (IllegalArgumentException e) {
					sender.sendMessage("Error:" + e.getLocalizedMessage());
				}
				break;
			case "remove":
				if(!sender.hasPermission("mineageddon.admin")) {
					sender.sendMessage("Insufficient permission");
					return;
				}
				if(args.length < 3){
					sender.sendMessage("Usage: /mineageddon world remove <world name>");
					return;
				}
				try {
				Mineageddon.getConfigStaticly().removeAllowedWorld(args[2]);
				} catch (IllegalArgumentException e) {
					sender.sendMessage("Error:" + e.getLocalizedMessage());
				}
				break;
			case "list":
				sender.sendMessage("Allowed Worlds: ");
				for(String world : Mineageddon.getConfigStaticly().getAllowedWorlds()) {
					sender.sendMessage(world);
				}
				break;
			default:
				sender.sendMessage("Usage: /mineageddon world [add/remove/list] <world name>");
				
				break;
			
		}
		
	}

	private void help(CommandSender sender, Command cmd, String label,
			String[] args) {
		
	}

}
