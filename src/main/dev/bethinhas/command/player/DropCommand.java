package main.dev.bethinhas.command.player;

import main.dev.bethinhas.Player;
import main.dev.bethinhas.command.Command;
import main.dev.bethinhas.command.CommandResult;
import main.dev.bethinhas.item.Item;
import main.dev.bethinhas.map.Location;

import java.util.Optional;

public class DropCommand implements Command {
    @Override
    public CommandResult execute(Player player, String argument) {
        if (argument.isBlank()) return new CommandResult(false, "Largar o que?");

        Location location = player.getCurrentLocation();
        Optional<Item> item = player.findItem(argument);
        if (item.isEmpty()) {
            return new CommandResult(false, "Item não encontrado.");
        }

        player.removeItem(item.get());
        location.addItem(item.get());

        return new CommandResult(true, "O item " + item.get().getName() + " foi solto em " + player.getCurrentLocation().getLocation());
    }
}
