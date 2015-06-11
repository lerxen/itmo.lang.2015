package ru.ifmo.lang.grushitcyna.t05;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ModernGun extends SimpleFileVisitor<Path> implements RussianRoulette.Gun {
    private int col;
    private Path path;
    private ArrayList<String> allFiles;
    private static GregorianCalendar calendar = new GregorianCalendar();

    public ModernGun(int col, String path) {
        super();
        allFiles = new ArrayList<String>();
        this.col = col;
        this.path = Paths.get(path);
    }

    public boolean fire() {
        if (calendar.get(Calendar.MONTH) == calendar.get(Calendar.APRIL)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public FileVisitResult visitFile(Path path, BasicFileAttributes basicFileAttributes) throws IOException {
        if (col >= 0 && fire()) {
            allFiles.add(path.toString() + " удален!");
            Files.delete(path);
            col--;
            System.out.println("Файл " + path + " удален!");
        } else {
            allFiles.add(path.toString() + "спасся, ему повезло!");
            System.out.println("Файл» " + path + " спасся, ему повезло!");
        }
        return FileVisitResult.CONTINUE;
    }
}