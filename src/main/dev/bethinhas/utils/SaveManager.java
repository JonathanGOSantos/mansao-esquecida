package main.utils;

import main.Save;
import java.io.*;

public class SaveManager {

    private static final String SAVE_DIRECTORY = "saves";
    private static final String SAVE_FILE_NAME = "savegame.dat";

    public static void saveGame(Save save) {
        File folder = new File(SAVE_DIRECTORY);

        if (!folder.exists()) {
            boolean created = folder.mkdirs(); // mkdirs() cria todas as subpastas necessárias
            if (created) {
                System.out.println("Pasta de saves criada: " + folder.getAbsolutePath());
            }
        }

        File saveFile = new File(folder, SAVE_FILE_NAME);

        try (FileOutputStream fileOut = new FileOutputStream(saveFile);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {

            objectOut.writeObject(save);
            System.out.println("Jogo salvo em: " + saveFile.getAbsolutePath());

        } catch (IOException e) {
            System.out.println("Erro ao salvar o jogo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static Save loadGame() {
        File saveFile = new File(SAVE_DIRECTORY, SAVE_FILE_NAME);

        try (FileInputStream fileIn = new FileInputStream(saveFile);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {

            Save save = (Save) objectIn.readObject();
            System.out.println("Jogo carregado com sucesso!");
            return save;

        } catch (FileNotFoundException e) {
            System.out.println("Nenhum arquivo de save encontrado em: " + saveFile.getPath());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar o jogo: " + e.getMessage());
        }
        return null;
    }
}