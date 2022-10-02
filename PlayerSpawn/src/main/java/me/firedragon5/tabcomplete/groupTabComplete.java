package me.firedragon5.tabcomplete;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class groupTabComplete implements TabCompleter {
	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {


		Player player = (Player) sender;

		List<String> groupList = new ArrayList<>();

		if (args.length == 1){
			groupList.add("set");
		}else if (args.length == 2){
			groupList.add("first");
		}




		return null;
	}
}
