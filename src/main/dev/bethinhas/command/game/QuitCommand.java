package main.dev.bethinhas.command.game;

import main.dev.bethinhas.Player;
import main.dev.bethinhas.command.Command;
import main.dev.bethinhas.command.CommandResult;

public class QuitCommand implements Command {
    @Override
    public CommandResult execute(Player player, String argument) {
        if (!argument.isBlank()) return new CommandResult(false, "Quer realmente sair?");
        System.out.println("Saindo do jogo...");
        System.exit(0);
        return new CommandResult(true);
    }
}