package net.mrfantivideo.morecrafting.Recipes.RecipesLoaders;

import net.mrfantivideo.morecrafting.Recipes.CustomRecipe;
import net.mrfantivideo.morecrafting.Recipes.RecipesManager;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.BlastingRecipe;
import org.bukkit.inventory.ItemStack;

public class BlastingRecipesLoader extends AbstractRecipesLoader
{
    public BlastingRecipesLoader()
    {
        super("recipes.blasting");
    }

    @Override
    protected void OnRecipeLoaded(Object recipe, int bookInventorySlot, String recipeName)
    {
        RecipesManager.GetInstance().AddRecipe(recipeName, new CustomRecipe((BlastingRecipe) recipe, bookInventorySlot, recipeName));
        String debug = GetConfig().GetDebug();
        if(debug == "true")
            System.out.println("Loading Blasting Recipe: " + recipeName);
        return;
    }

    @Override
    protected Object GetRecipe(String recipeName, ItemStack stack)
    {
        String burnMaterial = GetConfig().GetString(GetFormattedPath(recipeName, "craft.slot.1"));
        if(burnMaterial == null || burnMaterial.isEmpty())
            return null;
        Material material = Material.getMaterial(burnMaterial);
        if(material == null)
            return null;
        float experience = GetConfig().GetFloat(GetFormattedPath(recipeName,"craft.result.experience"));
        int cookingtime = GetConfig().GetInt(GetFormattedPath(recipeName,"craft.result.cooking-time"));
        @SuppressWarnings("deprecation")
		BlastingRecipe recipe = new BlastingRecipe(NamespacedKey.randomKey(), stack, material, experience, cookingtime);
        return recipe;
    }
}
