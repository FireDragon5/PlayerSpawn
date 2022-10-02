package me.firedragon5.commands.spawn;


import jdk.internal.loader.Loader;
import me.firedragon5.playerspawn.PlayerSpawn;
import me.firedragon5.playerspawn.utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.io.File;


public class createSpawnCommand implements CommandExecutor {

//	This command will let a player create the spawn point of the server

	private final PlayerSpawn plugin;

	public createSpawnCommand(PlayerSpawn plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


		if (sender instanceof Player) {
			Player player = (Player) sender;

			File serverSpawn = new File("plugins/PlayerSpawn/ServerSpawn/"  + "spawns.yml");
			FileConfiguration serverSpawnData = YamlConfiguration.loadConfiguration(serverSpawn);

			if (command.getName().equalsIgnoreCase("setspawn")) {



				if (player.hasPermission("playerspawn.setspawn") || player.isOp()) {

					if (args.length == 0) {

						Location location = player.getLocation();

//					Spawn in a npc on the player location for 10 seconds


						if (plugin.getConfig().getBoolean("spawnVillager")) {
							spawnVillager(player);

						}


						serverSpawnData.set("spawn", location);
						player.sendMessage(utils.color(plugin.getConfig().getString("spawnSetMessage"))
								.replace("{prefix}", plugin.getConfig().getString("prefix"))
										.replace("{player}", player.getName()));


						try {
							serverSpawnData.save(serverSpawn);
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else if (args.length == 1){
						if (args[0].equalsIgnoreCase("first")) {
							Location location = player.getLocation();
							serverSpawnData.set("firstSpawn", location);
							player.sendMessage(utils.color(plugin.getConfig().getString("SetSpawnMessage"))
									.replace("{prefix}", plugin.getConfig().getString("prefix"))
											.replace("{player}", player.getName()));

							try {
								serverSpawnData.save(serverSpawn);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}

					} else {
						player.sendMessage(utils.color(plugin.getConfig().getString("noPermissionMessage"))
								.replace("{prefix}", plugin.getConfig().getString("prefix"))
										.replace("{player}", player.getName()));
					}

		}

		}else{
			sender.sendMessage("You must be a player to create the spawn point!");

		}

		return false;
	}

//	Spawn in a npc on the player location for 10 seconds
	private void spawnVillager(Player player) {
		Location location = player.getLocation();
		Entity villager = player.getWorld().spawnEntity(location, EntityType.VILLAGER);


//		Add potion effects to the npc


		villager.setInvulnerable(true);
		villager.setSilent(true);
		villager.setGravity(false);
		villager.setGlowing(true);
		villager.setCustomName("Spawn Point");
		villager.setCustomNameVisible(true);


//		After 5 seconds remove the npc

		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			@Override
			public void run() {
				villager.remove();
			}
		}, 100L);






	}

}
