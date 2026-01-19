package main.phantom;

import main.Player;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

public class FatPhantom extends Phantom {
    @Serial
    private static final long serialVersionUID = 1L;

    private List<String> itemsNamesForCapture;
    private List<String> publicItemsNamesForCapture;

    public FatPhantom(String name, String introText, String whoCapture, List<String> itemsNamesForCapture) {
        super(name, introText, whoCapture);

        this.publicItemsNamesForCapture = itemsNamesForCapture;
        this.itemsNamesForCapture = new ArrayList<>(publicItemsNamesForCapture);

        for (String itemName : publicItemsNamesForCapture) {
            this.itemsNamesForCapture.add(itemName);
            this.itemsNamesForCapture.add(itemName.toLowerCase());
            this.itemsNamesForCapture.add(itemName.toUpperCase());
            this.itemsNamesForCapture.add(itemName.trim());
        }
    }

    public List<String> getItemsNamesForCapture() {
        return itemsNamesForCapture;
    }

    public void setItemsNamesForCapture(List<String> itemsNamesForCapture) {
        this.itemsNamesForCapture = itemsNamesForCapture;
    }

    @Override
    public void interact(Player player) {
        if (this.isCaptured()) throw new RuntimeException("O fantasma já está capturado.");

        System.out.println("Qual item deseja usar para capturar o fantasma?");
        String response = player.getResponse();
        if (!this.itemsNamesForCapture.contains(response)) throw new IllegalArgumentException("Esse item não pode ser utilizado para capturar o fantasma.");
        player.removeItem(response);
        this.setCaptured(true);;
    }
}
