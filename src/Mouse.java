
public class Mouse {
    //0 means mouse move, 1 is attack, 2 is ability, 3 is super
    private static int attackType;

    private static boolean attackTile;
    
    public static void setAttackTile(boolean attackingTile){
        attackTile=attackingTile;
    }
    public static void setAttackType(int type, boolean doubleAttacker){
        if (doubleAttacker) {
            System.out.print("returned cause double attaker");
            return;
        }

        if (type != 0 && attackType != type) {
            //play all the corresponding sound effects
            if (Player.getCurrentPlayer().getCharacterClass() instanceof Druid && type==1) {
                Druid ptr = (Druid)(Player.getCurrentPlayer().getCharacterClass());
                if (ptr.isBear())
                    Sounds.playCurrentSound(Player.getCurrentPlayer().getCharacterClass(), 0);
                else
                    Sounds.playCurrentSound(Player.getCurrentPlayer().getCharacterClass(), -1);
            }
            else
                Sounds.playCurrentSound(Player.getCurrentPlayer().getCharacterClass(), type-1);
        }
        attackType=type;
    }
    public static int getAttackType(){
        return attackType;
    }
    public static boolean isAttackTile(){
        return attackTile;
    }
}
