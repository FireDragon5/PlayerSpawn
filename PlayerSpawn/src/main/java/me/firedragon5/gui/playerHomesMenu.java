package me.firedragon5.gui;

import com.sun.org.apache.xml.internal.utils.ListingErrorHandler;
import me.firedragon5.playerspawn.utils;
import org.bukkit.Bukkit;
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

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;

public class playerHomesMenu implements Listener {

	public Inventory inv;

	public void playerHomes(Player player) {

		File playerConfig = new File("plugins/PlayerSpawn/PlayerHomes/" + player.getUniqueId() + ".yml");
		FileConfiguration playerConfigData = YamlConfiguration.loadConfiguration(playerConfig);


		int invSize = playerConfigData.getInt("homeCount");

		if (invSize < 9) {
			invSize = 9;
		} else if (invSize > 9){
			invSize = 54;
		}

		inv = Bukkit.createInventory(null, invSize, utils.color("&aYour Homes"));

		int beds = playerConfigData.getInt("homeCount");

		String icon = playerConfigData.getString("homeIcon");

		if (icon == null){
			icon = "RED_BED";
		}


		for (int i = 0; i < beds; i += beds){

			ItemStack item = new ItemStack(Material.valueOf(icon));
			ItemMeta meta = item.getItemMeta();

			for (String key : playerConfigData.getKeys(false)){
				if (!key.equals("homeCount")){
					ArrayList <String> lore = new ArrayList<>();
					meta.setDisplayName(utils.color("&a" + key));
					lore.add(utils.color("&6&lLeft Click &7to teleport to this home"));
					lore.add(utils.color("&g&lRight Click &7to change attributes"));
					meta.setLore(lore);
					item.setItemMeta(meta);
					inv.addItem(item);

				}


			}


		}

		player.openInventory(inv);


	}



	@EventHandler
	public void onClick(InventoryClickEvent event){



		Player player = (Player) event.getWhoClicked();

		File playerConfig = new File("plugins/PlayerSpawn/PlayerHomes/" + player.getUniqueId() + ".yml");
		FileConfiguration playerConfigData = YamlConfiguration.loadConfiguration(playerConfig);



		if (event.getView().getTitle().equals(utils.color("&aYour Homes"))){
			event.setCancelled(true);
			if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR || !event.getCurrentItem().hasItemMeta()){
				return;
			}
//		Add a left click and right click event
			if (event.getClick().isLeftClick()){

//				player.performCommand("home " + event.getCurrentItem().getItemMeta().getDisplayName());
				for (String key : playerConfigData.getKeys(false)){
					if (event.getCurrentItem().getItemMeta().getDisplayName().equals(utils.color("&a" + key))){
						player.teleport(playerConfigData.getLocation(key));
						player.sendMessage(utils.color("&aTeleported to home " + key));
					}
				}



//		Right click
			} else if (event.getClick().isRightClick()){

				homeAttributesMenu menu = new homeAttributesMenu();

				menu.attributesMenu(player, event.getCurrentItem().getItemMeta().getDisplayName());
			}




		}
	}





}
