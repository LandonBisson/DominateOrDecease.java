import java.awt.*;

public class Images {
    private static DominateOrDecease MainObj;
    private static Image logo;
    private static Image cobble;
    private static Image START;
    private static Image Flame;
    private static int FlameYpos;
    private static double flameSize;
    private static Image numberOfPlayer;
    private static Image rulesDorD;
    private static Image pressR;
    private static Image winScreen;
    private static Image spikeGrowth;
    private static Image cannon;
    private static Image brick;
    private static Image attackHere;

    private static Image tokens[] = new Image[9];
    private static Image abilities[] = new Image[9];
    private static Image attacks[] = new Image[10];
    private static Image supers[] = new Image[9];
    private static Image tiles[]=new Image[7];

    private static Image[] mute = new Image[2];

    //this is where I will create all the images, this class will just focus on all of that
    //I am going to working on the logo and the UI now
    public static void Init(){
        logo = Toolkit.getDefaultToolkit().getImage("src/Images/DominateOrDecease.PNG");
        cobble = Toolkit.getDefaultToolkit().getImage("src/Images/CobbleWall.GIF");
        START = Toolkit.getDefaultToolkit().getImage("src/Images/START.PNG");
        Flame = Toolkit.getDefaultToolkit().getImage("src/Images/Flame.GIF");
        numberOfPlayer= Toolkit.getDefaultToolkit().getImage("src/Images/NumberOfPlayers.PNG");
        FlameYpos=Window.getYNormal(Window.getHeight2() / 6+5);
        flameSize=0.45;
        rulesDorD= Toolkit.getDefaultToolkit().getImage("src/Images/rulesDorD.JPG");
        pressR= Toolkit.getDefaultToolkit().getImage("src/Images/pressR.PNG");
        winScreen= Toolkit.getDefaultToolkit().getImage("src/Images/winScreen.PNG");
        spikeGrowth= Toolkit.getDefaultToolkit().getImage("src/Images/spikeGrowth.PNG");
        cannon= Toolkit.getDefaultToolkit().getImage("src/Images/cannon.PNG");
        brick= Toolkit.getDefaultToolkit().getImage("src/Images/brick.JPG");
        attackHere= Toolkit.getDefaultToolkit().getImage("src/Images/attackHere.JPG");
        

        tokens[0] = Toolkit.getDefaultToolkit().getImage("src/Images/ArtificerUToken.PNG");
        tokens[1] = Toolkit.getDefaultToolkit().getImage("src/Images/BarbarianUToken.PNG");
        tokens[2] = Toolkit.getDefaultToolkit().getImage("src/Images/BardUToken.PNG");
        tokens[3] = Toolkit.getDefaultToolkit().getImage("src/Images/ClericUToken.PNG");
        tokens[4] = Toolkit.getDefaultToolkit().getImage("src/Images/DruidUToken.PNG");
        tokens[5] = Toolkit.getDefaultToolkit().getImage("src/Images/MonkUToken.PNG");
        tokens[6] = Toolkit.getDefaultToolkit().getImage("src/Images/RangerUToken.PNG");
        tokens[7] = Toolkit.getDefaultToolkit().getImage("src/Images/RogueUToken.PNG");
        tokens[8] = Toolkit.getDefaultToolkit().getImage("src/Images/WizardUToken.PNG");

        supers[0] = Toolkit.getDefaultToolkit().getImage("src/Images/artificerSuper.JPG");
        supers[1] = Toolkit.getDefaultToolkit().getImage("src/Images/barbarianSuper.JPG");
        supers[2] = Toolkit.getDefaultToolkit().getImage("src/Images/bardSuper.JPG");
        supers[3] = Toolkit.getDefaultToolkit().getImage("src/Images/clericSuper.JPG");
        supers[4] = Toolkit.getDefaultToolkit().getImage("src/Images/druidSuper.JPG");
        supers[5] = Toolkit.getDefaultToolkit().getImage("src/Images/monkSuper.JPG");
        supers[6] = Toolkit.getDefaultToolkit().getImage("src/Images/rangerSuper.JPG");
        supers[7] = Toolkit.getDefaultToolkit().getImage("src/Images/rogueSuper.JPG");
        supers[8] = Toolkit.getDefaultToolkit().getImage("src/Images/wizardSuper.JPG");

        abilities[0]=Toolkit.getDefaultToolkit().getImage("src/Images/a_ArtificerAbility.JPG");
        abilities[1]=Toolkit.getDefaultToolkit().getImage("src/Images/a_BarbarianAbility.JPG");
        abilities[2]=Toolkit.getDefaultToolkit().getImage("src/Images/a_BardAbility.JPG");
        abilities[3]=Toolkit.getDefaultToolkit().getImage("src/Images/a_ClericAbility.JPG");
        abilities[4]=Toolkit.getDefaultToolkit().getImage("src/Images/a_DruidAbility.JPG");
        abilities[5]=Toolkit.getDefaultToolkit().getImage("src/Images/a_MonkAbility.JPG");
        abilities[6]=Toolkit.getDefaultToolkit().getImage("src/Images/a_RangerAbility.JPG");
        abilities[7]=Toolkit.getDefaultToolkit().getImage("src/Images/a_RogueAbility.JPG");
        abilities[8]=Toolkit.getDefaultToolkit().getImage("src/Images/a_WizardAbility.JPG");

        attacks[0]=Toolkit.getDefaultToolkit().getImage("src/Images/a_ArtificerAttack.JPG");
        attacks[1]=Toolkit.getDefaultToolkit().getImage("src/Images/a_BarbarianAttack.JPG");
        attacks[2]=Toolkit.getDefaultToolkit().getImage("src/Images/a_BardAttack.JPG");
        attacks[3]=Toolkit.getDefaultToolkit().getImage("src/Images/a_ClericAttack.JPG");
        attacks[4]=Toolkit.getDefaultToolkit().getImage("src/Images/a_DruidAttackBear.JPG");
        attacks[5]=Toolkit.getDefaultToolkit().getImage("src/Images/a_MonkAttack.JPG");
        attacks[6]=Toolkit.getDefaultToolkit().getImage("src/Images/a_RangerAttack.JPG");
        attacks[7]=Toolkit.getDefaultToolkit().getImage("src/Images/a_RogueAttack.JPG");
        attacks[8]=Toolkit.getDefaultToolkit().getImage("src/Images/a_WizardAttack.JPG");
        attacks[9]=Toolkit.getDefaultToolkit().getImage("src/Images/a_DruidAttackEagle.JPG");

        tiles[0]=Toolkit.getDefaultToolkit().getImage("src/Images/a_tile0.PNG");
        tiles[1]=Toolkit.getDefaultToolkit().getImage("src/Images/a_tile1.PNG");
        tiles[2]=Toolkit.getDefaultToolkit().getImage("src/Images/a_tile2.PNG");
        tiles[3]=Toolkit.getDefaultToolkit().getImage("src/Images/a_tile3.PNG");
        tiles[4]=Toolkit.getDefaultToolkit().getImage("src/Images/a_tile4.PNG");
        tiles[5]=Toolkit.getDefaultToolkit().getImage("src/Images/a_tile5.PNG");
        tiles[6]=Toolkit.getDefaultToolkit().getImage("src/Images/a_tile6 .PNG");

        mute[0] = Toolkit.getDefaultToolkit().getImage("src/Images/muted.png");
        mute[1] = Toolkit.getDefaultToolkit().getImage("src/Images/unmuted.png");
    }
    public static void reset(){
        FlameYpos=Window.getYNormal(Window.getHeight2() / 6+5);
        flameSize=0.45;
    }
    public static void drawUI(Graphics2D g,DominateOrDecease ptr){
        MainObj=ptr;
        drawImage(g,cobble,(Window.getWidth2()/2)+50,Window.getHeight2()/2,0,3,3);
        drawMute(g);
        drawImage(g,logo,(Window.getWidth2()/2),Window.getHeight2()/2,0,0.5,0.5);
        drawFlame(g);
        drawImage(g,START,(Window.getWidth2()/2),Window.getYNormal(Window.getHeight2()/6),0,0.25,0.25);
        if (!DominateOrDecease.displayRules)
            displayPressR(g);
        if(DominateOrDecease.displayRules){
            displayRules(g);
        }
    }
    public static void drawMute(Graphics2D g){
        int imageWidth = (int)(Images.getMuteImage(DominateOrDecease.mute).getWidth(DominateOrDecease.frame)/2.5);
        if(DominateOrDecease.getMute())
            drawImage(g,mute[0],imageWidth/2+20, Window.getY(0)-30,0,0.5,0.5);
        else
            drawImage(g,mute[1],imageWidth/2+20, Window.getY(0)-30,0,0.5,0.5);
    }
    public static void drawCharacterSelect(Graphics2D g,DominateOrDecease ptr){
        MainObj=ptr;
        drawImage(g,numberOfPlayer,(Window.getWidth2()/2),Window.getYNormal(45*Window.getHeight2()/100),0,0.25,0.25);
        g.setColor(Color.white);
        g.setFont(new Font("Times New Roman", Font.PLAIN, 45));
        g.drawString(""+Player.getNumPlayers(),Window.getWidth2()/2-10,Window.getYNormal(45*Window.getHeight2()/100)+65);
        //these two last
        drawFlame(g);
        drawImage(g,START,(Window.getWidth2()/2),Window.getYNormal(Window.getHeight2()/6),0,0.25,0.25);
    }
    public static void drawGame(Graphics2D g,DominateOrDecease ptr){
        if (!DominateOrDecease.displayRules)
            displayPressR(g);
        //last line of code Always
        drawFlame(g);
        if(DominateOrDecease.displayRules){
            displayRules(g);
        }
    }
    public static void drawFlame(Graphics2D g){
        if(Images.getFlameYpos()>Window.getYNormal(Window.getHeight2() / 6 + 5)) {
            Images.setFlameYpos(Window.getYNormal(Window.getHeight2() / 6 + 5));
        }
        drawImage(g, Flame, (Window.getWidth2() / 2),FlameYpos, 0, flameSize, flameSize);
    }
    public static void drawClassCircle(Graphics2D g,CharacterClass ptr,int xpos, int ypos, double rot, double xscale,double yscale){
        for (int i = 0; i<CharacterClass.getClassNames().size(); i++) {
            if(ptr != null && ptr.getName().equals(CharacterClass.getClassNames().get(i)))
                drawImage(g,tokens[i], xpos, ypos, rot, xscale, yscale);
        }
    }
    public static void drawOval(Graphics2D g, int _xpos, int _ypos, double rot, double xscale, double yscale, Color color, boolean fill) {
        int ovalwidth = 130;
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
    public static double getTokenWidth() {
        return tokens[0].getWidth(MainObj) * 0.4;
    }
    public static void drawClassAttackAndAbility(Graphics2D g,CharacterClass ptr,int xpos, int ypos, double rot, double xscale,double yscale){
        //image width
        int xShift=120;
        for (int i = 0; i<CharacterClass.getClassNames().size(); i++) {
            if(ptr != null && ptr.getName().equals(CharacterClass.getClassNames().get(i))) {
                if (i==4) {
                    Druid druid=(Druid)(Player.getCurrentPlayer().getCharacterClass());
                    drawImage(g,supers[4],xpos,ypos-228,rot,xscale,yscale);

                    if(druid.isBear()){
                        drawImage(g,attacks[4], xpos, ypos, rot, xscale, yscale);
                    }
                    else{
                        drawImage(g,attacks[9], xpos, ypos, rot, xscale, yscale);
                    }
                    drawImage(g,abilities[4], xpos+xShift*2, ypos, rot, xscale, yscale);
                } else if(i==6){
                    int width = Board.getCharacterBoxX()/2;
                    int height = 105;

                    int startX = Window.getX(80 * Window.getWidth2() / 100) + width/2; // same x-value as on middle vertical line
                    int startY = Window.getY(72 * Window.getHeight2() / 100)+3+height/2;

                    int boxXpos[] = {startX,startX+width,startX,startX+width};
                    int boxYpos[] = {startY,startY,startY+height,startY+height};
                    drawImage(g,supers[6],xpos,ypos-228,rot,xscale,yscale);
                    drawImage(g,attacks[6], xpos, ypos, rot, xscale, yscale);
                    drawImage(g,abilities[6], xpos+xShift*2, ypos, rot, xscale, yscale);
                    Color grey=new Color(125,125,125,125);
                    Ranger range=(Ranger)(Player.getCurrentPlayer().getCharacterClass());
                    for(int a=0;a<boxYpos.length;a++){
                        //if it isnt unlocked
                        if(!(range.getAllArrTypes()[a]))
                            drawFillShape(g,boxXpos[a],boxYpos[a], 0, 1, 1,grey,width,height,false);
                    }
                    Color green=new Color(0,225,0,50);
                    drawFillShape(g,boxXpos[range.getArrType()],boxYpos[range.getArrType()], 0, 1, 1,green,width-30,height-30,true);
                } else if (i==8) {
                    int startX = Window.getX(60 * Window.getWidth2() / 100)+10;
                    int startY = Window.getY(66 * Window.getHeight2() / 100)+3*abilities[8].getHeight(MainObj)/4+7;
                    drawImage(g,supers[8],xpos,ypos-228,rot,xscale,yscale);
                    drawImage(g,abilities[8], xpos+xShift*2, ypos, rot, xscale, yscale);
                    drawImage(g,attacks[8], xpos, ypos, rot, xscale, yscale);
                    g.setColor(Color.yellow.darker());
                    Wizard wizard =(Wizard)(Player.getCurrentPlayer().getCharacterClass());
                    g.setFont(new Font("Times New Roman", Font.PLAIN, 45));
                    g.drawString(""+wizard.getUpcast(),startX+86,Window.getY(Window.getHeight2()-17));

                } else if (i==0) {
                    int startX = Window.getX(80 * Window.getWidth2() / 100)+15;
                    int startY = Window.getY(90 * Window.getHeight2() / 100)+6;
                    int width = 209;
                    int height = Window.getY(Window.getHeight2()) - startY;
                    drawImage(g,supers[0],xpos,ypos-228,rot,xscale,yscale);
                    drawImage(g, abilities[0], xpos + xShift * 2, ypos, rot, xscale, yscale);
                    drawImage(g, attacks[0], xpos, ypos, rot, xscale, yscale);
                    g.setColor(Color.lightGray.brighter());
                    Artificer artificer = (Artificer) (Player.getCurrentPlayer().getCharacterClass());
                    g.setFont(new Font("Times New Roman", Font.PLAIN, 45));
                    g.drawString(""+artificer.cannonLevel(),startX+width/2-10,Window.getY(Window.getHeight2())-25);

                }
                else {
                    drawImage(g,supers[i],xpos,ypos-228,rot,xscale,yscale);
                    drawImage(g,abilities[i], xpos+xShift*2, ypos, rot, xscale, yscale);
                    drawImage(g,attacks[i], xpos, ypos, rot, xscale, yscale);
                }
                g.setColor(Color.black);
                g.setStroke(new BasicStroke(2));
                g.drawRect(Window.getX(60 * Window.getWidth2() / 100), Window.getY(38 * Window.getHeight2() / 100)+2,Board.getCharacterBoxX(),Board.getCharacterBoxY()*2+20);
                g.drawRect(Window.getX(60 * Window.getWidth2() / 100), Window.getY(38 * Window.getHeight2() / 100)+2+Board.getCharacterBoxY()*2+20,Board.getCharacterBoxX()*2,Board.getCharacterBoxY()*2+60);
//            Window.getY(66 * Window.getHeight2() / 100) + characterBoxY + 35
                g.setStroke(new BasicStroke(1));
                g.drawLine(Window.getX(60 * Window.getWidth2() / 100)+Board.getCharacterBoxX(),Window.getY(38 * Window.getHeight2() / 100)+2+Board.getCharacterBoxY()*2+20,Window.getX(60 * Window.getWidth2() / 100)+Board.getCharacterBoxX(),Window.getY(Window.getHeight2()));
                Board.drawOrbTracker(g,Window.getX(70 * Window.getWidth2() / 100)+(Board.getCharacterBoxX()/8)-Window.getX(0)-2,325+(Board.getCharacterBoxY())+40);
            }
        }
    }

    private static void drawImage(Graphics2D g,Image image, int xpos, int ypos, double rot, double xscale, double yscale) {
        int width = image.getWidth(MainObj);
        int height = image.getHeight(MainObj);
        g.translate(xpos,ypos);
        g.rotate(rot  * Math.PI/180.0);
        g.scale( xscale , yscale );

        g.drawImage(image,-width/2,-height/2, width,height,MainObj);

        g.scale( 1.0/xscale,1.0/yscale );
        g.rotate(-rot  * Math.PI/180.0);
        g.translate(-xpos,-ypos);
    }
    private static void drawFillShape(Graphics2D g, int _xpos, int _ypos, double rot, double xscale, double yscale, Color color, int width, int height,boolean circle) {
        g.translate(_xpos,_ypos);
        g.rotate(rot  * Math.PI/180.0);
        g.scale( xscale , yscale );
        if(circle) {
            g.setColor(color);
            if(width<height)
                height=width;
            else{
                width=height;
            }
            g.fillOval(-width / 2, -height / 2, width, height);
        }
        else {
            g.setColor(color);
            g.fillRect(-width / 2, -height / 2, width, height);
        }

        g.scale( 1.0/xscale,1.0/yscale );
        g.rotate(-rot  * Math.PI/180.0);
        g.translate(-_xpos,-_ypos);

    }
    public static void drawGameOver(Graphics2D g){
        drawImage(g,winScreen, Window.getX(Window.getWidth2())/2+Window.getX(0)/2, Window.getY(Window.getHeight2())/2, 0, 1.12, 1.12);
    }
    public static void displayRules(Graphics2D g){
        drawImage(g,rulesDorD, Window.getX(Window.getWidth2())/2+Window.getX(0)/2, Window.getY(Window.getHeight2())/2+50, 0, 1.01, 1.01);
    }
    public static void displayPressR(Graphics2D g){
        drawImage(g,pressR, Window.getX(Window.getWidth2())/10, 95*Window.getY(Window.getHeight2())/100-10, 0, 0.4, 0.4);
    }
    public static void drawTileImage(Graphics2D g,int xpos,int ypos, int whichTile) {
        drawImage(g, tiles[whichTile], xpos, ypos, 0, 1, 1);
    }
    public static void drawAttackHere(Graphics2D g,int xpos,int ypos){
        drawImage(g, attackHere, xpos, ypos, 0, 0.9, 0.9);
    }
    public static void drawBrick(Graphics2D g,int xpos,int ypos){
        drawImage(g,brick, xpos,ypos, 0, 1, 1);
    }
    
    public static void drawSpikes(Graphics2D g, int xpos, int ypos){
        drawImage(g,spikeGrowth,xpos,ypos,0,1,1);
    }
    public static void drawCannon(Graphics2D g, int xpos, int ypos){
        drawImage(g,cannon,xpos,ypos,0,1,1);
    }
    public static int[] imageSize(Image image){
        int[] size={image.getWidth(MainObj),image.getHeight(MainObj)};
        return size;
    }
    public static Image getStartImage(){
        return START;
    }
    public static void setFlameSize(double newSize){
        flameSize=newSize;
    }
    public static double getFlameSize(){
        return flameSize;
    }
    public static void setFlameYpos(int newLocation){
        FlameYpos=newLocation;
    }
    public static int getFlameYpos(){
        return FlameYpos;
    }
    public static Image getMuteImage(boolean on) {
        if (on)
            return mute[0];
        else
            return mute[1];
    }
}
