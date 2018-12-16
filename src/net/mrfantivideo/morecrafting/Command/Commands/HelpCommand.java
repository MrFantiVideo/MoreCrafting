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
        return (sender.isOp() || sender.hasPermission(Main.GetInstance().GetConfigPermissions().GetConfiguration().getString("permissions.morecrafting.admin.help")) || sender.hasPermission(Main.GetInstance().GetConfigPermissions().GetConfiguration().getString("permissions.morecrafting.*")));
    }
    public boolean Execute(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        sender.sendMessage("§8== §eMoreCrafting §8=====================");
        sender.sendMessage("");
        sender.sendMessage("§e/morecrafting §8- §7§oPlugin version.");
        sender.sendMessage("§e/morecrafting help §8- §7§oAll available commands.");
        sender.sendMessage("§e/morecrafting book §8- §7§oGive the recipe book.");
        sender.sendMessage("§e/morecrafting reload §8- §7§oReload configuration files.");
        sender.sendMessage("");
        sender.sendMessage("§8===================================");;
        return true;
    }
}