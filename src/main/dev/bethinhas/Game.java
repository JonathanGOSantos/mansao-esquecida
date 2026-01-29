package main.dev.bethinhas;

import main.dev.bethinhas.command.Command;
import main.dev.bethinhas.command.CommandRegistry;
import main.dev.bethinhas.command.LoadCommand;
import main.dev.bethinhas.command.SaveCommand;
import main.dev.bethinhas.map.Location;
import main.dev.bethinhas.utils.StoryTeller;
import main.dev.bethinhas.view.Parser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Game {
    private final Parser parser;
    private final CommandRegistry commandRegistry;
    private Mansion mansion;

    private Player currentPlayer;
    private Location startLocation;

    public Game() {
        this.mansion = new Mansion();
        this.parser = new Parser();
        this.commandRegistry = new CommandRegistry();

        commandRegistry.registerCommand(new SaveCommand(this), "salvar", "save");
        commandRegistry.registerCommand(new LoadCommand(this), "carregar", "load");
    }

    public void loadState(Save save) {
        this.mansion = save.getMansion();
        this.currentPlayer = save.getPlayer();

        this.startLocation = this.mansion.getInitialLocation();

        System.out.println("Estado do jogo restaurado.");
        printRoomInfo();
    }

    public void play() {
        this.startLocation = this.mansion.getInitialLocation();
        createPlayer();
        printWelcome();

        while (true) {
            try {
                String fullLine = parser.readLine();
                if (fullLine.isBlank()) continue;

                List<String> parts = new ArrayList<>(List.of(fullLine.split(" ")));
                String commandWord = parts.removeFirst();
                String argument = parts.stream().collect(Collectors.joining(" ", "", ""));
                currentPlayer.setInput(argument);

                Command command = commandRegistry.getCommand(commandWord);

                if (command == null) throw new IllegalArgumentException("Não entendi o que é '" + commandWord + "'...");
                command.execute(currentPlayer, argument);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void createPlayer() {
        System.out.println("Digite o nome do jogador: ");
        String fullLine = parser.readLine();
        if (fullLine.isBlank()) throw new RuntimeException();

        this.currentPlayer = new Player(fullLine);
        this.currentPlayer.setCurrentLocation(this.startLocation);
    }

    private void printWelcome() {
        StoryTeller storyTeller = new StoryTeller();
        storyTeller.playIntro("intro.txt", currentPlayer.getName());

        printRoomInfo();
    }

    private void printRoomInfo() {
        System.out.println(currentPlayer.getCurrentLocation().getLocationInfo());
    }

    public Mansion getMansion() {
        return mansion;
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }
}
