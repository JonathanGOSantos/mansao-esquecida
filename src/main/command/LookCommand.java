package main.command;

import main.Player;
import main.utils.TextFormatter;

public class LookCommand implements Command {
    @Override
    public void execute(Player player, String argument) {
        System.out.println(TextFormatter.format(player.getCurrentLocation().getLocationInfo(), player));
    }
}
