package fr.mrfantivideo.morecrafting.Command.Commands;

import fr.mrfantivideo.morecrafting.Command.AbstractCommand;
import fr.mrfantivideo.morecrafting.Main;
import fr.mrfantivideo.morecrafting.Recipes.CustomRecipe;
import fr.mrfantivideo.morecrafting.Recipes.RecipesManager;
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
