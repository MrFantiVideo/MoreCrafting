package net.mrfantivideo.morecrafting.Recipes.RecipesLoaders;

import net.mrfantivideo.morecrafting.Recipes.CustomRecipe;
import net.mrfantivideo.morecrafting.Recipes.RecipesManager;
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
        RecipesManager.GetInstance().AddRecipe(recipeName, new CustomRecipe((StonecuttingRecipe) recipe, bookInventorySlot, recipeName));
        System.out.println("Loading Stonecutting Recipe: " + recipeName);
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
        @SuppressWarnings("deprecation")
		StonecuttingRecipe recipe = new StonecuttingRecipe(NamespacedKey.randomKey(), stack, material);
        return recipe;
    }
}
