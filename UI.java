import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * UI Class for Connect Four
 * Holds all methods requiring user input
 */
public class UI {

    Scanner scanner;

    public UI() {
        scanner = new Scanner(System.in);         
    }

    /** Determines which player's turn 
     * @param whoseMove - determines RED or YELLOW turn
    */
    public String getXOrO(int whoseMove) {
        if (whoseMove == Constants.RED) {
            return "R";
        } else if (whoseMove == Constants.YELLOW) {
            return "Y";
        } else {
            return " ";
        }
    }
    
    /** Gets the player's name using an if-else statement
     * @param whoseMove - determines RED or YELLOW turn
     * @param state - allows for use of methods in the State Class
     * @return the name of the player based on who's turn
    */
    public String getPlayerName(int whoseMove, State state) {
        return (whoseMove == Constants.RED) ? state.getRedPlayer().getName() : state.getYellowPlayer().getName();
    }
    
    /** Registers legal moves in-bounds & not empty
     * @param state - allows for use of methods in the State Class
     * @param row - the amount of rows in the board
     * @param col - the amount of columns in the board
    */
    public boolean isLegalMove(State state, int row, int col) {
        return 1 <= row && row <= Constants.BOARD_ROW &&
               1 <= col && col <= Constants.BOARD_COLUMNS &&
               state.getBoardCell(row - 1, col - 1) == Constants.BLANK;
    }

    /** Prompts the user for a name
     * @param player - the color of the player (R/Y)
    */
    public String promptForName(String player) {
        System.out.printf(Constants.GET_PLAYER_NAME, player);
        return scanner.next();
    }
    
    /** Gets the column chosen by the user
     * @param whoseMove - determines RED or YELLOW turn
     * @param state - allows for use of methods in the State Class
     * @return col - which column was chosen
    */
    public int getMoveCol(int whoseMove, State state) {
        int col = 0;
        System.out.printf(Constants.GET_COL_MOVE, getXOrO(whoseMove), getPlayerName(whoseMove, state));
        try {
            col = scanner.nextInt();
            if (col <= 0 || col > Constants.BOARD_COLUMNS) {
                printInvalidColumn();
                System.out.println();
                scanner.nextLine();
                col = getMoveCol(whoseMove, state);
            }
        } catch (InputMismatchException error) {
            printInvalidColumn();
            System.out.println();
            scanner.nextLine();
            col = getMoveCol(whoseMove, state);
        }
        return col;
    }
    
    /** Prints the message to inform user of invalid column */
    private void printInvalidColumn() {
        System.out.println(Constants.INVALID_ROW_OR_COLUMN);
    }
    
    /** Determines if user wants to start a new game
     * @return true/false boolean value
    */
    public boolean startNewGame() {
        System.out.println(Constants.START_NEW_GAME);
        String yesOrNo = scanner.next();
        return yesOrNo.equalsIgnoreCase("Y");
    }

    /** Prints a welcome message*/
    public void printWelcome() {
        System.out.println(Constants.TITLE);
    }
    
    /** Prints the whole board with dividers*/
    public void printBoard(State state) {
        for (int i = 0; i < 20; i++) {
            System.out.println();
        }
        System.out.println(Constants.DIVIDER_STRING);
        for (int row = 0; row < Constants.BOARD_ROW; row++) {
            for (int col = 0; col < Constants.BOARD_COLUMNS; col++) {
                System.out.printf("| %s ", getXOrO(state.getBoardCell(row, col)));
            }
            System.out.println("|");
            System.out.println(Constants.DIVIDER_STRING);
        }
    }
    
    /** Prints the message to inform user of invalid row or column*/
    public void printInvalidRowOrColumn() {
        System.out.printf(Constants.INVALID_ROW_OR_COLUMN);
    }
    
    /** Prints the message to inform user of invalid move*/
    public void printInvalidMove(int row, int col) {
        System.out.printf(Constants.INVALID_MOVE_ERROR, row, col);
    }
    
    /** Prints the message of who moved their piece and where */
    public void printMove(State state, int row, int col) {
        System.out.printf(
            Constants.PRINT_MOVE, 
            getXOrO(state.getWhoseMove()), 
            getPlayerName(state.getWhoseMove(), state), 
            row, 
            col
        );
        System.out.println();
    }
    
    /** Prints the winner message*/
    public void printWinner(State state) {
        System.out.printf(
            Constants.WINNER, 
            getXOrO(state.getWhoseMove()), 
            getPlayerName(state.getWhoseMove(), state)
        );
        System.out.println();
    }

    public void printTieGame() {
        System.out.println(Constants.TIE_GAME);
    }
}