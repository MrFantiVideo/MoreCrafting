package net.mrfantivideo.morecrafting.Recipes.RecipesLoaders;

import com.sun.istack.internal.NotNull;
import net.mrfantivideo.morecrafting.Configuration.Configs.ConfigSettings;
import net.mrfantivideo.morecrafting.Main;
import net.mrfantivideo.morecrafting.UnrealCoreImports.Flag;
import net.mrfantivideo.morecrafting.UnrealCoreImports.ItemStackUtils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;

import java.util.Arrays;
import java.util.Set;
import java.util.UUID;

public abstract class AbstractRecipesLoader
{
    private String m_BasePath;

    public AbstractRecipesLoader(@NotNull String basePath)
    {
        m_BasePath = basePath;
        if (!m_BasePath.endsWith("."))
            m_BasePath += ".";
    }

    protected abstract void OnRecipeLoaded(Object recipe, int bookInventorySlot, String recipeName);

    /**
     * Get base item path
     *
     * @return base path
     */
    public String GetBasePath()
    {
        return m_BasePath;
    }

    /**
     * Get config settings
     *
     * @return Config Settings
     */
    public ConfigSettings GetConfig()
    {
        return Main.getInstance().getConfigSettings();
    }

    /**
     * Load Recipes
     */
    public void LoadRecipe()
    {
        for (String recipe : GetConfig().GetRecipes(GetBasePath()))
        {
            if (!GetConfig().GetBool(GetFormattedPath(recipe, "enabled")))
                continue;
            ItemStack resultItem = GetRecipeResult(recipe);
            if (resultItem == null)
                continue;
            Object shapedRecipe = GetRecipe(recipe, resultItem);
            if (shapedRecipe == null)
                continue;
            int bookInventorySlot = GetConfig().GetInt(GetFormattedPath(recipe, "craft.result.id-book"));
            OnRecipeLoaded(shapedRecipe, bookInventorySlot, recipe);
        }
    }

    protected ItemStack GetPlayerHead(UUID playerUUID)
    {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta meta = (SkullMeta) ItemStackUtils.getMeta(head);
        meta.setOwningPlayer(Bukkit.getOfflinePlayer(playerUUID));
        head.setItemMeta(meta);
        return head;
    }

