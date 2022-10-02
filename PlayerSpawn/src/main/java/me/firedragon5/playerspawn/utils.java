package me.firedragon5.playerspawn;

import org.bukkit.Bukkit;

public class utils{

//	Colour chat

	public static String color(String s) {
		return s.replace("&", "§");
	}

//	Get the version of the plugin the server has

	public static String getPluginVersion() {
		return Bukkit.getPluginManager().getPlugin("PlayerSpawn").getDescription().getVersion();
	}



//	Get the latest version of the plugin

	public static String getLatestVersion() {
		return "1.0.0";
	}


}
