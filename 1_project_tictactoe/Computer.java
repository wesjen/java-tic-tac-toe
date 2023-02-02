/**
 * Determinates the moves for the enemy.
 * 
 * @author Jenna Westerlund
 */
class Computer {

    /**
     * Variables to define size of the game and needed marks.
     */
    static int gameSize;
    static int markAmount;

    /**
     * Makes the call for determinating mark place and put mark on the board.
     * 
     * @param board 2D-array Game Board
     * @return  2D-array board with placed mark
     */
    public static String [][] computerMove(String [][] board) {
        int yPos = 0;
        int xPos = 0;
        boolean compCorrectMove = false;
        int [] places = new int[2];

        while (compCorrectMove == false) {
            
            places = boardCheck(board);
            yPos = places[0];
            xPos = places[1];

            try {
                if (board[yPos][xPos].equals("[ ]")) {
                    compCorrectMove = true;
                    board[yPos][xPos] = "[O]";
                }
            } catch (Exception e) {}
            
        }
        return board;
    }

    /**
     * Sets the gameSize variable.
     * 
     * @param num Value representing game size
     */
    public static void setGameSize(int num) {
        gameSize = num;
    }

    /**
     * Sets the markAmount value.
     * 
     * @param num Value representing marks
     */
    public static void setMarkAmount(int num) {
        markAmount = num;
    }

    /**
     * Check where to put the next mark.
     * 
     * Tries to stop player from winning or then randomly chooses the next place.
     * 
     * @param board 2D-array game board
     * @return  2D-array containing y- and x-places for the next move 
     */
    public static int [] boardCheck(String [][] board) {
        int xMarkCount = 0;
        int marks = markAmount -1;
        boolean indexAcq = false;
        int [] index = new int[2];

        // Checks if X is winning
        for (int x = 0; x < gameSize; x += 1) {
            for(int x1 = 0; x1 < gameSize; x1 += 1) {

                // If wild X is encountered
                if (board[x][x1].equals("[X]")) {

                    // Horizontal check
                    for (int z = 0; z < marks; z += 1) {
                        try {
                            if (board[x][x1 + z].equals("[X]")) {
                                xMarkCount += 1;
                            } else {
                                xMarkCount = 0;
                            }
                        } catch (Exception e) {}
                    }

                    if (xMarkCount == marks) {
                        try {
                            if (board[x][x1-1].equals("[ ]") && indexAcq == false) {
                                index[0] = x;
                                index[1] = x1 - 1;
                                indexAcq = true;
                            }
                        } catch (Exception e) {}

                        try {
                            if (board[x][x1+marks].equals("[ ]") && indexAcq == false) {
                                index[0] = x;
                                index[1] = x1 + marks;
                                indexAcq = true;
                            }
                        } catch (Exception e) {}
                    }

                    xMarkCount = 0;

                    // Vertical check
                    for (int z = 0; z < marks; z += 1) {
                        try {
                            if (board[x+z][x1].equals("[X]")) {
                                xMarkCount += 1;
                            } else {
                                xMarkCount = 0;
                            }
                        } catch (Exception e) {}
                    }

                    if (xMarkCount == marks) {
                        try {
                            if (board[x-1][x1].equals("[ ]") && indexAcq == false) {
                                index[0] = x - 1;
                                index[1] = x1;
                                indexAcq = true;
                            }
                        } catch (Exception e) {}

                        try {
                            if (board[x + marks][x1].equals("[ ]") && indexAcq == false) {
                                index[0] = x + marks;
                                index[1] = x1;
                                indexAcq = true;
                            }
                        } catch (Exception e) {}
                    }

                    xMarkCount = 0;

                    // Diagonal left-right
                    for (int z = 0; z < marks; z += 1) {
                        try {
                            if (board[x+z][x1+z].equals("[X]")) {
                                xMarkCount += 1;
                            } else {
                                xMarkCount = 0;
                            }
                        } catch (Exception e) {}
                    }

                    if (xMarkCount == marks) {
                        try {
                            if (board[x-1][x1-1].equals("[ ]") && indexAcq == false) {
                                index[0] = x - 1;
                                index[1] = x1 - 1;
                                indexAcq = true;
                            }
                        } catch (Exception e) {}

                        try {
                            if (board[x + marks][x1 + marks].equals("[ ]") && indexAcq == false) {
                                index[0] = x + marks;
                                index[1] = x1 + marks;
                                indexAcq = true;
                            }
                        } catch (Exception e) {}
                    }

                    xMarkCount = 0;

                    // Diagonal right-left
                    for (int z = 0; z < marks; z += 1) {
                        try {
                            if (board[x+z][x1-z].equals("[X]")) {
                                xMarkCount += 1;
                            } else {
                                xMarkCount = 0;
                            }
                        } catch (Exception e) {}
                    }

                    if (xMarkCount == marks) {
                        try {
                            if (board[x-1][x1 + 1].equals("[ ]") && indexAcq == false) {
                                index[0] = x - 1;
                                index[1] = x1 + 1;
                                indexAcq = true;
                            }
                        } catch (Exception e) {}

                        try {
                            if (board[x + marks][x1 - marks].equals("[ ]") && indexAcq == false) {
                                index[0] = x + marks;
                                index[1] = x1 - marks;
                                indexAcq = true;
                            }
                        } catch (Exception e) {}
                    }

                    xMarkCount = 0;
                }
            }
        }

        // If nothing else, randomly picks xy places
        if (indexAcq == false) {
            index[0] = (int) (Math.random() * gameSize);
            index[1] = (int) (Math.random() * gameSize);
            indexAcq = true;
        }

        return index;
    }
}

// End of file