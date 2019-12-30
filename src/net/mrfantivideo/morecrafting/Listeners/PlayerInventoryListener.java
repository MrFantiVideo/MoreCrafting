package net.mrfantivideo.morecrafting.Listeners;

import net.mrfantivideo.morecrafting.Main;
import net.mrfantivideo.morecrafting.Recipes.CustomRecipe;
import net.mrfantivideo.morecrafting.Recipes.RecipesManager;
import net.mrfantivideo.morecrafting.UnrealCoreImports.Flag;
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
import org.bukkit.persistence.PersistentDataType;

import java.util.Map;

public class PlayerInventoryListener implements Listener
{
    @EventHandler
    public void OnPlayerInventoryClick(InventoryClickEvent event)
    {
        Player player = (Player) event.getWhoClicked();
        InventoryView inv = event.getView();
        ItemStack stack = event.getCurrentItem();

        if (stack == null)
            return;

        if (inv.getTitle().equalsIgnoreCase(Main.getInstance().getConfigMessages().GetGUITitle() + Main.getInstance().getConfigMessages().GetGUITitleCrafting()))
        {
            event.setCancelled(true);
            return;
        }

        if (inv.getTitle().equalsIgnoreCase(Main.getInstance().getConfigMessages().GetGUITitle() + Main.getInstance().getConfigMessages().GetGUITitleFurnace()))
        {
            event.setCancelled(true);
            return;
        }

        if (inv.getTitle().equalsIgnoreCase(Main.getInstance().getConfigMessages().GetGUITitle() + Main.getInstance().getConfigMessages().GetGUITitleSmoker()))
        {
            event.setCancelled(true);
            return;
        }

        if (inv.getTitle().equalsIgnoreCase(Main.getInstance().getConfigMessages().GetGUITitle() + Main.getInstance().getConfigMessages().GetGUITitleBlasting()))
        {
            event.setCancelled(true);
            return;
        }

        if (inv.getTitle().equalsIgnoreCase(Main.getInstance().getConfigMessages().GetGUITitle() + Main.getInstance().getConfigMessages().GetGUITitleStonecutting()))
        {
            event.setCancelled(true);
            return;
        }

        if (inv.getTitle().equalsIgnoreCase(Main.getInstance().getConfigMessages().GetGUITitle() + Main.getInstance().getConfigMessages().GetGUITitleCampfire()))
        {
            event.setCancelled(true);
            return;
        }

        if (!Flag.hasFlag(stack, "recipeName", PersistentDataType.STRING))
            return;

        if (inv.getTitle().equalsIgnoreCase(Main.getInstance().getConfigMessages().GetGUITitle() + Main.getInstance().getConfigMessages().GetGUITitleMain()))
        {
            CustomRecipe recipe = RecipesManager.GetInstance().GetRecipeByName(Flag.getFlag(stack, "recipeName", PersistentDataType.STRING));
            if (recipe != null)
            {
                Inventory inventory;
                if (recipe.IsFurnaceRecipe())
                {
                    inventory = Bukkit.createInventory(null, InventoryType.FURNACE, Main.getInstance().getConfigMessages().GetGUITitle() + Main.getInstance().getConfigMessages().GetGUITitleFurnace());
                    inventory.setItem(0, recipe.GetFurnaceRecipe().getInput().clone());
                    inventory.setItem(1, new ItemStack(Material.COAL));
                    inventory.setItem(2, recipe.GetResult().clone());
                }
                else if (recipe.IsSmokingRecipe())
                {
                    inventory = Bukkit.createInventory(null, InventoryType.FURNACE, Main.getInstance().getConfigMessages().GetGUITitle() + Main.getInstance().getConfigMessages().GetGUITitleSmoker());
                    inventory.setItem(0, recipe.GetSmokingRecipe().getInput().clone());
                    inventory.setItem(1, new ItemStack(Material.COAL));
                    inventory.setItem(2, recipe.GetResult().clone());
                }
                else if (recipe.IsBlastingRecipe())
                {
                    inventory = Bukkit.createInventory(null, InventoryType.FURNACE, Main.getInstance().getConfigMessages().GetGUITitle() + Main.getInstance().getConfigMessages().GetGUITitleBlasting());
                    inventory.setItem(0, recipe.GetBlastingRecipe().getInput().clone());
                    inventory.setItem(1, new ItemStack(Material.COAL));
                    inventory.setItem(2, recipe.GetResult().clone());
                }
                else if (recipe.IsStonecuttingRecipe())
                {
                    inventory = Bukkit.createInventory(null, InventoryType.STONECUTTER, Main.getInstance().getConfigMessages().GetGUITitle() + Main.getInstance().getConfigMessages().GetGUITitleStonecutting());
                    inventory.setItem(0, recipe.GetStonecuttingRecipe().getInput().clone());
                    inventory.setItem(1, recipe.GetResult().clone());
                }
                else if (recipe.IsCampfireRecipe())
                {
                    inventory = Bukkit.createInventory(null, InventoryType.FURNACE, Main.getInstance().getConfigMessages().GetGUITitle() + Main.getInstance().getConfigMessages().GetGUITitleCampfire());
                    inventory.setItem(0, recipe.GetCampfireRecipe().getInput().clone());
                    inventory.setItem(1, new ItemStack(Material.CAMPFIRE));
                    inventory.setItem(2, recipe.GetResult().clone());
                }
                else
                {
                    inventory = Bukkit.createInventory(null, InventoryType.WORKBENCH, Main.getInstance().getConfigMessages().GetGUITitle() + Main.getInstance().getConfigMessages().GetGUITitleCrafting());
                    for (Map.Entry<Character, ItemStack> entry : recipe.GetRecipe().getIngredientMap().entrySet())
                    {
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
