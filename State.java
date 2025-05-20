
/**
 * State Class for Connect Four
 * Holds methods that determine game status
*/
public class State {
    
    /** Instance variables */
    private int gameState = Constants.STANDBY;
    private int whoseMove = Constants.RED;
    private RedPlayer redPlayer;
    private YellowPlayer yellowPlayer;
    private int[][] board = new int[Constants.BOARD_ROW][Constants.BOARD_COLUMNS];
    
    /** Checks if there is a winner
     *  @return true/false boolean value
     */
    public boolean isWinner() {
        // Check rows
        for (int row = 0; row < Constants.BOARD_ROW; row++) {
            for (int col = 0; col <= Constants.BOARD_COLUMNS - 4; col++) {
                if (checkConsecutiveFour(row, col, 0, 1)) {
                    return true;
                }
            }
        }

        // Check columns
        for (int row = 0; row <= Constants.BOARD_ROW - 4; row++) {
            for (int col = 0; col < Constants.BOARD_COLUMNS; col++) {
                if (checkConsecutiveFour(row, col, 1, 0)) {
                    return true;
                }
            }
        }

        // Check diagonals
        for (int row = 0; row <= Constants.BOARD_ROW - 4; row++) {
            for (int col = 0; col <= Constants.BOARD_COLUMNS - 4; col++) {
                if (checkConsecutiveFour(row, col, 1, 1) || checkConsecutiveFour(row, col + 3, 1, -1)) {
                    return true;
                }
            }
        }

        return false;
    }
    
    /** Checks for four consecutive pieces
     * @param startRow - the starting row
     * @param startCol - the starting column
     * @param rowIncrement - increases row
     * @param colIncrement - increase column
     * @return true/false boolean value
     */
    private boolean checkConsecutiveFour(int startRow, int startCol, int rowIncrement, int colIncrement) {
        int player = getBoardCell(startRow, startCol);
        if (player == Constants.BLANK) return false;

        for (int i = 1; i < 4; i++) {
            if (getBoardCell(startRow + i * rowIncrement, startCol + i * colIncrement) != player) {
                return false;
            }
        }

        return true;
    }
    
    /** Checks for a tie
     * @return true/false boolean value
     */
    public boolean isTie() {
        for (int row = 0; row < Constants.BOARD_ROW; row++) {
            for (int col = 0; col < Constants.BOARD_COLUMNS; col++) {
                if (getBoardCell(row, col) == Constants.BLANK) return false;
            }
        }
        return true;
    }
    
    /** Getter for gameState
     * @return gameState - state of the Game
    */
    public int getGameState() {
        return gameState;
    }
    
    /** Sets the gameState */
    public void setGameState(int gameState) {
        this.gameState = gameState;
    }
    
    /** Getter for whoseMove
     * @return whoseMove - Red or Yellow's turn
    */
    public int getWhoseMove() {
        return whoseMove;
    }
    
    /** Sets the whoseMove */
    public void setWhoseMove(int whoseMove) {
        this.whoseMove = whoseMove;
    }
    
    /** Getter for the RedPlayer
     * @return redPlayer
    */
    public RedPlayer getRedPlayer() {
        return redPlayer;
    }
    
    /** Sets the redPlayer */
    public void setRedPlayer(RedPlayer redPlayer) {
        this.redPlayer = redPlayer;
    }
    
    /** Getter for the YellowPlayer
     * @return yellowPlayer
    */
    public YellowPlayer getYellowPlayer() {
        return yellowPlayer;
    }
    
    /** Sets the yellowPlayer */
    public void setYellowPlayer(YellowPlayer yellowPlayer) {
        this.yellowPlayer = yellowPlayer;
    }
    
    /** Gets the board cell
     * @param row - the row
     * @param col - the column
     * @return the cell in board[][]
    */
    public int getBoardCell(int row, int col) {
        return board[row][col];
    }
    
    /** Sets the board cell to a new value
     * @param row - the row
     * @param col - the column
     * @param value - new value
    */
    public void setBoardCell(int row, int col, int value) {
        board[row][col] = value;
    }
    
    /** Loop that checks column from top-down for a blank cell
     * @return row value of empty cell (-1 if full column)
    */
    public int findLowestEmptyRow(int col) {
        for (int row = Constants.BOARD_ROW - 1; row >= 0; row--) {
            if (getBoardCell(row, col) == Constants.BLANK) {
                return row;
            }
        }
        return -1;  // Column is full
    }
    
    /** Loop that sets all cells as empty and resets instance variables */
    public void resetGame() {
        for (int i = 0; i < Constants.BOARD_ROW; i++) {
            for (int j = 0; j < Constants.BOARD_COLUMNS; j++) {
                board[i][j] = Constants.BLANK;
            }
        }
        gameState = Constants.STANDBY;
        whoseMove = Constants.RED;
        redPlayer = null;
        yellowPlayer = null;
    }
}