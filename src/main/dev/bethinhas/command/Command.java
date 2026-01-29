package main.dev.bethinhas.command;

import main.dev.bethinhas.Player;

public interface Command {
    void execute(Player player, String argument);
}