    /**
     * Get Recipe
     *
     * @param recipeName Recipe Name
     * @param stack      Recipe Result
     * @return Recipe
     */
    protected Object GetRecipe(String recipeName, ItemStack stack)
    {
        @SuppressWarnings("deprecation")
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.randomKey(), stack);
        recipe.shape("123", "456", "789");
        for (int i = 1; i <= 9; i++)
        {
            String itemMaterial = GetConfig().GetString(GetFormattedPath(recipeName, "craft.slots." + i));
            if (itemMaterial == null || itemMaterial.isEmpty())
                continue;
            Material material = Material.getMaterial(itemMaterial);
            if (material == null)
                continue;
            recipe.setIngredient(Integer.toString(i).charAt(0), material);
        }
        return recipe;
    }

    /**
     * Get the result of the specified recipe
     *
     * @param recipeName Recipe Name
     * @return CustomStack
     */
    protected ItemStack GetRecipeResult(String recipeName)
    {
        ItemStack resultItem;
        if (GetConfig().GetConfiguration().contains(GetFormattedPath(recipeName, "craft.result.uuid")))
        {
            UUID uuid = GetConfig().GetUUID(GetFormattedPath(recipeName, "craft.result.uuid"));
            if (uuid == null)
            {
                Bukkit.getServer().getConsoleSender().sendMessage("Couldn't load recipe '" + recipeName + "', Invalid UUID");
                return new ItemStack(Material.AIR);
            }
            resultItem = GetPlayerHead(uuid);
        }
        else
        {
            String craftMaterial = GetConfig().GetString(GetFormattedPath(recipeName, "craft.result.id"));
            if (craftMaterial == null || Material.getMaterial(craftMaterial) == null)
                return null;
            int resultItemAmount = GetConfig().GetInt(GetFormattedPath(recipeName, "craft.result.id-amount"));
            if (resultItemAmount <= 0)
                return null;
            resultItem = new ItemStack(Material.getMaterial(craftMaterial), resultItemAmount);
        }

        Flag.setFlag(resultItem, "recipeName", recipeName, PersistentDataType.STRING);
        ItemMeta resultItemMeta = resultItem.getItemMeta();
        String craftCustomName = GetConfig().GetString(GetFormattedPath(recipeName, "craft.result.name"));
        if (craftCustomName != null && !craftCustomName.isEmpty())
            resultItemMeta.setDisplayName(craftCustomName.replace("&", "§"));
        String craftCustomLore = GetConfig().GetString(GetFormattedPath(recipeName, "craft.result.lore"));
        if (craftCustomLore != null && !craftCustomLore.isEmpty())
            resultItemMeta.setLore(Arrays.asList((craftCustomLore).replace("&", "§")));
        resultItem.setItemMeta(resultItemMeta);
        ApplyEnchantments(recipeName, resultItem);
        ApplyPotions(recipeName, resultItem);
        return resultItem;
    }


    /**
     * Apply available enchantments to the specified customstack
     *
     * @param recipeName Recipe Name
     * @param stack      Custom Stack
     */
    protected void ApplyEnchantments(String recipeName, ItemStack stack)
    {
        Set<String> enchantSet = GetConfig().GetSection(GetFormattedPath(recipeName, "craft.result.enchantments"));
        if (enchantSet == null)
            return;
        for (String enchant : enchantSet)
        {
            String enchantName = GetConfig().GetString(GetFormattedPath(recipeName, "craft.result.enchantments." + enchant + ".enchant"));
            if (enchantName == null || enchantName.isEmpty())
                continue;
            Enchantment enchantment = Enchantment.getByKey(NamespacedKey.minecraft(enchantName.toLowerCase()));
            if (enchantment == null)
                continue;
            int enchantLevel = GetConfig().GetInt(GetFormattedPath(recipeName, "craft.result.enchantments." + enchant + ".enchant-level"));
            if (enchantLevel <= 0)
                continue;
            stack.addUnsafeEnchantment(enchantment, enchantLevel);
        }
    }
    
    protected void ApplyPotions(String recipeName, ItemStack stack)
    {
        Set<String> potionSet = GetConfig().GetSection(GetFormattedPath(recipeName, "craft.result.potions"));
        if (potionSet == null)
            return;
        for (String potion : potionSet)
        {
        	ItemStack potionmeta = new ItemStack(Material.POTION, 1);
        	PotionMeta meta = (PotionMeta) ItemStackUtils.getMeta(potionmeta);
            PotionEffect effect = GetConfig().GetPotionEffect(GetFormattedPath(recipeName, "craft.result.potions." + potion + ".effect"));
            if (effect == null)
                continue;
            int duration = GetConfig().GetInt(GetFormattedPath(recipeName, "craft.result.potions." + potion + ".duration"));
            if (duration <= 0)
                continue;
            boolean ambient = GetConfig().GetBool(GetFormattedPath(recipeName, "craft.result.potions." + potion + ".ambient"));
            meta.addCustomEffect(effect, ambient);
            stack.setItemMeta(meta);
        }
    }
    

    /**
     * Get formatted path
     *
     * @param recipeName  Recipe Name
     * @param pathToValue Path to Value
     * @return Formatted Path
     */
    protected String GetFormattedPath(String recipeName, String pathToValue)
    {
        String finalPath = GetBasePath();
        if (!finalPath.endsWith("."))
            finalPath += ".";
        finalPath += recipeName;
        if (!pathToValue.startsWith("."))
            finalPath += ".";
        finalPath += pathToValue;
        return finalPath;
    }
}
