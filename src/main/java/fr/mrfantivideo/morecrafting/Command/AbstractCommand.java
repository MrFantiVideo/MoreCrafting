package fr.mrfantivideo.morecrafting.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public abstract class AbstractCommand
{
    private String m_commandPrefix;

    public AbstractCommand(String commandPrefix)
    {
        m_commandPrefix = commandPrefix;
    }

    /**
     * Get Command Prefix
     *
     * @return Command Prefix
     */
    public String GetCommandPrefix()
    {
        return m_commandPrefix;
    }

    /***
     * Execute this command, this is called when a player input a registered command
     * @param sender Command Sender
     * @param cmd Command
     * @param commandLabel Command Label
     * @param args Command Arguments
     * @return true if command succesfully executed, false otherwise
     */
    public abstract boolean Execute(CommandSender sender, Command cmd, String commandLabel, String[] args);

    /**
     * Checks if the Command's sender has permission
     *
     * @param sender Commmand Sender
     *
     * @return true if the sender has permission, false otherwise
     */
    public abstract boolean HasPermission(CommandSender sender);
}
