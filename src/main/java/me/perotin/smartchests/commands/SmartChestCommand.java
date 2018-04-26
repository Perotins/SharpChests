package me.perotin.smartchests.commands;

import me.perotin.smartchests.SmartChestsPlugin;
import me.perotin.smartchests.files.SmartFile;
import me.perotin.smartchests.objects.SmartChest;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class SmartChestCommand implements CommandExecutor{

    private SmartChestsPlugin plugin;
    private SmartFile messages;

    public SmartChestCommand (SmartChestsPlugin plugin){
        this.plugin = plugin;
    }




    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        this.messages = new SmartFile(SmartFile.Type.MESSAGES);
        String prefix = messages.getString("prefix");
        if(args.length == 0 || args[0].equalsIgnoreCase(messages.getString("help"))){
            // just ran /sc
            commandSender.sendMessage(messages.getString("sc-help"));
            commandSender.sendMessage(messages.getString("sc-help-page").replace("$misc$", "1"));
            commandSender.sendMessage(messages.getString("sc-help-info"));
            if(commandSender.hasPermission("smartchest.command.buy")){
                commandSender.spigot().sendMessage(formatTextComponentWith("sc-help-buy"));
            }
        } else if (args.length == 2){
            if(args[0].equalsIgnoreCase("buy")){
                if(commandSender.hasPermission("smartchest.command.buy")){
                    if(commandSender instanceof Player) {
                        int amount = 1;
                        try {
                             amount = Integer.parseInt(args[1]);
                        } catch (NumberFormatException ex){
                            commandSender.sendMessage(messages.getString("cannot-parse").replace("$misc$", args[1]).replace("$prefix$", prefix));
                            return true;
                        }
                        Player player = (Player) commandSender;
                        for(int x = 0; x < amount; x++){
                            SmartChest.createSmartChest(player);
                        }
                        player.sendMessage(messages.getString("bought-smartchest")
                                .replace("$prefix$", prefix)
                                .replace("$amount$", amount +""));
                        return true;

                    } else {
                        //not a player
                        commandSender.sendMessage(messages.getString("player-only"));
                        return true;
                    }

                } else {
                    commandSender.sendMessage(messages.getString("no-permission"));
                    return true;
                }
            }
        } else {

            return false;
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
