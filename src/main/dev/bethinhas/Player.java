package main;

import main.item.Item;
import main.map.Location;
import main.phantom.Phantom;
import main.view.Parser;

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

    private String input;

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

    public Item findItem(String itemName) {
        if (itemName.isBlank()) throw new IllegalArgumentException("Nome do item não pode ser nulo ou vazio.");

        return inventory.stream()
                .filter(item -> item.getName().equalsIgnoreCase(itemName))
                .findFirst()
                .orElse(null);
    }

    public Item takeItem(String itemName) {
        if (itemName.isBlank()) throw new IllegalArgumentException("Pegar o que?");
        Item item = currentLocation.takeItem(itemName);
        this.inventory.add(item);
        return item;
    }

    public Item dropItem(String itemName) {
        if (itemName.isBlank()) throw new IllegalArgumentException("Largar o que?");
        Item item = findItem(itemName);
        this.inventory.remove(item);
        return item;
    }

    public void removeItem(Item item) {
        inventory.remove(item);
    }

    public void removeItem(String itemName) {
        Item item = findItem(itemName);
        if (item == null) throw new IllegalArgumentException("Você não tem esse item.");
        inventory.remove(item);
    }

    public void registerRoom(Location location) {
        if (location == null) return;
        this.locationPath.push(location);
    }

    public Location go(String to) {
        Location location = getCurrentLocation().getExit(to);
        if (location == null) throw new IllegalArgumentException("Isso não é um cômodo!");

        this.registerRoom(currentLocation);
        this.currentLocation = location;
        return currentLocation;
    }

    public Location back() {
        if (locationPath.empty()) throw new RuntimeException("Não há salas para voltar.");
        currentLocation = locationPath.pop();
        return currentLocation;
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

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public Item dropCurrentItem() {
        return null;
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

