class Barbarian extends CharacterClass{
    private static final String className = "Barbarian";
    //0 means its a person attack, 1 is a tile attack, and 2 is no attack or attack everyone(aka nothing passed in)
    private static int[] _selectingTile={0,2,2};
    private double[] RAGE = {1,1,-1};
    //  rage modifier, health defence, RAGE count
    Barbarian(){
        super(className,0,_selectingTile,0);
    }




    ///even indexes are names, odd indexes are decriptions
    String[] getAttackName() {
        String[] attacks = {"Axe Swing", "Deal 2-12 damage to a player."};
        return attacks;
    }


    String[] getAbilityName() {
        String[] abilities = {"Rage", "For 2 turns, deal 1.5x damage and take 0.5 less damage. Consecutive uses cost 3-9 health."};
        return abilities;
    }


    String[] getSuperMoveName() {
        String[] superMove = {"Extra-Rage", "Obtain rage with health loss and gain an Action Point."};
        return superMove;
    }
////


    ////first is damage dealt, second is health healed, other effects can be called
   void Attack(Player ptr) {
        int damage=(Dice.RollDie(2,6,0));
        DealDamage(damage,ptr);
   }
    


    void Ability() {
        //taking damage because you used rage too soon
        if(RAGE[2] > -1){
            Player.getCurrentPlayer().modifyHealth(-RollDie(1,6,2));
        }
        //defence boost
        RAGE[0] = 1.5;
        //damage boost
        RAGE[1] = 2;
        //turns of rage
        RAGE[2] = 2.0;
        RAGE[2] = 2.0;
    }


    void SuperMove() {
        Player.getCurrentPlayer().addActionPoints(1);
        RAGE[2] = -1;
        Ability();
    }
    void EndTurn(){
        RAGE[2] -= 1.0;
        if (RAGE[2] <= -1.0) {
            for (int i = 0; i < RAGE.length; i++)
                RAGE[i] = 1;
            RAGE[2] = -1;
        }
    }
    public boolean isRaging(){
        if(RAGE[2] > 0){
            return true;
        }
        return false;
    }
////
    public double[] getRage() {
    return RAGE;
}

    //////////////ACTUAL IMPLEMENTATIONS HERE
    ////all these are shifted up one because they are the damage calculators, pass an int into


////each of these to get the added modifier complete
    // d2 (coin flip animation) and get random number 1 or 2
    // d4 (dice animation) and get random number 1 or 2 or 3 or 4
    // d6 (dice animation) and get random number 1 or 2 or 3 or 4 or 5 or 6
////


}

