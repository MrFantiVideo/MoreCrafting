package net.mrfantivideo.morecrafting.Listeners;

import net.mrfantivideo.morecrafting.Main;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerInventoryListener implements Listener
{
    @EventHandler
    public void OnPlayerInventoryClick(InventoryClickEvent event)
    {
        Player player = (Player) event.getWhoClicked();
        Inventory inv = event.getInventory();
        ItemStack stack = event.getCurrentItem();
        
        if(stack == null)
            return;

        if(inv.getName().equalsIgnoreCase(Main.GetInstance().GetConfigMessages().GetConfiguration().getString("messages.default.prefix").replace("&", "§") + Main.GetInstance().GetConfigMessages().GetConfiguration().getString("messages." + Main.GetInstance().GetConfigSettings().GetConfiguration().getString("language") + "." + "gui-title-main").replace("&", "§")))
        {
        	for(String str : Main.GetInstance().GetConfigSettings().GetConfiguration().getConfigurationSection("recipes.crafting").getKeys(false)) {	
    			
				String itemPath = "recipes.crafting." + str + ".";
			
				event.setCancelled(true);
				
				if(stack.getType() == Material.getMaterial(Main.GetInstance().GetConfigSettings().GetConfiguration().getString(itemPath + "craft.result.id")) 
					&& stack.hasItemMeta() && (stack.getItemMeta().hasDisplayName() 
					&& stack.getItemMeta().getDisplayName().equalsIgnoreCase(Main.GetInstance().GetConfigSettings().GetConfiguration().getString(itemPath + "craft.result.name").replace("&", "§")) 
					|| (stack.getItemMeta().hasDisplayName() && stack.getItemMeta().getDisplayName().equalsIgnoreCase(Main.GetInstance().GetConfigSettings().GetConfiguration().getString("messages." + Main.GetInstance().GetConfigSettings().GetConfiguration().getString("language") + ".name-craft-" + str).replace("&", "§"))))) {

					Inventory gui1 = Bukkit.createInventory(null, InventoryType.WORKBENCH, "Crafting");
				
					ItemStack craft1 = new ItemStack(Material.getMaterial(Main.GetInstance().GetConfigSettings().GetConfiguration().getString(itemPath + "craft.result.id")), Main.GetInstance().GetConfigSettings().GetConfiguration().getInt(itemPath + "craft.result.id-amount"));
					ItemMeta item1 = craft1.getItemMeta();
					craft1.setItemMeta(item1);
				
					for(int i = 1; i <= 9; i++) {
						
						String itemID = Main.GetInstance().GetConfigSettings().GetConfiguration().getString(itemPath + "craft.slots." + i);
						
						if(itemID == null || itemID.isEmpty())
							continue;
						
						ItemStack craft2 = new ItemStack(Material.getMaterial(Main.GetInstance().GetConfigSettings().GetConfiguration().getString(itemPath + "craft.slots." + i)));
						ItemMeta item2 = craft2.getItemMeta();
						craft2.setItemMeta(item2);
						
						gui1.setItem(0, craft1);
						gui1.setItem(i, craft2);
						
					}
				
					player.openInventory(gui1);
				
				}
			
			}
			
			//
			// GUI Furnace of Book V2.0
			//
			
			for(String str : Main.GetInstance().GetConfigSettings().GetConfiguration().getConfigurationSection("recipes.furnace").getKeys(false)) {	
				
				String itemPath = "recipes.furnace." + str + ".";
			
				event.setCancelled(true);
				
				if(stack.getType() == Material.getMaterial(Main.GetInstance().GetConfigSettings().GetConfiguration().getString(itemPath + "fire.result.id")) 
					&& stack.hasItemMeta() && (stack.getItemMeta().hasDisplayName() 
					&& stack.getItemMeta().getDisplayName().equalsIgnoreCase(Main.GetInstance().GetConfigSettings().GetConfiguration().getString(itemPath + "fire.result.name").replace("&", "§")) 
					|| (stack.getItemMeta().hasDisplayName() && stack.getItemMeta().getDisplayName().equalsIgnoreCase(Main.GetInstance().GetConfigSettings().GetConfiguration().getString("messages." + Main.GetInstance().GetConfigSettings().GetConfiguration().getString("language") + ".name-fire-" + str).replace("&", "§"))))) {
					
					Inventory gui1 = Bukkit.createInventory(null, InventoryType.FURNACE, "Furnace");
				
					ItemStack craft1 = new ItemStack(Material.getMaterial(Main.GetInstance().GetConfigSettings().GetConfiguration().getString(itemPath + "fire.result.id")), Main.GetInstance().GetConfigSettings().GetConfiguration().getInt(itemPath + "fire.result.id-amount"));
					ItemMeta item1 = craft1.getItemMeta();
					craft1.setItemMeta(item1);
				
					ItemStack craft2 = new ItemStack(Material.getMaterial(Main.GetInstance().GetConfigSettings().GetConfiguration().getString(itemPath + "fire.slot.1")));
					ItemMeta item2 = craft2.getItemMeta();
					craft2.setItemMeta(item2);
					
					ItemStack craft3 = new ItemStack(Material.COAL);
					ItemMeta item3 = craft3.getItemMeta();
					craft3.setItemMeta(item3);
				
					gui1.setItem(2, craft1);
					gui1.setItem(0, craft2);
					gui1.setItem(1, craft3);
				
				player.openInventory(gui1);
				
				}
			}
        }
    }
}
