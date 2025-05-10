class Bard extends CharacterClass {
    private int damageMod;
    private static final int reach = 1;
    private static final String className = "Bard";

    Bard(){
        super(className);
    }


    ///even indexes are names, odd indexes are decriptions
    String[] getAttackName() {
            String[] attacks = {"Push", "Push an enemy one tile and deal 3-6 damage."};
            return attacks;
    }

    String[] getAbilityName() {
        String[] abilities = {"Rhythmic Recovery", "Heal 3-8 health and your next attack does 2 additional damage."};
        return abilities;
    }

    String[] getSuperMoveName() {
        String[] superMove = {"Supersonic", "Push every character 2 tiles and deal 3-8 damage. Also, gain immunity for next 2 turns."};
        return superMove;
    }
////

    ////first is damage dealt, second is health healed, other effects can be called
    void Attack() {
        //WR write an if statement here to test if a player is on the same tile, then push them
        //DealDamage((int)(RollDie(1,4,2))+damageMod,reach);
        if (damageMod == 2)
            damageMod = 0;

    }

    void Ability() {
        damageMod = 2;
        Heal((RollDie(1,6,2)));
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
