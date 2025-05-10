class Cleric extends CharacterClass{
    private static final int reach = 1;
    private static final String className = "Cleric";
    Cleric(){
        super(className);

    }


    ///even indexes are names, odd indexes are decriptions
    String[] getAttackName() {
            String[] attacks = {"Heavens Light", "Deal 3-6 damage."};
            return attacks;
    }

    String[] getAbilityName() {
        String[] abilities = {"Great Restoration", "If not used last turn, heal 3-18 health, otherwise, 1-6 heath"};
        return abilities;
    }

    String[] getSuperMoveName() {
        String[] superMove = {"Burst of Radiance", "Permanently add 5 to total health, heal to full health, and every player on your tile takes 3-6 damage."};
        return superMove;
    }
////

    ////first is damage dealt, second is health healed, other effects can be called
    void Attack(Player ptr) {
        DealDamage(RollDie(1,4,2),ptr);
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

