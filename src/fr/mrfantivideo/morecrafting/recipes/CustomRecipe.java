package fr.mrfantivideo.morecrafting.recipes;

import fr.unreal852.sunrealcore.configuration.data.object.ConfigValue;
import fr.unreal852.sunrealcore.configuration.data.object.IConfigObject;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;

import java.util.Map;

public class CustomRecipe implements IConfigObject
{
    private Recipe                m_recipe;
    private String                m_recipeName;
    @ConfigValue(Path = "enabled")
    private Boolean               m_enabled;
    @ConfigValue(Path = "recipe-type")
    private CustomRecipeType      m_recipeType;
    @ConfigValue(Path = "craft.slots")
    private Map<String, Material> m_slots;
    @ConfigValue(Path = "craft.result")
    private CustomRecipeResult    m_resultItem;

    @Override
    public void onConfigLoaded(String name)
    {
        if (!m_enabled)
            return;
        if (m_recipeType == null || m_resultItem == null)
        {
            RecipesManager.RECIPES_LOGGER.sendConsoleMessage(ChatColor.RED + "Couldn't load recipe '");
            return;
        }
        m_recipeName = name;
        switch (m_recipeType)
        {
            case CRAFTING_TABLE:
            {
                ShapedRecipe shapedRecipe = new ShapedRecipe(NamespacedKey.randomKey(), m_resultItem.buildItemStack());
                shapedRecipe.shape("123", "456", "789");
                for (int i = 1; i <= 9; i++)
                {
                    if (get)
                }
                break;
            }
            case FURNACE:
                break;
            case CAMPFIRE:
                break;
            case BLASTING:
                break;
            case SMOKING:
                break;
            case STONE_CUTTING:
                break;
        }
        Bukkit.addRecipe(m_recipe);
    }

    /**
     * Returns Recipe name
     *
     * @return Recipe Name
     */
    public String getRecipeName()
    {
        return m_recipeName;
    }

    /**
     * Returns {@link Recipe}
     *
     * @return Recipe
     */
    public Recipe getRecipe()
    {
        return m_recipe;
    }

    /**
     * Returns {@link CustomRecipeResult}
     *
     * @return Custom Recipe Result
     */
    public CustomRecipeResult getCustomResult()
    {
        return m_resultItem;
    }

    /**
     * Returns the cloned {@link ItemStack}
     *
     * @return Result ItemStack
     */
    public ItemStack getClonedResult()
    {
        return m_recipe.getResult().clone();
    }
}
