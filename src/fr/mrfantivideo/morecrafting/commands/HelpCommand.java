package fr.mrfantivideo.morecrafting.commands;

import fr.mrfantivideo.morecrafting.config.MorecrafingConfig;
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
        sender.sendMessage(("§6/morecrafting §8- " + MorecrafingConfig.LANG.getMessage("command-help-morecrafting")));
        sender.sendMessage(("§6/morecrafting help §8- " + MorecrafingConfig.LANG.getMessage("command-help")));
        sender.sendMessage(("§6/morecrafting book §8- " + MorecrafingConfig.LANG.getMessage("command-help-book")));
        sender.sendMessage(("§6/morecrafting recipes §8- " + MorecrafingConfig.LANG.getMessage("command-help-recipes")));
        sender.sendMessage(("§6/morecrafting reload §8- " + MorecrafingConfig.LANG.getMessage("command-help-reload")));
        sender.sendMessage("");
        sender.sendMessage("§8===================================");
        return true;
    }

    @Override
    public boolean hasPermission(CommandSender sender)
    {
        return sender.isOp() || MorecrafingConfig.PERMISSIONS.hasPermissions(sender, "morecrafting.admin.help", "morecrafting.admin.*");
    }

    @Override
    public String getCommandUsage()
    {
        return "";
    }
}
