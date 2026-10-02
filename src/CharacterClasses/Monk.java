class Monk extends CharacterClass{
    private static final int reach = 0;
    private static final String className = "Monk";
    private int FoB = 1;
    private int mod = 1;
    private int Meditate;
    private int healthSave;
    //0 means its a person attack, 1 is a tile attack, and 2 is no attack or attack everyone(aka nothing passed in)
    private static int[] _selectingTile={0,2,2};

    
    Monk(){
        super(className,0,_selectingTile,2);
    }
    ///even indexes are names, odd indexes are decriptions

    String[] getAttackName() {
        String[] attacks = {"Flurry of Blows", "A series of two hits that increase by 1 damage but reset every turn."};
        return attacks;
    }

    String[] getAbilityName() {
        String[] abilities = {"Meditate", "50% you don't get hit until your next turn and recover 1-4 health."};
        return abilities;
    }

    String[] getSuperMoveName() {
        String[] superMove = {"Ultimate Form", "For this turn, add 4 damage per punch."};
        return superMove;
    }
////

    ////first is damage dealt, second is health healed, other effects can be called
    void Attack(Player ptr) {
        DominateOrDecease.setDoubleAttacker(true);
        FoB += mod;
        DealDamage(FoB, ptr);
        attackCount--;
        if (attackCount<=0)
            DominateOrDecease.setDoubleAttacker(false);
    }
    //falcon attack

    void Ability() {
        Meditate = 1;
        Heal(Dice.RollDie(1,4,0));
    }

    void SuperMove() {
        Player randomPlayer=Player.getPlayer((int)(Math.random()*Player.getNumPlayers()));
        while(randomPlayer.equals(Player.getCurrentPlayer())){
            randomPlayer=Player.getPlayer((int)(Math.random()*Player.getNumPlayers()));
        }
        Player.getCurrentPlayer().MoveAnywhere(randomPlayer.currTile(),false);
        mod = 4;
    }
    void EndTurn(){

        FoB = 1;
        mod = 1;
        if (Meditate == 2)
            Meditate = 0;
        else if (Meditate == 1){
            Meditate++;
        }
        attackCount = 2;
    }
    public boolean isMeditating(){
        if (Meditate > 0)
            return true;
        else
            return false;
    }
////

    //////////////ACTUAL IMPLEMENTATIONS HERE
    ////all these are shifted up one because they are the damage calculators, pass an int into

////each of these to get the added modifier complete
    // d2 (coin flip animation) and get random number 1 or 2
    // d4 (dice animation) and get random number 1 or 2 or 3 or 4
    // d6 (dice animation) and get random number 1 or 2 or 3 or 4 or 5 or 6
////

}
