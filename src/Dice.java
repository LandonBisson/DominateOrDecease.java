import javax.swing.*;
import java.awt.*;

public class Dice { //just need to put in images
    private static Image[] sixDie = new Image[6];
    public static Image[] coin = new Image[2];
    private static Image[] fourDie = new Image[4];
    private static int[] ranInt;
    private static Image[] gifs = new Image[3];
    private static int total;
    private static int numDie;
    private static int sides;
    private static int add;

    private static boolean isRolling;

    public static boolean firstDraw = true;

    private static Image[] staticDice;

    private static Image rightBracket;
    private static Image leftBracket;

    private static boolean barbRage;

    public static void Init(){
        sixDie[0] = new ImageIcon("src/Images/d6_1.png").getImage();
        sixDie[1] = new ImageIcon("src/Images/d6_2.png").getImage();
        sixDie[2] = new ImageIcon("src/Images/d6_3.png").getImage();
        sixDie[3] = new ImageIcon("src/Images/d6_4.png").getImage();
        sixDie[4] = new ImageIcon("src/Images/d6_5.png").getImage();
        sixDie[5] = new ImageIcon("src/Images/d6_6.png").getImage();

        coin[0] = new ImageIcon("src/Images/coinTails.png").getImage();
        coin[1] = new ImageIcon("src/Images/coinHeads.png").getImage();

        fourDie[0] = new ImageIcon("src/Images/d4_1.png").getImage();
        fourDie[1] = new ImageIcon("src/Images/d4_2.png").getImage();
        fourDie[2] = new ImageIcon("src/Images/d4_3.png").getImage();
        fourDie[3] = new ImageIcon("src/Images/d4_4.png").getImage();

        gifs[2] = new ImageIcon("src/Images/d6.GIF").getImage();
        gifs[0] = new ImageIcon("src/Images/coin.GIF").getImage();
        gifs[1] = new ImageIcon("src/Images/d4.GIF").getImage();

        rightBracket = new ImageIcon("src/Images/rightBracket.png").getImage();
        leftBracket = new ImageIcon("src/Images/leftBracket.png").getImage();
    }

    public static int RollDie(int _numDie, int _sides, int _add) {
        isRolling=true;
        ranInt = new int[_numDie];
        numDie = _numDie;
        sides = _sides;
        add = _add;
        firstDraw = true;
        total = 0;
        barbRage = false;
        staticDice = new Image[numDie];
        for (int i = 0; i < numDie; i++) {
            ranInt[i] = (int) ((Math.random() * sides) + 1);
            total += ranInt[i];
        }
        total += add;
        if (Player.getCurrentPlayer().getCharacterClass() instanceof Barbarian) {
            Barbarian ptr = (Barbarian)(Player.getCurrentPlayer().getCharacterClass());
            if (ptr.isRaging()) {
                total = (int)(1.5 *total);
                barbRage = true;
            }
        }
        return total;
    }

    public static void DrawDice(Graphics2D g) {
        for (int i = 0; i<numDie; i++) {
            if (sides == 6) {
                staticDice[i] = sixDie[ranInt[i] -1];
            } else if (sides == 4) {
                staticDice[i] = fourDie[ranInt[i] -1];
            } else {
                staticDice[i] = coin[ranInt[i] -1];
            }
        }
        if (staticDice != null) {
            DrawRollingDice(g, staticDice);
        }
    }


    private static void DrawRollingDice(Graphics2D g, Image[] image) {
        Init();
        int startX = Window.getX(80 * Window.getWidth2() / 100); // same x-value as on middle vertical line
        int boxWidth = Window.getX(Window.getWidth2()) - startX; //(212)

        int boxHeight =  (Window.getY(66 * Window.getHeight2() / 100)-372);
        int startY = 372 + boxHeight/2; //middle yvalue of dice box (468)

        int width = image[0].getWidth(DominateOrDecease.frame);
        int height = image[0].getHeight(DominateOrDecease.frame);

        if (sides==4) {
            width=135*image[0].getWidth(DominateOrDecease.frame)/100;
            height=135*image[0].getHeight(DominateOrDecease.frame)/100;
        }
//        int []imageX=new int[] {startX+boxWidth/2-width/2, startX+width/2,startX+boxWidth-3*width/2};
//        int []imageY=new int[] {startY-30,372+height/2,372+height/2};
        int []imageX=new int[] {startX+15,startX+15, startX+20+width};
        int []imageY=new int[] {372+15,372+20+width,372+15};

        g.setColor(Color.white);
        g.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        if (barbRage) {
            g.drawString("*  "+"1.5",Window.getX(Window.getWidth2())-70,imageY[1]-10);
            g.drawImage(leftBracket,startX+5,372+5,120,140,DominateOrDecease.frame);
            g.drawImage(rightBracket,startX+35,372+5,120,140,DominateOrDecease.frame);
        }

        if (firstDraw) {

            for (int i = 0; i< numDie;i++) {

                g.drawImage(gifs[sides/3],imageX[i], imageY[i], width,height,DominateOrDecease.frame);

            }

            new Timer(2000, e -> {
                firstDraw = false;
                isRolling=false;
            }) {{
                setRepeats(false);
                start();
            }};
        } else {
            for (int i = 0; i< numDie;i++) {
                g.drawImage(image[i],imageX[i], imageY[i], width,height,DominateOrDecease.frame);
            }
            CharacterClass.finalizeDamage();

//            g.drawString("+ " + add,imageX[2],startY+boxHeight/2-20);
//            g.drawString("= " + total,imageX[2],startY+boxHeight/2);

            if (add != -1) {
//                g.drawString("+ " + add,imageX[2],startY+boxHeight/2-20);
                g.drawString("+ " + add,imageX[1]+3*width/2,imageY[1]+40);

                g.drawString("Total:  " + total,imageX[2]+30,startY+boxHeight/2-15);

            }
        }
    }
    public static boolean isRolling(){
        return isRolling;
    }
    public static void Reset(){
        numDie=0;
        add=-1;
    }
}
