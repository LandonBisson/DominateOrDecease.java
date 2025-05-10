class Wizard extends CharacterClass{
    private static int reach = 1;
    private int upcast = 1;
    private static final String className = "Wizard";
    Wizard(){
        super(className);
    }

    ///even indexes are names, odd indexes are decriptions
    String[] getAttackName() {
        String[] attacks = {"Fireball", "Hits everybody on the tile for 1-6 damage"};
        return attacks;
    }

    String[] getAbilityName() {
        String[] abilities = {"Sheild", "For 2 turns, shield the entrance and exit of a chosen tile to prevent any incoming damage."};
        return abilities;
    }

    String[] getSuperMoveName() {
        String[] superMove = {"Meteor", "Everyone else takes 9-19 damage"};
        return superMove;
    }
    
    void Attack() {
        System.out.println("Wizard Fireball needs to have tile passed into attack to attack");
    }
////

    ////first is damage dealt, second is health healed, other effects can be called
    void Attack(int tileTargeted) {
        int totalDamage=RollDie(upcast,6,0);
        for (Player ptr : Tile.getTile(tileTargeted).getPlayerPtrs())
            DealDamage(totalDamage,ptr);
        upcast=1;
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
