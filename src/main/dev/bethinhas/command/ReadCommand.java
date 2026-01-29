package main.dev.bethinhas.command;

import main.dev.bethinhas.Player;
import main.dev.bethinhas.item.Item;
import main.dev.bethinhas.item.Mail;

public class ReadCommand implements Command {
    @Override
    public void execute(Player player, String argument) {
        Item item = player.findItem(argument);
        if (!(item instanceof Mail mail)) throw new IllegalArgumentException("Esse item não pode ser lido...");
        System.out.println(mail);
    }
}
