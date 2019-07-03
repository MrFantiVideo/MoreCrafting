package net.mrfantivideo.morecrafting.Configuration.Configs;

import net.mrfantivideo.morecrafting.Configuration.AbstractConfig;
import org.bukkit.inventory.Inventory;

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
        return (float)value;
    }

    public <T> T GetValue(Class<T> type, String path)
    {
        if (m_file.GetConfiguration().contains(path))
            return (T) m_file.GetConfiguration().get(path);
        else
            return null;
    }

    /**
     * Get Recipes
     *
     * @return Recipes if exists, null otherwise
     */
    public Set<String> GetRecipes()
    {
        if (m_file.GetConfiguration().contains("recipes.crafting"))
            return m_file.GetConfiguration().getConfigurationSection("recipes.crafting").getKeys(false);
        return null;
    }

    /**
     * Get Furnace Recipes
     *
     * @return Furnace Recipes if exists, null otherwise
     */
    public Set<String> GetFurnaceRecipes()
    {
        if (m_file.GetConfiguration().contains("recipes.furnace"))
            return m_file.GetConfiguration().getConfigurationSection("recipes.furnace").getKeys(false);
        return null;
    }

    /**
     * Get Furnace Recipes
     *
     * @return Furnace Recipes if exists, null otherwise
     */
    public Set<String> GetSmokingRecipes()
    {
        if (m_file.GetConfiguration().contains("recipes.smoking"))
            return m_file.GetConfiguration().getConfigurationSection("recipes.smoking").getKeys(false);
        return null;
    }

    /**
     * Get Furnace Recipes
     *
     * @return Furnace Recipes if exists, null otherwise
     */
    public Set<String> GetBlastingRecipes()
    {
        if (m_file.GetConfiguration().contains("recipes.blasting"))
            return m_file.GetConfiguration().getConfigurationSection("recipes.blasting").getKeys(false);
        return null;
    }

    /**
     * Get Furnace Recipes
     *
     * @return Furnace Recipes if exists, null otherwise
     */
    public Set<String> GetStonecuttingRecipes()
    {
        if (m_file.GetConfiguration().contains("recipes.stonecutting"))
            return m_file.GetConfiguration().getConfigurationSection("recipes.stonecutting").getKeys(false);
        return null;
    }

    /**
     * Get Furnace Recipes
     *
     * @return Furnace Recipes if exists, null otherwise
     */
    public Set<String> GetCampfireRecipes()
    {
        if (m_file.GetConfiguration().contains("recipes.campfire"))
            return m_file.GetConfiguration().getConfigurationSection("recipes.campfire").getKeys(false);
        return null;
    }

    /**
     * Get Recipe Value
     *
     * @param type       Return type
     * @param recipeName Recipe Name
     * @param pathValue  Path To Value
     * @return Type value if exists, null otherwise
     */
    public <T> T GetRecipeValue(Class<T> type, String recipeName, String pathValue)
    {
        if (m_file.GetConfiguration().contains("recipes.crafting." + recipeName + "." + pathValue))
            return GetValue(type, "recipes.crafting." + recipeName + "." + pathValue);
        else if (m_file.GetConfiguration().contains("recipes.furnace." + recipeName + "." + pathValue))
            return GetValue(type, "recipes.furnace." + recipeName + "." + pathValue);
        else if (m_file.GetConfiguration().contains("recipes.smoking." + recipeName + "." + pathValue))
            return GetValue(type, "recipes.smoking." + recipeName + "." + pathValue);
        else if (m_file.GetConfiguration().contains("recipes.blasting." + recipeName + "." + pathValue))
            return GetValue(type, "recipes.blasting." + recipeName + "." + pathValue);
        else if (m_file.GetConfiguration().contains("recipes.stonecutting." + recipeName + "." + pathValue))
            return GetValue(type, "recipes.stonecutting." + recipeName + "." + pathValue);
        else if (m_file.GetConfiguration().contains("recipes.campfire." + recipeName + "." + pathValue))
            return GetValue(type, "recipes.campfire." + recipeName + "." + pathValue);
        else
            return null;
    }

    /**
     * Get Recipe Value
     *
     * @param type       Return type
     * @param recipeName Recipe Name
     * @param pathValue  Path To Value
     * @return
     */
    public <T> float GetRecipeValueFloat(Class<T> type, String recipeName, String pathValue)
    {
        if (m_file.GetConfiguration().contains("recipes.crafting." + recipeName + "." + pathValue))
            return GetValueFloat(type, "recipes.crafting." + recipeName + "." + pathValue);
        else if (m_file.GetConfiguration().contains("recipes.furnace." + recipeName + "." + pathValue))
            return GetValueFloat(type, "recipes.furnace." + recipeName + "." + pathValue);
        else if (m_file.GetConfiguration().contains("recipes.smoking." + recipeName + "." + pathValue))
            return GetValueFloat(type, "recipes.smoking." + recipeName + "." + pathValue);
        else if (m_file.GetConfiguration().contains("recipes.blasting." + recipeName + "." + pathValue))
            return GetValueFloat(type, "recipes.blasting." + recipeName + "." + pathValue);
        else if (m_file.GetConfiguration().contains("recipes.stonecutting." + recipeName + "." + pathValue))
            return GetValueFloat(type, "recipes.stonecutting." + recipeName + "." + pathValue);
        else if (m_file.GetConfiguration().contains("recipes.campfire." + recipeName + "." + pathValue))
            return GetValueFloat(type, "recipes.campfire." + recipeName + "." + pathValue);
        return 0;
    }

    /**
     * Get Value
     *
     * @param type Return type
     * @param path Path
     * @param <T>  Return Type
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
