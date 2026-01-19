package main.command;

import main.Player;
import main.Save;
import main.Game;
import main.utils.SaveManager;

public class LoadCommand implements Command {
    private final Game game;

    public LoadCommand(Game game) {
        this.game = game;
    }

    @Override
    public void execute(Player player, String argument) {
        Save loadedSave = SaveManager.loadGame();

        if (loadedSave != null) {
            // AQUI É IMPORTANTE:
            // Precisamos atualizar o jogo atual com os dados carregados
            game.loadState(loadedSave);
        }
    }
}