package net.mrfantivideo.morecrafting.Recipes;

import net.mrfantivideo.morecrafting.Main;
import net.mrfantivideo.morecrafting.Utils.EConfig;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static net.mrfantivideo.morecrafting.Utils.ConfigUtils.Get;

public class RecipesManager
{
    private static RecipesManager s_instance;

    public static RecipesManager GetInstance()
    {
        return s_instance;
    }

    /**
     * Get recipe from id
     * @param id
     * @return CustomRecipe or null
     */
    public static CustomRecipe GetCustomRecipe(String id)
    {
        return GetInstance().GetRecipe(id);
    }

    /**
     * Get shaped recipe from id
     * @param id
     * @return ShapedRecipe or null
     */
    public static ShapedRecipe GetCustomShapedRecipe(String id)
    {
        return GetInstance().GetShapedRecipe(id);
    }

    /**
     * Get recipe from id
     * @param id
     * @return FurnaceRecipe or null
     */
    public FurnaceRecipe GetCustomFurnaceRecipe(String id)
    {
        return GetInstance().GetFurnaceRecipe(id);
    }

    private  Map<String, CustomRecipe> m_recipes = new HashMap<>();

    public RecipesManager()
    {
        s_instance = this;
        LoadRecipes();
    }

    /**
     * Get recipe from id
     * @param id
     * @return CustomRecipe or null
     */
    public CustomRecipe GetRecipe(String id)
    {
        if(m_recipes.containsKey(id))
            return m_recipes.get(id);
        return null;
    }

    /**
     * Get shaped recipe from id
     * @param id
     * @return ShapedRecipe or null
     */
    public ShapedRecipe GetShapedRecipe(String id)
    {
        CustomRecipe recipe = GetRecipe(id);
        if(recipe != null && !recipe.IsFurnaceRecipe())
            return recipe.GetRecipe();
        return null;
    }

    /**
     * Get recipe from id
     * @param id
     * @return FurnaceRecipe or null
     */
    public FurnaceRecipe GetFurnaceRecipe(String id)
    {
        CustomRecipe recipe = GetRecipe(id);
        if(recipe != null && recipe.IsFurnaceRecipe())
            return recipe.GetFurnaceRecipe();
        return null;
    }

    /**
     * Get all recipes
     * @return Recipes
     */
    public Collection<CustomRecipe> GetRecipes()
    {
        return m_recipes.values();
    }

    /**
     * Add a new recipe
     * @param itemID
     * @param recipe
     */
    public void AddRecipe(String itemID, CustomRecipe recipe)
    {
        m_recipes.put(itemID, recipe);
        if(recipe.IsFurnaceRecipe())
            Main.GetInstance().getServer().addRecipe(recipe.GetFurnaceRecipe());
        else
            Main.GetInstance().getServer().addRecipe(recipe.GetRecipe());
    }

    /**
     * Gets a recipe by it's type ( material )
     * @param material
     * @return CustomRecipe or null
     */
    public CustomRecipe GetRecipeByMaterial(Material material)
    {
        for(CustomRecipe recipe : m_recipes.values())
        {
            if(recipe.GetResult().getType() == material)
                return recipe;
        }
        return null;
    }

    /**
     * Clear recipes
     */
    public void Clear()
    {
        m_recipes.clear();
        Main.GetInstance().getServer().resetRecipes();
    }

    /*
        Load Recipes
     */
    public void LoadRecipes()
    {
        Clear();
        LoadShapedRecipes();
        LoadFurnaceRecipes();
        LoadBook();
    }

