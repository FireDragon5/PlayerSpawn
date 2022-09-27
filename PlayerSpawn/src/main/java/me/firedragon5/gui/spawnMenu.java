package me.firedragon5.gui;

import com.sun.tools.jdeps.InverseDepsAnalyzer;
import me.firedragon5.playerspawn.utils;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class spawnMenu implements Listener {

//	Spawn menu will be used to teleport to spawn, set spawn, and set home


	public Inventory inv;

	File serverSpawn = new File("plugins/PlayerSpawn/ServerSpawn/"  + "spawns.yml");
	FileConfiguration serverSpawnData = YamlConfiguration.loadConfiguration(serverSpawn);


	public void createMenu(Player player) {
		inv = Bukkit.createInventory(null, 9, "Spawn Menu Admin");

		ItemStack spawn = new ItemStack(Material.BEEF);
		ItemMeta spawnmeta = spawn.getItemMeta();

		spawnmeta.setDisplayName("Spawn");
		List<String> lore = new ArrayList<>();
		lore.add(utils.color("&aTeleport to spawn"));

		lore.add(utils.color("&6&lYou can also use the command /spawn!"));
		spawnmeta.setLore(lore);

		spawn.setItemMeta(spawnmeta);
		inv.setItem(0, spawn);


		ItemStack setspawn = new ItemStack(Material.LIGHT_WEIGHTED_PRESSURE_PLATE);
		ItemMeta setspawnmeta = setspawn.getItemMeta();

		setspawnmeta.setDisplayName("Spawn Menu");
		List<String> setspawnlore = new ArrayList<>();
		setspawnlore.add(utils.color("&6&lSet spawn"));
		setspawnmeta.setLore(setspawnlore);

		setspawn.setItemMeta(setspawnmeta);
		inv.setItem(4, setspawn);

		player.openInventory(inv);

	}





	@EventHandler
	public void onClick(InventoryClickEvent event){
		if(event.getView().getTitle().equalsIgnoreCase("Spawn Menu Admin")){
			event.setCancelled(true);
			Player player = (Player) event.getWhoClicked();
			ItemStack clicked = event.getCurrentItem();
			if(clicked == null || clicked.getType() == Material.AIR) return;
			if(clicked.getItemMeta().getDisplayName().equalsIgnoreCase("Spawn")){
				player.performCommand("spawn");

			}
			if(clicked.getItemMeta().getDisplayName().equalsIgnoreCase("Set Spawn")){
				player.performCommand("setspawn");
			}
		}
	}












}
