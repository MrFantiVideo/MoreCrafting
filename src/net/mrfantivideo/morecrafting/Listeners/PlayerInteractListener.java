package net.mrfantivideo.morecrafting.Listeners;

import net.mrfantivideo.morecrafting.Recipes.CustomRecipe;
import net.mrfantivideo.morecrafting.Recipes.RecipesManager;
import net.mrfantivideo.morecrafting.Utils.EConfig;
import net.mrfantivideo.morecrafting.Utils.PermissionsUtils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static net.mrfantivideo.morecrafting.Utils.ConfigUtils.Get;

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

        ItemStack customBook = RecipesManager.GetCustomShapedRecipe(Get(String.class, EConfig.SETTINGS, "others.book.craft.result.id")).getResult();
        if(stack.getType() == customBook.getType() && stack.hasItemMeta() && stack.getItemMeta().hasDisplayName() && stack.getItemMeta().getDisplayName().equalsIgnoreCase(Get(String.class, EConfig.SETTINGS, "others.book.craft.result.name").replace("&", "§")))
        {
            if(player.isOp() || PermissionsUtils.HasAnyPermission(player, "permissions.morecrafting.book", "permissions.morecrafting.*"))
            {
                if(action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK)
                {
                    int inventorySize = Get(Integer.class, EConfig.SETTINGS, "others.book.gui.menu.size");
                    String inventoryTitle = Get(String.class, EConfig.MESSAGES, "messages.default.gui-title").replace("&", "§") + Get(String.class, EConfig.MESSAGES, "messages." + Get(String.class, EConfig.SETTINGS, "language") + "." + "gui-title-main").replace("&", "§");
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
