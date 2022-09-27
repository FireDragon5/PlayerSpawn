package me.firedragon5.commands;

import me.firedragon5.playerspawn.utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class createPlayerHomes implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


		if (sender instanceof Player) {

			Player player = (Player) sender;

			File playerConfig = new File("plugins/PlayerSpawn/PlayerHomes/" + player.getUniqueId() + ".yml");
			FileConfiguration playerConfigData = YamlConfiguration.loadConfiguration(playerConfig);

			
			if (command.getName().equalsIgnoreCase("sethome")) {

				if (args.length == 0) {
					player.sendMessage(utils.color("&aPlease enter a name for your home!"));
				} else if (args.length == 1) {
					if (playerConfigData.contains(args[0])) {
						player.sendMessage(utils.color("&cYou already have a home with that name!"));
					} else {
						playerConfigData.set(args[0], player.getLocation());
						player.sendMessage(utils.color("&aHome " + args[0] + " has been created!"));
						playerConfigData.set("homeCount", playerConfigData.getInt("homeCount") + 1);

//						Save config
						try {
							playerConfigData.save(playerConfig);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				} else {
					player.sendMessage(utils.color("&cToo many arguments!"));
				}

				try {
					playerConfigData.save(playerConfig);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}






		}










		return false;
	}
}
