package net.mrfantivideo.morecrafting.Command;

import net.mrfantivideo.morecrafting.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import java.util.HashMap;
import java.util.Map;

public class CommandsManager implements CommandExecutor
{
    private static CommandsManager s_instance;

    /**
     * Get Commands Manager Instance
     * @return
     */
    public static CommandsManager GetInstance()
    {
        return s_instance;
    }

    private Map<String, AbstractCommand> m_commands = new HashMap<String, AbstractCommand>();

    public CommandsManager()
    {
        if(s_instance != null)
            return;
        s_instance = this;
        Main.GetInstance().getCommand("morecrafting").setExecutor(this);
    }

    /**
     * Register a new command
     * @param command Command to register
     */
    public void RegisterCommand(AbstractCommand command)
    {
        if(m_commands.containsKey(command.GetCommandPrefix()))
            return;
        m_commands.put(command.GetCommandPrefix(), command);
    }

    /**
     * Unregister the specified command
     * @param command Command to unregister
     */
    public void UnregisterCommand(AbstractCommand command)
    {
        if(!m_commands.containsKey(command.GetCommandPrefix()))
            return;
        m_commands.remove(command.GetCommandPrefix());
    }

    /**
     * Handle Commands
     * @param sender Command Sender
     * @param cmd Command
     * @param commandLabel Command Label
     * @param args Command Arguments
     * @return true if command succesfully executed, false otherwise
     */
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        if(cmd.getName().equalsIgnoreCase("morecrafting"))
        {
            if(args.length >= 1)
            {
                String commandLowered = args[0].toLowerCase();
                if(m_commands.containsKey(commandLowered))
                {
                    AbstractCommand command = m_commands.get(commandLowered);
                    if(command.HasPermission(sender))
                        return m_commands.get(commandLowered).Execute(sender, cmd, commandLabel, args);
                    else
                        sender.sendMessage(Main.GetInstance().GetConfigMessages().GetPrefix() + Main.GetInstance().GetConfigMessages().GetCmdPermissionDeniedMsg());
                }
                else
                    sender.sendMessage(Main.GetInstance().GetConfigMessages().GetPrefix() + Main.GetInstance().GetConfigMessages().GetCmdUnknownMsg());
            }
            else
                sender.sendMessage(Main.GetInstance().GetConfigMessages().GetPrefix() + Main.GetInstance().GetConfigMessages().GetCmdVersionMsg().replace("%minecraft%", "Minecraft 1.14").replace("%version%", "3.0"));
        }
        return false;
    }
}
