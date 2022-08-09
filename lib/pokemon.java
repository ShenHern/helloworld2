package lib;
import java.util.Scanner;

//Attack class to be used in Pokemon classes
class Attack{
    final int power;
    final String name;
    final String type;

    public Attack(int power, String name, String type){
        this.power = power;
        this.name = name;
        this.type = type;
    }
}

abstract class Pokemon{
    //attribute
    private final String[] types = new String[2];
    private final Attack[] attack_list = new Attack[4];

    //method used to set pokemon type during initialization of subclasses
    protected void setType(String type1, String type2) {
        this.types[0] = type1;
        this.types[1] = type2;
    }

    /* method to get pokemon type
     * returns: array of types [String, String] 
     */
    public String[] getPokemonTypes(){
        return this.types;
    }

    /* method to set the attack_list attribute during initialization of subclasses
     * result: this.attack_list [Attack(), Attack(), Attack(), Attack()]
     */
    protected void setAttackList(Attack[] attack_list) {
        for (int i = 0; i < attack_list.length; i++) {
            this.attack_list[i] = attack_list[i];
        }
    }

    /* method to return the attack used by a pokemon
     * returns an Attack object if present, returns null otherwise
     */
    public Attack getAttackUsed(){
        System.out.println("Please enter an attack to use.");
        System.out.printf("\nMudkip:1. %s 2. %s 3. %s 4. %s\n", this.attack_list[0], this.attack_list[1], this.attack_list[2], this.attack_list[3]);
        
        String user_selection;
        Scanner scanin = new Scanner(System.in);
        user_selection = scanin.nextLine();

        scanin.close();

        for (int i = 0; i < attack_list.length; i++){
            if (!user_selection.equals(this.attack_list[i].name)){
                continue;
            }
            return this.attack_list[i];
        }
        return null;
    }
}

class Mudkip extends Pokemon{
    public Mudkip(){
        this.setType("WATER", "\0");
        Attack watergun = new Attack(6, "Water Gun", "WATER");
        Attack[] mudkip_attacklist = {watergun};
        this.setAttackList(mudkip_attacklist);
    }
}