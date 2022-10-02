package me.firedragon5.playerspawn;

import me.firedragon5.commands.*;
import me.firedragon5.commands.home.*;
import me.firedragon5.commands.spawn.createSpawnCommand;
import me.firedragon5.commands.spawn.createSpawnCommand;
//import me.firedragon5.commands.spawn.groupSpawnCommand;
import me.firedragon5.commands.spawn.spawnCommand;
import me.firedragon5.commands.warp.createWarp;
import me.firedragon5.commands.warp.warpCommand;
import me.firedragon5.events.JoinEvent;
import me.firedragon5.gui.homeAttributesMenu;
import me.firedragon5.gui.playerHomesMenu;
import me.firedragon5.gui.spawnMenu;
import me.firedragon5.tabcomplete.groupTabComplete;
import me.firedragon5.tabcomplete.homeTabComplete;
import me.firedragon5.tabcomplete.warpTabComplete;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class PlayerSpawn extends JavaPlugin {

	private static PlayerSpawn instance;

	public static Plugin getInstance() {
		return instance;
	}

//	Check the version of the config file

	private void checkConfigVersion() {
		if (getConfig().getInt("configVersion") != 1) {
			getLogger().warning("The config file is outdated! " +
					"Please delete it and restart the server to generate a new one.");
		}
	}


	@Override
	public void onEnable() {

//		Config
		instance = this;

		checkConfigVersion();

		getConfig().options().copyDefaults();
		this.saveDefaultConfig();

//		Commands

		getCommand("setspawn").setExecutor(new createSpawnCommand(this));
//		getCommand("spawn").setExecutor(new groupSpawnCommand(this));
		getCommand("spawn").setExecutor(new spawnCommand(this));
		getCommand("sethome").setExecutor(new createPlayerHomes(this));
		getCommand("home").setExecutor(new teleportHomeCommand(this));
		getCommand("delhome").setExecutor(new deleteHomeCommand(this));
		getCommand("homehelp").setExecutor(new helpHomeCommand());
		getCommand("homelist").setExecutor(new listHomeCommand());
		getCommand("warp").setExecutor(new warpCommand());
		getCommand("setwarp").setExecutor(new createWarp(this));
//		getCommand("spawnmenu").setExecutor(new spawnMenuCommand());
		getCommand("homemenu").setExecutor(new homeMenuCommand());
		getCommand("spawnreload").setExecutor(new reloadCommand());
//		getCommand("adminhome").setExecutor(new adminHomeCommand());

//		Events

		getServer().getPluginManager().registerEvents(new JoinEvent(this), this);
		getServer().getPluginManager().registerEvents(new spawnMenu(), this);
		getServer().getPluginManager().registerEvents(new playerHomesMenu(), this);
		getServer().getPluginManager().registerEvents(new homeAttributesMenu(), this);

//		 Tab Complete Commands

		getCommand("home").setTabCompleter(new homeTabComplete());
		getCommand("warp").setTabCompleter(new warpTabComplete());
		getCommand("delhome").setTabCompleter(new homeTabComplete());
//		getCommand("delwarp").setTabCompleter(new warpTabComplete());
		getCommand("spawn").setTabCompleter(new groupTabComplete());


	}

	@Override
	public void onDisable() {
		// Plugin shutdown logic
	}
}
