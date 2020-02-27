package fr.mrfantivideo.morecrafting.config;


import fr.mrfantivideo.morecrafting.Main;
import fr.unreal852.sunrealcore.configuration.data.object.ConfigValue;
import fr.unreal852.sunrealcore.configuration.data.object.IConfigObject;
import fr.unreal852.sunrealcore.utils.JavaUtils;
import org.bukkit.ChatColor;

import java.util.HashMap;
import java.util.Map;

public class ConfigLang implements IConfigObject
{
    @ConfigValue(Path = "messages", Type = String.class)
    private Map<String, String> m_messages;

    @Override
    public void onConfigLoaded(String s)
    {
        m_messages = JavaUtils.ensureNotNull(m_messages, new HashMap<>());
        if (MorecrafingConfig.SETTINGS.isDebugEnabled())
            Main.getLog().sendConsoleMessage(ChatColor.GREEN + "Loaded " + MorecrafingConfig.SETTINGS.getLanguage() + ".yml");
    }

    public String getMessage(String key)
    {
        return m_messages.getOrDefault(key, key);
    }
}
