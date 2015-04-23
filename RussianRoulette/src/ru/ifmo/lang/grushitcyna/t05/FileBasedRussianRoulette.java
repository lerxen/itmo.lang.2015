package ru.ifmo.lang.grushitcyna.t05;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import ru.ifmo.lang.grushitcyna.t05.RussianRoulette;
import ru.ifmo.lang.grushitcyna.t05.SimpleGun;

public class FileBasedRussianRoulette implements RussianRoulette, ActionListener {
    private Path path;
    private static JFrame frame;
    private static JPanel panel;
    private static JButton button;
    private static JTextField field;
    private static JTextField col;
    private SimpleGun gun;

    public FileBasedRussianRoulette (SimpleGun gun) {
        this.gun = gun;
        createview();
    }

    public void play (Gun gun) {
        try {
            Files.walkFileTree(path , (SimpleGun) gun);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main (String args[]) {
        /*if (args.length != 2) {
            System.out.println("Использование: FileBasedRussianRoulette 'путь к файлу' 'количество выстрелов' ");
            return;
        }*/
        SimpleGun gun = new SimpleGun();
        FileBasedRussianRoulette fileBasedRussianRoulette = new FileBasedRussianRoulette(gun);


        /*Gun gun = new SimpleGun(Integer.parseInt(args[1]), args[0]);
        FileBasedRussianRoulette fileBasedRussianRoulette = new FileBasedRussianRoulette(Paths.get(args[0])); */
        //fileBasedRussianRoulette.play(gun);
    }

    public void createview() {
        frame = new JFrame();
        frame.setMinimumSize(new Dimension(600,400));
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(400,300));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        button = new JButton("Играть!");
        field = new JTextField("Путь к папке!");
        col = new JTextField("Количество выстрелов!");
        frame.setLayout(new GridLayout(3, 0));
        frame.add(field);
        frame.add(col);
        frame.add(button);
        frame.add(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane();
        frame.setVisible(true);
        button.addActionListener(this);
    }

    public static void updatePanel(SimpleGun gun) {
        panel.removeAll();
        for (JLabel label: gun.getAllFiles()) {
            panel.add (label);
        }
        panel.updateUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (field.getText() == null || col.getText() == null) {
            JOptionPane.showMessageDialog(null, "Пустые поля!");
        } else {
            path = Paths.get(field.getText());
            gun.setDir(field.getText());
            gun.setCol(Integer.parseInt(col.getText()));
            play(gun);
            updatePanel(gun);
        }

    }

}