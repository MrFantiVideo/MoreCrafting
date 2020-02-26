package fr.mrfantivideo.morecrafting.items;

import fr.mrfantivideo.morecrafting.recipes.CustomRecipe;
import fr.unreal852.sunrealcore.specials.events.inventory.IInventoryLeftClickable;
import fr.unreal852.sunrealcore.specials.item.SpecialItem;
import fr.unreal852.sunrealcore.utils.ItemStackUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class RecipeSpecialItem extends SpecialItem implements IInventoryLeftClickable
{
    private static final Map<CustomRecipe, RecipeSpecialItem> RECIPE_ITEMS = new HashMap<>();

    public static RecipeSpecialItem get(CustomRecipe recipe)
    {
        if (RECIPE_ITEMS.containsKey(recipe))
            return RECIPE_ITEMS.get(recipe);
        return null;
    }

    private CustomRecipe m_customRecipe;
    private ItemStack    m_itemStack;

    public RecipeSpecialItem(ItemStack stack, CustomRecipe recipe)
    {
        super(ItemStackUtils.getMeta(stack).getDisplayName(), stack, recipe.getCustomResult().getLore());
        m_customRecipe = recipe;
        m_itemStack = stack;
        RECIPE_ITEMS.put(recipe, this);
        setCancelNotImplementedEvents(true);
    }

    public CustomRecipe getRecipe()
    {
        return m_customRecipe;
    }

    public ItemStack getItemStack()
    {
        return m_itemStack;
    }

    @Override
    protected void onUnregister()
    {

    }

    @Override
    public void onInventoryLeftClick(InventoryClickEvent inventoryClickEvent)
    {
        Inventory inventory = null;
        switch (m_customRecipe.getRecipeType())
        {
            case CRAFTING_TABLE:
            {
                inventory = Bukkit.createInventory(null, InventoryType.WORKBENCH);
                inventory.setItem(0, UninteractableSpecialItem.apply(getClonedItemStack()));
                break;
            }
            case FURNACE:
                inventory = Bukkit.createInventory(null, InventoryType.FURNACE);
                inventory.setItem(1, UninteractableSpecialItem.apply(new ItemStack(Material.COAL)));
                inventory.setItem(2, UninteractableSpecialItem.apply(getClonedItemStack()));
                break;
            case CAMPFIRE:
                // Not working ingame :/
                break;
            case BLASTING:
                inventory = Bukkit.createInventory(null, InventoryType.BLAST_FURNACE);
                inventory.setItem(1, UninteractableSpecialItem.apply(new ItemStack(Material.COAL)));
                inventory.setItem(2, UninteractableSpecialItem.apply(getClonedItemStack()));
                break;
            case SMOKING:
                inventory = Bukkit.createInventory(null, InventoryType.SMOKER);
                inventory.setItem(1, UninteractableSpecialItem.apply(new ItemStack(Material.COAL)));
                inventory.setItem(2, UninteractableSpecialItem.apply(getClonedItemStack()));
                break;
            case STONE_CUTTING:
                inventory = Bukkit.createInventory(null, InventoryType.STONECUTTER);
                inventory.setItem(1, UninteractableSpecialItem.apply(getClonedItemStack()));
                break;
        }
        if (inventory == null)
            return;
        Map<String, Material> slots = m_customRecipe.getSlots();
        for (String slot : slots.keySet())
            inventory.setItem(Integer.parseInt(slot), UninteractableSpecialItem.apply(new ItemStack(slots.get(slot))));
        inventoryClickEvent.getWhoClicked().openInventory(inventory);
    }
}
