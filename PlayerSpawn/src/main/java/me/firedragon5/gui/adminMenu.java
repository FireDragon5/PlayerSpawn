//package me.firedragon5.gui;
//
//import me.firedragon5.playerspawn.utils;
//import org.bukkit.Bukkit;
//import org.bukkit.Material;
//import org.bukkit.configuration.file.FileConfiguration;
//import org.bukkit.configuration.file.YamlConfiguration;
//import org.bukkit.entity.Player;
//import org.bukkit.event.EventHandler;
//import org.bukkit.event.Listener;
//import org.bukkit.event.inventory.InventoryClickEvent;
//import org.bukkit.inventory.Inventory;
//import org.bukkit.inventory.ItemStack;
//import org.bukkit.inventory.meta.ItemMeta;
//
//import java.io.File;
//import java.util.UUID;
//
//public class adminMenu implements Listener {
//
//
//
//	public Inventory inv;
//
//	public void adminMenu(String args[]) {
//
//		String playerName = args[0];
//
//		UUID playerUUID = Bukkit.getPlayer(playerName).getUniqueId();
//
///
//		File playerConfig = new File("plugins/PlayerSpawn/PlayerHomes/" + playerUUID + ".yml");
//		FileConfiguration playerConfigData = YamlConfiguration.loadConfiguration(playerConfig);
//
//
//		int invSize = playerConfigData.getInt("homeCount");
//
//		if (invSize < 9) {
//			invSize = 9;
//		} else if (invSize > 9){
//			invSize = 54;
//		}
//
//		inv = Bukkit.createInventory(null, invSize, utils.color( "&a Homes"));
//
//		int beds = playerConfigData.getInt("homeCount");
//
//
//		for (int i = 0; i < beds; i += beds){
//
//			ItemStack item = new ItemStack(Material.RED_BED);
//			ItemMeta meta = item.getItemMeta();
//
//			for (String key : playerConfigData.getKeys(false)){
//				if (!key.equals("homeCount")){
//					meta.setDisplayName(utils.color("&a" + key));
//					item.setItemMeta(meta);
//					inv.addItem(item);
//
//				}
//
//
//			}
//
//
//		}
//
//
//
//
//
//	}
//
//
//
//	@EventHandler
//	public void onClick(InventoryClickEvent event){
//
//
//
//		Player player = (Player) event.getWhoClicked();
//
//		File playerConfig = new File("plugins/PlayerSpawn/PlayerHomes/" + player.getUniqueId() + ".yml");
//		FileConfiguration playerConfigData = YamlConfiguration.loadConfiguration(playerConfig);
//
//		if (event.getView().getTitle().equals(utils.color("&a Homes"))){
//			event.setCancelled(true);
//			if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR || !event.getCurrentItem().hasItemMeta()){
//				return;
//			}
//
//			for (String key : playerConfigData.getKeys(false)){
//				if (event.getCurrentItem().getItemMeta().getDisplayName().equals(utils.color("&a" + key))){
//					player.teleport(playerConfigData.getLocation(key));
//					player.sendMessage(utils.color("&aTeleported to home " + key));
//				}
//			}
//
//
//		}
//	}
//
//
//
//}
