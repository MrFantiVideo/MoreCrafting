package net.mrfantivideo.morecrafting.Recipes;

import org.bukkit.inventory.BlastingRecipe;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.SmokingRecipe;
import org.bukkit.inventory.StonecuttingRecipe;

public class CustomRecipe
{
    private ShapedRecipe m_recipe;
    private FurnaceRecipe m_furnaceRecipe;
    private SmokingRecipe m_smokingRecipe;
    private BlastingRecipe m_blastingRecipe;
    private StonecuttingRecipe m_stonecuttingRecipe;

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
     * Get the smoking recipe
     * @return Smoking Recipe
     */
    public SmokingRecipe GetSmokingRecipe()
    {
        return m_smokingRecipe;
    }
    
    /**
     * Get the blasting recipe
     * @return Blasting Recipe
     */
    public BlastingRecipe GetBlastingRecipe()
    {
        return m_blastingRecipe;
    }
    
    /**
     * Get the stonecutting recipe
     * @return Stonecutting Recipe
     */
    public BlastingRecipe GetStonecuttingRecipe()
    {
        return m_blastingRecipe;
    }

    /**
     * Get result
     * @return ItemStack
     */
    public ItemStack GetResult()
    {
        if(IsFurnaceRecipe())
            return m_furnaceRecipe.getResult();
        else if(IsSmokingRecipe())
            return m_smokingRecipe.getResult();
        else if(IsBlastingRecipe())
            return m_blastingRecipe.getResult();
        else if(IsStonecuttingRecipe())
            return m_stonecuttingRecipe.getResult();
        else
            return m_recipe.getResult();
    }
    
	/**
     * Is this a furnace recipe
     * @return true if this is a furnace recipe, false otherwise
     */
    public boolean IsSmokingRecipe()
    {
        return m_smokingRecipe != null;
    }

	/**
     * Is this a furnace recipe
     * @return true if this is a furnace recipe, false otherwise
     */
    public boolean IsBlastingRecipe()
    {
        return m_blastingRecipe != null;
    }
    
	/**
     * Is this a furnace recipe
     * @return true if this is a furnace recipe, false otherwise
     */
    public boolean IsStonecuttingRecipe()
    {
        return m_stonecuttingRecipe != null;
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
