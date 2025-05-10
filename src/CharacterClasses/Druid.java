public class Druid extends CharacterClass{

    private static final int[] attacks = {0,2}; //Ranger: Animal Strike--> own tile (0); Falcon Dive--> any (2)
    private static final String className = "Druid";

    Druid(){
        super(className);

    }

    ///even indexes are names, odd indexes are decriptions
    String[] getAttackName() {
        String[] attacks = {"Animal Strike", "1-4 damage to two enemies.", "Falcon Dive", "Choose a tile and do 1 damage to one random person on that tile."};
        return attacks;
    }

    String[] getAbilityName() {
        String[] abilities = {"Spike Growth", "Any movement on a selected tile will inflict 2 damage."};
        return abilities;
    }

    String[] getSuperMoveName() {
        String[] superMove = {"Dragon Morph", "Move to a selected tile and deal 5-20 damage to every place on that tile."};
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


