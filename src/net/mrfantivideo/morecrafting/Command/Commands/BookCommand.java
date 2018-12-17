package net.mrfantivideo.morecrafting.Command.Commands;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

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
        ItemStack book = new ItemStack(Material.getMaterial(Main.GetInstance().GetConfigSettings().GetConfiguration().getString("others.book.craft.result.id")), 1);
        ItemMeta meta = book.getItemMeta();
        meta.setDisplayName(Main.GetInstance().GetConfigSettings().GetConfiguration().getString("others.book.craft.result.name").replace("&", "§"));
        meta.setLore(Arrays.asList(Main.GetInstance().GetConfigSettings().GetConfiguration().getString("others.book.craft.result.lore").replace("&", "§")));
        book.setItemMeta(meta);
        inventory.addItem(book);
        return true;
    }
}
