package fr.mrfantivideo.morecrafting.Items;

import com.sun.istack.internal.NotNull;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class CustomStack extends ItemStack
{
    private String m_CraftPath;

    public CustomStack()
    {
        super();
    }

    public CustomStack(String craftPath, @NotNull Material type)
    {
        super(type, 1);
        m_CraftPath = craftPath;
    }

    public CustomStack(String craftPath, @NotNull Material type, int amount)
    {
        super(type, amount);
        m_CraftPath = craftPath;
    }

    /**
     * Get craft path ( ex: recipes.crafting.recipe-1 )
     * @return null or craftPath
     */
    public String GetCraftPath()
    {
        return m_CraftPath;
    }
}
