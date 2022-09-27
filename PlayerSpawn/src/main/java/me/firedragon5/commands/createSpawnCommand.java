package me.firedragon5.commands;


import me.firedragon5.playerspawn.PlayerSpawn;
import me.firedragon5.playerspawn.utils;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;


public class createSpawnCommand implements CommandExecutor {

//	This command will let a player create the spawn point of the server

	private final PlayerSpawn plugin;

	public createSpawnCommand(PlayerSpawn plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


		if (sender instanceof Player) {
			Player player = (Player) sender;

			File serverSpawn = new File("plugins/PlayerSpawn/ServerSpawn/"  + "spawns.yml");
			FileConfiguration serverSpawnData = YamlConfiguration.loadConfiguration(serverSpawn);

			if (command.getName().equalsIgnoreCase("setspawn")) {


				if (player.hasPermission("playerspawn.setspawn")) {

					Location location = player.getLocation();

					serverSpawnData.set("spawn", location);
					player.sendMessage(utils.color("&aSpawn set!"));

					try {
						serverSpawnData.save(serverSpawn);
					} catch (Exception e) {
						e.printStackTrace();
					}

				} else {
					player.sendMessage(utils.color("&cYou do not have permission to use this command!"));
				}


		}




		}else{
			sender.sendMessage("You must be a player to create the spawn point!");

		}





		return false;
	}
}
