package lib;

public class Mudkip extends Pokemon{
    //constructor
    public Mudkip(){
        //set pokemon hp
        this.hp = 30;
        //set pokemon name
        this.setName("Mudkip");
        //set pokemon type
        this.setType("WATER", "NONE");

        //set pokemon attacks
        Attack watergun = new Attack(6, "Water Gun", "WATER");
        Attack[] attacklist = {watergun};
        this.setAttackList(attacklist);
    }
}
