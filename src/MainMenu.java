import java.awt.*;
public class MainMenu{
private static boolean PassedMenu = true;
private static boolean PassedCSelect = true;
    MainMenu(){
    }

    public static void DrawMenu(Graphics2D g, DominateOrDecease ptr){
        Images.drawUI(g,ptr);

    }
    public static void DrawCSelect(Graphics2D g, DominateOrDecease ptr){
        Images.drawUI(g,ptr);

    }
    public static void ClickMenu(int mouseX, int mouseY){

    }
    public static void ClickCSelect(int mouseX, int mouseY){

    }



    public static boolean getPassedMenu(){
        return PassedMenu;
    }
    public static boolean getPassedCSelect(){
        return PassedCSelect;
    }


}

