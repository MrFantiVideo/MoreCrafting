package fr.mrfantivideo.morecrafting.Recipesold.RecipesLoaders;

import fr.mrfantivideo.morecrafting.Recipesold.CustomRecipeOld;
import fr.mrfantivideo.morecrafting.Recipesold.RecipesManagerOld;
import fr.mrfantivideo.morecrafting.Utils.LogUtils;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.StonecuttingRecipe;

public class StonecuttingRecipesLoader extends AbstractRecipesLoader
{
    public StonecuttingRecipesLoader()
    {
        super("recipes.stonecutting");
    }

    @Override
    protected void OnRecipeLoaded(Object recipe, int bookInventorySlot, String recipeName)
    {
        RecipesManagerOld.GetInstance().AddRecipe(recipeName, new CustomRecipeOld((StonecuttingRecipe) recipe, bookInventorySlot, recipeName));
        LogUtils.LogInfo("[Stonecutting Recipes Loader] Loading Recipe '" + recipeName + "'");
    }

    @Override
    protected Object GetRecipe(String recipeName, ItemStack stack)
    {
        String burnMaterial = GetConfig().GetString(GetFormattedPath(recipeName, "craft.slot.1"));
        if (burnMaterial == null || burnMaterial.isEmpty())
            return null;
        Material material = Material.getMaterial(burnMaterial);
        if (material == null)
            return null;
        @SuppressWarnings("deprecation")
        StonecuttingRecipe recipe = new StonecuttingRecipe(NamespacedKey.randomKey(), stack, material);
        return recipe;
    }
}
