package me.firedragon5.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;
import java.io.IOException;

public class JoinEvent implements Listener {

	public void onJoin(PlayerJoinEvent event) throws IOException {



		Player player  = event.getPlayer();
		File playerConfig = new File("plugins/PlayerSpawn/PlayerHomes/" + player.getUniqueId() + ".yml");

// Check if player as a config file else create one

		if (!playerConfig.exists()){
			playerConfig.createNewFile();
			System.out.println("Created new config file for " + player.getName());
		}

	}



}
