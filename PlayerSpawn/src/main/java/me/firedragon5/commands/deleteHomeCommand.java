package me.firedragon5.commands;

import me.firedragon5.playerspawn.utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class deleteHomeCommand implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (sender instanceof Player){

			Player player = (Player) sender;

			File playerConfig = new File("plugins/PlayerSpawn/PlayerHomes/" + player.getUniqueId() + ".yml");
			FileConfiguration playerConfigData = YamlConfiguration.loadConfiguration(playerConfig);


			if (command.getName().equalsIgnoreCase("delhome")){


					if (playerConfigData.contains(args[0])){
						playerConfigData.set(args[0], null);
						player.sendMessage(utils.color("&aDeleted " + args[0] + "!"));
						playerConfigData.set("homeCount", playerConfigData.getInt("homeCount") - 1);
						try {
							playerConfigData.save(playerConfig);
						} catch (Exception e) {
							e.printStackTrace();
						}


					} else {
						player.sendMessage(utils.color("&aYou don't have a home!"));
					}

			}


		}else{
			sender.sendMessage("You must be a player to delete a home!");
		}







		return false;
	}
}
