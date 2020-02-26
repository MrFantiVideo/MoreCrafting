package fr.mrfantivideo.morecrafting.items.categories;

import fr.mrfantivideo.morecrafting.items.RecipeSpecialItem;
import fr.mrfantivideo.morecrafting.recipes.CustomRecipe;
import fr.mrfantivideo.morecrafting.recipes.CustomRecipeType;
import fr.mrfantivideo.morecrafting.recipes.RecipesManager;
import fr.unreal852.sunrealcore.specials.events.inventory.IInventoryLeftClickable;
import fr.unreal852.sunrealcore.specials.item.SpecialItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.stream.Collectors;

public class RecipeBookCategorySpecialItem extends SpecialItem implements IInventoryLeftClickable
{
    private CustomRecipeType m_recipeType;

    public RecipeBookCategorySpecialItem(String name, Material material, CustomRecipeType recipeType)
    {
        super(name, new ItemStack(material), "");
        m_recipeType = recipeType;
        setCancelNotImplementedEvents(true);
    }

    public CustomRecipeType getRecipeType()
    {
        return m_recipeType;
    }

    @Override
    protected void onUnregister()
    {

    }

    @Override
    public void onInventoryLeftClick(InventoryClickEvent inventoryClickEvent)
    {
        Collection<CustomRecipe> recipes = RecipesManager.getRecipes().stream().filter(recipe -> recipe.getRecipeType() == m_recipeType).collect(Collectors.toList());
        Inventory inventory = Bukkit.createInventory(null, recipes.size() <= 9 ? 9 : ((recipes.size() / 9) + 1));
        for (CustomRecipe recipe : recipes)
            inventory.addItem(RecipeSpecialItem.get(recipe).getItemStack());
        inventoryClickEvent.getWhoClicked().openInventory(inventory);
    }
}
