package me.firedragon5.gui;

import com.sun.tools.jdeps.InverseDepsAnalyzer;
import me.firedragon5.playerspawn.utils;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
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
		inv = Bukkit.createInventory(null, 54, "Spawn Menu Admin");


		ItemStack setspawn = new ItemStack(Material.LIGHT_WEIGHTED_PRESSURE_PLATE);
		ItemMeta setspawnmeta = setspawn.getItemMeta();

		setspawnmeta.setDisplayName(utils.color("&b&lSet Spawn"));
		List<String> setspawnlore = new ArrayList<>();
		setspawnlore.add(utils.color("&6&lSet spawn"));
		setspawnmeta.setLore(setspawnlore);

		setspawn.setItemMeta(setspawnmeta);
		inv.setItem(13, setspawn);

		ItemStack setwarp = new ItemStack(Material.APPLE);
		ItemMeta setwarpmeta = setwarp.getItemMeta();

		setwarpmeta.setDisplayName(utils.color("&b&lSet Warp"));
		List<String> setwarplore = new ArrayList<>();
		setwarplore.add(utils.color("&6&lSet warp"));
		setwarpmeta.setLore(setwarplore);

		setwarp.setItemMeta(setwarpmeta);
		inv.setItem(16, setwarp);


		ItemStack thanks = new ItemStack(Material.ENCHANTED_GOLDEN_APPLE);
		ItemMeta thanksmeta = thanks.getItemMeta();

		thanksmeta.setDisplayName(utils.color("&b&lThanks for using PlayerSpawn!"));
		List<String> thankslore = new ArrayList<>();
		thankslore.add(utils.color("&6&lThanks for using this plugin!"));
		thankslore.add(utils.color("&c&lYour version: " + utils.getPluginVersion()));
		thankslore.add(utils.color("&a&lNew version: " + utils.getLatestVersion()));
		thanksmeta.setLore(thankslore);

//		thanks.addEnchantment(Enchantment.DURABILITY, 1);
		thanks.setItemMeta(thanksmeta);

		inv.setItem(53, thanks);


//		Adding plain glass panes to the menu to make it look nicer

		ItemStack glass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
		ItemMeta glassmeta = glass.getItemMeta();

		glassmeta.setDisplayName(utils.color(""));


		glass.setItemMeta(glassmeta);

//		Want numbers 0-53, but not 10, 13, 16, 53

		for (int i = 0; i < 54; i++) {

//			String list of all the numbers that should not be glass

			String[] notGlass = {"13", "16", "53"};

			String glassNumber = Integer.toString(i);

			if (glassNumber.equals(notGlass[0]) || glassNumber.equals(notGlass[1])
					|| glassNumber.equals(notGlass[2])) {
				continue;
			} else {
				inv.setItem(i, glass);
			}
		}


		player.openInventory(inv);

	}


	@EventHandler
	public void onClick(InventoryClickEvent event){
		if(event.getView().getTitle().equalsIgnoreCase("Spawn Menu Admin")){
			event.setCancelled(true);
			Player player = (Player) event.getWhoClicked();
			ItemStack clicked = event.getCurrentItem();
			if(clicked == null || clicked.getType() == Material.AIR) return;

			if(clicked.getItemMeta().getDisplayName().equalsIgnoreCase("&b&lSet Spawn")){
				player.performCommand("setspawn");

			}

//			If the player clicks the warp item, it will type the command /setwarp in chat for them
			if(clicked.getItemMeta().getDisplayName().equalsIgnoreCase("&b&lSet Warp")){
				player.performCommand("setwarp");
			}


		}
	}












}
