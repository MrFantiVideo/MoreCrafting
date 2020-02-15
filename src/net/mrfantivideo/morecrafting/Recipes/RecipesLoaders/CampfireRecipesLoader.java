package net.mrfantivideo.morecrafting.Recipes.RecipesLoaders;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.CampfireRecipe;
import org.bukkit.inventory.ItemStack;

import net.mrfantivideo.morecrafting.Recipes.CustomRecipe;
import net.mrfantivideo.morecrafting.Recipes.RecipesManager;
import net.mrfantivideo.morecrafting.Utils.LogUtils;

public class CampfireRecipesLoader extends AbstractRecipesLoader
{
    public CampfireRecipesLoader()
    {
        super("recipes.campfire");
    }

    @Override
    protected void OnRecipeLoaded(Object recipe, int bookInventorySlot, String recipeName)
    {
        RecipesManager.GetInstance().AddRecipe(recipeName, new CustomRecipe((CampfireRecipe) recipe, bookInventorySlot, recipeName));
        LogUtils.LogInfo("[Campfire Recipes Loader] Loading Recipe '" + recipeName + "'");
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
        //float experience = GetConfig().GetFloat(GetFormattedPath(recipeName,"craft.result.experience"));
        int cookingtime = GetConfig().GetInt(GetFormattedPath(recipeName, "craft.result.cooking-time"));
        @SuppressWarnings("deprecation")
        CampfireRecipe recipe = new CampfireRecipe(NamespacedKey.randomKey(), stack, material, 1, cookingtime);
        return recipe;
    }
}
