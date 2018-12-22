package net.mrfantivideo.morecrafting.Listeners;

import com.sun.istack.internal.NotNull;
import net.mrfantivideo.morecrafting.Main;
import net.mrfantivideo.morecrafting.Recipes.RecipesManager;
import net.mrfantivideo.morecrafting.Utils.ConfigUtils;
import net.mrfantivideo.morecrafting.Utils.EConfig;
import net.mrfantivideo.morecrafting.Utils.PermissionsUtils;
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

@SuppressWarnings("ConstantConditions")
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

        ItemStack customBook = RecipesManager.GetCustomRecipe(ConfigUtils.Get(String.class, EConfig.SETTINGS, "others.book.craft.result.id")).getResult();
        if(stack.getType() == customBook.getType() && stack.hasItemMeta() && stack.getItemMeta().hasDisplayName() && stack.getItemMeta().getDisplayName().equalsIgnoreCase(ConfigUtils.Get(String.class, EConfig.SETTINGS, "others.book.craft.result.name").replace("&", "§")))
        {
            if(player.isOp() || PermissionsUtils.HasAnyPermission(player, "permissions.morecrafting.book", "permissions.morecrafting.*"))
            {
                if(action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK)
                {
                    int inventorySize = ConfigUtils.Get(Integer.class, EConfig.SETTINGS,"others.book.gui.menu.size");
                    String inventoryTitle = ConfigUtils.Get(String.class, EConfig.MESSAGES,"messages.default.prefix").replace("&", "§") + ConfigUtils.Get(String.class, EConfig.MESSAGES,"messages." +  ConfigUtils.Get(String.class, EConfig.SETTINGS,"language") + "." + "gui-title-main").replace("&", "§");
                    Inventory inventory = Bukkit.createInventory(null, inventorySize, inventoryTitle);
                    FillInventory(inventory);
                    player.playSound(player.getLocation(), Sound.ITEM_ARMOR_EQUIP_ELYTRA, 1, 1);
                    player.openInventory(inventory);
                    event.setCancelled(true);
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
                String itemLORE = Main.GetInstance().GetConfigSettings().GetConfiguration().getString(itemPath + "craft.result.lore");
                if(itemNAME != null && !itemNAME.isEmpty())
                    item.setDisplayName(Main.GetInstance().GetConfigSettings().GetConfiguration().getString(itemPath + "craft.result.name").replace("&", "§"));
                if(itemLORE != null && !itemLORE.isEmpty())
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
