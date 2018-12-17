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
