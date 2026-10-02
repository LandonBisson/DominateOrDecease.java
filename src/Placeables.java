import java.awt.*;
import java.util.ArrayList;

abstract class Placeables {
    protected int tileNum;
    protected int timeAlive;
    protected Player createdThis;
    protected static ArrayList<Placeables> allPlace = new ArrayList<Placeables>();
    Placeables(int _tileNum, int _timeAlive){
        tileNum = _tileNum;
        timeAlive = _timeAlive;
    }
    public static void Draw(Graphics2D g){
        for(int i=0;i<allPlace.size();i++){
            allPlace.get(i).draw(g);
        }
    }
    public static void Clear(){
        allPlace.clear();
        for(int i=0;i<7;i++){
            Tile.getTile(i).setFull(false);
        }
    }
    public static void runAllConstant(){
        for (int i=0;i<allPlace.size();i++){
            if(allPlace.get(i).objectRunConstant())
                i--;
        }
    }
    public Player getCreatedThis(){
        return createdThis;
    }
    public static void removeEveryCreated(Player createdThis){
        if (createdThis == null) {
            System.out.println("NullPo in Placebels first if"); //probably dont need this check but better safe than sorry
            return;
        }
        for(int i=0;i<allPlace.size();i++){
            if (allPlace.get(i) != null) {
                System.out.println("NullPo in Placebels second if");
                return;
            }
            if(allPlace.get(i).getCreatedThis().equals(createdThis)) {
                allPlace.remove(allPlace.get(i));
            }
        }
    }
    protected static void addPlaceable(Placeables ptr){
        allPlace.add(ptr);
    }
    protected static void drawOval(Graphics2D g, int _xpos, int _ypos, double rot, double xscale, double yscale, Color color, boolean fill) {
        int ovalwidth = 50;
        g.translate(_xpos, _ypos);
        g.rotate(rot * Math.PI / 180.0);
        g.scale(xscale, yscale);

        if (fill) {
            g.setColor(color);
            g.fillOval(-ovalwidth / 2, -ovalwidth / 2, ovalwidth, ovalwidth);
        } else {
            g.setColor(color);
            g.drawOval(-ovalwidth / 2, -ovalwidth / 2, ovalwidth, ovalwidth);
        }

        g.scale(1.0 / xscale, 1.0 / yscale);
        g.rotate(-rot * Math.PI / 180.0);
        g.translate(-_xpos, -_ypos);

    }
    public static int[] getShieldTile() {
        ArrayList<Shield> Shields = new ArrayList<Shield>();
        for (int i = 0; i < allPlace.size();i++){
            if(allPlace.get(i) instanceof Shield)
                Shields.add((Shield)(allPlace.get(i)));
        }
        int[] allShield = new int[Shields.size()];
        for (int i = 0;i<Shields.size();i++){
            allShield[i] = Shields.get(i).getTileNum();
        }
        return allShield;
     }
    public static ArrayList<Placeables> getArrayPlaceables(){
        return allPlace;
    }
    abstract boolean objectRunConstant();
    // Modifiers
    abstract void draw(Graphics2D g);

    // Modifiers
    void modTileNum(int newTileNum){
        tileNum = newTileNum;
    }
    void timeAlive(int newTimeAlive){
        timeAlive = newTimeAlive;
    }

    // Accessors
    int getTileNum(){return tileNum;}
    int getTimeAlive(){
        return timeAlive;
    }
}
