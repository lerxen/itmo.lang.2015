package ru.ifmo.grushitcyna;

import java.util.Random;

public class Computer extends Gamer {

    public Computer(String name, NoughtsCrosses symbol) {

        super(name, symbol);
    }

    public void move(Board board) {

        Random random = new Random();
        while (true) {
            int i = random.nextInt(19);
            int j = random.nextInt(19);
            if (board.getSymbol(i, j) == null) {
                board.setSymbol(symbol, i, j);
                return;
            }
        }
    }
}