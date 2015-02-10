package me.melonmarc.startcommands.cmds;

import me.melonmarc.startcommands.MessageManager;
import me.melonmarc.startcommands.SettingsManager;

import org.bukkit.command.CommandSender;

public class Reload extends SubCommand {
	
	SettingsManager settings = SettingsManager.getInstance();
	
	public void onCommand(CommandSender sender, String[] args) {
		if (!sender.hasPermission("startcommands.relaod")) {
			MessageManager.getInstance().severe(sender, "You are not permitted to do this.");
			return;
		}
		settings.reloadConfig();
		MessageManager.getInstance().good(sender, "Reloaded the StartCommands config.");
	}
	
	public String name() {
		return "reload";
	}
	
	public String info() {
		return "Reload the StartCommands config.";
	}
	
	public String[] aliases() {
		return new String[] { "r" };
	}
}
