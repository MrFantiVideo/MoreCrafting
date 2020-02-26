package fr.mrfantivideo.morecrafting.commands;

import fr.mrfantivideo.morecrafting.Main;
import fr.mrfantivideo.morecrafting.Recipesold.RecipesManagerOld;
import fr.unreal852.sunrealcore.commands.BaseCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class ReloadCommand extends BaseCommand
{
    public ReloadCommand()
    {
        super("reload");
    }

    @Override
    public boolean execute(CommandSender sender, Command command, String s, String[] strings)
    {
        Main.getInstance().loadSettings();
        RecipesManagerOld.GetInstance().LoadRecipes();
        sender.sendMessage(Main.getInstance().getConfigMessages().GetPrefix() + Main.getInstance().getConfigMessages().GetCmdReloadMsg());
        return true;
    }

    @Override
    public boolean hasPermission(CommandSender sender)
    {
        return (sender.isOp() || sender.hasPermission(Main.getInstance().getConfigPermissions().GetAdminReloadPerm()) ||
                sender.hasPermission(Main.getInstance().getConfigPermissions().GetAdminAllPerm()));
    }

    @Override
    public String getCommandUsage()
    {
        return "";
    }
}
