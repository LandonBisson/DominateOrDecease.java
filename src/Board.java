
import java.awt.*;
import java.awt.Polygon;

public class Board {
    protected final static int NUMHEX = 7;

    protected static int BoardMiddleX;
    protected static int BoardMiddleY;
    private static int[] HexX = null;
    private static int[] HexY = null;
    protected static Polygon Hexagon = null;
    protected static Tile[] allTiles = new Tile[NUMHEX];
    protected static int widthDis = 200;
    protected static int heightDis = 175;
    private static int characterBoxX = 240;
    private static int characterBoxY = 94;
    private static int playerTurnCount;
    private static Player playerSave;

    public static void create() {
        BoardMiddleX = Window.getX(30 * Window.getWidth2() / 100);
        BoardMiddleY = Window.getY(50 * Window.getHeight2() / 100);
        int[] xpoints = new int[]{100, 0, -100, -100, 0, 100, 100};
        int[] ypoints = new int[]{58, 115, 58, -58, -115, -58, 58};
        Hexagon = new Polygon(xpoints, ypoints, xpoints.length);
        ///////////////////
        //
        //Hex X pos and Hex Y pos (centered)
        HexX = new int[]{BoardMiddleX - widthDis / 2, BoardMiddleX + widthDis / 2, BoardMiddleX - widthDis, BoardMiddleX, BoardMiddleX + widthDis, BoardMiddleX - widthDis / 2, BoardMiddleX + widthDis / 2};
        HexY = new int[]{BoardMiddleY - heightDis, BoardMiddleY - heightDis, BoardMiddleY, BoardMiddleY, BoardMiddleY, BoardMiddleY + heightDis, BoardMiddleY + heightDis};
        for (int i = 0; i < NUMHEX; i++) {
            allTiles[i] = new Tile(HexX[i], HexY[i], i);
        }
    }

    public static void Draw(Graphics2D g, DominateOrDecease ptr) {
        Tile.draw(g);
        draw(g);
        //last line of code always
        Images.drawGame(g, ptr);
//        g.fillOval(Window.getX(60 * Window.getWidth2() / 100)+Window.getX(120),Window.getY(Board.getCharacterBoxY()/2)+2*Board.getCharacterBoxY(),5,5);
    }

    private static void draw(Graphics2D g) {

        g.setColor(Color.red);
        //        g.fillOval(Window.getX(Window.getWidth2()/2), Window.getY(Window.getHeight2()/2),10, 10); //center dot
        //left line
        if (Player.getCurrentPlayer() != playerSave){
            playerSave = Player.getCurrentPlayer();
            playerTurnCount++;
        }
        if (playerTurnCount <= Player.getAllPlayer().size())
            Images.drawAttackHere(g,675,675);

        g.drawLine(Window.getX(60 * Window.getWidth2() / 100), Window.getY(0), Window.getX(60 * Window.getWidth2() / 100), Window.getY(Window.getHeight2())); //vertical line of 0.6 width2
        int imageWidth = (int)(Images.getMuteImage(DominateOrDecease.mute).getWidth(DominateOrDecease.frame)/2.5);
        Images.drawMute(g);
        //start is the center of the bottom left square
        int startX = Window.getX(70 * Window.getWidth2() / 100);
        int startY = 327;
        int[] playerBoxX = new int[]{startX, startX + characterBoxX, startX, startX + characterBoxX, startX, startX + characterBoxX};
        int[] playerBoxY = new int[]{startY, startY, startY - characterBoxY, startY - characterBoxY, startY - characterBoxY * 2, startY - characterBoxY * 2};
        //draw top right boxes
        drawPlayerInfo(g, playerBoxX, playerBoxY);
        g.setColor(Color.white);
        g.setFont(new Font("Times New Roman", Font.PLAIN, 40));
        g.drawString(Player.getCurrentPlayer().getCharacterClass().getName() + "'s Turn", Window.getX(playerBoxX[4]), Window.getYNormal(playerBoxX[4] - heightDis) - 60);
        g.drawString("Actions left: " + Player.getCurrentPlayer().getActionsLeft(), Window.getX(30 * Window.getWidth2() / 100), Window.getYNormal(playerBoxX[4] - heightDis) - 60);

        g.setColor(Color.white);
        g.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        if(getTileDescrip(DominateOrDecease.getCurMouseHex())!=null) {
            g.drawString("Hovered Tiles Ability: ", Window.getX(5*Window.getWidth2() / 100), Window.getYNormal(85*Window.getHeight2()/100+75));
            g.setFont(new Font("Times New Roman", Font.PLAIN, 15));
            g.drawString(getTileDescrip(DominateOrDecease.getCurMouseHex()), Window.getX(5*Window.getWidth2() / 100), Window.getYNormal(85*Window.getHeight2()/100+45));
        }
        //dice box
        g.setColor(Color.darkGray.darker());
        g.fillRect(1110-(characterBoxX/2),423-(characterBoxY/2),characterBoxX,characterBoxY*2+17);
        //End Game
        int x=110;
        int y=70;
        g.setColor(Color.green.darker());
        g.fillRect(666-(x/2),778-(y/2),x,y);
        g.setColor(Color.black);
        g.drawRect(666-(x/2),778-(y/2),x,y);
        g.setFont(new Font("Times New Roman", Font.PLAIN, 21));
        g.drawString("Move Mode", 666-(x/2)+3,784);

        g.setColor(Color.red);
        g.fillRect(388-(x/2), 778-(y/2), x, y);
        g.setColor(Color.black);
        g.drawRect(388-(x/2), 778-(y/2), x, y);
        g.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        g.drawString("End Turn", 388-(x/2)+10,787);
    }
    private static String getTileDescrip(int tileNum){
        if(tileNum==0)
            return "75% chance to get the top left 1/4 of the orb.";
        else if(tileNum==1)
            return "75% chance to get the top right 1/4 of the orb.";
        else if(tileNum==2)
            return "Do 1 more damage on your next attack this turn.";
        else if(tileNum==3)
            return "Heal 3 health.";
        else if(tileNum==4)
            return "Take -1 damage from attacks this turn.";
        else if(tileNum==5)
            return "75% chance to get the bottom left 1/4 of the orb.";
        else if(tileNum==6)
            return "75% chance to get the bottom right 1/4 of the orb.";
        return null;
    }

