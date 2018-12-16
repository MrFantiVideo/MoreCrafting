package net.mrfantivideo.morecrafting.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public abstract class AbstractCommand
{
    private String m_commandPrefix;

    public AbstractCommand(String commandPrefix)
    {
        m_commandPrefix = commandPrefix;
    }

    public String GetCommandPrefix()
    {
        return m_commandPrefix;
    }

    public abstract boolean Execute(CommandSender sender, Command cmd, String commandLabel, String[] args);

    public abstract boolean HasPermission(CommandSender sender);
}
