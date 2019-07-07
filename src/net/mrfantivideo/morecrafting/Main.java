package net.mrfantivideo.morecrafting;

import net.mrfantivideo.morecrafting.Command.Commands.BookCommand;
import net.mrfantivideo.morecrafting.Command.Commands.HelpCommand;
import net.mrfantivideo.morecrafting.Command.Commands.ReloadCommand;
import net.mrfantivideo.morecrafting.Command.CommandsManager;
import net.mrfantivideo.morecrafting.Configuration.Configs.ConfigMessages;
import net.mrfantivideo.morecrafting.Configuration.Configs.ConfigPermissions;
import net.mrfantivideo.morecrafting.Configuration.Configs.ConfigSettings;
import net.mrfantivideo.morecrafting.Listeners.PlayerInteractListener;
import net.mrfantivideo.morecrafting.Listeners.PlayerInventoryListener;
import net.mrfantivideo.morecrafting.Recipes.RecipesManager;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
{
    private static Main s_instance;
    private ConfigSettings m_configSettings;
    private ConfigPermissions m_configPermissions;
    private ConfigMessages m_configMessages;
    private boolean m_debug = false;

    public Main()
    {
        s_instance = this;
    }

    /**
     * Get Main Instance
     * @return Instance
     */
    public static Main GetInstance()
    {
        return s_instance;
    }

    /**
     * Get Config Settings
     * @return Config Settings
     */
    public ConfigSettings GetConfigSettings()
    {
        return m_configSettings;
    }

    /**
     * Get Config Permissions
     * @return Config Permissions
     */
    public ConfigPermissions GetConfigPermissions()
    {
        return m_configPermissions;
    }

    /**
     * Get Config Messages
     * @return Config Messages
     */
    public ConfigMessages GetConfigMessages()
    {
        return m_configMessages;
    }

    /**
     * Is Debug enabled
     * @return true if debug is enabled, false otherwise
     */
    public boolean IsDebugging()
    {
        return m_debug;
    }

    /**
     * On Enable
     */
    public void onEnable()
    {
        LoadSettings();

        getServer().getPluginManager().registerEvents(new PlayerInteractListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerInventoryListener(), this);

        new CommandsManager();
        CommandsManager.GetInstance().RegisterCommand(new ReloadCommand());
        CommandsManager.GetInstance().RegisterCommand(new HelpCommand());
        CommandsManager.GetInstance().RegisterCommand(new BookCommand());

        new RecipesManager();

        BroadcastEnableMessage(true);
    }

    /**
     * On Disable
     */
    public void onDisable()
    {
        BroadcastEnableMessage(false);
    }

    /**
     * Broadcast Enabled Message
     */
    private void BroadcastEnableMessage(boolean enable)
    {
        if(enable)
        {
            System.out.println(" ");
            getServer().getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "             ÉÍÍÍÍÍÍÍÍÍÍÍÍÍÍ»");
            getServer().getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "ÉÍÍÍÍÍÍÍÍÍÍÍÍ¹" + ChatColor.GRAY + " MoreCrafting " + ChatColor.DARK_GRAY + "ÌÍÍÍÍÍÍÍÍÍÍÍÍ»");
            getServer().getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "º            ÈÍÍÍÍÍÍÍÍÍÍÍÍÍÍ¼            º");
            getServer().getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "º " + ChatColor.GRAY + "Plugin ¯ " + ChatColor.GREEN + "ON " + ChatColor.GRAY + "            Minecraft 1.14" + ChatColor.DARK_GRAY + " º");
            getServer().getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "º " + ChatColor.GRAY + "Version ¯ 3.0" + ChatColor.DARK_GRAY + "           (Stable Build) º");
            getServer().getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "º                                        º");
            getServer().getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "ÌÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍËÍÍÍÍÍÍÍÍÍÍÍÍÍ¹");
            getServer().getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "º Created by " + ChatColor.GRAY + "Mr.FantiVideo" + ChatColor.DARK_GRAY + " º 2018 - 2019 º");
            getServer().getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "ÈÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÊÍÍÍÍÍÍÍÍÍÍÍÍÍ¼");
            System.out.println(" ");
        }
        else
        {
            System.out.println(" ");
            getServer().getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "             ÉÍÍÍÍÍÍÍÍÍÍÍÍÍÍ»");
            getServer().getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "ÉÍÍÍÍÍÍÍÍÍÍÍÍ¹" + ChatColor.GRAY + " MoreCrafting " + ChatColor.DARK_GRAY + "ÌÍÍÍÍÍÍÍÍÍÍÍÍ»");
            getServer().getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "º            ÈÍÍÍÍÍÍÍÍÍÍÍÍÍÍ¼            º");
            getServer().getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "º " + ChatColor.GRAY + "Plugin ¯ " + ChatColor.RED + "OFF" + ChatColor.GRAY + "            Minecraft 1.14" + ChatColor.DARK_GRAY + " º");
            getServer().getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "º " + ChatColor.GRAY + "Version ¯ 3.0" + ChatColor.DARK_GRAY + "           (Stable Build) º");
            getServer().getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "º                                        º");
            getServer().getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "ÌÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍËÍÍÍÍÍÍÍÍÍÍÍÍÍ¹");
            getServer().getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "º Created by " + ChatColor.GRAY + "Mr.FantiVideo" + ChatColor.DARK_GRAY + " º 2018 - 2019 º");
            getServer().getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "ÈÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÊÍÍÍÍÍÍÍÍÍÍÍÍÍ¼");
            System.out.println(" ");
        }
    }

    /**
     * Load Settings
     */
    public void LoadSettings()
    {
        m_configSettings = new ConfigSettings();
        m_configMessages = new ConfigMessages();
        m_configPermissions = new ConfigPermissions();
        m_debug = GetConfigSettings().GetDebug();
    }
}
