package fr.mrfantivideo.morecrafting.config;

import fr.mrfantivideo.morecrafting.Main;
import fr.mrfantivideo.morecrafting.items.RecipeSpecialItem;
import fr.mrfantivideo.morecrafting.recipes.CustomRecipe;
import fr.unreal852.sunrealcore.configuration.data.object.ConfigValue;
import fr.unreal852.sunrealcore.configuration.data.object.IConfigObject;
import fr.unreal852.sunrealcore.utils.JavaUtils;
import org.bukkit.ChatColor;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ConfigSettings
{
    private String                    m_language;
    private Boolean                   m_debug;
    private Map<String, CustomRecipe> m_recipes;

    protected void loadConfig(MorecrafingConfig config)
    {
        m_language = JavaUtils.ensureNotNull(config.get(String.class, "language"), "en_US");
        m_debug = JavaUtils.ensureNotNull(config.get(Boolean.class, "debug"), false);
        m_recipes = JavaUtils.ensureNotNull(config.getMap(CustomRecipe.class, "recipes"), new HashMap<>());
        for (CustomRecipe recipe : m_recipes.values())
        {
            if (!recipe.isEnabled() || recipe.getRecipeName() == null || recipe.getRecipeName().isEmpty() || recipe.getRecipe() == null)
                continue;
            new RecipeSpecialItem(recipe.getClonedResult(), recipe);
            if (m_debug)
                Main.getLog().sendConsoleMessage(ChatColor.GREEN + "Loaded recipe " + ChatColor.GOLD + "'" + recipe.getRecipeName() + "'" + ChatColor.GREEN + ".");
        }
        if(m_debug)
            Main.getLog().sendConsoleMessage(ChatColor.GREEN + "Loaded settings.yml");
    }

    public String getLanguage()
    {
        return m_language;
    }

    public boolean isDebugEnabled()
    {
        return m_debug;
    }

    public Collection<CustomRecipe> getRecipes()
    {
        return m_recipes.values();
    }

    public CustomRecipe getRecipe(String recipeName)
    {
        return m_recipes.getOrDefault(recipeName, null);
    }
}
