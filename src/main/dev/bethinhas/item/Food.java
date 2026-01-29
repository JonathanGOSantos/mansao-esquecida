package main.dev.bethinhas.item;

import java.io.Serial;

public class Food extends Item {
    @Serial
    private static final long serialVersionUID = 1L;

    public Food(String name, String description) {
        super(name, description);
    }
}
