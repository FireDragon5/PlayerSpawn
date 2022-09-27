package me.firedragon5.commands;

import me.firedragon5.gui.spawnMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class spawnMenuCommand implements CommandExecutor {

	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if(sender instanceof Player){
			Player player = (Player) sender;

			if(command.getName().equalsIgnoreCase("spawnmenu")){
				spawnMenu menu = new spawnMenu();
				menu.createMenu(player);
			}
		}else{
			sender.sendMessage("You must be a player to use this command!");
			}




		return false;
	}




}
