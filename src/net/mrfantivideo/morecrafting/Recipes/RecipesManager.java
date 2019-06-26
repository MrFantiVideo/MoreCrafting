package net.mrfantivideo.morecrafting.Recipes;

import net.mrfantivideo.morecrafting.Main;
import net.mrfantivideo.morecrafting.Recipes.RecipesLoaders.BlastingRecipesLoader;
import net.mrfantivideo.morecrafting.Recipes.RecipesLoaders.FurnaceRecipesLoader;
import net.mrfantivideo.morecrafting.Recipes.RecipesLoaders.RecipesBookLoader;
import net.mrfantivideo.morecrafting.Recipes.RecipesLoaders.ShapedRecipesLoader;
import net.mrfantivideo.morecrafting.Recipes.RecipesLoaders.SmokingRecipesLoader;

import org.bukkit.Material;
import org.bukkit.inventory.BlastingRecipe;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.SmokingRecipe;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class RecipesManager
{
    private static RecipesManager s_instance;

    /**
     * Get Recipes Manager Instance
     * @return Instance
     */
    public static RecipesManager GetInstance()
    {
        return s_instance;
    }

    /**
     * Get recipe from id
     * @param recipeName Name
     * @return CustomRecipe or null
     */
    public static CustomRecipe GetCustomRecipe(String recipeName)
    {
        return GetInstance().GetRecipe(recipeName);
    }

    /**
     * Get shaped recipe from id
     * @param recipeName Name
     * @return ShapedRecipe or null
     */
    public static ShapedRecipe GetCustomShapedRecipe(String recipeName)
    {
        return GetInstance().GetShapedRecipe(recipeName);
    }

    /**
     * Get recipe from id
     * @param recipeName Name
     * @return FurnaceRecipe or null
     */
    public FurnaceRecipe GetCustomFurnaceRecipe(String recipeName)
    {
        return GetInstance().GetFurnaceRecipe(recipeName);
    }

    private Map<String, CustomRecipe> m_recipes = new HashMap<>();

    public RecipesManager()
    {
        if(s_instance != null)
            return;
        s_instance = this;
        LoadRecipes();
    }

    /**
     * Get recipe from name
     * @param recipeName Name
     * @return CustomRecipe or null
     */
    public CustomRecipe GetRecipe(String recipeName)
    {
        if(m_recipes.containsKey(recipeName))
            return m_recipes.get(recipeName);
        return null;
    }

    /**
     * Get shaped recipe from Name
     * @param recipeName Name
     * @return ShapedRecipe or null
     */
    public ShapedRecipe GetShapedRecipe(String recipeName)
    {
        CustomRecipe recipe = GetRecipe(recipeName);
        if(recipe != null && !recipe.IsFurnaceRecipe() && !recipe.IsSmokingRecipe())
            return recipe.GetRecipe();
        return null;
    }

    /**
     * Get recipe from Name
     * @param recipeName Name
     * @return FurnaceRecipe or null
     */
    public FurnaceRecipe GetFurnaceRecipe(String recipeName)
    {
        CustomRecipe recipe = GetRecipe(recipeName);
        if(recipe != null && recipe.IsFurnaceRecipe())
            return recipe.GetFurnaceRecipe();
        return null;
    }
    
    /**
     * Get recipe from Name
     * @param recipeName Name
     * @return FurnaceRecipe or null
     */
    public SmokingRecipe GetSmokingRecipe(String recipeName)
    {
        CustomRecipe recipe = GetRecipe(recipeName);
        if(recipe != null && recipe.IsSmokingRecipe())
            return recipe.GetSmokingRecipe();
        return null;
    }
    
    /**
     * Get recipe from Name
     * @param recipeName Name
     * @return FurnaceRecipe or null
     */
    public BlastingRecipe GetBlastingRecipe(String recipeName)
    {
        CustomRecipe recipe = GetRecipe(recipeName);
        if(recipe != null && recipe.IsBlastingRecipe())
            return recipe.GetBlastingRecipe();
        return null;
    }

    /**
     * Get all recipes
     * @return Recipes
     */
    public Collection<CustomRecipe> GetRecipes()
    {
        return m_recipes.values();
    }

    /**
     * Add a new recipe
     * @param recipeName Recipe Name
     * @param recipe Recipe
     */
    public void AddRecipe(String recipeName, CustomRecipe recipe)
    {
        m_recipes.put(recipeName, recipe);
        if(recipe.IsFurnaceRecipe())
            Main.GetInstance().getServer().addRecipe(recipe.GetFurnaceRecipe());
        else if(recipe.IsSmokingRecipe())
            Main.GetInstance().getServer().addRecipe(recipe.GetSmokingRecipe());
        else if(recipe.IsBlastingRecipe())
            Main.GetInstance().getServer().addRecipe(recipe.GetBlastingRecipe());
        else
            Main.GetInstance().getServer().addRecipe(recipe.GetRecipe());
    }

    /**
     * Gets a recipe by it's type ( material )
     * @param material
     * @return CustomRecipe or null
     */
    public CustomRecipe GetRecipeByMaterial(Material material)
    {
        for(CustomRecipe recipe : m_recipes.values())
        {
            if(recipe.GetResult().getType() == material)
                return recipe;
        }
        return null;
    }

    /**
     * Clear recipes
     */
    public void Clear()
    {
        m_recipes.clear();
        Main.GetInstance().getServer().resetRecipes();
    }

    /**
     * Load Recipes
     */
    public void LoadRecipes()
    {
        Clear();
        ShapedRecipesLoader.LoadShapedRecipes();
        FurnaceRecipesLoader.LoadFurnacesRecipes();
        SmokingRecipesLoader.LoadSmokingRecipes();
        BlastingRecipesLoader.LoadBlastingRecipes();
        RecipesBookLoader.LoadRecipesBook();
    }
}
