package lib;

public class Perilla extends Pokemon {
    public Perilla(){
        //set pokemon hp
        this.hp = 30;
        //set pokemon name
        this.setName("Perilla");
        //set pokemon type
        this.setType("GRASS", "NONE");

        //set pokemon attacks
        Attack sheeshee = new Attack(6, "SheeShee", "WATER");
        Attack[] attacklist = {sheeshee};
        this.setAttackList(attacklist);
    }
}
