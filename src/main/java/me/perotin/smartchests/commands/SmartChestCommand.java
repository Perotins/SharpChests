package me.perotin.smartchests.commands;

import me.perotin.smartchests.SmartChestsPlugin;
import me.perotin.smartchests.files.SmartFile;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
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
        if(args.length == 0 || args[0].equalsIgnoreCase(messages.getString("help"))){
            // just ran /sc
            commandSender.sendMessage(messages.getString("sc-help"));
            commandSender.sendMessage(messages.getString("sc-help-page").replace("$misc$", "1"));
            commandSender.sendMessage(messages.getString("sc-help-info"));
            if(commandSender.hasPermission("smartchest.command.buy")){
                commandSender.spigot().sendMessage(formatTextComponentWith("sc-help-buy"));
            }



        }

        return true;
    }

    private TextComponent formatTextComponentWith(String path){
        TextComponent component = new TextComponent(TextComponent.fromLegacyText(messages.getString(path)));
        component.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(messages.getString("click-copy")).create()));
        component.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, messages.getString(path+"-paste")));
        return component;
    }
}
