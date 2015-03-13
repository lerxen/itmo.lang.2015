package ru.ifmo.grushitcyna;

import java.util.Scanner;

public class Man extends Gamer {

    public Man(String name, NoughtsCrosses symbol) {

        super(name, symbol);
    }

    public void move(Board board) {

        Scanner sc = new Scanner(System.in);
        int i;
        int j;
        int size = board.size();

        System.out.println("Write horizontal coordinate");
        i = sc.nextInt();
        System.out.println("Write vertical coordinate");
        j = sc.nextInt();
        if (i < 0 || i > size || j < 0 || j > size) {
            System.out.println("Wrong coordinate, you've lost your move");
            return;
        }

        if (board.getSymbol(i, j) == null) {
            board.setSymbol(symbol, i, j);
        } else {
            System.out.println("Cell isn't empty, you've lost your move");
        }


    }
}
