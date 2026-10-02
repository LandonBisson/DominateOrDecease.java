class Cleric extends CharacterClass{
    private static final String className = "Cleric";
    private int countdown;
    //0 means its a person attack, 1 is a tile attack, and 2 is no attack or attack everyone(aka nothing passed in)
    private static int[] _selectingTile={0,2,2};
    Cleric(){
        super(className,1,_selectingTile,0);
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
        DealDamage(Dice.RollDie(1,4,2),ptr);
    }

    void Ability() {
        if (countdown<= 0) {
            countdown=2;
            Heal(Dice.RollDie(3, 6, 0));
        }
        else {
            countdown=2;
            Heal(Dice.RollDie(1, 6, 0));
        }
    }

    void SuperMove() {
        Player.getCurrentPlayer().setMaxHealth(Player.getCurrentPlayer().getMaxHealth()+5);
        Heal(Dice.RollDie(3,6,0));
        Player[] ptr = Tile.getTile(Player.getCurrentPlayer().currTile()).getPlayerPtrs();
        for (int i = 0; i < ptr.length; i++){
            if(ptr[i]!=Player.getCurrentPlayer())
                DealDamage(4,ptr[i]);
        }
    }

    void EndTurn(){
        countdown--;
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

