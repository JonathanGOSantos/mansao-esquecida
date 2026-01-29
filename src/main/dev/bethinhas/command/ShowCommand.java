package main.dev.bethinhas.command;

import main.dev.bethinhas.Player;
import main.dev.bethinhas.item.Item;
import main.dev.bethinhas.item.Picture;

public class ShowCommand implements Command {

    @Override
    public void execute(Player player, String argument) {
        Item item = player.findItem(argument);
        if (!(item instanceof Picture picture)) throw new IllegalArgumentException("Esse item não pode ser visto...");
        picture.showImage();
    }
}
