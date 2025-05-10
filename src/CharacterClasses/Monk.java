class Monk extends CharacterClass{
    private static final int reach = 0;
    private static final String className = "Monk";
    Monk(){
        super(className);

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
        
    }

    void Ability() {
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
