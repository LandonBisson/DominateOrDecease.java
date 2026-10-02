import java.awt.*;
import java.util.ArrayList;

class Cannon extends Placeables{
    int tileNum;
    int damageDealtPerTurn;
    int timeAlive;
    int level;
    boolean STOP;
    Cannon(int _tileNum, int _timeAlive, int _level, Player _createdThis){
        super(_tileNum, _timeAlive);
        createdThis=_createdThis;
        tileNum = _tileNum;
        timeAlive = _timeAlive+1;
        addPlaceable(this);
        Tile.getTile(tileNum).setFull(true);
        if (_level > 2)
            level = 3;
        else
            level = _level;
    }
    public void draw(Graphics2D g){
        if(!STOP) {
            int x = Board.getTileX(tileNum);
            int y = Board.getTileY(tileNum) + 85;
            Color brown = new Color(88, 57, 39, 255);
//            drawOval(g, x, y, 0, 1, 1, brown, true);
            Images.drawCannon(g,x,y);
            drawOval(g,x+12,y+12,0,0.5,0.5,Color.gray,true);
            g.setColor(brown);
            g.fillArc(x, y, 25, 25, 90, 120*timeAlive);

            g.setColor(Color.white);
            g.setFont(new Font("Times New Roman", Font.PLAIN, 25));
            g.drawString(""+level,x+6,y+20);
        }
    }
    public boolean objectRunConstant(){
        if (!STOP) {
            //if the artificier who created this is there turn
            if(Player.getCurrentPlayer().equals(createdThis)) {
                //time down
                timeAlive--;
                //if its still alive
                if (timeAlive > 0) {
                    int random = (int)(Math.random()*Player.getNumPlayers());
                    while(Player.getPlayer(random).equals(createdThis)){
                        random = (int)(Math.random()*Player.getNumPlayers());
                    }
                    int damage=0;
                    for (int i = 0; i < level; i++)
                         damage += ((int)(Math.random()*4)+1);
                    //cannon shouldnt be able to hit player in a shield Implement here
                    ArrayList<Shield> shieldPlace = new ArrayList<Shield>();
                    //isolate all the shield placables here
                    for(int i=0;i<getArrayPlaceables().size();i++){
                        if(getArrayPlaceables().get(i) instanceof Shield)
                            shieldPlace.add((Shield)(getArrayPlaceables().get(i)));
                    }
                    //make sure we are not shooting a cannonball into on of them.
                    for (int i=0;i<shieldPlace.size();i++){
                        if(shieldPlace.get(i).getTileNum()==Player.getPlayer(random).getTileNum()){
                            System.out.println("Cannot hit player with cannonball in shield.");
                            return false;
                        }
                    }
                    CharacterClass.DealDamage(damage, Player.getPlayer(random));
                    DominateOrDecease.setInfoBoard("Cannon hit "+Player.getPlayer(random).getCharacterClass().getName()+ " for "+damage+" damage." , 2,Window.getHeight2()/2+300);
                } else {
                    STOP = true;
                    Tile.getTile(tileNum).setFull(false);
                    allPlace.remove(this);
                    return true;
                }
            }
        }
        return false;
    }
    // Modifiers
    void modDamageDealtPerTurn(int newDamageDealtPerTurn){
        damageDealtPerTurn = newDamageDealtPerTurn;
    }

    // Accessors
    int getDamageDealtPerTurndamageDealtPerTurn(){
        return damageDealtPerTurn;
    }
}

