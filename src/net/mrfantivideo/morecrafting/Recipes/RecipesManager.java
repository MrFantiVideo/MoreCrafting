package net.mrfantivideo.morecrafting.Recipes;

import net.mrfantivideo.morecrafting.Main;
import org.bukkit.Material;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ShapedRecipe;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class RecipesManager
{
    private static RecipesManager s_instance;

    public static RecipesManager GetInstance()
    {
        return s_instance;
    }

    /**
     * Get recipe from id
     * @param id
     * @return CustomRecipe or null
     */
    public static CustomRecipe GetCustomRecipe(String id)
    {
        return GetInstance().GetRecipe(id);
    }

    /**
     * Get shaped recipe from id
     * @param id
     * @return ShapedRecipe or null
     */
    public static ShapedRecipe GetCustomShapedRecipe(String id)
    {
        return GetInstance().GetShapedRecipe(id);
    }

    /**
     * Get recipe from id
     * @param id
     * @return FurnaceRecipe or null
     */
    public FurnaceRecipe GetCustomFurnaceRecipe(String id)
    {
        return GetInstance().GetFurnaceRecipe(id);
    }

    private  Map<String, CustomRecipe> m_recipes = new HashMap<>();

    public RecipesManager()
    {
        s_instance = this;
    }

    /**
     * Get recipe from id
     * @param id
     * @return CustomRecipe or null
     */
    public CustomRecipe GetRecipe(String id)
    {
        if(m_recipes.containsKey(id))
            return m_recipes.get(id);
        return null;
    }

    /**
     * Get shaped recipe from id
     * @param id
     * @return ShapedRecipe or null
     */
    public ShapedRecipe GetShapedRecipe(String id)
    {
        CustomRecipe recipe = GetRecipe(id);
        if(recipe != null && !recipe.IsFurnaceRecipe())
            return recipe.GetRecipe();
        return null;
    }

    /**
     * Get recipe from id
     * @param id
     * @return FurnaceRecipe or null
     */
    public FurnaceRecipe GetFurnaceRecipe(String id)
    {
        CustomRecipe recipe = GetRecipe(id);
        if(recipe != null && recipe.IsFurnaceRecipe())
            return recipe.GetFurnaceRecipe();
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
     * @param itemID
     * @param recipe
     */
    public void AddRecipe(String itemID, CustomRecipe recipe)
    {
        m_recipes.put(itemID, recipe);
        if(recipe.IsFurnaceRecipe())
            Main.GetInstance().getServer().addRecipe(recipe.GetFurnaceRecipe());
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
}
