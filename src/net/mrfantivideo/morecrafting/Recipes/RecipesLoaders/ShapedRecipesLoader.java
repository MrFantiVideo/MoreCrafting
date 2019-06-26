package net.mrfantivideo.morecrafting.Recipes.RecipesLoaders;

import net.mrfantivideo.morecrafting.Configuration.Configs.ConfigSettings;
import net.mrfantivideo.morecrafting.Main;
import net.mrfantivideo.morecrafting.Recipes.CustomRecipe;
import net.mrfantivideo.morecrafting.Recipes.RecipesManager;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Set;

public final class ShapedRecipesLoader
{
    /**
     * Load Shaped Recipes
     */
    public static void LoadShapedRecipes()
    {
        ConfigSettings config = Main.GetInstance().GetConfigSettings();
        for(String recipe : config.GetRecipes())
        {
            if(!config.GetRecipeValue(Boolean.class, recipe, "enabled"))
                continue;
            ItemStack resultItem = GetRecipeResult(config, recipe);
            if(resultItem == null)
                continue;
            ShapedRecipe shapedRecipe = GetRecipe(config, recipe, resultItem);
            if(shapedRecipe == null)
                continue;
            int bookInventorySlot = config.GetRecipeValue(Integer.class, recipe, "craft.result.id-book");
            RecipesManager.GetInstance().AddRecipe(recipe, new CustomRecipe(shapedRecipe, bookInventorySlot, recipe));
        }
    }

    /**
     * Get Recipe Result
     * @param config Config
     * @param recipeName Recipe Name
     * @return ItemStack Result if successfully loaded, null otherwise
     */
    private static ItemStack GetRecipeResult(ConfigSettings config, String recipeName)
    {
        String craftMaterial = config.GetRecipeValue(String.class, recipeName, "craft.result.id");
        if(craftMaterial == null || Material.getMaterial(craftMaterial) == null)
            return null;
        int resultItemAmount = config.GetRecipeValue(Integer.class, recipeName, "craft.result.id-amount"); //Todo: this may throw exception
        if(resultItemAmount <= 0)
            return null;
        ItemStack resultItem = new ItemStack(Material.getMaterial(craftMaterial), resultItemAmount);
        ItemMeta resultItemMeta = resultItem.getItemMeta();

        String craftCustomName = config.GetRecipeValue(String.class, recipeName, "craft.result.name");
        if(craftCustomName != null && !craftCustomName.isEmpty())
            resultItemMeta.setDisplayName(craftCustomName.replace("&", "§"));

        String craftCustomLore = config.GetRecipeValue(String.class, recipeName, "craft.result.lore");
        if(craftCustomLore != null && !craftCustomLore.isEmpty())
            resultItemMeta.setLore(Arrays.asList((craftCustomLore).replace("&", "§")));

        resultItem.setItemMeta(resultItemMeta);
        ApplyEnchantments(config, recipeName, resultItem);
        return resultItem;
    }

    /**
     * Get Recipe
     * @param config Config
     * @param recipeName Recipe Name
     * @param result Recipe Result
     * @return ShapedRecipe is successfully loaded, null otherwise
     */
    private static ShapedRecipe GetRecipe(ConfigSettings  config, String recipeName, ItemStack result)
    {
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.randomKey(), result);
        recipe.shape("123", "456", "789");
        for(int i = 1; i <= 9; i++)
        {
            String itemMaterial = config.GetRecipeValue(String.class, recipeName, "craft.slots." + i);
            if(itemMaterial == null || itemMaterial.isEmpty())
                continue;
            Material material = Material.getMaterial(itemMaterial);
            if(material == null)
                continue;
            recipe.setIngredient(Integer.toString(i).charAt(0), material);
        }
        return recipe;
    }

    /**
     * Apply enchantments to the specified ItemStack
     * @param config Config
     * @param recipeName Recipe Name
     * @param stack ItemStack
     */
    private static void ApplyEnchantments(ConfigSettings config, String recipeName, ItemStack stack)
    {
        Set<String> enchantSet = config.GetSection(recipeName, "craft.result.enchantments");
        if(enchantSet == null)
            return;
        for (String enchant : enchantSet)
        {
            String enchantName = config.GetRecipeValue(String.class, recipeName, "craft.result.enchantments." + enchant + ".enchant");
            if(enchantName == null || enchantName.isEmpty())
                continue;
            Enchantment enchantment = Enchantment.getByKey(NamespacedKey.minecraft(enchantName.toLowerCase()));
            if(enchantment == null)
                continue;
            int enchantLevel = config.GetRecipeValue(Integer.class, recipeName, "craft.result.enchantments." + enchant + ".enchant-level");
            if(enchantLevel <= 0)
                continue;
            stack.addUnsafeEnchantment(enchantment, enchantLevel);
        }
    }
}

