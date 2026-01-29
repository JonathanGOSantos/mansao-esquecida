package main.dev.bethinhas.command;

import main.dev.bethinhas.Player;
import main.dev.bethinhas.phantom.Phantom;
import main.dev.bethinhas.utils.TextFormatter;

public class CaptureCommand implements Command {
    @Override
    public void execute(Player player, String argument) {
        Phantom phantom = player.getCurrentLocation().getPhantom();

        if (phantom == null) throw new RuntimeException("Não há fantasma para ser capturado nesse cômodo");

        phantom.interact(player);

        if (phantom.isCaptured()) {
            player.addCapturedPhantom(phantom);
            player.getCurrentLocation().setPhantom(null);
            System.out.println("Fantasma capturado com sucesso!");
            System.out.println(TextFormatter.format(player.getCurrentLocation().getLocationInfo(), player));
        }
    }
}
