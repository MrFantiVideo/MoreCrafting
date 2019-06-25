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
        sender.sendMessage("§8== §6MoreCrafting §8=====================");
        sender.sendMessage("");
        sender.sendMessage(("§6/morecrafting §8- " + Main.GetInstance().GetConfigMessages().GetConfiguration().getString("messages." + Main.GetInstance().GetConfigSettings().GetConfiguration().getString("language") + ".command-help-morecrafting").replace("&", "§")));
        sender.sendMessage(("§6/morecrafting help §8- " + Main.GetInstance().GetConfigMessages().GetConfiguration().getString("messages." + Main.GetInstance().GetConfigSettings().GetConfiguration().getString("language") + ".command-help").replace("&", "§")));
        sender.sendMessage(("§6/morecrafting book §8- " + Main.GetInstance().GetConfigMessages().GetConfiguration().getString("messages." + Main.GetInstance().GetConfigSettings().GetConfiguration().getString("language") + ".command-help-book").replace("&", "§")));
        sender.sendMessage(("§6/morecrafting reload §8- " + Main.GetInstance().GetConfigMessages().GetConfiguration().getString("messages." + Main.GetInstance().GetConfigSettings().GetConfiguration().getString("language") + ".command-help-reload").replace("&", "§")));
        sender.sendMessage("");
        sender.sendMessage("§8===================================");;
        return true;
    }
}