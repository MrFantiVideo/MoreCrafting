package fr.mrfantivideo.morecrafting.Configuration.Configs;

import fr.mrfantivideo.morecrafting.Configuration.AbstractConfig;
import fr.mrfantivideo.morecrafting.Configuration.CustomConfigFile;
import fr.mrfantivideo.morecrafting.Main;
import fr.mrfantivideo.morecrafting.Utils.LogUtils;
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
     *
     * @param path Path to message
     * @return Message if path is valid, null otherwise
     */
    public String GetMessage(String path)
    {
        if (m_file.GetConfiguration().contains(path))
            return m_file.GetConfiguration().getString(path).replace("&", "§");
        return null;
    }

    /**
     * Get the prefix from config
     *
     * @return Prefix if exists, null otherwise
     */
    public String GetPrefix()
    {
        return GetMessage("messages.default.prefix");
    }

    public String GetGUITitle()
    {
        return GetMessage("messages.default.gui-title");
    }

    public String GetGUITitleMain()
    {
        return GetMessage("messages." + "fr_FR" + ".gui-title-main");
    }

    public String GetGUITitleCrafting()
    {
        return GetMessage("messages." + "fr_FR" + ".gui-title-crafting");
    }

    public String GetGUITitleFurnace()
    {
        return GetMessage("messages." + "fr_FR" + ".gui-title-furnace");
    }

    public String GetGUITitleSmoker()
    {
        return GetMessage("messages." + "fr_FR" + ".gui-title-smoker");
    }

    public String GetGUITitleBlasting()
    {
        return GetMessage("messages." + "fr_FR" + ".gui-title-blasting");
    }

    public String GetGUITitleStonecutting()
    {
        return GetMessage("messages." + "fr_FR" + ".gui-title-stonecutting");
    }

    public String GetGUITitleCampfire()
    {
        return GetMessage("messages." + "fr_FR" + ".gui-title-campfire");
    }

    public String GetCmdVersionMsg()
    {
        return GetMessage("messages." + "fr_FR" + ".command-version");
    }

    public String GetCmdUnknownMsg()
    {
        return GetMessage("messages." + "fr_FR" + ".command-unknown");
    }

    public String GetCmdReloadMsg()
    {
        return GetMessage("messages." + "fr_FR" + ".command-reload");
    }

    public String GetCmdHelpMsg()
    {
        return GetMessage("messages." + "fr_FR" + ".command-help");
    }

    public String GetCmdMoreCraftingMsg()
    {
        return GetMessage("messages." + "fr_FR" + ".command-help-morecrafting");
    }

    public String GetCmdHelpBookMsg()
    {
        return GetMessage("messages." + "fr_FR" + ".command-help-book");
    }

    public String GetCmdHelpRecipesMsg()
    {
        return GetMessage("messages." + "fr_FR" + ".command-help-recipes");
    }

    public String GetCmdHelpReloadMsg()
    {
        return GetMessage("messages." + "fr_FR" + ".command-help-reload");
    }

    public String GetCmdPermissionDeniedMsg()
    {
        return GetMessage("messages." + "fr_FR" + ".permission-denied");
    }

    @Override
    protected void Load()
    {
        if (m_file != null)
            return;
        String lang = "fr_FR";
        if (lang != null && !lang.isEmpty())
        {
            File file = new File(Main.getInstance().getDataFolder() + File.separator + "lang" + File.separator + lang + ".yml");
            if (file.exists())
                m_file = new CustomConfigFile(file, YamlConfiguration.loadConfiguration(file));
            else
            {
                Main.getInstance().saveResource("lang" + File.separator + lang + ".yml", false);
                m_file = new CustomConfigFile(file, YamlConfiguration.loadConfiguration(file));
            }
        }
        else
            LogUtils.LogError("Could not load file configuration 'ConfigMessages', no language found in file 'settings.yml'");
    }

    @Override
    public void Save()
    {
        LogUtils.LogError("Could not save 'ConfigMessages', operation not supported");
    }
}
