package main.dev.bethinhas;

import main.dev.bethinhas.item.*;
import main.dev.bethinhas.map.*;
import main.dev.bethinhas.phantom.FatPhantom;
import main.dev.bethinhas.phantom.FighterPhantom;
import main.dev.bethinhas.phantom.IntelligentPhantom;
import main.dev.bethinhas.phantom.Phantom;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class Mansion implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Set<Location> locations;
    private Location initialLocation;

    private Set<Item> items;
    private Set<Phantom> phantoms;

    public Mansion() {
        this.locations = new HashSet<>();

        this.init();
    }

    public Mansion(Save save) {
        this.locations = save.getMansion().getLocations();
        this.items = save.getMansion().getItems();
        this.phantoms = save.getMansion().getPhantoms();
    }

    public void init() {
        if (initialLocation != null) return;

        this.generateFirstFloor();
    }

    private void generateFirstFloor() {
        // Cômodos
        var HW001 = new Hallway("Corredor 1", "HW-001", "no corredor principal da mansão");
        var HW002 = new Hallway("Corredor 2", "HW-002", "em um corredor que leva a vários cômodos");
        var HW003 = new Hallway("Corredor 3", "HW-003", "no corredor que te leva a uma escada");
        var RO001 = new Room("Quarto 1", "RO-001", "no quarto do casal Baker");
        var RO002 = new Room("Quarto 2", "RO-002", "no quarto do menino");
        var RO003 = new Room("Quarto 3", "RO-003", "no quarto da menina");
        var KT001 = new Kitchen("Cozinha", "KI-001", "na cozinha do 1º andar");
        var LV001 = new LivingRoom("Sala", "LR-001", "em uma sala bem grande");
        var BT001 = new BathRoom("Banheiro", "BR-001", "no banheiro");
        var ST001 = new Stair("Escada", "ST-001", "de frente a uma escada", true);

        HW001.addExit(LV001.getLocation(), LV001);
        HW001.addExit(RO001.getLocation(), RO001);
        HW001.addExit(HW002.getLocation(), HW002);

        HW002.addExit(LV001.getLocation(), LV001);
        HW002.addExit(RO002.getLocation(), RO002);
        HW002.addExit(KT001.getLocation(), KT001);
        HW002.addExit(HW001.getLocation(), HW001);
        HW002.addExit(HW003.getLocation(), HW003);

        HW003.addExit(RO003.getLocation(), RO003);
        HW003.addExit(ST001.getLocation(), ST001);

        LV001.addExit(BT001.getLocation(), BT001);

        // Itens
        var FO001 = new Food("Alface", "Um alface bem verdinho.");

        var KEY001 = new Key("Chave", "Uma chave como qualquer outra", ST001);

        var PI001 = new Picture("Foto", "Foto da familia", "family.png");

        var MAIL001 = new Mail("Carta 1", "Uma carta", "mail1.txt");
        var MAIL002 = new Mail("Carta 2", "Uma outra carta", "mail2.txt");
        var MAIL003 = new Mail("Carta 3", "Uma outra carta ainda", "mail3.txt");

        KT001.addItem(FO001);
        LV001.addItem(KEY001);
        LV001.addItem(MAIL002);
        BT001.addItem(PI001);
        RO002.addItem(MAIL001);
        RO001.addItem(MAIL003);

        // Fantasmas
        var PH001 = new FatPhantom(
                "Pai",
                "{player} entra em {location} e escuta uma voz dizendo:  \"Estou com fome...\"",
                "Você pode capturar esse fantasma lhe dando comida.",
                List.of(FO001)
        );

        var PH002 = new FighterPhantom(
                "Mãe",
                "{player} entra {location} e escuta uma voz dizendo:  \"Saudades da minha familia...\"",
                "Você pode capturar esse fantasma com uma foto da família.",
                List.of(PI001),
                5,
                0
        );

        var PH003 = new IntelligentPhantom(
                "Filho",
                "{player} entra {location} e escuta uma voz dizendo:  \"Mamãe, mamãe, mamãe...\"",
                "Você pode capturar esse fantasma resolvendo um enigma.",
                """
                        Nós éramos quatro no jantar, mas apenas uma sombra na parede. \
                        
                        Fomos unidos pelo sangue e separados pela dor. \
                        
                        O que é que cresce quanto mais você retira, mas nunca pode ser preenchido novamente?""",
                List.of("Buraco", "Cova", "Túmulo")
        );

        var PH004 = new IntelligentPhantom(
                "Filha",
                "{player} entra {location} e escuta uma voz dizendo:  \"Querido irmão, onde você esta?\"",
                "Você pode capturar esse fantasma resolvendo um enigma.",
                """
                        Eu devoro todas as coisas: pássaros, feras, árvores e flores.\
                        
                        Mastigo o aço e mordo o ferro. Mato reis, arruíno cidades e derrubo montanhas até o pó.\
                        
                        Mesmo assim, não tenho dentes. Quem sou eu?""",
                List.of("O tempo", "tempo")
        );

        KT001.setPhantom(PH001);
        LV001.setPhantom(PH002);
        RO001.setPhantom(PH003);
        RO003.setPhantom(PH004);

        this.locations.addAll(List.of(HW001, HW002, HW003, RO001, RO002, RO003, KT001, LV001, BT001, ST001));

        initialLocation = HW001;
    }

    private void generateSecondFloor(Stair firstFloorStairs) {
    }

    private void generateThirdFloor(Stair secondFloorStairs) {
    }

    public Location getInitialLocation() {
        return initialLocation;
    }

    public Set<Location> getLocations() {
        return locations;
    }

    public Set<Phantom> getPhantoms() {
        return phantoms;
    }

    public void setPhantoms(Set<Phantom> phantoms) {
        this.phantoms = phantoms;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }
}
