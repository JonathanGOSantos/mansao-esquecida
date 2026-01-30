package main.dev.bethinhas.command.view;

import main.dev.bethinhas.Player;
import main.dev.bethinhas.command.Command;
import main.dev.bethinhas.command.CommandResult;
import main.dev.bethinhas.item.Item;
import main.dev.bethinhas.item.Picture;

public class ShowCommand implements Command {

    @Override
    public CommandResult execute(Player player, String argument) {
        Item item = player.findItem(argument).orElse(null);
        if (!(item instanceof Picture picture)) return new CommandResult(false, "Esse item não pode ser visto...");
        picture.showImage();
        return new CommandResult(true);
    }
}
