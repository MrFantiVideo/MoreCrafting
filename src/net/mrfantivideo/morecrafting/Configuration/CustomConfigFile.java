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

    /*
        Get File
     */
    public File GetFile()
    {
        return m_file;
    }

    /*
        Get Configuration
     */
    public FileConfiguration GetConfiguration()
    {
        return m_fileConfiguration;
    }

    /*
        Set file
     */
    public void SetFile(File file)
    {
        m_file = file;
        m_fileConfiguration = YamlConfiguration.loadConfiguration(file);
    }

    /*
        Save file
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
