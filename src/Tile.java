

import java.awt.*;
import java.util.ArrayList;
//this will be the base code for the tiles, there will be subclasses for each of the different effects on each
//individual tile
class Tile extends Board {
    private int xpos;
    private int ypos;
    private int numPlayers;
    private static int []playerXpos=null;
    private static int []playerYpos=null;
    private static int spaceDif;
    private boolean Full;
    private int tile;
    private static Color[] tileColors=new Color[]{Color.white,Color.white,Color.white,Color.white,Color.white,Color.white,Color.white};
    Tile(int _xpos,int _ypos, int _tile)
    {
        tile=_tile;
        numPlayers=0;
        xpos=_xpos;
        ypos=_ypos;
        Full=false;
    }
    public static void draw(Graphics2D g) {
        for (int i = 0; i < NUMHEX; i++) {
            drawHexagon(g, allTiles[i].getXpos(), allTiles[i].getYpos(), 0, 1.0, 1.0, tileColors[i],true);
            Images.drawTileImage(g, allTiles[i].getXpos(), allTiles[i].getYpos(),i);
            drawHexagon(g, allTiles[i].getXpos(), allTiles[i].getYpos(), 0, 1.0, 1.0, Color.BLACK,false);
            //need to draw a placeable
            Placeables.Draw(g);
        }
        spaceDif=60;

        //goes through all tiles
        for (int i = 0; i < NUMHEX; i++) {
            updatePlayerPiecePos(i);
            //for the number of plays on this i hex draw them
            for (int p=0;p<Tile.getTileNumPlayer(i);p++) {
                //if there is only one player on this tile
                if (Tile.getTileNumPlayer(i) == 1) {
                    try {
                        Player.drawOval(g, Board.getTileX(i), Board.getTileY(i), 0, 1, 1, Color.black, true);
                        Images.drawClassCircle(g, Tile.getTile(i).getPlayerPtrs()[p].getCharacterClass(), Board.getTileX(i), Board.getTileY(i), 0.0, 0.4, 0.4);
                    }
                    catch(Exception ArrayIndexOutOfBounds){

                    }
                }
                //more than one player
                else if (Tile.getTileNumPlayer(i) > 1) {
                    try {
                        Player.drawOval(g, playerXpos[p], playerYpos[p], 0, 1, 1, Color.black, true);
                        Images.drawClassCircle(g,Tile.getTile(i).getPlayerPtrs()[p].getCharacterClass(), playerXpos[p], playerYpos[p], 0.0, 0.4,0.4);
                    }
                    catch(Exception ArrayIndexOutOfBounds){

                    }
                }
            }
            //no players on that tile
        }
    }
    private static void drawHexagon(Graphics2D g, int _xpos, int _ypos, double rot, double xscale, double yscale,Color color, boolean fill) {
        g.translate(_xpos,_ypos);
        g.rotate(rot  * Math.PI/180.0);
        g.scale( xscale , yscale );
        if(fill){
            g.setColor(color);
            g.fillPolygon(Hexagon);
        }
        else{
            g.setColor(color);
            g.drawPolygon(Hexagon);
        }

        g.scale( 1.0/xscale,1.0/yscale );
        g.rotate(-rot  * Math.PI/180.0);
        g.translate(-_xpos,-_ypos);

    }
    public static int[] getPlayerXpos(int tile){
        updatePlayerPiecePos(tile);
        return playerXpos;
    }
    public static int[] getPlayerYpos(int tile){
            updatePlayerPiecePos(tile);
            return playerYpos;
    }
    private static void updatePlayerPiecePos(int tile){
        playerXpos=new int[] {Board.getTileX(tile) - spaceDif,Board.getTileX(tile),Board.getTileX(tile) + spaceDif,Board.getTileX(tile) - spaceDif,Board.getTileX(tile),Board.getTileX(tile) + spaceDif};
        playerYpos=new int[] {Board.getTileY(tile) - spaceDif/2,Board.getTileY(tile) - spaceDif/2,Board.getTileY(tile) - spaceDif/2,Board.getTileY(tile) + spaceDif/2,Board.getTileY(tile) + spaceDif/2,Board.getTileY(tile) + spaceDif/2};
    }
    public int getXpos() {
        return xpos;
    }
    public int getYpos() {
        return ypos;
    }
    public static int getTileNumPlayer(int i) {
        if(i==-1)
            return -1;
        return allTiles[i].getNumPlayers();
    }
    public static Tile getTile(int i){
        return allTiles[i];
    }
    public void setNumPlayers(int _numPlayers) {
        numPlayers=_numPlayers;
    }
    public Player[] getPlayerPtrs(){
        ArrayList<Player> _players=new ArrayList<Player>();
        for(int t=0;t<Player.getNumPlayers();t++){
            //if the player is one this tile
            if(Player.getPlayer(t).getTileNum()==tile){
                _players.add(Player.getPlayer(t));
            }
        }
        Player[] players=new Player[_players.size()];
        for(int i=0;i<_players.size();i++)
            players[i]=_players.get(i);
        return players;
    }
    public boolean isFull(){return Full;}
    public void setFull(boolean _squareFull){Full=_squareFull;}
    public int getNumPlayers() {
        return numPlayers;
    }
    public static int[] findAdjacent(int tileNum,boolean countSameTile){
        if (tileNum == -1){
            int[] byebye = {-1};
            return byebye;
        }
        int[]ret;
        if(countSameTile)
            ret = new int[4];
        else
            ret = new int[3];
        String test = "201465";
        int[] hexSpotsArray = {2,0,1,4,6,5};
        if (tileNum == 3){
            return hexSpotsArray;
        }
        String temp = ""+tileNum;
        if (test.indexOf(temp) == 0)
            ret[0] = hexSpotsArray[hexSpotsArray.length-1];
        else
            ret[0] = hexSpotsArray[test.indexOf(temp)-1];
        if (test.indexOf(temp) == hexSpotsArray.length-1)
            ret[1] = hexSpotsArray[0];
        else
            ret[1] = hexSpotsArray[test.indexOf(temp)+1];
        //middle and own tile will be apart of adjacent
        ret[2] = 3;
        if(countSameTile)
            ret[3] = tileNum;
        return ret;
    }
    public static void runTileEffects(int tile){
        Player.getCurrentPlayer().setDamageReduction(0);
        if (tile == 0){
           int random = (int)(Math.random()*4);
           if(random != 0)
               Player.getCurrentPlayer().setOrb(0);
           else{
//               Player.getCurrentPlayer().modifyHealth(-2);
//               CharacterClass.tileDamageBoost = 1;
           }
           // add damage to next attack
        }
        if (tile == 1){
           int random = (int)(Math.random()*4);
           if(random != 0)
               Player.getCurrentPlayer().setOrb(1);
           else{
//               Player.getCurrentPlayer().setCurrentActionsLeft(Player.getCurrentPlayer().getActionsLeft() + 1);
//               Player.getCurrentPlayer().modifyHealth(-4);
           }
        }
        if (tile == 2){
            CharacterClass.tileDamageBoost = 1;
        }
        if (tile == 3){
            Player.getCurrentPlayer().modifyHealth(3);
        }
        if (tile == 4){
            Player.getCurrentPlayer().setDamageReduction(1);
        }
        if (tile == 5){
           int random = (int)(Math.random()*4);
           if(random != 0)
               Player.getCurrentPlayer().setOrb(2);
           else{
//               Player.getCurrentPlayer().setCurrentActionsLeft(Player.getCurrentPlayer().getActionsLeft() - 1);
//               Player.getCurrentPlayer().modifyHealth(4);
           }
        }
        if (tile == 6){
           int random = (int)(Math.random()*4);
           if(random != 0)
               Player.getCurrentPlayer().setOrb(3);
           else{
//               Player.getCurrentPlayer().modifyHealth(2);
//               CharacterClass.tileDamageBoost = -1;
           }
        }
    }
    public void previousTileEffectsReset(){

    }
}