package fr.mrfantivideo.morecrafting.Recipes.RecipesLoaders;

import fr.mrfantivideo.morecrafting.Recipes.CustomRecipe;
import fr.mrfantivideo.morecrafting.Recipes.RecipesManager;
import fr.mrfantivideo.morecrafting.Utils.LogUtils;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;

public class FurnaceRecipesLoader extends AbstractRecipesLoader
{
    public FurnaceRecipesLoader()
    {
        super("recipes.furnace");
    }

    @Override
    protected void OnRecipeLoaded(Object recipe, int bookInventorySlot, String recipeName)
    {
        RecipesManager.GetInstance().AddRecipe(recipeName, new CustomRecipe((FurnaceRecipe) recipe, bookInventorySlot, recipeName));
        LogUtils.LogInfo("[Furnace Recipes Loader] Loading Recipe '" + recipeName + "'");
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
        float experience = GetConfig().GetFloat(GetFormattedPath(recipeName, "craft.result.experience"));
        int cookingtime = GetConfig().GetInt(GetFormattedPath(recipeName, "craft.result.cooking-time"));
        @SuppressWarnings("deprecation")
        FurnaceRecipe recipe = new FurnaceRecipe(NamespacedKey.randomKey(), stack, material, experience, cookingtime);
        return recipe;
    }
}
