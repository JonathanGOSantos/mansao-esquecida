package main.item;

import main.map.Location;

import java.io.Serial;

public class Key extends Item {
    @Serial
    private static final long serialVersionUID = 1L;

    private Location location;

    public Key(String name, String description, Location location) {
        super(name, description);
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public void setRoom(Location location) {
        this.location = location;
    }

    public void unlock() {
        this.location.setLocked(false);
    }
}
