package main.dev.bethinhas.view;

import java.util.*;

public class Parser {
    private final Scanner reader;

    public Parser()
    {
        reader = new Scanner(System.in);
    }

    public String readLine() {
        System.out.print("> "); // Prompt visual para o usuário saber que pode digitar
        return reader.nextLine();
    }
}