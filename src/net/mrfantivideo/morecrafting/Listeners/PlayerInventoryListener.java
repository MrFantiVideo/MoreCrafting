package net.mrfantivideo.morecrafting.Listeners;

import net.mrfantivideo.morecrafting.Main;
import net.mrfantivideo.morecrafting.Recipes.CustomRecipe;
import net.mrfantivideo.morecrafting.Recipes.RecipesManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import java.util.Map;

public class PlayerInventoryListener implements Listener
{
    @EventHandler
    public void OnPlayerInventoryClick(InventoryClickEvent event)
    {
        Player player = (Player) event.getWhoClicked();
        InventoryView inv = event.getView();
        ItemStack stack = event.getCurrentItem();

        if(stack == null)
            return;

        if(inv.getTitle().equalsIgnoreCase(Main.GetInstance().GetConfigMessages().GetGUITitle() + Main.GetInstance().GetConfigMessages().GetGUITitleCrafting()))
        {
            event.setCancelled(true);
            return;
        }
        
        if(inv.getTitle().equalsIgnoreCase(Main.GetInstance().GetConfigMessages().GetGUITitle() + Main.GetInstance().GetConfigMessages().GetGUITitleFurnace()))
        {
            event.setCancelled(true);
            return;
        }
        
        if(inv.getTitle().equalsIgnoreCase(Main.GetInstance().GetConfigMessages().GetGUITitle() + Main.GetInstance().GetConfigMessages().GetGUITitleSmoker()))
        {
            event.setCancelled(true);
            return;
        }

        if(inv.getTitle().equalsIgnoreCase(Main.GetInstance().GetConfigMessages().GetGUITitle() + Main.GetInstance().GetConfigMessages().GetGUITitleMain())) {
            CustomRecipe recipe = RecipesManager.GetInstance().GetRecipeByMaterial(stack.getType());
            if (recipe != null) {
                Inventory inventory;
                if (recipe.IsFurnaceRecipe()) {
                    inventory = Bukkit.createInventory(null, InventoryType.FURNACE, Main.GetInstance().GetConfigMessages().GetGUITitle() + Main.GetInstance().GetConfigMessages().GetGUITitleFurnace());
                    inventory.setItem(0, recipe.GetFurnaceRecipe().getInput().clone());
                    inventory.setItem(1, new ItemStack(Material.COAL));
                    inventory.setItem(2, recipe.GetResult().clone());
                } else if (recipe.IsSmokingRecipe()) {
                    inventory = Bukkit.createInventory(null, InventoryType.SMOKER, Main.GetInstance().GetConfigMessages().GetGUITitle() + Main.GetInstance().GetConfigMessages().GetGUITitleSmoker());
                    inventory.setItem(0, recipe.GetSmokingRecipe().getInput().clone());
                    inventory.setItem(1, new ItemStack(Material.COAL));
                    inventory.setItem(2, recipe.GetResult().clone());
                } else {
                    inventory = Bukkit.createInventory(null, InventoryType.WORKBENCH, Main.GetInstance().GetConfigMessages().GetGUITitle() + Main.GetInstance().GetConfigMessages().GetGUITitleCrafting());
                    for (Map.Entry<Character, ItemStack> entry : recipe.GetRecipe().getIngredientMap().entrySet()) {
                        if (entry.getValue() == null)
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
