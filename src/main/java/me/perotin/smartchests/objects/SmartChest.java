package me.perotin.smartchests.objects;

import me.perotin.smartchests.files.SmartFile;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class SmartChest {

    private UUID uniqueId;
    private ChestStatus status;
    private UUID owner = null;
    private ItemWrapper wrapper;



    public static HashSet<SmartChest> chests = new HashSet<>();

    public SmartChest(UUID uniqueId, ChestStatus status, UUID owner, ItemWrapper wrapper) {
        this.uniqueId = uniqueId;
        this.status = status;
        this.wrapper = wrapper;

    }

    public ItemWrapper getWrapper() {
        return wrapper;
    }

    public void setWrapper(ItemWrapper wrapper) {
        this.wrapper = wrapper;
    }

    public UUID getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(UUID uniqueId) {
        this.uniqueId = uniqueId;
    }

    public ChestStatus getStatus() {
        return status;
    }

    public void setStatus(ChestStatus status) {
        this.status = status;
    }

    public UUID getOwner() {
        return owner;
    }

    public void setOwner(UUID owner) {
        this.owner = owner;
    }

    public enum ChestStatus {
        CLAIMED, UNCLAIMED
    }

    public static void createSmartChest(Player player){
        ItemStack chest = constructChest(player.getName(), player.getUniqueId().toString());
        UUID chestID = UUID.randomUUID();
        SmartChest smartChest = new SmartChest(chestID, ChestStatus.UNCLAIMED, null, new ItemWrapper(chestID, chest.getItemMeta()));
        chests.add(smartChest);
        player.getInventory().addItem(chest);



    }

    private static ItemStack constructChest(String name, String uuid){
        SmartFile messages = new SmartFile(SmartFile.Type.MESSAGES);
        ItemStack chest = new ItemStack(Material.CHEST);
        ItemMeta chestMeta = chest.getItemMeta();
        chestMeta.setDisplayName(messages.getString("smartchest-display").replace("$name$", name));
        chestMeta.setLore(Arrays.asList(messages.getString("smartchest-uuid").replace("$uuid$", uuid)));
        chest.setItemMeta(chestMeta);



        return chest;
    }
}
