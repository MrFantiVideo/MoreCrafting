package net.mrfantivideo.morecrafting.UnrealCoreImports;

import net.mrfantivideo.morecrafting.Main;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.persistence.PersistentDataType;

/**
 * IMPORTED FROM UNREALCORE LIBRARY
 */
public final class Flag
{
    /**
     * Checks if the specified DataHolder has the specified flag.
     *
     * @param data Data Holder
     * @param key  Flag Key
     * @param type Value Type
     * @param <D>  Data Holder Type
     * @param <T>  Java type of the value tag
     * @param <Z>  Type of the object to store
     * @return true if the flag exists, false otherwise
     */
    public static <D extends PersistentDataHolder, T, Z> boolean hasFlag(D data, String key, PersistentDataType<T, Z> type)
    {
        if (data.getPersistentDataContainer().isEmpty())
            return false;
        return data.getPersistentDataContainer().has(new NamespacedKey(Main.getInstance(), key), type);
    }

    /**
     * Get flag value
     *
     * @param data Data Holder
     * @param key  Flag Key
     * @param type Value type
     * @param <D>  Data Holder Type
     * @param <T>  Java type of the tag value
     * @param <Z>  Type of the object to store
     * @return Stored Flag Value
     */
    public static <D extends PersistentDataHolder, T, Z> Z getFlag(D data, String key, PersistentDataType<T, Z> type)
    {
        if (!hasFlag(data, key, type))
            return null;
        return data.getPersistentDataContainer().get(new NamespacedKey(Main.getInstance(), key), type);
    }

    /**
     * Set flag value
     *
     * @param data  Data Holder
     * @param key   Flag Key
     * @param value Flag Value
     * @param type  Value type
     * @param <D>   Data Holder Type
     * @param <T>   Java type of the tag value
     * @param <Z>   Type of the object to store
     */
    public static <D extends PersistentDataHolder, T, Z> void setFlag(D data, String key, Z value, PersistentDataType<T, Z> type)
    {
        data.getPersistentDataContainer().set(new NamespacedKey(Main.getInstance(), key), type, value);
    }

    /**
     * Remove flag
     *
     * @param data Data Holder
     * @param key  Flag Key
     * @param <D>  Data Holder Type
     */
    public static <D extends PersistentDataHolder> void removeFlag(D data, String key)
    {
        data.getPersistentDataContainer().remove(new NamespacedKey(Main.getInstance(), key));
    }

    /**
     * Checks if the specified DataHolder has the specified flag.
     *
     * @param stack Item Stack
     * @param key   Flag Key
     * @param type  Value Type
     * @param <T>   Java type of the tag value
     * @param <Z>   Type of the object to store
     * @return true if the flag exists, false otherwise
     */
    public static <T, Z> boolean hasFlag(ItemStack stack, String key, PersistentDataType<T, Z> type)
    {
        return hasFlag(ItemStackUtils.getMeta(stack), key, type);
    }

    /**
     * @param stack Item Stack
     * @param key   Flag Key
     * @param type  Value Type
     * @param <T>   Java type of the tag value
     * @param <Z>   Type of the object to store
     * @return Stored Flag Value
     */
    public static <T, Z> Z getFlag(ItemStack stack, String key, PersistentDataType<T, Z> type)
    {
        return getFlag(ItemStackUtils.getMeta(stack), key, type);
    }

    /**
     * Set Flag
     *
     * @param stack Item Stack
     * @param key   Flag Key
     * @param value Flag Value
     * @param type  Value Type
     * @param <T>   Java type of the tag value
     * @param <Z>   Type of the object to store
     */
    public static <T, Z> void setFlag(ItemStack stack, String key, Z value, PersistentDataType<T, Z> type)
    {
        ItemMeta meta = ItemStackUtils.getMeta(stack);
        setFlag(meta, key, value, type);
        stack.setItemMeta(meta);
    }

    /**
     * Remove flag
     *
     * @param stack Item Stack
     * @param key   Flag Key
     */
    public static void removeFlag(ItemStack stack, String key)
    {
        ItemMeta meta = ItemStackUtils.getMeta(stack);
        removeFlag(meta, key);
        stack.setItemMeta(meta);
    }

}
