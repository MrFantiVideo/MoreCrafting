package fr.mrfantivideo.morecrafting.recipes;

import fr.unreal852.sunrealcore.configuration.data.object.ConfigValue;
import fr.unreal852.sunrealcore.configuration.data.object.IConfigObject;
import fr.unreal852.sunrealcore.utils.JavaUtils;
import org.bukkit.enchantments.Enchantment;

public class CustomRecipeEnchantment implements IConfigObject
{
    @ConfigValue(Path = "enchant")
    private Enchantment m_enchant;
    @ConfigValue(Path = "level")
    private Integer     m_enchantLevel;

    @Override
    public void onConfigLoaded(String name)
    {
        m_enchantLevel = JavaUtils.ensureNotNull(m_enchantLevel, 1);
    }

    /**
     * Returns enchantment
     *
     * @return Enchantment
     */
    public Enchantment getEnchantment()
    {
        return m_enchant;
    }

    /**
     * Returns enchantment level
     *
     * @return Enchantment Level
     */
    public int getLevel()
    {
        return m_enchantLevel;
    }
}
