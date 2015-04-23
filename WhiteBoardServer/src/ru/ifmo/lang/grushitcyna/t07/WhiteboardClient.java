package ru.ifmo.lang.grushitcyna.t07;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class WhiteboardClient {
    public static void main (String args[]) {
        if (args.length == 0 || args.length > 2) {
            System.out.println("Использование: ru.ifmo.lang.grushitcyna.t07.WhiteboardClient '/get' || '/post <message>' !");
        } else if (args[0].equals("/get")) {
            try {
                System.out.println(get());
            } catch (Exception e) {
                System.out.println("Произошла неизвестная ошибка!");
                e.printStackTrace();
            }
        } else if (args[0].equals("/post")) {
            try {
                post(args[1]);
            } catch (Exception e) {
                System.out.println("Произошла неизвестная ошибка!");
                e.printStackTrace();
            }
        } else {
            System.out.println("Неверный параметр!");
            System.out.println("Использование: ru.ifmo.lang.grushitcyna.t07.WhiteboardClient '/get' || '/post <message>' !");
        }
    }

    public static String get () throws IOException {
        URL url = new URL("http://localhost:8080/get");
        URLConnection connection = url.openConnection();
        InputStream stream = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        String answ = reader.readLine();
        reader.close();
        return answ;
    }

    public static void post (String s) throws IOException {
        URL url = new URL("http://localhost:8080/post");
        String quest = "POST /post HTTP/1.1\nHOST: localhost:8080\nContent-Lenght: 200\nmessage=" + s;
        URLConnection connection = url.openConnection();
        OutputStream stream = connection.getOutputStream();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(stream));
        bw.write(quest);
        bw.close();
    }
}