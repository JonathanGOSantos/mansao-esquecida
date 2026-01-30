package main.dev.bethinhas.command.view;

import main.dev.bethinhas.Player;
import main.dev.bethinhas.command.Command;
import main.dev.bethinhas.command.CommandResult;
import main.dev.bethinhas.item.Item;
import main.dev.bethinhas.item.Mail;

public class ReadCommand implements Command {
    @Override
    public CommandResult execute(Player player, String argument) {
        Item item = player.findItem(argument).orElse(null);

        if (!(item instanceof Mail mail)) return new CommandResult(false, "Esse item não pode ser lido...");

        return new CommandResult(true, mail.toString());
    }
}
