package ru.ifmo.lang.grushitcyna.t06;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SimpleGrepUtility implements Grep {

    private BufferedReader bufferedReader;
    private List<String> result = new ArrayList<String>();

    public SimpleGrepUtility(InputStream inputStream) {
        this.bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
    }

    public void close() {
        try {
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("Ошибка I/O при закрытии файла!");
            e.printStackTrace();
        }
    }

    public List<String> findLines(String regex) {
        try {
            String line = bufferedReader.readLine();
            while (line != null) {
                if (line.contains(regex)) {
                    result.add(line);
                }
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            System.out.println("Ошибка I/O при считывании из файла!");
            e.printStackTrace();
        }
        return result;
    }

    public List<String> findParts(String regex) {
        try {
            String line = bufferedReader.readLine();
            while (line != null) {
                if (line.contains(regex)) {
                    result.add(regex);
                }
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            System.out.println("Ошибка I/O при считывании из файла!");
            e.printStackTrace();
        }
        return result;
    }

    public List<String> findInvertMatch(String regex) {
        try {
            String line = bufferedReader.readLine();
            while (line != null) {
                if (!line.contains(regex)) {
                    result.add(line);
                }
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            System.out.println("Ошибка I/O при считывании из файла!");
            e.printStackTrace();
        }
        return result;
    }


    public static void main(String args[]) {

        if (args.length < 2 || args.length > 3) {
            System.out.println("Использование: ru.ifmo.lang.grushitcyna.t06.ru.ifmo.lang.grushitcyna.t06.SimpleGrepUtility [-o] [-v] 'Шаблон' 'Путь к файлу' ");
            return;
        }

        if (args.length == 2) {
            if (!Files.exists(Paths.get(args[1]))) {
                System.out.println("Файла не сушествует!");
                return;
            }
            InputStream inputStream = null;
            try {
                inputStream = new FileInputStream(args[1]);
            } catch (FileNotFoundException e) {
                System.out.println("Файл не найден!");
                e.printStackTrace();
            }
            SimpleGrepUtility simpleGrepUtility = new SimpleGrepUtility(inputStream);
            System.out.println(simpleGrepUtility.findLines(args[0]));
            simpleGrepUtility.close();
        }

        if (args.length == 3) {
            if (!Files.exists(Paths.get(args[2]))) {
                System.out.println("Файл не найден!");
                return;
            }

            if (args[0].equals("-o")) {
                InputStream inputStream = null;
                try {
                    inputStream = new FileInputStream(args[2]);
                } catch (FileNotFoundException e) {
                    System.out.println("Файл не найден!");
                    e.printStackTrace();
                }
                SimpleGrepUtility simpleGrepUtility = new SimpleGrepUtility(inputStream);
                System.out.println(simpleGrepUtility.findParts(args[1]));
                simpleGrepUtility.close();
            } else if (args[0].equals("-v")) {
                InputStream inputStream = null;
                try {
                    inputStream = new FileInputStream(args[2]);
                } catch (FileNotFoundException e) {
                    System.out.println("Файл не найден!");
                    e.printStackTrace();
                }
                SimpleGrepUtility simpleGrepUtility = new SimpleGrepUtility(inputStream);
                System.out.println(simpleGrepUtility.findInvertMatch(args[1]));
                simpleGrepUtility.close();
            } else {
                System.out.println("Использование: ru.ifmo.lang.grushitcyna.t06.ru.ifmo.lang.grushitcyna.t06.SimpleGrepUtility [-o] [-v] 'Шаблон' 'Путь к файлу' ");
            }
        }

    }
}