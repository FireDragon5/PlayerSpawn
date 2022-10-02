package me.firedragon5.commands.warp;

import jdk.jfr.internal.Utils;
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

public class createWarp implements CommandExecutor {

	private final PlayerSpawn plugin;

	public createWarp(PlayerSpawn plugin) {
		this.plugin = plugin;
	}


	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


		File serverWarp = new File("plugins/PlayerSpawn/ServerSpawn/"  + "warp.yml");
		FileConfiguration serverWarpData = YamlConfiguration.loadConfiguration(serverWarp);

		if (sender instanceof Player){
			Player player = (Player) sender;

			if (player.hasPermission("playerspawn.setwarp")){
				if (args.length == 0){
					player.sendMessage(utils.color(plugin.getConfig().getString("warpCreateUsage"))
							.replace("{prefix}", plugin.getConfig().getString("prefix"))
									.replace("{player}", player.getName()));
				}
				else if (args.length == 1){
					String warpName = args[0];

					if (serverWarpData.contains(warpName)){
						player.sendMessage(utils.color(plugin.getConfig().getString("warpAlreadyExists"))
								.replace("{prefix}", plugin.getConfig().getString("prefix"))
								.replace("{player}", player.getName())
								.replace("%warp%", warpName));
					}
					else {
						Location playerLocation = player.getLocation();
						serverWarpData.set(warpName, playerLocation);
						player.sendMessage(utils.color(plugin.getConfig().getString("warpCreated"))
								.replace("{prefix}", plugin.getConfig().getString("prefix"))
										.replace("{player}", player.getName())
								.replace("%warp%", warpName));


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
				player.sendMessage(utils.color(plugin.getConfig().getString("noPermissionMessage"))
						.replace("{prefix}", plugin.getConfig().getString("prefix"))
								.replace("{player}", player.getName()));
			}

		}else{
			sender.sendMessage("You must be a player to use this command!");
		}



		return false;
	}
}
