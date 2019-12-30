package net.mrfantivideo.morecrafting.Configuration.Configs;

import net.mrfantivideo.morecrafting.Configuration.AbstractConfig;

import java.util.Set;

public class ConfigSettings extends AbstractConfig
{
    public ConfigSettings()
    {
        super("settings");
    }

    /**
     * Get Language from config
     *
     * @return Language if exists, null otherwise
     */
    public String GetLanguage()
    {
        if (m_file.GetConfiguration().contains("language"))
            return m_file.GetConfiguration().getString("language");
        return null;
    }

    /**
     * Get Debug from config
     *
     * @return Debug if exists, null otherwise
     */
    public boolean GetDebug()
    {
        return GetBool("debug");
    }

    public Set<String> GetSection(String path)
    {
        if (m_file.GetConfiguration().contains(path))
            return m_file.GetConfiguration().getConfigurationSection(path).getKeys(false);
        return null;
    }

    public Set<String> GetRecipes(String path)
    {
        if (m_file.GetConfiguration().contains(path))
            return m_file.GetConfiguration().getConfigurationSection(path).getKeys(false);
        return null;
    }

    public String GetString(String path)
    {
        return GetValue(String.class, path);
    }

    public boolean GetBool(String path)
    {
        return GetValue(Boolean.class, path);
    }

    public int GetInt(String path)
    {
        return GetValue(Integer.class, path);
    }

    public double GetDouble(String path)
    {
        return GetValue(Double.class, path);
    }

    public float GetFloat(String path)
    {
        double value = GetDouble(path);
        return (float) value;
    }

    @SuppressWarnings("unchecked")
    public <T> T GetValue(Class<T> type, String path)
    {
        if (m_file.GetConfiguration().contains(path))
            return (T) m_file.GetConfiguration().get(path);
        else
            return null;
    }

    /**
     * Get Value
     *
     * @param type Return type
     * @param path Path
     * @param <T>  Return Type
     *
     * @return Type value if exists, null otherwise
     */
    @SuppressWarnings({"null"})
    public <T> float GetValueFloat(Class<T> type, String path)
    {
        if (m_file.GetConfiguration().contains(path))
            return (float) m_file.GetConfiguration().getDouble(path);
        else
            return (Float) null;
    }
}
