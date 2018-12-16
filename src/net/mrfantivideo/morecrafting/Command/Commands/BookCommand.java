package net.mrfantivideo.morecrafting.Command.Commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import net.mrfantivideo.morecrafting.Main;
import net.mrfantivideo.morecrafting.Command.AbstractCommand;

public class BookCommand extends AbstractCommand 
{
    public BookCommand()
    {
        super("book");
    }

    public boolean HasPermission(CommandSender sender)
    {
        return (sender.isOp() || sender.hasPermission(Main.GetInstance().GetConfigPermissions().GetConfiguration().getString("permissions.morecrafting.admin.book")) || sender.hasPermission(Main.GetInstance().GetConfigPermissions().GetConfiguration().getString("permissions.morecrafting.admin.*")));
    }

    public boolean Execute(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {	
        Player player = (Player) sender;
        PlayerInventory inventory = player.getInventory();
        inventory.addItem(new ItemStack(Material.ENCHANTED_BOOK));
        return true;
    }
}
