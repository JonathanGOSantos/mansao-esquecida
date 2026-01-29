package main.dev.bethinhas.command;

import main.dev.bethinhas.Player;
import main.dev.bethinhas.map.Location;
import main.dev.bethinhas.utils.TextFormatter;

public class BackCommand implements Command {

    @Override
    public void execute(Player player, String argument) {
        Location location = player.back();
        System.out.println(TextFormatter.format(location.getLocationInfo(), player));
    }
}
