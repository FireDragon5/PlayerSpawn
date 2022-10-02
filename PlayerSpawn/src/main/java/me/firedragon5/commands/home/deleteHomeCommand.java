package me.firedragon5.commands.home;

import me.firedragon5.playerspawn.PlayerSpawn;
import me.firedragon5.playerspawn.utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class deleteHomeCommand implements CommandExecutor {

	private final PlayerSpawn plugin;

	public deleteHomeCommand(PlayerSpawn plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (sender instanceof Player) {

			Player player = (Player) sender;

			File playerConfig = new File("plugins/PlayerSpawn/PlayerHomes/" + player.getUniqueId() + ".yml");
			FileConfiguration playerConfigData = YamlConfiguration.loadConfiguration(playerConfig);


			if (player.hasPermission("playerspawn.delhome")) {
				if (command.getName().equalsIgnoreCase("delhome")) {

					String homeName = args[0];


					if (playerConfigData.contains(homeName)) {
						playerConfigData.set(homeName, null);
						player.sendMessage(utils.color(plugin.getConfig().getString("homeDeleted")
								.replace("{prefix}", plugin.getConfig().getString("prefix"))
										.replace("{player}", player.getName())
										.replace("{home}", homeName)));

						playerConfigData.set("homeCount", playerConfigData.getInt("homeCount") - 1);
//						Get the Item in the player hand and set it as homeIcon



						try {
							playerConfigData.save(playerConfig);
						} catch (Exception e) {
							e.printStackTrace();
						}


					} else {
						player.sendMessage(utils.color(plugin.getConfig().getString("homeDoesntExist")
								.replace("{prefix}", plugin.getConfig().getString("prefix"))
										.replace("{player}", player.getName())
										.replace("{home}", args[0])));
					}

				}

			} else {
				player.sendMessage(utils.color(plugin.getConfig().getString("noPermissionMessage")
						.replace("{prefix}", plugin.getConfig().getString("prefix"))
								.replace("{player}", player.getName())));

			}
			} else {
				sender.sendMessage("You must be a player to delete a home!");
			}








		return false;
	}
}