    public static void drawAttacks(Graphics2D g, CharacterClass ptr) {
        Images.drawClassAttackAndAbility(g, ptr, Window.getX(60 * Window.getWidth2() / 100) + characterBoxX / 2, Window.getY(66 * Window.getHeight2() / 100) + characterBoxY + 35, 0, 1, 1);
    }

    private static void drawPlayerInfo(Graphics2D g, int[] x, int[] y) {
        //below is drawing stuff in each chracter class area  **FIX FONT/SIZING**
        int drawActive=0;
        for (int i = 0; i < Player.getAllPlayer().size(); i++) {
            if (Player.getPlayer(i) == null || Player.getCurrentPlayer() == null) {
                continue;
            }
            else if (!(Player.getPlayer(i).equals(Player.getCurrentPlayer()))) {
                drawInfoBox(g, x, y, i, Color.red);
            }
            else{
                drawActive=i;
            }

        }
        drawInfoBox(g, x, y, drawActive, Color.green);

    }

    private static void drawInfoBox(Graphics2D g, int[] x, int[] y, int i, Color color) {
        Player ptr = Player.getPlayer(i);
        drawRectangle(g, x[i], y[i], 0, 1, 1, color, false, characterBoxY);
        //green oval
        Images.drawClassCircle(g, Player.getPlayer(i).getCharacterClass(), x[i] + 25, y[i] - 25, 0.0, 0.3, 0.3);
        g.setColor(Color.green.darker());
        g.fillOval(x[i] + 40, y[i] - 35, 75, 75); //health arcs for left side
        if(Player.getPlayer(i).getMaxHealth()!=Player.getPlayer(i).getHealth()){
            //red arc over the green oval
            g.setColor(Color.red);
            g.fillArc(x[i] + 40, y[i] - 35, 75, 75, 90, (int) (360.0 / Player.getPlayer(i).getMaxHealth()) * Player.getPlayer(i).getHealth() - 360);
        }
       g.setColor(Color.white);
        g.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        if (ptr.getCharacterClass() instanceof Barbarian) {
           Barbarian a = (Barbarian)ptr.getCharacterClass();
           if (a.isRaging()) {
               g.setColor(Color.red);
               g.drawString("Raging", x[i] - 105, y[i] + 6);
           }
       }
       else if (ptr.getCharacterClass() instanceof Rogue) {
           Rogue a = (Rogue)ptr.getCharacterClass();
           if (a.isSneaking()) {
               g.setColor(Color.green.darker());
               g.drawString("Sneaking", x[i] - 110, y[i] + 6);
           }
       }
       else if (ptr.getCharacterClass() instanceof Monk) {
           Monk a = (Monk)ptr.getCharacterClass();
           if (a.isMeditating()) {
               g.setColor(Color.pink);
               g.drawString("Meditating", x[i] - 115, y[i] + 6);
           }
       }
       g.setFont(new Font("Times New Roman", Font.PLAIN, 25));
       g.setColor(Color.white);
       g.drawString("AP: "+ptr.getActionPoints(), x[i] - 105, y[i] + 36);
       g.setFont(new Font("Times New Roman", Font.PLAIN, 30));
       g.drawString(ptr.getCharacterClass().getName(), x[i] - 110, y[i] - 15); //display classname/ap for left side
       g.setColor(Color.white);
       g.setFont(new Font("Times New Roman", Font.PLAIN, 30));
       g.drawString("" + ptr.getHealth(), x[i] + 63, y[i] + 13);
        //orb tracker
        g.setColor(Color.gray.darker());
        g.fillOval(x[i] - 25, y[i] - 10, 50, 50); //orb arcs for left side
        g.setColor(Color.yellow);
        int[] angles = {90,0,180,270};
        for (int j=0; j<Player.getPlayer(i).getOrb().length;j++) {
            if(Player.getPlayer(i).getOrb()[j]){
                g.fillArc(x[i] - 25, y[i] - 10, 50, 50, angles[j], 90);
            }
        }
//        if(Player.getPlayer(i).getOrb()[0]){
//            g.fillArc(x[i] - 25, y[i] - 10, 50, 50, 90, 90);
//        }
//        if(Player.getPlayer(i).getOrb()[1]){
//            g.fillArc(x[i] - 25, y[i] - 10, 50, 50, 0, 90);
//        }
//        if(Player.getPlayer(i).getOrb()[2]){
//            g.fillArc(x[i] - 25, y[i] - 10, 50, 50, 180, 90);
//        }
//        if(Player.getPlayer(i).getOrb()[3]){
//            g.fillArc(x[i] - 25, y[i] - 10, 50, 50, 270, 90);
//        }
    }
    public static void drawOrbTracker(Graphics2D g,int x, int y){
        //drawRectangle(g,x,y,0,1,1,Color.red,false,characterBoxY*2+17);
        g.setColor(Color.gray.darker());
        g.fillOval(x-75/2, y-75/2 , 75, 75); //orb arcs for left side

        g.setColor(Color.yellow);
        if(Player.getCurrentPlayer().getOrb()[0]){
            g.fillArc(x-75/2, y-75/2, 75, 75, 90, 90);
        }
        if(Player.getCurrentPlayer().getOrb()[1]){
            g.fillArc(x-75/2, y-75/2, 75, 75, 0, 90);
        }
        if(Player.getCurrentPlayer().getOrb()[2]){
            g.fillArc(x-75/2, y-75/2, 75, 75, 180, 90);
        }
        if(Player.getCurrentPlayer().getOrb()[3]){
            g.fillArc(x-75/2 , y-75/2, 75, 75, 270, 90);
        }
    }

