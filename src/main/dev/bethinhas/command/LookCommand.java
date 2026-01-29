package main.dev.bethinhas.command;

import main.dev.bethinhas.Player;
import main.dev.bethinhas.utils.TextFormatter;

public class LookCommand implements Command {
    @Override
    public void execute(Player player, String argument) {
        System.out.println(TextFormatter.format(player.getCurrentLocation().getLocationInfo(), player));
    }
}
