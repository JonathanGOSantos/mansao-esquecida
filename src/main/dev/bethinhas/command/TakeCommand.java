package main.command;

import main.Player;
import main.item.Item;

public class TakeCommand implements Command {

    @Override
    public void execute(Player player, String argument) {
        Item item = player.takeItem(argument);
        System.out.println("O item " + item.getName() + " foi coletado.");
    }
}
