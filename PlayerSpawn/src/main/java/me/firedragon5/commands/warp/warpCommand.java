package me.firedragon5.commands.warp;

import me.firedragon5.playerspawn.utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

/*
 To Do:
 - Add a warp other players

 */

public class warpCommand implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


		if(sender instanceof Player){

			Player player = (Player) sender;

			File serverWarp = new File("plugins/PlayerSpawn/ServerSpawn/"  + "warp.yml");
			FileConfiguration serverWarpData = YamlConfiguration.loadConfiguration(serverWarp);

			if (player.hasPermission("playerwarp.warp")){
				if (args.length == 0){
					player.sendMessage(utils.color("&cPlease specify a warp to go to!"));
				}
				else if (args.length == 1){
					String warpName = args[0];

					if (serverWarpData.contains(warpName)){
						player.teleport(serverWarpData.getLocation(warpName));
						player.sendMessage(utils.color("&aTeleported to warp " + warpName + "!"));
					}
					else {
						player.sendMessage(utils.color("&cThis warp does not exist!"));
					}

				}
				else if (args.length > 1){
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
