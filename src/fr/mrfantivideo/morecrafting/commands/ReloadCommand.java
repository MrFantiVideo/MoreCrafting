package fr.mrfantivideo.morecrafting.commands;

import fr.mrfantivideo.morecrafting.Main;
import fr.mrfantivideo.morecrafting.config.MorecrafingConfig;
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
        //RecipesManagerOld.GetInstance().LoadRecipes();
        sender.sendMessage(MorecrafingConfig.LANG.getMessage("prefix") + (MorecrafingConfig.LANG.getMessage("command-reload")));
        return true;
    }

    @Override
    public boolean hasPermission(CommandSender sender)
    {
        return sender.isOp() || MorecrafingConfig.PERMISSIONS.hasPermissions(sender, "morecrafting.admin.reload", "morecrafting.admin.*");
    }

    @Override
    public String getCommandUsage()
    {
        return "";
    }
}
