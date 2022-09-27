package me.firedragon5.commands;

import me.firedragon5.playerspawn.PlayerSpawn;
import me.firedragon5.playerspawn.utils;
import org.bukkit.Location;
import org.bukkit.Utility;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class spawnCommand implements CommandExecutor {

 	private final PlayerSpawn plugin;

 	public spawnCommand(PlayerSpawn plugin) {
 		this.plugin = plugin;
 	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

 		if (sender instanceof Player) {
			Player player = (Player) sender;

			File serverSpawn = new File("plugins/PlayerSpawn/ServerSpawn/"  + "spawns.yml");
			FileConfiguration serverSpawnData = YamlConfiguration.loadConfiguration(serverSpawn);

			if (command.getName().equalsIgnoreCase("spawn")) {

					Location location = (Location) serverSpawnData.get("spawn");
					player.teleport(location);
					player.sendMessage(utils.color("&aTeleported to spawn!"));

			}
 		}



		return false;
	}
}
