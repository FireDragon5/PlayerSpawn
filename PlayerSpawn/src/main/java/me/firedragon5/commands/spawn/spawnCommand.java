package me.firedragon5.commands.spawn;

import me.firedragon5.playerspawn.PlayerSpawn;
import me.firedragon5.playerspawn.utils;
import org.bukkit.Bukkit;
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



			if (player.hasPermission("playerspawn.spawn")){
			if (command.getName().equalsIgnoreCase("spawn")) {

				if (args.length == 0) {
					Location location = (Location) serverSpawnData.get("spawn");


//					Check if the player has permission to skip the delay
					if (player.hasPermission("playerspawn.bypass.delay") || player.isOp()) {
						player.teleport(location);
						player.sendMessage(utils.color(plugin.getConfig().getString("spawn-message"))
								.replace("{prefix}", plugin.getConfig().getString("prefix"))
								.replace("{player}", player.getName()));
					} else {

//						Teleport the player to spawn after 3 seconds, also send message
						if (plugin.getConfig().getBoolean("wantDelay")) {
							player.sendMessage(utils.color("&aTeleporting to spawn in 3 seconds!"));

							Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
								@Override
								public void run() {
									player.teleport(location);
									player.sendMessage(utils.color(plugin.getConfig().getString("spawn-message"))
											.replace("{prefix}", plugin.getConfig().getString("prefix"))
											.replace("{player}", player.getName()));
								}
							}, plugin.getConfig().getInt("teleportDelay") * 20L);
						}else {
							player.teleport(location);
							player.sendMessage(utils.color(plugin.getConfig().getString("spawn-message"))
									.replace("{prefix}", plugin.getConfig().getString("prefix"))
									.replace("{player}", player.getName()));
						}
					}


				} else if (args.length == 1) {
//					Check if the player has permission to teleport other players to spawn
					if (player.hasPermission("playerspawn.spawn.others") || player.isOp()) {
						Player target = plugin.getServer().getPlayer(args[0]);
						Location location = (Location) serverSpawnData.get("spawn");
						target.teleport(location);
						target.sendMessage(utils.color(plugin.getConfig().getString("spawn-message"))
								.replace("{prefix}", plugin.getConfig().getString("prefix"))
										.replace("{player}", player.getName()));
						player.sendMessage(utils.color(plugin.getConfig().getString("spawn-others-message"))
								.replace("{prefix}", plugin.getConfig().getString("prefix"))
										.replace("{player}", target.getName()));
					} else {
//						Send message if the player doesn't have permission
						player.sendMessage(utils.color(plugin.getConfig().getString("noPermissionMessage"))
								.replace("{prefix}", plugin.getConfig().getString("prefix")));
					}
				}

			}
			}else {
				player.sendMessage(utils.color(plugin.getConfig().getString("noPermissionMessage"))
						.replace("{prefix}", plugin.getConfig().getString("prefix")));
			}
 		}



		return false;
	}
}
