package me.melonmarc.startcommands.cmds;

import java.util.ArrayList;
import java.util.List;

import me.melonmarc.startcommands.MessageManager;
import me.melonmarc.startcommands.SettingsManager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Addp extends SubCommand {
	
	SettingsManager settings = SettingsManager.getInstance();
	
	public void onCommand(CommandSender sender, String[] args) {
		if (!sender.hasPermission("startcommands.addp")) {
			MessageManager.getInstance().severe(sender, "You are not permitted to do this.");
			return;
		}
		if (args.length == 0) {
			MessageManager.getInstance().severe(sender, "Usage: /<startcommands> <addp> <player> <command>");
			return;
		}
		Player target = Bukkit.getServer().getPlayer(args[0]);
		if (target == null) {
			MessageManager.getInstance().severe(sender, "Could not find player " + args[0]);
			return;
		}
		
		if (args.length == 1) {
			MessageManager.getInstance().severe(sender, "Usage: /<startcommands> <addp> <player> <command>");
			return;
		}
		
		StringBuilder str = new StringBuilder();
		for (int i = 1; i < args.length; i++) {
			str.append(args[i] + " ");
		}
		String com = str.toString();
		
		String uuid = target.getUniqueId().toString();
		
		if (!settings.getConfig().contains(uuid)) settings.getConfig().set(uuid + ".commands", new ArrayList<String>());
		
		List<String> list = settings.getConfig().getStringList(uuid + ".commands");
		list.add(com);
		settings.getConfig().set(uuid + ".commands", list);
		settings.saveConfig();
		settings.reloadConfig();
		MessageManager.getInstance().good(sender, "Added " + ChatColor.RED + "/" + com + ChatColor.GOLD + "to " + target.getName() + "'s startcommands.");
		MessageManager.getInstance().good(target, sender.getName() + " added " + ChatColor.RED + "/" + com + ChatColor.GOLD + "to your startcommands.");
	}
	
	public String name() {
		return "addp";
	}
	
	public String info() {
		return "Add a command to another player's startcommands.";
	}
	
	public String[] aliases() {
		return new String[] { "addplayer" };
	}
}
