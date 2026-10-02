import java.awt.*;

class SpikeGrowth extends Placeables{
    int tileNum;
    int timeAlive;
    boolean STOP;
    Player createdThis;
    SpikeGrowth(int _tileNum, int _timeAlive, Player _createdThis){
        super(_tileNum, _timeAlive);
        tileNum = _tileNum;
        timeAlive = _timeAlive;
        createdThis=_createdThis;
        addPlaceable(this);
        Tile.getTile(tileNum).setFull(true);
    }
    public boolean objectRunConstant(){
        if (!STOP) {
            if (Player.getCurrentPlayer().equals(createdThis)) {
                timeAlive--;
                if (timeAlive < 0) {
                    STOP = true;
                    Tile.getTile(tileNum).setFull(false);
                    allPlace.remove(this);
                    return true;
                }
            } else {
                System.out.println("here");
                if (Player.getCurrentPlayer().currTile() == tileNum) {
                    System.out.println("dealt");
                    CharacterClass.DealDamage(2, Player.getCurrentPlayer());
                }
            }
        }
        return false;
    }
    public void draw(Graphics2D g){
        if(!STOP) {
            int x = Board.getTileX(tileNum);
            int y = Board.getTileY(tileNum);
            Color brown = new Color(88, 57, 39, 75);
            //drawOval(g, x, y, 0, 3, 3, brown, true);
            Images.drawSpikes(g,x,y);
        }
    }
}
