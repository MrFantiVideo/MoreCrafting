package net.mrfantivideo.morecrafting.Listeners;

import net.mrfantivideo.morecrafting.Recipes.CustomRecipe;
import net.mrfantivideo.morecrafting.Recipes.RecipesManager;
import net.mrfantivideo.morecrafting.Utils.EConfig;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

import static net.mrfantivideo.morecrafting.Utils.ConfigUtils.Get;

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
        if(inv.getName().equalsIgnoreCase("Custom Crafting"))
        {
            event.setCancelled(true);
            return;
        }

        if(inv.getName().equalsIgnoreCase(Get(String.class, EConfig.MESSAGES, "messages.default.prefix").replace("&", "§") + Get(String.class, EConfig.MESSAGES, "messages." + Get(String.class, EConfig.SETTINGS, "language") + "." + "gui-title-main").replace("&", "§")))
        {
            CustomRecipe recipe = RecipesManager.GetInstance().GetRecipeByMaterial(stack.getType());
            if(recipe != null)
            {
                Inventory inventory;
                if(recipe.IsFurnaceRecipe())
                {
                    inventory = Bukkit.createInventory(null, InventoryType.FURNACE, "Custom Crafting");
                    inventory.setItem(0, recipe.GetFurnaceRecipe().getInput().clone());
                    inventory.setItem(1, new ItemStack(Material.COAL));
                    inventory.setItem(2, recipe.GetResult().clone());
                }
                else
                {
                    inventory = Bukkit.createInventory(null, InventoryType.WORKBENCH, "Custom Crafting");
                    for(Map.Entry<Character, ItemStack> entry : recipe.GetRecipe().getIngredientMap().entrySet())
                    {
                        if(entry.getValue() == null)
                            continue;
                        inventory.setItem(Integer.valueOf(entry.getKey().toString()), entry.getValue().clone());
                    }
                    inventory.setItem(0, recipe.GetResult().clone());
                }
                player.openInventory(inventory);
                event.setCancelled(true);
            }
        }
    }
}
