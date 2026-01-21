package main.phantom;

import main.Player;
import main.item.Item;

import java.io.Serial;

public class FighterPhantom extends Phantom {
    @Serial
    private static final long serialVersionUID = 1L;

    private int health;
    private int maxHealth;
    private int attackPower;

    public FighterPhantom(String name, String introText, String whoCapture, int maxHealth, int attackPower) {
        super(name, introText, whoCapture);
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.attackPower = attackPower;
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

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    @Override
    public void interact(Player player) {
        if (this.isCaptured()) throw new RuntimeException("O fantasma já está capturado.");

        System.out.println("Qual item deseja utilizar para batalhar o fantasma?");
        Item equippedItem = player.findItem(player.getResponse());
        if (equippedItem != null && equippedItem.getName().equalsIgnoreCase("Espelho")) {
            this.health = 0;
            this.setCaptured(true);
            System.out.println("Você levanta o Espelho! O " + this.getName() + " vê seu próprio reflexo distorcido, grita de horror e é sugado para dentro do espelho! Capturado!");
            return;
        }

        int playerDamage = calculateDamage(equippedItem);
        if (playerDamage < 5 && Math.random() > 0.7) {
            System.out.println("O " + this.getName() + " desviou do seu ataque!\n");
        } else {
            this.health = Math.max(0, this.health - playerDamage);
            System.out.println("Você acertou o fantasma causando " + playerDamage + " de dano! ");
            System.out.println("(Vida do Fantasma: " + this.health + "/" + this.maxHealth + ")\n");
        }

        if (this.health == 0) {
            this.setCaptured(true);
            System.out.println("O fantasma cai de joelhos e se dissipa. Você venceu a luta!");
            return;
        }

        int phantomDamage = phantomDamage(player);
        player.takeDamage(phantomDamage);

        System.out.println("O fantasma contra ataca e te causa " + phantomDamage + " de dano!");
    }

    private int calculateDamage(Item equippedItem) {
        if (equippedItem == null) return 10; // Soco

        return switch (equippedItem.getName().toLowerCase()) {
            case "espada", "faca" -> (int)(Math.random() * 10) + 5;
            case "pedra" -> 3;
            default -> 10;
        };
    }

    private int phantomDamage(Player player) {
        if (Math.random() > 0.8) {
            return 0;
        }

        return (int)(Math.random() * this.attackPower) + 1;
    }
}
