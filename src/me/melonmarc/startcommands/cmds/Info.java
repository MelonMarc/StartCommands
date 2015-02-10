package me.melonmarc.startcommands.cmds;

import me.melonmarc.startcommands.MessageManager;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class Info extends SubCommand {
	
	public void onCommand(CommandSender sender, String[] args) {
		if (!sender.hasPermission("startcommands.info")) {
			MessageManager.getInstance().severe(sender, "You are not permitted to do this.");
			return;
		}
		sender.sendMessage(ChatColor.GOLD + "StartCommands Info:");
		sender.sendMessage(ChatColor.GRAY + "Version: " + ChatColor.WHITE + "1.0-pre 1");
		sender.sendMessage(ChatColor.GRAY + "Author: " + ChatColor.WHITE + "MelonMarc");
		sender.sendMessage(ChatColor.GRAY + "Description: " + ChatColor.WHITE + "Automatically run usefull commands!");
	}
		
	public String name() {
			return "info";
	}
		
	public String info() {
			return "StartCommands info.";
	}
		
	public String[] aliases() {
		return new String[] { };
	}

}
