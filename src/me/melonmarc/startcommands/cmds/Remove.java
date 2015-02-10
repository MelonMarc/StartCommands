package me.melonmarc.startcommands.cmds;

import java.util.List;

import me.melonmarc.startcommands.MessageManager;
import me.melonmarc.startcommands.SettingsManager;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Remove extends SubCommand {
	
	SettingsManager settings = SettingsManager.getInstance();
	
	public void onCommand(CommandSender sender, String[] args) {
		if (!(sender instanceof Player)) {
			MessageManager.getInstance().severe(sender, "Only players can use /sc remove, try /sc removep.");
			return;
		}
			
			Player p = (Player) sender;
			
		if (!p.hasPermission("startcommands.remove")) {
			MessageManager.getInstance().severe(p, "You are not permitted to do this.");
			return;
		}
		if (args.length == 0) {
			MessageManager.getInstance().severe(p, "Usage: /<startcommands> <remove> <command>");
			return;
		}
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < args.length; i++) {
			str.append(args[i] + " ");
		}
		String com = str.toString();
		
		String uuid = p.getUniqueId().toString();
		
		List<String> list = settings.getConfig().getStringList(uuid + ".commands");
		
        if (!list.contains(com)) {
        	MessageManager.getInstance().severe(p, "Could not find " + ChatColor.RED + "/" + com + ChatColor.DARK_RED + "in your startcommands.");
        	return;
        }
		if (list.contains(com)) {
            list.remove(com);
            settings.getConfig().set(uuid + ".commands", list);
            MessageManager.getInstance().good(p, "Removed " + ChatColor.RED + "/" + com + ChatColor.GOLD + "from your startcommands.");
        }
		settings.saveConfig();
		settings.reloadConfig();
	}
	
	public String name() {
		return "remove";
	}
	
	public String info() {
		return "Remove a command from your startcommands.";
	}
	
	public String[] aliases() {
		return new String[] { "delete" };
	}
}
