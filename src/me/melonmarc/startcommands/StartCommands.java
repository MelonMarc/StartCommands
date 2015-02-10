package me.melonmarc.startcommands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class StartCommands extends JavaPlugin implements Listener {
	
	SettingsManager settings = SettingsManager.getInstance();
	
	public void onEnable() {
		settings.setup(this);
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		
		CommandManager cm = new CommandManager();
		cm.setup();
		getCommand("startcommands").setExecutor(cm);
	}
	
	public void onDisable() {
		
	}
	
	@EventHandler
	public void onPlayerjoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		String uuid = p.getUniqueId().toString();
		List<String> list = settings.getConfig().getStringList(uuid + ".commands");
		for(String m : list) {
			p.performCommand(m);
			}
	}
	
}
