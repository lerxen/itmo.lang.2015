package ru.ifmo.lang.grushitcyna.t07;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

public class WhiteBoardServer{

    private static String desk = "";

    public static void main (String args[]) throws UnknownHostException {
        try {
            final HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
            server.createContext("/get", new MyStringHandler());
            server.createContext("/post", new MyMessage());
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class MyStringHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            exchange.sendResponseHeaders(200, desk.length());
            final OutputStream outputStream = exchange.getResponseBody();
            outputStream.write(desk.getBytes());
            outputStream.close();
        }
    }

    private static class MyMessage implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            HttpContext hc = exchange.getHttpContext();
            if (exchange.getRequestHeaders().containsKey("message")) {
                System.out.println(true);
            }
            String s = exchange.getRequestURI().getQuery();
            desk = s.substring(8);
            exchange.sendResponseHeaders(200, desk.length());
            final OutputStream os = exchange.getResponseBody();
            os.write(desk.getBytes());
            os.close();
        }
    }
}
