package net.mrfantivideo.morecrafting.Recipes;

import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class CustomRecipe
{
    private ShapedRecipe m_recipe;

    private FurnaceRecipe m_furnaceRecipe;

    private  String m_configItemName;

    private int m_bookInventorySlot;

    public CustomRecipe(ShapedRecipe recipe, int bookInventorySlot, String configItemName)
    {
        m_recipe = recipe;
        m_bookInventorySlot = bookInventorySlot;
        m_configItemName = configItemName;
    }

    public CustomRecipe(FurnaceRecipe recipe, int bookInventorySlot, String configItemName)
    {
        m_furnaceRecipe = recipe;
        m_bookInventorySlot = bookInventorySlot;
        m_configItemName = configItemName;
    }

    /**
     * Gets config item name
     * @return Name
     */
    public String GetConfigName()
    {
        return m_configItemName;
    }

    /**
     * Get the Shaped Recipe
     * @return Recipe
     */
    public ShapedRecipe GetRecipe()
    {
        return m_recipe;
    }

    /**
     * Get the furnace recipe
     * @return Furnace Recipe
     */
    public FurnaceRecipe GetFurnaceRecipe()
    {
        return m_furnaceRecipe;
    }

    /**
     * Get result
     * @return ItemStack
     */
    public ItemStack GetResult()
    {
        if(IsFurnaceRecipe())
            return m_furnaceRecipe.getResult();
        else
            return m_recipe.getResult();
    }

    /**
     * Is this a furnace recipe
     * @return true if this is a furnace recipe, false otherwise
     */
    public boolean IsFurnaceRecipe()
    {
        return m_furnaceRecipe != null;
    }

    /**
     * Gets the book inventory slot
     * @return Book Inventory Slot
     */
    public int GetBookInventorySlot()
    {
        return m_bookInventorySlot;
    }
}
