package main.command;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CommandRegistry {
    private final Map<String, Command> commands = new HashMap<>();

    public CommandRegistry() {
        registerCommand(new EnterCommand(), "entre", "ir", "entrar", "vá", "va");
        registerCommand(new BackCommand(), "voltar", "volte");

        registerCommand(new LookCommand(), "olhar", "ver");

        registerCommand(new TakeCommand(), "pegar", "pegue", "coletar", "colete");
        registerCommand(new DropCommand(), "largar", "solte", "soltar");

        registerCommand(new CaptureCommand(), "capturar");

        registerCommand(new UnlockCommand(), "destrancar");

        registerCommand(new QuitCommand(), "sair");
    }

    public void registerCommand(Command command, String... aliases) {
        for (String alias : aliases) {
            commands.put(alias, command);
        }
    }

    public Command getCommand(String word) {
        return commands.get(word);
    }

    public Set<Command> getCommands(String commandWord) {
        return new HashSet<>(commands.values());
    }
}