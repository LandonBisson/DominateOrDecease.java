
import java.awt.*;
import java.awt.Image;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;
import javax.sound.sampled.*;

public class DominateOrDecease extends JFrame implements Runnable {
    boolean animateFirstTime = true;
    Image image;
    Graphics2D g;
    static int MouseX;
    static int MouseY;
    static int CurMouseHex;
    static final int frameRate = 20;
    static int TimeLeftDisplayingInfo=-1;
    static String currInfo=null;
    static int infoYpos;
    static boolean gameOver;
    //for the next player
    static int nextTurnTime;
    static boolean testing;
    //for the rules board
    public static boolean displayRules;
    static Sounds MenuSound = null;
    static Sounds BoardSound = null;
    static Sounds finaleSound = null;
    static boolean mute;
    static boolean doubleAttacker;

    static DominateOrDecease frame;
    public static void main(String[] args) {
        frame = new DominateOrDecease();
        frame.setSize(Window.WINDOW_WIDTH, Window.WINDOW_HEIGHT);
        frame.setResizable(false); //changes window size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public DominateOrDecease() {
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                //left click
                if (e.BUTTON1 == e.getButton() ) {
                    MainMenu.Mute(MouseX, MouseY);
                    //dont want misclicks
                    if(displayRules)
                        return;
                    if(gameOver)
                        reset();
                    //main game clicking
                    if (MainMenu.getMenuPhase()==2) {
                        ///after they select who they are hitting or what tile it needs to go bacl to mouse moving mode
                        Mouse.setAttackType(MainMenu.boardClickChangeMouse(), doubleAttacker);
                        if(Dice.isRolling()){
                            setInfoBoard("Wait for dice to roll!",1.5,Window.getHeight2()/2+200);
                        }
                        //do the mouses actions
                        if(MainMenu.endTurnButton()){
                            getNextPlayer();
                        }
                        else if(MainMenu.moveModeButton()){
                            Mouse.setAttackType(0,doubleAttacker);
                        }
                        //in moving mode
                        if (Mouse.getAttackType()==0 ){
                            if (!doubleAttacker)
                            //cannot move if you need to do your other attack
                                Player.getCurrentPlayer().Move(CurMouseHex,false);
                        }
                        //in attacking mode
                        else if(Mouse.getAttackType()==1){
                            MainMenu.attackPlayer(Mouse.isAttackTile());
                        }
                        else if(Mouse.getAttackType()==2){
//                            Sounds.playCurrentSound(Player.getCurrentPlayer().getCharacterClass(), 1);
                            if (!doubleAttacker)
                                MainMenu.abilityPlayer();
                        }
                        else if(Mouse.getAttackType()==3){
                            if(Player.getCurrentPlayer().canSuper() && !doubleAttacker) {
//                                Sounds.playCurrentSound(Player.getCurrentPlayer().getCharacterClass(), 2);
                                MainMenu.superPlayer(Mouse.isAttackTile());
                            }
                        }
                    }
                    //characterSelect
                    else if(MainMenu.getMenuPhase()==1){
                        MainMenu.ClickCSelect(e.getX(), e.getY());
                    }
                    //Menu start, and num players bounding
                    else {
                        MainMenu.ClickMenu(e.getX(), e.getY());
                    }
                }
                if (e.BUTTON3 == e.getButton()) {

                }
                repaint();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {

                repaint();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent e) {
                //
                repaint();
                MouseX=e.getX();
                MouseY=e.getY();
                if(MainMenu.getMenuPhase()==2)
                    CurMouseHex = Board.findHexagon(e.getX(),e.getY());
            }
        });

        addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
                if (e.VK_R == e.getKeyCode()) {
                    if(MainMenu.getMenuPhase()==0 ||MainMenu.getMenuPhase()==2)
                        displayRules=!displayRules;
                }
                else if (e.VK_T == e.getKeyCode()) {
                    testing=!testing;
                }
                else if (e.VK_ESCAPE == e.getKeyCode()) {
                    reset();
                }
                repaint();
            }
        });
        init();
        start();
    }
    Thread relaxer;
    ////////////////////////////////////////////////////////////////////////////
    public void init() {
        requestFocus();
    }
    ////////////////////////////////////////////////////////////////////////////
    public void destroy() {
    }
    ////////////////////////////////////////////////////////////////////////////
    public void paint(Graphics gOld) {
        if (image == null || Window.xsize != getSize().width || Window.ysize != getSize().height) {
            Window.xsize = getSize().width;
            Window.ysize = getSize().height;
            image = createImage(Window.xsize, Window.ysize);
            g = (Graphics2D) image.getGraphics();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
        }
//fill background
        g.setColor(Color.darkGray.darker());
        g.fillRect(0, 0, Window.xsize, Window.ysize);

        int x[] = {Window.getX(0), Window.getX(Window.getWidth2()), Window.getX(Window.getWidth2()), Window.getX(0), Window.getX(0)};
        int y[] = {Window.getY(0), Window.getY(0), Window.getY(Window.getHeight2()), Window.getY(Window.getHeight2()), Window.getY(0)};
//fill border
        g.setColor(Color.darkGray);
        g.fillPolygon(x, y, 4);
// draw border
        g.setColor(Color.black);
        g.drawPolyline(x, y, 5);
        if (animateFirstTime) {
            gOld.drawImage(image, 0, 0, null);
            return;
        }
        Images.drawBrick(g,Window.getX(Window.getWidth2()/2),Window.getYNormal(Window.getHeight2()/2));
        DrawWindowPhase(g,this);
        //phase 2, want to make it turn off when not hovering over active selection
        setCursor(DEFAULT_CURSOR);
        //all the reasons you want a hand cursor within the game are found here
        if (MainMenu.checkHandCursor(MouseX, MouseY) || (MainMenu.getMenuPhase()==2 && MainMenu.endTurnButton()) || (MainMenu.getMenuPhase()==2 && MainMenu.moveModeButton())
        || (MainMenu.getMenuPhase()==2 && MainMenu.getTileAttack() && CurMouseHex!=-1)
        || (MainMenu.getMenuPhase()==2 && !MainMenu.getTileAttack() && MainMenu.selectPlayerToAttack()!=null && MainMenu.selectPlayerToAttack()!=Player.getCurrentPlayer())
        )
            setCursor(HAND_CURSOR);
        /////
        //display next turn
        if (nextTurnTime>0){
            try{
                drawFillRect(g, Window.getWidth2() / 2, Window.getHeight2()/2, 0.0, 1, 1, (new Color(255, 255, 255, 100)));
                g.setColor(Color.BLACK);
                g.setFont(new Font("Times New Roman", Font.PLAIN, 35));
                g.drawString(Player.getCurrentPlayer().getCharacterClass().getName(), Window.getWidth2() / 2 - 60, Window.getHeight2()/2+65);
                g.setFont(new Font("Times New Roman", Font.PLAIN, 100));
                g.drawString("Next Turn!", Window.getWidth2() / 2 - 225, Window.getHeight2()/2+25);
            }
            catch (Exception nullPointer){
                //make sure there is not an error at the start of the game
            }
        }
        if (TimeLeftDisplayingInfo>=0 && currInfo!=null){
            drawFillRect(g,Window.getWidth2()/2,infoYpos,0.0,1,1,(new Color(255,255,255,100)));
            g.setColor(Color.BLACK);
            g.setFont(new Font("Times New Roman", Font.PLAIN, 73));
            g.drawString(""+currInfo,Window.getWidth2()/2-(currInfo.length()*15),infoYpos+25);
        }
        if(MainMenu.getMenuPhase()>=2) {
            if(testing) {
                testingInfo(g);
            }
            //move mode
            if(Mouse.getAttackType()==0)
                drawOval(g,MouseX,MouseY, 0,1,1, Color.green.darker(), false);
                //mouse attack mode
            else if(Mouse.getAttackType()==1)
                drawOval(g,MouseX,MouseY, 0,1,1, Color.red.darker(), false);
                //tile selecting abilites,
            else
                drawOval(g,MouseX,MouseY, 0,1,1, Color.yellow.darker(), false);

        }
        if(gameOver)
            Images.drawGameOver(g);

        gOld.drawImage(image, 0, 0, null);
    }

    ////////////////////////////////////////////////////////////////////////////
