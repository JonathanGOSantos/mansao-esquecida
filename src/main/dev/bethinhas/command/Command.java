package main.command;

import main.Player;

public interface Command {
    void execute(Player player, String argument);
}