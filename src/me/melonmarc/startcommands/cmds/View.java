package me.melonmarc.startcommands.cmds;

import java.util.List;

import me.melonmarc.startcommands.MessageManager;
import me.melonmarc.startcommands.SettingsManager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class View extends SubCommand {
	
	SettingsManager settings = SettingsManager.getInstance();
	
	public void onCommand(CommandSender sender, String[] args) {
		if (args.length == 0) {
			if (!(sender instanceof Player)) {
				MessageManager.getInstance().severe(sender, "Only players can view their startcommands. Try /sc <list> <player>");
				return;
			}
				
				Player p = (Player) sender;
				
				String uuid = p.getUniqueId().toString();
				
				if (!p.hasPermission("startcommands.list")) {
					MessageManager.getInstance().severe(p, "You are not permitted to do this.");
					return;
					}
				
				List<String> list = settings.getConfig().getStringList(uuid + ".commands");
				p.sendMessage(ChatColor.GRAY + "Your startcommands:");
				p.sendMessage(ChatColor.GOLD + "" + list);
				return;
			}
		if (!sender.hasPermission("startcommands.list.player")) {
			MessageManager.getInstance().severe(sender, "You are not permitted to do this.");
			return;
			}
		
		Player target = Bukkit.getServer().getPlayer(args[0]);
		if (target == null) {
			MessageManager.getInstance().severe(sender, "Could not find player " + args[0]);
			return;
		}
		
		String uuid = target.getUniqueId().toString();
		
		List<String> list = settings.getConfig().getStringList(uuid + ".commands");
		sender.sendMessage(ChatColor.GRAY + "" + target.getName() + "'s startcommands:");
		sender.sendMessage(ChatColor.GOLD + "" + list);
		return;
		
	}
	
	public String name() {
		return "list";
	}
	
	public String info() {
		return "A list of your or another player's startcommands.";
	}
	
	public String[] aliases() {
		return new String[] { "view" };
	}

}
