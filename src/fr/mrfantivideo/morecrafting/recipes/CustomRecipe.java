package fr.mrfantivideo.morecrafting.recipes;

import fr.unreal852.sunrealcore.configuration.data.object.ConfigValue;
import fr.unreal852.sunrealcore.configuration.data.object.IConfigObject;
import fr.unreal852.sunrealcore.utils.JavaUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.*;

import java.util.HashMap;
import java.util.Map;

public class CustomRecipe implements IConfigObject
{
    private Recipe                m_recipe;
    private String                m_recipeName;
    @ConfigValue(Path = "enabled")
    private Boolean               m_enabled;
    @ConfigValue(Path = "recipe-type")
    private CustomRecipeType      m_recipeType;
    @ConfigValue(Path = "craft.slots", Type = Material.class)
    private Map<String, Material> m_slots;
    @ConfigValue(Path = "craft.result")
    private CustomRecipeResult    m_resultItem;

    @Override
    public void onConfigLoaded(String name)
    {
        m_enabled = JavaUtils.ensureNotNull(m_enabled, false);
        if (!m_enabled)
            return;
        m_recipeName = name;
        m_slots = JavaUtils.ensureNotNull(m_slots, new HashMap<>());
        if (m_recipeType == null || m_resultItem == null || m_slots.size() == 0)
        {
            RecipesManager.RECIPES_LOGGER.sendConsoleMessage(ChatColor.RED + "Couldn't load recipe '" + name + "'. Missing Recipe Type and/or Recipe Result and/or Crafting slots");
            return;
        }
        m_recipeName = name;
        switch (m_recipeType)
        {
            case CRAFTING_TABLE:
            {
                ShapedRecipe shapedRecipe = new ShapedRecipe(NamespacedKey.randomKey(), m_resultItem.buildItemStack());
                shapedRecipe.shape("123", "456", "789");
                for (String key : m_slots.keySet())
                    shapedRecipe.setIngredient(key.charAt(0), m_slots.get(key));
                m_recipe = shapedRecipe;
                break;
            }
            case FURNACE:
                m_recipe = new FurnaceRecipe(NamespacedKey.randomKey(), m_resultItem.buildItemStack(), m_slots.get("0"), m_resultItem.getExperience(), m_resultItem.getCookTime());
                break;
            case CAMPFIRE:
                m_recipe = new CampfireRecipe(NamespacedKey.randomKey(), m_resultItem.buildItemStack(), m_slots.get("0"), m_resultItem.getExperience(), m_resultItem.getCookTime());
                break;
            case BLASTING:
                m_recipe = new BlastingRecipe(NamespacedKey.randomKey(), m_resultItem.buildItemStack(), m_slots.get("0"), m_resultItem.getExperience(), m_resultItem.getCookTime());
                break;
            case SMOKING:
                m_recipe = new SmokingRecipe(NamespacedKey.randomKey(), m_resultItem.buildItemStack(), m_slots.get("0"), m_resultItem.getExperience(), m_resultItem.getCookTime());
                break;
            case STONE_CUTTING:
                m_recipe = new StonecuttingRecipe(NamespacedKey.randomKey(), m_resultItem.buildItemStack(), m_slots.get("0"));
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
     * Returns Recipe type
     *
     * @return Recipe Type
     */
    public CustomRecipeType getRecipeType()
    {
        return m_recipeType;
    }

    /**
     * Returns enable state of this recipe
     *
     * @return true if enabled, false otherwise
     */
    public boolean isEnabled()
    {
        return m_enabled;
    }

    /**
     * Returns slots
     *
     * @return Slots
     */
    public Map<String, Material> getSlots()
    {
        return m_slots;
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