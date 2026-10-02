import java.util.ArrayList;

abstract class CharacterClass {
    private String getName;
    ////0 index is the name, second index is decription
    abstract String[] getAttackName();
    abstract String[] getAbilityName();
    abstract String[] getSuperMoveName();
    private int[] selectingTile;
    protected static int tileDamageBoost;
    private static ArrayList<String> classNames = new ArrayList<>();
    private String name;
    protected int reach;
    protected int attackCount;
    protected int maxAttackCount;
    private int turnsLeft;
////
    private static int numHeal;
    private static int numDamage;
    protected static ArrayList<Player> targetedPlayers = new ArrayList<Player>();

    ////first is damage dealt, second is health healed, other effects can be called
    void SuperMove(){
        System.out.println("Calling Super Move No Parameters When Should Not Be Used");
    }
    void SuperMove(int tile){
        System.out.println("Calling Tile Super Move When Should Not Be Used");
    }
    abstract void Ability();
    abstract void EndTurn();
    void Attack(int tile){
        System.out.println("Calling Tile Attack When Should Not Be Used");
    }
    void Attack(Player ptr){
            System.out.println("Calling Player Target Attack When Should Not Be Used");
    }

////
    public CharacterClass(String _name, int _reach,int [] select,int count) {
        name=_name;
        reach=_reach;
        selectingTile=select;
        attackCount=count;
        maxAttackCount=count;
        if (classNames.size() < 9)
                classNames.add(_name);
    }
    public static ArrayList<String> getClassNames () {
        return classNames;
    }
    public String getName () {
        return name;
    }
    public int getReach(){
        return reach;
    }

    public static void DealDamage(int damage,Player ptr){
            targetedPlayers.add(ptr);
            numDamage= -(damage+tileDamageBoost);
            tileDamageBoost=0;
    public static void finalizeDamage(){
        for(int i = 0; i < targetedPlayers.size(); i++) {
            targetedPlayers.get(i).modifyHealth(Player.hitCheck(targetedPlayers.get(i),numDamage));
            if(Player.getCurrentPlayer().getCharacterClass().getName().equals("Rogue"))
                numHeal=-Player.hitCheck(targetedPlayers.get(i),numDamage);
        }
        Player.getCurrentPlayer().modifyHealth(numHeal);
        numHeal=0;
        numDamage=0;
        targetedPlayers.clear();
    }
    }
    public static int RollDie(int NumDie, int DieSize,int add){
        int ret=0;
        while (NumDie > 0){
            ret += (int)(Math.random()*DieSize)+1;
            NumDie--;
        }
        return ret + add;
    }
    public void endTurn(){
        if(turnsLeft<=0)
            turnsLeft--;
    }
    public int[] getSelectingTile(){
        return selectingTile;
    }
    public int getAttackCount(){
        return attackCount;
    }
    public int getMaxAttackCount(){
        return maxAttackCount;
    }
    public void setAttackCount(int _setAttackCount){
        attackCount = _setAttackCount;
    }
    public void resetAttackCount(){
        attackCount = maxAttackCount;
    }
    public void setTileDamageBoost(int mod){
        tileDamageBoost = mod;
    }
    public int getTileDamageBoost(){
        return tileDamageBoost;
    }
    public static CharacterClass[] getAllClasses(){
       CharacterClass[] classes = new CharacterClass[]{new Artificer(),new Barbarian(),new Bard(),
                       new Cleric(),new Druid(),new Monk(),new Ranger(),new Rogue(),new Wizard()};
        return classes;
   }

//ignore what i am doing here
    //////////////ACTUAL IMPLEMENTATIONS HERE
    ////all these are shifted up one because they are the damage calculators, pass an int into

////each of these to get the added modifier complete
    // d2 (coin flip animation) and get random number 1 or 2
    // d4 (dice animation) and get random number 1 or 2 or 3 or 4
    // d6 (dice animation) and get random number 1 or 2 or 3 or 4 or 5 or 6
////

}