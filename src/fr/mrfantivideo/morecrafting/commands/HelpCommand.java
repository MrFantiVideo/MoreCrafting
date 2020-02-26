package fr.mrfantivideo.morecrafting.commands;

import fr.mrfantivideo.morecrafting.Main;
import fr.unreal852.sunrealcore.commands.BaseCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class HelpCommand extends BaseCommand
{
    public HelpCommand()
    {
        super("help");
    }

    @Override
    public boolean execute(CommandSender sender, Command command, String s, String[] strings)
    {
        sender.sendMessage("§8== §6MoreCrafting §8=====================");
        sender.sendMessage("");
        sender.sendMessage(("§6/morecrafting §8- " + Main.getInstance().getConfigMessages().GetCmdMoreCraftingMsg()));
        sender.sendMessage(("§6/morecrafting help §8- " + Main.getInstance().getConfigMessages().GetCmdHelpMsg()));
        sender.sendMessage(("§6/morecrafting book §8- " + Main.getInstance().getConfigMessages().GetCmdHelpBookMsg()));
        sender.sendMessage(("§6/morecrafting recipes §8- " + Main.getInstance().getConfigMessages().GetCmdHelpRecipesMsg()));
        sender.sendMessage(("§6/morecrafting reload §8- " + Main.getInstance().getConfigMessages().GetCmdHelpReloadMsg()));
        sender.sendMessage("");
        sender.sendMessage("§8===================================");
        return true;
    }

    @Override
    public boolean hasPermission(CommandSender sender)
    {
        return (sender.isOp() || sender.hasPermission(Main.getInstance().getConfigPermissions().GetAdminHelpPerm()) ||
                sender.hasPermission(Main.getInstance().getConfigPermissions().GetAdminAllPerm()));
    }

    @Override
    public String getCommandUsage()
    {
        return "";
    }
}
