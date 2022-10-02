package me.firedragon5.commands.home;

import me.firedragon5.playerspawn.PlayerSpawn;
import me.firedragon5.playerspawn.utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class teleportHomeCommand implements CommandExecutor {

	private final PlayerSpawn plugin;

	public teleportHomeCommand(PlayerSpawn plugin) {
		this.plugin = plugin;
	}

	/*
	 * To do:
	 * - Add a cooldown to the command
	 * - Add a Home other players
	 */

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


		if (sender instanceof Player){

			String homeName = args[0];

			if (homeName.startsWith("&")){
				homeName = homeName.replace("&", "");
			}


			Player player = (Player) sender;

			File playerConfig = new File("plugins/PlayerSpawn/PlayerHomes/" + player.getUniqueId() + ".yml");
			FileConfiguration playerConfigData = YamlConfiguration.loadConfiguration(playerConfig);

			if (player.hasPermission("playerspawn.home")) {

				if (command.getName().equalsIgnoreCase("home")) {

					if (args.length == 0) {
						player.sendMessage(utils.color("&aYou must specify a home!"));
					} else if (args.length == 1) {
						if (playerConfigData.contains(args[0])) {


//						Teleport the player to there home after 3 seconds, also send message

							if (player.hasPermission("playerspawn.bypass.delay") || player.isOp()) {
								player.teleport((Location) playerConfigData.get(args[0]));
								player.sendMessage(utils.color(plugin.getConfig().getString("homeTeleportMessage"))
										.replace("{prefix}", plugin.getConfig().getString("prefix"))
												.replace("{player}", player.getName())
												.replace("{home}", homeName)
												.replace("&a", ""));

							} else {



								if (plugin.getConfig().getBoolean("wantDelay")) {
									player.sendMessage(utils.color("&aTeleporting to " + homeName + " in 3 seconds!"));


									String finalHomeName = homeName;
									Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

										@Override
										public void run() {
											player.teleport((Location) playerConfigData.get(args[0]));
											player.sendMessage(utils.color(plugin.getConfig().getString("homeTeleportMessage"))
													.replace("{prefix}", plugin.getConfig().getString("prefix"))
															.replace("{player}", player.getName())
															.replace("{home}", finalHomeName));

										}
									}, plugin.getConfig().getInt("teleportDelay") * 20L);
								}else{
									player.teleport((Location) playerConfigData.get(args[0]));
									player.sendMessage(utils.color(plugin.getConfig().getString("homeTeleportMessage"))
											.replace("{prefix}", plugin.getConfig().getString("prefix"))
													.replace("{player}", player.getName())
													.replace("{home}", homeName));
								}
							}


						} else {
							player.sendMessage(utils.color(plugin.getConfig().getString("homeDoesntExist"))
									.replace("{prefix}", plugin.getConfig().getString("prefix"))
									.replace("{player}", player.getDisplayName())
									.replace("{home}", homeName));



						}
					} else {
						player.sendMessage("Too many arguments!");
					}

				}
			}else{
				player.sendMessage(utils.color(plugin.getConfig().getString("noPermissionMessage"))
						.replace("{prefix}", plugin.getConfig().getString("prefix"))
								.replace("{player}", player.getName()));
			}



		}




		return false;
	}
}
