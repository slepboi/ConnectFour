/** Player Class for Connect 4
 * A superclass that helps create objects
*/
public class Player {
    
    /** Instance variables*/
    private String name;
    private int playerColor;
    
    /** Constructor for Player
     * @param name - player name
     * @param playerColor - player color
    */
    public Player(String name, int playerColor) {
        this.name = name;
        this.playerColor = playerColor;
    }
    
    /** Gets the player name 
     * @return name
    */
    public String getName() {
        return name;
    }
    
    /** Sets the name */
    public void setName(String name) {
        this.name = name;
    }
    
    /** Gets the player color
     * @return playerColor
    */
      public int getPlayerColor() {
        return playerColor;
    }
    
    /** Sets the player color */
    public void setPlayerColor(int playerColor) {
        this.playerColor = playerColor;
    }
    
    /** Gets the piece of RED/YELLOW player
     * @return String of piece
    */
    public String getPiece() {
        if (playerColor == Constants.RED) {
            return "X";  // Red player assigned as "X"
        } else if (playerColor == Constants.YELLOW) {
            return "O";  // Yellow player assigned as "O"
        }
        return "";  // Default case, should not happen if playerColor
    }               // is always valid (properly declared)
}