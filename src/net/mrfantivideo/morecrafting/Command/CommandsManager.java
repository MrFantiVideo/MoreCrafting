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
    private Map<String, AbstractCommand> m_commands = new HashMap<String, AbstractCommand>();

    public CommandsManager()
    {
        s_instance = this;
        Main.GetInstance().getCommand("morecrafting").setExecutor(this);
    }

    public static CommandsManager GetInstance()
    {
        return s_instance;
    }

    /*
        Register a new command
     */
    public void RegisterCommand(AbstractCommand command)
    {
        if(m_commands.containsKey(command.GetCommandPrefix()))
            return;
        m_commands.put(command.GetCommandPrefix(), command);
    }

    /*
        Unregister the specified command
     */
    public void UnregisterCommand(AbstractCommand command)
    {
        if(!m_commands.containsKey(command.GetCommandPrefix()))
            return;
        m_commands.remove(command.GetCommandPrefix());
    }

    /*
        Handle commands
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
                        sender.sendMessage(Main.GetInstance().GetConfigMessages().GetConfiguration().getString("messages.default.prefix").replace("&", "§") + Main.GetInstance().GetConfigMessages().GetConfiguration().getString("messages." + Main.GetInstance().GetConfigSettings().GetConfiguration().getString("language") + ".permission-denied").replace("&", "§"));
                }
                else
                    sender.sendMessage(Main.GetInstance().GetConfigMessages().GetConfiguration().getString("messages.default.prefix").replace("&", "§") + Main.GetInstance().GetConfigMessages().GetConfiguration().getString("messages." + Main.GetInstance().GetConfigSettings().GetConfiguration().getString("language") + ".command-unknown").replace("&", "§"));
            }
            else
                sender.sendMessage(Main.GetInstance().GetConfigMessages().GetConfiguration().getString("messages.default.prefix").replace("&", "§") + Main.GetInstance().GetConfigMessages().GetConfiguration().getString("messages." + Main.GetInstance().GetConfigSettings().GetConfiguration().getString("language") + ".command-version").replace("&", "§").replace("%minecraft%", "Minecraft 1.14.2").replace("%version%", "3.0"));
        }
        return false;
    }
}
