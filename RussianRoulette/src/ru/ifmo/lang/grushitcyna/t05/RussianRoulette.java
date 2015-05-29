package ru.ifmo.lang.grushitcyna.t05;

public interface RussianRoulette {
    void play (Gun gun);
    interface Gun {
        boolean fire();
    }
}