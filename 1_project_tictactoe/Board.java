import java.io.Console;

/**
 * Board class defines, prints and checks the board. Also checks winner.
 * 
 * @author  Jenna Westerlund
 */

class Board {

    /**
     * Variables, allows user input, defines games sizes and winner.
     */
    static Console c = System.console();
    static int markAmount;
    static int gameBoardSize = 0;
    static String tempUser;
    static String winner = "empty";

    /**
     * Prints, replacing System.out for convenience.
     * @param t String input
     */
    public static void printer(String t) {
        System.out.println(t);
    }

    /**
     * Forwards collected data to other classes.
     */
    public static void setData() {
        Main.setGameBoardSize(gameBoardSize);
        Main.setMarkAmount(markAmount);
        Computer.setGameSize(gameBoardSize);
        Computer.setMarkAmount(markAmount);
    }

    /**
     * Fills the board with defined sign to display empty spaces.
     * 
     * @return  Finished 2D-array board
     */
    public static String[][] makeBoard() {

        // Asks the size of the game board, uses try-catch and temporary variable tempUser to catch wrong inputs
        while (gameBoardSize < 3) {
            printer("Insert game board size, minimum size of 3");
            tempUser = c.readLine();
            try {
                gameBoardSize = Integer.parseInt(tempUser);
            } catch (Exception e) {
                printer("Insert a number");
            }
        }

        // Make the board
        String [][] gameBoardMake = new String[gameBoardSize][gameBoardSize];

        for (int x = 0; x < gameBoardSize; x += 1) {
            for (int x1 = 0; x1 < gameBoardSize; x1 += 1) {
                gameBoardMake[x][x1] = "[ ]";
            }
        }

        markAmountCounter();

        return gameBoardMake;
    }

    /**
     * Counts the amount of marks needed for the win and makes call to forwarding method.
     */
    public static void markAmountCounter() {
        if (gameBoardSize < 10) {
            markAmount = 3;
        } else {
            markAmount = 5;
        }
        setData();
    }

    /**
     * Prints the game board.
     * 
     * If board is bigger than 100 in size, print won't show numbers to avoid confusion with numbers
     * and for extra bingo-mode.
     * 
     * @param gameBoardPrint    2D-array Game board
     */
    public static void printBoard(String [][] gameBoardPrint) {

        if (gameBoardSize <= 100) {
            // Prints the gameboard and the xy-axis numbers
            for (int x = 0; x <= gameBoardSize; x += 1) {
                for (int x1 = 0; x1 <= gameBoardSize; x1 += 1) {
                    if (x == 0) {
                        if (x1 == 0) {
                            System.out.print("    ");
                        } else if (x1 >= 9 && x1 != 0) {
                            System.out.print(x1 + " ");
                        } else {
                            System.out.print(x1 + "  ");
                        }
                    } else if (x1 == 0) {
                        if (x < 10) {
                            System.out.print(" " + x + " ");
                        } else {
                            System.out.print(x + " ");
                        }
                    } else {
                        System.out.print(gameBoardPrint[x-1][x1-1]);
                    }
                }
                printer("");
            }
        } else {
            for (int x = 0; x < gameBoardSize; x += 1) {
                for (int x1 = 0; x1 < gameBoardSize; x1 += 1) {
                    System.out.print(gameBoardPrint[x][x1]);
                }
            }
        }
    }

