package me.perotin.smartchests.objects;

import java.util.UUID;

public class SmartChest {

    private UUID uniqueId;
    private ChestStatus status;
    private UUID owner = null;

    public SmartChest(UUID uniqueId, ChestStatus status, UUID owner) {
        this.uniqueId = uniqueId;
        this.status = status;
        this.owner = owner;
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
        PRIVATE, PUBLIC
    }
}
