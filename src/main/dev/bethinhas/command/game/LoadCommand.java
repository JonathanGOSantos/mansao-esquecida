package main.dev.bethinhas.command.game;

import main.dev.bethinhas.Player;
import main.dev.bethinhas.Save;
import main.dev.bethinhas.Game;
import main.dev.bethinhas.command.Command;
import main.dev.bethinhas.command.CommandResult;
import main.dev.bethinhas.utils.SaveManager;

public class LoadCommand implements Command {
    private final Game game;

    public LoadCommand(Game game) {
        this.game = game;
    }

    @Override
    public CommandResult execute(Player player, String argument) {
        Save loadedSave = SaveManager.loadGame();

        if (loadedSave != null) {
            game.loadState(loadedSave);
            return new CommandResult(true, "Estado anterior carregado com sucesso.");
        }
        return new CommandResult(false, "Não há um jogo salvo para ser carregado.");
    }
}