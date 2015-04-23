import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;

public class FileBasedRussianRoulette implements RussianRoulette {
    Path path;

    public FileBasedRussianRoulette (Path path) {
        this.path = path;
    }

    public void play (Gun gun) {
        try {
            Files.walkFileTree(path , (SimpleGun) gun);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main (String args[]) {
        if (args.length != 2) {
            System.out.println("Использование: FileBasedRussianRoulette 'путь к файлу' 'количество выстрелов' ");
            return;
        }

        Gun gun = new SimpleGun(Integer.parseInt(args[1]), args[0]);
        FileBasedRussianRoulette fileBasedRussianRoulette = new FileBasedRussianRoulette(Paths.get(args[0]));
        fileBasedRussianRoulette.play(gun);
    }
}
