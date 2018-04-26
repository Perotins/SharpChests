package me.perotin.smartchests.objects;

import org.bukkit.Location;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;

public class ItemWrapper {

    private ItemMeta meta;
    private Location location = null;
    private UUID chest;

    public ItemWrapper(UUID uuid, ItemMeta meta) {
        this.chest = uuid;
        this.meta = meta;
    }


    public void setLocation(Location loc){
        this.location = loc;
    }

}
