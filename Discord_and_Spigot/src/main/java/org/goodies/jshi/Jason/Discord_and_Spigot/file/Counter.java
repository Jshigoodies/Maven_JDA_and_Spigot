package org.goodies.jshi.Jason.Discord_and_Spigot.file;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.goodies.jshi.Jason.Discord_and_Spigot.Main;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

public class Counter {
    private Main plugin;
    private FileConfiguration dataConfig = null;
    private File configFile = null;


    public Counter(Main p)
    {
        this.plugin = p;

        // saves/initializes the config
        saveDefaultConfig();
    }

    public void reloadConfig()
    {
        if(this.configFile == null)
        {
            this.configFile = new File(this.plugin.getDataFolder(), "data.yml"); //create Folder
        }

        this.dataConfig = YamlConfiguration.loadConfiguration(this.configFile); //loads the data file

        InputStream defaultStream = this.plugin.getResource("data.yml");
        /*
         * InputStream , represents an ordered stream of bytes.
         * In other words, you can read data from a Java InputStream as an ordered sequence of bytes.
         * This is useful when reading data from a file, or received over the network.
         */

        if(defaultStream != null) //if there are bytes in the file, then do this
        {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream)); //I'm basically parsing a InputStreamReader (sequence of bytes) into a yaml configuration. A yaml configuration looks like a plugin.yml file
            this.dataConfig.setDefaults(defaultConfig); //I think it's parsing yaml to FileConfiguration
        }
    }

    public FileConfiguration getConfig()
    {
        if(this.dataConfig == null) {
            reloadConfig();
        }
        return this.dataConfig;
    }

    public void saveConfig()
    {
        if(this.dataConfig == null || this.configFile == null)
        {
            return;
        }

        try {
            this.getConfig().save(this.configFile);
        } catch (IOException e) {
            plugin.getLogger().log(Level.SEVERE, "Could not save config to " + this.configFile, e);
        }
    }

    public void saveDefaultConfig()
    {
        if(this.configFile == null)
        {
            this.configFile = new File(this.plugin.getDataFolder(), "data.yml");
        }

        if(!this.configFile.exists())
        {
            this.plugin.saveResource("data.yml", false);
        }
    }
}
