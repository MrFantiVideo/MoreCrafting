package net.mrfantivideo.morecrafting.Configuration;

import net.mrfantivideo.morecrafting.Utils.LogUtils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class CustomConfigFile
{
    private File m_file;

    private FileConfiguration m_fileConfiguration;

    public CustomConfigFile(File file, FileConfiguration fileConfiguration)
    {
        m_file = file;
        m_fileConfiguration = fileConfiguration;
    }

    /**
     * Get File
     * @return File
     */
    public File GetFile()
    {
        return m_file;
    }

    /**
     * Get Configuration
     * @return File Configuration
     */
    public FileConfiguration GetConfiguration()
    {
        return m_fileConfiguration;
    }

    /**
     * Set File
     * @param file File
     */
    public void SetFile(File file)
    {
        m_file = file;
        m_fileConfiguration = YamlConfiguration.loadConfiguration(file);
    }

    /**
     * Save File
     */
    public void Save()
    {
        if(m_file == null || m_fileConfiguration == null)
            return;
        try
        {
            GetConfiguration().save(GetFile());
        }
        catch(IOException ex)
        {
            LogUtils.LogError("Could not save config file '" + GetFile().getName() + "'", ex);
        }
    }
}
