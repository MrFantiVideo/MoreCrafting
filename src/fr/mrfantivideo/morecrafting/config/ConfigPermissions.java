package fr.mrfantivideo.morecrafting.config;

import fr.mrfantivideo.morecrafting.Main;
import fr.unreal852.sunrealcore.utils.JavaUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.Map;

public class ConfigPermissions
{
    private Map<String, String> m_permissions;

    protected void loadConfig(MorecrafingConfig config)
    {
        m_permissions = JavaUtils.ensureNotNull(config.getMap(String.class, "permissions"), new HashMap<>());
        if(MorecrafingConfig.SETTINGS.isDebugEnabled())
            Main.getLog().sendConsoleMessage(ChatColor.GREEN + "Loaded permissions.yml");
    }

    public String getPermission(String key)
    {
        return m_permissions.getOrDefault(key, key);
    }

    public boolean hasPermissions(CommandSender sender, String... permissions)
    {
        if (permissions == null || permissions.length == 0)
            return false;
        if (permissions.length == 1)
            return sender.hasPermission(getPermission(permissions[0]));
        for (String permission : permissions)
        {
            if (!sender.hasPermission(getPermission(permission)))
                return false;
        }
        return true;
    }
}
