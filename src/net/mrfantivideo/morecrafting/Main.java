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
import net.mrfantivideo.morecrafting.Recipes.CustomRecipe;
import net.mrfantivideo.morecrafting.Recipes.RecipesManager;
import net.mrfantivideo.morecrafting.Utils.ConfigUtils;
import net.mrfantivideo.morecrafting.Utils.EConfig;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

import static net.mrfantivideo.morecrafting.Utils.ConfigUtils.Get;

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
        m_configSettings = new ConfigSettings();
        m_configMessages = new ConfigMessages();
        m_configPermissions = new ConfigPermissions();
    }

    /*
        Load Recipes
     */
    @SuppressWarnings({"deprecation", "ConstantConditions"})
	private void LoadRecipes()
    {
        new RecipesManager();

        /** Shaped Recipes **/
        for(String str : GetConfigSettings().GetConfiguration().getConfigurationSection("recipes.crafting").getKeys(false))
        {
            String itemPath = "recipes.crafting." + str + ".";
            if(!Get(Boolean.class, EConfig.SETTINGS, itemPath + "enabled"))
                continue;
            String craftID = Get(String.class, EConfig.SETTINGS, itemPath + "craft.result.id");
            String craftCustomName = Get(String.class, EConfig.SETTINGS, itemPath + "craft.result.name");
            String craftCustomLore = Get(String.class, EConfig.SETTINGS, itemPath + "craft.result.lore");
            int craftAmount = Get(Integer.class, EConfig.SETTINGS, itemPath + "craft.result.id-amount");
            int bookInventorySlot = Get(Integer.class, EConfig.SETTINGS, itemPath + "craft.result.id-book");
            ItemStack result = new ItemStack(Material.getMaterial(craftID), craftAmount);
            ItemMeta meta = result.getItemMeta();
            if(craftCustomName != null && !craftCustomName.isEmpty())
                meta.setDisplayName(craftCustomName);
            if(craftCustomLore != null && !craftCustomLore.isEmpty())
                meta.setLore(Arrays.asList(craftCustomLore));
            result.setItemMeta(meta);
            ShapedRecipe craft = new ShapedRecipe(NamespacedKey.randomKey(), result);
            craft.shape("123", "456", "789");
            for(int i = 1; i <= 9; i++)
            {
                String itemID = Get(String.class, EConfig.SETTINGS, itemPath + "craft.slots." + i);
                if(itemID == null || itemID.isEmpty())
                    continue;
                craft.setIngredient(Integer.toString(i).charAt(0), Material.getMaterial(Get(String.class, EConfig.SETTINGS, itemPath + "craft.slots." + i)));
            }
            RecipesManager.GetInstance().AddRecipe(craftID, new CustomRecipe(craft, bookInventorySlot, itemPath, str));
        }

        /** Furnace Recipes **/
        for(String str : GetConfigSettings().GetConfiguration().getConfigurationSection("recipes.furnace").getKeys(false))
        {
            String itemPath = "recipes.furnace." + str + ".";
            if(!Get(Boolean.class, EConfig.SETTINGS, itemPath + "enabled"))
                continue;
            String craftID = Get(String.class, EConfig.SETTINGS,itemPath + "fire.result.id");
            String craftCustomName = Get(String.class, EConfig.SETTINGS, itemPath + "fire.result.name");
            String craftCustomLore = Get(String.class, EConfig.SETTINGS, itemPath + "fire.result.lore");
            int craftAmount = Get(Integer.class, EConfig.SETTINGS, itemPath + "fire.result.id-amount");
            int bookInventorySlot = Get(Integer.class, EConfig.SETTINGS, itemPath + "fire.result.id-book");
            ItemStack result = new ItemStack(Material.getMaterial(craftID), craftAmount);
            ItemMeta meta = result.getItemMeta();
            if(craftCustomName != null && !craftCustomName.isEmpty())
                meta.setDisplayName(craftCustomName);
            if(craftCustomLore != null && !craftCustomLore.isEmpty())
                meta.setLore(Arrays.asList(craftCustomLore));
            result.setItemMeta(meta);
            FurnaceRecipe recipe = new FurnaceRecipe(result, Material.getMaterial(Get(String.class, EConfig.SETTINGS, itemPath + "fire.slot.1")));
            RecipesManager.GetInstance().AddRecipe(craftID, new CustomRecipe(recipe, bookInventorySlot, itemPath, str));
        }

        /** Books Shaped Recipes **/
        if(Get(Boolean.class, EConfig.SETTINGS, "others.book.enabled"))
        {
            String craftID = Get(String.class, EConfig.SETTINGS,"others.book.craft.result.id");
            ItemStack book = new ItemStack(Material.getMaterial(craftID), 1);
            ItemMeta meta = book.getItemMeta();
            meta.setDisplayName(Get(String.class, EConfig.SETTINGS,"others.book.craft.result.name").replace("&", "§"));
            meta.setLore(Arrays.asList(Get(String.class, EConfig.SETTINGS,"others.book.craft.result.lore").replace("&", "§")));
            book.setItemMeta(meta);
            ShapedRecipe craft = new ShapedRecipe(NamespacedKey.randomKey(), book);
            craft.shape("123", "456", "789");
            for(int i = 1; i <= 9; i++)
            {
                String itemID = Get(String.class, EConfig.SETTINGS,"others.book.craft.slots." + i);
                if(itemID == null || itemID.isEmpty())
                    continue;
                craft.setIngredient(Integer.toString(i).charAt(0), Material.getMaterial(Get(String.class, EConfig.SETTINGS,"others.book.craft.slots." + i)));
            }
            RecipesManager.GetInstance().AddRecipe(craftID, new CustomRecipe(craft, -1, null, null));
        }
    }
}

/// Todo :
///         Enregistrer les settings recurrent pour Ã©viter d'aller rechercher dans la config a chaques fois, par exemple pour le prefix des messages, ou pour chaques permissions

