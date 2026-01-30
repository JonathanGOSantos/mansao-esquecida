package main.dev.bethinhas.command.player;

import main.dev.bethinhas.Player;
import main.dev.bethinhas.command.Command;
import main.dev.bethinhas.command.CommandResult;
import main.dev.bethinhas.map.Location;
import main.dev.bethinhas.utils.TextFormatter;

public class BackCommand implements Command {

    @Override
    public CommandResult execute(Player player, String argument) {
        Location to = player.getLocationPath().pop();

        if (to == null) return new CommandResult(false, "Você já voltou o máximo que dava...");

        return new CommandResult(true, TextFormatter.format(to.getLocationInfo(), player));
    }
}
