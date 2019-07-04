package net.mrfantivideo.morecrafting.Utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

import com.mojang.authlib.GameProfile;

public final class NBTEditor
{
    private static final Map<String, Class<?>> classCache;
    private static final Map<String, Method> methodCache;
    private static final Map<Class<?>, Constructor<?>> constructorCache;
    private static final Map<Class<?>, Class<?>> NBTClasses;
    private static final Map<Class<?>, Field> NBTTagFieldCache;
    private static Field NBTListData;
    private static Field NBTCompoundMap;
    private static final String VERSION;

    /**
     * Cache all NMS classes
     */
    static
    {
        VERSION = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        classCache = new HashMap<String, Class<?>>();
        try
        {
            classCache.put("NBTBase", Class.forName("net.minecraft.server." + VERSION + "." + "NBTBase"));
            classCache.put("NBTTagCompound", Class.forName("net.minecraft.server." + VERSION + "." + "NBTTagCompound"));
            classCache.put("NBTTagList", Class.forName("net.minecraft.server." + VERSION + "." + "NBTTagList"));
            classCache.put("NBTBase", Class.forName("net.minecraft.server." + VERSION + "." + "NBTBase"));
            classCache.put("ItemStack", Class.forName("net.minecraft.server." + VERSION + "." + "ItemStack"));
            classCache.put("CraftItemStack", Class.forName("org.bukkit.craftbukkit." + VERSION + ".inventory." + "CraftItemStack"));
            classCache.put("Entity", Class.forName("net.minecraft.server." + VERSION + "." + "Entity"));
            classCache.put("CraftEntity", Class.forName("org.bukkit.craftbukkit." + VERSION + ".entity." + "CraftEntity"));
            classCache.put("EntityLiving", Class.forName("net.minecraft.server." + VERSION + "." + "EntityLiving"));
            classCache.put("CraftWorld", Class.forName("org.bukkit.craftbukkit." + VERSION + "." + "CraftWorld"));
            classCache.put("CraftBlockState", Class.forName("org.bukkit.craftbukkit." + VERSION + ".block." + "CraftBlockState"));
            classCache.put("BlockPosition", Class.forName("net.minecraft.server." + VERSION + "." + "BlockPosition"));
            classCache.put("TileEntity", Class.forName("net.minecraft.server." + VERSION + "." + "TileEntity"));
            classCache.put("World", Class.forName("net.minecraft.server." + VERSION + "." + "World"));
            classCache.put("TileEntitySkull", Class.forName("net.minecraft.server." + VERSION + "." + "TileEntitySkull"));
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        NBTClasses = new HashMap<Class<?>, Class<?>>();
        try
        {
            NBTClasses.put(Byte.class, Class.forName("net.minecraft.server." + VERSION + "." + "NBTTagByte"));
            NBTClasses.put(String.class, Class.forName("net.minecraft.server." + VERSION + "." + "NBTTagString"));
            NBTClasses.put(Double.class, Class.forName("net.minecraft.server." + VERSION + "." + "NBTTagDouble"));
            NBTClasses.put(Integer.class, Class.forName("net.minecraft.server." + VERSION + "." + "NBTTagInt"));
            NBTClasses.put(Long.class, Class.forName("net.minecraft.server." + VERSION + "." + "NBTTagLong"));
            NBTClasses.put(Short.class, Class.forName("net.minecraft.server." + VERSION + "." + "NBTTagShort"));
            NBTClasses.put(Float.class, Class.forName("net.minecraft.server." + VERSION + "." + "NBTTagFloat"));
            NBTClasses.put(Class.forName("[B"), Class.forName("net.minecraft.server." + VERSION + "." + "NBTTagByteArray"));
            NBTClasses.put(Class.forName("[I"), Class.forName("net.minecraft.server." + VERSION + "." + "NBTTagIntArray"));
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        methodCache = new HashMap<String, Method>();
        try
        {
            methodCache.put("get", getNMSClass("NBTTagCompound").getMethod("get", String.class));
            methodCache.put("set", getNMSClass("NBTTagCompound").getMethod("set", String.class, getNMSClass("NBTBase")));
            methodCache.put("hasKey", getNMSClass("NBTTagCompound").getMethod("hasKey", String.class));
            methodCache.put("setIndex", getNMSClass("NBTTagList").getMethod("a", int.class, getNMSClass("NBTBase")));
            if (VERSION.contains("1_14"))
            {
                methodCache.put("getTypeId", getNMSClass("NBTBase").getMethod("getTypeId"));
                methodCache.put("add", getNMSClass("NBTTagList").getMethod("add", int.class, getNMSClass("NBTBase")));
            }
            else
                methodCache.put("add", getNMSClass("NBTTagList").getMethod("add", getNMSClass("NBTBase")));

            if (VERSION.contains("1_8"))
            {
                methodCache.put("listRemove", getNMSClass("NBTTagList").getMethod("a", int.class));
            }
            else
                methodCache.put("listRemove", getNMSClass("NBTTagList").getMethod("remove", int.class));
            methodCache.put("remove", getNMSClass("NBTTagCompound").getMethod("remove", String.class));
            methodCache.put("hasTag", getNMSClass("ItemStack").getMethod("hasTag"));
            methodCache.put("getTag", getNMSClass("ItemStack").getMethod("getTag"));
            methodCache.put("setTag", getNMSClass("ItemStack").getMethod("setTag", getNMSClass("NBTTagCompound")));
            methodCache.put("asNMSCopy", getNMSClass("CraftItemStack").getMethod("asNMSCopy", ItemStack.class));
            methodCache.put("asBukkitCopy", getNMSClass("CraftItemStack").getMethod("asBukkitCopy", getNMSClass("ItemStack")));
            methodCache.put("getEntityHandle", getNMSClass("CraftEntity").getMethod("getHandle"));
            methodCache.put("getEntityTag", getNMSClass("Entity").getMethod("c", getNMSClass("NBTTagCompound")));
            methodCache.put("setEntityTag", getNMSClass("Entity").getMethod("f", getNMSClass("NBTTagCompound")));

            if (VERSION.contains("1_12") || VERSION.contains("1_13") || VERSION.contains("1_14"))
                methodCache.put("setTileTag", getNMSClass("TileEntity").getMethod("load", getNMSClass("NBTTagCompound")));
            else
                methodCache.put("setTileTag", getNMSClass("TileEntity").getMethod("a", getNMSClass("NBTTagCompound")));
            methodCache.put("getTileEntity", getNMSClass("World").getMethod("getTileEntity", getNMSClass("BlockPosition")));
            methodCache.put("getWorldHandle", getNMSClass("CraftWorld").getMethod("getHandle"));
            methodCache.put("setGameProfile", getNMSClass("TileEntitySkull").getMethod("setGameProfile", GameProfile.class));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        try
        {
            methodCache.put("getTileTag", getNMSClass("TileEntity").getMethod("save", getNMSClass("NBTTagCompound")));
        }
        catch (NoSuchMethodException exception)
        {
            try
            {
                methodCache.put("getTileTag", getNMSClass("TileEntity").getMethod("b", getNMSClass("NBTTagCompound")));
            }
            catch (Exception exception2)
            {
                exception2.printStackTrace();
            }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }

        constructorCache = new HashMap<Class<?>, Constructor<?>>();
        try
        {
            constructorCache.put(getNBTTag(Byte.class), getNBTTag(Byte.class).getConstructor(byte.class));
            constructorCache.put(getNBTTag(String.class), getNBTTag(String.class).getConstructor(String.class));
            constructorCache.put(getNBTTag(Double.class), getNBTTag(Double.class).getConstructor(double.class));
            constructorCache.put(getNBTTag(Integer.class), getNBTTag(Integer.class).getConstructor(int.class));
            constructorCache.put(getNBTTag(Long.class), getNBTTag(Long.class).getConstructor(long.class));
            constructorCache.put(getNBTTag(Float.class), getNBTTag(Float.class).getConstructor(float.class));
            constructorCache.put(getNBTTag(Short.class), getNBTTag(Short.class).getConstructor(short.class));
            constructorCache.put(getNBTTag(Class.forName("[B")), getNBTTag(Class.forName("[B")).getConstructor(Class.forName("[B")));
            constructorCache.put(getNBTTag(Class.forName("[I")), getNBTTag(Class.forName("[I")).getConstructor(Class.forName("[I")));
            constructorCache.put(getNMSClass("BlockPosition"), getNMSClass("BlockPosition").getConstructor(int.class, int.class, int.class));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        NBTTagFieldCache = new HashMap<Class<?>, Field>();
        try
        {
            for (Class<?> clazz : NBTClasses.values())
            {
                Field data = clazz.getDeclaredField("data");
                data.setAccessible(true);
                NBTTagFieldCache.put(clazz, data);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        try
        {
            NBTListData = getNMSClass("NBTTagList").getDeclaredField("list");
            NBTListData.setAccessible(true);
            NBTCompoundMap = getNMSClass("NBTTagCompound").getDeclaredField("map");
            NBTCompoundMap.setAccessible(true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private static Class<?> getNBTTag(Class<?> primitiveType)
    {
        if (NBTClasses.containsKey(primitiveType))
            return NBTClasses.get(primitiveType);
        return primitiveType;
    }

    private static Object getNBTVar(Object object)
    {
        if (object == null)
            return null;
        Class<?> clazz = object.getClass();
        try
        {
            if (NBTTagFieldCache.containsKey(clazz))
                return NBTTagFieldCache.get(clazz).get(object);
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        return null;
    }

    private static Method getMethod(String name)
    {
        return methodCache.containsKey(name) ? methodCache.get(name) : null;
    }

    private static Constructor<?> getConstructor(Class<?> clazz)
    {
        return constructorCache.containsKey(clazz) ? constructorCache.get(clazz) : null;
    }

    private static Class<?> getNMSClass(String name)
    {
        if (classCache.containsKey(name))
            return classCache.get(name);
        try
        {
            return Class.forName("net.minecraft.server." + VERSION + "." + name);
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Gets the Bukkit version
     *
     * @return The Bukkit version in standard package format
     */
    public final static String getVersion()
    {
        return VERSION;
    }

    /**
     * @param item The itemstack to get the keys from
     * @param keys The keys to fetch; an integer after a key value indicates that it should get the nth place of
     *             the previous compound because it is a list;
     * @return The item represented by the keys, and an integer if it is showing how long a list is.
     * @deprecated Gets an NBT tag in a given item with the specified keys
     */
    public final static Object getItemTag(ItemStack item, Object... keys)
    {
        if (item == null)
            return null;
        try
        {
            Object stack = null;
            stack = getMethod("asNMSCopy").invoke(null, item);

            Object tag = null;

            if (getMethod("hasTag").invoke(stack).equals(true))
                tag = getMethod("getTag").invoke(stack);
            else
                tag = getNMSClass("NBTTagCompound").newInstance();
            return getTag(tag, keys);
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            return null;
        }
    }

    /**
     * @param item  The itemstack to set
     * @param keys  The keys to set, String for NBTCompound, int or null for an NBTTagList
     * @param value The value to set
     * @return A new ItemStack with the updated NBT tags
     * @deprecated Sets an NBT tag in an item with the provided keys and value
     */
    public final static ItemStack setItemTag(ItemStack item, Object value, Object... keys)
    {
        if (item == null)
            return null;
        try
        {
            Object stack = getMethod("asNMSCopy").invoke(null, item);
            Object tag = null;
            if (getMethod("hasTag").invoke(stack).equals(true))
                tag = getMethod("getTag").invoke(stack);
            else
                tag = getNMSClass("NBTTagCompound").newInstance();
            setTag(tag, value, keys);
            getMethod("setTag").invoke(stack, tag);
            return (ItemStack) getMethod("asBukkitCopy").invoke(null, stack);
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            return null;
        }
    }

    /**
     * Gets a string from an object
     *
     * @param object Must be an ItemStack, Entity, or Block
     * @param keys   Keys in descending order
     * @return A string, or null if none is stored at the provided location
     */
    public final static String getString(Object object, Object... keys)
    {
        Object result;
        if (object instanceof ItemStack)
            result = getItemTag((ItemStack) object, keys);
        else
            throw new IllegalArgumentException("Object provided must be of type ItemStack, Entity, or Block!");
        return result instanceof String ? (String) result : null;
    }

    /**
     * Checks if the object contains the given key
     *
     * @param object Must be an ItemStack, Entity, or Block
     * @param keys   Keys in descending order
     * @return Whether or not the particular tag exists, may not be a primitive
     */
    public final static boolean contains(Object object, Object... keys)
    {
        Object result;
        if (object instanceof ItemStack)
            result = getItemTag((ItemStack) object, keys);
        else
            throw new IllegalArgumentException("Object provided must be of type ItemStack, Entity, or Block!");
        return result != null;
    }

    /**
     * Sets the value in the object with the given keys
     *
     * @param object Must be an ItemStack, Entity, or Block
     * @param value  The value to set, can be an NBTCompound
     * @param keys   The keys in descending order
     * @return The new item stack if the object provided is an item, else original object
     */
    public final static <T> T set(T object, Object value, Object... keys)
    {
        if (object instanceof ItemStack)
            return (T) setItemTag((ItemStack) object, value, keys);
        else
            throw new IllegalArgumentException("Object provided must be of type ItemStack, Entity, or Block!");
    }

    private static void setTag(Object tag, Object value, Object... keys) throws Exception
    {
        Object notCompound;
        if (value != null)
        {
            if (getNMSClass("NBTTagList").isInstance(value) || getNMSClass("NBTTagCompound").isInstance(value))
                notCompound = value;
            else
                notCompound = getConstructor(getNBTTag(value.getClass())).newInstance(value);
        }
        else
            notCompound = null;
        Object compound = tag;
        for (int index = 0; index < keys.length; index++)
        {
            Object key = keys[index];
            if (index + 1 == keys.length)
            {
                if (key == null)
                {
                    if (VERSION.contains("1_14"))
                    {
                        int type = (int) getMethod("getTypeId").invoke(notCompound);
                        getMethod("add").invoke(compound, type, notCompound);
                    }
                    else
                        getMethod("add").invoke(compound, notCompound);
                }
                else if (key instanceof Integer)
                {
                    if (notCompound == null)
                        getMethod("listRemove").invoke(compound, (int) key);
                    else
                        getMethod("setIndex").invoke(compound, (int) key, notCompound);
                }
                else
                {
                    if (notCompound == null)
                        getMethod("remove").invoke(compound, (String) key);
                    else
                        getMethod("set").invoke(compound, (String) key, notCompound);
                }
                break;
            }
            Object oldCompound = compound;
            if (key instanceof Integer)
                compound = ((List<?>) NBTListData.get(compound)).get((int) key);
            else if (key != null)
                compound = getMethod("get").invoke(compound, (String) key);
            if (compound == null || key == null)
            {
                if (keys[index + 1] == null || keys[index + 1] instanceof Integer)
                    compound = getNMSClass("NBTTagList").newInstance();
                else
                    compound = getNMSClass("NBTTagCompound").newInstance();
                if (oldCompound.getClass().getSimpleName().equals("NBTTagList"))
                    getMethod("add").invoke(oldCompound, compound);
                else
                {
                    if (notCompound == null)
                        getMethod("remove").invoke(oldCompound, (String) key);
                    else
                        getMethod("set").invoke(oldCompound, (String) key, compound);
                }
            }
        }
    }

    private static Object getTag(Object tag, Object... keys) throws Exception
    {
        if (keys.length == 0)
            return getTags(tag);
        Object notCompound = tag;
        for (Object key : keys)
        {
            if (notCompound == null)
                return null;
            else if (getNMSClass("NBTTagCompound").isInstance(notCompound))
                notCompound = getMethod("get").invoke(notCompound, (String) key);
            else if (getNMSClass("NBTTagList").isInstance(notCompound))
                notCompound = ((List<?>) NBTListData.get(notCompound)).get((int) key);
            else
                return getNBTVar(notCompound);
        }
        if (notCompound == null)
            return null;
        else if (getNMSClass("NBTTagList").isInstance(notCompound))
            return getTags(notCompound);
        else if (getNMSClass("NBTTagCompound").isInstance(notCompound))
            return getTags(notCompound);
        else
            return getNBTVar(notCompound);
    }

    @SuppressWarnings("unchecked")
    private static Object getTags(Object tag)
    {
        Map<Object, Object> tags = new HashMap<Object, Object>();
        try
        {
            if (getNMSClass("NBTTagCompound").isInstance(tag))
            {
                Map<String, Object> tagCompound = (Map<String, Object>) NBTCompoundMap.get(tag);
                for (String key : tagCompound.keySet())
                {
                    Object value = tagCompound.get(key);
                    if (getNMSClass("NBTTagEnd").isInstance(value))
                        continue;
                    tags.put(key, getTag(value));
                }
            }
            else if (getNMSClass("NBTTagList").isInstance(tag))
            {
                List<Object> tagList = (List<Object>) NBTListData.get(tag);
                for (int index = 0; index < tagList.size(); index++)
                {
                    Object value = tagList.get(index);
                    if (getNMSClass("NBTTagEnd").isInstance(value))
                        continue;
                    tags.put(index, getTag(value));
                }
            }
            else
                return getNBTVar(tag);
            return tags;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return tags;
        }
    }
}
