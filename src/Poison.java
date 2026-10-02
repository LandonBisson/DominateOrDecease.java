import java.awt.*;

class Poison extends Placeables{
    int tileNum;
    int timeAlive;
    boolean STOP;
    Player createdThis;

    Poison(int _tileNum, int _timeAlive, Player createdObj){
        super(_tileNum, _timeAlive);
        tileNum = _tileNum;
        timeAlive = _timeAlive;
        createdThis=createdObj;
        addPlaceable(this);
        Tile.getTile(tileNum).setFull(true);
    }
    public void draw(Graphics2D g){
        if(!STOP) {
            int x = Board.getTileX(tileNum);
            int y = Board.getTileY(tileNum);
            Color transGreen = new Color(0, 255, 0, 50);
            drawOval(g, x, y, 0, 3, 3, transGreen, true);
        }
    }
    boolean objectRunConstant(){
        if (!STOP) {
            if(Player.getCurrentPlayer().equals(createdThis)) {
                timeAlive--;
                if (timeAlive > 0) {
                    CharacterClass.DealDamage(1, Player.getCurrentPlayer());
                } else {
                    STOP = true;
                    Tile.getTile(tileNum).setFull(false);
                    allPlace.remove(this);
                    return true;
                }
            }
            else{
                CharacterClass.DealDamage(1, Player.getCurrentPlayer());
            }
        }
        return false;
    }
}
