package main.command;

import main.Player;
import main.map.Location;
import main.utils.TextFormatter;

public class EnterCommand implements Command {
    @Override
    public void execute(Player player, String argument) {
        if (argument.isBlank()) throw new RuntimeException("Ir para onde?");

        Location location = player.go(argument);

        System.out.println(TextFormatter.format(location.getLocationInfo(), player));
    }
}