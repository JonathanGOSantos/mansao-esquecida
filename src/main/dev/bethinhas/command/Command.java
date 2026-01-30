package main.dev.bethinhas.command;

import main.dev.bethinhas.Player;

public interface Command {
    CommandResult execute(Player player, String argument);
}