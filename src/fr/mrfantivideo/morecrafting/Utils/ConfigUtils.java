package fr.mrfantivideo.morecrafting.Utils;

import fr.mrfantivideo.morecrafting.Main;

public class ConfigUtils
{
    @SuppressWarnings("unchecked")
    public static <T> T Get(Class<T> type, EConfig config, String path)
    {
        Object obj = null;
        switch (config)
        {
            case SETTINGS:
                break;
            case MESSAGES:
                if (Main.getInstance().getConfigMessages().GetConfiguration().contains(path))
                    obj = Main.getInstance().getConfigMessages().GetConfiguration().get(path);
                break;
            case PERMISSIONS:
                if (Main.getInstance().getConfigPermissions().GetConfiguration().contains(path))
                    obj = Main.getInstance().getConfigPermissions().GetConfiguration().get(path);
                break;
        }
        if (obj != null)
            return (T) obj;
        else
        {
            //LogUtils.LogWarning("Could not find the specified key '" + path + "' in '" + config.toString() + "'. Does it exists ?");
            return null;
        }
    }
}
