package main.dev.bethinhas.command;

import main.dev.bethinhas.Player;
import main.dev.bethinhas.item.Item;

import java.util.Set;
import java.util.stream.Collectors;

public class InventoryCommand implements Command {

    @Override
    public void execute(Player player, String argument) {
        Set<Item> inventory = player.getInventory();
        StringBuilder sb = new StringBuilder();

        if (inventory.isEmpty()) {
            sb.append("\tVocê não possui nada no inventário.");
        } else {
            sb.append("\tItens: ");
            String itemsStr = inventory.stream()
                    .map(Item::getName)
                    .collect(Collectors.joining(", ", "", "."));
            sb.append(itemsStr);
        }
        System.out.println(sb);
    }
}
