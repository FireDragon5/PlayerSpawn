package me.firedragon5.tabcomplete;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class warpTabComplete implements TabCompleter {
	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

		Player player = (Player) sender;

		File serverWarp = new File("plugins/PlayerSpawn/ServerSpawn/"  + "warp.yml");
		FileConfiguration serverWarpData = YamlConfiguration.loadConfiguration(serverWarp);


		List<String> results = new ArrayList<String>();

		if (args.length == 1){
			for (String key : serverWarpData.getKeys(false)) {
				if (!key.equals("warpCount")) {
					results.add(key);
				}
			}

		}



		return null;
	}
}