package main.dev.bethinhas.phantom;

import main.dev.bethinhas.Player;
import main.dev.bethinhas.item.Item;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

public class FatPhantom extends Phantom {
    @Serial
    private static final long serialVersionUID = 1L;

    private List<Item> itemsForCapture;

    public FatPhantom(String name, String introText, String whoCapture, List<Item> itemsForCapture) {
        super(name, introText, whoCapture);
        this.itemsForCapture = itemsForCapture;
    }

    @Override
    public void interact(Player player) {
        if (this.isCaptured()) throw new RuntimeException("O fantasma já está capturado.");

        System.out.println("Qual item deseja usar para capturar o fantasma?");
        String response = player.getResponse();
        Item item = player.findItem(response);
        if (!this.itemsForCapture.contains(item)) throw new IllegalArgumentException("Esse item não pode ser utilizado para capturar o fantasma.");
        player.removeItem(response);
        this.setCaptured(true);;
    }

    public List<Item> getItemsForCapture() {
        return itemsForCapture;
    }

    public void setItemsForCapture(List<Item> itemsForCapture) {
        this.itemsForCapture = itemsForCapture;
    }
}
