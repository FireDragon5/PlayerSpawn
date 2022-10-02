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

		File serverWarp = new File("plugins/PlayerSpawn/ServerSpawn/warp.yml");
		FileConfiguration serverWarpData = YamlConfiguration.loadConfiguration(serverWarp);



		if (args.length == 1){
			List<String> results = new ArrayList<String>();

			for (String key : serverWarpData.getKeys(false)) {
				if (key == null) {
					results.add("No warps found");
				}else{
					results.add(key);
				}
			}
			return results;

		}



		return null;
	}
}
