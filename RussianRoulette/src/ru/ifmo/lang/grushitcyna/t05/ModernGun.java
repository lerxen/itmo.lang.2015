package ru.ifmo.lang.grushitcyna.t05;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

public class ModernGun extends SimpleFileVisitor<Path> implements RussianRoulette.Gun {
    private int col;
    private Path path;
    private ArrayList<JLabel> allFiles;
    private static GregorianCalendar calendar = new GregorianCalendar();

    public ModernGun() {
        super();
        allFiles = new ArrayList<JLabel>();
    }

    public boolean fire() {
        if (calendar.get(Calendar.MONTH) == calendar.get(Calendar.APRIL)) {
            return true;
        } else {
            return false;
        }
    }

    public Path getPath() {
        return path;
    }

    @Override
    public FileVisitResult visitFile(Path path, BasicFileAttributes basicFileAttributes) throws IOException {
        if (col >= 0 && fire()) {
            allFiles.add(new JLabel(path.toString() + " удален!"));
            Files.delete(path);
            col--;
            System.out.println("Файл " + path + " удален!");
        } else {
            allFiles.add(new JLabel(path.toString() + "спасся, ему повезло!"));
            System.out.println("Файл» " + path + " спасся, ему повезло!");
        }
        return FileVisitResult.CONTINUE;
    }

    public ArrayList<JLabel> getAllFiles() {
        return allFiles;
    }

    public void setDir(String dir) {
        path = Paths.get(dir);
    }

    public void setCol(int i) {
        col = i;
    }
}