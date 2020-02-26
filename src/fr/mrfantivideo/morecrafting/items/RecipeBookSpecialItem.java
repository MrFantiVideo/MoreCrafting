package fr.mrfantivideo.morecrafting.items;

import fr.mrfantivideo.morecrafting.recipes.CustomRecipeType;
import fr.unreal852.sunrealcore.specials.item.SpecialItem;
import fr.unreal852.sunrealcore.specials.events.IHeldChangeable;
import fr.unreal852.sunrealcore.specials.events.IRightHandClickable;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class RecipeBookSpecialItem extends SpecialItem implements IRightHandClickable, IHeldChangeable
{
    private static       RecipeBookSpecialItem         INSTANCE;
    private static final RecipeBookCategorySpecialItem CATEGORY_CRAFTING_TABLE = new RecipeBookCategorySpecialItem("Crating Table Recipes", Material.CRAFTING_TABLE, CustomRecipeType.CRAFTING_TABLE);
    private static final RecipeBookCategorySpecialItem CATEGORY_FURNACE        = new RecipeBookCategorySpecialItem("Furnace Recipes", Material.FURNACE, CustomRecipeType.FURNACE);
    private static final RecipeBookCategorySpecialItem CATEGORY_BLASTING       = new RecipeBookCategorySpecialItem("Blast Furnace Recipes", Material.BLAST_FURNACE, CustomRecipeType.BLASTING);
    private static final RecipeBookCategorySpecialItem CATEGORY_SMOKING        = new RecipeBookCategorySpecialItem("Smoking Recipes", Material.SMOKER, CustomRecipeType.SMOKING);
    private static final RecipeBookCategorySpecialItem CATEGORY_CAMPFIRE       = new RecipeBookCategorySpecialItem("Campfire Recipes", Material.CAMPFIRE, CustomRecipeType.CAMPFIRE);
    private static final RecipeBookCategorySpecialItem CATEGORY_STONECUTTING   = new RecipeBookCategorySpecialItem("Stonecutting Recipes", Material.STONECUTTER, CustomRecipeType.STONE_CUTTING);

    public static RecipeBookSpecialItem getInstance()
    {
        return INSTANCE;
    }

    public RecipeBookSpecialItem(String name, ItemStack stack, String... lore)
    {
        super("Recipe Book", stack, lore);
        INSTANCE = this;
    }

    @Override
    protected void onUnregister()
    {

    }

    @Override
    public void onHandRightClick(PlayerInteractEvent playerInteractEvent)
    {
        Inventory inventory = Bukkit.createInventory(null, 9);
        inventory.setItem(0, CATEGORY_CRAFTING_TABLE.getClonedItemStack());
        inventory.setItem(1, CATEGORY_FURNACE.getClonedItemStack());
        inventory.setItem(2, CATEGORY_BLASTING.getClonedItemStack());
        inventory.setItem(3, CATEGORY_SMOKING.getClonedItemStack());
        inventory.setItem(4, CATEGORY_CAMPFIRE.getClonedItemStack());
        inventory.setItem(5, CATEGORY_STONECUTTING.getClonedItemStack());
        playerInteractEvent.getPlayer().openInventory(inventory);
    }

    @Override
    public void onSelected(PlayerItemHeldEvent playerItemHeldEvent)
    {

    }

    @Override
    public void onUnselected(PlayerItemHeldEvent playerItemHeldEvent)
    {

    }
}
