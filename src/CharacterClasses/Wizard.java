class Wizard extends CharacterClass{
    private int upcast = 1;
    private static final String className = "Wizard";
    //0 means its a person attack, 1 is a tile attack, and 2 is no attack or attack everyone
    private static int[] _selectingTile={1,1,2};
    Wizard(){
        super(className,1,_selectingTile,0);
    }

    ///even indexes are names, odd indexes are decriptions
    String[] getAttackName() {
        String[] attacks = {"Fireball", "Hits everybody on the tile for 1-6 damage"};
        return attacks;
    }

    String[] getAbilityName() {
        String[] abilities = {"Shield", "For 2 turns, shield the entrance and exit of a chosen tile to prevent any incoming damage."};
        return abilities;
    }

    String[] getSuperMoveName() {
        String[] superMove = {"Meteor", "Everyone else takes 9-19 damage"};
        return superMove;
    }
////

    ////first is damage dealt, second is health healed, other effects can be called
    void Attack(int tileTargeted) {
        int totalDamage=Dice.RollDie(upcast,6,0);
        for (Player ptr : Tile.getTile(tileTargeted).getPlayerPtrs())
            DealDamage(totalDamage,ptr);
        upcast=1;
    }

    void Ability() {
        if(!(Tile.getTile(0).isFull()&&Tile.getTile(1).isFull()&&Tile.getTile(2).isFull()&&Tile.getTile(3).isFull()&&Tile.getTile(4).isFull()&&Tile.getTile(5).isFull()&&Tile.getTile(6).isFull()))
        {
            Tile[] totalTiles = new Tile[Tile.getAllTiles().length];
            int tile = (int) (Math.random() * 7);
            while (Tile.getTile(tile).isFull()) {
                tile = (int) (Math.random() * 7);
            }
            Shield ptr = new Shield(tile, 2,Player.getCurrentPlayer());
        }
        else{
            Player.getCurrentPlayer().addActionPoints(1);
            Player.getCurrentPlayer().resetActionsLeft(Player.getCurrentPlayer().getActionsLeft()+1);
        }
    }

    void SuperMove() {
        for (int i = 0;i < Player.getNumAlivePlayers();i++){
            if (!(Player.getPlayer(i).getCharacterClass().getName().equals("Wizard"))){
                DealDamage(Dice.RollDie(2,6,7),Player.getPlayer(i));
            }
        }
    }
    void EndTurn(){

    }
    int getUpcast(){
        return upcast;
    }
    void setUpcast(int newUpcast){
        upcast = newUpcast;
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
