package ru.ifmo.grushitcyna;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class GameInterface extends JFrame {
    int i;
    int j;

    public GameInterface() {
        super("TicTacToe");
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(i, 19, 2, 2));
        for (i = 1; i <= 19; i++) {

            panel.add(new JButton(" "));
            panel.add(new JButton(" "));
            panel.add(new JButton(" "));
            panel.add(new JButton(" "));
            panel.add(new JButton(" "));
            panel.add(new JButton(" "));
            panel.add(new JButton(" "));
            panel.add(new JButton(" "));
            panel.add(new JButton(" "));
            panel.add(new JButton(" "));
            panel.add(new JButton(" "));
            panel.add(new JButton(" "));
            panel.add(new JButton(" "));
            panel.add(new JButton(" "));
            panel.add(new JButton(" "));
            panel.add(new JButton(" "));
            panel.add(new JButton(" "));
            panel.add(new JButton(" "));
            panel.add(new JButton(" "));
        }

        setContentPane(panel);
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}