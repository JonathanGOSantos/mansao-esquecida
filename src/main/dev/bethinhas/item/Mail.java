package main.item;

import java.io.Serial;

public class Mail extends Item {
    @Serial
    private static final long serialVersionUID = 1L;

    private String content;

    public Mail(String name, String description, String content) {
        super(name, description);
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
