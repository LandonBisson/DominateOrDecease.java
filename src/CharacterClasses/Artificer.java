class Artificer extends CharacterClass {
    private static final int reach = 1;
    private static final String className = "Artificer";
    Artificer(){
        super(className);
    }

    ///even indexes are names, odd indexes are decriptions
    String[] getAttackName() {
            String[] attacks = {"Potion", "Select a tile to deal 1-2 damage on hit and 1 damage  with movement in that tile."};
            return attacks;
    }

    String[] getAbilityName() {
        String[] abilities = {"Auto Cannon", "Create a cannon on current tile that lasts for 3 turns with 3 health and deals 2-5 damage to nearest player."};
        return abilities;
    }

    String[] getSuperMoveName() {
        String[] superMove = {"Super Cannon", "Create an Auto Cannon on every tile with 5 health instead of 3."};
        return superMove;
    }
////

    ////first is damage dealt, second is health healed, other effects can be called
    void Attack() {
        //DealDamage(RollDie(1,6,0),0);
    }

    void  Ability() {
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


