
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

//the player wi
public class Player {
    //MAX NUM PLAYERS
    private static int numPlayers; //all players alive
    private static ArrayList<Player> players = new ArrayList<Player>();
    private static Player currentPlayer;
    private static int MAXplayers;
    private static int charWidth;
    private int actionsLeft;
    private int tileNum;
    private int ActionPoints;
    private int MaxHealth=30;
    private int health;
    private boolean[] orb = new boolean[4];
    private int damageReduction;


    private CharacterClass classPtr;

    //Class methods.
    Player(int _tileNum, CharacterClass ptr) {
        actionsLeft=2;
        classPtr = ptr;
        tileNum = _tileNum;
        health = MaxHealth;
        Arrays.fill(orb, false);
        MAXplayers=players.size();
    }

    public static void drawOval(Graphics2D g, int _xpos, int _ypos, double rot, double xscale, double yscale, Color color, boolean fill) {
        charWidth = 50;
        g.translate(_xpos, _ypos);
        g.rotate(rot * Math.PI / 180.0);
        g.scale(xscale, yscale);

        if (fill) {
            g.setColor(color);
            g.fillOval(-charWidth / 2, -charWidth / 2, charWidth, charWidth);
        } else {
            g.setColor(color);
            g.drawOval(-charWidth / 2, -charWidth / 2, charWidth, charWidth);
        }

        g.scale(1.0 / xscale, 1.0 / yscale);
        g.rotate(-rot * Math.PI / 180.0);
        g.translate(-_xpos, -_ypos);

    }
    //only used for the start of the game
    public static void CreatePlayer(int numPlayers) {
        //for the start of the game
        if(numPlayers<2)
            numPlayers=2;
        //clear arraylist
        players.clear();
        //set al tiles to 0
        for (int i = 0; i < 7; i++)
            Tile.getTile(i).setNumPlayers(0);
        ///above is set things to 0
        //change num players to the needed value
        setNumPlayers(numPlayers);
        for (int i = 0; i < numPlayers; i++) {
            //random hexes
            int startingHex = (int) (Math.random() * 7);
            //add players with no classes yet
            players.add(i, new Player(startingHex, null));
            //add to the random hexes
            Tile.getTile(startingHex).setNumPlayers(Tile.getTile(startingHex).getNumPlayers() + 1);
        }
        currentPlayer = players.get(0);
        currentPlayer.addActionPoints(1);
        Tile.runTileEffects(Player.getCurrentPlayer().currTile());
    }

    public static void getNextPlayer() {
        for (int i = 0; i < numPlayers; i++) {
            //pointing to same thing
            if (currentPlayer.equals(players.get(i))) {
                if ((i + 1) < numPlayers) {
                    currentPlayer = players.get(i + 1);
                    break;
                }
                else {
                    currentPlayer = players.get(0);
                    break;
                }
            }
        }
        //always start two adtions
        currentPlayer.resetActionsLeft(2);
    }

    public void Move(int movingTo, boolean ignoreAdjacent) {
        //CurMouseHex, numplayers in Tile, getTile, findAdjacent
        for (int a : Placeables.getShieldTile()){
            if (movingTo == a){
                DominateOrDecease.setInfoBoard("Cannot move to an active Shield.",1,Window.getHeight2()/2+200);
                return;
            }
            else if (getCurrentPlayer().getTileNum() == a){
                DominateOrDecease.setInfoBoard("Cannot move out of an active Shield.",1.5,Window.getHeight2()/2+200);
                return;
            }
        }
        if (actionsLeft != 0) {
            int[] adj=Tile.findAdjacent(tileNum,false);
            //going through adjacent tiles
            for(int i=0;i<adj.length;i++){
                //if they can move there
                if(adj[i]==movingTo || ignoreAdjacent){
                    
                    //subtracting one form curtile
                    Tile.getTile(tileNum).setNumPlayers(Tile.getTile(tileNum).getNumPlayers() - 1);

                    tileNum = movingTo;
                    Tile.getTile(tileNum).setNumPlayers(Tile.getTile(tileNum).getNumPlayers() + 1);

                    actionsLeft--;
                    break;
                }
             }
        }

    }
    public void MoveAnywhere(int movingTo, boolean useActionPoint) {
            //CurMouseHex, numplayers in Tile, getTile, findAdjacent
            for (int a : Placeables.getShieldTile()){
                if (movingTo == a){
                    DominateOrDecease.setInfoBoard("Can not move to an active Shield.",1,Window.getHeight2()/2);
                    if(useActionPoint) {
                        Player.getCurrentPlayer().addActionPoints(1);
                        Player.getCurrentPlayer().setCurrentActionsLeft(Player.getCurrentPlayer().getActionsLeft() + 1);
                    }
                    return;
                }
                else if (getCurrentPlayer().getTileNum() == a){
                    DominateOrDecease.setInfoBoard("Can not move out of an active Shield.",1.5,Window.getHeight2()/2);
                    if(useActionPoint) {
                        Player.getCurrentPlayer().addActionPoints(1);
                        Player.getCurrentPlayer().setCurrentActionsLeft(Player.getCurrentPlayer().getActionsLeft() + 1);
                    }
                    return;
                }

            }
            Tile.getTile(tileNum).setNumPlayers(Tile.getTile(tileNum).getNumPlayers() - 1);

            tileNum = movingTo;
            Tile.getTile(tileNum).setNumPlayers(Tile.getTile(tileNum).getNumPlayers() + 1);
            if(useActionPoint)
                ActionPoints--;
        }
    public void addActionPoints(int added){
        ActionPoints+=added;
    }

