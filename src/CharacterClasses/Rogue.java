class Rogue extends CharacterClass{
    private static final String className = "Rogue";
    private boolean isSneaking;
    //0 means its a person attack, 1 is a tile attack, and 2 is no attack or attack everyone
    private static int[] _selectingTile={0,2,2};
    Rogue(){
        super(className,0,_selectingTile,0);
        isSneaking = false;
    }


    ///even indexes are names, odd indexes are decriptions
    String[] getAttackName() {
        String[] attacks = {"Lifesteal", "Steal 1-6 health from one person."};
        return attacks;
    }


    String[] getAbilityName() {
        String[] abilities = {"Sneak", "Cannot be hit until you attack by anyone on your tile."};
        return abilities;
    }


    String[] getSuperMoveName() {        //can somebody replcae the 0 below to the number of players? i don't know how to call it
        int val = 11 - Player.getNumPlayers();
        String[] superMove = {"The Heist", "Steal " + val + " health from every player."};
        return superMove;
    }
////


    ////first is damage dealt, second is health healed, other effects can be called
    void Attack(Player ptr) {
        isSneaking = false;
        int lifeSteal = Dice.RollDie(1,6,0);


        DealDamage(lifeSteal,ptr);
        Heal(lifeSteal);
    }


    void Ability() {
        // make isSneaking be false  damage is dealt
        isSneaking = true;
    }


    void SuperMove() {
        isSneaking = false;
        int lifeSteal = 13 - Player.getNumPlayers();
        int totalDamage = 0;
        for (int i = 0;i < Player.getNumPlayers();i++){
            if (Player.getPlayer(i) != Player.getCurrentPlayer()){
                System.out.print(totalDamage);
                DealDamage(lifeSteal,Player.getPlayer(i));
                totalDamage+=lifeSteal;
            }
        }
        Heal(totalDamage);
    }
    void EndTurn(){

    }
    public boolean isSneaking() {return isSneaking;}
    public void setSneaking(boolean _sneak) {isSneaking=_sneak;}
}

