import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    public static final Random RANDOM = new Random();

    public static void main(String[] args) {
        TTTBoard b = new TTTBoard();
        Scanner scanner = new Scanner(System.in);
        b.displayBoard(b.getAvailableCells());
        System.out.println("Enter number who goes first:\n1. Computer (X) / 2. User (O) : ");

        int choice = scanner.nextInt();

        // If person chooses computer goes first... chooses a random point <(1-3),(1-3)> and places the move
        if(choice == b.getPlayerX()) {
            Point p = new Point(RANDOM.nextInt(3), RANDOM.nextInt(3));
            b.placeAMove(p, b.getPlayerX());
            b.displayBoard(b.getAvailableCells());
        }

        while(!b.isGameOver()) {
            boolean moveOK = true;

            do {
                if(!moveOK) {
                    System.out.println("Cell already filled !");
                }
                System.out.println("Your move : ");
                Point userMove = new Point(scanner.nextInt(), scanner.nextInt());
                moveOK = b.placeAMove(userMove, b.getPlayerX());
            } while (!moveOK);

            b.displayBoard(b.getAvailableCells());

            if(b.isGameOver()) {
                break;
            }
            b.minimax(0, b.getPlayerX());
            System.out.println("Computer choose position : " + b.getComputerMove());

            b.placeAMove(b.getComputerMove(), b.getPlayerX());
            b.displayBoard(b.getAvailableCells());
        }
        if(b.hasPlayerWon(TTTBoard.getPlayerX())) {
            System.out.println("You lost!");
        }
        else if(b.hasPlayerWon(TTTBoard.getPlayerO())) {
            System.out.println("You won!");
        }
        else {
            System.out.println("DRAW!");
        }

    }
}
