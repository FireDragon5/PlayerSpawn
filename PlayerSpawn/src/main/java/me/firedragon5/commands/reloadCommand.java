package me.firedragon5.commands;

import me.firedragon5.playerspawn.PlayerSpawn;
import me.firedragon5.playerspawn.utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class reloadCommand implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


		if (sender instanceof Player){

			Player player = (Player) sender;

			if (player.hasPermission("playerspawn.reload") || player.isOp()){

				try {
					PlayerSpawn.getInstance().reloadConfig();
					player.sendMessage(utils.color("&aReloaded config!"));
				}catch (Exception e){
					player.sendMessage(utils.color("&cError reloading config!"));
					player.sendMessage(utils.color("&cError: " + e));

				}


			}else{

				player.sendMessage("You do not have permissions for this command!");
			}


		}else{
			sender.sendMessage("You must be a player to use this command!");
		}





		return false;
	}
}
