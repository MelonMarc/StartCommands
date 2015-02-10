package me.melonmarc.startcommands.cmds;

import java.util.ArrayList;
import java.util.List;

import me.melonmarc.startcommands.MessageManager;
import me.melonmarc.startcommands.SettingsManager;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Add extends SubCommand {
	
	SettingsManager settings = SettingsManager.getInstance();
	
	public void onCommand(CommandSender sender, String[] args) {
		if (!(sender instanceof Player)) {
			MessageManager.getInstance().severe(sender, "Only players can use /sc add, try /sc addp.");
			return;
		}
			
			Player p = (Player) sender;
		
		if (!p.hasPermission("startcommands.add")) {
			MessageManager.getInstance().severe(p, "You are not permitted to do this.");
			return;
		}
		if (args.length == 0) {
			MessageManager.getInstance().severe(p, "Usage: /<startcommands> <add> <command>");
			return;
		}
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < args.length; i++) {
			str.append(args[i] + " ");
		}
		String com = str.toString();
		
		String uuid = p.getUniqueId().toString();
		
		if (!settings.getConfig().contains(uuid)) settings.getConfig().set(uuid + ".commands", new ArrayList<String>());
		
		List<String> list = settings.getConfig().getStringList(uuid + ".commands");
		list.add(com);
		settings.getConfig().set(uuid + ".commands", list);
		settings.saveConfig();
		settings.reloadConfig();
		MessageManager.getInstance().good(p, "Added " + ChatColor.RED + "/" + com + ChatColor.GOLD + "to your startcommands.");
		
	}
	
	public String name() {
		return "add";
	}
	
	public String info() {
		return "Add a command to your startcommands.";
	}
	
	public String[] aliases() {
		return new String[] { };
	}
}
