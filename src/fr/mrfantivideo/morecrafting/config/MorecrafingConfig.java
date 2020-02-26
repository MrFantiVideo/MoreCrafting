package fr.mrfantivideo.morecrafting.config;

import fr.mrfantivideo.morecrafting.Main;
import fr.unreal852.sunrealcore.configuration.ConfigFile;
import fr.unreal852.sunrealcore.configuration.data.ConfigDataManager;

public class MorecrafingConfig extends ConfigFile
{
    public static final ConfigDataManager CONFIG_DATA_MANAGER = new ConfigDataManager();

    public MorecrafingConfig(String filePath, String resourcePath)
    {
        super(Main.getInstance(), filePath, resourcePath);
        setDataManager(CONFIG_DATA_MANAGER);
    }
}
