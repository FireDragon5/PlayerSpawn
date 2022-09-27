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

public class homeTabComplete  implements TabCompleter {
	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

		Player player = (Player) sender;

		File playerConfig = new File("plugins/PlayerSpawn/PlayerHomes/" + player.getUniqueId() + ".yml");
		FileConfiguration playerConfigData = YamlConfiguration.loadConfiguration(playerConfig);

		if (args.length == 1){

			List<String> homes = new ArrayList<String>();
			for (String home : playerConfigData.getKeys(false)){
				if (!home.equals("homeCount")){
					homes.add(home);

				}


			}
			return homes;

		}




		return null;
	}
}
