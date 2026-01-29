package main.dev.bethinhas.item;

import main.dev.bethinhas.map.Location;

import java.io.Serial;

public class Key extends Item {
    @Serial
    private static final long serialVersionUID = 1L;

    private Location location;

    public Key(String name, String description, Location location) {
        super(name, description);
        this.location = location;
        location.setKey(this);
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location.setKey(null);
        this.location = location;
        this.location.setKey(this);
    }

    public void unlock() {
        this.location.setLocked(false);
    }
}
