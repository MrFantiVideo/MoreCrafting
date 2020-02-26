package fr.mrfantivideo.morecrafting.Listeners;

import fr.mrfantivideo.morecrafting.items.RecipeBookSpecialItem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerInteractListener implements Listener
{
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        event.getPlayer().getInventory().addItem(RecipeBookSpecialItem.getInstance().getClonedItemStack());
    }

    /*
    @EventHandler
    public void OnPlayerInteract(PlayerInteractEvent event)
    {
        Player player = event.getPlayer();
        Action action = event.getAction();
        ItemStack stack = event.getItem();

        if (stack == null)
            return;

        ShapedRecipe recipe = RecipesManagerOld.GetCustomShapedRecipe("MoreCraftingRecipeBook");
        if (recipe != null)
        {
            ItemStack book = recipe.getResult();
            if (book != null && stack.getType() ==
                    book.getType() && stack.hasItemMeta() &&
                    stack.getItemMeta().hasDisplayName() &&
                    stack.getItemMeta().getDisplayName().equalsIgnoreCase(book.getItemMeta().getDisplayName()))
            {
                if (player.isOp() || player.hasPermission(Main.getInstance().getConfigPermissions().GetBookPerm()) ||
                        player.hasPermission(Main.getInstance().getConfigPermissions().GetAllPerm()))
                {
                    if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK)
                    {
                        int inventorySize = Main.getInstance().getConfigSettings().GetValue(Integer.class, "others.book.gui.menu.size");
                        if (inventorySize <= 0)
                            return;
                        String inventoryTitle = Main.getInstance().getConfigMessages().GetGUITitle() + Main.getInstance().getConfigMessages().GetGUITitleMain();
                        if (inventoryTitle == null || inventoryTitle.isEmpty())
                            return;
                        Inventory inventory = Bukkit.createInventory(null, inventorySize, inventoryTitle);

                        FillInventory(inventory);
                        player.playSound(player.getLocation(), Sound.ITEM_ARMOR_EQUIP_ELYTRA, 1, 1);
                        player.openInventory(inventory);
                        event.setCancelled(true);
                    }
                }
                else
                {
                    player.sendMessage(Main.getInstance().getConfigMessages().GetPrefix() + Main.getInstance().getConfigMessages().GetCmdPermissionDeniedMsg());
                }
            }
        }
    }

    /**
     * Fill inventory
     *
     * @param inv Inventory
     /
    private void FillInventory(Inventory inv)
    {
        for (CustomRecipeOld recipe : RecipesManagerOld.GetInstance().GetRecipes())
        {
            if (recipe.GetBookInventorySlot() <= -1)
                continue;
            ItemStack item = recipe.GetResult().clone();
            item.setAmount(1);
            inv.setItem(recipe.GetBookInventorySlot(), item);
        }
    }  */
}
