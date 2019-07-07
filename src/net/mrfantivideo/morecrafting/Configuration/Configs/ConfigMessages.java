package net.mrfantivideo.morecrafting.Configuration.Configs;

import net.mrfantivideo.morecrafting.Configuration.AbstractConfig;
import net.mrfantivideo.morecrafting.Configuration.CustomConfigFile;
import net.mrfantivideo.morecrafting.Main;
import net.mrfantivideo.morecrafting.Utils.LogUtils;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;

public class ConfigMessages extends AbstractConfig
{
    public ConfigMessages()
    {
        super("");
    }

    /**
     * Get Message from config
     * @param path Path to message
     * @return Message if path is valid, null otherwise
     */
    public String GetMessage(String path)
    {
        if(m_file.GetConfiguration().contains(path))
            return m_file.GetConfiguration().getString(path).replace("&", "§");
        return null;
    }

    /**
     * Get the prefix from config
     * @return Prefix if exists, null otherwise
     */
    public String GetPrefix()
    {
        return GetMessage("messages.default.prefix");
    }

    /**
     * Get GUI Title from config
     * @return GUI Title if exists, null otherwise
     */
    public String GetGUITitle()
    {
        return GetMessage("messages.default.gui-title");
    }

    /**
     * Get GUI Title Main from config
     * @return GUI Title Main if exists, null otherwise
     */
    public String GetGUITitleMain()
    {
        return GetMessage("messages." + Main.GetInstance().GetConfigSettings().GetLanguage() + ".gui-title-main");
    }

    /**
     * Get GUI Title Recipe Crafting from config
     * @return GUI Title Recipe Crafting if exists, null otherwise
     */
    public String GetGUITitleCrafting()
    {
        return GetMessage("messages." + Main.GetInstance().GetConfigSettings().GetLanguage() + ".gui-title-crafting");
    }
    
    /**
     * Get GUI Title Recipe Furnace from config
     * @return GUI Title Recipe Furnace if exists, null otherwise
     */
    public String GetGUITitleFurnace()
    {
        return GetMessage("messages." + Main.GetInstance().GetConfigSettings().GetLanguage() + ".gui-title-furnace");
    }
    
    /**
     * Get GUI Title Recipe Smoker from config
     * @return GUI Title Recipe Smoker if exists, null otherwise
     */
    public String GetGUITitleSmoker()
    {
        return GetMessage("messages." + Main.GetInstance().GetConfigSettings().GetLanguage() + ".gui-title-smoker");
    }
    
    /**
     * Get GUI Title Recipe Blasting from config
     * @return GUI Title Recipe Blasting if exists, null otherwise
     */
    public String GetGUITitleBlasting()
    {
        return GetMessage("messages." + Main.GetInstance().GetConfigSettings().GetLanguage() + ".gui-title-blasting");
    }

    /**
     * Get GUI Title Recipe Stonecutting from config
     * @return GUI Title Recipe Stonecutting if exists, null otherwise
     */
    public String GetGUITitleStonecutting()
    {
        return GetMessage("messages." + Main.GetInstance().GetConfigSettings().GetLanguage() + ".gui-title-stonecutting");
    }
    
    /**
     * Get GUI Title Recipe Campfire from config
     * @return GUI Title Recipe Campfire if exists, null otherwise
     */
    public String GetGUITitleCampfire()
    {
        return GetMessage("messages." + Main.GetInstance().GetConfigSettings().GetLanguage() + ".gui-title-campfire");
    }
    
    /**
     * Get Cmd Version Message from config
     * @return Cmd Version Message if exists, null otherwise
     */
    public String GetCmdVersionMsg()
    {
        return GetMessage("messages." + Main.GetInstance().GetConfigSettings().GetLanguage() + ".command-version");
    }

    /**
     * Get Cmd Unknown Message from config
     * @return Cmd Unknown Message if exists, null otherwise
     */
    public String GetCmdUnknownMsg()
    {
        return GetMessage("messages." + Main.GetInstance().GetConfigSettings().GetLanguage() + ".command-unknown");
    }

    /**
     * Get Cmd Reload Message from config
     * @return Cmd Reload Message if exists, null otherwise
     */
    public String GetCmdReloadMsg()
    {
        return GetMessage("messages." + Main.GetInstance().GetConfigSettings().GetLanguage() + ".command-reload");
    }

    /**
     * Get Cmd Help Message from config
     * @return Cmd Help Message if exists, null otherwise
     */
    public String GetCmdHelpMsg()
    {
        return GetMessage("messages." + Main.GetInstance().GetConfigSettings().GetLanguage() + ".command-help");
    }

    /**
     * Get Cmd MoreCrafting Message from config
     * @return Cmd MoreCrafting Message if exists, null otherwise
     */
    public String GetCmdMoreCraftingMsg()
    {
        return GetMessage("messages." + Main.GetInstance().GetConfigSettings().GetLanguage() + ".command-help-morecrafting");
    }

    /**
     * Get Cmd Help Book Message from config
     * @return Cmd Help Book Message if exists, null otherwise
     */
    public String GetCmdHelpBookMsg()
    {
        return GetMessage("messages." + Main.GetInstance().GetConfigSettings().GetLanguage() + ".command-help-book");
    }

    /**
     * Get Cmd Help Reload Message from config
     * @return Cmd Help Reload Message if exists, null otherwise
     */
    public String GetCmdHelpReloadMsg()
    {
        return GetMessage("messages." + Main.GetInstance().GetConfigSettings().GetLanguage() + ".command-help-reload");
    }

    /**
     * Get Cmd Permission Denied Message from config
     * @return Cmd Permission Denied Message if exists, null otherwise
     */
    public String GetCmdPermissionDeniedMsg()
    {
        return GetMessage("messages." + Main.GetInstance().GetConfigSettings().GetLanguage() + ".permission-denied");
    }

    @Override
    protected void Load()
    {
        if(m_file != null)
            return;
        String lang = Main.GetInstance().GetConfigSettings().GetConfiguration().getString("language");
        if(lang != null && !lang.isEmpty())
        {
            File file = new File(Main.GetInstance().getDataFolder() + File.separator + "lang" + File.separator + lang + ".yml");
            if(file.exists())
                m_file = new CustomConfigFile(file, YamlConfiguration.loadConfiguration(file));
            else
            {
                Main.GetInstance().saveResource("lang" + File.separator + lang + ".yml", false);
                m_file = new CustomConfigFile(file, YamlConfiguration.loadConfiguration(file));
            }
        }
        else
            LogUtils.LogError("Could not load file configuration 'ConfigMessages', no language found in file 'settings.yml'", null);
    }

    @Override
    public void Save()
    {
        LogUtils.LogError("Could not save 'ConfigMessages', operation not supported", null);
    }
}
