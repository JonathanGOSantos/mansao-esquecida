package main.map;

import java.io.Serial;

public class BathRoom extends Location {
    @Serial
    private static final long serialVersionUID = 1L;

    public BathRoom(String location, String code, String description) {
        super(location, code, description);
    }

    public BathRoom(String location, String code, String description, Boolean locked) {
        super(location, code, description, locked);
    }
}
