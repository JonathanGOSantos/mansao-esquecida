package main.command;

import main.Player;
import main.map.Location;
import main.utils.TextFormatter;

public class EnterCommand implements Command {
    @Override
    public void execute(Player player, String argument) {
        if (argument.isBlank()) throw new RuntimeException("Ir para onde?");

        Location location = player.getCurrentLocation().getExit(argument);
        if (location.isLocked()) throw new RuntimeException("O cômodo está trancada, para entrar, deve destrancar primeiro.");

        player.go(argument);

        System.out.println(TextFormatter.format(location.getLocationInfo(), player));
    }
}