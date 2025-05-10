
import java.awt.*;

//the player wi
public class Player {
    //MAX NUM PLAYERS
    private static int numPlayers; //all players dead and alive
    private static int alivePlayers; //all players alive
    private static Player players[];
    private static Player currentPlayer;
    private static int charWidth;
    private int turnsLeft;
    private int tileNum;
    private int health;

    protected CharacterClass classPtr;
    //Class methods.
    Player(int _tileNum){
        tileNum=_tileNum;
        classPtr=null;
    }
    private void draw(Graphics2D g) {
        Tile.getTileNumPlayer(DominateOrDecease.getCurMouseHex());
        drawOval(g,Board.getTileX(tileNum),Board.getTileY(tileNum),0,1,1,Color.black,true);
    }
    public static void drawOval(Graphics2D g, int _xpos, int _ypos, double rot, double xscale, double yscale, Color color, boolean fill) {
            charWidth = 40;
            g.translate(_xpos,_ypos);
            g.rotate(rot  * Math.PI/180.0);
            g.scale( xscale , yscale );

            if (fill) {
                g.setColor(color);
                g.fillOval(-charWidth/2, -charWidth/2, charWidth, charWidth);
            } else{
                g.setColor(color);
                g.drawOval(-charWidth/2, -charWidth/2, charWidth, charWidth);
            }

            g.scale( 1.0/xscale,1.0/yscale );
            g.rotate(-rot  * Math.PI/180.0);
            g.translate(-_xpos,-_ypos);

        }

    public static void CreatePlayer(int numPlayers){
        setNumPlayers(numPlayers);
        players=new Player [numPlayers];
        for(int i=0;i<numPlayers;i++){
            int startingHex=(int)(Math.random()*7);
            players[i]=new Player(startingHex);
            Tile.getTile(startingHex).setNumPlayers(Tile.getTile(startingHex).getNumPlayers()+1);
        }
        currentPlayer=players[0];
    }
    public static void getNextPlayer() {
        for (int i = 0; i < numPlayers; i++){
            if (currentPlayer == players[i]) {
                if ((i+1)<numPlayers)
                    currentPlayer = players[i + 1];
                else
                    currentPlayer = players[0];
            }
        }
    }

    public void setClass(CharacterClass _class) {
        classPtr=_class;
    }
    public static void setNumPlayers(int _numPlayers) {
        numPlayers=_numPlayers;
    }
    public static void setAlivePlayers(int _alivePlayers) {
            alivePlayers=_alivePlayers;
        }
    public static Player getCurrentPlayer() {
        return currentPlayer;
    }
    public int getTileNum() {
        return tileNum;
    }
    public static Player getPlayer(int whichPlayer) {
        return players[whichPlayer];
    }
    public static Player[] getAllPlayer() {
        return players;
    }
    public void setCurrentTurnsLeft(int _turns){
        turnsLeft=_turns;
    }
    public int getTurnsLeft(){
        return turnsLeft;
    }
    public static int getNumPlayers() {
        return players.length;
    }
    public static int getNumAlivePlayers() {
        int ret = 0;
        for (int i = 0;i<players.length;i++){
            if (players[i] != null)
                ret++;
        }
        return ret;
    }
    public static int getAlivePlayers() {
            return alivePlayers;
        }
    public int getHealth (){
        return health;
    }
    public int currTile(){
        return tileNum;
    }
    public void modifyHealth (int healthChange){
        health += healthChange;
    }
}

