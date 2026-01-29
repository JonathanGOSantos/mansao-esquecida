package main.dev.bethinhas.phantom;

import main.dev.bethinhas.Player;

import java.io.Serial;
import java.util.List;
import java.util.stream.Collectors;

public class IntelligentPhantom extends Phantom {
    @Serial
    private static final long serialVersionUID = 1L;

    private String puzzleDescription;
    private List<String> puzzleResponses;

    public IntelligentPhantom(String name, String introText, String whoCapture, String puzzleDescription, List<String> puzzleResponses) {
        super(name, introText, whoCapture);

        this.puzzleDescription = puzzleDescription;
        this.puzzleResponses = puzzleResponses.stream().map(String::toLowerCase).toList();
    }

    public String getPuzzleDescription() {
        return puzzleDescription;
    }

    public void setPuzzleDescription(String puzzleDescription) {
        this.puzzleDescription = puzzleDescription;
    }

    public List<String> getPuzzleResponse() {
        return puzzleResponses;
    }

    public void setPuzzleResponse(List<String> puzzleResponses) {
        this.puzzleResponses = puzzleResponses;
    }

    @Override
    public void interact(Player player) {
        if (this.isCaptured()) throw new RuntimeException("O fantasma já está capturado.");

        System.out.println("Enigma: " + puzzleDescription);
        System.out.println("Qual sua resposta?");
        String response = player.getResponse();

        if (response == null) throw new IllegalArgumentException("A resposta não pode ser nula.");

        if (puzzleResponses.contains(response.toLowerCase())) {
            this.setCaptured(true);
            System.out.println("Parabéns, você acertou! Fantasma capturado com sucesso.");
        } else {
            System.out.println("Você errou e perdeu 1 de vida. Estude mais e tente novamente.");
            player.takeDamage(1);
        }
    }
}
