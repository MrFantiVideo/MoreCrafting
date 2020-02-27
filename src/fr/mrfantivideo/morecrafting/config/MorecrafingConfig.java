package fr.mrfantivideo.morecrafting.config;

import fr.mrfantivideo.morecrafting.Main;

import fr.unreal852.sunrealcore.configuration.ConfigFile;
import fr.unreal852.sunrealcore.configuration.data.ConfigDataManager;

public class MorecrafingConfig extends ConfigFile
{
    private static final String            CONFIG_JAR_PATH     = "fr/mrfantivideo/morecrafting";
    public static final  ConfigDataManager CONFIG_DATA_MANAGER = new ConfigDataManager();
    public static final  ConfigSettings    SETTINGS            = new ConfigSettings();
    public static final  ConfigLang        LANG                = new ConfigLang();
    public static final  ConfigPermissions PERMISSIONS         = new ConfigPermissions();

    /**
     * Call this to static-init this class
     */
    public static void loadConfig()
    {
        SETTINGS.loadConfig(new MorecrafingConfig("/settings.yml", CONFIG_JAR_PATH + "/config/settings.yml"));
        LANG.loadConfig(new MorecrafingConfig("/lang/" + SETTINGS.getLanguage() + ".yml", CONFIG_JAR_PATH + "/lang/" + SETTINGS.getLanguage() + ".yml"));
        PERMISSIONS.loadConfig(new MorecrafingConfig("/permissions.yml", CONFIG_JAR_PATH + "/config/permissions.yml"));
    }

    public MorecrafingConfig(String filePath, String resourcePath)
    {
        super(Main.getInstance(), filePath, resourcePath);
        setDataManager(CONFIG_DATA_MANAGER);
    }
}
