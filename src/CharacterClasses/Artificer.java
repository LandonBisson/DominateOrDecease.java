class Artificer extends CharacterClass {
    private static final String className = "Artificer";
    //0 means its a person attack, 1 is a tile attack, and 2 is no attack or attack everyone(aka nothing passed in)
    private static int[] _selectingTile={1,1,2};
    private int superCount = 0;
    private int cannonLevel;
    Artificer(){
        super(className,1,_selectingTile,0);
        cannonLevel=1;
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
        String[] superMove = {"Time Distortion", "Get 2 action points now, and at the end of your next two turns"};
        return superMove;
    }
////

    ////first is damage dealt, second is health healed, other effects can be called

    void  Ability() {
        if(!Tile.getTile(Player.getCurrentPlayer().currTile()).isFull()) {
            Cannon ptr = new Cannon(Player.getCurrentPlayer().currTile(), 2, cannonLevel, Player.getCurrentPlayer());
            cannonLevel = 1;
        }
        else{
            Player.getCurrentPlayer().addActionPoints(cannonLevel);
            Player.getCurrentPlayer().resetActionsLeft(Player.getCurrentPlayer().getActionsLeft()+1);
        }
    }

    void SuperMove() {
        Player.getCurrentPlayer().addActionPoints(2);
        superCount = 2;
    }
    void EndTurn(){
        if (superCount > 0) {
            Player.getCurrentPlayer().addActionPoints(2);
            superCount--;
        }
    }

    void Attack(int tile) {
        int dam=Dice.RollDie(1,2,0);
        for (Player ptr : Tile.getTile(tile).getPlayerPtrs())
            DealDamage(dam,ptr);
        if(!Tile.getTile(Player.getCurrentPlayer().currTile()).isFull()) {
            Poison ptr = new Poison(tile, 1, Player.getCurrentPlayer());
        }
    }
    void setCannonLevel(int level){
        cannonLevel = level;
    }
    int cannonLevel(){
        return cannonLevel;
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


