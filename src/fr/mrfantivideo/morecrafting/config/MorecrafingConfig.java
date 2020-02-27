package fr.mrfantivideo.morecrafting.config;

import fr.mrfantivideo.morecrafting.Main;

import fr.unreal852.sunrealcore.configuration.ConfigFile;
import fr.unreal852.sunrealcore.configuration.data.ConfigDataManager;

public class MorecrafingConfig extends ConfigFile
{
    private static final String            CONFIG_JAR_PATH     = "fr/mrfantivideo/morecrafting";
    public static final  ConfigDataManager CONFIG_DATA_MANAGER = new ConfigDataManager();
    public static final  ConfigSettings    SETTINGS;
    public static final  ConfigLang        LANG;
    public static final  ConfigPermissions PERMISSIONS;

    static
    {
        SETTINGS = new MorecrafingConfig("/settings.yml", CONFIG_JAR_PATH + "/config/settings.yml").get(ConfigSettings.class, "");
        LANG = new MorecrafingConfig("/lang/" + SETTINGS.getLanguage() + ".yml", CONFIG_JAR_PATH + "/lang/" + SETTINGS.getLanguage() + ".yml").get(ConfigLang.class, "");
        PERMISSIONS = new MorecrafingConfig("/permissions.yml", CONFIG_JAR_PATH + "/config/permissions.yml").get(ConfigPermissions.class, "");
    }

    /**
     * Call this to static-init this class
     */
    public static void loadConfig()
    {

    }

    public MorecrafingConfig(String filePath, String resourcePath)
    {
        super(Main.getInstance(), filePath, resourcePath);
        setDataManager(CONFIG_DATA_MANAGER);
    }
}
