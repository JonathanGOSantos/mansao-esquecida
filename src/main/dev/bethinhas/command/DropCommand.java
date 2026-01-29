package main.dev.bethinhas.command;

import main.dev.bethinhas.Player;
import main.dev.bethinhas.item.Item;

public class DropCommand implements Command {
    @Override
    public void execute(Player player, String argument) {
        Item item = player.dropItem(argument);
        player.getCurrentLocation().addItem(item);
        System.out.println("O item " + item.getName() + " foi solto em " + player.getCurrentLocation().getLocation());
    }
}
