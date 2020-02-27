package fr.mrfantivideo.morecrafting.commands;

import fr.mrfantivideo.morecrafting.config.MorecrafingConfig;
import fr.unreal852.sunrealcore.commands.BaseCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class RecipesCommand extends BaseCommand
{
    public RecipesCommand()
    {
        super("recipes");
    }

    @Override
    public boolean execute(CommandSender sender, Command command, String s, String[] strings)
    {
        /*
        Player player = (Player) sender;
        int inventorySize = Main.getInstance().getConfigSettings().GetValue(Integer.class, "others.book.gui.menu.size");
        String inventoryTitle = Main.getInstance().getConfigMessages().GetGUITitle() + Main.getInstance().getConfigMessages().GetGUITitleMain();
        Inventory inventory = Bukkit.createInventory(null, inventorySize, inventoryTitle);
        FillInventory(inventory);
        player.playSound(player.getLocation(), Sound.ITEM_ARMOR_EQUIP_ELYTRA, 1, 1);
        player.openInventory(inventory); */
        return true;
    }

    @Override
    public boolean hasPermission(CommandSender sender)
    {
        return sender.isOp() || MorecrafingConfig.PERMISSIONS.hasPermissions(sender, "morecrafting.admin.recipes", "morecrafting.admin.*");
    }

    @Override
    public String getCommandUsage()
    {
        return "";
    }
}
