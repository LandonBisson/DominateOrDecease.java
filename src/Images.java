import java.awt.*;

public class Images {
    private static Image logo;
    //this is where I will create all the images, this class will just focus on all of that
    //I am going to working on the logo and the UI now
    public static void Init(){
        logo = Toolkit.getDefaultToolkit().getImage("src/Images/DominateOrDecease.PNG");
    }
    public static void drawUI(Graphics2D g,DominateOrDecease ptr){
        drawImage(g,ptr,logo,(5*Window.getWidth2()/6),Window.getHeight2()/2,0,1,1);
    }
    public static void drawGame(Graphics2D g,DominateOrDecease ptr){

    }

    private static void drawImage(Graphics2D g, DominateOrDecease thisObj, Image image, int xpos, int ypos, double rot, double xscale, double yscale) {
        int width = image.getWidth(thisObj);
        int height = image.getHeight(thisObj);
        g.translate(xpos,ypos);
        g.rotate(rot  * Math.PI/180.0);
        g.scale( xscale , yscale );

        g.drawImage(image,-width/2,-height/2, width,height,thisObj);

        g.scale( 1.0/xscale,1.0/yscale );
        g.rotate(-rot  * Math.PI/180.0);
        g.translate(-xpos,-ypos);
    }
}
