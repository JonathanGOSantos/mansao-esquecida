package main.dev.bethinhas.command;

import main.dev.bethinhas.Player;
import main.dev.bethinhas.item.Item;
import main.dev.bethinhas.item.Key;
import main.dev.bethinhas.map.Location;

public class UnlockCommand implements Command {
    @Override
    public void execute(Player player, String argument) {

        Location location = player.getCurrentLocation().getExit(argument);
        if (location == null) throw new IllegalArgumentException("Cômodo não está no alcance para ser destrancado ou não existe.");
        if (!location.isLocked()) throw new IllegalArgumentException("Cômodo já destrancado.");

        Item item = player.findItem(location.getKey().getName());
        if (!(item instanceof Key key)) throw new IllegalArgumentException("Você não tem o objeto necessário para destrancar o cômodo.");
        key.unlock();
        System.out.println("O cômodo foi destrancado e agora você pode explorá-lo.");
    }
}
