package main;

import java.io.Serial;
import java.io.Serializable;

public class Save implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Mansion mansion;
    private Player player;

    public Save(Mansion mansion, Player player) {
        this.mansion = mansion;
        this.player = player;
    }

    public Mansion getMansion() {
        return mansion;
    }

    public void setMansion(Mansion mansion) {
        this.mansion = mansion;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
