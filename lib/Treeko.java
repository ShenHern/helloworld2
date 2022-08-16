package lib;

public class Treeko extends Pokemon{
    //constructor
    public Treeko(){
        //set pokemon hp
        this.hp = 25;
        //set pokemon name
        this.setName("Treeko");
        //set pokemon type
        this.setType("GRASS", "NONE");

        //set pokemon attacks
        Attack leafblade = new Attack(7, "Leaf Blade", "GRASS");
        Attack[] attacklist = {leafblade};
        this.setAttackList(attacklist);
    }
}
