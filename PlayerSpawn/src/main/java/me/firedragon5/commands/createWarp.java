package me.firedragon5.commands;

import jdk.jfr.internal.Utils;
import me.firedragon5.playerspawn.utils;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class createWarp implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


		File serverWarp = new File("plugins/PlayerSpawn/ServerSpawn/"  + "warp.yml");
		FileConfiguration serverWarpData = YamlConfiguration.loadConfiguration(serverWarp);

		if (sender instanceof Player){
			Player player = (Player) sender;

			if (player.hasPermission("playerwarp.create")){
				if (args.length == 0){
					player.sendMessage(utils.color("&cPlease specify a name for your warp!"));
				}
				else if (args.length == 1){
					String warpName = args[0];

					if (serverWarpData.contains(warpName)){
						player.sendMessage(utils.color("&cThis warp already exists!"));
					}
					else {
						Location playerLocation = player.getLocation();
						serverWarpData.set(warpName, playerLocation);
						player.sendMessage(utils.color("&aWarp " + warpName + " created!"));


						try {
							serverWarpData.save(serverWarp);
						}
						catch (Exception e){
							e.printStackTrace();
						}
					}

				} else if (args.length > 1){
						player.sendMessage(utils.color("&cToo many arguments!"));
				}
			}
			else {
				player.sendMessage(utils.color("&cYou do not have permission to use this command!"));
			}

		}else{
			sender.sendMessage("You must be a player to use this command!");
		}



		return false;
	}
}
