package net.mrfantivideo.morecrafting.Recipes;

import net.minecraft.server.v1_13_R2.ItemStack;
import net.mrfantivideo.morecrafting.Main;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ShapedRecipe;

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
     * @return ShapedRecipe or null
     */
    public static ShapedRecipe GetCustomRecipe(String id)
    {
        return GetInstance().GetRecipe(id);
    }

    /**
     * Get recipe from id
     * @param id
     * @return FurnaceRecipe or null
     */
    public static FurnaceRecipe GetCustomFurnaceRecipe(String id)
    {
        return GetInstance().GetFurnaceRecipe(id);
    }

    private Map<String, ShapedRecipe> m_recipes = new HashMap<>();
    private Map<String, FurnaceRecipe> m_furnacesRecipes = new HashMap<>();

    public RecipesManager()
    {
        s_instance = this;
    }

    /**
     * Get recipe from id
     * @param id
     * @return ShapedRecipe or null
     */
    public ShapedRecipe GetRecipe(String id)
    {
        if(m_recipes.containsKey(id))
            return m_recipes.get(id);
        return null;
    }

    /**
     * Get recipe from id
     * @param id
     * @return FurnaceRecipe or null
     */
    public FurnaceRecipe GetFurnaceRecipe(String id)
    {
        if(m_furnacesRecipes.containsKey(id))
            return m_furnacesRecipes.get(id);
        return null;
    }

    /**
     * Add a new recipe
     * @param itemID
     * @param recipe
     */
    public void AddRecipe(String itemID, ShapedRecipe recipe)
    {
        m_recipes.put(itemID, recipe);
        Main.GetInstance().getServer().addRecipe(recipe);
    }

    /**
     * Add a new furnace recipe
     * @param itemID
     * @param recipe
     */
    public void AddRecipe(String itemID, FurnaceRecipe recipe)
    {
        m_furnacesRecipes.put(itemID, recipe);
        Main.GetInstance().getServer().addRecipe(recipe);
    }

    /**
     * Clear recipes
     */
    public void Clear()
    {
        m_recipes.clear();
        m_furnacesRecipes.clear();
        Main.GetInstance().getServer().resetRecipes();
    }
}
