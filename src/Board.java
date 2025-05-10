
import java.awt.*;
import java.awt.Polygon;

public class Board {
    protected final static int NUMHEX = 7;

    protected static int BoardMiddleX;
    protected static int BoardMiddleY;
    private static int[] HexX=null;
    private static int[] HexY=null;
    protected static Polygon Hexagon = null;
    protected static Tile[] allTiles = new Tile[NUMHEX];
    protected static int widthDis=200;
    protected static int heightDis=175;

    public static void create(){
        BoardMiddleX=Window.getX(Window.getWidth2()/3);
        BoardMiddleY=Window.getY(Window.getHeight2()/2);
        int []xpoints= new int[] {100,0,-100,-100,0,100,100};
        int []ypoints= new int[] {58,115,58,-58,-115,-58,58};
        Hexagon= new Polygon (xpoints, ypoints,xpoints.length);
        ///////////////////
        //
        //Hex X pos and Hex Y pos (centered)
        HexX = new int[]{BoardMiddleX - widthDis/2, BoardMiddleX + widthDis/2, BoardMiddleX - widthDis, BoardMiddleX, BoardMiddleX + widthDis,BoardMiddleX - widthDis/2,BoardMiddleX + widthDis/2};
        HexY = new int[]{BoardMiddleY - heightDis, BoardMiddleY - heightDis, BoardMiddleY, BoardMiddleY, BoardMiddleY,BoardMiddleY+heightDis,BoardMiddleY+heightDis};
        for(int i=0;i<NUMHEX;i++){
            allTiles[i] = new Tile(HexX[i],HexY[i],i);
        }
    }

    public static void Draw(Graphics2D g) {
        Tile.draw(g);
    }
    public static int findHexagon(int _xpos, int _ypos) {
        if (HexX.length==HexY.length) {
            for (int i=0;i<HexX.length;i++){
                if (Hexagon.contains(_xpos - (HexX[i]), _ypos - (HexY[i]))) {
                    return i;
                }
            }
        }
        return -1;
    }
    public static int getTileX(int i){
        return HexX[i];
    }
    public static int getTileY(int i){
        return HexY[i];
    }
    public static int getBoardMiddleY(){
        return BoardMiddleY;
    }
    public static int getBoardMiddleX(){
        return BoardMiddleX;
    }
}



