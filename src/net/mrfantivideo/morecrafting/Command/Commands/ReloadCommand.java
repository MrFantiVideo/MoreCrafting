package net.mrfantivideo.morecrafting.Command.Commands;

import net.mrfantivideo.morecrafting.Command.AbstractCommand;
import net.mrfantivideo.morecrafting.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class ReloadCommand extends AbstractCommand
{
    public ReloadCommand()
    {
        super("reload");
    }

    public boolean HasPermission(CommandSender sender)
    {
        return (sender.isOp() || sender.hasPermission(Main.GetInstance().GetConfigPermissions().GetConfiguration().getString("permissions.morecrafting.admin.reload")) || sender.hasPermission(Main.GetInstance().GetConfigPermissions().GetConfiguration().getString("permissions.morecrafting.admin.*")));
    }

    public boolean Execute(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        Main.GetInstance().LoadSettings();
        sender.sendMessage(Main.GetInstance().GetConfigMessages().GetConfiguration().getString("messages.default.prefix").replace("&", "§") + Main.GetInstance().GetConfigMessages().GetConfiguration().getString("messages." + Main.GetInstance().GetConfigSettings().GetConfiguration().getString("language") + ".command-reload").replace("&", "§"));
        return true;
    }
}
