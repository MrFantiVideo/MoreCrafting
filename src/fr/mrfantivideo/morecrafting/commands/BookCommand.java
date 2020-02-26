package fr.mrfantivideo.morecrafting.commands;

import fr.mrfantivideo.morecrafting.Main;
import fr.mrfantivideo.morecrafting.Recipesold.CustomRecipeOld;
import fr.mrfantivideo.morecrafting.Recipesold.RecipesManagerOld;
import fr.unreal852.sunrealcore.commands.BaseCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

public class BookCommand extends BaseCommand
{
    public BookCommand()
    {
        super("book");
    }

    @Override
    public boolean execute(CommandSender sender, Command command, String s, String[] strings)
    {
        Player player = (Player) sender;
        PlayerInventory inventory = player.getInventory();
        CustomRecipeOld recipe = RecipesManagerOld.GetInstance().GetRecipe("MoreCraftingRecipeBook");
        if (recipe != null)
            inventory.addItem(recipe.GetResult().clone());
        return true;
    }

    @Override
    public boolean hasPermission(CommandSender sender)
    {
        return (sender.isOp() || sender.hasPermission(Main.getInstance().getConfigPermissions().GetAdminBookPerm()) ||
                sender.hasPermission(Main.getInstance().getConfigPermissions().GetAdminAllPerm()));
    }

    @Override
    public String getCommandUsage()
    {
        return "";
    }
}
