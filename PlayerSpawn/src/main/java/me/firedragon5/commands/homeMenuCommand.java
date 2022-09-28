package me.firedragon5.commands;

import me.firedragon5.gui.playerHomesMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class homeMenuCommand implements CommandExecutor {



	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (sender instanceof Player){

			Player player = (Player) sender;

			if (command.getName().equalsIgnoreCase("homemenu")){
				playerHomesMenu menu = new playerHomesMenu();

				menu.playerHomes(player);


			}


		}else{
			sender.sendMessage("You must be a player to use this command!");
		}







		return false;
	}
}
