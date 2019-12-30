package net.mrfantivideo.morecrafting.Command.Commands;

import net.mrfantivideo.morecrafting.Command.AbstractCommand;
import net.mrfantivideo.morecrafting.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class HelpCommand extends AbstractCommand
{
    public HelpCommand()
    {
        super("help");
    }

    public boolean HasPermission(CommandSender sender)
    {
        return (sender.isOp() || sender.hasPermission(Main.getInstance().getConfigPermissions().GetAdminHelpPerm()) ||
                sender.hasPermission(Main.getInstance().getConfigPermissions().GetAdminAllPerm()));
    }

    public boolean Execute(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        sender.sendMessage("§8== §6MoreCrafting §8=====================");
        sender.sendMessage("");
        sender.sendMessage(("§6/morecrafting §8- " + Main.getInstance().getConfigMessages().GetCmdMoreCraftingMsg()));
        sender.sendMessage(("§6/morecrafting help §8- " + Main.getInstance().getConfigMessages().GetCmdHelpMsg()));
        sender.sendMessage(("§6/morecrafting book §8- " + Main.getInstance().getConfigMessages().GetCmdHelpBookMsg()));
        sender.sendMessage(("§6/morecrafting reload §8- " + Main.getInstance().getConfigMessages().GetCmdHelpReloadMsg()));
        sender.sendMessage("");
        sender.sendMessage("§8===================================");
        return true;
    }
}
