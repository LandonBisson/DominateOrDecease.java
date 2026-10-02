import java.awt.*;
import java.util.ArrayList;

public class MainMenu{
private static int menuStage=0;
private static int MouseX;
private static int MouseY;
private static boolean transition;
private static double rateOfChange;
/////////ALL CHARACTER SELECT VARAIBLES for bounding and drawing
private static int[] CharSelectX;
private static int[] CharSelectY;
private static int CharSelectXchange;
private static int CharSelectYchange;
private static int[] CharSelectWholeBoxX;
private static int[] CharSelectWholeBoxY;
private static int upcast = 1;
private static int cannonLevel = 1;
private static boolean tileAttack;

/////////////////

private static CharacterClass currentHoveredClass;

//////////////////////////////
    public static void DrawCSelect(Graphics2D g, DominateOrDecease ptr){
        CharSelectXchange=100;
        CharSelectYchange=50;
        int StartX=100;
        int StartY=175;
        //whole box pos
        CharSelectWholeBoxX=new int[]{StartX,(Window.getWidth2()-StartX*3),StartX,(Window.getWidth2()-StartX*3),StartX,(Window.getWidth2()-StartX*3)};
        CharSelectWholeBoxY=new int[]{StartY,StartY,StartY+CharSelectYchange*4+20,StartY+CharSelectYchange*4+20,StartY+CharSelectYchange*8+40,StartY+CharSelectYchange*8+40};
        for(int i=0;i<Player.getNumPlayers();i++) {
            //also this draws the class image and info
            drawCharacterSelect(g, CharSelectWholeBoxX[i], CharSelectWholeBoxY[i], i, CharSelectXchange, CharSelectYchange);
        }

    }
    private static void drawCharacterSelect(Graphics2D g,int startX,int startY, int playerNum, int xchange, int ychange){
        updateSelectXandY(startX,startY);
        //player num up top
        g.setColor(Color.white);
        g.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        if(Player.getPlayer(playerNum)!=null && Player.getPlayer(playerNum).getCharacterClass()!=null)
            g.drawString(Player.getPlayer(playerNum).getCharacterClass().getName(),CharSelectX[6]+45,CharSelectY[6]-50);
        else
            g.drawString("Player #"+(playerNum+1),CharSelectX[6]+45,CharSelectY[6]-50);
        //g.drawString(Player.getPlayer(playerNum).getCharacterClass().getName(),CharSelectX[6]+45,CharSelectY[6]-50);
        for(int i=0;i<9;i++){
            //draw them
            drawCharacterBox(g, CharSelectX[i], CharSelectY[i], 0, 1, 1, Color.white,false,xchange,ychange, i,Player.getPlayer(playerNum));
        }

    }
    public static void drawCSelectClassImage(Graphics2D g,int _MouseX,int _MouseY){
        MouseX=_MouseX;
        MouseY=_MouseY;
        ///////////draw hovered class image
        for (int i = 0; i < Player.getNumPlayers(); i++) {
            if (SquareBoundingBoxTest((CharSelectWholeBoxX[i])+CharSelectXchange, (CharSelectWholeBoxY[i]+CharSelectYchange), (int)(CharSelectXchange*1.5), (int) (CharSelectYchange*1.5))) {
                currentHoveredClass=getSelectedClass(i);
            }
        }
        g.setFont(new Font("Times New Roman", Font.PLAIN, 50));

        g.drawString("Character Select!", Window.getWidth2() / 2-160, Window.getHeight2() / 9);
        if(currentHoveredClass!=null) {
            Images.drawClassCircle(g, currentHoveredClass, 50*Window.getWidth2() / 100, 25*Window.getHeight2() / 100, 0.0, 1.25, 1.25);
            g.setColor(Color.white);
            g.setFont(new Font("Times New Roman", Font.PLAIN, 40));
            g.drawString(currentHoveredClass.getName(), 50*Window.getWidth2() / 100-(currentHoveredClass.getName().length()*9), 25*Window.getHeight2() / 100+125);
            g.setFont(new Font("Times New Roman", Font.PLAIN, 30));
            //- (currentHoveredClass.getAttackName()[0].length() * 10)
            g.drawString("Attack: "+ currentHoveredClass.getAttackName()[0], (36*Window.getWidth2() / 100) , 50*Window.getHeight2() /100);
            g.drawString("Ability: "+currentHoveredClass.getAbilityName()[0], (36*Window.getWidth2() / 100), 55*Window.getHeight2() /100);
            g.drawString("Super Move: "+currentHoveredClass.getSuperMoveName()[0], (36*Window.getWidth2() / 100), 60*Window.getHeight2() /100);

        }
        //////////////////done images
    }
/////////////////////////////////////////
    public static void animate(){
        fireTransition();
    }

