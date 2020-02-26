package fr.mrfantivideo.morecrafting.Recipesold.RecipesLoaders;

import fr.mrfantivideo.morecrafting.Configuration.Configs.ConfigSettings;
import fr.mrfantivideo.morecrafting.Main;
import fr.mrfantivideo.morecrafting.Recipesold.CustomRecipeOld;
import fr.mrfantivideo.morecrafting.Recipesold.RecipesManagerOld;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public final class RecipesBookLoader
{
    /**
     * Load Recipes Book
     */
    public static void LoadRecipesBook()
    {
        ConfigSettings config = Main.getInstance().getConfigSettings();
        if (!config.GetValue(Boolean.class, "others.book.enabled"))
            return;
        String craftMaterial = config.GetValue(String.class, "others.book.craft.result.id");
        if (craftMaterial == null || craftMaterial.isEmpty())
            return;
        Material material = Material.getMaterial(craftMaterial);
        if (material == null)
            return;
        ItemStack book = new ItemStack(material, 1);
        ItemMeta bookMeta = book.getItemMeta();

        String bookName = config.GetValue(String.class, "others.book.craft.result.name").replace("&", "§");
        if (bookName != null && !bookName.isEmpty())
            bookMeta.setDisplayName(bookName);

        String bookLore = config.GetValue(String.class, "others.book.craft.result.lore").replace("&", "§");
        if (bookLore != null && !bookLore.isEmpty())
            bookMeta.setLore(Arrays.asList(bookLore));

        book.setItemMeta(bookMeta);

        ShapedRecipe shapedRecipe = GetRecipe(config, book);
        if (shapedRecipe == null)
            return;
        RecipesManagerOld.GetInstance().AddRecipe("MoreCraftingRecipeBook", new CustomRecipeOld(shapedRecipe, -1, "MoreCraftingRecipeBook"));
    }

    /**
     * Get Recpe
     *
     * @param config Config
     * @param result Result
     *
     * @return ShapedRecipe if successfully loaded, null otherwise
     */
    private static ShapedRecipe GetRecipe(ConfigSettings config, ItemStack result)
    {
        @SuppressWarnings("deprecation")
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.randomKey(), result);
        recipe.shape("123", "456", "789");
        for (int i = 1; i <= 9; i++)
        {
            String itemMaterial = config.GetValue(String.class, "others.book.craft.slots." + i);
            if (itemMaterial == null || itemMaterial.isEmpty())
                continue;
            Material material = Material.getMaterial(itemMaterial);
            if (material == null)
                continue;
            recipe.setIngredient(Integer.toString(i).charAt(0), material);
        }
        return recipe;
    }
}
