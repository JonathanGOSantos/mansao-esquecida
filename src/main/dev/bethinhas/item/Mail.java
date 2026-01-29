package main.dev.bethinhas.item;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serial;
import java.util.stream.Collectors;

public class Mail extends Item {
    @Serial
    private static final long serialVersionUID = 1L;

    private String filename;

    public Mail(String name, String description, String filename) {
        super(name, description);
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Override
    public String toString() {
        InputStream is = getClass().getResourceAsStream("/main/resources/mails/" + filename);

        if (is == null) throw new RuntimeException("Erro ao ler o arquivo: " + filename);

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        return reader.lines().collect(Collectors.joining("\n"));
    }
}
