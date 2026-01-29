package main.dev.bethinhas.command;

import main.dev.bethinhas.Player;
import main.dev.bethinhas.map.Location;
import main.dev.bethinhas.utils.TextFormatter;

public class EnterCommand implements Command {
    @Override
    public void execute(Player player, String argument) {
        if (argument.isBlank()) throw new RuntimeException("Ir para onde?");

        Location location = player.getCurrentLocation().getExit(argument);
        if (location == null) throw new RuntimeException("Ir para onde?");
        if (location.isLocked()) throw new RuntimeException("O cômodo está trancada, para entrar, deve destrancar primeiro.");

        player.go(argument);

        System.out.println(TextFormatter.format(location.getLocationInfo(), player));
    }
}