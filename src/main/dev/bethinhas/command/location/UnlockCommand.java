package main.dev.bethinhas.command.location;

import main.dev.bethinhas.Player;
import main.dev.bethinhas.command.Command;
import main.dev.bethinhas.command.CommandResult;
import main.dev.bethinhas.item.Item;
import main.dev.bethinhas.item.Key;
import main.dev.bethinhas.map.Location;

import java.util.Optional;

public class UnlockCommand implements Command {
    @Override
    public CommandResult execute(Player player, String argument) {
        if (argument.isBlank()) return new CommandResult(false, "Destrancar onde?");

        Location from = player.getCurrentLocation();
        Optional<Location> to = from.getExit(argument);

        if (to.isEmpty()) return new CommandResult(false, "Cômodo não encontrado.");

        Location location = to.get();

        if (!location.isLocked()) return new CommandResult(false, "O cômodo não está trancado.");

        Optional<Item> item = player.findItem(location.getKey().getName());
        if (item.isEmpty()) {
            return new CommandResult(false,"Você não possui a chave necessária para destrancar o cômodo.");
        }
        if (!(item.get() instanceof Key key)) {
            throw new RuntimeException("Ops... tipo de item não corresponde a chave.");
        }
        key.unlock();

        return new CommandResult(true, "O cômodo foi destrancado e agora você pode explorá-lo.");
    }
}
