package fr.mrfantivideo.morecrafting.Utils;

import org.bukkit.entity.Player;

import static fr.mrfantivideo.morecrafting.Utils.ConfigUtils.Get;

public class PermissionsUtils
{
    /**
     * Checks if the player has any of the specified permissions
     *
     * @param player
     * @param permissions
     *
     * @return true if has permission, false otherwise
     */
    public static boolean HasAnyPermission(Player player, String... permissions)
    {
        for (String perm : permissions)
        {
            if (player.hasPermission(Get(String.class, EConfig.PERMISSIONS, perm)))
                return true;
        }
        return false;
    }
}
