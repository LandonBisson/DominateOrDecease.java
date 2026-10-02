class Ranger extends CharacterClass {
    private static final String className = "Ranger";
    private int modifier;
    private boolean[] allArrTypes;
    //0 means its a person attack, 1 is a tile attack, and 2 is no attack or attack everyone
    private static int[] _selectingTile={1,2,1};
    private int arrType;
    private int superActivated = 0;
    private int diceRoll;
    //                      0 is normal, 2 is push, 1 is fire, 3 is healing ... 1 means the player has it, 0 means it doesn't
    Ranger() {
        super(className,2,_selectingTile,0);
        allArrTypes = new boolean[]{true, false, false, false};
    }

    ///even indexes are names, odd indexes are decriptions
    String[] getAttackName() {
        String[] attacks;
        if (4 - Player.getNumPlayers() > 0)
            modifier = 4 - Player.getNumPlayers();
        else
            modifier = 0;

        attacks = new String[]{"Magic Arrow", "Hits everybody in a row of tiles for " + (1 + modifier) + "-" + (4 + modifier) + "damage" + "."};

        return attacks;
    }

    String[] getAbilityName() {
        String[] abilities = {"Research Arrows", "Fire Arrow", "Deal 3 more damages", "Push Arrow", "Everyone hit is pushed away.", "Healing Arrow", "You heal half of the damage dealt."};
        return abilities;
    }

    String[] getSuperMoveName() {
        String[] superMove = {"Volley of Arrows", "Next arrow shot will combine all types and fire two arrows at the farthest tile."};
        return superMove;
    }
////

    ////first is damage dealt, second is health healed, other effects can be called
    void Attack(int tile) {
        if (superActivated == 2){
            superActivated--;
            arrType = 0;
            Attack(tile);
        }
        else if (superActivated == 1){
            arrType++;
            if (arrType < 3)
                Attack(tile);
        }
        int arrHealCount = 0;
        modifier = 4 - Player.getNumAlivePlayers();
        if(modifier<0)
            modifier=0;
        if (arrType == 1)
            modifier += 3;
        if (superActivated > 0){
            //minus one to make it not repeat
            arrType = 4 - superActivated;
            superActivated--;
            Attack(tile);
            diceRoll = Dice.RollDie(1, 4, modifier);
        }
        else{
            diceRoll = Dice.RollDie(1, 4, modifier);
        }

        int currTile = Player.getCurrentPlayer().currTile();
        int otherTile = 0;
        int[] tempArray = {0, 1, 2, 3, 4, 5, 6};
        if (tile == 3) {
            if (currTile < 3)
                otherTile = tempArray[tempArray.length - currTile - 1];
            else if (currTile > 3)
                otherTile = tempArray[3-(currTile-3)];
        }
        else if ((currTile == 0 && tile == 6) || (currTile == 1 && tile == 5) || (currTile == 2 && tile == 4) || (currTile == 6 && tile == 0) || (currTile == 5 && tile == 1) || (currTile == 4 && tile == 2))
            otherTile = 3;
        else
            otherTile = -1;

        boolean ret = false;
        if (otherTile == -1){
            for (int i = 0; i < Tile.findAdjacent(currTile,true).length;i++){
                if (Tile.findAdjacent(currTile,true)[i] == tile)
                    ret = true;
            }
        }
        else if ((currTile == 0 && tile == 6) || (currTile == 1 && tile == 5) || (currTile == 2 && tile == 4) || (currTile == 6 && tile == 0) || (currTile == 5 && tile == 1) || (currTile == 4 && tile == 2))
            ret = true;
        if (!ret && tile != 3){
            DominateOrDecease.setInfoBoard("Tile is not a selectable tile.",1,90*Window.getHeight2()/100);
            Player.getCurrentPlayer().addActionPoints(1);
            Player.getCurrentPlayer().setCurrentActionsLeft(Player.getCurrentPlayer().getActionsLeft()+1);
            return;
        }
        Player[] ptr = Tile.getTile(tile).getPlayerPtrs();
        for (Player value : ptr) {
            if (!(Player.getCurrentPlayer()==value)) {
                DealDamage(diceRoll, value);
            }
            arrHealCount+=diceRoll;
        }
        ptr = Tile.getTile(currTile).getPlayerPtrs();
        for (Player player : ptr) {
            if (!(Player.getCurrentPlayer()==player))
                DealDamage(diceRoll, player);
            arrHealCount+=diceRoll;
        }
        if (otherTile != -1) {
            ptr = Tile.getTile(otherTile).getPlayerPtrs();
            for (int i = 0; i < ptr.length; i++) {
                if (!(Player.getCurrentPlayer()==ptr[i])) {
                    DealDamage(diceRoll, ptr[i]);
                }
                arrHealCount+=diceRoll;
            }
        }
        if(arrType == 3)
            Heal(arrHealCount/2);
        else{
            arrHealCount=0;
        }
        //Pushing Arrow used
        if (arrType == 2){
            if (tile == currTile){
                int[] tiles = Tile.findAdjacent(tile,false);
                for (Player peps : Tile.getTile(tile).getPlayerPtrs()){
                    int ranTile=tiles[(int)(Math.random()*tiles.length)];
                    peps.MoveAnywhere(ranTile,false);
                }
            }
            else if (otherTile == -1){
                for (Player peps : Tile.getTile(currTile).getPlayerPtrs()) {
                    peps.MoveAnywhere(tile,false);
                }
            }
            else if (otherTile == 3){
                for (Player peps : Tile.getTile(otherTile).getPlayerPtrs()) {
                    peps.MoveAnywhere(tile,false);
                }
                for (Player peps : Tile.getTile(currTile).getPlayerPtrs()) {
                    peps.MoveAnywhere(otherTile,false);
                }
            }
            else if (tile == 3){
                for (Player peps : Tile.getTile(tile).getPlayerPtrs()) {
                    peps.MoveAnywhere(otherTile,false);
                }
                for (Player peps : Tile.getTile(currTile).getPlayerPtrs()) {
                    peps.MoveAnywhere(tile,false);
                }
            }
            else {
                for (Player peps : Tile.getTile(3).getPlayerPtrs()) {
                    peps.MoveAnywhere(otherTile,false);
                }
                for (Player peps : Tile.getTile(currTile).getPlayerPtrs()) {
                    peps.MoveAnywhere(3,false);
                }
            }
            Player.getCurrentPlayer().MoveAnywhere(currTile,false);
        }
        diceRoll=0;
        arrType--;
        if (arrType < 0){
            arrType = 0;
            superActivated = 0;
        }
    }


    void Ability() {
        if(allArrTypes[MainMenu.makeArrowTrue(this,false)]){
            Player.getCurrentPlayer().addActionPoints(1);
            Player.getCurrentPlayer().resetActionsLeft(Player.getCurrentPlayer().getActionsLeft()+1);
        }
        else {
            allArrTypes[MainMenu.makeArrowTrue(this,false)]=true;
        }
        ChangeArrow(MainMenu.makeArrowTrue(this,true));
    }

    void SuperMove() {
        superActivated = 3;
        Player.getCurrentPlayer().addActionPoints(1);
    }
    void EndTurn(){

    }
    void ChangeArrow(int ArrowNum){
        if (allArrTypes[ArrowNum] == false){
   //         DominateOrDecease.setInfoBoard("Arrow Selected Has Not Been Unlocked",1,90*Window.getHeight2()/100);
            arrType = 0;
            return;
        }
        arrType = ArrowNum;
    }
    public boolean[] getAllArrTypes(){
        return allArrTypes;
    }
    public int getArrType(){
        return arrType;
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