    /**
     * Checks if anyone has won the game.
     * 
     * Checks horizontal, vertical and diagonal from left-right to right-left.
     * 
     * @param boardCheck 2D-array Game Board
     * @return  Status of the winner variable
     */
    public static String boardChecker(String[][] boardCheck) {

        int xMarkCount = 0;
        int oMarkCount = 0;
        boolean spaceLeft = false;

        for (int x = 0; x < gameBoardSize; x += 1) {
            for (int x1 = 0; x1 < gameBoardSize; x1 += 1) {

                // Checks X
                if (boardCheck[x][x1].equals("[X]")) {

                    // Checks horizontal
                    for (int z = 0; z < markAmount; z += 1) {
                        try {
                            if (boardCheck[x][x1 + z].equals("[X]")) {
                                xMarkCount += 1;
                            } else {
                                xMarkCount = 0;
                            }
                        } catch (Exception e) {}
                    }

                    // Checks victory
                    if (xMarkCount == markAmount && winner.equals("empty")) {
                        winner = "Player";
                    } else {
                        xMarkCount = 0;
                    }

                    // Checks vertical
                    for (int z = 0; z < markAmount; z += 1) {
                        try {
                            if (boardCheck[x + z][x1].equals("[X]")) {
                                xMarkCount += 1;
                            } else {
                                xMarkCount = 0;
                            }
                        } catch (Exception e) {}
                    }

                    // Checks victory
                    if (xMarkCount == markAmount && winner.equals("empty")) {
                        winner = "Player";
                    } else {
                        xMarkCount = 0;
                    }

                    // Checks diagonal left-right
                    for (int z = 0; z < markAmount; z += 1) {
                        try {
                            if (boardCheck[x + z][x1 + z].equals("[X]")) {
                                xMarkCount += 1;
                            } else {
                                xMarkCount = 0;
                            }
                        } catch (Exception e) {}
                    }

                    // Checks victory
                    if (xMarkCount == markAmount && winner.equals("empty")) {
                        winner = "Player";
                    } else {
                        xMarkCount = 0;
                    }

                    // Checks diagonal right-left
                    for (int z = 0; z < markAmount; z += 1) {
                        try {
                            if (boardCheck[x + z][x1 - z].equals("[X]")) {
                                xMarkCount += 1;
                            } else {
                                xMarkCount = 0;
                            }
                        } catch (Exception e) {}
                    }

                    // Checks victory
                    if (xMarkCount == markAmount && winner.equals("empty")) {
                        winner = "Player";
                    } else {
                        xMarkCount = 0;
                    }

                // Checks O
                } else if (boardCheck[x][x1].equals("[O]")) {

                    // Checks horizontal
                    for (int z = 0; z < markAmount; z += 1) {
                        try {
                            if (boardCheck[x][x1 + z].equals("[O]")) {
                                oMarkCount += 1;
                            } else {
                                oMarkCount = 0;
                            }
                        } catch (Exception e) {}
                    }

                    // Checks victory
                    if (oMarkCount == markAmount && winner.equals("empty")) {
                        winner = "Computer";
                    } else {
                        oMarkCount = 0;
                    }

                    // Checks vertical
                    for (int z = 0; z < markAmount; z += 1) {
                        try {
                            if (boardCheck[x + z][x1].equals("[O]")) {
                                oMarkCount += 1;
                            } else {
                                oMarkCount = 0;
                            }
                        } catch (Exception e) {}
                    } 

                    // Checks victory
                    if (oMarkCount == markAmount && winner.equals("empty")) {
                        winner = "Computer";
                    } else {
                        oMarkCount = 0;
                    }

                    // Checks diagonal left-right
                    for (int z = 0; z < markAmount; z += 1) {
                        try {
                            if (boardCheck[x + z][x1 + z].equals("[O]")) {
                                oMarkCount += 1;
                            } else {
                                oMarkCount = 0;
                            }
                        } catch (Exception e) {}
                    }

                    // Checks victory
                    if (oMarkCount == markAmount && winner.equals("empty")) {
                        winner = "Computer";
                    } else {
                        oMarkCount = 0;
                    }

                    // Checks diagonal right-left
                    for (int z = 0; z < markAmount; z += 1) {
                        try {
                            if (boardCheck[x + z][x1 - z].equals("[O]")) {
                                oMarkCount += 1;
                            } else {
                                oMarkCount = 0;
                            }
                        } catch (Exception e) {}
                    }

                    // Checks victory
                    if (oMarkCount == markAmount && winner.equals("empty")) {
                        winner = "Computer";
                    } else {
                        oMarkCount = 0;
                    }

                }
            }
        }

        for (int x = 0; x < gameBoardSize; x += 1) {
            for (int x1 = 0; x1 < gameBoardSize; x1 += 1) {
                if (boardCheck[x][x1].equals("[ ]")) {
                    spaceLeft = true;
                    break;
                }
            }
        }

        if (spaceLeft == false && winner.equals("empty")) {
            winner = "Tie";
        }

        return winner;
    }
}

// End of file
