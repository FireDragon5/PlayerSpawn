package me.firedragon5.commands;

import me.firedragon5.playerspawn.utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class teleportHomeCommand implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


		if (sender instanceof Player){


			Player player = (Player) sender;

			File playerConfig = new File("plugins/PlayerSpawn/PlayerHomes/" + player.getUniqueId() + ".yml");
			FileConfiguration playerConfigData = YamlConfiguration.loadConfiguration(playerConfig);

			if (command.getName().equalsIgnoreCase("home")) {

				if (args.length == 0) {
					player.sendMessage(utils.color("&aYou must specify a home!"));
				} else if (args.length == 1) {
					if (playerConfigData.contains(args[0])) {
						player.teleport(playerConfigData.getLocation(args[0]));
						player.sendMessage(utils.color("&aYou have been teleported to your home!"));

					} else {
						player.sendMessage(utils.color("&cYou don't have a home with that name!"));
					}
				} else {
					player.sendMessage("Too many arguments!");
				}

			}



		}




		return false;
	}
}
