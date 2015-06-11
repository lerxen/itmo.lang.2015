package ru.ifmo.lang.grushitcyna.t05;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileBasedRussianRoulette implements RussianRoulette {
    Path path;

    public FileBasedRussianRoulette (Path path) {
        this.path = path;
    }

    public void play (RussianRoulette.Gun gun) {
        try {
            Files.walkFileTree(path , (ModernGun) gun);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main (String args[]) {
        if (args.length != 2) {
            System.out.println("Использование: ru.ifmo.lang.grushitcyna.t05.ru.ifmo.lang.grushitcyna.t05.FileBasedRussianRoulette 'путь к файлу' 'количество выстрелов' ");
            return;
        }
        //ru.ifmo.lang.grushitcyna.t05.RussianRoulette.Gun gun = new ru.ifmo.lang.grushitcyna.t05.SimpleGun(Integer.parseInt(args[1]), args[0]);
        RussianRoulette.Gun gun = new ModernGun(Integer.parseInt(args[1]), args[0]);
        FileBasedRussianRoulette fileBasedRussianRoulette = new FileBasedRussianRoulette(Paths.get(args[0]));
        fileBasedRussianRoulette.play(gun);
    }
}