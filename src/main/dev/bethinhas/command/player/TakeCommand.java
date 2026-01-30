package main.dev.bethinhas.command.player;

import main.dev.bethinhas.Player;
import main.dev.bethinhas.command.Command;
import main.dev.bethinhas.command.CommandResult;
import main.dev.bethinhas.item.Item;
import main.dev.bethinhas.map.Location;

import java.util.Optional;

public class TakeCommand implements Command {

    @Override
    public CommandResult execute(Player player, String argument) {
        if (argument.isBlank()) return new CommandResult(false, "Pegar o que?");

        Location location = player.getCurrentLocation();
        Optional<Item> item = location.findItem(argument);
        if (item.isEmpty()) {
            return new CommandResult(false, "Item não encontrado.");
        }

        location.removeItem(item.get());
        player.addItem(item.get());

        return new CommandResult(true, "O item " + item.get().getName() + " foi coletado.");
    }
}
