package me.melonmarc.startcommands.cmds;

import org.bukkit.command.CommandSender;

public abstract class SubCommand {
	
	public abstract void onCommand(CommandSender sender,String[] args);
	
	public abstract String name();
	
	public abstract String info();
	
	public abstract String[] aliases();
}
