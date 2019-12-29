package net.mrfantivideo.morecrafting.Listeners;

import net.mrfantivideo.morecrafting.Main;
import net.mrfantivideo.morecrafting.Recipes.CustomRecipe;
import net.mrfantivideo.morecrafting.Recipes.RecipesManager;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

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

        ShapedRecipe recipe = RecipesManager.GetCustomShapedRecipe("MoreCraftingRecipeBook");
        if(recipe != null)
        {
            ItemStack book = recipe.getResult();
            if(book != null && stack.getType() == 
               book.getType() && stack.hasItemMeta() && 
               stack.getItemMeta().hasDisplayName() && 
               stack.getItemMeta().getDisplayName().equalsIgnoreCase(book.getItemMeta().getDisplayName()))
            {
                if(player.isOp() || player.hasPermission(Main.GetInstance().GetConfigPermissions().GetBookPerm()) || 
                   player.hasPermission(Main.GetInstance().GetConfigPermissions().GetAllPerm()))
                {
                    if(action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK)
                    {
                        int inventorySize = Main.GetInstance().GetConfigSettings().GetValue(Integer.class, "others.book.gui.menu.size");
                        if(inventorySize <= 0)
                            return;
                        String inventoryTitle = Main.GetInstance().GetConfigMessages().GetGUITitle() + Main.GetInstance().GetConfigMessages().GetGUITitleMain();
                        if(inventoryTitle == null || inventoryTitle.isEmpty())
                            return;
                        Inventory inventory = Bukkit.createInventory(null, inventorySize, inventoryTitle);
                        FillInventory(inventory);
                        player.playSound(player.getLocation(), Sound.ITEM_ARMOR_EQUIP_ELYTRA, 1, 1);
                        player.openInventory(inventory);
                        event.setCancelled(true);
                    }
                }
                else {
                	player.sendMessage(Main.GetInstance().GetConfigMessages().GetPrefix() + Main.GetInstance().GetConfigMessages().GetCmdPermissionDeniedMsg());
                }
            }
        }
    }

    /**
     * Fill inventory
     * @param inv Inventory
     */
    private void FillInventory(Inventory inv)
    {
        for(CustomRecipe recipe : RecipesManager.GetInstance().GetRecipes())
        {
            if(recipe.GetBookInventorySlot() <= -1)
                continue;
            ItemStack item = recipe.GetResult().clone();
            item.setAmount(1);
            inv.setItem(recipe.GetBookInventorySlot(), item);
        }
    }
}
