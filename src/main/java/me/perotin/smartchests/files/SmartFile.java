package me.perotin.smartchests.files;

import me.perotin.smartchests.SmartChestsPlugin;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class SmartFile {

    private File file;
    private FileConfiguration configuration;

    public SmartFile(Type type){
       switch (type) {
           case CHESTS: file = new File(SmartChestsPlugin.getSingleton().getDataFolder(), "chests.yml");
           break;
           case MESSAGES: file = new File(SmartChestsPlugin.getSingleton().getDataFolder(), "messages.yml");
           break;
       }
        configuration = YamlConfiguration.loadConfiguration(file);
    }

    public void save() {
        try {
            configuration.save(file);
        } catch (IOException ex) {
            ex.printStackTrace();
            ;
        }
    }

    // some generic methods to speed up the process

    public FileConfiguration getConfiguration() {
        return configuration;
    }

    public Object get(String path) {
        return configuration.get(path);
    }

    public void set(String path, Object value) {
        configuration.set(path, value);
    }

    public String getString(String path) {
        return ChatColor.translateAlternateColorCodes('&', configuration.getString(path));


    }

    public enum Type {
        MESSAGES, CHESTS,
    }
}
