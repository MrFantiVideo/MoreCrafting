package net.mrfantivideo.morecrafting.Command.Commands;

import net.mrfantivideo.morecrafting.Command.AbstractCommand;
import net.mrfantivideo.morecrafting.Main;
import net.mrfantivideo.morecrafting.Recipes.RecipesManager;
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
        return (sender.isOp() || sender.hasPermission(Main.GetInstance().GetConfigPermissions().GetAdminReloadPerm()) || sender.hasPermission(Main.GetInstance().GetConfigPermissions().GetAdminAllPerm()));
    }

    public boolean Execute(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        Main.GetInstance().LoadSettings();
        RecipesManager.GetInstance().LoadRecipes();
        sender.sendMessage(Main.GetInstance().GetConfigMessages().GetPrefix() + Main.GetInstance().GetConfigMessages().GetCmdReloadMsg());
        return true;
    }
}
