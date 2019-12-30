package net.mrfantivideo.morecrafting.Utils;

import net.mrfantivideo.morecrafting.Main;

import java.util.logging.Level;

public class LogUtils
{

    /**
     * Log info
     *
     * @param message Log Message
     */
    public static void LogInfo(String message)
    {
        Log(Level.INFO, message);
    }

    /**
     * Log error
     *
     * @param message Log Message
     */
    public static void LogError(String message)
    {
        Log(Level.SEVERE, message);
    }

    /**
     * Log Warning
     *
     * @param message Log Message
     */
    public static void LogWarning(String message)
    {
        Log(Level.WARNING, message);
    }

    /**
     * Log message to console
     *
     * @param level   Log Level
     * @param message Log Message
     */
    public static void Log(Level level, String message)
    {
        if (!Main.getInstance().isDebugging())
            return;
        Main.getInstance().getLogger().log(level, message);
    }
}
