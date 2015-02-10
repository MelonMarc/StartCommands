package me.melonmarc.startcommands.cmds;

import java.util.List;

import me.melonmarc.startcommands.MessageManager;
import me.melonmarc.startcommands.SettingsManager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Removep extends SubCommand {
	
	SettingsManager settings = SettingsManager.getInstance();
	
	public void onCommand(CommandSender sender, String[] args) {
		if (!sender.hasPermission("startcommands.removep")) {
			MessageManager.getInstance().severe(sender, "You are not permitted to do this.");
			return;
		}
		if (args.length == 0) {
			MessageManager.getInstance().severe(sender, "Usage: /<startcommands> <removep> <player> <command>");
			return;
		}
		Player target = Bukkit.getServer().getPlayer(args[0]);
		if (target == null) {
			MessageManager.getInstance().severe(sender, "Could not find player " + args[0]);
			return;
		}
		
		if (args.length == 1) {
			MessageManager.getInstance().severe(sender, "Usage: /<startcommands> <removep> <player> <command>");
			return;
		}
		
		StringBuilder str = new StringBuilder();
		for (int i = 1; i < args.length; i++) {
			str.append(args[i] + " ");
		}
		String com = str.toString();
		
		String uuid = target.getUniqueId().toString();
		
		List<String> list = settings.getConfig().getStringList(uuid + ".commands");
		
		if (!list.contains(com)) {
        	MessageManager.getInstance().severe(sender, "Could not find " + ChatColor.RED + "/" + com + ChatColor.DARK_RED + "in " + target.getName() + "'s startcommands.");
        	return;
        }
		if (list.contains(com)) {
            list.remove(com);
            settings.getConfig().set(uuid + ".commands", list);
            MessageManager.getInstance().good(sender, "Removed " + ChatColor.RED + "/" + com + ChatColor.GOLD + "from " + target.getName() + "'s startcommands.");
            MessageManager.getInstance().good(target, sender.getName() + " removed " + ChatColor.RED + "/" + com + ChatColor.GOLD + "from your startcommands.");
        }
		
		settings.saveConfig();
		settings.reloadConfig();
		
	}
	
	public String name() {
		return "removep";
	}
	
	public String info() {
		return "Remove a command from another player's startcommands.";
	}
	
	public String[] aliases() {
		return new String[] { "removeplayer" };
	}

}
