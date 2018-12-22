package net.mrfantivideo.morecrafting.Utils;

import net.mrfantivideo.morecrafting.Main;

import java.util.logging.Level;

public class LogUtils
{
    /**
     * Log error
     * @param message
     * @param ex
     */
    public static void LogError(String message, Exception ex)
    {
        if(ex != null)
            Main.GetInstance().getLogger().log(Level.SEVERE, message, ex);
        else
            Main.GetInstance().getLogger().log(Level.SEVERE, message);
    }

    /**
     * Log Warning
     * @param message
     */
    public static void LogWarning(String message)
    {
        Main.GetInstance().getLogger().log(Level.WARNING, message);
    }
}
