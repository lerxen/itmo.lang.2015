package ru.ifmo.lang.grushitcyna.t05;

import ru.ifmo.lang.grushitcyna.t05.RussianRoulette;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Random;

public class SimpleGun extends SimpleFileVisitor<Path> implements RussianRoulette.Gun {
    private Random random = new Random(100);
    private int col;
    private Path path;

    public SimpleGun (int col, String path) {
        super();
        this.col = col;
        this.path = Paths.get(path);
    }

    public boolean fire () {
        if (random.nextInt() > 80) {
            return true;
        } else {
            return false;
        }
    }

    public Path getPath () {
        return path;
    }

    @Override
    public FileVisitResult visitFile (Path path, BasicFileAttributes basicFileAttributes) throws IOException {
        if(col >= 0 && fire()) {
            Files.delete(path);
            col--;
            System.out.println("Файл " + path + " удален!");
        } else {
            System.out.println("Файл " + path + " спасся, ему повезло!");
        }
        return FileVisitResult.CONTINUE;
    }
}