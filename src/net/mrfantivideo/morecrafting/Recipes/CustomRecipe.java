package net.mrfantivideo.morecrafting.Recipes;

import org.bukkit.inventory.*;

public class CustomRecipe
{
    private ShapedRecipe       m_recipe;
    private FurnaceRecipe      m_furnaceRecipe;
    private SmokingRecipe      m_smokingRecipe;
    private BlastingRecipe     m_blastingRecipe;
    private StonecuttingRecipe m_stonecuttingRecipe;
    private CampfireRecipe     m_campfireRecipe;
    private String m_configItemName;
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

    public CustomRecipe(SmokingRecipe recipe, int bookInventorySlot, String configItemName)
    {
        m_smokingRecipe = recipe;
        m_bookInventorySlot = bookInventorySlot;
        m_configItemName = configItemName;
    }

    public CustomRecipe(BlastingRecipe recipe, int bookInventorySlot, String configItemName)
    {
        m_blastingRecipe = recipe;
        m_bookInventorySlot = bookInventorySlot;
        m_configItemName = configItemName;
    }

    public CustomRecipe(StonecuttingRecipe recipe, int bookInventorySlot, String configItemName)
    {
        m_stonecuttingRecipe = recipe;
        m_bookInventorySlot = bookInventorySlot;
        m_configItemName = configItemName;
    }

    public CustomRecipe(CampfireRecipe recipe, int bookInventorySlot, String configItemName)
    {
        m_campfireRecipe = recipe;
        m_bookInventorySlot = bookInventorySlot;
        m_configItemName = configItemName;
    }

    public static void Get(Object recipe, int bookInventorySlot, String configItemName)
    {

    }

    /**
     * Gets config item name
     *
     * @return Name
     */
    public String GetConfigName()
    {
        return m_configItemName;
    }

    /**
     * Get the Shaped Recipe
     *
     * @return Recipe
     */
    public ShapedRecipe GetRecipe()
    {
        return m_recipe;
    }

    /**
     * Get the furnace recipe
     *
     * @return Furnace Recipe
     */
    public FurnaceRecipe GetFurnaceRecipe()
    {
        return m_furnaceRecipe;
    }

    /**
     * Get the smoking recipe
     *
     * @return Smoking Recipe
     */
    public SmokingRecipe GetSmokingRecipe()
    {
        return m_smokingRecipe;
    }

    /**
     * Get the blasting recipe
     *
     * @return Blasting Recipe
     */
    public BlastingRecipe GetBlastingRecipe()
    {
        return m_blastingRecipe;
    }

    /**
     * Get the stonecutting recipe
     *
     * @return Stonecutting Recipe
     */
    public StonecuttingRecipe GetStonecuttingRecipe()
    {
        return m_stonecuttingRecipe;
    }

    /**
     * Get the campfire recipe
     *
     * @return Campfire Recipe
     */
    public CampfireRecipe GetCampfireRecipe()
    {
        return m_campfireRecipe;
    }

    /**
     * Get result
     *
     * @return ItemStack
     */
    public ItemStack GetResult()
    {
        if (IsFurnaceRecipe())
            return m_furnaceRecipe.getResult();
        else if (IsSmokingRecipe())
            return m_smokingRecipe.getResult();
        else if (IsBlastingRecipe())
            return m_blastingRecipe.getResult();
        else if (IsStonecuttingRecipe())
            return m_stonecuttingRecipe.getResult();
        else if (IsCampfireRecipe())
            return m_campfireRecipe.getResult();
        else
            return m_recipe.getResult();
    }

    /**
     * Is this a smoking recipe
     *
     * @return true if this is a smoking recipe, false otherwise
     */
    public boolean IsSmokingRecipe()
    {
        return m_smokingRecipe != null;
    }

    /**
     * Is this a blasting recipe
     *
     * @return true if this is a blasting recipe, false otherwise
     */
    public boolean IsBlastingRecipe()
    {
        return m_blastingRecipe != null;
    }

    /**
     * Is this a stonecutting recipe
     *
     * @return true if this is a stonecutting recipe, false otherwise
     */
    public boolean IsStonecuttingRecipe()
    {
        return m_stonecuttingRecipe != null;
    }

    /**
     * Is this a campfire recipe
     *
     * @return true if this is a campfire recipe, false otherwise
     */
    public boolean IsCampfireRecipe()
    {
        return m_campfireRecipe != null;
    }

    /**
     * Is this a furnace recipe
     *
     * @return true if this is a furnace recipe, false otherwise
     */
    public boolean IsFurnaceRecipe()
    {
        return m_furnaceRecipe != null;
    }

    /**
     * Gets the book inventory slot
     *
     * @return Book Inventory Slot
     */
    public int GetBookInventorySlot()
    {
        return m_bookInventorySlot;
    }


}
