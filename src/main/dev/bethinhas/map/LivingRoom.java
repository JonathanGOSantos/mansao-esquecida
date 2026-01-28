package main.map;

import java.io.Serial;

public class LivingRoom extends Location {
    @Serial
    private static final long serialVersionUID = 1L;

    public LivingRoom(String location, String code, String description) {
        super(location, code, description);
    }

    public LivingRoom(String location, String code, String description, Boolean locked) {
        super(location, code, description, locked);
    }
}
