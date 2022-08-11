package lib;

public class Mudkip extends Pokemon{
    //constructor
    public Mudkip(){
        //set pokemon hp
        this.hp = 60;
        //set pokemon name
        this.setName("Mudkip");
        //set pokemon type
        this.setType("WATER", "\0");

        //set pokemon attacks
        Attack watergun = new Attack(6, "Water Gun", "WATER");
        Attack[] mudkip_attacklist = {watergun};
        this.setAttackList(mudkip_attacklist);
    }
}
