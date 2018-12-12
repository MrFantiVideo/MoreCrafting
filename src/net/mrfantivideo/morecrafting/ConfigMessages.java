package net.mrfantivideo.morecrafting;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigMessages {

    Main plugin;

    private FileConfiguration customConfig = null;
    private File customConfigFile = null;
   
    public ConfigMessages(Main instance) {
        plugin = instance;
    }
   
    public void initCustomConfig() {

        this.saveDefaultCustomConfig();
        this.saveCustomConfig();
       
    }
    
    public void reloadCustomConfig() {
    	
		ConfigSettings configSettings;
		configSettings = new ConfigSettings(Main.instance, this);
		configSettings.initCustomConfig();
		configSettings.getCustomConfig();
    	
        if (customConfigFile == null) {
        customConfigFile = new File(plugin.getDataFolder(), "lang"  + File.separator + configSettings.getCustomConfig().getString("language") + ".yml");
        }
        customConfig = YamlConfiguration.loadConfiguration(customConfigFile);
        
        Reader defConfigStream = null;
        try {
            defConfigStream = new InputStreamReader(plugin.getResource("lang"  + File.separator + configSettings.getCustomConfig().getString("language") + ".yml"), "UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (defConfigStream != null) {
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(customConfigFile);
            customConfig.setDefaults(defConfig);
        }
    }
   
    public FileConfiguration getCustomConfig() {
        if (customConfig == null) {
            reloadCustomConfig();
        }
        return customConfig;
    }
   
    public void saveCustomConfig() {
        if (customConfig == null || customConfigFile == null) {
            return;
        }
        try {
            getCustomConfig().save(customConfigFile);
        } catch (IOException ex) {
            plugin.getLogger().log(Level.SEVERE, "Could not save config to " + customConfigFile, ex);
        }
    }
   
    public void saveDefaultCustomConfig() {
    	
		ConfigSettings configSettings;
		configSettings = new ConfigSettings(Main.instance, this);
		configSettings.initCustomConfig();
		configSettings.getCustomConfig();
		
        if (customConfigFile == null) {
            customConfigFile = new File(plugin.getDataFolder(), "lang"  + File.separator + configSettings.getCustomConfig().getString("language") + ".yml");
        }
        if (!customConfigFile.exists()) {           
             this.plugin.saveResource("lang"  + File.separator + configSettings.getCustomConfig().getString("language") + ".yml", false);
        }
        
    }
    
}