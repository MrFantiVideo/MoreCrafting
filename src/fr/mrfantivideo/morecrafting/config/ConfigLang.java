package fr.mrfantivideo.morecrafting.config;


import fr.mrfantivideo.morecrafting.Main;
import fr.unreal852.sunrealcore.utils.JavaUtils;
import org.bukkit.ChatColor;

import java.util.HashMap;
import java.util.Map;

public class ConfigLang
{
    private Map<String, String> m_messages;

    protected void loadConfig(MorecrafingConfig config)
    {
        m_messages = JavaUtils.ensureNotNull(config.getMap(String.class, "messages"), new HashMap<>());
        if (MorecrafingConfig.SETTINGS.isDebugEnabled())
            Main.getLog().sendConsoleMessage(ChatColor.GREEN + "Loaded " + config.getFile().getName());
    }

    public String getMessage(String key)
    {
        return m_messages.getOrDefault(key, key);
    }
}
