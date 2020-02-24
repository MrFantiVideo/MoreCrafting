package fr.mrfantivideo.morecrafting.Recipes;

import fr.mrfantivideo.morecrafting.Main;
import fr.mrfantivideo.morecrafting.Recipes.RecipesLoaders.*;
import org.bukkit.Material;
import org.bukkit.inventory.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class RecipesManager
{
    private static RecipesManager s_instance;
    private Map<String, CustomRecipe> m_recipes = new HashMap<>();

    public RecipesManager()
    {
        if (s_instance != null)
            return;
        s_instance = this;
        LoadRecipes();
    }

    /**
     * Get Recipes Manager Instance
     *
     * @return Instance
     */
    public static RecipesManager GetInstance()
    {
        return s_instance;
    }

    /**
     * Get recipe from id
     *
     * @param recipeName Name
     *
     * @return CustomRecipe or null
     */
    public static CustomRecipe GetCustomRecipe(String recipeName)
    {
        return GetInstance().GetRecipe(recipeName);
    }

    /**
     * Get shaped recipe from id
     *
     * @param recipeName Name
     *
     * @return ShapedRecipe or null
     */
    public static ShapedRecipe GetCustomShapedRecipe(String recipeName)
    {
        return GetInstance().GetShapedRecipe(recipeName);
    }

    /**
     * Get recipe from id
     *
     * @param recipeName Name
     *
     * @return FurnaceRecipe or null
     */
    public FurnaceRecipe GetCustomFurnaceRecipe(String recipeName)
    {
        return GetInstance().GetFurnaceRecipe(recipeName);
    }

    /**
     * Get recipe from name
     *
     * @param recipeName Name
     *
     * @return CustomRecipe or null
     */
    public CustomRecipe GetRecipe(String recipeName)
    {
        if (m_recipes.containsKey(recipeName))
            return m_recipes.get(recipeName);
        return null;
    }

    /**
     * Get shaped recipe from Name
     *
     * @param recipeName Name
     *
     * @return ShapedRecipe or null
     */
    public ShapedRecipe GetShapedRecipe(String recipeName)
    {
        CustomRecipe recipe = GetRecipe(recipeName);
        if (recipe != null && !recipe.IsFurnaceRecipe() && !recipe.IsSmokingRecipe())
            return recipe.GetRecipe();
        return null;
    }

    /**
     * Get recipe from Name
     *
     * @param recipeName Name
     *
     * @return FurnaceRecipe or null
     */
    public FurnaceRecipe GetFurnaceRecipe(String recipeName)
    {
        CustomRecipe recipe = GetRecipe(recipeName);
        if (recipe != null && recipe.IsFurnaceRecipe())
            return recipe.GetFurnaceRecipe();
        return null;
    }

    /**
     * Get recipe from Name
     *
     * @param recipeName Name
     *
     * @return SmokingRecipe or null
     */
    public SmokingRecipe GetSmokingRecipe(String recipeName)
    {
        CustomRecipe recipe = GetRecipe(recipeName);
        if (recipe != null && recipe.IsSmokingRecipe())
            return recipe.GetSmokingRecipe();
        return null;
    }

    /**
     * Get recipe from Name
     *
     * @param recipeName Name
     *
     * @return BlastingRecipe or null
     */
    public BlastingRecipe GetBlastingRecipe(String recipeName)
    {
        CustomRecipe recipe = GetRecipe(recipeName);
        if (recipe != null && recipe.IsBlastingRecipe())
            return recipe.GetBlastingRecipe();
        return null;
    }

    /**
     * Get recipe from Name
     *
     * @param recipeName Name
     *
     * @return StonecuttingRecipe or null
     */
    public StonecuttingRecipe GetStonecuttingRecipe(String recipeName)
    {
        CustomRecipe recipe = GetRecipe(recipeName);
        if (recipe != null && recipe.IsStonecuttingRecipe())
            return recipe.GetStonecuttingRecipe();
        return null;
    }

    /**
     * Get recipe from Name
     *
     * @param recipeName Name
     *
     * @return CampfireRecipe or null
     */
    public CampfireRecipe GetCampfireRecipe(String recipeName)
    {
        CustomRecipe recipe = GetRecipe(recipeName);
        if (recipe != null && recipe.IsCampfireRecipe())
            return recipe.GetCampfireRecipe();
        return null;
    }

    /**
     * Get all recipes
     *
     * @return Recipes
     */
    public Collection<CustomRecipe> GetRecipes()
    {
        return m_recipes.values();
    }

    /**
     * Add a new recipe
     *
     * @param recipeName Recipe Name
     * @param recipe     Recipe
     */
    public void AddRecipe(String recipeName, CustomRecipe recipe)
    {
        m_recipes.put(recipeName, recipe);
        if (recipe.IsFurnaceRecipe())
            Main.getInstance().getServer().addRecipe(recipe.GetFurnaceRecipe());
        else if (recipe.IsSmokingRecipe())
            Main.getInstance().getServer().addRecipe(recipe.GetSmokingRecipe());
        else if (recipe.IsBlastingRecipe())
            Main.getInstance().getServer().addRecipe(recipe.GetBlastingRecipe());
        else if (recipe.IsStonecuttingRecipe())
            Main.getInstance().getServer().addRecipe(recipe.GetStonecuttingRecipe());
        else if (recipe.IsCampfireRecipe())
            Main.getInstance().getServer().addRecipe(recipe.GetCampfireRecipe());
        else
            Main.getInstance().getServer().addRecipe(recipe.GetRecipe());
    }

    /**
     * Gets a recipe by it's type ( material )
     *
     * @param material
     *
     * @return CustomRecipe or null
     */
    public CustomRecipe GetRecipeByMaterial(Material material)
    {
        for (CustomRecipe recipe : m_recipes.values())
        {
            if (recipe.GetResult().getType() == material)
                return recipe;
        }
        return null;
    }

    /**
     * Find recipe by its settings name
     *
     * @param recipeName Recipe settings name
     *
     * @return CustomRecipe if the recipe exists, null otherwise
     */
    public CustomRecipe GetRecipeByName(String recipeName)
    {
        if (m_recipes.containsKey(recipeName))
            return m_recipes.get(recipeName);
        return null;
    }

    /**
     * Clear recipes
     */
    public void Clear()
    {
        m_recipes.clear();
        Main.getInstance().getServer().resetRecipes();
    }

    /**
     * Load Recipes
     */
    public void LoadRecipes()
    {
        new ShapedRecipesLoader().LoadRecipe();
        new FurnaceRecipesLoader().LoadRecipe();
        new SmokingRecipesLoader().LoadRecipe();
        new BlastingRecipesLoader().LoadRecipe();
        new StonecuttingRecipesLoader().LoadRecipe();
        new CampfireRecipesLoader().LoadRecipe();
        RecipesBookLoader.LoadRecipesBook();
    }
}