    public static int findHexagon(int _xpos, int _ypos) {
        if (HexX.length == HexY.length) {
            for (int i = 0; i < HexX.length; i++) {
                if (Hexagon.contains(_xpos - (HexX[i]), _ypos - (HexY[i]))) {
                    return i;
                }
            }
        }
        return -1;
    }

    private static void drawRectangle(Graphics2D g, int _xpos, int _ypos, double rot, double xscale, double yscale, Color color, boolean fill, int charY) {
        g.translate(_xpos, _ypos);
        g.rotate(rot * Math.PI / 180.0);
        g.scale(xscale, yscale);

        if (fill) {
            g.setColor(color);
            g.fillRect(-characterBoxX / 2, -charY / 2, characterBoxX, charY);
        } else {
            g.setColor(color);
            g.drawRect(-characterBoxX / 2, -charY / 2, characterBoxX, charY);
        }

        g.scale(1.0 / xscale, 1.0 / yscale);
        g.rotate(-rot * Math.PI / 180.0);
        g.translate(-_xpos, -_ypos);

    }

    public static int getTileX(int i) {
        return HexX[i];
    }

    public static int getTileY(int i) {
        return HexY[i];
    }

    public static int getCharacterBoxX() {
        return characterBoxX;
    }

    public static int getCharacterBoxY() {
        return characterBoxY;
    }

    public static int getBoardMiddleY() {
        return BoardMiddleY;
    }

    public static int getBoardMiddleX() {
        return BoardMiddleX;
    }

    public static Tile[] getAllTiles() {
        return allTiles;
    }
}