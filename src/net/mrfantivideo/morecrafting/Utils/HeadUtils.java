package net.mrfantivideo.morecrafting.Utils;

import net.mrfantivideo.morecrafting.UnrealCoreImports.ItemStackUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.UUID;

public final class HeadUtils
{
    /**
     * Get a player head
     *
     * @param displayName Item Display Name
     * @param playerUUID  Head UUID
     *
     * @return Item Stack
     */
    public static ItemStack getPlayerHead(String displayName, UUID playerUUID)
    {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta meta = (SkullMeta) ItemStackUtils.getMeta(head);
        meta.setOwningPlayer(Bukkit.getOfflinePlayer(playerUUID));
        meta.setDisplayName(displayName);
        head.setItemMeta(meta);
        return head;
    }
}
