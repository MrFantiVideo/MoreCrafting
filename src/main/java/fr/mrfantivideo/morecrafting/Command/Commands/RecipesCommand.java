package fr.mrfantivideo.morecrafting.Command.Commands;

import fr.mrfantivideo.morecrafting.Command.AbstractCommand;
import fr.mrfantivideo.morecrafting.Main;
import fr.mrfantivideo.morecrafting.Recipes.CustomRecipe;
import fr.mrfantivideo.morecrafting.Recipes.RecipesManager;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class RecipesCommand extends AbstractCommand
{
    public RecipesCommand()
    {
        super("recipes");
    }

    public boolean HasPermission(CommandSender sender)
    {
        return (sender.isOp() || sender.hasPermission(Main.getInstance().getConfigPermissions().GetAdminRecipesPerm()) ||
                sender.hasPermission(Main.getInstance().getConfigPermissions().GetAdminAllPerm()));
    }

    public boolean Execute(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        Player player = (Player) sender;
        int inventorySize = Main.getInstance().getConfigSettings().GetValue(Integer.class, "others.book.gui.menu.size");
        String inventoryTitle = Main.getInstance().getConfigMessages().GetGUITitle() + Main.getInstance().getConfigMessages().GetGUITitleMain();
        Inventory inventory = Bukkit.createInventory(null, inventorySize, inventoryTitle);
        FillInventory(inventory);
        player.playSound(player.getLocation(), Sound.ITEM_ARMOR_EQUIP_ELYTRA, 1, 1);
        player.openInventory(inventory);
		return false;
    }

    private void FillInventory(Inventory inv)
    {
        for (CustomRecipe recipe : RecipesManager.GetInstance().GetRecipes())
        {
            if (recipe.GetBookInventorySlot() <= -1)
                continue;
            ItemStack item = recipe.GetResult().clone();
            item.setAmount(1);
            inv.setItem(recipe.GetBookInventorySlot(), item);
        }
    }
}
