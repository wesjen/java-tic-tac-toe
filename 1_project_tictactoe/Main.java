import java.io.Console;

/**
 * Main class where the game of Tic-Tac-Toe is played.
 * 
 * Combines Computer- and Board-classes for a game and moves the player.
 * 
 * @author Jenna Westerlund
 */
public class Main {
    static Console c = System.console();
    /**
     * Variables for determining winner, enables user input and determines sizes.
     */
    static int gameBoardSize;
    static String tempUser;
    static String winner = "empty";
    static int markAmount;

    /**
     * Displays texts to keep player updated and makes necessary method calls.
     */
    public static void main(String [] args) {

        // Intro texts
        printer("\nGame of tic-tac-toe\n");
        printer("You will play using numbers to deteminate the place of your marks");
        printer("You will play as X, against computer's O\n");

        String [][] gameBoard = Board.makeBoard();

        printer("\nYou need " + markAmount + " marks in row horizontally, vertically or diagonally to win\n");
        Board.printBoard(gameBoard);

        // Play until winner is known
        while (winner.equals("empty")) {

            // Player goes first
            userMove(gameBoard);
            Board.printBoard(gameBoard);
            checkWinner(gameBoard);

            // Breaks if winner is known
            if (!(winner.equals("empty"))) {
                break;
            }

            // Computer's turn
            Computer.computerMove(gameBoard);
            Board.printBoard(gameBoard);
            checkWinner(gameBoard);
        }
        printer("\nVictory state: " + winner);
    }

    /**
     * Prints given texts and replace typing system.out.
     * 
     * @param t Given String
     */
    public static void printer(String t) {
        System.out.println(t);
    }

    /**
     * Sets value to markAmount variable that determinates marks needed for the win.
     * 
     * @param num Value for markAmount
     */
    public static void setMarkAmount(int num) {
        markAmount = num;
    }

    /**
     * Sets value to gameBoardSize variable, that tells the size of the game.
     * 
     * @param num Value for gameBoardSize
     */
    public static void setGameBoardSize(int num) {
        gameBoardSize = num;
    }

    /**
     * Makes the method call for checking the winner.
     * 
     * @param board 2D-array Game board
     */
    public static void checkWinner(String [][] board) {
        winner = Board.boardChecker(board);
    }

    /**
     * Takes the input from user to determinate mark's place.
     * 
     * Makes sure input is valid.
     * 
     * @param board 2D-array Game Board
     * @return  2D-array Game Board with mark placed
     */
    public static String [][] userMove(String [][] board) {
        int yPos = 0;
        int xPos = 0;
        boolean userCorrectMove = false;

        while (userCorrectMove == false) {
            while (yPos < 1 || yPos > gameBoardSize) {
                printer("\nInsert where you want to place your mark on y-axis");
                tempUser = c.readLine();
                try {
                    yPos = Integer.parseInt(tempUser);
                } catch (Exception e) {
                    printer("Insert proper number");
                }
            }

            while (xPos < 1 || xPos > gameBoardSize) {
                printer("\nInsert where you want to place your mark on x-axis");
                tempUser = c.readLine();
                try {
                    xPos = Integer.parseInt(tempUser);
                } catch (Exception e) {
                    printer("Insert proper number");
                }
            }

            // User inserts numbers from 1-gameBoardSize
            yPos -= 1;
            xPos -= 1;

            if (board[yPos][xPos].equals("[ ]")) {
                userCorrectMove = true;
                board[yPos][xPos] = "[X]";
            } else {
                printer("Place your mark on empty space");
                yPos = 0;
                xPos = 0;
            }
        }
        return board;
    }
}

// End of file   