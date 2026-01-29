package main.dev.bethinhas.map;

import java.io.Serial;

public class Kitchen extends Location {
    @Serial
    private static final long serialVersionUID = 1L;

    public Kitchen(String location, String code, String description) {
        super(location, code, description);
    }

    public Kitchen(String location, String code, String description, Boolean locked) {
        super(location, code, description, locked);
    }
}
