package net.mrfantivideo.morecrafting.Utils;

import net.mrfantivideo.morecrafting.Main;

public class ConfigUtils
{
    @SuppressWarnings("unchecked")
    public static <T> T Get(Class<T> type, EConfig config, String path)
    {
        Object obj = null;
        switch(config)
        {
            case SETTINGS:
                if(Main.GetInstance().GetConfigSettings().GetConfiguration().contains(path))
                    obj = Main.GetInstance().GetConfigSettings().GetConfiguration().get(path);
                break;
            case MESSAGES:
                if(Main.GetInstance().GetConfigMessages().GetConfiguration().contains(path))
                    obj = Main.GetInstance().GetConfigMessages().GetConfiguration().get(path);
                break;
            case PERMISSIONS:
                if(Main.GetInstance().GetConfigPermissions().GetConfiguration().contains(path))
                    obj = Main.GetInstance().GetConfigPermissions().GetConfiguration().get(path);
                break;
        }
        if(obj != null)
            return (T)obj;
        else
        {
            //LogUtils.LogWarning("Could not find the specified key '" + path + "' in '" + config.toString() + "'. Does it exists ?");
            return null;
        }
    }
}
