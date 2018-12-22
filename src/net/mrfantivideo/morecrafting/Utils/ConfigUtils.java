package net.mrfantivideo.morecrafting.Utils;

import com.sun.istack.internal.NotNull;
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
                obj = Main.GetInstance().GetConfigSettings().GetConfiguration().get(path);
                break;
            case MESSAGES:
                obj = Main.GetInstance().GetConfigMessages().GetConfiguration().get(path);
                break;
            case PERMISSIONS:
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
