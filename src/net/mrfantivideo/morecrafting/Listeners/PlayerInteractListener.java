package net.mrfantivideo.morecrafting.Listeners;

import net.mrfantivideo.morecrafting.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class PlayerInteractListener implements Listener
{
    @EventHandler
    public void OnPlayerInteract(PlayerInteractEvent event)
    {
        Player player = event.getPlayer();
        Action action = event.getAction();
        ItemStack stack = event.getItem();

        if(stack == null)
            return;

        if(stack.getType() == Material.getMaterial(Main.GetInstance().GetConfigSettings().GetConfiguration().getString("others.book.craft.result.id"))
                && stack.hasItemMeta() && stack.getItemMeta().hasDisplayName()
                && stack.getItemMeta().getDisplayName().equalsIgnoreCase(Main.GetInstance().GetConfigSettings().GetConfiguration().getString("others.book.craft.result.name").replace("&", "§")))
        {
            if(player.hasPermission(Main.GetInstance().GetConfigPermissions().GetConfiguration().getString("permissions.morecrafting.book"))
                    || player.hasPermission(Main.GetInstance().GetConfigPermissions().GetConfiguration().getString("permissions.morecrafting.*"))
                    || player.isOp())
            {
                if(action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK)
                {
                    Inventory inventory = Bukkit.createInventory(null, Main.GetInstance().GetConfigSettings().GetConfiguration().getInt("others.book.gui.menu.size"), Main.GetInstance().GetConfigMessages().GetConfiguration().getString("messages.default.prefix").replace("&", "§") + Main.GetInstance().GetConfigMessages().GetConfiguration().getString("messages." + Main.GetInstance().GetConfigSettings().GetConfiguration().getString("language") + "." + "gui-title-main").replace("&", "§"));
                    FillInventory(inventory);
                    if(Main.GetInstance().GetConfigSettings().GetConfiguration().getString("others.book.give-book").equals("true"))
                    {
                    	inventory = player.getInventory();
                    	ItemStack book = new ItemStack(Material.getMaterial(Main.GetInstance().GetConfigSettings().GetConfiguration().getString("others.book.craft.result.id")), 1);
                    	ItemMeta meta = book.getItemMeta();
                    	meta.setDisplayName(Main.GetInstance().GetConfigSettings().GetConfiguration().getString("others.book.craft.result.name").replace("&", "§"));
                    	meta.setLore(Arrays.asList(Main.GetInstance().GetConfigSettings().GetConfiguration().getString("others.book.craft.result.lore").replace("&", "§")));
                    	book.setItemMeta(meta);
                    	inventory.addItem(book);
                    }
                    player.playSound(player.getLocation(), Sound.ITEM_ARMOR_EQUIP_ELYTRA, 1, 1);
                    player.openInventory(inventory);
                }
            }
        }
    }

    /*
        Fill the specified inventory
     */
    private void FillInventory(Inventory inv)
    {
        for(String str : Main.GetInstance().GetConfigSettings().GetConfiguration().getConfigurationSection("recipes.crafting").getKeys(false))
        {
            String itemPath = "recipes.crafting." + str + ".";
            if(!Main.GetInstance().GetConfigSettings().GetConfiguration().getBoolean(itemPath + "enabled"))
                continue;
            ItemStack craft = new ItemStack(Material.getMaterial(Main.GetInstance().GetConfigSettings().GetConfiguration().getString(itemPath + "craft.result.id")));
            ItemMeta item = craft.getItemMeta();
            for(int i = 1; i <= 9; i++)
            {
                String itemNAME = Main.GetInstance().GetConfigSettings().GetConfiguration().getString(itemPath + "craft.result.name");
                if(itemNAME == null || itemNAME.isEmpty())
                    continue;
                item.setDisplayName(Main.GetInstance().GetConfigSettings().GetConfiguration().getString(itemPath + "craft.result.name").replace("&", "§"));
            }
            for(int i = 1; i <= 9; i++)
            {
                String itemLORE = Main.GetInstance().GetConfigSettings().GetConfiguration().getString(itemPath + "craft.result.lore");
                if(itemLORE == null || itemLORE.isEmpty())
                    continue;
                item.setLore(Arrays.asList(Main.GetInstance().GetConfigSettings().GetConfiguration().getString(itemPath + "craft.result.lore").replace("&", "§")));
            }
            craft.setItemMeta(item);
            inv.setItem(Main.GetInstance().GetConfigSettings().GetConfiguration().getInt(itemPath + "craft.result.id-book"), craft);
        }

        for(String str : Main.GetInstance().GetConfigSettings().GetConfiguration().getConfigurationSection("recipes.furnace").getKeys(false))
        {
            String itemPath = "recipes.furnace." + str + ".";
            if(!Main.GetInstance().GetConfigSettings().GetConfiguration().getBoolean(itemPath + "enabled"))
                continue;
            ItemStack furnace = new ItemStack(Material.getMaterial(Main.GetInstance().GetConfigSettings().GetConfiguration().getString(itemPath + "fire.result.id")));
            ItemMeta item = furnace.getItemMeta();
            String itemNAME = Main.GetInstance().GetConfigSettings().GetConfiguration().getString(itemPath + "fire.result.name");
            if(itemNAME == null || itemNAME.isEmpty())
                continue;
            item.setDisplayName(Main.GetInstance().GetConfigSettings().GetConfiguration().getString(itemPath + "fire.result.name").replace("&", "§"));
            String itemLORE = Main.GetInstance().GetConfigSettings().GetConfiguration().getString(itemPath + "fire.result.lore");
            if(itemLORE == null || itemLORE.isEmpty())
                item.setLore(Arrays.asList(Main.GetInstance().GetConfigSettings().GetConfiguration().getString(itemPath + "fire.result.lore").replace("&", "§")));
            else
                item.setLore(Arrays.asList(Main.GetInstance().GetConfigMessages().GetConfiguration().getString("messages." + Main.GetInstance().GetConfigSettings().GetConfiguration().getString("language") + ".name-fire-" + str.replace("RECIPE-", "")).replace("&", "§")));
            furnace.setItemMeta(item);
            inv.setItem(Main.GetInstance().GetConfigSettings().GetConfiguration().getInt(itemPath + "fire.result.id-book"), furnace);
        }
    }
}
