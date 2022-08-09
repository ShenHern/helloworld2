package lib;
import java.util.Scanner;

abstract class Pokemon{
    //attribute
    private final String[] types = new String[2];
    private Attack[] attack_list;

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
        this.attack_list = new Attack[attack_list.length];
        for (int i = 0; i < attack_list.length; i++) {
            this.attack_list[i] = attack_list[i];
        }
    }

    /* method to return the attack used by a pokemon
     * returns an Attack object if present, returns null otherwise
     */
    public Attack getAttackUsed(){
        System.out.println("Please enter an attack to use.");
        System.out.printf("\nMudkip:\n");
        for (int i = 0; i < this.attack_list.length; i++){
            System.out.printf("%d. %s   \n", i+1, this.attack_list[i].name);
        }
        
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