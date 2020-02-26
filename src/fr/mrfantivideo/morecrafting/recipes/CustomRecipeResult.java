package fr.mrfantivideo.morecrafting.recipes;

import fr.unreal852.sunrealcore.configuration.data.object.ConfigValue;
import fr.unreal852.sunrealcore.configuration.data.object.IConfigObject;
import fr.unreal852.sunrealcore.utils.ItemStackUtils;
import fr.unreal852.sunrealcore.utils.JavaUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.*;

public class CustomRecipeResult implements IConfigObject
{
    @ConfigValue(Path = "name")
    private String                        m_name;
    @ConfigValue(Path = "lore")
    private String                        m_lore;
    @ConfigValue(Path = "material")
    private Material                      m_material;
    @ConfigValue(Path = "amount")
    private Integer                       m_amount;
    @ConfigValue(Path = "experience")
    private Float                         m_experience;
    @ConfigValue(Path = "cooking-time")
    private Integer                       m_cookingTime;
    @ConfigValue(Path = "enchantments", Type = CustomRecipeEnchantment.class)
    private List<CustomRecipeEnchantment> m_enchantments;
    @ConfigValue(Path = "potions", Type = CustomRecipePotion.class)
    private List<CustomRecipePotion>      m_potions;
    @ConfigValue(Path = "uuid")
    private UUID                          m_playerUUID;

    @Override
    public void onConfigLoaded(String name)
    {
        if (m_material == null)
            return;
        m_name = JavaUtils.ensureNotNull(m_name, "");
        m_lore = JavaUtils.ensureNotNull(m_name, "");
        m_amount = JavaUtils.ensureNotNull(m_amount, 1);
        m_experience = JavaUtils.ensureNotNull(m_experience, 0.0f);
        m_cookingTime = JavaUtils.ensureNotNull(m_cookingTime, 1);
        m_enchantments = JavaUtils.ensureNotNull(m_enchantments, new ArrayList<>());
        m_potions = JavaUtils.ensureNotNull(m_potions, new ArrayList<>());
        m_playerUUID = JavaUtils.ensureNotNull(m_playerUUID, UUID.fromString("069a79f4-44e9-4726-a5be-fca90e38aaf5"));
    }

    public String getName()
    {
        return m_name;
    }

    public String getLore()
    {
        return m_lore;
    }

    public Material getMaterial()
    {
        return m_material;
    }

    public int getAmount()
    {
        return m_amount;
    }

    public float getExperience()
    {
        return m_experience;
    }

    public int getCookTime()
    {
        return m_cookingTime;
    }

    public Collection<CustomRecipeEnchantment> getEnchantments()
    {
        return m_enchantments;
    }

    public Collection<CustomRecipePotion> getPotions()
    {
        return m_potions;
    }

    public ItemStack buildItemStack()
    {
        if (m_material == null)
            return new ItemStack(Material.BARRIER, 1);
        ItemStack itemStack = new ItemStack(m_material, m_amount);
        ItemMeta itemMeta = ItemStackUtils.getMeta(itemStack);
        if (!m_name.isEmpty())
            itemMeta.setDisplayName(m_name);
        if (!m_lore.isEmpty())
            itemMeta.setLore(Collections.singletonList(m_lore.replace("&", "§")));
        if (m_material.name().endsWith("POTION") && itemMeta instanceof PotionMeta)
        {
            PotionMeta potionMeta = (PotionMeta) itemMeta;
            for (CustomRecipePotion potion : m_potions)
            {
                if (potion.getEffectType() == null)
                    continue;
                potionMeta.addCustomEffect(potion.getPotion(), true);
            }
        }
        else if (m_material == Material.PLAYER_HEAD && itemMeta instanceof SkullMeta)
        {
            SkullMeta skullMeta = (SkullMeta) itemMeta;
            skullMeta.setOwningPlayer(Bukkit.getOfflinePlayer(m_playerUUID));
        }
        itemStack.setItemMeta(itemMeta);
        for (CustomRecipeEnchantment enchantment : m_enchantments)
        {
            if (enchantment.getEnchantment() == null)
                continue;
            itemStack.addEnchantment(enchantment.getEnchantment(), enchantment.getLevel());
        }
        return itemStack;
    }
}