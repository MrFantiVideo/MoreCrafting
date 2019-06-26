package net.mrfantivideo.morecrafting.Command.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import net.mrfantivideo.morecrafting.Main;
import net.mrfantivideo.morecrafting.Command.AbstractCommand;

public class HelpCommand extends AbstractCommand 
{
    public HelpCommand()
    {
        super("help");
    }
    
    public boolean HasPermission(CommandSender sender)
    {
        return (sender.isOp() || sender.hasPermission(Main.GetInstance().GetConfigPermissions().GetPermission("permissions.morecrafting.admin.help")) || sender.hasPermission(Main.GetInstance().GetConfigPermissions().GetAllPerm()));
    }

    public boolean Execute(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        sender.sendMessage("§8== §6MoreCrafting §8=====================");
        sender.sendMessage("");
        sender.sendMessage(("§6/morecrafting §8- " + Main.GetInstance().GetConfigMessages().GetCmdMoreCraftingMsg()));
        sender.sendMessage(("§6/morecrafting help §8- " + Main.GetInstance().GetConfigMessages().GetCmdHelpMsg()));
        sender.sendMessage(("§6/morecrafting book §8- " + Main.GetInstance().GetConfigMessages().GetCmdHelpBookMsg()));
        sender.sendMessage(("§6/morecrafting reload §8- " + Main.GetInstance().GetConfigMessages().GetCmdHelpReloadMsg()));
        sender.sendMessage("");
        sender.sendMessage("§8===================================");;
        return true;
    }
}