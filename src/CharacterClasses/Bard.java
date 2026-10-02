class Bard extends CharacterClass {
    private int damageMod;
    private static final String className = "Bard";
    //0 means its a person attack, 1 is a tile attack, and 2 is no attack or attack everyone(aka nothing passed in)
    private static int[] _selectingTile={1,2,2};
    Bard(){
        super(className,1,_selectingTile,1);
    }


    ///even indexes are names, odd indexes are decriptions
    String[] getAttackName() {
            String[] attacks = {"Percussion Push", "Push an enemy one tile and deal 3-6 damage."};
            return attacks;
    }

    String[] getAbilityName() {
        String[] abilities = {"Rhythmic Recovery", "Heal 3-8 health and your next attack does 2 additional damage."};
        return abilities;
    }

    String[] getSuperMoveName() {
        String[] superMove = {"Supersonic Waves", "Push every character 2 tiles and deal 3-8 damage. Also, gain immunity for next 2 turns."};
        return superMove;
    }
////

    ////first is damage dealt, second is health healed, other effects can be called
    void Attack(int tile) {
        tile=Player.getCurrentPlayer().currTile();
        Player[] ptr = Tile.getTile(tile).getPlayerPtrs();
        int damage=Dice.RollDie(1,6,damageMod);
        for (Player rtp : ptr) {
            if (!(rtp.equals(Player.getCurrentPlayer())))
                DealDamage(damage, rtp);
        }

        int[] tiles = Tile.findAdjacent(tile,false);
        for (Player value : ptr){
            int ranTile=tiles[(int)(Math.random()*tiles.length)];
            value.MoveAnywhere(ranTile, false);
        }
        Player.getCurrentPlayer().MoveAnywhere(tile,false);

        if (damageMod == 2)
            damageMod = 0;
    }
    void Ability() {
        damageMod = 2;
        Heal(Dice.RollDie(1,6,0));
    }

    void SuperMove() {
        int[] tempArray = {0, 1, 2, 3, 4, 5, 6};
        int oppTile = -1;
        if (Player.getCurrentPlayer().currTile() != 3) {
            if (Player.getCurrentPlayer().currTile() < 3)
                oppTile = tempArray[tempArray.length - Player.getCurrentPlayer().currTile() - 1];
            else if (Player.getCurrentPlayer().currTile() > 3)
                oppTile = tempArray[3 - (Player.getCurrentPlayer().currTile() - 3)];
        }
        else{
            int tile=Player.getCurrentPlayer().currTile();
            Player[] ptr = Tile.getTile(tile).getPlayerPtrs();
            int[] tiles = Tile.findAdjacent(tile,false);
            for (Player value : ptr){
                int ranTile=tiles[(int)(Math.random()*tiles.length)];
                value.MoveAnywhere(ranTile, false);
            }
            Player.getCurrentPlayer().MoveAnywhere(tile,false);
        }
        int damage = Dice.RollDie(2,6,damageMod);
        if (oppTile != -1) {
            for(int i=0;i<Player.getNumPlayers();i++){
                if(!Player.getPlayer(i).equals(Player.getCurrentPlayer())){
                    if(Player.getCurrentPlayer().currTile() != 3)
                        Player.getPlayer(i).MoveAnywhere(oppTile,false);
                    DealDamage(damage,Player.getPlayer(i));
                }
            }
        }
    }
    void EndTurn(){

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
