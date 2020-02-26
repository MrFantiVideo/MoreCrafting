package fr.mrfantivideo.morecrafting.items;

import fr.unreal852.sunrealcore.flags.Flag;
import fr.unreal852.sunrealcore.specials.item.SpecialItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class UninteractableSpecialItem extends SpecialItem
{
    private static final UninteractableSpecialItem INSTANCE = new UninteractableSpecialItem();

    public static ItemStack apply(ItemStack stack)
    {
        Flag.setFlag(stack, "SpecialItemID", INSTANCE.getID(), PersistentDataType.STRING);
        return stack;
    }

    public UninteractableSpecialItem()
    {
        super("", new ItemStack(Material.BARRIER), "");
        setCancelNotImplementedEvents(true);
    }

    @Override
    protected void onUnregister()
    {

    }
}
