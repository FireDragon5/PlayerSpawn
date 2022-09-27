package me.firedragon5.playerspawn;

import me.firedragon5.commands.*;
import me.firedragon5.events.JoinEvent;
import me.firedragon5.gui.spawnMenu;
import me.firedragon5.tabcomplete.homeTabComplete;
import me.firedragon5.tabcomplete.warpTabComplete;
import org.bukkit.plugin.java.JavaPlugin;

public final class PlayerSpawn extends JavaPlugin {

	@Override
	public void onEnable() {

		getCommand("setspawn").setExecutor(new createSpawnCommand(this));
		getCommand("spawn").setExecutor(new spawnCommand(this));
		getCommand("sethome").setExecutor(new createPlayerHomes());
		getCommand("home").setExecutor(new teleportHomeCommand());
		getCommand("delhome").setExecutor(new deleteHomeCommand());
		getCommand("homehelp").setExecutor(new helpHomeCommand());
		getCommand("homelist").setExecutor(new listHomeCommand());
		getCommand("warp").setExecutor(new warpCommand());
		getCommand("setwarp").setExecutor(new createWarp());
		getCommand("spawnmenu").setExecutor(new spawnMenuCommand());

		getServer().getPluginManager().registerEvents(new JoinEvent(), this);
		getServer().getPluginManager().registerEvents(new spawnMenu(), this);

		getCommand("home").setTabCompleter(new homeTabComplete());
		getCommand("warp").setTabCompleter(new warpTabComplete());


	}

	@Override
	public void onDisable() {
		// Plugin shutdown logic
	}
}
