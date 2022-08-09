import lib.*;

class Test{
    public static void main(String[] args){
        lib.Mudkip mk = new lib.Mudkip();
        System.out.println(mk.getAttackUsed().name);
        System.out.println(mk.getPokemonTypes()[0]);
    }
}