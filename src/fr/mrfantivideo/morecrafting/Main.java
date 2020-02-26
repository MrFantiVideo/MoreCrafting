package fr.mrfantivideo.morecrafting;

import fr.mrfantivideo.morecrafting.commands.BookCommand;
import fr.mrfantivideo.morecrafting.commands.HelpCommand;
import fr.mrfantivideo.morecrafting.commands.RecipesCommand;
import fr.mrfantivideo.morecrafting.commands.ReloadCommand;
import fr.mrfantivideo.morecrafting.Configuration.Configs.ConfigMessages;
import fr.mrfantivideo.morecrafting.Configuration.Configs.ConfigPermissions;
import fr.mrfantivideo.morecrafting.Listeners.PlayerInteractListener;
import fr.mrfantivideo.morecrafting.Listeners.PlayerInventoryListener;
import fr.mrfantivideo.morecrafting.items.RecipeBookSpecialItem;
import fr.mrfantivideo.morecrafting.recipes.RecipesManager;
import fr.unreal852.sunrealcore.commands.CommandsManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
{
    private static Main              s_instance;
    private static CommandsManager   m_commandsManager;
    private        ConfigPermissions m_configPermissions;
    private        ConfigMessages    m_configMessages;
    private        boolean           m_debug = false;

    public Main()
    {
        s_instance = this;
    }

    /**
     * Get Main Instance
     *
     * @return Instance
     */
    public static Main getInstance()
    {
        return s_instance;
    }

    /**
     * Returns {@link CommandsManager}
     *
     * @return Commands Manager
     */
    public CommandsManager getCommandsManager()
    {
        return m_commandsManager;
    }

    /**
     * Get Config Permissions
     *
     * @return Config Permissions
     */
    public ConfigPermissions getConfigPermissions()
    {
        return m_configPermissions;
    }

    /**
     * Get Config Messages
     *
     * @return Config Messages
     */
    public ConfigMessages getConfigMessages()
    {
        return m_configMessages;
    }

    /**
     * Is Debug enabled
     *
     * @return true if debug is enabled, false otherwise
     */
    public boolean isDebugging()
    {
        return m_debug;
    }

    /**
     * On Enable
     */
    public void onEnable()
    {
        //loadSettings();

        getServer().getPluginManager().registerEvents(new PlayerInteractListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerInventoryListener(), this);

        m_commandsManager = new CommandsManager(this, "morecrafting");
        m_commandsManager.registerCommand(new ReloadCommand());
        m_commandsManager.registerCommand(new HelpCommand());
        m_commandsManager.registerCommand(new BookCommand());
        m_commandsManager.registerCommand(new RecipesCommand());

        //new RecipesManagerOld();
        RecipesManager.loadRecipes();
        new RecipeBookSpecialItem("Recipe book", new ItemStack(Material.BOOK), "");

        broadcastEnableMessage(true);
    }

    /**
     * On Disable
     */
    public void onDisable()
    {
        broadcastEnableMessage(false);
    }

    /**
     * Broadcast Enabled Message
     */
    private void broadcastEnableMessage(boolean enable)
    {
        if (enable)
        {
            System.out.println(" ");
            getServer().getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "             ÉÍÍÍÍÍÍÍÍÍÍÍÍÍÍ»");
            getServer().getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "ÉÍÍÍÍÍÍÍÍÍÍÍÍ¹" + ChatColor.GRAY + " MoreCrafting " + ChatColor.DARK_GRAY + "ÌÍÍÍÍÍÍÍÍÍÍÍÍ»");
            getServer().getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "º            ÈÍÍÍÍÍÍÍÍÍÍÍÍÍÍ¼            º");
            getServer().getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "º " + ChatColor.GRAY + "Plugin ¯ " + ChatColor.GREEN + "ON " + ChatColor.GRAY + "            Minecraft 1.15" + ChatColor.DARK_GRAY + " º");
            getServer().getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "º " + ChatColor.GRAY + "Version ¯ 3.5" + ChatColor.DARK_GRAY + "           (Stable Build) º");
            getServer().getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "º                                        º");
            getServer().getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "ÌÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍËÍÍÍÍÍÍÍÍÍÍÍÍÍ¹");
            getServer().getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "º Created by " + ChatColor.GRAY + "Mr.FantiVideo" + ChatColor.DARK_GRAY + " º 2018 - 2020 º");
            getServer().getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "ÈÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÊÍÍÍÍÍÍÍÍÍÍÍÍÍ¼");
            System.out.println(" ");
        }
        else
        {
            System.out.println(" ");
            getServer().getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "             ÉÍÍÍÍÍÍÍÍÍÍÍÍÍÍ»");
            getServer().getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "ÉÍÍÍÍÍÍÍÍÍÍÍÍ¹" + ChatColor.GRAY + " MoreCrafting " + ChatColor.DARK_GRAY + "ÌÍÍÍÍÍÍÍÍÍÍÍÍ»");
            getServer().getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "º            ÈÍÍÍÍÍÍÍÍÍÍÍÍÍÍ¼            º");
            getServer().getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "º " + ChatColor.GRAY + "Plugin ¯ " + ChatColor.RED + "OFF" + ChatColor.GRAY + "            Minecraft 1.15" + ChatColor.DARK_GRAY + " º");
            getServer().getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "º " + ChatColor.GRAY + "Version ¯ 3.5" + ChatColor.DARK_GRAY + "           (Stable Build) º");
            getServer().getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "º                                        º");
            getServer().getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "ÌÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍËÍÍÍÍÍÍÍÍÍÍÍÍÍ¹");
            getServer().getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "º Created by " + ChatColor.GRAY + "Mr.FantiVideo" + ChatColor.DARK_GRAY + " º 2018 - 2020 º");
            getServer().getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "ÈÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÊÍÍÍÍÍÍÍÍÍÍÍÍÍ¼");
            System.out.println(" ");
        }
    }

    /**
     * Load Settings
     */
    public void loadSettings()
    {
        m_configMessages = new ConfigMessages();
        m_configPermissions = new ConfigPermissions();
    }
}
