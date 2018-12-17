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
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class Main extends JavaPlugin
{
    private static Main s_instance;
    private ConfigSettings m_configSettings;
    private ConfigPermissions m_configPermissions;
    private ConfigMessages m_configMessages;

    public Main()
    {
        s_instance = this;
    }

    public static Main GetInstance()
    {
        return s_instance;
    }

    public ConfigSettings GetConfigSettings()
    {
        return m_configSettings;
    }

    public ConfigPermissions GetConfigPermissions()
    {
        return m_configPermissions;
    }

    public ConfigMessages GetConfigMessages()
    {
        return m_configMessages;
    }

    /*
        Called when enabling
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

        LoadRecipes();

        BroadcastEnabledMessage();
    }

    /*
    Broadcast the enabling message
    */
    private void BroadcastEnabledMessage()
    {
    	getServer().getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "== " + ChatColor.YELLOW + "MoreCrafting" + ChatColor.DARK_GRAY + " =====================");
    	System.out.println(" ");
    	getServer().getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "Plugin : " + ChatColor.GREEN + "ON" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Minecraft 1.13.2");
    	System.out.println(" ");
    	getServer().getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "Version " + ChatColor.YELLOW + "2.0 (pre-release 2)");
    	getServer().getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "Created by " + ChatColor.YELLOW + "MrFantiVideo" + ChatColor.DARK_GRAY + " | 2018 - 2019");
    	System.out.println(" ");
    	getServer().getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "=====================================");
    }

    /*
    	Broadcast the disabling message
     */
    public void onDisable()
    {
        getServer().getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "== " + ChatColor.YELLOW + "MoreCrafting" + ChatColor.DARK_GRAY + " =====================");
        System.out.println(" ");
        getServer().getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "Plugin : " + ChatColor.RED + "OFF" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Minecraft 1.13.2");
        System.out.println(" ");
        getServer().getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "Version " + ChatColor.YELLOW + "2.0 (pre-release 2)");
        getServer().getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "Created by " + ChatColor.YELLOW + "MrFantiVideo" + ChatColor.DARK_GRAY + " | 2018 - 2019");
        System.out.println(" ");
        getServer().getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "=====================================");
    }

    /*
        Load/Reload settings
     */
    public void LoadSettings()
    {
        m_configSettings = new ConfigSettings(this);
        m_configMessages = new ConfigMessages(this);
        m_configPermissions = new ConfigPermissions(this);
    }

    /*
        Load Recipes
     */
    @SuppressWarnings("deprecation")
	private void LoadRecipes()
    {
        /** Shaped Recipes **/
        for(String str : GetConfigSettings().GetConfiguration().getConfigurationSection("recipes.crafting").getKeys(false))
        {
            String itemPath = "recipes.crafting." + str + ".";
            if(!GetConfigSettings().GetConfiguration().getBoolean(itemPath + "enabled"))
                continue;
            ShapedRecipe craft = new ShapedRecipe(NamespacedKey.randomKey(), new ItemStack(Material.getMaterial(GetConfigSettings().GetConfiguration().getString(itemPath + "craft.result.id")), GetConfigSettings().GetConfiguration().getInt(itemPath + "craft.result.id-amount")));
            craft.shape("123", "456", "789");
            for(int i = 1; i <= 9; i++)
            {

                String itemID = GetConfigSettings().GetConfiguration().getString(itemPath + "craft.slots." + i);
                if(itemID == null || itemID.isEmpty())
                    continue;
                craft.setIngredient(Integer.toString(i).charAt(0), Material.getMaterial(GetConfigSettings().GetConfiguration().getString(itemPath + "craft.slots." + i)));
            }
            getServer().addRecipe(craft);
        }

        /** Furnace Recipes **/
        for(String str : GetConfigSettings().GetConfiguration().getConfigurationSection("recipes.furnace").getKeys(false))
        {
            String itemPath = "recipes.furnace." + str + ".";
            if(!GetConfigSettings().GetConfiguration().getBoolean(itemPath + "enabled"))
                continue;
            ItemStack result1 = new ItemStack(Material.getMaterial(GetConfigSettings().GetConfiguration().getString(itemPath + "fire.result.id")), GetConfigSettings().GetConfiguration().getInt(itemPath + "fire.result.id-amount"));
            FurnaceRecipe fire1 = new FurnaceRecipe(result1, Material.getMaterial(GetConfigSettings().GetConfiguration().getString(itemPath + "fire.slot.1")));
            getServer().addRecipe(fire1);
        }

        /** Books Shaped Recipes **/
        if(GetConfigSettings().GetConfiguration().getString("others.book.enabled").equals("true"))
        {
            ItemStack book = new ItemStack(Material.getMaterial(GetConfigSettings().GetConfiguration().getString("others.book.craft.result.id")), 1);
            ItemMeta meta = book.getItemMeta();
            meta.setDisplayName(GetConfigSettings().GetConfiguration().getString("others.book.craft.result.name").replace("&", "§"));
            meta.setLore(Arrays.asList(GetConfigSettings().GetConfiguration().getString("others.book.craft.result.lore").replace("&", "§")));
            book.setItemMeta(meta);
            ShapedRecipe craft = new ShapedRecipe(NamespacedKey.randomKey(), book);
            craft.shape("123", "456", "789");
            for(int i = 1; i <= 9; i++)
            {
                String itemID = GetConfigSettings().GetConfiguration().getString("others.book.craft.slots." + i);
                if(itemID == null || itemID.isEmpty())
                    continue;
                craft.setIngredient(Integer.toString(i).charAt(0), Material.getMaterial(GetConfigSettings().GetConfiguration().getString("others.book.craft.slots." + i)));
            }
            getServer().addRecipe(craft);
        }
    }
}

/// Todo :
///         Enregistrer les settings recurrent pour Ã©viter d'aller rechercher dans la config a chaques fois, par exemple pour le prefix des messages, ou pour chaques permissions

