package lib;

public class Torchick extends Pokemon{
    //constructor
    public Torchick(){
        //set pokemon hp
        this.hp = 20;
        //set pokemon name
        this.setName("Torchick");
        //set pokemon type
        this.setType("FIRE", "NONE");

        //set pokemon attacks
        Attack flameburst = new Attack(8, "Flame Burst", "FIRE");
        Attack[] attack_list = {flameburst};
        this.setAttackList(attack_list);
    }
}
