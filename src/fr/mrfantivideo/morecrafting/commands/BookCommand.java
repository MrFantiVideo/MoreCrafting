package fr.mrfantivideo.morecrafting.commands;

import fr.mrfantivideo.morecrafting.config.MorecrafingConfig;
import fr.mrfantivideo.morecrafting.items.RecipeBookSpecialItem;
import fr.unreal852.sunrealcore.commands.BaseCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BookCommand extends BaseCommand
{
    public BookCommand()
    {
        super("book");
    }

    @Override
    public boolean execute(CommandSender sender, Command command, String s, String[] strings)
    {
        if (!(sender instanceof Player))
            return false;
        Player player = (Player) sender;
        player.getInventory().addItem(RecipeBookSpecialItem.getInstance().getClonedItemStack());
        return true;
    }

    @Override
    public boolean hasPermission(CommandSender sender)
    {
        return sender.isOp() || MorecrafingConfig.PERMISSIONS.hasPermissions(sender, "morecrafting.admin.book", "morecrafting.admin.*");
    }

    @Override
    public String getCommandUsage()
    {
        return "";
    }
}
