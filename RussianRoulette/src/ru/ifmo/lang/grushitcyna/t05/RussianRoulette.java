package ru.ifmo.lang.grushitcyna.t05;

import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;

public interface RussianRoulette {
    void play (Gun gun);
    interface Gun {
        boolean fire();
    }
}