    //LB this is seeing the main menu bounding
    public static void ClickMenu(int mouseX, int mouseY){
        MouseX=mouseX;
        MouseY=mouseY;
        if((SquareBoundingBoxTest((Window.getWidth2() / 2),Window.getYNormal(Window.getHeight2() / 6),190,90))){
            transition=true;
            rateOfChange=1.25;
        }
    }
    public static boolean moveModeButton(){
        if(Player.getCurrentPlayer().getCharacterClass().getName().equals("Monk")){
            Monk ptr =(Monk)(Player.getCurrentPlayer().getCharacterClass());
            if(ptr.getAttackCount()<=1)
                return false;
        }
        else if(Player.getCurrentPlayer().getCharacterClass().getName().equals("Druid")){
            Druid ptr =(Druid)(Player.getCurrentPlayer().getCharacterClass());
            if(ptr.isBear()) {
                if (ptr.getAttackCount() <= 1)
                    return false;
            }
        }
        return SquareBoundingBoxTest(666,778,55,35);
    }
    public static boolean endTurnButton(){
        return SquareBoundingBoxTest(388,778,55,35);
    }
    public static int makeArrowTrue(Ranger ptr,boolean findArrowSwitch){
        int width = Board.getCharacterBoxX()/2;
        int height = 105;

        int startX = Window.getX(80 * Window.getWidth2() / 100) + width/2; // same x-value as on middle vertical line
        int startY = Window.getY(72 * Window.getHeight2() / 100)+3+height/2;

        int boxXpos[] = {startX,startX+width,startX,startX+width};
        int boxYpos[] = {startY,startY,startY+height,startY+height};

        for (int i = 0; i<boxYpos.length;i++) {
            if(findArrowSwitch){
                if(SquareBoundingBoxTest(boxXpos[i],boxYpos[i],width/2,height/2)){
                    return i;
                }
            }
            else {
                //if arrow type is false
                if (!ptr.getAllArrTypes()[i]) {
                    if (SquareBoundingBoxTest(boxXpos[i], boxYpos[i], width / 2, height / 2)) {
                        return i;
                    }
                }
            }
        }
        return 0;
    }
    public static boolean checkHandCursor(int mouseX, int mouseY){
        MouseX=mouseX;
        MouseY=mouseY;
        if (menuStage==2) {
            if (DominateOrDecease.getDoubleAttacker()) {

                Player playerPtr = Player.getCurrentPlayer();
                //attack bounding box
                if (playerPtr.getActionPoints() >= 1 && playerPtr.getActionsLeft() >= 1 && SquareBoundingBoxTest((Window.getX(60 * Window.getWidth2() / 100) + Board.getCharacterBoxX() / 2), Window.getY(66 * Window.getHeight2() / 100) + Board.getCharacterBoxY() + 35, 120, 125)) {
                    //always check druid first
                    if (Player.getCurrentPlayer().getCharacterClass().getName().equals("Druid")) {
                        Druid ptr = (Druid) Player.getCurrentPlayer().getCharacterClass();
                        if (ptr.isBear())
                            Mouse.setAttackTile(false);
                        else
                            Mouse.setAttackTile(true);
                    } else if (Player.getCurrentPlayer().getCharacterClass().getSelectingTile()[0] == 0) {
                        Mouse.setAttackTile(false);
                    } else if (Player.getCurrentPlayer().getCharacterClass().getSelectingTile()[0] == 1) {
                        Mouse.setAttackTile(true);
                    }
                    //return attack mode if you have the action points and actions available

                    //get type from
                    return true;
                }
            }
            //boolean returns if the mouse is in attack mode
            Player playerPtr = Player.getCurrentPlayer();
            //attack bounding box
            if (playerPtr.getActionPoints() >= 1 && playerPtr.getActionsLeft() >= 1 && SquareBoundingBoxTest((Window.getX(60 * Window.getWidth2() / 100) + Board.getCharacterBoxX() / 2), Window.getY(66 * Window.getHeight2() / 100) + Board.getCharacterBoxY() + 35, 120, 125)) {
                //to ensure that they get the correct # of attacks
                Player.getCurrentPlayer().getCharacterClass().resetAttackCount();
                //always check druid first
                if (Player.getCurrentPlayer().getCharacterClass().getName().equals("Druid")) {
                    Druid ptr = (Druid) Player.getCurrentPlayer().getCharacterClass();
                    if (ptr.isBear())
                        Mouse.setAttackTile(false);
                    else
                        Mouse.setAttackTile(true);
                } else if (Player.getCurrentPlayer().getCharacterClass().getSelectingTile()[0] == 0) {
                    Mouse.setAttackTile(false);
                } else if (Player.getCurrentPlayer().getCharacterClass().getSelectingTile()[0] == 1) {
                    Mouse.setAttackTile(true);
                }
                //return attack mode if you have the action points and actions available

                //get type from
                return true;
            }
            if (playerPtr.getActionPoints() >= 1 && playerPtr.getActionsLeft() >= 1 && SquareBoundingBoxTest((Window.getX(60 * Window.getWidth2() / 100) + Board.getCharacterBoxX() / 2) + 240, Window.getY(66 * Window.getHeight2() / 100) + Board.getCharacterBoxY() + 40, 120, 125)) {
                //return ability mode if you have the action points and actions available
                if (Player.getCurrentPlayer().getCharacterClass().getSelectingTile()[1] == 1)
                    Mouse.setAttackTile(true);
                else if (Player.getCurrentPlayer().getCharacterClass().getSelectingTile()[1] == 0)
                    Mouse.setAttackTile(false);
                return true;
            }
            //super box
            if (SquareBoundingBoxTest((Window.getX(70 * Window.getWidth2() / 100) + Board.getCharacterBoxX() / 8 - Window.getX(0) - 2), 325 + (Board.getCharacterBoxY() + 60), Board.getCharacterBoxX() / 2, Board.getCharacterBoxY() + 40)) {
                if (Player.getCurrentPlayer().canSuper())
                    return true;
            }
        }
        else if (menuStage==1){
            if((SquareBoundingBoxTest((Window.getWidth2() / 2),Window.getYNormal(Window.getHeight2() / 6),190,90))){
                return true;
            }
            //add button Character
            else if((SquareBoundingBoxTest(Window.getWidth2()/2+100,Window.getYNormal(45*Window.getHeight2()/100)+50,50,20))){
                return true;
            }
            //subtract Button Character
            else if((SquareBoundingBoxTest(Window.getWidth2()/2-100,Window.getYNormal(45*Window.getHeight2()/100)+50,50,20))){
                return true;
            }
            else {
                for (int i = 0; i < Player.getNumPlayers(); i++) {
                    //which box are you in for players
                    if (SquareBoundingBoxTest((CharSelectWholeBoxX[i])+CharSelectXchange, (CharSelectWholeBoxY[i]+CharSelectYchange), (int)(CharSelectXchange*1.5), (int) (CharSelectYchange*1.5))) {
                        return true;
                    }
                }
            }
        }
        else{
            //start button
            if((SquareBoundingBoxTest((Window.getWidth2() / 2),Window.getYNormal(Window.getHeight2() / 6),190,90))){
                return true;
            }
        }
        return false;
    }
    public static int boardClickChangeMouse(){
        //double attacking classes, and have already attacked but not reseting attack counts
        if (DominateOrDecease.getDoubleAttacker()){

            Player playerPtr = Player.getCurrentPlayer();
            //attack bounding box
            if(playerPtr.getActionPoints()>=1&&playerPtr.getActionsLeft()>=1&&SquareBoundingBoxTest((Window.getX(60 * Window.getWidth2() / 100)+Board.getCharacterBoxX()/2),Window.getY(66 * Window.getHeight2() / 100)+Board.getCharacterBoxY()+35,120,125)){
                //always check druid first
                if(Player.getCurrentPlayer().getCharacterClass().getName().equals("Druid")){
                    Druid ptr=(Druid)Player.getCurrentPlayer().getCharacterClass();
                    if(ptr.isBear())
                        Mouse.setAttackTile(false);
                    else
                        Mouse.setAttackTile(true);
                }
                else if(Player.getCurrentPlayer().getCharacterClass().getSelectingTile()[0]==0) {
                    Mouse.setAttackTile(false);
                }
                else if(Player.getCurrentPlayer().getCharacterClass().getSelectingTile()[0]==1) {
                    Mouse.setAttackTile(true);
                }
                //return attack mode if you have the action points and actions available

                //get type from
                return 1;
            }
        }
        //boolean returns if the mouse is in attack mode
        Player playerPtr = Player.getCurrentPlayer();
        //attack bounding box
        if(playerPtr.getActionPoints()>=1&&playerPtr.getActionsLeft()>=1&&SquareBoundingBoxTest((Window.getX(60 * Window.getWidth2() / 100)+Board.getCharacterBoxX()/2),Window.getY(66 * Window.getHeight2() / 100)+Board.getCharacterBoxY()+35,120,125)){
            //to ensure that they get the correct # of attacks
            Player.getCurrentPlayer().getCharacterClass().resetAttackCount();
            //always check druid first
            if(Player.getCurrentPlayer().getCharacterClass().getName().equals("Druid")){
                Druid ptr=(Druid)Player.getCurrentPlayer().getCharacterClass();
                if(ptr.isBear())
                    Mouse.setAttackTile(false);
                else
                    Mouse.setAttackTile(true);
            }
            else if(Player.getCurrentPlayer().getCharacterClass().getSelectingTile()[0]==0) {
                Mouse.setAttackTile(false);
            }
            else if(Player.getCurrentPlayer().getCharacterClass().getSelectingTile()[0]==1) {
                Mouse.setAttackTile(true);
            }
            //return attack mode if you have the action points and actions available

            //get type from
            return 1;
        }
        if(playerPtr.getActionPoints()>=1&&playerPtr.getActionsLeft()>=1&&SquareBoundingBoxTest((Window.getX(60 * Window.getWidth2() / 100)+Board.getCharacterBoxX()/2)+240,Window.getY(66 * Window.getHeight2() / 100)+Board.getCharacterBoxY()+40,120,125)){
            //return ability mode if you have the action points and actions available
            if(Player.getCurrentPlayer().getCharacterClass().getSelectingTile()[1]==1)
                Mouse.setAttackTile(true);
            else if(Player.getCurrentPlayer().getCharacterClass().getSelectingTile()[1]==0)
                Mouse.setAttackTile(false);
            return 2;
        }
        //super box
        if(SquareBoundingBoxTest((Window.getX(70 * Window.getWidth2() / 100)+Board.getCharacterBoxX()/8-Window.getX(0)-2), 325+(Board.getCharacterBoxY()+60),Board.getCharacterBoxX()/2,Board.getCharacterBoxY()+40)){
            if (Player.getCurrentPlayer().canSuper())
                return 3;
        }
        //maintain mouse type
        return Mouse.getAttackType();
    }
    public static void attackPlayer(boolean tile){
        tileAttack=tile;
        if (Player.getCurrentPlayer().getCharacterClass() instanceof Wizard) {
            upcast = makeUpcast();
            if (upcast == 0)
                return;
        } else
            upcast=1;
        if(tile){
            //select tile to attack
            if(DominateOrDecease.getCurMouseHex()!=-1) {
                ///SHIELD BLOCK
                for (int a : Placeables.getShieldTile()){
                    if (a != Player.getCurrentPlayer().currTile()) {
                        if (DominateOrDecease.getCurMouseHex() == a) {
                            DominateOrDecease.setInfoBoard("Shield is blocking players.", 1.5, Window.getHeight2() / 2 + 300);
                            return;
                        }
                    }
                    else{
                        if (DominateOrDecease.getCurMouseHex() != a) {
                            DominateOrDecease.setInfoBoard("Cannot attack out of shield.", 1.5, Window.getHeight2() / 2 + 300);
                            return;
                        }
                    }
                }
                ////////////////
                boolean inRange=false;
                ////////////////if on the same tile or range is two
                if((Player.getCurrentPlayer().getTileNum()==DominateOrDecease.getCurMouseHex())||(Player.getCurrentPlayer().getCharacterClass().getReach()==2)){
                    inRange=true;
                }
                ///////////////range is same tile and adjacent
                if(Player.getCurrentPlayer().getCharacterClass().getReach()==1){
                    int[] adj=Tile.findAdjacent(Player.getCurrentPlayer().getTileNum(),true);
                    for(int i=0;i<adj.length;i++){
                        if(DominateOrDecease.getCurMouseHex()==adj[i])
                            inRange=true;
                    }
                }
                if(inRange) {
                    //do the attack
                    Player.getCurrentPlayer().getCharacterClass().Attack(DominateOrDecease.getCurMouseHex());
                    //minus everything needed

                    //attack done, use points, reset attack count
                    Player.getCurrentPlayer().addActionPoints(-1-(upcast-1));

                Player.getCurrentPlayer().resetActionsLeft(Player.getCurrentPlayer().getActionsLeft() - 1);

                    Player.getCurrentPlayer().getCharacterClass().resetAttackCount();
                    //go to mouse move now that you selected your target
                    Mouse.setAttackType(0,DominateOrDecease.getDoubleAttacker());
                }
            }
        }
        else {
            //attack object selected needing to select person
            if (Mouse.getAttackType() == 1 && !Mouse.isAttackTile()) {
                if (selectPlayerToAttack() != null) {
                    for (int a : Placeables.getShieldTile()){
                        if (a != Player.getCurrentPlayer().currTile()) {
                            if (DominateOrDecease.getCurMouseHex() == a) {
                                DominateOrDecease.setInfoBoard("Shield is blocking players.", 1.5, Window.getHeight2() / 2 + 300);
                                return;
                            }
                        }
                        else{
                            if (DominateOrDecease.getCurMouseHex() != a) {
                                DominateOrDecease.setInfoBoard("Cannot attack out of shield.", 1.5, Window.getHeight2() / 2 + 300);
                                return;
                            }
                        }
                    }
                    boolean inRange=false;
                    ////////////////if on the same tile
                    if(Player.getCurrentPlayer().getTileNum()==selectPlayerToAttack().getTileNum()){
                        inRange=true;
                    }
                    ///////////////range is same tile and adjacent
                    if( inRange||Player.getCurrentPlayer().getCharacterClass().getReach()==1){
                        int[] adj=Tile.findAdjacent(Player.getCurrentPlayer().getTileNum(),true);
                        for(int i=0;i<adj.length;i++){
                            if(selectPlayerToAttack().getTileNum()==adj[i])
                                inRange=true;
                        }
                    }
                    /////////everything is in range
                    else if (inRange||Player.getCurrentPlayer().getCharacterClass().getReach()==2 ){
                        inRange=true;
                    }
                    if(inRange) {
                        Player.getCurrentPlayer().getCharacterClass().Attack(selectPlayerToAttack());
                        //working
                        if((Player.getCurrentPlayer().getCharacterClass().getName().equals("Monk"))){
                            Player.getCurrentPlayer().getCharacterClass().setAttackCount(Player.getCurrentPlayer().getCharacterClass().getAttackCount()-1);
                        }
                        else if ((Player.getCurrentPlayer().getCharacterClass().getName().equals("Druid"))){
                            Druid ptr=(Druid)(Player.getCurrentPlayer().getCharacterClass());
                            if(ptr.isBear()) {
                                Player.getCurrentPlayer().getCharacterClass().setAttackCount(Player.getCurrentPlayer().getCharacterClass().getAttackCount() - 1);
                            }
                        }

                        //make sure youn dont have to click again
                        MinusActions();
                    }
                    /////////////////
                }

            }
        }
    }
    public static boolean MinusActions(){
        //minus action stuff
        if (Player.getCurrentPlayer().getCharacterClass().getAttackCount()<=0 && !DominateOrDecease.getDoubleAttacker()){
            //attack done, use points, reset attack count
            Player.getCurrentPlayer().addActionPoints(-1-(upcast-1));
            Player.getCurrentPlayer().resetActionsLeft(Player.getCurrentPlayer().getActionsLeft() - 1);

            Player.getCurrentPlayer().getCharacterClass().resetAttackCount();
            //go to mouse move now that you selected your target
            Mouse.setAttackType(0,DominateOrDecease.getDoubleAttacker());
            return true;
        }
        return false;
    }
    public static int makeUpcast () {
        int startX = Window.getX(60 * Window.getWidth2() / 100)+10;
        int startY = Window.getY(66 * Window.getHeight2() / 100)+3*250/4+7;
        int width = 196;
        int height = Window.getY(Window.getHeight2())-9 - startY;
        Wizard ptr = (Wizard)(Player.getCurrentPlayer().getCharacterClass());
        if (Player.getCurrentPlayer().getActionPoints() > ptr.getUpcast()) {
            if(SquareBoundingBoxTest(startX+width/8+4, startY+height/2+2,width/8+5,height/2+1) && ptr.getUpcast()>1) {
                ptr.setUpcast(ptr.getUpcast()-1);
                return 0;
            }
            else if(SquareBoundingBoxTest(startX+7*width/8-4, startY+height/2+2,width/8+5,height/2+1) && ptr.getUpcast()<3) {
                ptr.setUpcast(ptr.getUpcast()+1);
                return 0;
            }
        }
        return ptr.getUpcast();
    }
    public static int makeAutoCannon () {
        int startX = Window.getX(80 * Window.getWidth2() / 100)+15;
        int startY = Window.getY(90 * Window.getHeight2() / 100)+6;
        int width = 209;
        int height = Window.getY(Window.getHeight2()) - startY;
        Artificer ptr = (Artificer) (Player.getCurrentPlayer().getCharacterClass());
        if(SquareBoundingBoxTest(startX+width/8+3,startY+25,width/8+5,height/2+1) && ptr.cannonLevel()>1) {
            ptr.setCannonLevel(ptr.cannonLevel()-1);
            return 0;
        }
        if (Player.getCurrentPlayer().getActionPoints() > ptr.cannonLevel()) {
            if(SquareBoundingBoxTest( startX+7*width/8-3,startY+25,width/8+5,height/2+1) && ptr.cannonLevel()<3){
                ptr.setCannonLevel(ptr.cannonLevel()+1);
                return 0;
            }
        }
        if (SquareBoundingBoxTest(Window.getX(80 * Window.getWidth2() / 100)+9+222/2, Window.getY(90 * Window.getHeight2() / 100)+65/2,111,32))
            return 0;
        return ptr.cannonLevel();
    }
    public static void abilityPlayer(){
        if (Player.getCurrentPlayer().getCharacterClass() instanceof Artificer) {
            cannonLevel = makeAutoCannon();
            if (cannonLevel == 0)
                return;
        } else
            cannonLevel=1;
        Player.getCurrentPlayer().getCharacterClass().Ability();
        //attack done, use points, reset attack count
        Player.getCurrentPlayer().addActionPoints(-cannonLevel);
        Player.getCurrentPlayer().resetActionsLeft(Player.getCurrentPlayer().getActionsLeft() - 1);

        Player.getCurrentPlayer().getCharacterClass().resetAttackCount();
        //go to mouse move now that you selected your target
        Mouse.setAttackType(0,DominateOrDecease.getDoubleAttacker());

    }