    /*
        Load Shaped Recipes
     */
    private void LoadShapedRecipes()
    {
        for(String str : Main.GetInstance().GetConfigSettings().GetConfiguration().getConfigurationSection("recipes.crafting").getKeys(false))
        {
            String itemPath = "recipes.crafting." + str + ".";
            if(!Get(Boolean.class, EConfig.SETTINGS, itemPath + "enabled"))
                continue;
            String craftID = Get(String.class, EConfig.SETTINGS, itemPath + "craft.result.id");
            String craftCustomName = Get(String.class, EConfig.SETTINGS, itemPath + "craft.result.name");
            String craftCustomLore = Get(String.class, EConfig.SETTINGS, itemPath + "craft.result.lore");
            String craftEnchant = Get(String.class, EConfig.SETTINGS, itemPath + "craft.enchantment.enchant");
            int craftEnchantAmount = Get(Integer.class, EConfig.SETTINGS, itemPath + "craft.enchantment.enchant-amount");
            int craftAmount = Get(Integer.class, EConfig.SETTINGS, itemPath + "craft.result.id-amount");
            int bookInventorySlot = Get(Integer.class, EConfig.SETTINGS, itemPath + "craft.result.id-book");
            ItemStack result = new ItemStack(Material.getMaterial(craftID), craftAmount);
            ItemMeta meta = result.getItemMeta();
            if(craftCustomName != null && !craftCustomName.isEmpty())
                meta.setDisplayName((craftCustomName).replace("&", "§"));
            if(craftCustomLore != null && !craftCustomLore.isEmpty())
                meta.setLore(Arrays.asList((craftCustomLore).replace("&", "§")));
            result.setItemMeta(meta);
            if(craftEnchant != null && !craftEnchant.isEmpty())
            {
                Enchantment enchant = Enchantment.getByKey(NamespacedKey.minecraft(craftEnchant));
                if(enchant != null)
                    result.addUnsafeEnchantment(enchant, craftEnchantAmount);
            }
            ShapedRecipe craft = new ShapedRecipe(NamespacedKey.randomKey(), result);
            craft.shape("123", "456", "789");
            for(int i = 1; i <= 9; i++)
            {
                String itemID = Get(String.class, EConfig.SETTINGS, itemPath + "craft.slots." + i);
                if(itemID == null || itemID.isEmpty())
                    continue;
                craft.setIngredient(Integer.toString(i).charAt(0), Material.getMaterial(Get(String.class, EConfig.SETTINGS, itemPath + "craft.slots." + i)));
            }
            AddRecipe(craftID, new CustomRecipe(craft, bookInventorySlot, itemPath, str));
        }
    }

    /*
        Load Furnace Recipes
     */
    private void LoadFurnaceRecipes()
    {
        for(String str : Main.GetInstance().GetConfigSettings().GetConfiguration().getConfigurationSection("recipes.furnace").getKeys(false))
        {
            String itemPath = "recipes.furnace." + str + ".";
            if(!Get(Boolean.class, EConfig.SETTINGS, itemPath + "enabled"))
                continue;
            String craftID = Get(String.class, EConfig.SETTINGS,itemPath + "fire.result.id");
            String craftCustomName = Get(String.class, EConfig.SETTINGS, itemPath + "fire.result.name");
            String craftCustomLore = Get(String.class, EConfig.SETTINGS, itemPath + "fire.result.lore");
            int craftAmount = Get(Integer.class, EConfig.SETTINGS, itemPath + "fire.result.id-amount");
            int bookInventorySlot = Get(Integer.class, EConfig.SETTINGS, itemPath + "fire.result.id-book");
            ItemStack result = new ItemStack(Material.getMaterial(craftID), craftAmount);
            ItemMeta meta = result.getItemMeta();
            if(craftCustomName != null && !craftCustomName.isEmpty())
                meta.setDisplayName((craftCustomName).replace("&", "§"));
            if(craftCustomLore != null && !craftCustomLore.isEmpty())
                meta.setLore(Arrays.asList((craftCustomLore).replace("&", "§")));
            result.setItemMeta(meta);
            FurnaceRecipe recipe = new FurnaceRecipe(result, Material.getMaterial(Get(String.class, EConfig.SETTINGS, itemPath + "fire.slot.1")));
            AddRecipe(craftID, new CustomRecipe(recipe, bookInventorySlot, itemPath, str));
        }
    }

    /*
        Load Book
     */
    private void LoadBook()
    {
        if(Get(Boolean.class, EConfig.SETTINGS, "others.book.enabled"))
        {
            String craftID = Get(String.class, EConfig.SETTINGS,"others.book.craft.result.id");
            ItemStack book = new ItemStack(Material.getMaterial(craftID), 1);
            ItemMeta meta = book.getItemMeta();
            meta.setDisplayName(Get(String.class, EConfig.SETTINGS,"others.book.craft.result.name").replace("&", "§"));
            meta.setLore(Arrays.asList(Get(String.class, EConfig.SETTINGS,"others.book.craft.result.lore").replace("&", "§")));
            book.setItemMeta(meta);
            ShapedRecipe craft = new ShapedRecipe(NamespacedKey.randomKey(), book);
            craft.shape("123", "456", "789");
            for(int i = 1; i <= 9; i++)
            {
                String itemID = Get(String.class, EConfig.SETTINGS,"others.book.craft.slots." + i);
                if(itemID == null || itemID.isEmpty())
                    continue;
                craft.setIngredient(Integer.toString(i).charAt(0), Material.getMaterial(Get(String.class, EConfig.SETTINGS,"others.book.craft.slots." + i)));
            }
            AddRecipe(craftID, new CustomRecipe(craft, -1, null, null));
        }
    }
}
