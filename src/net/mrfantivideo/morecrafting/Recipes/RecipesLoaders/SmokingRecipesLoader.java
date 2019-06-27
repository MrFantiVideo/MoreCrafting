package net.mrfantivideo.morecrafting.Recipes.RecipesLoaders;

import net.mrfantivideo.morecrafting.Configuration.Configs.ConfigSettings;
import net.mrfantivideo.morecrafting.Main;
import net.mrfantivideo.morecrafting.Recipes.CustomRecipe;
import net.mrfantivideo.morecrafting.Recipes.RecipesManager;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.SmokingRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Set;

public final class SmokingRecipesLoader
{
    /**
     * Load Smoking Recipes
     */
    public static void LoadSmokingRecipes()
    {
        ConfigSettings config = Main.GetInstance().GetConfigSettings();
        for(String recipe : config.GetSmokingRecipes())
        {
            if(!config.GetRecipeValue(Boolean.class, recipe, "enabled"))
                continue;
            ItemStack resultItem = GetRecipeResult(config, recipe);
            if(resultItem == null)
                continue;
            SmokingRecipe smokingRecipe = GetRecipe(config, recipe, resultItem);
            if(smokingRecipe == null)
                continue;
            int bookInventorySlot = config.GetRecipeValue(Integer.class, recipe, "smoking.result.id-book");
            RecipesManager.GetInstance().AddRecipe(recipe, new CustomRecipe(smokingRecipe, bookInventorySlot, recipe));
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
        String craftMaterial = config.GetRecipeValue(String.class, recipeName, "smoking.result.id");
        if(craftMaterial == null || Material.getMaterial(craftMaterial) == null)
            return null;
        int resultItemAmount = config.GetRecipeValue(Integer.class, recipeName, "smoking.result.id-amount"); //Todo: this may throw exception
        if(resultItemAmount <= 0)
            return null;
        ItemStack resultItem = new ItemStack(Material.getMaterial(craftMaterial), resultItemAmount);
        ItemMeta resultItemMeta = resultItem.getItemMeta();

        String craftCustomName = config.GetRecipeValue(String.class, recipeName, "smoking.result.name");
        if(craftCustomName != null && !craftCustomName.isEmpty())
            resultItemMeta.setDisplayName(craftCustomName.replace("&", "§"));

        String craftCustomLore = config.GetRecipeValue(String.class, recipeName, "smoking.result.lore");
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
    private static SmokingRecipe GetRecipe(ConfigSettings config, String recipeName, ItemStack result)
    {
        String burnMaterial = config.GetRecipeValue(String.class, recipeName, "smoking.slot.1");
        if(burnMaterial == null || burnMaterial.isEmpty())
            return null;
        Material material = Material.getMaterial(burnMaterial);
        if(material == null)
            return null;
        Float experience = config.GetRecipeValue(Float.class, recipeName, "smoking.result.experience");
        int cookingtime = config.GetRecipeValue(Integer.class, recipeName, "smoking.result.cooking-time");
		@SuppressWarnings("deprecation")
		SmokingRecipe recipe = new SmokingRecipe(NamespacedKey.randomKey(), result, material, experience, cookingtime);
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
        Set<String> enchantSet = config.GetSection(recipeName, "smoking.result.enchantments");
        if(enchantSet == null)
            return;
        for (String enchant : enchantSet)
        {
            String enchantName = config.GetRecipeValue(String.class, recipeName, "smoking.result.enchantments." + enchant + ".enchant");
            if(enchantName == null || enchantName.isEmpty())
                continue;
            Enchantment enchantment = Enchantment.getByKey(NamespacedKey.minecraft(enchantName.toLowerCase()));
            if(enchantment == null)
                continue;
            int enchantLevel = config.GetRecipeValue(Integer.class, recipeName, "smoking.result.enchantments." + enchant + ".enchant-level");
            if(enchantLevel <= 0)
                continue;
            stack.addUnsafeEnchantment(enchantment, enchantLevel);
        }
    }
}