    public static void superPlayer(boolean tile){
        if (tile){
            if(DominateOrDecease.getCurMouseHex()!=-1) {
                ///SHIELD BLOCK
                for (int a : Placeables.getShieldTile()){
                    if(DominateOrDecease.getCurMouseHex()==a){
                        DominateOrDecease.setInfoBoard("Shield is blocking players.",1.5,Window.getHeight2()/2+300);
                        return;
                    }
                }
                Player.getCurrentPlayer().getCharacterClass().SuperMove();
                //attack done, use points, reset attack count            Player.getCurrentPlayer().getCharacterClass().resetAttackCount();
                //go to mouse move now that you selected your target
                Mouse.setAttackType(0,DominateOrDecease.getDoubleAttacker());
            }

            Player.getCurrentPlayer().getCharacterClass().SuperMove();
            //attack done, use points, reset attack count     
            //Player.getCurrentPlayer().resetActionsLeft(Player.getCurrentPlayer().getActionsLeft() - 1);
            Player.getCurrentPlayer().setAllOrbSections(false);
            Player.getCurrentPlayer().getCharacterClass().resetAttackCount();
            //go to mouse move now that you selected your target
            Mouse.setAttackType(0,DominateOrDecease.getDoubleAttacker());
        }
        else {
            Player.getCurrentPlayer().getCharacterClass().SuperMove();;
            //attack done, use points, reset attack count        Player.getCurrentPlayer().addActionPoints(-1);
            //this limits the monk like crazy, wasnt designed in this way Player.getCurrentPlayer().resetActionsLeft(Player.getCurrentPlayer().getActionsLeft() - 1);
            Player.getCurrentPlayer().setAllOrbSections(false);
            Player.getCurrentPlayer().getCharacterClass().resetAttackCount();
            //go to mouse move now that you selected your target
            Mouse.setAttackType(0,DominateOrDecease.getDoubleAttacker());
        }
    }

