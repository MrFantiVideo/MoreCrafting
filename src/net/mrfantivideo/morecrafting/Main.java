package net.mrfantivideo.morecrafting;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.Inventory;

public class Main extends JavaPlugin implements Listener {
    
	public static Main instance;
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		instance = this;
		
		ConfigSettings configSettings;
		configSettings = new ConfigSettings(this);
		configSettings.initCustomConfig();
		configSettings.getCustomConfig();
		
		ConfigMessages configMessages;
		configMessages = new ConfigMessages(this);
		configMessages.initCustomConfig();
		configMessages.getCustomConfig();
		
		ConfigPermissions configPermissions;
		configPermissions = new ConfigPermissions(this);
		configPermissions.initCustomConfig();
		configPermissions.getCustomConfig();
		
		if (cmd.getName().equalsIgnoreCase("morecrafting")) {
			if(args.length == 0) {
				sender.sendMessage(configMessages.getCustomConfig().getString("messages.default.prefix").replace("&", "§") + configMessages.getCustomConfig().getString("messages." + configSettings.getCustomConfig().getString("language") + "." + "command-unknown").replace("&", "§"));
				return true;
			}
			
			if((args.length == 1) && (args[0].equalsIgnoreCase("reload")))
				
				if(sender.hasPermission(configPermissions.getCustomConfig().getString("permissions.morecrafting.admin.reload")) || sender.hasPermission(configPermissions.getCustomConfig().getString("permissions.morecrafting.admin.reload.*"))) {
					
					configSettings = new ConfigSettings(this);
					configSettings.initCustomConfig();
					configSettings.getCustomConfig();
					
					configMessages = new ConfigMessages(this);
					configMessages.initCustomConfig();
					configMessages.getCustomConfig();
					
					configPermissions = new ConfigPermissions(this);
					configPermissions.initCustomConfig();
					configPermissions.getCustomConfig();
					
					sender.sendMessage(configMessages.getCustomConfig().getString("messages.default.prefix").replace("&", "§") + configMessages.getCustomConfig().getString("messages." + configSettings.getCustomConfig().getString("language") + "." + "command-reload").replace("&", "§"));
					return true;
				}
				else {
					sender.sendMessage(configMessages.getCustomConfig().getString("messages.default.prefix").replace("&", "§") + configMessages.getCustomConfig().getString("messages." + configSettings.getCustomConfig().getString("language") + "." + "permission-denied").replace("&", "§"));
					return true;
				}
			else {
				sender.sendMessage(configMessages.getCustomConfig().getString("messages.default.prefix").replace("&", "§") + configMessages.getCustomConfig().getString("messages." + configSettings.getCustomConfig().getString("language") + "." + "command-unknown").replace("&", "§"));
			}
		}
		
