package net.mrfantivideo.morecrafting.Recipes.RecipesLoaders;

import net.mrfantivideo.morecrafting.Recipes.CustomRecipe;
import net.mrfantivideo.morecrafting.Recipes.RecipesManager;
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
        RecipesManager.GetInstance().AddRecipe(recipeName, new CustomRecipe((ShapedRecipe)recipe, bookInventorySlot, recipeName));
        String debug = GetConfig().GetDebug();
        if(debug == "true")
            System.out.println("Loading Crafting Recipe: " + recipeName);
        return;
    }
}
