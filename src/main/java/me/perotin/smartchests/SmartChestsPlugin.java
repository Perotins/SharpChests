package me.perotin.smartchests;

import me.perotin.smartchests.commands.SmartChestCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class SmartChestsPlugin extends JavaPlugin {

    /*
        Let's write it! :D
     */


    // singleton for files
    private static SmartChestsPlugin singleton;

    @Override
    public void onEnable(){
        singleton = this;
        getCommand("smartchests").setExecutor(new SmartChestCommand(this));
        init();

    }

    private void init(){
        saveResource("messages.yml", false);
    }

    public static SmartChestsPlugin getSingleton(){
        return singleton;
    }
}
