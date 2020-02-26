package fr.mrfantivideo.morecrafting.Recipesold.RecipesLoaders;

import fr.mrfantivideo.morecrafting.Recipesold.CustomRecipeOld;
import fr.mrfantivideo.morecrafting.Recipesold.RecipesManagerOld;
import fr.mrfantivideo.morecrafting.Utils.LogUtils;
import org.bukkit.inventory.ShapedRecipe;

public class ShapedRecipesLoader extends AbstractRecipesLoader
{
    public ShapedRecipesLoader()
    {
        super("recipes.crafting");
    }

    @Override
    protected void OnRecipeLoaded(Object recipe, int bookInventorySlot, String recipeName)
    {
        RecipesManagerOld.GetInstance().AddRecipe(recipeName, new CustomRecipeOld((ShapedRecipe) recipe, bookInventorySlot, recipeName));
        LogUtils.LogInfo("[Crafting Recipes Loader] Loading Recipe '" + recipeName + "'");
    }
}
