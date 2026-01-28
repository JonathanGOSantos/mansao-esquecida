package main.command;

import main.Player;
import main.Save;
import main.Game; // Talvez precise passar o Game ou Mansion
import main.utils.SaveManager;

public class SaveCommand implements Command {
    private final Game game; // Precisamos do contexto do jogo para pegar a Mansão atual

    public SaveCommand(Game game) {
        this.game = game;
    }

    @Override
    public void execute(Player player, String argument) {
        // Cria o pacote de save com o estado ATUAL do jogo
        Save saveState = new Save(game.getMansion(), player);

        SaveManager.saveGame(saveState);
    }
}