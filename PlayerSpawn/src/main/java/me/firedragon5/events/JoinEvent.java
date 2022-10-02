package me.firedragon5.events;

import me.firedragon5.playerspawn.PlayerSpawn;
import me.firedragon5.playerspawn.utils;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class JoinEvent implements Listener {

	private final PlayerSpawn plugin;

	public JoinEvent(PlayerSpawn plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent event) throws IOException {

		Player player  = event.getPlayer();

//		Force spawn on join if the force spawn on join is true

		if (!player.hasPermission("playerspawn.bypass.join")) {
			if (plugin.getConfig().getBoolean("forceSpawn")) {

				File serverSpawn = new File("plugins/PlayerSpawn/ServerSpawn/" + "spawns.yml");
				FileConfiguration serverSpawnData = YamlConfiguration.loadConfiguration(serverSpawn);

				Location location = (Location) serverSpawnData.get("spawn");
				player.teleport(location);
				player.sendMessage(utils.color("&aTeleported to spawn!"));

			}
		}




		File playerConfig = new File("plugins/PlayerSpawn/PlayerHomes/" + player.getUniqueId() + ".yml");

// Check if player as a config file else create one

		if (!player.hasPlayedBefore()) {
			if (!playerConfig.exists()) {
				playerConfig.createNewFile();
				System.out.println("Created new config file for " + player.getName());
			}

			File serverSpawn = new File("plugins/PlayerSpawn/ServerSpawn/"  + "spawns.yml");
			FileConfiguration serverSpawnData = YamlConfiguration.loadConfiguration(serverSpawn);

			if (serverSpawnData.contains("firstSpawn")){
				if (serverSpawnData.getBoolean("firstSpawn")) {
						Location location = (Location) serverSpawnData.get("firstSpawn");
						player.teleport(location);
						player.sendMessage(utils.color(plugin.getConfig().getString("firstSpawnMessage"))
								.replace("{prefix}", plugin.getConfig().getString("prefix"))
								.replace("{player}", player.getName()));
					}
			}
		}

	}



}
