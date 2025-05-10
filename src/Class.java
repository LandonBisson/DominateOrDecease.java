abstract class CharacterClass {
    private String getName;
    ////0 index is the name, second index is decription
    abstract String[] getAttackName();
    abstract String[] getAbilityName();
    abstract String[] getSuperMoveName();
    private String name;
////

    ////first is damage dealt, second is health healed, other effects can be called
    abstract void Ability();
    abstract void SuperMove();
////
    public CharacterClass(String _name) {
        name=_name;
    }

    public String getName () {
        return name;
    }

    public static void DealDamage(int damage,Player ptr){
            ptr.modifyHealth(-1*damage);
    }
    public static void Heal(int health){
        Player.getCurrentPlayer().modifyHealth(health);
    }
    public static int RollDie(int NumDie, int DieSize,int add){
        int ret=0;
        while (NumDie > 0){
            ret += (int)(Math.random()*DieSize)+1;
            NumDie--;
        }
        return ret + add;
    }
    //////////////ACTUAL IMPLEMENTATIONS HERE
    ////all these are shifted up one because they are the damage calculators, pass an int into

////each of these to get the added modifier complete
    // d2 (coin flip animation) and get random number 1 or 2
    // d4 (dice animation) and get random number 1 or 2 or 3 or 4
    // d6 (dice animation) and get random number 1 or 2 or 3 or 4 or 5 or 6
////

}