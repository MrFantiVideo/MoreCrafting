package net.mrfantivideo.morecrafting;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.Inventory;

public class Main extends JavaPlugin implements Listener{
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		if (cmd.getName().equalsIgnoreCase("morecrafting")){
			if(args.length == 0){
				sender.sendMessage(getConfig().getString("messages.default.prefix").replace("&", "§") + getConfig().getString("messages." + getConfig().getString("language") + "." + "command-unknown").replace("&", "§"));
				return true;
			}
			
			if((args.length == 1) && (args[0].equalsIgnoreCase("reload")))
				
				if(sender.hasPermission("morecrafting.admin.reload")){
					reloadConfig();
					sender.sendMessage(getConfig().getString("messages.default.prefix").replace("&", "§") + getConfig().getString("messages." + getConfig().getString("language") + "." + "command-reload").replace("&", "§"));
					return true;
				}
				else {
					sender.sendMessage(getConfig().getString("messages.default.prefix").replace("&", "§") + getConfig().getString("messages." + getConfig().getString("language") + "." + "permission-denied").replace("&", "§"));
					return true;
				}
			}
		
		return false;
	}
	

	@SuppressWarnings("deprecation")
	public void onEnable(){
		
		
		saveDefaultConfig();
		this.getServer().getPluginManager().registerEvents(this, this);
		
		getServer().getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "== " + ChatColor.YELLOW + "MoreCrafting" + ChatColor.DARK_GRAY + " =====================");
		System.out.println(" ");
		getServer().getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "Plugin : " + ChatColor.GREEN + "ON" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Minecraft 1.13.2");
		System.out.println(" ");
		getServer().getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "Version " + ChatColor.YELLOW + "1.1");
		getServer().getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "Created by " + ChatColor.YELLOW + "MrFantiVideo" + ChatColor.DARK_GRAY + " | 2018 - 2019");
		System.out.println(" ");
		getServer().getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "=====================================");
	
		// List of all ShapedRecipe available

		if(getConfig().getString("items.craft1.enabled").equals("true")){	
			
			ShapedRecipe craft1 = new ShapedRecipe(new ItemStack(Material.getMaterial(getConfig().getString("items.craft1.id")), 9));
			craft1.shape(new String[] {"000","000","000"});
			craft1.setIngredient('0', Material.getMaterial(getConfig().getString("items.craft1.id-craft-1")));
			getServer().addRecipe(craft1);
			
		}
		
		if(getConfig().getString("items.craft2.enabled").equals("true")){	
		
			ShapedRecipe craft2 = new ShapedRecipe(new ItemStack(Material.getMaterial(getConfig().getString("items.craft2.id")), 9));
			craft2.shape(new String[] {"000","000","000"});
			craft2.setIngredient('0', Material.getMaterial(getConfig().getString("items.craft2.id-craft-1")));
			getServer().addRecipe(craft2);

		}
		
		if(getConfig().getString("items.craft3.enabled").equals("true")){	
		
			ShapedRecipe craft3 = new ShapedRecipe(new ItemStack(Material.getMaterial(getConfig().getString("items.craft3.id")), 9));
			craft3.shape(new String[] {"000","000","000"});
			craft3.setIngredient('0', Material.getMaterial(getConfig().getString("items.craft3.id-craft-1")));
			getServer().addRecipe(craft3);

		}
		
		if(getConfig().getString("items.craft4.enabled").equals("true")){	
		
			ShapedRecipe craft4 = new ShapedRecipe(new ItemStack(Material.getMaterial(getConfig().getString("items.craft4.id")), 9));
			craft4.shape(new String[] {"000","000","000"});
			craft4.setIngredient('0', Material.getMaterial(getConfig().getString("items.craft4.id-craft-1")));
			getServer().addRecipe(craft4);

		}
		
		if(getConfig().getString("items.craft5.enabled").equals("true")){	
		
			ShapedRecipe craft5 = new ShapedRecipe(new ItemStack(Material.getMaterial(getConfig().getString("items.craft5.id")), 6));
			craft5.shape(new String[] {"111","000","000"});
			craft5.setIngredient('0', Material.getMaterial(getConfig().getString("items.craft5.id-craft-1")));
			craft5.setIngredient('1', Material.getMaterial(getConfig().getString("items.craft5.id-craft-2")));
			getServer().addRecipe(craft5);

		}
		
		if(getConfig().getString("items.craft6.enabled").equals("true")){	
		
			ShapedRecipe craft6 = new ShapedRecipe(new ItemStack(Material.getMaterial(getConfig().getString("items.craft6.id")), 6));
			craft6.shape(new String[] {"111","000","000"});
			craft6.setIngredient('0', Material.getMaterial(getConfig().getString("items.craft6.id-craft-1")));
			craft6.setIngredient('1', Material.getMaterial(getConfig().getString("items.craft6.id-craft-2")));
			getServer().addRecipe(craft6);

		}
		
		if(getConfig().getString("items.craft7.enabled").equals("true")){	
		
			ShapedRecipe craft7 = new ShapedRecipe(new ItemStack(Material.getMaterial(getConfig().getString("items.craft7.id")), 1));
			craft7.shape(new String[] {"000","010","232"});
			craft7.setIngredient('0', Material.getMaterial(getConfig().getString("items.craft7.id-craft-1")));
			craft7.setIngredient('1', Material.getMaterial(getConfig().getString("items.craft7.id-craft-2")));
			craft7.setIngredient('2', Material.getMaterial(getConfig().getString("items.craft7.id-craft-3")));
			craft7.setIngredient('3', Material.getMaterial(getConfig().getString("items.craft7.id-craft-4")));
			getServer().addRecipe(craft7);

		}
		
		if(getConfig().getString("items.craft8.enabled").equals("true")){	
		
			ShapedRecipe craft8 = new ShapedRecipe(new ItemStack(Material.getMaterial(getConfig().getString("items.craft8.id")), 1));
			craft8.shape(new String[] {"12 ","230"," 03"});
			craft8.setIngredient('0', Material.getMaterial(getConfig().getString("items.craft8.id-craft-1")));
			craft8.setIngredient('1', Material.getMaterial(getConfig().getString("items.craft8.id-craft-2")));
			craft8.setIngredient('2', Material.getMaterial(getConfig().getString("items.craft8.id-craft-3")));
			craft8.setIngredient('3', Material.getMaterial(getConfig().getString("items.craft8.id-craft-4")));
			getServer().addRecipe(craft8);

		}
		
		if(getConfig().getString("items.craft9.enabled").equals("true")){	
			
			ShapedRecipe craft9 = new ShapedRecipe(new ItemStack(Material.getMaterial(getConfig().getString("items.craft9.id")), 1));
			craft9.shape(new String[] {"000","010","000"});
			craft9.setIngredient('0', Material.getMaterial(getConfig().getString("items.craft9.id-craft-1")));
			craft9.setIngredient('1', Material.getMaterial(getConfig().getString("items.craft9.id-craft-2")));
			getServer().addRecipe(craft9);
			
		}
		
		if(getConfig().getString("items.book.enabled").equals("true")){	
			
			ItemStack book = new ItemStack(Material.getMaterial(getConfig().getString("items.book.id")), 1);
			ItemMeta meta = book.getItemMeta();
			meta.setDisplayName(getConfig().getString("items.book.name").replace("&", "§"));
			meta.setLore(Arrays.asList(getConfig().getString("items.book.lore").replace("&", "§")));
			book.setItemMeta(meta);

			ShapedRecipe craft10 = new ShapedRecipe(book);
			craft10.shape(new String[] {"000","010","000"});
			craft10.setIngredient('0', Material.getMaterial(getConfig().getString("items.book.id-craft-1")));
			craft10.setIngredient('1', Material.getMaterial(getConfig().getString("items.book.id-craft-2")));
			getServer().addRecipe(craft10);
			
		}
		
		else{
			
			return;
			
		}
		
		// List of all FurnaceRecipe available
		
		if(getConfig().getString("items.fire1.enabled").equals("true")){	
		
			ItemStack result1 = new ItemStack(Material.getMaterial(getConfig().getString("items.fire1.id")));
			FurnaceRecipe fire1 = new FurnaceRecipe(result1, Material.getMaterial(getConfig().getString("items.fire1.id-furnace-1")));
			getServer().addRecipe(fire1);
		
		}
		
		if(getConfig().getString("items.fire2.enabled").equals("true")){	
			
			ItemStack result2 = new ItemStack(Material.getMaterial(getConfig().getString("items.fire2.id")));
			FurnaceRecipe fire2 = new FurnaceRecipe(result2, Material.getMaterial(getConfig().getString("items.fire2.id-furnace-1")));
			getServer().addRecipe(fire2);
		
		}
		
		if(getConfig().getString("items.fire3.enabled").equals("true")){	
			
			ItemStack result3 = new ItemStack(Material.getMaterial(getConfig().getString("items.fire3.id")));
			FurnaceRecipe fire3 = new FurnaceRecipe(result3, Material.getMaterial(getConfig().getString("items.fire3.id-furnace-1")));
			getServer().addRecipe(fire3);
		
		}
		
		if(getConfig().getString("items.fire4.enabled").equals("true")){	
			
			ItemStack result4 = new ItemStack(Material.getMaterial(getConfig().getString("items.fire4.id")));
			FurnaceRecipe fire4 = new FurnaceRecipe(result4, Material.getMaterial(getConfig().getString("items.fire4.id-furnace-1")));
			getServer().addRecipe(fire4);
		
		}
		
		if(getConfig().getString("items.fire5.enabled").equals("true")){	
			
			ItemStack result5 = new ItemStack(Material.getMaterial(getConfig().getString("items.fire5.id")), 4);
			FurnaceRecipe fire5 = new FurnaceRecipe(result5, Material.getMaterial(getConfig().getString("items.fire5.id-furnace-1")));
			getServer().addRecipe(fire5);
		
		}
		
		if(getConfig().getString("items.fire6.enabled").equals("true")){	
			
			ItemStack result6 = new ItemStack(Material.getMaterial(getConfig().getString("items.fire6.id")), 4);
			FurnaceRecipe fire6 = new FurnaceRecipe(result6, Material.getMaterial(getConfig().getString("items.fire6.id-furnace-1")));
			getServer().addRecipe(fire6);
		
		}
		
		if(getConfig().getString("items.fire7.enabled").equals("true")){	
			
			ItemStack result7 = new ItemStack(Material.getMaterial(getConfig().getString("items.fire7.id")), 4);
			FurnaceRecipe fire7 = new FurnaceRecipe(result7, Material.getMaterial(getConfig().getString("items.fire7.id-furnace-1")));
			getServer().addRecipe(fire7);
		
		}
		
		if(getConfig().getString("items.fire8.enabled").equals("true")){	
			
			ItemStack result8 = new ItemStack(Material.getMaterial(getConfig().getString("items.fire8.id")), 4);
			FurnaceRecipe fire8 = new FurnaceRecipe(result8, Material.getMaterial(getConfig().getString("items.fire8.id-furnace-1")));
			getServer().addRecipe(fire8);
		
		}
		
		if(getConfig().getString("items.fire9.enabled").equals("true")){	
			
			ItemStack result9 = new ItemStack(Material.getMaterial(getConfig().getString("items.fire9.id")), 4);
			FurnaceRecipe fire9 = new FurnaceRecipe(result9, Material.getMaterial(getConfig().getString("items.fire9.id-furnace-1")));
			getServer().addRecipe(fire9);
		
		}
		
		if(getConfig().getString("items.fire10.enabled").equals("true")){	
			
			ItemStack result10 = new ItemStack(Material.getMaterial(getConfig().getString("items.fire10.id")), 4);
			FurnaceRecipe fire10 = new FurnaceRecipe(result10, Material.getMaterial(getConfig().getString("items.fire10.id-furnace-1")));
			getServer().addRecipe(fire10);
		
		}
		
		if(getConfig().getString("items.fire11.enabled").equals("true")){	
			
			ItemStack result11 = new ItemStack(Material.getMaterial(getConfig().getString("items.fire11.id")), 4);
			FurnaceRecipe fire11 = new FurnaceRecipe(result11, Material.getMaterial(getConfig().getString("items.fire11.id-furnace-1")));
			getServer().addRecipe(fire11);
		
		}
		
		if(getConfig().getString("items.fire12.enabled").equals("true")){	
			
			ItemStack result12 = new ItemStack(Material.getMaterial(getConfig().getString("items.fire12.id")), 9);
			FurnaceRecipe fire12 = new FurnaceRecipe(result12, Material.getMaterial(getConfig().getString("items.fire12.id-furnace-1")));
			fire12.setCookingTime(75);
			getServer().addRecipe(fire12);
		
		}
		
		if(getConfig().getString("items.fire13.enabled").equals("true")){	
			
			ItemStack result13 = new ItemStack(Material.getMaterial(getConfig().getString("items.fire13.id")), 9);
			FurnaceRecipe fire13 = new FurnaceRecipe(result13, Material.getMaterial(getConfig().getString("items.fire13.id-furnace-1")));
			fire13.setCookingTime(75);
			getServer().addRecipe(fire13);
		
		}
		
		if(getConfig().getString("items.fire14.enabled").equals("true")){	
			
			ItemStack result14 = new ItemStack(Material.getMaterial(getConfig().getString("items.fire14.id")), 4);
			FurnaceRecipe fire14 = new FurnaceRecipe(result14, Material.getMaterial(getConfig().getString("items.fire14.id-furnace-1")));
			fire14.setCookingTime(55);
			getServer().addRecipe(fire14);
		
		}
		
		else{
			
			return;
			
		}
	
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent event){
		
		Player player = event.getPlayer(); 
		Action action = event.getAction();
		ItemStack it = event.getItem();
		
		if(it == null) return;
		
		if(it.getType() == Material.getMaterial(getConfig().getString("items.book.id")) && it.hasItemMeta() && it.getItemMeta().hasDisplayName() && it.getItemMeta().getDisplayName().equalsIgnoreCase(getConfig().getString("items.book.name").replace("&", "§"))){
			if(player.hasPermission(getConfig().getString("items.book.permission")) || player.hasPermission("morecrafting.*")){
				if(action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK){
				
					Inventory inv = Bukkit.createInventory(null, 27, getConfig().getString("messages.default.prefix").replace("&", "§") + getConfig().getString("messages." + getConfig().getString("language") + "." + "gui-title-main").replace("&", "§"));{
					
					if(getConfig().getString("items.craft1.enabled").equals("true")){
					
					ItemStack craft1 = new ItemStack(Material.getMaterial(getConfig().getString("items.craft1.id")));
					ItemMeta item1 = craft1.getItemMeta();
					item1.setDisplayName(getConfig().getString("messages." + getConfig().getString("language") + "." + "name-craft1").replace("&", "§"));
					craft1.setItemMeta(item1);
					inv.setItem(0, craft1);
					
					}
					
					if(getConfig().getString("items.craft2.enabled").equals("true")){
					
					ItemStack craft2 = new ItemStack(Material.getMaterial(getConfig().getString("items.craft2.id")));
					ItemMeta item2 = craft2.getItemMeta();
					item2.setDisplayName(getConfig().getString("messages." + getConfig().getString("language") + "." + "name-craft2").replace("&", "§"));
					craft2.setItemMeta(item2);
					inv.setItem(1, craft2);
					
					}
					
					if(getConfig().getString("items.craft3.enabled").equals("true")){
					
					}
					
					ItemStack craft3 = new ItemStack(Material.getMaterial(getConfig().getString("items.craft3.id")));
					ItemMeta item3 = craft3.getItemMeta();
					item3.setDisplayName(getConfig().getString("messages." + getConfig().getString("language") + "." + "name-craft3").replace("&", "§"));
					craft3.setItemMeta(item3);
					inv.setItem(2, craft3);
					
					}
					
					if(getConfig().getString("items.craft4.enabled").equals("true")){
					
					ItemStack craft4 = new ItemStack(Material.getMaterial(getConfig().getString("items.craft4.id")));
					ItemMeta item4 = craft4.getItemMeta();
					item4.setDisplayName(getConfig().getString("messages." + getConfig().getString("language") + "." + "name-craft4").replace("&", "§"));
					craft4.setItemMeta(item4);
					inv.setItem(3, craft4);
					
					}
					
					if(getConfig().getString("items.craft5.enabled").equals("true")){
					
					
					ItemStack craft5 = new ItemStack(Material.getMaterial(getConfig().getString("items.craft5.id")));
					ItemMeta item5 = craft5.getItemMeta();
					item5.setDisplayName(getConfig().getString("messages." + getConfig().getString("language") + "." + "name-craft5").replace("&", "§"));
					craft5.setItemMeta(item5);
					inv.setItem(4, craft5);
					
					}
					
					if(getConfig().getString("items.craft6.enabled").equals("true")){
					
					
					ItemStack craft6 = new ItemStack(Material.getMaterial(getConfig().getString("items.craft6.id")));
					ItemMeta item6 = craft6.getItemMeta();
					item6.setDisplayName(getConfig().getString("messages." + getConfig().getString("language") + "." + "name-craft6").replace("&", "§"));
					craft6.setItemMeta(item6);
					inv.setItem(5, craft6);
					
					}
					
					if(getConfig().getString("items.craft7.enabled").equals("true")){
					
					
					ItemStack craft7 = new ItemStack(Material.getMaterial(getConfig().getString("items.craft7.id")));
					ItemMeta item7 = craft7.getItemMeta();
					item7.setDisplayName(getConfig().getString("messages." + getConfig().getString("language") + "." + "name-craft7").replace("&", "§"));
					craft7.setItemMeta(item7);
					inv.setItem(6, craft7);
					
					}
					
					if(getConfig().getString("items.craft8.enabled").equals("true")){
					
					
					ItemStack craft8 = new ItemStack(Material.getMaterial(getConfig().getString("items.craft8.id")));
					ItemMeta item8 = craft8.getItemMeta();
					item8.setDisplayName(getConfig().getString("messages." + getConfig().getString("language") + "." + "name-craft8").replace("&", "§"));
					craft8.setItemMeta(item8);
					inv.setItem(7, craft8);
					
					}
					
					if(getConfig().getString("items.craft9.enabled").equals("true")){
					
					
					ItemStack craft9 = new ItemStack(Material.getMaterial(getConfig().getString("items.craft9.id")));
					ItemMeta item9 = craft9.getItemMeta();
					item9.setDisplayName(getConfig().getString("messages." + getConfig().getString("language") + "." + "name-craft9").replace("&", "§"));
					craft9.setItemMeta(item9);
					inv.setItem(8, craft9);
					
					}
					
					if(getConfig().getString("items.fire1.enabled").equals("true")){
					
					
					ItemStack fire1 = new ItemStack(Material.getMaterial(getConfig().getString("items.fire1.id")));
					ItemMeta item10 = fire1.getItemMeta();
					item10.setDisplayName(getConfig().getString("messages." + getConfig().getString("language") + "." + "name-fire1").replace("&", "§"));
					fire1.setItemMeta(item10);
					inv.setItem(9, fire1);
					
					}
					
					if(getConfig().getString("items.fire2.enabled").equals("true")){
					
					
					ItemStack fire2 = new ItemStack(Material.getMaterial(getConfig().getString("items.fire2.id")));
					ItemMeta item11 = fire2.getItemMeta();
					item11.setDisplayName(getConfig().getString("messages." + getConfig().getString("language") + "." + "name-fire2").replace("&", "§"));
					fire2.setItemMeta(item11);
					inv.setItem(10, fire2);
					
					}
					
					if(getConfig().getString("items.fire3.enabled").equals("true")){
					
					
					ItemStack fire3 = new ItemStack(Material.getMaterial(getConfig().getString("items.fire3.id")));
					ItemMeta item12 = fire3.getItemMeta();
					item12.setDisplayName(getConfig().getString("messages." + getConfig().getString("language") + "." + "name-fire3").replace("&", "§"));
					fire3.setItemMeta(item12);
					inv.setItem(11, fire3);
					
					}
					
					if(getConfig().getString("items.fire4.enabled").equals("true")){
					
					
					ItemStack fire4 = new ItemStack(Material.getMaterial(getConfig().getString("items.fire4.id")));
					ItemMeta item13 = fire4.getItemMeta();
					item13.setDisplayName(getConfig().getString("messages." + getConfig().getString("language") + "." + "name-fire4").replace("&", "§"));
					fire4.setItemMeta(item13);
					inv.setItem(12, fire4);
					
					}
					
					if(getConfig().getString("items.fire5.enabled").equals("true")){
					
					
					ItemStack fire5 = new ItemStack(Material.getMaterial(getConfig().getString("items.fire5.id")));
					ItemMeta item14 = fire5.getItemMeta();
					item14.setDisplayName(getConfig().getString("messages." + getConfig().getString("language") + "." + "name-fire5").replace("&", "§"));
					fire5.setItemMeta(item14);
					inv.setItem(13, fire5);
					
					}
					
					if(getConfig().getString("items.fire6.enabled").equals("true")){
					
					
					ItemStack fire6 = new ItemStack(Material.getMaterial(getConfig().getString("items.fire6.id")));
					ItemMeta item15 = fire6.getItemMeta();
					item15.setDisplayName(getConfig().getString("messages." + getConfig().getString("language") + "." + "name-fire6").replace("&", "§"));
					fire6.setItemMeta(item15);
					inv.setItem(14, fire6);
					
					}
					
					if(getConfig().getString("items.fire7.enabled").equals("true")){
					
					
					ItemStack fire7 = new ItemStack(Material.getMaterial(getConfig().getString("items.fire7.id")));
					ItemMeta item16 = fire7.getItemMeta();
					item16.setDisplayName(getConfig().getString("messages." + getConfig().getString("language") + "." + "name-fire7").replace("&", "§"));
					fire7.setItemMeta(item16);
					inv.setItem(15, fire7);
					
					}
					
					if(getConfig().getString("items.fire8.enabled").equals("true")){
					
					
					ItemStack fire8 = new ItemStack(Material.getMaterial(getConfig().getString("items.fire8.id")));
					ItemMeta item17 = fire8.getItemMeta();
					item17.setDisplayName(getConfig().getString("messages." + getConfig().getString("language") + "." + "name-fire8").replace("&", "§"));
					fire8.setItemMeta(item17);
					inv.setItem(16, fire8);
					
					}
					
					if(getConfig().getString("items.fire9.enabled").equals("true")){
					
					
					ItemStack fire9 = new ItemStack(Material.getMaterial(getConfig().getString("items.fire9.id")));
					ItemMeta item18 = fire9.getItemMeta();
					item18.setDisplayName(getConfig().getString("messages." + getConfig().getString("language") + "." + "name-fire9").replace("&", "§"));
					fire9.setItemMeta(item18);
					inv.setItem(17, fire9);
					
					}
					
					if(getConfig().getString("items.fire10.enabled").equals("true")){
					
					
					ItemStack fire10 = new ItemStack(Material.getMaterial(getConfig().getString("items.fire10.id")));
					ItemMeta item19 = fire10.getItemMeta();
					item19.setDisplayName(getConfig().getString("messages." + getConfig().getString("language") + "." + "name-fire10").replace("&", "§"));
					fire10.setItemMeta(item19);
					inv.setItem(18, fire10);
					
					}
					
					if(getConfig().getString("items.fire11.enabled").equals("true")){
					
					
					ItemStack fire11 = new ItemStack(Material.getMaterial(getConfig().getString("items.fire11.id")));
					ItemMeta item20 = fire11.getItemMeta();
					item20.setDisplayName(getConfig().getString("messages." + getConfig().getString("language") + "." + "name-fire11").replace("&", "§"));
					fire11.setItemMeta(item20);
					inv.setItem(19, fire11);
					
					}
					
					if(getConfig().getString("items.fire12.enabled").equals("true")){
					
					
					ItemStack fire12 = new ItemStack(Material.getMaterial(getConfig().getString("items.fire12.id")));
					ItemMeta item21 = fire12.getItemMeta();
					item21.setDisplayName(getConfig().getString("messages." + getConfig().getString("language") + "." + "name-fire12").replace("&", "§"));
					fire12.setItemMeta(item21);
					inv.setItem(20, fire12);
					
					}
					
					if(getConfig().getString("items.fire13.enabled").equals("true")){
					
					
					ItemStack fire13 = new ItemStack(Material.getMaterial(getConfig().getString("items.fire13.id")));
					ItemMeta item22 = fire13.getItemMeta();
					item22.setDisplayName(getConfig().getString("messages." + getConfig().getString("language") + "." + "name-fire13").replace("&", "§"));
					fire13.setItemMeta(item22);
					inv.setItem(21, fire13);
					
					}
					
					if(getConfig().getString("items.fire14.enabled").equals("true")){
					
					
					ItemStack fire14 = new ItemStack(Material.getMaterial(getConfig().getString("items.fire14.id")));
					ItemMeta item23 = fire14.getItemMeta();
					item23.setDisplayName(getConfig().getString("messages." + getConfig().getString("language") + "." + "name-fire14").replace("&", "§"));
					fire14.setItemMeta(item23);
					inv.setItem(22, fire14);
					
					}
					
					player.playSound(player.getLocation(), Sound.ITEM_ARMOR_EQUIP_ELYTRA, 1, 1);
					player.openInventory(inv);
					
				}
				
			}
			else {
					
				player.sendMessage(getConfig().getString("messages.default.prefix").replace("&", "§") + getConfig().getString("messages." + getConfig().getString("language") + "." + "permission-denied").replace("&", "§"));
				
			}
				
		}
	}

	@EventHandler
	public void onClick(InventoryClickEvent event){
		
		Inventory inv = event.getInventory();
		Player player = (Player) event.getWhoClicked();
		ItemStack current = event.getCurrentItem();
		
		if(current == null) return;
		
		if(inv.getName().equalsIgnoreCase(getConfig().getString("messages.default.prefix").replace("&", "§") + getConfig().getString("messages." + getConfig().getString("language") + "." + "gui-title-main").replace("&", "§"))){
	
			event.setCancelled(true);
			
			if(current.getType() == Material.getMaterial(getConfig().getString("items.craft1.id"))){
				
				Inventory gui1 = Bukkit.createInventory(null, 27, getConfig().getString("messages.default.prefix").replace("&", "§") + getConfig().getString("messages." + getConfig().getString("language") + "." + "gui-title-craft").replace("&", "§"));
				
				ItemStack craft1 = new ItemStack(Material.getMaterial(getConfig().getString("items.craft1.id")), 9);
				ItemMeta item1 = craft1.getItemMeta();
				craft1.setItemMeta(item1);
				
				ItemStack craft2 = new ItemStack(Material.getMaterial(getConfig().getString("items.craft1.id-craft-1")));
				ItemMeta item2 = craft2.getItemMeta();
				craft2.setItemMeta(item2);
				
				gui1.setItem(15, craft1);
				gui1.setItem(1, craft2);
				gui1.setItem(2, craft2);
				gui1.setItem(3, craft2);
				gui1.setItem(10, craft2);
				gui1.setItem(11, craft2);
				gui1.setItem(12, craft2);
				gui1.setItem(19, craft2);
				gui1.setItem(20, craft2);
				gui1.setItem(21, craft2);
				
				player.openInventory(gui1);
				
			}
			
			if(current.getType() == Material.getMaterial(getConfig().getString("items.craft2.id"))){
				
				Inventory gui1 = Bukkit.createInventory(null, 27, getConfig().getString("messages.default.prefix").replace("&", "§") + getConfig().getString("messages." + getConfig().getString("language") + "." + "gui-title-craft").replace("&", "§"));
				
				ItemStack craft1 = new ItemStack(Material.getMaterial(getConfig().getString("items.craft2.id")), 9);
				ItemMeta item1 = craft1.getItemMeta();
				craft1.setItemMeta(item1);
				
				ItemStack craft2 = new ItemStack(Material.getMaterial(getConfig().getString("items.craft2.id-craft-1")));
				ItemMeta item2 = craft2.getItemMeta();
				craft2.setItemMeta(item2);
				
				gui1.setItem(15, craft1);
				gui1.setItem(1, craft2);
				gui1.setItem(2, craft2);
				gui1.setItem(3, craft2);
				gui1.setItem(10, craft2);
				gui1.setItem(11, craft2);
				gui1.setItem(12, craft2);
				gui1.setItem(19, craft2);
				gui1.setItem(20, craft2);
				gui1.setItem(21, craft2);
				
				player.openInventory(gui1);
				
			}
			
			if(current.getType() == Material.getMaterial(getConfig().getString("items.craft3.id"))){
				
				Inventory gui1 = Bukkit.createInventory(null, 27, getConfig().getString("messages.default.prefix").replace("&", "§") + getConfig().getString("messages." + getConfig().getString("language") + "." + "gui-title-craft").replace("&", "§"));
				
				ItemStack craft1 = new ItemStack(Material.getMaterial(getConfig().getString("items.craft3.id")), 9);
				ItemMeta item1 = craft1.getItemMeta();
				craft1.setItemMeta(item1);
				
				ItemStack craft2 = new ItemStack(Material.getMaterial(getConfig().getString("items.craft3.id-craft-1")));
				ItemMeta item2 = craft2.getItemMeta();
				craft2.setItemMeta(item2);
				
				gui1.setItem(15, craft1);
				gui1.setItem(1, craft2);
				gui1.setItem(2, craft2);
				gui1.setItem(3, craft2);
				gui1.setItem(10, craft2);
				gui1.setItem(11, craft2);
				gui1.setItem(12, craft2);
				gui1.setItem(19, craft2);
				gui1.setItem(20, craft2);
				gui1.setItem(21, craft2);
				
				player.openInventory(gui1);
				
			}
			
			if(current.getType() == Material.getMaterial(getConfig().getString("items.craft4.id"))){
				
				Inventory gui1 = Bukkit.createInventory(null, 27, getConfig().getString("messages.default.prefix").replace("&", "§") + getConfig().getString("messages." + getConfig().getString("language") + "." + "gui-title-craft").replace("&", "§"));
				
				ItemStack craft1 = new ItemStack(Material.getMaterial(getConfig().getString("items.craft4.id")), 9);
				ItemMeta item1 = craft1.getItemMeta();
				craft1.setItemMeta(item1);
				
				ItemStack craft2 = new ItemStack(Material.getMaterial(getConfig().getString("items.craft4.id-craft-1")));
				ItemMeta item2 = craft2.getItemMeta();
				craft2.setItemMeta(item2);
				
				gui1.setItem(15, craft1);
				gui1.setItem(1, craft2);
				gui1.setItem(2, craft2);
				gui1.setItem(3, craft2);
				gui1.setItem(10, craft2);
				gui1.setItem(11, craft2);
				gui1.setItem(12, craft2);
				gui1.setItem(19, craft2);
				gui1.setItem(20, craft2);
				gui1.setItem(21, craft2);
				
				player.openInventory(gui1);
				
			}
			
			if(current.getType() == Material.getMaterial(getConfig().getString("items.craft5.id"))){
				
				Inventory gui1 = Bukkit.createInventory(null, 27, getConfig().getString("messages.default.prefix").replace("&", "§") + getConfig().getString("messages." + getConfig().getString("language") + "." + "gui-title-craft").replace("&", "§"));
				
				ItemStack craft1 = new ItemStack(Material.getMaterial(getConfig().getString("items.craft5.id")), 6);
				ItemMeta item1 = craft1.getItemMeta();
				craft1.setItemMeta(item1);
				
				ItemStack craft2 = new ItemStack(Material.getMaterial(getConfig().getString("items.craft5.id-craft-1")));
				ItemMeta item2 = craft2.getItemMeta();
				craft2.setItemMeta(item2);
				
				ItemStack craft3 = new ItemStack(Material.getMaterial(getConfig().getString("items.craft5.id-craft-2")));
				ItemMeta item3 = craft3.getItemMeta();
				craft3.setItemMeta(item3);
				
				gui1.setItem(15, craft1);
				gui1.setItem(1, craft3);
				gui1.setItem(2, craft3);
				gui1.setItem(3, craft3);
				gui1.setItem(10, craft2);
				gui1.setItem(11, craft2);
				gui1.setItem(12, craft2);
				gui1.setItem(19, craft2);
				gui1.setItem(20, craft2);
				gui1.setItem(21, craft2);
				
				player.openInventory(gui1);
				
			}
			
			if(current.getType() == Material.getMaterial(getConfig().getString("items.craft6.id"))){
				
				Inventory gui1 = Bukkit.createInventory(null, 27, getConfig().getString("messages.default.prefix").replace("&", "§") + getConfig().getString("messages." + getConfig().getString("language") + "." + "gui-title-craft").replace("&", "§"));
				
				ItemStack craft1 = new ItemStack(Material.getMaterial(getConfig().getString("items.craft6.id")), 6);
				ItemMeta item1 = craft1.getItemMeta();
				craft1.setItemMeta(item1);
				
				ItemStack craft2 = new ItemStack(Material.getMaterial(getConfig().getString("items.craft6.id-craft-1")));
				ItemMeta item2 = craft2.getItemMeta();
				craft2.setItemMeta(item2);
				
				ItemStack craft3 = new ItemStack(Material.getMaterial(getConfig().getString("items.craft6.id-craft-2")));
				ItemMeta item3 = craft3.getItemMeta();
				craft3.setItemMeta(item3);
				
				gui1.setItem(15, craft1);
				gui1.setItem(1, craft3);
				gui1.setItem(2, craft3);
				gui1.setItem(3, craft3);
				gui1.setItem(10, craft2);
				gui1.setItem(11, craft2);
				gui1.setItem(12, craft2);
				gui1.setItem(19, craft2);
				gui1.setItem(20, craft2);
				gui1.setItem(21, craft2);
				
				player.openInventory(gui1);
				
			}
			
			if(current.getType() == Material.getMaterial(getConfig().getString("items.craft7.id"))){
				
				Inventory gui1 = Bukkit.createInventory(null, 27, getConfig().getString("messages.default.prefix").replace("&", "§") + getConfig().getString("messages." + getConfig().getString("language") + "." + "gui-title-craft").replace("&", "§"));
				
				ItemStack craft1 = new ItemStack(Material.getMaterial(getConfig().getString("items.craft7.id")));
				ItemMeta item1 = craft1.getItemMeta();
				craft1.setItemMeta(item1);
				
				ItemStack craft2 = new ItemStack(Material.getMaterial(getConfig().getString("items.craft7.id-craft-1")));
				ItemMeta item2 = craft2.getItemMeta();
				craft2.setItemMeta(item2);
				
				ItemStack craft3 = new ItemStack(Material.getMaterial(getConfig().getString("items.craft7.id-craft-2")));
				ItemMeta item3 = craft3.getItemMeta();
				craft3.setItemMeta(item3);
				
				ItemStack craft4 = new ItemStack(Material.getMaterial(getConfig().getString("items.craft7.id-craft-3")));
				ItemMeta item4 = craft4.getItemMeta();
				craft4.setItemMeta(item4);
				
				ItemStack craft5 = new ItemStack(Material.getMaterial(getConfig().getString("items.craft7.id-craft-4")));
				ItemMeta item5 = craft5.getItemMeta();
				craft5.setItemMeta(item5);
				
				gui1.setItem(15, craft1);
				gui1.setItem(1, craft2);
				gui1.setItem(2, craft2);
				gui1.setItem(3, craft2);
				gui1.setItem(10, craft2);
				gui1.setItem(11, craft3);
				gui1.setItem(12, craft2);
				gui1.setItem(19, craft4);
				gui1.setItem(20, craft5);
				gui1.setItem(21, craft4);
				
				player.openInventory(gui1);
				
			}
			
			if(current.getType() == Material.getMaterial(getConfig().getString("items.craft8.id"))){
				
				Inventory gui1 = Bukkit.createInventory(null, 27, getConfig().getString("messages.default.prefix").replace("&", "§") + getConfig().getString("messages." + getConfig().getString("language") + "." + "gui-title-craft").replace("&", "§"));
				
				ItemStack craft1 = new ItemStack(Material.getMaterial(getConfig().getString("items.craft8.id")));
				ItemMeta item1 = craft1.getItemMeta();
				craft1.setItemMeta(item1);
				
				ItemStack craft2 = new ItemStack(Material.getMaterial(getConfig().getString("items.craft8.id-craft-1")));
				ItemMeta item2 = craft2.getItemMeta();
				craft2.setItemMeta(item2);
				
				ItemStack craft3 = new ItemStack(Material.getMaterial(getConfig().getString("items.craft8.id-craft-2")));
				ItemMeta item3 = craft3.getItemMeta();
				craft3.setItemMeta(item3);
				
				ItemStack craft4 = new ItemStack(Material.getMaterial(getConfig().getString("items.craft8.id-craft-3")));
				ItemMeta item4 = craft4.getItemMeta();
				craft4.setItemMeta(item4);
				
				ItemStack craft5 = new ItemStack(Material.getMaterial(getConfig().getString("items.craft8.id-craft-4")));
				ItemMeta item5 = craft5.getItemMeta();
				craft5.setItemMeta(item5);
				
				gui1.setItem(15, craft1);
				gui1.setItem(1, craft3);
				gui1.setItem(2, craft4);
				gui1.setItem(10, craft4);
				gui1.setItem(11, craft5);
				gui1.setItem(12, craft2);
				gui1.setItem(20, craft2);
				gui1.setItem(21, craft5);
				
				player.openInventory(gui1);
				
			}
			
			if(current.getType() == Material.getMaterial(getConfig().getString("items.craft9.id")) && current.hasItemMeta() && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase(getConfig().getString("messages." + getConfig().getString("language") + "." + "name-craft9").replace("&", "§"))){
	
				
				Inventory gui1 = Bukkit.createInventory(null, 27, getConfig().getString("messages.default.prefix").replace("&", "§") + getConfig().getString("messages." + getConfig().getString("language") + "." + "gui-title-craft").replace("&", "§"));
				
				ItemStack craft1 = new ItemStack(Material.getMaterial(getConfig().getString("items.craft9.id")));
				ItemMeta item1 = craft1.getItemMeta();
				craft1.setItemMeta(item1);
				
				ItemStack craft2 = new ItemStack(Material.getMaterial(getConfig().getString("items.craft9.id-craft-1")));
				ItemMeta item2 = craft2.getItemMeta();
				craft2.setItemMeta(item2);
				
				ItemStack craft3 = new ItemStack(Material.getMaterial(getConfig().getString("items.craft9.id-craft-2")));
				ItemMeta item3 = craft3.getItemMeta();
				craft3.setItemMeta(item3);
				
				gui1.setItem(15, craft1);
				gui1.setItem(1, craft2);
				gui1.setItem(2, craft2);
				gui1.setItem(3, craft2);
				gui1.setItem(10, craft2);
				gui1.setItem(11, craft3);
				gui1.setItem(12, craft2);
				gui1.setItem(19, craft2);
				gui1.setItem(20, craft2);
				gui1.setItem(21, craft2);
				
				player.openInventory(gui1);
				
			}
			
			if(current.getType() == Material.getMaterial(getConfig().getString("items.fire1.id"))){
				
				Inventory gui1 = Bukkit.createInventory(null, 9, getConfig().getString("messages.default.prefix").replace("&", "§") + getConfig().getString("messages." + getConfig().getString("language") + "." + "gui-title-furnace").replace("&", "§"));
				
				ItemStack craft1 = new ItemStack(Material.getMaterial(getConfig().getString("items.fire1.id")));
				ItemMeta item1 = craft1.getItemMeta();
				craft1.setItemMeta(item1);
				
				ItemStack craft2 = new ItemStack(Material.getMaterial(getConfig().getString("items.fire1.id-furnace-1")));
				ItemMeta item2 = craft2.getItemMeta();
				craft2.setItemMeta(item2);
				
				gui1.setItem(6, craft1);
				gui1.setItem(2, craft2);
				
				player.openInventory(gui1);
				
			}
			
			if(current.getType() == Material.getMaterial(getConfig().getString("items.fire2.id"))){
				
				Inventory gui1 = Bukkit.createInventory(null, 9, getConfig().getString("messages.default.prefix").replace("&", "§") + getConfig().getString("messages." + getConfig().getString("language") + "." + "gui-title-furnace").replace("&", "§"));
				
				ItemStack craft1 = new ItemStack(Material.getMaterial(getConfig().getString("items.fire2.id")));
				ItemMeta item1 = craft1.getItemMeta();
				craft1.setItemMeta(item1);
				
				ItemStack craft2 = new ItemStack(Material.getMaterial(getConfig().getString("items.fire2.id-furnace-1")));
				ItemMeta item2 = craft2.getItemMeta();
				craft2.setItemMeta(item2);
				
				gui1.setItem(6, craft1);
				gui1.setItem(2, craft2);
				
				player.openInventory(gui1);
				
			}
			
			if(current.getType() == Material.getMaterial(getConfig().getString("items.fire3.id"))){
				
				Inventory gui1 = Bukkit.createInventory(null, 9, getConfig().getString("messages.default.prefix").replace("&", "§") + getConfig().getString("messages." + getConfig().getString("language") + "." + "gui-title-furnace").replace("&", "§"));
				
				ItemStack craft1 = new ItemStack(Material.getMaterial(getConfig().getString("items.fire3.id")));
				ItemMeta item1 = craft1.getItemMeta();
				craft1.setItemMeta(item1);
				
				ItemStack craft2 = new ItemStack(Material.getMaterial(getConfig().getString("items.fire3.id-furnace-1")));
				ItemMeta item2 = craft2.getItemMeta();
				craft2.setItemMeta(item2);
				
				gui1.setItem(6, craft1);
				gui1.setItem(2, craft2);
				
				player.openInventory(gui1);
				
			}
			
			if(current.getType() == Material.getMaterial(getConfig().getString("items.fire4.id"))){
				
				Inventory gui1 = Bukkit.createInventory(null, 9, getConfig().getString("messages.default.prefix").replace("&", "§") + getConfig().getString("messages." + getConfig().getString("language") + "." + "gui-title-furnace").replace("&", "§"));
				
				ItemStack craft1 = new ItemStack(Material.getMaterial(getConfig().getString("items.fire4.id")));
				ItemMeta item1 = craft1.getItemMeta();
				craft1.setItemMeta(item1);
				
				ItemStack craft2 = new ItemStack(Material.getMaterial(getConfig().getString("items.fire4.id-furnace-1")));
				ItemMeta item2 = craft2.getItemMeta();
				craft2.setItemMeta(item2);
				
				gui1.setItem(6, craft1);
				gui1.setItem(2, craft2);
				
				player.openInventory(gui1);
				
			}
			
			if(current.getType() == Material.getMaterial(getConfig().getString("items.fire5.id"))){
				
				Inventory gui1 = Bukkit.createInventory(null, 9, getConfig().getString("messages.default.prefix").replace("&", "§") + getConfig().getString("messages." + getConfig().getString("language") + "." + "gui-title-furnace").replace("&", "§"));
				
				ItemStack craft1 = new ItemStack(Material.getMaterial(getConfig().getString("items.fire5.id")), 4);
				ItemMeta item1 = craft1.getItemMeta();
				craft1.setItemMeta(item1);
				
				ItemStack craft2 = new ItemStack(Material.getMaterial(getConfig().getString("items.fire5.id-furnace-1")));
				ItemMeta item2 = craft2.getItemMeta();
				craft2.setItemMeta(item2);
				
				gui1.setItem(6, craft1);
				gui1.setItem(2, craft2);
				
				player.openInventory(gui1);
				
			}
			
			if(current.getType() == Material.getMaterial(getConfig().getString("items.fire6.id"))){
				
				Inventory gui1 = Bukkit.createInventory(null, 9, getConfig().getString("messages.default.prefix").replace("&", "§") + getConfig().getString("messages." + getConfig().getString("language") + "." + "gui-title-furnace").replace("&", "§"));
				
				ItemStack craft1 = new ItemStack(Material.getMaterial(getConfig().getString("items.fire6.id")), 4);
				ItemMeta item1 = craft1.getItemMeta();
				craft1.setItemMeta(item1);
				
				ItemStack craft2 = new ItemStack(Material.getMaterial(getConfig().getString("items.fire6.id-furnace-1")));
				ItemMeta item2 = craft2.getItemMeta();
				craft2.setItemMeta(item2);
				
				gui1.setItem(6, craft1);
				gui1.setItem(2, craft2);
				
				player.openInventory(gui1);
				
			}
			
			if(current.getType() == Material.getMaterial(getConfig().getString("items.fire7.id"))){
				
				Inventory gui1 = Bukkit.createInventory(null, 9, getConfig().getString("messages.default.prefix").replace("&", "§") + getConfig().getString("messages." + getConfig().getString("language") + "." + "gui-title-furnace").replace("&", "§"));
				
				ItemStack craft1 = new ItemStack(Material.getMaterial(getConfig().getString("items.fire7.id")), 4);
				ItemMeta item1 = craft1.getItemMeta();
				craft1.setItemMeta(item1);
				
				ItemStack craft2 = new ItemStack(Material.getMaterial(getConfig().getString("items.fire7.id-furnace-1")));
				ItemMeta item2 = craft2.getItemMeta();
				craft2.setItemMeta(item2);
				
				gui1.setItem(6, craft1);
				gui1.setItem(2, craft2);
				
				player.openInventory(gui1);
				
			}
			
			if(current.getType() == Material.getMaterial(getConfig().getString("items.fire8.id"))){
				
				Inventory gui1 = Bukkit.createInventory(null, 9, getConfig().getString("messages.default.prefix").replace("&", "§") + getConfig().getString("messages." + getConfig().getString("language") + "." + "gui-title-furnace").replace("&", "§"));
				
				ItemStack craft1 = new ItemStack(Material.getMaterial(getConfig().getString("items.fire8.id")), 4);
				ItemMeta item1 = craft1.getItemMeta();
				craft1.setItemMeta(item1);
				
				ItemStack craft2 = new ItemStack(Material.getMaterial(getConfig().getString("items.fire8.id-furnace-1")));
				ItemMeta item2 = craft2.getItemMeta();
				craft2.setItemMeta(item2);
				
				gui1.setItem(6, craft1);
				gui1.setItem(2, craft2);
				
				player.openInventory(gui1);
				
			}
			
			if(current.getType() == Material.getMaterial(getConfig().getString("items.fire9.id"))){
				
				Inventory gui1 = Bukkit.createInventory(null, 9, getConfig().getString("messages.default.prefix").replace("&", "§") + getConfig().getString("messages." + getConfig().getString("language") + "." + "gui-title-furnace").replace("&", "§"));
				
				ItemStack craft1 = new ItemStack(Material.getMaterial(getConfig().getString("items.fire9.id")), 4);
				ItemMeta item1 = craft1.getItemMeta();
				craft1.setItemMeta(item1);
				
				ItemStack craft2 = new ItemStack(Material.getMaterial(getConfig().getString("items.fire9.id-furnace-1")));
				ItemMeta item2 = craft2.getItemMeta();
				craft2.setItemMeta(item2);
				
				gui1.setItem(6, craft1);
				gui1.setItem(2, craft2);
				
				player.openInventory(gui1);
				
			}
			
			if(current.getType() == Material.getMaterial(getConfig().getString("items.fire10.id"))){
				
				Inventory gui1 = Bukkit.createInventory(null, 9, getConfig().getString("messages.default.prefix").replace("&", "§") + getConfig().getString("messages." + getConfig().getString("language") + "." + "gui-title-furnace").replace("&", "§"));
				
				ItemStack craft1 = new ItemStack(Material.getMaterial(getConfig().getString("items.fire10.id")), 4);
				ItemMeta item1 = craft1.getItemMeta();
				craft1.setItemMeta(item1);
				
				ItemStack craft2 = new ItemStack(Material.getMaterial(getConfig().getString("items.fire10.id-furnace-1")));
				ItemMeta item2 = craft2.getItemMeta();
				craft2.setItemMeta(item2);
				
				gui1.setItem(6, craft1);
				gui1.setItem(2, craft2);
				
				player.openInventory(gui1);
				
			}
			
			if(current.getType() == Material.getMaterial(getConfig().getString("items.fire11.id"))){
				
				Inventory gui1 = Bukkit.createInventory(null, 9, getConfig().getString("messages.default.prefix").replace("&", "§") + getConfig().getString("messages." + getConfig().getString("language") + "." + "gui-title-furnace").replace("&", "§"));
				
				ItemStack craft1 = new ItemStack(Material.getMaterial(getConfig().getString("items.fire11.id")), 4);
				ItemMeta item1 = craft1.getItemMeta();
				craft1.setItemMeta(item1);
				
				ItemStack craft2 = new ItemStack(Material.getMaterial(getConfig().getString("items.fire11.id-furnace-1")));
				ItemMeta item2 = craft2.getItemMeta();
				craft2.setItemMeta(item2);
				
				gui1.setItem(6, craft1);
				gui1.setItem(2, craft2);
				
				player.openInventory(gui1);
				
			}
			
			if(current.getType() == Material.getMaterial(getConfig().getString("items.fire12.id"))){
				
				Inventory gui1 = Bukkit.createInventory(null, 9, getConfig().getString("messages.default.prefix").replace("&", "§") + getConfig().getString("messages." + getConfig().getString("language") + "." + "gui-title-furnace").replace("&", "§"));
				
				ItemStack craft1 = new ItemStack(Material.getMaterial(getConfig().getString("items.fire12.id")));
				ItemMeta item1 = craft1.getItemMeta();
				craft1.setItemMeta(item1);
				
				ItemStack craft2 = new ItemStack(Material.getMaterial(getConfig().getString("items.fire12.id-furnace-1")));
				ItemMeta item2 = craft2.getItemMeta();
				craft2.setItemMeta(item2);
				
				gui1.setItem(6, craft1);
				gui1.setItem(2, craft2);
				
				player.openInventory(gui1);
				
			}
			
			if(current.getType() == Material.getMaterial(getConfig().getString("items.fire13.id")) && current.hasItemMeta() && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase(getConfig().getString("messages." + getConfig().getString("language") + "." + "name-fire13").replace("&", "§"))){
				
				Inventory gui1 = Bukkit.createInventory(null, 9, getConfig().getString("messages.default.prefix").replace("&", "§") + getConfig().getString("messages." + getConfig().getString("language") + "." + "gui-title-furnace").replace("&", "§"));
				
				ItemStack craft1 = new ItemStack(Material.getMaterial(getConfig().getString("items.fire13.id")));
				ItemMeta item1 = craft1.getItemMeta();
				craft1.setItemMeta(item1);
				
				ItemStack craft2 = new ItemStack(Material.getMaterial(getConfig().getString("items.fire13.id-furnace-1")));
				ItemMeta item2 = craft2.getItemMeta();
				craft2.setItemMeta(item2);
				
				gui1.setItem(6, craft1);
				gui1.setItem(2, craft2);
				
				player.openInventory(gui1);
				
			}
			
			if(current.getType() == Material.getMaterial(getConfig().getString("items.fire14.id"))){
				
				Inventory gui1 = Bukkit.createInventory(null, 9, getConfig().getString("messages.default.prefix").replace("&", "§") + getConfig().getString("messages." + getConfig().getString("language") + "." + "gui-title-furnace").replace("&", "§"));
				
				ItemStack craft1 = new ItemStack(Material.getMaterial(getConfig().getString("items.fire14.id")));
				ItemMeta item1 = craft1.getItemMeta();
				craft1.setItemMeta(item1);
				
				ItemStack craft2 = new ItemStack(Material.getMaterial(getConfig().getString("items.fire14.id-furnace-1")));
				ItemMeta item2 = craft2.getItemMeta();
				craft2.setItemMeta(item2);
				
				gui1.setItem(6, craft1);
				gui1.setItem(2, craft2);
				
				player.openInventory(gui1);
				
			}
		}
		
		if(inv.getName().equalsIgnoreCase(getConfig().getString("messages.default.prefix").replace("&", "§") + getConfig().getString("messages." + getConfig().getString("language") + "." + "gui-title-craft").replace("&", "§"))){
			
			event.setCancelled(true);
			
		}
		
		if(inv.getName().equalsIgnoreCase(getConfig().getString("messages.default.prefix").replace("&", "§") + getConfig().getString("messages." + getConfig().getString("language") + "." + "gui-title-furnace").replace("&", "§"))){
			
			event.setCancelled(true);
			
		}
	}


	
	public void onDisable() {
		getServer().getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "== " + ChatColor.YELLOW + "MoreCrafting" + ChatColor.DARK_GRAY + " =====================");
		System.out.println(" ");
		getServer().getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "Plugin : " + ChatColor.RED + "OFF" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Minecraft 1.13.2");
		System.out.println(" ");
		getServer().getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "Version " + ChatColor.YELLOW + "1.1");
		getServer().getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "Created by " + ChatColor.YELLOW + "MrFantiVideo" + ChatColor.DARK_GRAY + " | 2018 - 2019");
		System.out.println(" ");
		getServer().getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "=====================================");
	}

}
