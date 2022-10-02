package me.firedragon5.commands.home;

import me.firedragon5.playerspawn.PlayerSpawn;
import me.firedragon5.playerspawn.utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;

public class createPlayerHomes implements CommandExecutor {


	private final PlayerSpawn plugin;

	public createPlayerHomes(PlayerSpawn plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


		if (sender instanceof Player) {

			Player player = (Player) sender;

			File playerConfig = new File("plugins/PlayerSpawn/PlayerHomes/" + player.getUniqueId() + ".yml");
			FileConfiguration playerConfigData = YamlConfiguration.loadConfiguration(playerConfig);

			if (player.hasPermission("playerspawn.sethome")) {

				if (command.getName().equalsIgnoreCase("sethome")) {

					String homeName = args[0];

					if (args.length == 0) {
						player.sendMessage(utils.color(plugin.getConfig().getString("homeName-error")
								.replace("{prefix}", plugin.getConfig().getString("prefix"))
										.replace("{player}", player.getName())
										.replace("{home}", homeName)));
					} else if (args.length == 1) {
						if (playerConfigData.contains(homeName)) {
							player.sendMessage(utils.color(plugin.getConfig().getString("homeName-exists")
									.replace("{prefix}", plugin.getConfig().getString("prefix"))
											.replace("{player}", player.getName())
											.replace("{home}", homeName)));
						} else {
//							Get Item in hand
							String icon	= player.getInventory().getItemInMainHand().getType().toString();

							playerConfigData.set(homeName, player.getLocation());

							player.sendMessage(utils.color(plugin.getConfig().getString("homeName-set"))
									.replace("{prefix}", plugin.getConfig().getString("prefix"))
											.replace("{player}", player.getName())
											.replace("{home}", homeName));


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
			}else{
				player.sendMessage(utils.color(plugin.getConfig().getString("noPermissionMessage")
						.replace("{prefix}", plugin.getConfig().getString("prefix"))
								.replace("{player}", player.getName())));
			}

		}



		return false;
	}
}
