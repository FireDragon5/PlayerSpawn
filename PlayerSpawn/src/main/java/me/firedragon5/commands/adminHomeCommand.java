//package me.firedragon5.commands;
//
//import me.firedragon5.playerspawn.utils;
//import me.firedragon5.gui.playerHomesMenu;
//
//import org.bukkit.Bukkit;
//import org.bukkit.command.Command;
//import org.bukkit.command.CommandExecutor;
//import org.bukkit.command.CommandSender;
//import org.bukkit.configuration.file.FileConfiguration;
//import org.bukkit.configuration.file.YamlConfiguration;
//import org.bukkit.entity.Player;
//
//import java.io.File;
//import java.util.UUID;
//
//public class adminHomeCommand implements CommandExecutor
//{
//	@Override
//	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
//
//
//		if (sender instanceof Player){
//
//			Player player = (Player) sender;
//
//
//
//			if (player.hasPermission("playerspawn.adminhome")) {
//
//				if (args.length == 0) {
//					player.sendMessage(utils.color("&cPlease enter a player name!"));
//				} else if (args.length == 1) {
//
//					String playerName = args[0];
//
//					if (playerName == null) {
//
//						player.sendMessage(utils.color("&cPlayer is not online!"));
//
//					} else {
//						UUID playerUUID = Bukkit.getPlayer(playerName).getUniqueId();
//
//						File playerConfig2 = new File("plugins/PlayerSpawn/PlayerHomes/" + playerUUID + ".yml");
//						FileConfiguration playerConfigData2 = YamlConfiguration.loadConfiguration(playerConfig2);
//
//						playerHomesMenu menu = new playerHomesMenu();
//						Player target = Bukkit.getPlayer(playerName);
//						menu.playerHomes(target);
//
//
//						player.sendMessage(utils.color("&aYou opened " + args[0] + "'s home menu!"));
//
//					}
//				}
//
//			} else {
//				player.sendMessage(utils.color("&cYou do not have permission to use this command!"));
//			}
//
//
//		}else{
//			sender.sendMessage("You must be a player to use this command!");
//		}
//
//		return false;
//	}
//}
