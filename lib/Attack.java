package lib;

//Attack class to be used in Pokemon classes
public class Attack{
    final int power;
    public final String name;
    final String type;

    public Attack(int power, String name, String type){
        this.power = power;
        this.name = name;
        this.type = type;
    }
}
