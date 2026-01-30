package main.dev.bethinhas.phantom;

import main.dev.bethinhas.Player;
import main.dev.bethinhas.item.Food;
import main.dev.bethinhas.item.Item;
import main.dev.bethinhas.item.Mail;
import main.dev.bethinhas.item.Picture;

import java.io.Serial;
import java.util.List;

public class FighterPhantom extends Phantom {
    @Serial
    private static final long serialVersionUID = 1L;

    private List<Item> itemsForCapture;

    private int health;
    private int maxHealth;
    private int attackPower;

    public FighterPhantom(String name, String introText, String whoCapture, List<Item> itemsForCapture, int maxHealth, int attackPower) {
        super(name, introText, whoCapture);
        this.itemsForCapture = itemsForCapture;
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
    public PhantomInteractResult capture(Player player) {
        if (this.isCaptured()) return new PhantomInteractResult(true, "Fantasma já capturado anteriormente.");

        System.out.println("Qual item deseja utilizar para batalhar com o fantasma?");
        Item equippedItem = player.findItem(player.getResponse()).orElse(null);

        if (equippedItem != null && this.itemsForCapture.contains(equippedItem)) {
            this.health = 0;
            this.setCaptured(true);
            return switch (equippedItem) {
                case Picture picture -> new PhantomInteractResult(true, "Você abre a foto a mostra ao fantasma.", "Ao ver sua familía, " + this.getName() + " cai em profundas lamentações e é capturado.");
                case Mail mail -> new PhantomInteractResult(true, "Não pensei ainda");
                case Food food -> new PhantomInteractResult(true, "Não pensei ainda");
                default -> new PhantomInteractResult(true, "Algo que não estava nos planos...");
            };
        }

        while (true) {
            int playerDamage = calculateDamage(equippedItem);
            if (playerDamage < 5 && Math.random() > 0.7) {
                System.out.println("Você tenta atacar o fantasma mas erra o golpe.\n");
            } else {
                this.health = Math.max(0, this.health - playerDamage);
                System.out.println("Você ataca o fantasma causando " + playerDamage + " de dano! ");
                System.out.println("(Vida do Fantasma: " + this.health + "/" + this.maxHealth + ")\n");
            }

            if (this.health == 0) {
                this.setCaptured(true);
                break;
            }

            int phantomDamage = phantomDamage(player);
            player.takeDamage(phantomDamage);

            if (phantomDamage == 0) {
                System.out.println("O fantasma tenta contra-atacar mas erra o golpe.");
            } else {
                System.out.println("O fantasma contra ataca e te causa " + phantomDamage + " de dano!");
            }

            if (player.getHealth() <= 0) {
                return new PhantomInteractResult(false, "Você morreu...");
            }

            System.out.println("Pressione 'enter' para continuar para o próximo round...");
            try {
                System.in.read();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        if (isCaptured()) {
            return new PhantomInteractResult(true, "O fantasma cai de joelhos e se dissipa. Você venceu a luta!");
        } else {
            return new PhantomInteractResult(false, "Algo deu errado para você...");
        }
    }

    private int calculateDamage(Item equippedItem) {
        if (equippedItem == null) return 1; // Soco

        return switch (equippedItem.getName().toLowerCase()) {
            case "espada", "faca" -> (int)(Math.random() * 10) + 5;
            case "pedra" -> 3;
            default -> 1;
        };
    }

    private int phantomDamage(Player player) {
        if (Math.random() > 0.8) {
            return 0;
        }

        return (int)(Math.random() * this.attackPower) + 1;
    }
}
