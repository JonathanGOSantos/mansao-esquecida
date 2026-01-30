package main.dev.bethinhas.command.location;

import main.dev.bethinhas.Player;
import main.dev.bethinhas.command.Command;
import main.dev.bethinhas.command.CommandResult;
import main.dev.bethinhas.utils.TextFormatter;

public class LookCommand implements Command {
    @Override
    public CommandResult execute(Player player, String argument) {
        return new CommandResult(true, TextFormatter.format(player.getCurrentLocation().getLocationInfo(), player));
    }
}
