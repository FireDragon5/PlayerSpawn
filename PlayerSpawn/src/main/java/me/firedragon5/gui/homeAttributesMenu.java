package me.firedragon5.gui;

import me.firedragon5.playerspawn.utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;

public class homeAttributesMenu implements Listener {



	private Inventory inv;

//Here we can change the name of the home
//	Change the location of the home
//	Change the item of the home icon
	public void attributesMenu(Player player, String displayName){

		File playerConfig = new File("plugins/PlayerSpawn/PlayerHomes/" + player.getUniqueId() + ".yml");
		FileConfiguration playerConfigData = YamlConfiguration.loadConfiguration(playerConfig);

		inv = Bukkit.createInventory(null, 54, utils.color("&aHome Attributes"));


//		Get the home icon form the display name
//		String homeIcon = playerConfigData.getString(displayName + ".icon");


//		if (homeIcon.equals("")){
//			homeIcon = "RED_BED";
//		}else {
//			homeIcon = playerConfigData.getString(displayName + ".icon");
//		}
//
//
////		Show the name of the home we are changing
//		ItemStack currentHome = new ItemStack(Material.valueOf(homeIcon));
//		ItemMeta currentHomeMeta = currentHome.getItemMeta();
//		currentHomeMeta.setDisplayName(utils.color("&aCurrent Home: &6" + displayName));
//		currentHome.setItemMeta(currentHomeMeta);
//		inv.setItem(4, currentHome);

//		Here we can change the icon of your home

		ItemStack icon = new ItemStack(Material.RED_BED);
		ItemMeta iconMeta = icon.getItemMeta();
		iconMeta.setDisplayName(utils.color("&aHome Icon"));
		List<String> iconLore = new ArrayList<>();
		iconLore.add(utils.color("&6&lLeft Click &7to change the icon of this home"));
		iconLore.add(utils.color("&6&lTakes the item in your hand"));
//		iconLore.add(homeIcon);
		iconMeta.setLore(iconLore);
		icon.setItemMeta(iconMeta);
		inv.setItem(11, icon);



//		Change the Name in the config
		ItemStack name = new ItemStack(Material.NAME_TAG);
		ItemMeta nameMeta = name.getItemMeta();

		nameMeta.setDisplayName(utils.color("&aHome Name"));
		List<String> nameLore = new ArrayList<>();
		nameLore.add(utils.color("&6&lLeft Click &7to change the name of this home"));
		nameMeta.setLore(nameLore);
		name.setItemMeta(nameMeta);
		inv.setItem(15, name);



		player.openInventory(inv);



	}


	@EventHandler
	public void onClick(InventoryClickEvent event){
		Player player = (Player) event.getWhoClicked();
		ItemStack clicked = event.getCurrentItem();

		if (event.getView().getTitle().equals(utils.color("&aHome Attributes"))){
			event.setCancelled(true);

			if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR || !event.getCurrentItem().hasItemMeta()){
				return;
			}

//			Change the icon of the home to the item in your hand when you click the icon
//			if (clicked.getType() == Material.valueOf(player.getInventory().getItemInMainHand().getType().toString())){
//				if (clicked.getItemMeta().getDisplayName().equals(utils.color("&aHome Icon"))){
//					File playerConfig = new File("plugins/PlayerSpawn/PlayerHomes/" + player.getUniqueId() + ".yml");
//					FileConfiguration playerConfigData = YamlConfiguration.loadConfiguration(playerConfig);
//
////					playerConfigData.set(homeName + ".icon", icon);
//
//					playerConfigData.set("homeIcon", player.getInventory().getItemInMainHand().getType().toString());
//
//
//					player.sendMessage(utils.color("&aYou have changed the icon of your home to &6" + player.getInventory().getItemInMainHand().getType().toString()));
//					player.closeInventory();
//				}
//			}

		}
	}


	@EventHandler
	public void onChat(AsyncPlayerChatEvent event){
		Player player = event.getPlayer();
		String message = event.getMessage();

//		Here we can change the name of the home
		if (player.getOpenInventory().getTitle().equals(utils.color("&aHome Attributes"))){
			event.setCancelled(true);

			File playerConfig = new File("plugins/PlayerSpawn/PlayerHomes/" + player.getUniqueId() + ".yml");
			FileConfiguration playerConfigData = YamlConfiguration.loadConfiguration(playerConfig);

			playerConfigData.set("homeName", message);
			player.sendMessage(utils.color("&aHome name changed to " + message));
			player.closeInventory();
		}

	}



}