    public static Player selectPlayerToAttack(){

        ///
        for (int hex = 0; hex<Board.getAllTiles().length; hex++) {
            int[] x = Tile.getPlayerXpos(hex);
            int[] y = Tile.getPlayerYpos(hex);
            //one player bounding circle
            if(Board.getAllTiles()[hex].getNumPlayers()==1){
                if (CircleBoundingBoxTest(Tile.getTileX(hex),Tile.getTileY(hex),Images.getTokenWidth()/2) && Tile.getTile(hex).getPlayerPtrs()[0] != Player.getCurrentPlayer()) {
                    return Tile.getTile(hex).getPlayerPtrs()[0];
                }
            }
            else {
                for (int j = 0; j < Board.getAllTiles()[hex].getNumPlayers(); j++) {
                    //LB current player is one this tile being targeted so make sure they get skipped
                    if(Board.getAllTiles()[hex].getPlayerPtrs()[j].equals(Player.getCurrentPlayer())){
                        continue;
                    }
                    //2-6 players on tile bounding
                    if (CircleBoundingBoxTest(x[j], y[j], Images.getTokenWidth() / 2) && Player.getPlayer(j) != Player.getCurrentPlayer()) {
                        return Tile.getTile(hex).getPlayerPtrs()[j];
                    }
                }
            }

        }
        //working 100%
        //////////////////////selected one on the side boxes////////////////////////////////////
        int startX = Window.getX(60 * Window.getWidth2() / 100)+Board.getCharacterBoxX()/2;
        int startY = Window.getY(Board.getCharacterBoxY()/2)+2*Board.getCharacterBoxY();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 2; col++) {
                //LB too many players
                if(2*row+col+1>Player.getNumPlayers())
                    return null;
                if (SquareBoundingBoxTest(startX + col * Board.getCharacterBoxX(), startY - row * Board.getCharacterBoxY(), Board.getCharacterBoxX() / 2, Board.getCharacterBoxY() / 2)) {
//                    System.out.println(Player.getPlayer(2 * row + col).getCharacterClass().getName());
                    if (Player.getCurrentPlayer() != Player.getPlayer(2 * row + col))
                        return Player.getPlayer(2 * row + col);
                }
            }
        }

        return null;
    }

    private static CharacterClass getUnUsedClass(){
        CharacterClass[] classes = CharacterClass.getAllClasses();
        ArrayList<CharacterClass> NOTchoices = new ArrayList<CharacterClass>();
        //look at all of the classes and find the ones not to choose
        for(int i=0;i<Player.getNumPlayers();i++){
            if(Player.getPlayer(i).getCharacterClass()!=null) {
                NOTchoices.add(Player.getPlayer(i).getCharacterClass());
            }
        }
        //removing same
        for(int i=0;i<classes.length;i++){
            for(int a=0;a<NOTchoices.size();a++){
                if(classes[i]!=null) {
                    if (classes[i].getName().equals(NOTchoices.get(a).getName()))
                        classes[i] = null;
                }
            }
        }
        int randomNum=(int)(Math.random()* classes.length);
        while(classes[randomNum]==null){
            randomNum=(int)(Math.random()* classes.length);
        }
        return classes[randomNum];
    }

    //LB this is gonna have a lot of bounding boxes, 9 for each character - per player
    public static void ClickCSelect(int mouseX, int mouseY){
        MouseX=mouseX;
        MouseY=mouseY;
        if((SquareBoundingBoxTest((Window.getWidth2() / 2),Window.getYNormal(Window.getHeight2() / 6),190,90))){
            transition=true;
            rateOfChange=1.25;
            for(int i=0;i<Player.getNumPlayers();i++){
                if(Player.getPlayer(i).getCharacterClass()==null) {
                    Player.getPlayer(i).setClass(getUnUsedClass());
                }
            }
        }
        //add button Character
        else if((SquareBoundingBoxTest(Window.getWidth2()/2+100,Window.getYNormal(45*Window.getHeight2()/100)+50,50,20))){
            Player.addPlayer(1);
        }
        //subtract Button Character
        else if((SquareBoundingBoxTest(Window.getWidth2()/2-100,Window.getYNormal(45*Window.getHeight2()/100)+50,50,20))){
            Player.subtractPlayer(1);
        }
        else {
            for (int i = 0; i < Player.getNumPlayers(); i++) {
                //which box are you in for players
                if (SquareBoundingBoxTest((CharSelectWholeBoxX[i])+CharSelectXchange, (CharSelectWholeBoxY[i]+CharSelectYchange), (int)(CharSelectXchange*1.5), (int) (CharSelectYchange*1.5))) {
                    Player.getPlayer(i).setClass(getSelectedClass(i));
                }
            }
        }
    }
    private static CharacterClass getSelectedClass(int whatBox){
        CharacterClass[] classes=new CharacterClass[]{new Artificer(),new Barbarian(),new Bard(),new Cleric(),new Druid(),new Monk(),new Ranger(),new Rogue(),new Wizard()};
        for(int i=0;i<9;i++) {
            //move to the correct box
            updateSelectXandY(CharSelectWholeBoxX[whatBox],CharSelectWholeBoxY[whatBox]);
            if (SquareBoundingBoxTest(CharSelectX[i], CharSelectY[i],CharSelectXchange/2,CharSelectYchange/2)){
                if(isUnused(classes[i]))
                    return classes[i];
            }
        }
        return null;
    }
    public static boolean isUnused(CharacterClass ptr){
        ArrayList<CharacterClass> NOTchoices = new ArrayList<CharacterClass>();
        //look at all of the classes and find the ones not to choose
        for(int i=0;i<Player.getNumPlayers();i++){
            if(Player.getPlayer(i).getCharacterClass()!=null) {
                NOTchoices.add(Player.getPlayer(i).getCharacterClass());
            }
        }
        boolean ret=true;
        for(int i=0;i<NOTchoices.size();i++){
            if(ptr.getName().equals(NOTchoices.get(i).getName()))
                ret=false;
        }
        return ret;
    }
    public static boolean SquareBoundingBoxTest(int CenterX, int CenterY, int width, int height){
        if (MouseX<CenterX+width &&
                MouseX>CenterX-width &&
                MouseY<CenterY+height &&
                MouseY>CenterY-height){
            return true;
        }
        return false;
    }

    public static boolean CircleBoundingBoxTest(int CenterX, int CenterY, double radius){
        if (Math.sqrt(((CenterX - MouseX) * (CenterX - MouseX)) + ((CenterY - MouseY) * (CenterY - MouseY))) < radius)
        {
            return true;
        }
        return false;
    }
    public static void Mute(int mouseX, int mouseY) {
        int imageWidth = (int)(Images.getMuteImage(DominateOrDecease.mute).getWidth(DominateOrDecease.frame)/2.5);
        int imageHeight = (int)(Images.getMuteImage(DominateOrDecease.mute).getHeight(DominateOrDecease.frame)/2.5);
        if (mouseX<imageWidth/2+imageWidth &&
                mouseX>imageWidth/2-imageWidth &&
                mouseY<Window.getY(0)-50+imageHeight &&
                mouseY>Window.getY(0)-10-imageHeight){
            DominateOrDecease.mute = !DominateOrDecease.mute;
        }
    }
    public static void fireTransition(){
        if(transition){
            //LB reach 17 times size, then go back down
            if(Images.getFlameSize()>17) {
                rateOfChange = -rateOfChange;
                menuStage++;
            }
            //LB increase flame size by ROC
            Images.setFlameSize(Images.getFlameSize()+rateOfChange);
            Images.setFlameYpos((int) (Images.getFlameYpos()-(50*rateOfChange)));
            if (menuStage==2){
                if(Images.getFlameSize()<=0) {
                    Images.setFlameYpos(Window.getYNormal(Window.getHeight2() / 6+5));
                    //visually disapear without using 0
                    Images.setFlameSize(0.0000000001);
                    transition = false;
                }
            }
            else {
                if(Images.getFlameYpos()>Window.getYNormal(Window.getHeight2() / 6 + 5)) {
                    Images.setFlameYpos(Window.getYNormal(Window.getHeight2() / 6 + 5));
                    Images.setFlameSize(0.45);
                }
                if (Images.getFlameSize() < 0.45 || Images.getFlameYpos()>Window.getYNormal(Window.getHeight2() / 6 + 5)) {
                    Images.setFlameYpos(Window.getYNormal(Window.getHeight2() / 6 + 5));
                    Images.setFlameSize(0.45);
                    transition = false;
                }
            }
        }
    }
    private static void drawCharacterBox(Graphics2D g, int _xpos, int _ypos, double rot, double xscale, double yscale, Color color, boolean fill,int x,int y,int PlayerNum,Player a) {
        g.setColor(color);
        String[] names=new String[]{"Artificer","Barbarian","Bard","Cleric","Druid","Monk","Ranger","Rogue","Wizard"};
        if(names[PlayerNum].length()>8) {
            g.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        }
        else {
            g.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        }

        g.drawString(names[PlayerNum], _xpos - 45, _ypos+10);
        g.translate(_xpos,_ypos);
        g.rotate(rot  * Math.PI/180.0);
        g.scale( xscale , yscale );
        if(fill) {
            g.setColor(color);
            g.fillRect(-x/2, -y/2, x, y);
        }
        else{
            g.setColor(color);
            g.drawRect(-x/2, -y/2, x, y);
        }
        CharacterClass[] classes = CharacterClass.getAllClasses();
        if (classes[PlayerNum].getName().equals(names[PlayerNum]) && !(isUnused(classes[PlayerNum]))){
            if (a.getCharacterClass() != null && a.getCharacterClass().getName().equals(names[PlayerNum]))
               drawOval(g,x/4-25,y/4-12,0,1,1,Color.green,false);
            else{
                g.setColor(Color.red);
                g.drawLine(-x / 2, -y / 2, x / 2, y / 2);
                g.drawLine(x / 2, -y / 2, -x / 2, y / 2);
            }
        }
        g.scale( 1.0/xscale,1.0/yscale );
        g.rotate(-rot  * Math.PI/180.0);
        g.translate(-_xpos,-_ypos);
        g.setColor(color);
    }
    private static void updateSelectXandY(int startX,int startY){
        //where are the bounding boxes gonna be
        CharSelectX=new int[]{startX,startX+CharSelectXchange,startX+2*CharSelectXchange,startX,startX+CharSelectXchange,startX+2*CharSelectXchange,startX,startX+CharSelectXchange,startX+2*CharSelectXchange};
        CharSelectY=new int[]{startY+CharSelectYchange*2,startY+CharSelectYchange*2,startY+CharSelectYchange*2,startY+CharSelectYchange,startY+CharSelectYchange,startY+CharSelectYchange,startY,startY,startY};
    }
    public static void drawOval(Graphics2D g, int _xpos, int _ypos, double rot, double xscale, double yscale, Color color, boolean fill) {
        int ovalwidth = 100;
        g.translate(_xpos, _ypos);
        g.rotate(rot * Math.PI / 180.0);
        g.scale(xscale, yscale);

        if (fill) {
            g.setColor(color);
            g.fillOval(-ovalwidth / 2, -ovalwidth / 4, ovalwidth, ovalwidth/2);
        } else {
            g.setColor(color);
            g.drawOval(-ovalwidth / 2, -ovalwidth / 4, ovalwidth, ovalwidth/2);
        }

        g.scale(1.0 / xscale, 1.0 / yscale);
        g.rotate(-rot * Math.PI / 180.0);
        g.translate(-_xpos, -_ypos);

    }
    public static boolean getTileAttack(){return tileAttack;}
    public static int getMenuPhase(){
        return menuStage;
    }
    public static void setMenuPhase(int phase){
        menuStage=phase;
    }
}

