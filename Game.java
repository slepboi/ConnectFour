import java.util.Scanner;


/** Game class for Connect 4
  * Keeps the game active until the loop is broken 
 */
public class Game {
    
    /** Instance variables of the UI & State classes*/
    State state = new State();
    UI ui = new UI();
    int row, col;
    
    /** Instance variables of objects representing players*/
    private RedPlayer redPlayer;
    private YellowPlayer yellowPlayer;
    private Player currentPlayer;

    public static void main(String[] args) {
        Game game = new Game();
        game.run();
    }
    
    /** Method that runs a loop with a cycle of the game status*/
    public void run() {
        System.out.println("            __        __   _                            \n" +
                "            \\ \\      / /__| | ___ ___  _ __ ___   ___  \n" +
                "             \\ \\ /\\ / / _ \\ |/ __/ _ \\| '_ ` _ \\ / _ \\ \n" +
                "              \\ V  V /  __/ | (_| (_) | | | | | |  __/ \n" +
                "               \\_/\\_/ \\___|_|\\___\\___/|_| |_| |_|\\___| \n" +
                "                             _____                  \n" +
                "                            |_   _|__                \n" +
                "                              | |/ _ \\              \n" +
                "                              | | (_) |                \n" +
                "                              |_|\\___/       \n" +
                "   ____    ____   _    _   _    _  _____    _____  _______    __ __ \r\n" +
                "  / ___|  |  _ |  | \\ | | | \\ | | | ____|  / ___| |__   __|  | || |  \r\n" +
                " | |      | | ||  |  \\| | |  \\| | |  _|   | |        | |     | || |_ \r\n" +
                " | |___   | |_||  | |\\  | | |\\  | | |___  | |___     | |     |__   _|\r\n" +
                "  \\____|  |____|  |_| \\_| |_| \\_| |_____|  \\____|    |_|        |_|  ");
        
        /** Loop that runs until end with use of if-statements
         *  Calls for methods and values from other classes: State,UI,Constants */
        while (state.getGameState() != Constants.QUIT_PROGRAM) {
            int gameState = state.getGameState(); // Renews value of gameState
            if (gameState == Constants.STANDBY) {
                state.setGameState(Constants.GET_RED_NAME); 
            } else if (gameState == Constants.GET_RED_NAME) {
                redPlayer = new RedPlayer(ui.promptForName("RED")); // Gets and assigns the name of player
                state.setRedPlayer(redPlayer);                      // RED to the redPlayer object
                state.setGameState(Constants.GET_YELLOW_NAME);

            } else if (gameState == Constants.GET_YELLOW_NAME) {
                yellowPlayer = new YellowPlayer(ui.promptForName("YELLOW"));  // Same function with yellowPlayer
                state.setYellowPlayer(yellowPlayer);
                currentPlayer = redPlayer; // Assigns redPlayer to "currentPlayer"; helps determine player turn
                state.setGameState(Constants.GET_RED_MOVE);

            } else if (gameState == Constants.GET_RED_MOVE || gameState == Constants.GET_YELLOW_MOVE) {
                ui.printBoard(state); // Prints the board
                col = ui.getMoveCol(currentPlayer.getPlayerColor(), state);
                row = state.findLowestEmptyRow(col - 1);  
                if (row != -1) {
                    state.setGameState(Constants.MAKE_MOVE);
                } else {
                    System.out.println("Column is full. Please choose another column.");
                }
            } else if (gameState == Constants.MAKE_MOVE) {
                ui.printMove(state, row, col);
                System.out.println();
                state.setBoardCell(row, col - 1, currentPlayer.getPlayerColor()); // Puts the player's piece in the board
                state.setGameState(Constants.CHECK_IF_WINNER);                    // and sees if it was a winning move

            } else if (gameState == Constants.CHECK_IF_WINNER) {
                if (state.isWinner()) {
                    if (currentPlayer == redPlayer) {       // If-statement that changes the game state to a win status
                        state.setGameState(Constants.RED_WINS);
                    } else {
                        state.setGameState(Constants.YELLOW_WINS);
                    }
                } else {
                    state.setGameState(Constants.CHECK_IF_TIE);
                }

            } else if (gameState == Constants.CHECK_IF_TIE) {
                if (state.isTie()) {
                    ui.printTieGame();                      // If-statement that checks if the game is at
                    state.setGameState(Constants.GAME_OVER);// a tie status --> changes to endscreen
                } else {
                    currentPlayer = (currentPlayer == redPlayer) ? yellowPlayer : redPlayer;
                    state.setGameState(currentPlayer == redPlayer ? Constants.GET_RED_MOVE : Constants.GET_YELLOW_MOVE);
                }

            } else if (gameState == Constants.RED_WINS || gameState == Constants.YELLOW_WINS) {
                ui.printBoard(state);                       // If-statement that displays the winning board,
                ui.printWinner(state);                      // the winner, and changes to the endscreen
                state.setGameState(Constants.GAME_OVER);

            } else if (gameState == Constants.GAME_OVER) {
                if (ui.startNewGame()) {                    // Endscreen; resets the game or quits 
                    state.resetGame();                      // the loop based on user input
                    state.setGameState(Constants.STANDBY);
                } else {
                    state.setGameState(Constants.QUIT_PROGRAM);
                }
            }
        }
    }
}