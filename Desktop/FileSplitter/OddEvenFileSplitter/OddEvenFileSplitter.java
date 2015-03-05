import java.io.*;
import java.util.ArrayList;

public class OddEvenFileSplitter implements FileSplitter  {

    public void splitFile(SplitConfig config) {
        File file1 = new File(config.getSourceFilePath());
        File file2 = new File(config.getOddLinesFilePath());
        File file3 = new File(config.getEvenLinesFilePath());
        ArrayList<String> lines = new ArrayList<String>();
        /**
         * блок считывания из файла в массив
         */
        try {
            file2.createNewFile();
            file3.createNewFile();
            FileReader fr = new FileReader(file1);
            BufferedReader br = new BufferedReader(fr);
            lines.add(br.readLine());
            int i = 0;
            if (lines.get(i) == null) {
                System.out.println("Файл пуст!");
                return;
            }
            while (lines.get(i) != null ) {
                lines.add(br.readLine());
                i++;
            }
            br.close();
        } catch (Exception e) {
            System.out.println("ошибка ввода - вывода!");
            return;
        }
        /**
         * блок записи в файлы из массива
         */
        try {
            FileWriter fw2 = new FileWriter(file2);
            FileWriter fw3 = new FileWriter(file3);
            BufferedWriter bw2 = new BufferedWriter(fw2);
            BufferedWriter bw3 = new BufferedWriter(fw3);
            int i;
            for (i = 0; i < lines.size(); i++) {
                if (lines.get(i) != null) {
                    if ((i + 1) % 2 == 0) {
                        bw3.write(lines.get(i));
                        bw3.write("\n");
                    } else {
                        bw2.write(lines.get(i));
                        bw2.write("\n");
                    }
                }
            }
            bw2.close();
            bw3.close();
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

