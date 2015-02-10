package me.melonmarc.startcommands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.melonmarc.startcommands.cmds.Add;
import me.melonmarc.startcommands.cmds.Addp;
import me.melonmarc.startcommands.cmds.Help;
import me.melonmarc.startcommands.cmds.Info;
import me.melonmarc.startcommands.cmds.Reload;
import me.melonmarc.startcommands.cmds.Remove;
import me.melonmarc.startcommands.cmds.Removep;
import me.melonmarc.startcommands.cmds.SubCommand;
import me.melonmarc.startcommands.cmds.View;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandManager implements CommandExecutor {
	
	SettingsManager settings = SettingsManager.getInstance();
	
	private ArrayList<SubCommand> commands = new ArrayList<SubCommand>();
	
	public void setup() {
		commands.add(new Add());
		commands.add(new Addp());
		commands.add(new Help());
		commands.add(new Info());
		commands.add(new Reload());
		commands.add(new Remove());
		commands.add(new Removep());
		commands.add(new View());
		
		// Add all commands here.
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		if (cmd.getName().equalsIgnoreCase("startcommands")) {
			if (args.length == 0) {
				if (!(sender instanceof Player)) {
					MessageManager.getInstance().severe(sender, "Only players can run their startcommands.");
					return true;
				}
					
					Player p = (Player) sender;
					
				if (!p.hasPermission("startcommands.run")) {
					MessageManager.getInstance().severe(p, "You are not permitted to do this.");
					return true;
				}
				
				String uuid = p.getUniqueId().toString();
				List<String> wordlist = settings.getConfig().getStringList(uuid + ".commands");
				
				if (!settings.getConfig().contains(uuid + ".commands")) {
					MessageManager.getInstance().severe(p, "You do not have any startcommands.");
					return true;
				}
				
				for(String m : wordlist) {
					p.performCommand(m);
					}
				
				for (SubCommand c : commands) {
				}
				return true;
			}
			
			SubCommand target = get(args[0]);
			
			if (target == null) {
				MessageManager.getInstance().severe(sender, "/startcommands " + args[0] + " is not a valid subcommand!");
				return true;
			}
			
			ArrayList<String> a = new ArrayList<String>();
			a.addAll(Arrays.asList(args));
			a.remove(0);
			args = a.toArray(new String[a.size()]);
			
			try {
				target.onCommand(sender, args);
			}
			catch (Exception e) {
				MessageManager.getInstance().severe(sender, "An error has occured: " + e.getCause());
				e.printStackTrace();
			}
			
		}
		
		return true;
	}
	
	private String aliases(SubCommand cmd) {
		String fin = "";
		
		for (String a : cmd.aliases()) {
			fin += a + " | ";
		}
		
		return fin.substring(0, fin.lastIndexOf(" | "));
	}
	
	private SubCommand get(String name) {
		for (SubCommand cmd : commands) {
			if (cmd.name().equalsIgnoreCase(name)) return cmd;
			for (String alias : cmd.aliases()) if (name.equalsIgnoreCase(alias)) return cmd;
		}
		return null;
	}
}
