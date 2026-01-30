package main.dev.bethinhas.command.player;

import main.dev.bethinhas.Player;
import main.dev.bethinhas.command.Command;
import main.dev.bethinhas.command.CommandResult;
import main.dev.bethinhas.map.Location;
import main.dev.bethinhas.utils.TextFormatter;

import java.util.Optional;

public class EnterCommand implements Command {
    @Override
    public CommandResult execute(Player player, String argument) {
        if (argument.isBlank()) return new CommandResult(false, "Ir para onde?");

        Location from = player.getCurrentLocation();
        Optional<Location> to = from.getExit(argument);
        if (to.isEmpty()) {
            return new CommandResult(false, "Cômodo '" + argument + "' não encontrado.");
        }

        Location location = to.get();
        if (location.isLocked()) {
            return new CommandResult(false, "Você tenta entrar em " + argument + " pôrem o cômodo está trancado.");
        }

        player.setCurrentLocation(location);
        player.registerLocation(location);

        return new CommandResult(true, TextFormatter.format(location.getLocationInfo(), player));
    }
}