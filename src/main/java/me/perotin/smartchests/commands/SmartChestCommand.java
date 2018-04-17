package me.perotin.smartchests.commands;

import me.perotin.smartchests.SmartChestsPlugin;
import me.perotin.smartchests.files.SmartFile;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


public class SmartChestCommand implements CommandExecutor{

    private SmartChestsPlugin plugin;
    private SmartFile messages;

    public SmartChestCommand (SmartChestsPlugin plugin){
        this.plugin = plugin;
    }



    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        this.messages = new SmartFile(SmartFile.Type.MESSAGES);
        if(args.length == 0){
            // just ran /sc

        }

        return true;
    }
}
