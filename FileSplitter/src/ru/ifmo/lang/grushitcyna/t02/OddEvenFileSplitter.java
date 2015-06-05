package ru.ifmo.lang.grushitcyna.t02;

import java.io.*;
import java.util.ArrayList;

public class OddEvenFileSplitter implements FileSplitter {

    public void splitFile(SplitConfig config) {
        File sourceFile = new File(config.getSourceFilePath());
        File oddLinesFile = new File(config.getOddLinesFilePath());
        File evenLinesFile = new File(config.getEvenLinesFilePath());
        ArrayList<String> lines = new ArrayList<String>();
        /**
         * блок считывания из файла в массив
         */
        try {
            oddLinesFile.createNewFile();
            evenLinesFile.createNewFile();
            FileReader fileReader = new FileReader(sourceFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            lines.add(bufferedReader.readLine());
            int i = 0;
            if (lines.get(i) == null) {
                System.out.println("Файл пуст!");
                return;
            }
            while (lines.get(i) != null ) {
                lines.add(bufferedReader.readLine());
                i++;
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println("ошибка ввода - вывода!");
            return;
        }
        /**
         * блок записи в файлы из массива
         */
        try {
            FileWriter oddLinesFileWriter = new FileWriter(oddLinesFile);
            FileWriter evenLinesFileWriter = new FileWriter(evenLinesFile);
            BufferedWriter oddLinesBufferedWriter = new BufferedWriter(oddLinesFileWriter);
            BufferedWriter evenLinesBufferedWriter = new BufferedWriter(evenLinesFileWriter);
            int i;
            for (i = 0; i < lines.size(); i++) {
                if (lines.get(i) != null) {
                    if ((i + 1) % 2 == 0) {
                        evenLinesBufferedWriter.write(lines.get(i));
                        evenLinesBufferedWriter.write("\n");
                    } else {
                        oddLinesBufferedWriter.write(lines.get(i));
                        oddLinesBufferedWriter.write("\n");
                    }
                }
            }
            oddLinesBufferedWriter.close();
            evenLinesBufferedWriter.close();
        } catch (IOException e) {
            System.out.println("ошибка ввода - вывода!");
            return;
        }
    }
    public static void main (String args[]) {
        if (args.length != 3) {
            System.out.println("Использование: OddEvenFileSplitter путь_файла1 путь_файла2 путь_файла3");
            return;
        } else {
            SplitConfig splitConfig = new WayToFile(args[0],args[1],args[2]);
            OddEvenFileSplitter oddEvenFileSplitter = new OddEvenFileSplitter();
            oddEvenFileSplitter.splitFile(splitConfig);
            System.out.println("Выполнено!");
            return;
        }
    }
}