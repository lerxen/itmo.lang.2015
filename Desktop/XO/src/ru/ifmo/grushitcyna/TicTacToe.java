package ru.ifmo.grushitcyna;

public class TicTacToe {
    public static void main(String[] args) {
        GameInterface app = new GameInterface();
        app.setVisible(true);


        Board board1 = new Board(19);

        Gamer comp = new Computer("1", NoughtsCrosses.X);
        Gamer man = new Man("2", NoughtsCrosses.O);

        while (!board1.winner()) {
            comp.move(board1);
            System.out.println("after move comp: ");
            board1.showTable();
            System.out.println();
            man.move(board1);
            System.out.println("after your move: ");
            board1.showTable();
            System.out.println();


        }
    }
}