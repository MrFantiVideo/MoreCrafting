package fr.mrfantivideo.morecrafting.Recipesold.RecipesLoaders;

import fr.mrfantivideo.morecrafting.Recipesold.CustomRecipeOld;
import fr.mrfantivideo.morecrafting.Recipesold.RecipesManagerOld;
import fr.mrfantivideo.morecrafting.Utils.LogUtils;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.SmokingRecipe;

public class SmokingRecipesLoader extends AbstractRecipesLoader
{
    public SmokingRecipesLoader()
    {
        super("recipes.smoking");
    }

    @Override
    protected void OnRecipeLoaded(Object recipe, int bookInventorySlot, String recipeName)
    {
        RecipesManagerOld.GetInstance().AddRecipe(recipeName, new CustomRecipeOld((SmokingRecipe) recipe, bookInventorySlot, recipeName));
        LogUtils.LogInfo("[Smoking Recipes Loader] Loading Recipe '" + recipeName + "'");
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
        SmokingRecipe recipe = new SmokingRecipe(NamespacedKey.randomKey(), stack, material, experience, cookingtime);
        return recipe;
    }
}
