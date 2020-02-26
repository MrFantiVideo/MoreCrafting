package fr.mrfantivideo.morecrafting.recipes;

import fr.mrfantivideo.morecrafting.Main;
import fr.mrfantivideo.morecrafting.config.MorecrafingConfig;
import fr.mrfantivideo.morecrafting.items.RecipeSpecialItem;
import fr.unreal852.sunrealcore.messages.PluginMessenger;
import org.bukkit.ChatColor;

import java.util.*;

public final class RecipesManager
{
    public static final  PluginMessenger           RECIPES_LOGGER = new PluginMessenger(Main.getInstance(), "§a[§eRECIPE§a]§f ");
    private static final Map<String, CustomRecipe> RECIPES        = new HashMap<>();

    public static void loadRecipes()
    {
        RECIPES.clear();
        MorecrafingConfig config = new MorecrafingConfig("/settings.yml", "settings.yml");
        if (!config.isLoaded())
            return;
        List<CustomRecipe> recipeList = config.getList(CustomRecipe.class, "recipes");
        for (CustomRecipe recipe : recipeList)
        {
            if (!recipe.isEnabled() || recipe.getRecipeName() == null || recipe.getRecipeName().isEmpty() || recipe.getRecipe() == null)
                continue;
            RECIPES.put(recipe.getRecipeName(), recipe);
            new RecipeSpecialItem(recipe.getClonedResult(), recipe);
            RECIPES_LOGGER.sendConsoleMessage("§aLoaded Recipe '" + recipe.getRecipeName() + "'.");
        }
    }

    public static Collection<CustomRecipe> getRecipes()
    {
        return RECIPES.values();
    }
}