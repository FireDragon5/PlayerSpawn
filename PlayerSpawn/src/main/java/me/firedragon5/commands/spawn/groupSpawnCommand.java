//package me.firedragon5.commands.spawn;
//
//import me.firedragon5.playerspawn.PlayerSpawn;
//import me.firedragon5.playerspawn.utils;
//import org.bukkit.Location;
//import org.bukkit.command.Command;
//import org.bukkit.command.CommandExecutor;
//import org.bukkit.command.CommandSender;
//import org.bukkit.configuration.file.FileConfiguration;
//import org.bukkit.configuration.file.YamlConfiguration;
//import org.bukkit.entity.Player;
//
//import java.io.File;
//
//public class groupSpawnCommand implements CommandExecutor {
//
//	private final PlayerSpawn plugin;
//
//	public groupSpawnCommand(PlayerSpawn plugin) {
//		this.plugin = plugin;
//	}
//
//	@Override
//	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
//
//
//		if (sender instanceof Player) {
//			Player player = (Player) sender;
//
//			File serverSpawn = new File("plugins/PlayerSpawn/ServerSpawn/"  + "spawns.yml");
//			FileConfiguration serverSpawnData = YamlConfiguration.loadConfiguration(serverSpawn);
//
//			if (command.getName().equalsIgnoreCase("spawn")) {
//
////				Teleport the player to spawn
//				if (args.length == 0) {
//					if (player.hasPermission("playerspawn.spawn") || player.isOp()) {
//						player.teleport((Location) serverSpawnData.get("spawn"));
//						player.sendMessage(utils.color(plugin.getConfig().getString("spawnTeleportMessage"))
//								.replace("{prefix}", plugin.getConfig().getString("prefix")));
//					} else {
//						player.sendMessage(utils.color(plugin.getConfig().getString("noPermissionMessage"))
//								.replace("{prefix}", plugin.getConfig().getString("prefix")));
//					}
////					Set the spawn point for /spawn
//				} else {
//					if (player.hasPermission("playerspawn.setspawn") || player.isOp()) {
//						if (args[0].equalsIgnoreCase("set")) {
//							serverSpawnData.set("spawn", player.getLocation());
//							player.sendMessage(utils.color(plugin.getConfig().getString("spawnSetMessage"))
//									.replace("{prefix}", plugin.getConfig().getString("prefix")));
//						}
//					}
////					Set the first span point if a player joins for the first time
//
//					if (player.hasPermission("playerspawn.setspawn") || player.isOp()) {
//						if (args[1].equalsIgnoreCase("first")) {
//							serverSpawnData.set("firstSpawn", player.getLocation());
//							player.sendMessage(utils.color(plugin.getConfig().getString("firstSpawnSetMessage"))
//									.replace("{prefix}", plugin.getConfig().getString("prefix")));
//						}
//					} else {
//						player.sendMessage(utils.color(plugin.getConfig().getString("noPermissionMessage"))
//								.replace("{prefix}", plugin.getConfig().getString("prefix")));
//					}
//
//				}
//			}
//
//
//
//		}
//		else {
//			sender.sendMessage("You must be a player to use this command!");
//		}
//
//
//
//
//		return false;
//	}
//}