		return false;
	}
	
	
	
	@SuppressWarnings({ "deprecation"})
	public void onEnable() {
		
		instance = this;
		
		ConfigSettings configSettings;
		configSettings = new ConfigSettings(this);
		configSettings.initCustomConfig();
		configSettings.getCustomConfig();
		
		ConfigMessages configMessages;
		configMessages = new ConfigMessages(this);
		configMessages.initCustomConfig();
		configMessages.getCustomConfig();
		
		ConfigPermissions configPermissions;
		configPermissions = new ConfigPermissions(this);
		configPermissions.initCustomConfig();
		configPermissions.getCustomConfig();

		this.getServer().getPluginManager().registerEvents(this, this);
		
		getServer().getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "== " + ChatColor.YELLOW + "MoreCrafting" + ChatColor.DARK_GRAY + " =====================");
		System.out.println(" ");
		getServer().getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "Plugin : " + ChatColor.GREEN + "ON" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Minecraft 1.13.2");
		System.out.println(" ");
		getServer().getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "Version " + ChatColor.YELLOW + "2.0");
		getServer().getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "Created by " + ChatColor.YELLOW + "MrFantiVideo" + ChatColor.DARK_GRAY + " | 2018 - 2019");
		System.out.println(" ");
		getServer().getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "=====================================");
	
		//
		// ShapedRecipe V2.0
		//
		
		for(String str : configSettings.getCustomConfig().getConfigurationSection("recipes.crafting").getKeys(false)) {
			
			String itemPath = "recipes.crafting." + str + ".";
			
			if(configSettings.getCustomConfig().getBoolean(itemPath + "enabled") == false)
				continue;
			
				ShapedRecipe craft = new ShapedRecipe(NamespacedKey.randomKey(), new ItemStack(Material.getMaterial(configSettings.getCustomConfig().getString(itemPath + "craft.result.id")), configSettings.getCustomConfig().getInt(itemPath + "craft.result.id-amount")));
				craft.shape(new String[] {"123","456","789"});
				
				for(int i = 1; i <= 9; i++) {
					
					String itemID = configSettings.getCustomConfig().getString(itemPath + "craft.slots." + i);
					
					if(itemID == null || itemID.isEmpty())
						continue;
					
					    craft.setIngredient(Integer.toString(i).charAt(0), Material.getMaterial(configSettings.getCustomConfig().getString(itemPath + "craft.slots." + i)));
				}
				
			getServer().addRecipe(craft);

		}
		
		//
		// FurnaceRecipe V2.0
		//
		
		for(String str : configSettings.getCustomConfig().getConfigurationSection("recipes.furnace").getKeys(false)) {
			
			String itemPath = "recipes.furnace." + str + ".";
			
			if(configSettings.getCustomConfig().getBoolean(itemPath + "enabled") == false)
				continue;
			
				ItemStack result1 = new ItemStack(Material.getMaterial(configSettings.getCustomConfig().getString(itemPath + "fire.result.id")), configSettings.getCustomConfig().getInt(itemPath + "fire.result.id-amount"));
				FurnaceRecipe fire1 = new FurnaceRecipe(result1, Material.getMaterial(configSettings.getCustomConfig().getString(itemPath + "fire.slot.1")));
				
			getServer().addRecipe(fire1);
		
		}
		
		//
		// ShapedRecipe of Book V2.0
		//
		
		if(configSettings.getCustomConfig().getString("others.book.enabled").equals("true")) {	
			
			ItemStack book = new ItemStack(Material.getMaterial(configSettings.getCustomConfig().getString("others.book.craft.result.id")), 1);
			ItemMeta meta = book.getItemMeta();
			meta.setDisplayName(configSettings.getCustomConfig().getString("others.book.craft.result.name").replace("&", "§"));
			meta.setLore(Arrays.asList(configSettings.getCustomConfig().getString("others.book.craft.result.lore").replace("&", "§")));
			book.setItemMeta(meta);

			ShapedRecipe craft = new ShapedRecipe(NamespacedKey.randomKey(), book);
			craft.shape(new String[] {"123","456","789"});
			
			for(int i = 1; i <= 9; i++) {
				
				String itemID = configSettings.getCustomConfig().getString("others.book.craft.slots." + i);
				
				if(itemID == null || itemID.isEmpty())
					continue;
				
				    craft.setIngredient(Integer.toString(i).charAt(0), Material.getMaterial(configSettings.getCustomConfig().getString("others.book.craft.slots." + i)));
			
				}
			
			getServer().addRecipe(craft);
			
		}
	
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		
		instance = this;
		
		ConfigSettings configSettings;
		configSettings = new ConfigSettings(this);
		configSettings.initCustomConfig();
		configSettings.getCustomConfig();
		
		ConfigMessages configMessages;
		configMessages = new ConfigMessages(this);
		configMessages.initCustomConfig();
		configMessages.getCustomConfig();
		
		ConfigPermissions configPermissions;
		configPermissions = new ConfigPermissions(this);
		configPermissions.initCustomConfig();
		configPermissions.getCustomConfig();
		
		Player player = event.getPlayer(); 
		Action action = event.getAction();
		ItemStack it = event.getItem();
		
		if(it == null) return;
		
		if(it.getType() == Material.getMaterial(configSettings.getCustomConfig().getString("others.book.craft.result.id")) && it.hasItemMeta() && it.getItemMeta().hasDisplayName() && it.getItemMeta().getDisplayName().equalsIgnoreCase(configSettings.getCustomConfig().getString("others.book.craft.result.name").replace("&", "§"))) {
			if(player.hasPermission(configPermissions.getCustomConfig().getString("permissions.morecrafting.book")) || player.hasPermission(configPermissions.getCustomConfig().getString("permissions.morecrafting.*"))) {
				if(action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
				
					//
					// GUI Menu of Book V2.0
					//
					
					Inventory inv = Bukkit.createInventory(null, configSettings.getCustomConfig().getInt("others.book.gui.menu.size"), configMessages.getCustomConfig().getString("messages.default.prefix").replace("&", "§") + configMessages.getCustomConfig().getString("messages." + configSettings.getCustomConfig().getString("language") + "." + "gui-title-main").replace("&", "§")); {
					
					for(String str : configSettings.getCustomConfig().getConfigurationSection("recipes.crafting").getKeys(false)) {	
						
						String itemPath = "recipes.crafting." + str + ".";
						
						if(configSettings.getCustomConfig().getBoolean(itemPath + "enabled") == false)
							continue;
							
							ItemStack craft = new ItemStack(Material.getMaterial(configSettings.getCustomConfig().getString(itemPath + "craft.result.id")));
							ItemMeta item = craft.getItemMeta();
							
							for(int i = 1; i <= 9; i++) {
								
								String itemNAME = configSettings.getCustomConfig().getString(itemPath + "craft.result.name");
								
								if(itemNAME == null || itemNAME.isEmpty())
									continue;
								
								item.setDisplayName(configSettings.getCustomConfig().getString(itemPath + "craft.result.name").replace("&", "§"));
							}
							
							for(int i = 1; i <= 9; i++) {
								
								String itemLORE = configSettings.getCustomConfig().getString(itemPath + "craft.result.lore");
								
								if(itemLORE == null || itemLORE.isEmpty())
									continue;
								
								item.setLore(Arrays.asList(configSettings.getCustomConfig().getString(itemPath + "craft.result.lore").replace("&", "§")));
							}

							craft.setItemMeta(item);
							inv.setItem(configSettings.getCustomConfig().getInt(itemPath + "craft.result.id-book"), craft);
							
					}
					
					for(String str : configSettings.getCustomConfig().getConfigurationSection("recipes.furnace").getKeys(false)) {	
						
						String itemPath = "recipes.furnace." + str + ".";
						
						if(configSettings.getCustomConfig().getBoolean(itemPath + "enabled") == false)
							continue;
							
							ItemStack furnace = new ItemStack(Material.getMaterial(configSettings.getCustomConfig().getString(itemPath + "fire.result.id")));
							ItemMeta item = furnace.getItemMeta();

							// NAME OF FIRE
							
							String itemNAME = configSettings.getCustomConfig().getString(itemPath + "fire.result.name");
								
							if(itemNAME == null || itemNAME.isEmpty())
								continue;
								
								item.setDisplayName(configSettings.getCustomConfig().getString(itemPath + "fire.result.name").replace("&", "§"));

							// LORE OF FIRE
							
							String itemLORE = configSettings.getCustomConfig().getString(itemPath + "fire.result.lore");
								
							if(itemLORE == null || itemLORE.isEmpty()) 
								
								item.setLore(Arrays.asList(configSettings.getCustomConfig().getString(itemPath + "fire.result.lore").replace("&", "§")));
								
								else
									
								item.setLore(Arrays.asList(configMessages.getCustomConfig().getString("messages." + configSettings.getCustomConfig().getString("language") + ".name-fire-" + str).replace("&", "§")));
								
							
							furnace.setItemMeta(item);
							inv.setItem(configSettings.getCustomConfig().getInt(itemPath + "fire.result.id-book"), furnace);
							
					}
				}
					
					player.playSound(player.getLocation(), Sound.ITEM_ARMOR_EQUIP_ELYTRA, 1, 1);
					player.openInventory(inv);
					
				}
			}
			
			else {
					
				player.sendMessage(configMessages.getCustomConfig().getString("messages.default.prefix").replace("&", "§") + configMessages.getCustomConfig().getString("messages." + configSettings.getCustomConfig().getString("language") + "." + "permission-denied").replace("&", "§"));
				
			}
		}
	}

	@EventHandler
	public void onClick(InventoryClickEvent event) {
		
		instance = this;
		
		ConfigSettings configSettings;
		configSettings = new ConfigSettings(this);
		configSettings.initCustomConfig();
		configSettings.getCustomConfig();
		
		ConfigMessages configMessages;
		configMessages = new ConfigMessages(this);
		configMessages.initCustomConfig();
		configMessages.getCustomConfig();
		
		ConfigPermissions configPermissions;
		configPermissions = new ConfigPermissions(this);
		configPermissions.initCustomConfig();
		configPermissions.getCustomConfig();
		
		Inventory inv = event.getInventory();
		Player player = (Player) event.getWhoClicked();
		ItemStack current = event.getCurrentItem();
		
		event.setCancelled(true);
		
		if(current == null) return;
		
		if(inv.getName().equalsIgnoreCase(configMessages.getCustomConfig().getString("messages.default.prefix").replace("&", "§") + configMessages.getCustomConfig().getString("messages." + configSettings.getCustomConfig().getString("language") + "." + "gui-title-main").replace("&", "§"))) {
			
			//
			// GUI Crafting of Book V2.0
			//
			
			for(String str : configSettings.getCustomConfig().getConfigurationSection("recipes.crafting").getKeys(false)) {	
			
				String itemPath = "recipes.crafting." + str + ".";
			
				if(current.getType() == Material.getMaterial(configSettings.getCustomConfig().getString(itemPath + "craft.result.id")) && current.hasItemMeta() && (current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase(configSettings.getCustomConfig().getString(itemPath + "craft.result.name").replace("&", "§")) || (current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase(configMessages.getCustomConfig().getString("messages." + configSettings.getCustomConfig().getString("language") + ".name-craft-" + str).replace("&", "§"))))) {

					Inventory gui1 = Bukkit.createInventory(null, InventoryType.WORKBENCH, "Crafting");
				
					ItemStack craft1 = new ItemStack(Material.getMaterial(configSettings.getCustomConfig().getString(itemPath + "craft.result.id")), configSettings.getCustomConfig().getInt(itemPath + "craft.result.id-amount"));
					ItemMeta item1 = craft1.getItemMeta();
					craft1.setItemMeta(item1);
				
					for(int i = 1; i <= 9; i++) {
						
						String itemID = configSettings.getCustomConfig().getString(itemPath + "craft.slots." + i);
						
						if(itemID == null || itemID.isEmpty())
							continue;
						
						ItemStack craft2 = new ItemStack(Material.getMaterial(configSettings.getCustomConfig().getString(itemPath + "craft.slots." + i)));
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
			
			for(String str : configSettings.getCustomConfig().getConfigurationSection("recipes.furnace").getKeys(false)) {	
				
				String itemPath = "recipes.furnace." + str + ".";
			
				if(current.getType() == Material.getMaterial(configSettings.getCustomConfig().getString(itemPath + "fire.result.id")) && current.hasItemMeta() && (current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase(configSettings.getCustomConfig().getString(itemPath + "fire.result.name").replace("&", "§")) || (current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase(configMessages.getCustomConfig().getString("messages." + configSettings.getCustomConfig().getString("language") + ".name-fire-" + str).replace("&", "§"))))) {
					
					Inventory gui1 = Bukkit.createInventory(null, InventoryType.FURNACE, "Furnace");
				
					ItemStack craft1 = new ItemStack(Material.getMaterial(configSettings.getCustomConfig().getString(itemPath + "fire.result.id")), configSettings.getCustomConfig().getInt(itemPath + "fire.result.id-amount"));
					ItemMeta item1 = craft1.getItemMeta();
					craft1.setItemMeta(item1);
				
					ItemStack craft2 = new ItemStack(Material.getMaterial(configSettings.getCustomConfig().getString(itemPath + "fire.slot.1")));
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
		
		if(inv.getName().equalsIgnoreCase(configMessages.getCustomConfig().getString("messages.default.prefix").replace("&", "§") + configMessages.getCustomConfig().getString("messages." + configSettings.getCustomConfig().getString("language") + "." + "gui-title-craft").replace("&", "§"))) {
			
			event.setCancelled(true);
			
			if(current.getType() == Material.getMaterial(configSettings.getCustomConfig().getString("items.setting.id-gui-2"))) {
				
				player.closeInventory();
			
			}
		}
		
		if(inv.getName().equalsIgnoreCase(configMessages.getCustomConfig().getString("messages.default.prefix").replace("&", "§") + configMessages.getCustomConfig().getString("messages." + configSettings.getCustomConfig().getString("language") + "." + "gui-title-furnace").replace("&", "§"))) {
			
			event.setCancelled(true);
			
			if(current.getType() == Material.getMaterial(configSettings.getCustomConfig().getString("items.setting.id-gui-2"))) {
				
				player.closeInventory();
				
			}
		}
		
	}
	
	public void onDisable() {
		getServer().getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "== " + ChatColor.YELLOW + "MoreCrafting" + ChatColor.DARK_GRAY + " =====================");
		System.out.println(" ");
		getServer().getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "Plugin : " + ChatColor.RED + "OFF" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Minecraft 1.13.2");
		System.out.println(" ");
		getServer().getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "Version " + ChatColor.YELLOW + "2.0");
		getServer().getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "Created by " + ChatColor.YELLOW + "MrFantiVideo" + ChatColor.DARK_GRAY + " | 2018 - 2019");
		System.out.println(" ");
		getServer().getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "=====================================");
	}

}
