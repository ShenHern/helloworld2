package lib;

abstract class Pokemon{
    //attribute
    public int hp;
    private final String[] types = new String[2];
    private Attack[] attack_list;
    private String name;

    protected void setName(String name) {
        this.name = name;
    }

    protected String getName(){
        return this.name;
    }

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

    public void printAttackList(){
        for (int i = 0; i < this.attack_list.length; i++) {
            System.out.printf("%d.%s ", i+1, attack_list[i].name);
        }
    }

    /* method to return the attack used by a pokemon
     * returns an Attack object if present, returns null otherwise
     */
    public Attack returnAttackUsed(int user_selection){
        return attack_list[user_selection];
    }

    public void takeDamage(float damage){
        this.hp -= damage;
    }
}