    public void resetActionsLeft(int i){
        actionsLeft=i;
    }
    public void setClass(CharacterClass _class) {
        classPtr=_class;
    }
    public static void setNumPlayers(int _numPlayers) {
        numPlayers=_numPlayers;
    }
    public void setCurrentActionsLeft(int _actions){
        actionsLeft=_actions;
    }
    ///////
    public static Player getCurrentPlayer() {
        return currentPlayer;
    }
    public int getTileNum() {
        return tileNum;
    }
    public static Player getPlayer(int whichPlayer) {
        if (whichPlayer >= 0 && whichPlayer < players.size())
            return players.get(whichPlayer);
        else
            return null;
    }
    public static ArrayList<Player> getAllPlayer() {
        return players;
    }
    public int getActionsLeft(){
        return actionsLeft;
    }
    public static int getNumPlayers() {
        return numPlayers;
    }
    public static int getNumAlivePlayers() {
        int ret = 0;
        for (int i = 0;i<players.size();i++){
            if (players.get(i) != null)
                ret++;
        }
        return ret;
    }
    public CharacterClass getCharacterClass() {
        return classPtr;
    }
    public int getHealth (){
        return health;
    }
    public int getActionPoints(){
        return ActionPoints;
    }
    public int getMaxHealth (){
        return MaxHealth;
    }
    public void setMaxHealth (int total){
        MaxHealth=total;
    }
    public boolean[] getOrb (){
            return orb;
    }
    public void setOrb (int val){
        orb[val] = true;
    }
    public boolean canSuper(){
        int count = 0;
        for (boolean a : orb){
            if (a)
                count++;
        }
        if (count == 4)
            return true;
        return false;
    }
    public int checkOrbs(){
        int count = 0;
        for(boolean a : orb){
        if (a)
            count ++;
        }
        return (count);
    }
    public static void addPlayer(int added){
        if(Player.getNumPlayers()<6) {
            for (int i = numPlayers; i < numPlayers+added; i++) {
                int startingHex = (int) (Math.random() * 7);
                players.add(i,new Player(startingHex, null));
                Tile.getTile(startingHex).setNumPlayers(Tile.getTile(startingHex).getNumPlayers() + 1);
            }
            numPlayers+=added;
        }
    }
    public static void subtractPlayer(int subtract){
        if(Player.getNumPlayers()>2) {
            int endpoint = numPlayers - subtract;
            for (int i = numPlayers - 1; i >= endpoint; i--) {
                Tile.getTile(players.get(i).currTile()).setNumPlayers(Tile.getTile(players.get(i).currTile()).getNumPlayers() - 1);
                players.remove(i);
            }
            numPlayers = endpoint;
        }
    }
    public int currTile(){
        return tileNum;
    }
    public int getDamageReduction(){
        return damageReduction;
    }
    public void setDamageReduction(int mod){
        damageReduction = mod;
    }
    public void modifyHealth (int healthChange){
        //healing
        if (healthChange>=0){
            health += healthChange;
            if (health >= MaxHealth)
                health = MaxHealth;
            return;
        }
        //you took damage lets see if you have a damage reduction, and finalize health
        health += healthChange + getDamageReduction();

        // if you died
        if (health <= 0) {
            numPlayers--;

            //remove placeables
            Placeables.removeEveryCreated(Player.getCurrentPlayer());
            Tile.getTile(tileNum).setNumPlayers(Tile.getTile(tileNum).getNumPlayers() - 1);
            //remove stuff than set gameover
            if(numPlayers<=1) {
                DominateOrDecease.setGameOver(true);
                return;
            }
            //if the game isnt over keep moving on
            players.remove(this);
            DominateOrDecease.getNextPlayer();
        }
    }
    public static int hitCheck(Player ptr, int damage){
        if(ptr==null)
            return damage;
        if (ptr.getCharacterClass().getName().equals("Barbarian")){
            Barbarian barb = (Barbarian) ptr.getCharacterClass();
            if (barb.isRaging()) {
                //dividing damage by the rage health mod
                DominateOrDecease.setInfoBoard("Barbarian took half damage.",1.5,Window.getHeight2()/2+300);
                return (int)(damage / barb.getRage()[1]);
            }
        }
        else if (ptr.getCharacterClass().getName().equals("Monk")){
            Monk monk = (Monk) ptr.getCharacterClass();
            if (monk.isMeditating()) {
                if((int)(Math.random()*2)==0) {
                    DominateOrDecease.setInfoBoard("Monks meditate negated damage.",1.5,Window.getHeight2()/2+300);
                    return 0;
                }
            }
        }
        else if(ptr.getCharacterClass().getName().equals("Rogue")){
            if(ptr.currTile()==Player.getCurrentPlayer().currTile()) {
                //95% chance to negate
                Rogue ptrTwo=(Rogue)ptr.getCharacterClass();
                if(ptrTwo.isSneaking()) {
                    if ((int) (Math.random() * 100) > 4) {
                        DominateOrDecease.setInfoBoard("Rogue is sneaking...", 1.5,Window.getHeight2()/2+300);
                        ptrTwo.setSneaking(false);
                        return 0;
                    }
                }
            }
        }
        return damage;
    }
    public void setAllOrbSections(boolean a){
        Arrays.fill(orb, a);
    }
    public static int getMAXplayers(){
        return MAXplayers;
    }
}


