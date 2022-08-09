package lib;

public class Mudkip extends Pokemon{
    //constructor
    public Mudkip(){
        //set pokemon type
        this.setType("WATER", "\0");

        //set pokemon attacks
        Attack watergun = new Attack(6, "Water Gun", "WATER");
        Attack[] mudkip_attacklist = {watergun};
        this.setAttackList(mudkip_attacklist);
    }
}