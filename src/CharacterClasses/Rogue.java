class Rogue extends CharacterClass{
    private static final int reach = 0;
    private static final String className = "Rogue";
    Rogue(){
        super(className);
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
        int lifeSteal = RollDie(1,6,0);

        DealDamage(lifeSteal,ptr);
        Heal(lifeSteal);
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
