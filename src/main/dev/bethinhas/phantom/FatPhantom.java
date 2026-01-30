package main.dev.bethinhas.phantom;

import main.dev.bethinhas.Player;
import main.dev.bethinhas.item.Item;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FatPhantom extends Phantom {
    @Serial
    private static final long serialVersionUID = 1L;

    private List<Item> itemsForCapture;

    public FatPhantom(String name, String introText, String whoCapture, List<Item> itemsForCapture) {
        super(name, introText, whoCapture);
        this.itemsForCapture = itemsForCapture;
    }

    @Override
    public PhantomInteractResult capture(Player player) {
        if (this.isCaptured()) return new PhantomInteractResult(true, "Fantasma já capturado anteriormente.");

        System.out.println("Qual item deseja usar para capturar o fantasma?");
        String response = player.getResponse();

        Optional<Item> item = player.findItem(response);
        if (item.isEmpty()) return new PhantomInteractResult(false, "Você não possui esse item.");

        if (!this.itemsForCapture.contains(item.get())) return new PhantomInteractResult(false, "Esse item não pode ser utilizado para capturar o fantasma.");
        player.removeItem(item.get());
        this.setCaptured(true);

        return new PhantomInteractResult(true, "O fantasma foi atraído pelo item e ao utilizá-lo foi consumido.");
    }

    public List<Item> getItemsForCapture() {
        return itemsForCapture;
    }

    public void setItemsForCapture(List<Item> itemsForCapture) {
        this.itemsForCapture = itemsForCapture;
    }
}
