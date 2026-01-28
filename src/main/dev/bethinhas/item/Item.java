package main.item;

import java.io.Serial;
import java.io.Serializable;

public abstract class Item implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String name;
    private String description;

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInfo() {
        return "Nome: " + name + ".\nDescrição: " + description;
    }
}
