package main;

import main.command.Command;
import main.command.CommandRegistry;
import main.command.LoadCommand;
import main.command.SaveCommand;
import main.map.Location;
import main.view.Parser;

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

    private void createRooms() {
        this.startLocation = this.mansion.getInitialLocation();
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
