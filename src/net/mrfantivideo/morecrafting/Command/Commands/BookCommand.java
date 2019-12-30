package net.mrfantivideo.morecrafting.Command.Commands;

import net.mrfantivideo.morecrafting.Command.AbstractCommand;
import net.mrfantivideo.morecrafting.Main;
import net.mrfantivideo.morecrafting.Recipes.CustomRecipe;
import net.mrfantivideo.morecrafting.Recipes.RecipesManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

public class BookCommand extends AbstractCommand
{
    public BookCommand()
    {
        super("book");
    }

    public boolean HasPermission(CommandSender sender)
    {
        return (sender.isOp() || sender.hasPermission(Main.getInstance().getConfigPermissions().GetAdminBookPerm()) ||
                sender.hasPermission(Main.getInstance().getConfigPermissions().GetAdminAllPerm()));
    }

    public boolean Execute(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        Player player = (Player) sender;
        PlayerInventory inventory = player.getInventory();
        CustomRecipe recipe = RecipesManager.GetInstance().GetRecipe("MoreCraftingRecipeBook");
        if (recipe != null)
            inventory.addItem(recipe.GetResult().clone());
        return true;
    }
}
