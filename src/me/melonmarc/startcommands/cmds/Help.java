package me.melonmarc.startcommands.cmds;

import me.melonmarc.startcommands.MessageManager;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class Help extends SubCommand {
	
	public void onCommand(CommandSender sender, String[] args) {
		if (!sender.hasPermission("startcommands.help")) {
			MessageManager.getInstance().severe(sender, "You are not permitted to do this.");
			return;
		}
		sender.sendMessage(ChatColor.GRAY + "StartCommands help:");
		if (sender.hasPermission("startcommands.run")) {
			sender.sendMessage(ChatColor.GOLD + "/startcommands: " + ChatColor.WHITE + "Run your startcommands");
		}
		if (sender.hasPermission("startcommands.add")) {
			sender.sendMessage(ChatColor.GOLD + "/startcommands <add> <command>: " + ChatColor.WHITE + "Add a command");
		}
		if (sender.hasPermission("startcommands.addp")) {
			sender.sendMessage(ChatColor.GOLD + "/startcommands <addp> <player> <command>: " + ChatColor.WHITE + "Add a command to another player");
		}
		if (sender.hasPermission("startcommands.help")) {
			sender.sendMessage(ChatColor.GOLD + "/startcommands <help>: " + ChatColor.WHITE + "StartCommands help");
		}
		if (sender.hasPermission("startcommands.info")) {
			sender.sendMessage(ChatColor.GOLD + "/startcommands <info>: " + ChatColor.WHITE + "StartCommands information");
		}
		if (sender.hasPermission("startcommands.list")) {
			sender.sendMessage(ChatColor.GOLD + "/startcommands <list>: " + ChatColor.WHITE + "A list of your startcommands");
		}
		if (sender.hasPermission("startcommands.relaod")) {
			sender.sendMessage(ChatColor.GOLD + "/startcommands <reload>: " + ChatColor.WHITE + "Reload the StartCommands config");
		}
		if (sender.hasPermission("startcommands.remove")) {
			sender.sendMessage(ChatColor.GOLD + "/startcommands <remove> <command>: " + ChatColor.WHITE + "Remove a command");
		}
		if (sender.hasPermission("startcommands.removep")) {
			sender.sendMessage(ChatColor.GOLD + "/startcommands <removep> <player> <command>: " + ChatColor.WHITE + "Remove a command from another player");
		}
		
	}
	
	public String name() {
		return "help";
	}
	
	public String info() {
		return "StartCommands help.";
	}
	
	public String[] aliases() {
		return new String[] { "?" };
	}
}
