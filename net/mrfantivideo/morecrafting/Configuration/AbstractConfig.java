package net.mrfantivideo.morecrafting.Configuration;

import net.mrfantivideo.morecrafting.Main;
import net.mrfantivideo.morecrafting.Utils.LogUtils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public abstract class AbstractConfig
{
    protected CustomConfigFile m_file;
    private String m_fileName;

    public AbstractConfig(String fileName)
    {
        m_fileName = fileName.endsWith(".yml") ? fileName : fileName + ".yml";
        try
        {
            Load();
        }
        catch(IOException ex)
        {
            LogUtils.LogError("An error occured while loading the file configuration '" + fileName + "'", ex);
        }
    }

    /**
     * Get Custom Config File
     * @return Custom Config File
     */
    public CustomConfigFile GetCustomFile()
    {
        return m_file;
    }

    /**
     * Get Configuration
     * @return File Configuration
     */
    public FileConfiguration GetConfiguration()
    {
        return m_file.GetConfiguration();
    }

    /**
     * Get File
     * @return File
     */
    public File GetFile()
    {
        return m_file.GetFile();
    }

    /**
     * Get File Name
     * @return File Name
     */
    public String GetFileName()
    {
        return m_fileName;
    }

    /**
     * Load File
     * @throws IOException
     */
    protected void Load() throws IOException
    {
        if(m_file != null)
            return;
        File file = new File(Main.GetInstance().getDataFolder(), GetFileName());
        if(!file.exists())
            Main.GetInstance().saveResource(GetFileName(), false);
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        m_file = new CustomConfigFile(file, configuration);
    }

    /**
     * Save Configuration
     */
    public void Save()
    {
        if(m_file != null)
            GetCustomFile().Save();
    }
}
