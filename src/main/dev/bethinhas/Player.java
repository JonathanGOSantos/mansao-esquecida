package main.dev.bethinhas;

import main.dev.bethinhas.item.Item;
import main.dev.bethinhas.map.Location;
import main.dev.bethinhas.phantom.Phantom;
import main.dev.bethinhas.view.Parser;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

public class Player implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final String name;

    private int health;
    private int maxHealth;

    private Location currentLocation;
    private Stack<Location> locationPath;

    private Set<Item> inventory;

    private List<Phantom> capturedPhantoms;

    public Player(String name) {
        this.name = name;
        this.maxHealth = 20;
        this.locationPath = new Stack<>();
        this.inventory = new HashSet<>();
        this.capturedPhantoms = new ArrayList<>();
    }

    public Player(Save save) {
        this.name = save.getPlayer().getName();

        this.currentLocation = save.getPlayer().getCurrentLocation();
        this.locationPath = save.getPlayer().getLocationPath();

        this.inventory = save.getPlayer().getInventory();
        this.capturedPhantoms = save.getPlayer().getCapturedPhantoms();
        this.health = save.getPlayer().getHealth();
        this.maxHealth = save.getPlayer().getMaxHealth();
    }

    public String getName() {
        return name;
    }

    public Optional<Item> findItem(String itemName) {
        if (itemName.isBlank()) throw new IllegalArgumentException("Nome do item não pode ser nulo ou vazio.");

        return inventory.stream()
                .filter(item -> item.getName().equalsIgnoreCase(itemName))
                .findFirst();
    }

    public void removeItem(Item item) {
        inventory.remove(item);
    }

    public void registerLocation(Location location) {
        if (location == null) return;
        this.locationPath.push(location);
    }

    public Location getCurrentLocation() {
        return this.currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public Set<Item> getInventory() {
        return inventory;
    }

    public void setInventory(Set<Item> inventory) {
        this.inventory = inventory;
    }

    public Stack<Location> getRoomPath() {
        return locationPath;
    }

    public void setRoomPath(Stack<Location> locationPath) {
        this.locationPath = locationPath;
    }

    public void addItem(Item item) {
        this.inventory.add(item);
    }

    public String getResponse() {
        Parser parser = new Parser();
        return parser.readLine();
    }

    public List<Phantom> getCapturedPhantoms() {
        return capturedPhantoms;
    }

    public void setCapturedPhantoms(List<Phantom> capturedPhantoms) {
        this.capturedPhantoms = capturedPhantoms;
    }

    public void addCapturedPhantom(Phantom phantom) {
        this.capturedPhantoms.add(phantom);
    }

    public Stack<Location> getLocationPath() {
        return locationPath;
    }

    public void setLocationPath(Stack<Location> locationPath) {
        this.locationPath = locationPath;
    }

    public void takeDamage(int damage) {
        this.health = Math.max(this.health - damage, 0);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }
}

