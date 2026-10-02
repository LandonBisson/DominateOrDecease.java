import java.awt.*;

class Shield extends Placeables {
    int tileNum;
    int timeAlive;
    boolean STOP;
    Shield(int _tileNum, int _timeAlive,Player _createdThis){
        super(_tileNum, _timeAlive);
        tileNum = _tileNum;
        timeAlive = _timeAlive;
        createdThis=_createdThis;
        addPlaceable(this);
    }
    public void draw(Graphics2D g){
        if(!STOP) {
            int x = Board.getTileX(tileNum);
            int y = Board.getTileY(tileNum);
            Color transBlue = new Color(0, 100, 255, 100);
            drawOval(g, x, y, 0, 3, 3, transBlue, true);
        }
    }
    public boolean objectRunConstant(){

        if (!STOP) {
            if(Player.getCurrentPlayer().equals(createdThis)) {
                timeAlive--;
                if (timeAlive < 0) {
                    STOP = true;
                    Tile.getTile(tileNum).setFull(false);
                    allPlace.remove(this);
                    return true;
                }
            }
        }
        return false;
    }
}
