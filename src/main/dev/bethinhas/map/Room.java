package main.dev.bethinhas.map;

import java.io.Serial;

public class Room extends Location {
    @Serial
    private static final long serialVersionUID = 1L;

    public Room(String location, String code, String description) {
        super(location, code, description);
    }

    public Room(String location, String code, String description, Boolean locked) {
        super(location, code, description, locked);
    }
}
