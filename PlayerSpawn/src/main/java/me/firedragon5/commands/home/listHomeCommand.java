package me.firedragon5.commands.home;

import me.firedragon5.playerspawn.utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class listHomeCommand implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


		if (sender instanceof Player){

			Player player = (Player) sender;

			File playerConfig = new File("plugins/PlayerSpawn/PlayerHomes/" + player.getUniqueId() + ".yml");
			FileConfiguration playerConfigData = YamlConfiguration.loadConfiguration(playerConfig);

			if (command.getName().equalsIgnoreCase("homelist")){

//				Display the list of homes excluding homeCount
				player.sendMessage(utils.color("&6&l---&6&l[&e&lHome List&6&l]&6&l---"));
//				player.sendMessage(utils.color("&aList of homes:"));
				for (String key : playerConfigData.getKeys(false)) {
					if (!key.equals("homeCount")) {
						player.sendMessage(utils.color("&a" + key));
					}
				}
				player.sendMessage(utils.color("&6&l----------------------------------"));
				player.sendMessage(utils.color("&aTotal homes: " + playerConfigData.getInt("homeCount")));
				player.sendMessage(utils.color("&6&l----------------------------------"));





			}
		}else{
			sender.sendMessage("You must be a player to list homes!");
		}

		return false;
	}
}
