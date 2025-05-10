
adabdasdhahsdgasdasdads 


import java.awt.*;
import java.awt.Image;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;
// does this work? testing how to udtate code

//Write a short description of what you have been doing as well
//Landon Bisson Time=11.5 hours (Designed the logo, fixed players dsiplayed, helped create acessors to start getting attcks ready)
//Wesley Richey Time=2 hours (made FindAdjacent, Heal, ModifyHealth, and GetHealth)
//Niko Calabro Time=5 hours (did a lot of chracterclass stuff like reorginzation and naming some atacks/abilities)
//Coltrane Creed Time=
public class DominateOrDecease extends JFrame implements Runnable {
    boolean animateFirstTime = true;
    Image image;
    Graphics2D g;
    static int MouseX;
    static int MouseY;
    static int CurMouseHex;
    static final int frameRate = 50;

    public static void main(String[] args) {
        DominateOrDecease frame = new DominateOrDecease();
        frame.setSize(Window.WINDOW_WIDTH, Window.WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public DominateOrDecease() {
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {

                if (e.BUTTON1 == e.getButton() ) {
                    if (MainMenu.getPassedMenu()){
                        if (MainMenu.getPassedCSelect())
                            Player.getNextPlayer();

                        else
                            MainMenu.ClickCSelect(e.getX(), e.getY());
                    }
                    else
                        MainMenu.ClickMenu(e.getX(), e.getY());
                }
                if (e.BUTTON3 == e.getButton()) {
                    reset();
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
                repaint();
                MouseX=e.getX();
                MouseY=e.getY();
                CurMouseHex = Board.findHexagon(e.getX(),e.getY());
            }
        });

        addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
                if (e.VK_UP == e.getKeyCode()) {
                } else if (e.VK_DOWN == e.getKeyCode()) {
                } else if (e.VK_LEFT == e.getKeyCode()) {
                } else if (e.VK_RIGHT == e.getKeyCode()) {
                } else if (e.VK_ESCAPE == e.getKeyCode()) {
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

        g.setColor(Color.gray);
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
        if (MainMenu.getPassedMenu()){
            if (MainMenu.getPassedCSelect())
                Board.Draw(g);
            else
                MainMenu.DrawCSelect(g,this);
        }

        else
            MainMenu.DrawMenu(g,this);
//         Images.drawUI(g,this);


//All of this is centered in the middle y and the first 1/3 of the screen, this is where the center of the baord should be
        //////////////testing STUFF
        int numAdjacent=0;
        for(int i=0;i<Tile.findAdjacent(CurMouseHex,false).length;i++){

            numAdjacent+=Tile.getTileNumPlayer(Tile.findAdjacent(CurMouseHex,false)[i]);
        }
        g.setColor(Color.black);
        int hypotenuse=((MouseX-Board.getBoardMiddleX())* (MouseX-Board.getBoardMiddleX()))+((MouseY-Window.getHeight2()/2)*(MouseY-Window.getHeight2()/2));
//         g.drawString("("+MouseX+","+MouseY+")"+" Hyp = "+(int)Math.sqrt(hypotenuse)+" Current Hex: "+Board.findHexagon(MouseX,MouseY),MouseX,MouseY);
        g.drawString("("+MouseX+","+MouseY+")"+" Hyp = "+(int)Math.sqrt(hypotenuse)+" Current Hex: "+CurMouseHex+" With "+Tile.getTileNumPlayer(CurMouseHex) +" People on it and "+numAdjacent+" adjacent",MouseX,MouseY);
        //creates red triangle example
        g.setColor(Color.red);
        g.drawLine(MouseX,MouseY,Window.getX(Window.getWidth2()/3),Window.getY(Window.getHeight2()/2));
        g.drawLine(MouseX,MouseY,MouseX,Window.getY(Window.getHeight2()/2));
        g.drawLine(MouseX,Window.getY(Window.getHeight2()/2),Window.getX(Window.getWidth2()/3),Window.getY(Window.getHeight2()/2));
        g.setColor(Color.black);
        /////////////////////

        Images.drawUI(g,this);


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
        Board.create();//also sets numplayers to 0
        //////////////////////////Above is all the stuff for reseting, below is setup after the Ui is gone through
        Board.create();
        //create players after board ALWAYS
        Player.CreatePlayer(6);
    }
    /////////////////////////////////////////////////////////////////////////
    public void animate() {

        if (animateFirstTime) {
            animateFirstTime = false;
            if (Window.xsize != getSize().width || Window.ysize != getSize().height) {
                Window.xsize = getSize().width;
                Window.ysize = getSize().height;
            }
            Images.Init();
            reset();

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
    private void drawFillRect(Graphics2D g, int _xpos, int _ypos, double rot, double xscale, double yscale,Color color) {
        g.translate(_xpos,_ypos);
        g.rotate(rot  * Math.PI/180.0);
        g.scale( xscale , yscale );

        g.setColor(color);
        g.fillRect(-25,-25, 50,50);

        g.scale( 1.0/xscale,1.0/yscale );
        g.rotate(-rot  * Math.PI/180.0);
        g.translate(-_xpos,-_ypos);

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
