package main.command;

import main.Player;

public class QuitCommand implements Command {
    @Override
    public void execute(Player player, String argument) {
        if (!argument.isBlank()) {
            System.out.println("Quer realmente sair?");
        }

        System.out.println("Saindo do jogo...");
        System.exit(0);
    }
}