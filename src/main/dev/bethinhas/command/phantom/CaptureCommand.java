package main.dev.bethinhas.command.phantom;

import main.dev.bethinhas.Player;
import main.dev.bethinhas.command.Command;
import main.dev.bethinhas.command.CommandResult;
import main.dev.bethinhas.map.Location;
import main.dev.bethinhas.phantom.Phantom;
import main.dev.bethinhas.phantom.PhantomInteractResult;
import main.dev.bethinhas.utils.TextFormatter;

public class CaptureCommand implements Command {
    @Override
    public CommandResult execute(Player player, String argument) {
        Location location = player.getCurrentLocation();
        Phantom phantom = location.getPhantom().orElse(null);

        if (phantom == null) return new CommandResult(false, "Não há fantasma nesse cômodo.");

        PhantomInteractResult result = phantom.capture(player);
        if (!result.success()) return new CommandResult(false, result.messages());

        player.addCapturedPhantom(phantom);
        location.setPhantom(null);

        return new CommandResult(true, "Fantasma capturado com sucesso!", TextFormatter.format(location.getLocationInfo(), player));
    }
}
