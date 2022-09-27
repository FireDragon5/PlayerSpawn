package me.firedragon5.commands;

import me.firedragon5.playerspawn.utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class helpHomeCommand implements CommandExecutor {

//	This command will let a player get help on the home command
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


		if (command.getName().equalsIgnoreCase("homehelp")) {


			sender.sendMessage(utils.color("&6&l---&6&l[&e&lHome Help&6&l]&6&l---"));

			sender.sendMessage(utils.color("&aThis command will let you set a home, teleport to your home, and delete your home."));
			sender.sendMessage(utils.color("&aTo set a home, type /sethome {name}"));
			sender.sendMessage(utils.color("&aTo teleport to your home, type /home {name}"));
			sender.sendMessage(utils.color("&aTo delete your home, type /delhome {name}"));

			sender.sendMessage(utils.color("&6&l-----------------------------------------------------------------------"));


		}



		return false;
	}
}
