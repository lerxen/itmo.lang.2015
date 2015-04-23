package ru.ifmo.lang.grushitcyna.t09;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LineProcessor {

    private static Stream<String> myStream;
    private static List mylines;
    private static Path in;
    private static Path out;


    public static void main (String args[]) {
        if (args.length < 2) {
            System.out.println("Использование: ru.ifmo.lang.grushitcyna.t09.LineProcessor input.txt output.txt [param = regex] ...");
            return;
        } else if (args.length == 2) {
            try {
                if (!Files.exists(Paths.get(args[1]))) {
                    Files.createFile(Paths.get(args[1]));
                }
                pull(Paths.get(args[0]));
                push(Paths.get(args[1]));
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        in = Paths.get(args[0]);
        try {
            pull(Paths.get(args[0]));
            myStream = mylines.stream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        out = Paths.get(args[1]);
        int i = 2;
        while (i < args.length) {
            switch (args[i]) {
                case "sort": {
                    myStream = myStream.sorted();
                    break;
                }
                case "skip": {
                    if (args[i + 1] != null) {
                        myStream = myStream.skip(Integer.parseInt(args[i + 1]));
                        i++;
                        break;
                    } else {
                        System.out.println("Неверный аргумент 1!");
                        return;
                    }
                }
                case "limit": {
                    if (args[i + 1] != null) {
                        myStream = myStream.limit(Integer.parseInt(args[i + 1]));
                        i++;
                        break;
                    } else {
                        System.out.println("Неверный аргумент 2!");
                        return;
                    }
                }
                case "shuffle": {
                    mylines = myStream.collect(Collectors.toList());
                    Collections.shuffle(mylines);
                    myStream = mylines.stream();
                }
                case "distinct": {
                    myStream = myStream.distinct();
                    break;
                }
                case "filter": {
                    if (args[i + 1] != null) {
                        String atr = args[i + 1];
                        myStream = myStream.filter(m -> m.contains(atr));
                        i++;
                        break;
                    } else {
                        System.out.println("Неверный аргумент 3!");
                        return;
                    }
                }
                default: {
                    System.out.println("Неверный аргумент 4!");
                    return;
                }
            }
            i++;
        }
        mylines = myStream.collect(Collectors.toList());
        try {
            push(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void pull (Path path) throws IOException {
        mylines = Files.readAllLines(path);
    }

    private static void push (Path path) throws IOException {
        String result = "";
        for (Object myline : mylines) {
            result = result.concat(myline + "\n");
        }
        Files.write(path, result.getBytes());
    }
}