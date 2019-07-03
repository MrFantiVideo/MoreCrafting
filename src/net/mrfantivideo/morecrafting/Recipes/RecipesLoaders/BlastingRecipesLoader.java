package net.mrfantivideo.morecrafting.Recipes.RecipesLoaders;

import net.mrfantivideo.morecrafting.Items.CustomStack;
import net.mrfantivideo.morecrafting.Recipes.CustomRecipe;
import net.mrfantivideo.morecrafting.Recipes.RecipesManager;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.BlastingRecipe;

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
        System.out.println("Loading Blasting Recipe: " + recipeName);
    }

    @Override
    protected Object GetRecipe(String recipeName, CustomStack stack)
    {
        String burnMaterial = GetConfig().GetString(GetFormattedPath(recipeName, "craft.slot.1"));
        if(burnMaterial == null || burnMaterial.isEmpty())
            return null;
        Material material = Material.getMaterial(burnMaterial);
        if(material == null)
            return null;
        float experience = GetConfig().GetFloat(GetFormattedPath(recipeName,"craft.result.experience"));
        int cookingtime = GetConfig().GetInt(GetFormattedPath(recipeName,"craft.result.cooking-time"));
        BlastingRecipe recipe = new BlastingRecipe(NamespacedKey.randomKey(), stack, material, experience, cookingtime);
        return recipe;
    }
}
