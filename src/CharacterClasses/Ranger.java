
class Ranger extends CharacterClass{
    private static final int reach = 2;
    private static final String className = "Ranger";
    private static int modifier;
    Ranger(){
        super(className);
    }

    ///even indexes are names, odd indexes are decriptions
    String[] getAttackName() {
            String[] attacks;
            if (4 - Player.getAlivePlayers() > 0)
                modifier = 4 - Player.getAlivePlayers();
            else
                modifier = 0;
    
            attacks = new String[] {"Magic Arrow", "Hits everybody in a row of tiles for " + (1+modifier) + "-" + (4+modifier) + "damage" + "."};
    
            return attacks;
    }

    String[] getAbilityName() {
        String[] abilities = {/*insert arrow types*/};
        return abilities;
    }

    String[] getSuperMoveName() {
        String[] superMove = {"Sniper", "Next arrow shot will combine all types and fire two arrows at the farthest tile."};
        return superMove;
    }
////

    ////first is damage dealt, second is health healed, other effects can be called
    void Attack() {
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