// needed for     implement runnable
    public void run() {
        while (true) {
            animate();
            repaint();
            double seconds = (1.0 /frameRate);    //time that 1 frame takes.
            int miliseconds = (int) (1000.0 * seconds);
            try {
                Thread.sleep(miliseconds);
            } catch (InterruptedException e) {
            }
        }
    }


    /////////////////////////////////////////////////////////////////////////
    public void reset() {
        //mkae this out of bounds so its not displayed on the menu
        nextTurnTime=0;
        testing=false;
        TimeLeftDisplayingInfo=-1;
        currInfo=null;
        gameOver=false;
        infoYpos=Window.getHeight2()/2;
        Dice.Reset();
        ///
        //super important omg
        MainMenu.setMenuPhase(0);
        Board.create();
        //create players after board ALWAYS
        Mouse.setAttackType(0,doubleAttacker);
        Player.CreatePlayer(Player.getNumPlayers());
        Images.reset();
        Placeables.Clear();
        displayRules = false;
    }
    /////////////////////////////////////////////////////////////////////////
    public void animate() {

        if (animateFirstTime) {
            animateFirstTime = false;
            if (Window.xsize != getSize().width || Window.ysize != getSize().height) {
                Window.xsize = getSize().width;
                Window.ysize = getSize().height;
            }
            Dice.Init();
            Images.Init();
            Sounds.InitSoundEffects();
            reset();
            MenuSound = new Sounds("src/Songs/mainTitle.wav");
            BoardSound = new Sounds ("src/Songs/Board.wav");
            finaleSound = new Sounds("src/Songs/finale.wav");
            mute=false;
        }
        nextTurnTime--;
        TimeLeftDisplayingInfo--;
        if(Player.getNumPlayers()<=1) {
            System.out.println("Game over in main");
            gameOver=true;
        }

            // what else needs to get done?            // what else needs to get done?


        if(!Dice.isRolling())
            CharacterClass.finalizeDamage();
        if(Player.getCurrentPlayer().getActionsLeft()<=0 && !Dice.isRolling()) {
           getNextPlayer();
        }
        MainMenu.animate();
        //if they have used both actions per turn go to next player, add action point
        ///////////////////////////////////////////////
        for (int i = 0; i < Player.getNumAlivePlayers();i++){
            if (Player.getPlayer(i).getHealth() <= 0){
                Player.setNumPlayers(Player.getAllPlayer().size()-1);
                Player.getAllPlayer().remove(Player.getAllPlayer().get(i));
            }

        }
        /////////////////////////////////////
        ReInitSounds();
        ///sounds below
        if (!mute) {
            BoardSound.pausePlaying = false;
            MenuSound.pausePlaying = false;
            finaleSound.pausePlaying = false;
            if (MainMenu.getMenuPhase() != 2){
                BoardSound.stopPlaying = true;
                MenuSound.stopPlaying = false;
                finaleSound.stopPlaying = true;
            }
            else {
                if(gameOver){
                    MenuSound.stopPlaying = true;
                    finaleSound.stopPlaying = true;
                    BoardSound.stopPlaying = true;
                }
                else if(Player.getNumPlayers()<=Player.getMAXplayers()/2 || Player.getNumPlayers()==2){
                    MenuSound.stopPlaying = true;
                    finaleSound.stopPlaying = false;
                    BoardSound.stopPlaying = true;
                }
                else {
                    MenuSound.stopPlaying = true;
                    BoardSound.stopPlaying = false;
                    finaleSound.stopPlaying = true;
                }
            }
        }
        else {
            BoardSound.pausePlaying = true;
            MenuSound.pausePlaying = true;
            finaleSound.pausePlaying = true;
        }

    }

    ////////////////////////////////////////////////////////////////////////////
    public void start() {
        if (relaxer == null) {
            relaxer = new Thread(this);
            relaxer.start();
        }
    }
    ////////////////////////////////////////////////////////////////////////////
    private static void testingInfo(Graphics2D g){
        //////////////testing STUFF
        g.setFont(new Font("Arial", Font.PLAIN, 10));
        int numAdjacent = 0;
        for (int i = 0; i < Tile.findAdjacent(CurMouseHex, false).length; i++) {

            numAdjacent += Tile.getTileNumPlayer(Tile.findAdjacent(CurMouseHex, false)[i]);
        }
        g.setColor(Color.black);
        int hypotenuse = ((MouseX - Board.getBoardMiddleX()) * (MouseX - Board.getBoardMiddleX())) + ((MouseY - Window.getHeight2() / 2) * (MouseY - Window.getHeight2() / 2));
        g.drawString("(" + MouseX + "," + MouseY + ")" + " Hyp = " + (int) Math.sqrt(hypotenuse) + " Current Hex: " + CurMouseHex + " With " + Tile.getTileNumPlayer(CurMouseHex) + " People on it and " + numAdjacent + " adjacent", MouseX, MouseY);
        //creates red triangle example
        g.setColor(Color.red);
        g.drawLine(MouseX, MouseY, Board.getBoardMiddleX(), Board.getBoardMiddleY());
        g.drawLine(MouseX, MouseY, MouseX, Board.getBoardMiddleY());
        g.drawLine(MouseX, Board.getBoardMiddleY(), Board.getBoardMiddleX(), Board.getBoardMiddleY());
        g.setColor(Color.black);
    }
    private void drawFillRect(Graphics2D g, int _xpos, int _ypos, double rot, double xscale, double yscale,Color color) {
        g.translate(_xpos,_ypos);
        g.rotate(rot  * Math.PI/180.0);
        g.scale( xscale , yscale );

        g.setColor(color);
        g.fillRect(-(Window.getWidth2()+250)/2,-Window.getHeight2()/10, Window.getWidth2()+Window.getX(0)+250,Window.getHeight2()/5);

        g.scale( 1.0/xscale,1.0/yscale );
        g.rotate(-rot  * Math.PI/180.0);
        g.translate(-_xpos,-_ypos);

    }
    public static void drawOval(Graphics2D g, int _xpos, int _ypos, double rot, double xscale, double yscale, Color color, boolean fill) {
        int ovalwidth = 15;
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
    public static void DrawWindowPhase(Graphics2D g, DominateOrDecease ptr){
        if (MainMenu.getMenuPhase()==2) {
            Board.drawAttacks(g,Player.getCurrentPlayer().getCharacterClass());
            Board.Draw(g,ptr);
            Dice.DrawDice(g);
        }
        else if(MainMenu.getMenuPhase()==1){
            displayRules=false;
            MainMenu.DrawCSelect(g, ptr);
            MainMenu.drawCSelectClassImage(g,MouseX,MouseY);
            Images.drawCharacterSelect(g,ptr);
        }
        else {
            Images.drawUI(g,ptr);
        }
    }
    public static void ReInitSounds(){
        if (MenuSound.donePlaying && !MenuSound.stopPlaying) {
            MenuSound = new Sounds("src/Songs/mainTitle.wav");
        }
        if (BoardSound.donePlaying && !BoardSound.stopPlaying) {
            BoardSound = new Sounds("src/Songs/Board.wav");
        }
        if (finaleSound.donePlaying && !finaleSound.stopPlaying){
            finaleSound = new Sounds("src/Songs/finale.wav");
        }
    }

    public static void getNextPlayer(){
        doubleAttacker=false;
        Player.getCurrentPlayer().getCharacterClass().EndTurn();

        Player.getNextPlayer();
        Player.getCurrentPlayer().addActionPoints(1);

        //new player gets to start turn
        Placeables.runAllConstant();
        Tile.runTileEffects(Player.getCurrentPlayer().currTile());
        //start at top and move down to show that its the next turn
        nextTurnTime=frameRate*2;
    }
    public static void setInfoBoard(String _currInfo,double seconds,int ypos){
        TimeLeftDisplayingInfo=(int)(frameRate*seconds);
        currInfo=_currInfo;
        infoYpos=ypos;
    }
    public static boolean getDoubleAttacker(){return doubleAttacker;}
    public static void setDoubleAttacker(boolean attacker){doubleAttacker=attacker;}
    public static void setGameOver(boolean gameover){
        gameOver=gameover;
    }
    public static JFrame getFrame(){
        return frame;
    }
    public static boolean getMute(){
        return mute;
    }
    public static int getFrameRate(){
        return frameRate;
    }
    public static int getMouseX(){
        return MouseX;
    }

    public static int getMouseY(){
        return MouseY;
    }
    public static int getCurMouseHex(){return CurMouseHex;}
}
