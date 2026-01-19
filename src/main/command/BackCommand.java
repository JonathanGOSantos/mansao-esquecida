package main.command;

import main.Player;
import main.map.Location;
import main.utils.TextFormatter;

public class BackCommand implements Command {

    @Override
    public void execute(Player player, String argument) {
        Location location = player.back();
        System.out.println(TextFormatter.format(location.getLocationInfo(), player));
    }
}
