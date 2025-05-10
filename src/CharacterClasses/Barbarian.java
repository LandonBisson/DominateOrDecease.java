class Barbarian extends CharacterClass{
    private static final int reach = 0;
    private static final String className = "Barbarian";
    private double[] RAGE = {0,0,0};
    //  rage modifier, health defence, RAGE count
    Barbarian(){
        super(className);
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
    void Attack() {
        //DealDamage((int)(RollDie(2,6,0)*RAGE[0]),reach);

        RAGE[2] -= 1.0;
        if (RAGE[2] <= 0){
            for (int i=0;i<RAGE.length;i++) {RAGE[i] = 0;}
        }
    }

    void Ability() {
        if(RAGE[2] > 0){}
//WR Make player lose health if the above if statement is true
        RAGE[0] = 1.5;
        RAGE[1] = 0.5;
        RAGE[2] = 2.0;
    }

    void SuperMove() {
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
