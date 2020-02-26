package fr.mrfantivideo.morecrafting.recipes;

import fr.mrfantivideo.morecrafting.Main;
import fr.mrfantivideo.morecrafting.config.MorecrafingConfig;
import fr.unreal852.sunrealcore.messages.PluginMessenger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class RecipesManager
{
    public static final  PluginMessenger           RECIPES_LOGGER = new PluginMessenger(Main.getInstance(), "[RECIPE] ");
    private static final Map<String, CustomRecipe> RECIPES        = new HashMap<>();

    public static void loadRecipes()
    {
        RECIPES.clear();
        MorecrafingConfig config = new MorecrafingConfig("/settings.yml", "fr.mrfantivideo.morecrafting.settings.yml");
        if (!config.isLoaded())
            return;
        List<CustomRecipe> recipeList = config.getList(CustomRecipe.class, "recipes");
        for (CustomRecipe recipe : recipeList)
        {
            if (recipe.getRecipeName() == null || recipe.getRecipeName().isEmpty() || recipe.getRecipe() == null)
                continue;
            RECIPES.put(recipe.getRecipeName(), recipe);
        }
    }
}
